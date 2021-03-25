package com.ats.webapi.repository.newpos;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.newpos.NewPosBillItem;

public interface NewPosBillItemRepo extends JpaRepository<NewPosBillItem,Integer> {
	
	@Query(value="SELECT\n" + 
			" \n" + 
			"    a.id,\n" + 
			"    a.id AS item_id,\n" + 
			"    a.item_name, a.uom,\n" + 
			"    a.item_grp1 as cat_id,\n" + 
			"    a.item_grp2 as sub_cat_id,\n" + 
			"    a.item_image as item_img,\n" + 
			"    0 as qty,\n" + 
			"    a.item_tax1,\n" + 
			"    a.item_tax2,\n" + 
			"    a.item_tax3,\n" + 
			"    CASE WHEN (SELECT  fr_rate_cat FROM m_franchisee WHERE fr_id=:frId)=1\n" + 
			"    THEN a.item_rate1\n" + 
			" 	ELSE CASE WHEN (SELECT  fr_rate_cat FROM m_franchisee WHERE fr_id=:frId)=2\n" + 
			"    THEN  a.item_rate2\n" + 
			"    ELSE  a.item_rate3 END \n" + 
			"    END AS rate,\n" + 
			"    CASE WHEN (SELECT  fr_rate_cat FROM m_franchisee WHERE fr_id=:frId)=1\n" + 
			"    THEN a.item_mrp1\n" + 
			" 	ELSE CASE WHEN (SELECT  fr_rate_cat FROM m_franchisee WHERE fr_id=:frId)=2\n" + 
			"    THEN  a.item_mrp2\n" + 
			"    ELSE  a.item_mrp3 END \n" + 
			"    END AS mrp,\n" + 
			"    \n" + 
			"    \n" + 
			"\n" + 
			"    COALESCE(\n" + 
			"        COALESCE(\n" + 
			"            (\n" + 
			"                (COALESCE(b.reg_opening_stock,0) + COALESCE(c.reg,0)) -(COALESCE(d.grn_grn_qty,0) + COALESCE(e.reg,0))\n" + 
			"            ),\n" + 
			"            (COALESCE(c.reg,0) -(COALESCE(d.grn_grn_qty,0) + COALESCE(e.reg,0)))\n" + 
			"        ),\n" + 
			"        0\n" + 
			"    ) total_reg_stock\n" + 
			"\n" + 
			"FROM\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        m_item.*,m_item_sup.item_uom as uom\n" + 
			"    FROM\n" + 
			"        m_item , m_item_sup \n" + 
			"    WHERE\n" + 
			"        m_item.id IN(:itemList) AND m_item.id=m_item_sup.item_id\n" + 
			"    ORDER BY\n" + 
			"        item_name ASC\n" + 
			") a\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        m_fr_opening_stock_detail.*\n" + 
			"    FROM\n" + 
			"        m_fr_opening_stock_detail\n" + 
			"    WHERE\n" + 
			"        m_fr_opening_stock_detail.opening_stock_header_id IN(\n" + 
			"        SELECT\n" + 
			"            m_fr_opening_stock_header.opening_stock_header_id\n" + 
			"        FROM\n" + 
			"            m_fr_opening_stock_header\n" + 
			"        WHERE\n" + 
			"            m_fr_opening_stock_header.fr_id = :frId AND m_fr_opening_stock_header.month = :month AND m_fr_opening_stock_header.year = :year \n" + 
			"    )\n" + 
			") b\n" + 
			"ON\n" + 
			"    a.id = b.item_id\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        t_bill_detail.item_id,\n" + 
			"        COALESCE(\n" + 
			"            SUM(\n" + 
			"                CASE WHEN grn_type != 3 THEN bill_qty ELSE 0\n" + 
			"            END\n" + 
			"        ),\n" + 
			"        0\n" + 
			") AS reg,\n" + 
			"COALESCE(\n" + 
			"    SUM(\n" + 
			"        CASE WHEN grn_type = 3 THEN bill_qty ELSE 0\n" + 
			"    END\n" + 
			"),\n" + 
			"0\n" + 
			") AS sp\n" + 
			"FROM\n" + 
			"    t_bill_detail,\n" + 
			"    t_bill_header\n" + 
			"WHERE\n" + 
			"    t_bill_header.bill_no = t_bill_detail.bill_no AND t_bill_header.fr_id = :frId AND t_bill_header.status = 2 AND t_bill_header.bill_date BETWEEN :fromDt AND :toDt\n" + 
			"GROUP BY\n" + 
			"    t_bill_detail.item_id\n" + 
			") c\n" + 
			"ON\n" + 
			"    a.id = c.item_id\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        t_grn_gvn.item_id,\n" + 
			"        COALESCE(SUM(t_grn_gvn.grn_gvn_qty),\n" + 
			"        0) AS grn_grn_qty\n" + 
			"    FROM\n" + 
			"        t_grn_gvn\n" + 
			"    WHERE\n" + 
			"        t_grn_gvn.fr_id = :frId AND t_grn_gvn.grn_gvn_date BETWEEN :fromDt AND :toDt\n" + 
			"    GROUP BY\n" + 
			"        t_grn_gvn.item_id\n" + 
			") d\n" + 
			"ON\n" + 
			"    a.id = d.item_id\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        t_sell_bill_detail.item_id,\n" + 
			"        COALESCE(\n" + 
			"            SUM(\n" + 
			"                CASE WHEN bill_stock_type = 1 THEN qty ELSE 0\n" + 
			"            END\n" + 
			"        ),\n" + 
			"        0\n" + 
			") AS reg,\n" + 
			"COALESCE(\n" + 
			"    SUM(\n" + 
			"        CASE WHEN bill_stock_type = 2 THEN qty ELSE 0\n" + 
			"    END\n" + 
			"),\n" + 
			"0\n" + 
			") AS sp\n" + 
			"FROM\n" + 
			"    t_sell_bill_detail\n" + 
			"WHERE\n" + 
			"    t_sell_bill_detail.sell_bill_no IN(\n" + 
			"    SELECT\n" + 
			"        t_sell_bill_header.sell_bill_no\n" + 
			"    FROM\n" + 
			"        t_sell_bill_header\n" + 
			"    WHERE\n" + 
			"        t_sell_bill_header.fr_id = :frId AND t_sell_bill_header.bill_date BETWEEN :fromDt AND :toDt\n" + 
			")\n" + 
			"GROUP BY\n" + 
			"    t_sell_bill_detail.item_id\n" + 
			") e\n" + 
			"ON\n" + 
			"    a.id = e.item_id\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        m_fr_item_stock.item_id,\n" + 
			"        COALESCE(m_fr_item_stock.reorder_qty, 0) re_order_qty\n" + 
			"    FROM\n" + 
			"        m_fr_item_stock\n" + 
			"    WHERE\n" + 
			"        m_fr_item_stock.type =:frStockType \n" + 
			") f\n" + 
			"ON\n" + 
			"    a.id = f.item_id  \n" + 
			"ORDER BY a.item_name ASC",nativeQuery=true)
	List<NewPosBillItem> getNewPosBillItems(@Param("frId") int frId,
											@Param("fromDt") String fromDt,
											@Param("toDt") String toDt,
											@Param("month") int month,
											@Param("year") int year,
											@Param("frStockType") int frStockType,
											@Param("itemList") List<Integer> itemList);

	@Query(value="SELECT\n"
			+ "        a.id,\n"
			+ "        a.id AS item_id,\n"
			+ "        a.item_name,\n"
			+ "        a.uom,\n"
			+ "        a.item_grp1 as cat_id,\n"
			+ "        a.item_grp2 as sub_cat_id,\n"
			+ "        a.item_image as item_img,\n"
			+ "        0 as qty,\n"
			+ "        a.item_tax1,\n"
			+ "        a.item_tax2,\n"
			+ "        a.item_tax3,\n"
			+ "        CASE \n"
			+ "            WHEN (SELECT\n"
			+ "                fr_rate_cat \n"
			+ "            FROM\n"
			+ "                m_franchisee \n"
			+ "            WHERE\n"
			+ "                fr_id=:frId)=1     THEN a.item_rate1   \n"
			+ "            ELSE CASE \n"
			+ "                WHEN (SELECT\n"
			+ "                    fr_rate_cat \n"
			+ "                FROM\n"
			+ "                    m_franchisee \n"
			+ "                WHERE\n"
			+ "                    fr_id=:frId)=2     THEN  a.item_rate2     \n"
			+ "                ELSE  a.item_rate3 \n"
			+ "            END      \n"
			+ "        END AS rate,\n"
			+ "        CASE \n"
			+ "            WHEN (SELECT\n"
			+ "                fr_rate_cat \n"
			+ "            FROM\n"
			+ "                m_franchisee \n"
			+ "            WHERE\n"
			+ "                fr_id=:frId)=1     THEN a.item_mrp1   \n"
			+ "            ELSE CASE \n"
			+ "                WHEN (SELECT\n"
			+ "                    fr_rate_cat \n"
			+ "                FROM\n"
			+ "                    m_franchisee \n"
			+ "                WHERE\n"
			+ "                    fr_id=:frId)=2     THEN  a.item_mrp2     \n"
			+ "                ELSE  a.item_mrp3 \n"
			+ "            END      \n"
			+ "        END AS mrp,\n"
			+ "        0 as  total_reg_stock  \n"
			+ "    FROM\n"
			+ "        (     SELECT\n"
			+ "            m_item.*,\n"
			+ "            m_item_sup.item_uom as uom     \n"
			+ "        FROM\n"
			+ "            m_item ,\n"
			+ "            m_item_sup      \n"
			+ "        WHERE\n"
			+ "            m_item.id IN(:itemList) \n"
			+ "            AND m_item.id=m_item_sup.item_id     \n"
			+ "        ORDER BY\n"
			+ "            item_name ASC ) a  \n"
			+ "                ORDER BY\n"
			+ "                    a.item_name ASC",nativeQuery=true)
	List<NewPosBillItem> getNewPosBillItems(@Param("frId") int frId, @Param("itemList") List<Integer> itemList);
	

}

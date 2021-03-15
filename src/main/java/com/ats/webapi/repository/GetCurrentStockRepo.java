package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.GetCurrentStock;

public interface GetCurrentStockRepo extends JpaRepository<GetCurrentStock, Integer> {

	@Query(value="SELECT\n" + 
			"        t1.*,\n" + 
			"        COALESCE(t2.production_item_id,\n" + 
			"        0) AS production_item_id,\n" + 
			"        COALESCE(t2.production_qty,\n" + 
			"        0) AS production_qty,\n" + 
			"        COALESCE(t3.bill_item_id,\n" + 
			"        0) AS bill_item_id,\n" + 
			"        COALESCE(t3.bill_qty,\n" + 
			"        0) AS bill_qty,\n" + 
			"        COALESCE(t4.opening_stock,\n" + 
			"        0) AS opening_stock        \n" + 
			"    FROM\n" + 
			"        (SELECT\n" + 
			"            m_item.id,\n" + 
			"            m_item.item_id as item_code,\n" + 
			"            m_cat_sub.sub_cat_id,\n" + 
			"            m_cat_sub.sub_cat_name,\n" + 
			"            m_category.cat_id, m_category.cat_name,\n" + 
			"            m_item.item_name                  \n" + 
			"        FROM\n" + 
			"            m_item,\n" + 
			"            m_cat_sub,\n" + 
			"            m_category                 \n" + 
			"        WHERE\n" + 
			"            m_item.item_grp1=m_category.cat_id                           \n" + 
			"            AND   m_item.item_grp2=m_cat_sub.sub_cat_id                           \n" + 
			"            AND   m_item.del_status=0                           \n" + 
			"            AND   m_category.cat_id=:catId)t1               \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                pd.item_id AS production_item_id,\n" + 
			"                SUM(pd.production_qty) AS production_qty                           \n" + 
			"            FROM\n" + 
			"                t_production_plan_header ph,\n" + 
			"                t_production_plan_detail pd                           \n" + 
			"            WHERE\n" + 
			"                pd.production_header_id=ph.production_header_id                                   \n" + 
			"                AND     ph.production_date BETWEEN :prodFromDate AND :prodToDate                                    \n" + 
			"                AND     ph.production_status IN (\n" + 
			"                    4,5                 \n" + 
			"                )                                   \n" + 
			"                AND     ph.cat_id=:catId                                 \n" + 
			"                AND ph.del_status=0                                 \n" + 
			"            GROUP BY\n" + 
			"                pd.item_id                  \n" + 
			"        )t2                           \n" + 
			"            ON t1.id=t2.production_item_id           \n" + 
			"    LEFT JOIN\n" + 
			"        (\n" + 
			"            SELECT\n" + 
			"                bd.item_id AS bill_item_id,\n" + 
			"                SUM(bd.bill_qty) AS bill_qty,\n" + 
			"                SUM(bd.order_qty) AS order_qty                           \n" + 
			"            FROM\n" + 
			"                t_bill_header bh,\n" + 
			"                t_bill_detail bd                           \n" + 
			"            WHERE\n" + 
			"                bh.remark BETWEEN :fromTimeStamp AND :toTimeStamp                                  \n" + 
			"                AND     bh.bill_no = bd.bill_no                                   \n" + 
			"                AND     bd.cat_id =:catId                                 \n" + 
			"                AND bh.del_status=0                           \n" + 
			"            GROUP BY\n" + 
			"                bd.item_id                   \n" + 
			"        )t3                           \n" + 
			"            ON t1.id=t3.bill_item_id\n" + 
			"     LEFT JOIN\n" + 
			"     	(\n" + 
			"        	SELECT         \n" + 
			"                fd.op_total AS opening_stock,\n" + 
			"                fd.item_id\n" + 
			"        FROM            \n" + 
			"            finished_good_stock_detail fd,\n" + 
			"            finished_good_stock fh                    \n" + 
			"        WHERE                                    \n" + 
			"            fd.fin_stock_id=fh.fin_stock_id                           \n" + 
			"            AND   fh.fin_good_stock_status=0                           \n" + 
			"            AND   fh.fin_good_stock_date =:currStockDate                            \n" + 
			"            AND   fd.cat_id=:catId \n" + 
			"        ) t4\n" + 
			"        ON t1.id=t4.item_id",nativeQuery=true)
	

	List<GetCurrentStock> getPlanProdCurrStkItemQty(@Param("currStockDate") String currStockDate,
			@Param("prodFromDate") String prodFromDate, @Param("prodToDate") String prodToDate, @Param("fromTimeStamp") String fromTimeStamp,
			@Param("toTimeStamp") String toTimeStamp, @Param("catId") int catId);
}


//SELECT\n" + 
//"        t1.*,\n" + 
//"        COALESCE(t2.production_item_id,\n" + 
//"        0) AS production_item_id,\n" + 
//"        COALESCE(t2.production_qty,\n" + 
//"        0) AS production_qty,\n" + 
//"        COALESCE(t3.bill_item_id,\n" + 
//"        0) AS bill_item_id,\n" + 
//"        COALESCE(t3.bill_qty,\n" + 
//"        0) AS bill_qty\n" + 
//"         \n" + 
//"    FROM\n" + 
//"        (SELECT\n" + 
//"            m_item.id,\n" + 
//"            m_item.item_id as item_code,\n" + 
//"            m_cat_sub.sub_cat_id,\n" + 
//"            m_cat_sub.sub_cat_name,\n" + 
//"            m_category.cat_name,\n" + 
//"            fd.cat_id,\n" + 
//"            m_item.item_name,\n" + 
//"            fd.op_total AS opening_stock           \n" + 
//"        FROM\n" + 
//"            m_item,\n" + 
//"            m_cat_sub,\n" + 
//"            m_category,\n" + 
//"            finished_good_stock_detail fd,\n" + 
//"            finished_good_stock fh           \n" + 
//"        WHERE\n" + 
//"            m_item.item_grp1=m_category.cat_id              \n" + 
//"            AND   m_item.item_grp2=m_cat_sub.sub_cat_id              \n" + 
//"            AND  m_item.del_status=0              \n" + 
//"            AND   fd.item_id=m_item.id              \n" + 
//"            AND   fd.fin_stock_id=fh.fin_stock_id              \n" + 
//"            AND    fh.fin_good_stock_status=0              \n" + 
//"            AND    fh.fin_good_stock_date =:currStockDate               \n" + 
//"            AND    fd.cat_id=:catId)t1          \n" + 
//"    LEFT JOIN\n" + 
//"        (\n" + 
//"            SELECT\n" + 
//"                pd.item_id AS production_item_id,\n" + 
//"                SUM(pd.production_qty) AS production_qty              \n" + 
//"            FROM\n" + 
//"                t_production_plan_header ph,\n" + 
//"                t_production_plan_detail pd              \n" + 
//"            WHERE\n" + 
//"                pd.production_header_id=ph.production_header_id                  \n" + 
//"                AND     ph.production_date BETWEEN :prodFromDate AND :prodToDate                      \n" + 
//"                AND     ph.production_status IN (\n" + 
//"                    4,5\n" + 
//"                )                  \n" + 
//"                AND     ph.cat_id=:catId                 \n" + 
//"                AND ph.del_status=0                    \n" + 
//"            GROUP BY\n" + 
//"                pd.item_id         \n" + 
//"        )t2              \n" + 
//"            ON t1.id=t2.production_item_id      \n" + 
//"    LEFT JOIN\n" + 
//"        (\n" + 
//"            SELECT\n" + 
//"                bd.item_id AS bill_item_id,\n" + 
//"                SUM(bd.bill_qty) AS bill_qty,\n" + 
//"                SUM(bd.order_qty) AS order_qty              \n" + 
//"            FROM\n" + 
//"                t_bill_header bh,\n" + 
//"                t_bill_detail bd              \n" + 
//"            WHERE\n" + 
//"                bh.remark BETWEEN :fromTimeStamp AND :toTimeStamp                 \n" + 
//"                AND     bh.bill_no = bd.bill_no                  \n" + 
//"                AND     bd.cat_id =:catId               \n" + 
//"                AND bh.del_status=0              \n" + 
//"            GROUP BY\n" + 
//"                bd.item_id          \n" + 
//"        )t3              \n" + 
//"            ON t1.id=t3.bill_item_id      \n" + 
//"    
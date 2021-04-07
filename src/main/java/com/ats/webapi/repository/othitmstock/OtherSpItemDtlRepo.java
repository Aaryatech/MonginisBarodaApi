package com.ats.webapi.repository.othitmstock;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.otheritemstock.OtherSpItemDtl;

public interface OtherSpItemDtlRepo extends JpaRepository<OtherSpItemDtl, Integer> {

	@Query(value=" SELECT\n" + 
			" 		fr_id,\n" + 
			"        item_id,\n" + 
			"        item_code,\n" + 
			"        item_name,\n" + 
			"        spf_name,\n" + 
			"        order_qty,\n" + 
			"        menu_id,\n" + 
			"        menu_title,\n" + 
			"        order_amt,\n" + 
			"        fr_email\n" + 
			"    FROM\n" + 
			"        (SELECT\n" + 
			"         o.fr_id,\n" + 
			"            i.id AS item_id,\n" + 
			"            i.item_id AS item_code,\n" + 
			"            i.item_name,\n" + 
			"            'NA' AS spf_name,\n" + 
			"            o.order_qty,\n" + 
			"            o.order_mrp as order_amt,\n" + 
			"            menu.menu_id,\n" + 
			"            menu.menu_title,\n" + 
			"         	fr.fr_email \n" + 
			"        FROM\n" + 
			"            m_item i,\n" + 
			"            t_order o,\n" + 
			"            m_fr_menu_show menu, \n" + 
			"         	m_franchisee fr \n" + 
			"        WHERE\n" + 
			"            i.id=o.item_id \n" + 
			"            AND o.order_date = :orderDate\n" + 
			"            AND o.fr_id = fr.fr_id \n" + 
			"        	AND fr.fr_id = :frId\n" + 
			"            AND o.order_type!=5 \n" + 
			"            AND menu.menu_id=o.menu_id     \n" + 
			"        GROUP BY\n" + 
			"            i.id  \n" + 
			"        UNION\n" + 
			"        ALL   SELECT\n" + 
			"         	tsp.fr_id,\n" + 
			"            msp.sp_id as id,\n" + 
			"            msp.sp_code AS item_code ,\n" + 
			"            msp.sp_name AS item_name,\n" + 
			"            f.spf_name AS spf_name,\n" + 
			"            tsp.sp_selected_weight AS order_qty,\n" + 
			"            tsp.sp_grand_total as order_amt,\n" + 
			"            menu.menu_id,\n" + 
			"            menu.menu_title,\n" + 
			"         	fr.fr_email\n" + 
			"        FROM\n" + 
			"            t_sp_cake tsp,\n" + 
			"            m_sp_cake msp,\n" + 
			"            m_fr_menu_show menu,\n" + 
			"            m_sp_flavour f, \n" + 
			"         	m_franchisee fr\n" + 
			"        WHERE\n" + 
			"            tsp.order_date = :orderDate\n" + 
			"            AND     msp.sp_id=tsp.sp_id \n" + 
			"            AND     tsp.menu_id=menu.menu_id \n" + 
			"            AND     tsp.sp_flavour_id=f.spf_id \n" + 
			"            AND     tsp.fr_id = fr.fr_id \n" + 
			"        	AND fr.fr_id = :frId\n" + 
			"    )a \n" + 
			"ORDER BY\n" + 
			"    fr_id",nativeQuery=true)
	
	public List<OtherSpItemDtl> getOtherSpItemDtls(@Param("orderDate") String orderDate, @Param("frId") int frId);
}

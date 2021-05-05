package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.GetOrder;
import com.ats.webapi.model.GetOrderNew;

public interface GetOrderNewRepository extends JpaRepository<GetOrderNew, Integer> {
	
	
	@Query(value="SELECT\n" + 
			"        m_franchisee.fr_name ,\n" + 
			"        m_item.id ,\n" + 
			"        m_item.item_name ,\n" + 
			"        t_order.order_id,\n" + 
			"        t_order.order_qty ,\n" + 
			"        m_category.cat_name,\n" + 
			"        t_order.delivery_date,\n" + 
			"        t_order.is_bill_generated as is_edit,\n" + 
			"        t_order.edit_qty,\n" + 
			"        t_order.is_positive,\n" + 
			"        m_fr_menu_show.menu_title AS menu_name,\n" + 
			"        m_fr_configure.disc_per AS menu_discper,\n" + 
			"        t_order.grn_type AS  grn_per,\n" + 
			"        t_order.order_rate  AS  billing_rate\n" + 
			"    FROM\n" + 
			"        m_franchisee ,\n" + 
			"        m_category,\n" + 
			"        m_item,\n" + 
			"        t_order,\n" + 
			"        m_fr_menu_show,\n" + 
			"        m_fr_configure\n" + 
			"    WHERE\n" + 
			"        t_order.production_date = :date \n" + 
			"        AND t_order.is_edit=0 \n" + 
			"        AND t_order.menu_id=m_fr_menu_show.menu_id\n" + 
			"        AND t_order.menu_id=m_fr_configure.menu_id\n" + 
			"        AND t_order.item_id = m_item.id \n" + 
			"        AND t_order.menu_id IN (\n" + 
			"           :menuId\n" + 
			"        ) \n" + 
			"        AND t_order.fr_id = m_franchisee.fr_id \n" + 
			"        AND t_order.order_type = m_category.cat_id \n" + 
			"    ORDER BY\n" + 
			"        m_franchisee.fr_id,\n" + 
			"        m_category.cat_id,\n" + 
			"        m_item.item_grp2,\n" + 
			"        m_item.item_name",nativeQuery=true)
				List<GetOrderNew> findAllNativeAllFr(@Param("menuId")List<String> menuId,@Param("date")String date);


}

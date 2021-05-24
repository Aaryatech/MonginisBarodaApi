package com.ats.webapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.GetOrder;
import com.ats.webapi.model.NewGetOrder;

public interface NewGetOrderRepository extends JpaRepository<NewGetOrder, Integer> {
	
	
	@Query(value="  SELECT\n" + 
			"        m_franchisee.fr_name ,\n" + 
			"        m_item.id ,\n" + 
			"        m_item.item_name ,\n" + 
			"      \n" + 
			"        t_order.order_id,\n" + 
			"        t_order.order_qty ,\n" + 
			"        m_category.cat_name,\n" + 
			"        t_order.delivery_date,\n" + 
			"        t_order.is_bill_generated as is_edit,\n" + 
			"        t_order.edit_qty,\n" + 
			"        t_order.is_positive ,\n" + 
			"        m_fr_menu_show.menu_title,\n" + 
			"         m_fr_configure.disc_per,\n" + 
			"        m_fr_configure.grn_per,\n" + 
			"          m_item.item_rate1\n" + 
			"    FROM\n" + 
			"        m_franchisee ,\n" + 
			"        m_category,\n" + 
			"        m_item,\n" + 
			"        t_order,\n" + 
			"        m_fr_configure,\n" + 
			"        m_fr_menu_show\n" + 
			"       \n" + 
			"    WHERE\n" + 
			"        t_order.production_date = :date\n" + 
			"        AND t_order.is_edit=0 \n" + 
			"        AND t_order.item_id = m_item.id \n" + 
			"        AND t_order.menu_id IN (\n" + 
			"          :menuId\n" + 
			"        ) \n" + 
			"        AND t_order.fr_id = m_franchisee.fr_id \n" + 
			"        AND t_order.order_type = m_category.cat_id \n" + 
			"        AND m_fr_menu_show.menu_id=:menuId\n" + 
			"         AND m_fr_configure.menu_id=:menuId\n" + 
			"    ORDER BY\n" + 
			"        m_franchisee.fr_id,\n" + 
			"        m_category.cat_id,\n" + 
			"        m_item.item_grp2,\n" + 
			"        m_item.item_name",nativeQuery=true)
	List<NewGetOrder> getNewOrdersForAllFr(@Param("menuId")List<String> menuId,@Param("date")String date);
	
	
	@Query(value="SELECT\n" + 
			"    m_franchisee.fr_name,\n" + 
			"    m_item.id,\n" + 
			"    m_item.item_name,\n" + 
			"    t_order.order_id,\n" + 
			"    t_order.order_qty,\n" + 
			"    m_category.cat_name,\n" + 
			"    t_order.delivery_date,\n" + 
			"    t_order.is_edit,\n" + 
			"    t_order.edit_qty,\n" + 
			"    t_order.is_positive,\n" + 
			"    m_fr_menu_show.menu_title,\n" + 
			"    m_fr_configure.disc_per,\n" + 
			"    m_fr_configure.grn_per,\n" + 
			"    m_item.item_rate1\n" + 
			"FROM\n" + 
			"    m_franchisee,\n" + 
			"    m_category,\n" + 
			"    m_item,\n" + 
			"    t_order,\n" + 
			"    m_fr_configure,\n" + 
			"    m_fr_menu_show\n" + 
			"WHERE\n" + 
			"    t_order.production_date =:date  AND t_order.is_edit = 0 AND t_order.fr_id IN(:frId) AND t_order.item_id = m_item.id AND t_order.menu_id IN(:menuId) AND t_order.fr_id = m_franchisee.fr_id AND t_order.order_type = m_category.cat_id AND t_order.menu_id = m_fr_menu_show.menu_id AND t_order.menu_id = m_fr_configure.menu_id\n" + 
			"ORDER BY\n" + 
			"    m_franchisee.fr_id,\n" + 
			"    m_category.cat_id,\n" + 
			"    m_item.item_grp2,\n" + 
			"    m_item.item_name",nativeQuery=true)
	List<NewGetOrder> findAllNative(@Param("frId")List<String>  frId,@Param("menuId") List<String> menuId,@Param("date")String date);
	
	
	
	
	
	
	
	
	

}

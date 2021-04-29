package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.OrderItemSubCatTotal;
@Repository
public interface OrderItemSubCatTotalRepository extends JpaRepository<OrderItemSubCatTotal, Integer>{

	@Query(value="SELECT m_cat_sub.sub_cat_id,m_cat_sub.sub_cat_name,SUM(t_order.order_qty) as total FROM m_item,t_order,m_cat_sub WHERE t_order.production_date =:date AND t_order.is_edit=0 AND t_order.fr_id IN (:frId) AND 	t_order.item_id = m_item.id AND t_order.menu_id IN (:menuId)  and m_cat_sub.sub_cat_id=m_item.item_grp2 group by m_cat_sub.sub_cat_id ",nativeQuery=true)
	List<OrderItemSubCatTotal> findQtyTotalSubCatWise(@Param("frId")List<String>  frId,@Param("menuId") List<String> menuId,@Param("date")String date);

	@Query(value="SELECT m_cat_sub.sub_cat_id,m_cat_sub.sub_cat_name,SUM(t_order.order_qty) as total FROM m_item,t_order,m_cat_sub WHERE t_order.production_date =:date AND t_order.is_edit=0 AND  	t_order.item_id = m_item.id AND t_order.menu_id IN (:menuId)  and m_cat_sub.sub_cat_id=m_item.item_grp2 group by m_cat_sub.sub_cat_id ",nativeQuery=true)
	List<OrderItemSubCatTotal> findQtyTotalSubCatWiseAllFr(@Param("menuId") List<String> menuId,@Param("date")String date);

	//Sachin 12-02-2020
	@Query(value="SELECT m_cat_sub.sub_cat_id,m_cat_sub.sub_cat_name,SUM(t_order.order_qty) as total FROM m_item,t_order,m_cat_sub WHERE t_order.production_date =:date AND t_order.is_edit=0 AND t_order.fr_id IN (:frId) AND 	t_order.item_id = m_item.id AND t_order.menu_id IN (:menuId)  and m_cat_sub.sub_cat_id=m_item.item_grp2 and m_item.id IN (:itemId) group by m_cat_sub.sub_cat_id ",nativeQuery=true)
	List<OrderItemSubCatTotal> findQtyTotalSubCatWiseSpecItem(@Param("frId")List<String>  frId,@Param("menuId") List<String> menuId,@Param("date")String date,@Param("itemId") List<String> itemId);
	//Sachin 12-02-2020
	@Query(value="SELECT m_cat_sub.sub_cat_id,m_cat_sub.sub_cat_name,SUM(t_order.order_qty) as total FROM m_item,t_order,m_cat_sub WHERE t_order.production_date =:date AND t_order.is_edit=0 AND  	t_order.item_id = m_item.id AND t_order.menu_id IN (:menuId)  and m_cat_sub.sub_cat_id=m_item.item_grp2 and m_item.id IN (:itemId) group by m_cat_sub.sub_cat_id ",nativeQuery=true)
	List<OrderItemSubCatTotal> findQtyTotalSubCatWiseAllFrSpecItem(@Param("menuId") List<String> menuId,@Param("date")String date,@Param("itemId") List<String> itemId);


}

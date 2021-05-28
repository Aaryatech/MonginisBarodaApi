package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.report.PDispatchReport;

@Repository
public interface PDispatchReportRepository extends JpaRepository<PDispatchReport, Integer>{

	@Query(value="select 'NA' AS slip_no, 'NA' AS cake_no, t_order.order_id,m_category.cat_id,m_category.cat_name,m_cat_sub.sub_cat_id,t_order.fr_id,m_franchisee.fr_name,t_order.item_id,m_item.item_name,SUM(t_order.order_qty) as order_qty,SUM(t_order.edit_qty) as edit_qty,t_order.is_bill_generated from t_order,m_category,m_franchisee,m_item,m_cat_sub\n" + 
			"	where  t_order.menu_id IN(:menuId) and t_order.order_id In(select order_id from t_order where delivery_date=:productionDateYMD and fr_id in(:frId)) And (select sub_cat_id from m_fr_menu_show where menu_id=t_order.menu_id) In :categories  " + 
			"	And (select cat_id from m_fr_menu_show where menu_id=t_order.menu_id)=m_category.cat_id And t_order.fr_id=m_franchisee.fr_id and m_item.id=t_order.item_id And m_cat_sub.sub_cat_id=m_item.item_grp2  " + 
			"	group by t_order.item_id,t_order.fr_id "
			/*
			 * +
			 * "order by t_order.fr_id,m_item.item_grp1 asc,m_item.item_grp2,m_item.item_name,m_item.item_sort_id asc,m_item.item_mrp2 asc"
			 */
			+ " UNION ALL "
			+ " SELECT " + 
			"        'reg' AS slip_no, " + 
			"        'reg' AS cake_no, " + 
			"        t_regular_sp_cake.rsp_id as order_id, " + 
			"        m_category.cat_id, " + 
			"        m_category.cat_name, " + 
			"        m_cat_sub.sub_cat_id, " + 
			"        t_regular_sp_cake.fr_id, " + 
			"        m_franchisee.fr_name, " + 
			"        t_regular_sp_cake.item_id, " + 
			"        m_item.item_name, " + 
			"        SUM(t_regular_sp_cake.qty) as order_qty, " + 
			"        SUM(t_regular_sp_cake.qty) as edit_qty, " + 
			"        t_regular_sp_cake.is_bill_generated  " + 
			"         " + 
			"       " + 
			"         FROM " + 
			"        t_regular_sp_cake, " + 
			"        m_category, " + 
			"        m_franchisee, " + 
			"        m_item,\n" + 
			"        m_cat_sub   " + 
			"         " + 
			"        WHERE  " + 
			"        t_regular_sp_cake.menu_id IN (:menuId) " + 
			"        and t_regular_sp_cake.rsp_delivery_dt=:productionDateYMD and t_regular_sp_cake.fr_id in (:frId) " + 
			"        and t_regular_sp_cake.item_id=m_item.id and m_item.item_grp1=m_category.cat_id AND m_cat_sub.sub_cat_id=m_item.item_grp2 AND m_cat_sub.sub_cat_id IN (:categories)  " + 
			"        and t_regular_sp_cake.fr_id=m_franchisee.fr_id " + 
			"        " + 
			"        GROUP BY " + 
			"        t_regular_sp_cake.item_id, m_franchisee.fr_id " + 
			"        " + 
			/*
			 * "        ORDER BY " + "             t_regular_sp_cake.fr_id, " +
			 * "        m_item.item_grp1 asc ," + "        m_item.item_grp2, " +
			 * "        m_item.item_name, " + "        m_item.item_sort_id asc, " +
			 * "        m_item.item_mrp2 asc " +
			 */
			"        " + 
			"",nativeQuery=true)
	List<PDispatchReport> getPDispatchItemReport(@Param("productionDateYMD")String productionDateYMD,@Param("frId") List<String> frId,@Param("categories") List<Integer> categories,@Param("menuId") List<Integer> menuId);// cat_id changed to to sub_cat_id (select sub_cat_id from m_fr_menu_show where menu_id=)13 feb 19 
	
	//sumit
	@Query(value="select  'NA' AS slip_no, 'NA' AS cake_no, t_order.order_id,m_category.cat_id,m_category.cat_name,m_cat_sub.sub_cat_id,t_order.fr_id,m_franchisee.fr_name,t_order.item_id,m_item.item_name,SUM(t_order.order_qty) as order_qty,SUM(t_order.edit_qty) as edit_qty,t_order.is_bill_generated from t_order,m_category,m_franchisee,m_item,m_cat_sub\n" + 
			"	where t_order.order_id In(select order_id from t_order where delivery_date=:productionDateYMD and fr_id in(:frId) And t_order.menu_id in(:menu) and t_order.item_id in(:ItemId) )  \n" + 
			"	And (select cat_id from m_fr_menu_show where menu_id=t_order.menu_id)=m_category.cat_id And t_order.fr_id=m_franchisee.fr_id and m_item.id=t_order.item_id And m_cat_sub.sub_cat_id=m_item.item_grp2 \n" + 
			"	group by t_order.item_id,t_order.fr_id "
			+ " "
			+ " UNION ALL  " + 
			"		  SELECT  " + 
			"			  'reg' AS slip_no,    " + 
			"		  'reg' AS cake_no,     " + 
			"		      t_regular_sp_cake.rsp_id as order_id,     " + 
			"		       m_category.cat_id,  " + 
			"		      m_category.cat_name,     " + 
			"			       m_cat_sub.sub_cat_id,    " + 
			"		         t_regular_sp_cake.fr_id,  " + 
			"		        m_franchisee.fr_name,  " + 
			"			        t_regular_sp_cake.item_id,   " + 
			"			        m_item.item_name,  " + 
			"			        SUM(t_regular_sp_cake.qty) as order_qty,     " + 
			"			        SUM(t_regular_sp_cake.qty) as edit_qty,     " + 
			"			        t_regular_sp_cake.is_bill_generated   " + 
			"			          " + 
			"			        " + 
			"			         FROM  " + 
			"			        t_regular_sp_cake,  " + 
			"			        m_category,  " + 
			"			        m_franchisee,  " + 
			"			        m_item,  " + 
			"			        m_cat_sub    " + 
			"			          " + 
			"			        WHERE   " + 
			"			        t_regular_sp_cake.menu_id IN (:menu)  " + 
			"			        and t_regular_sp_cake.rsp_delivery_dt=:productionDateYMD and t_regular_sp_cake.fr_id in (:frId)  " + 
			"			        and t_regular_sp_cake.item_id=m_item.id and m_item.item_grp1=m_category.cat_id AND m_cat_sub.sub_cat_id=m_item.item_grp2 AND   t_regular_sp_cake.item_id IN (:ItemId)  " + 
			"			        and t_regular_sp_cake.fr_id=m_franchisee.fr_id  " + 
			"			         " + 
			"			        GROUP BY  " + 
			"			        t_regular_sp_cake.item_id, m_franchisee.fr_id " + 
			"",nativeQuery=true)
	List<PDispatchReport> getPDispatchItemReportMenuwise(@Param("productionDateYMD")String productionDateYMD,@Param("frId") List<String> frId,@Param("menu") List<Integer> menu,@Param("ItemId") List<Integer> ItemId);// cat_id changed to to sub_cat_id (select sub_cat_id from m_fr_menu_show where menu_id=)

//---------------------------------------------------------DispatchReport Franchise wise Special Cake ----SUMIT---19 APRIL 2019------------------------------------------------
	@Query(value="select t_sp_cake.sp_order_no AS order_id,t_sp_cake.fr_id,m_franchisee.fr_name,t_sp_cake.sp_id as item_id,   GROUP_CONCAT(t_sp_cake.slip_no )  As slip_no,  GROUP_CONCAT(t_sp_cake.sp_delivery_place )  As cake_no,t_sp_cake.item_id as item_name,COUNT(t_sp_cake.sp_order_no) as order_qty,COUNT(t_sp_cake.sp_order_no) as edit_qty,t_sp_cake.is_bill_generated,m_franchisee.fr_route_id AS cat_id, m_franchise_sup.no_in_route AS sub_cat_id,m_franchisee.fr_code as cat_name from t_sp_cake,m_franchisee,m_franchise_sup where t_sp_cake.sp_delivery_date=(:deliveryDateYMD) AND t_sp_cake.menu_id in(:menu) AND m_franchisee.fr_id=t_sp_cake.fr_id AND m_franchisee.fr_id=m_franchise_sup.fr_id AND m_franchisee.fr_id IN (:frId) GROUP BY t_sp_cake.fr_id ORDER BY m_franchisee.fr_route_id,m_franchise_sup.no_in_route" + 
			"",nativeQuery=true)
	List<PDispatchReport> getPDispatchFranchisewiseSpCake(@Param("deliveryDateYMD")String deliveryDateYMD,@Param("frId") List<String> frId,@Param("menu") List<Integer> menu);
}

package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.DispatchStationItem;

public interface DispatchReportRepositoryForItemwiseMin extends JpaRepository<DispatchStationItem, Integer>{

	@Query(value="select UUID() as uuid, \n" + 
			"        CONCAT(i.id, m_franchisee.fr_id, i.item_mrp2) AS id,\n" + 
			"        i.id as item_id,\n" + 
			"        i.item_name,\n" + 
			"        i.item_mrp2,\n" + 
			"        m_franchisee.fr_id,\n" + 
			"        m_franchisee.fr_name, m_franchisee.fr_route_id,i.item_grp2\n" + 
			"        coalesce((select\n" + 
			"            SUM(t_order.order_qty) \n" + 
			"        from\n" + 
			"            t_order \n" + 
			"        where\n" + 
			"            t_order.order_id In(         select\n" + 
			"                order_id          \n" + 
			"            from\n" + 
			"                t_order          \n" + 
			"            where\n" + 
			"                delivery_date=:date             \n" + 
			"                and  t_order.fr_id=m_franchisee.fr_id\n" + 
			"                and t_order.menu_id in (:menuIds)     )        \n" + 
			"            And t_order.fr_id=m_franchisee.fr_id  and  t_order.fr_id=m_franchisee.fr_id    \n" + 
			"            and i.id=t_order.item_id),0 ) as order_qty\n" + 
			"        \n" + 
			"    from\n" + 
			"        m_item i,\n" + 
			"       m_franchisee \n" + 
			"    where\n" + 
			"       i.del_status=0   \n" + 
			"        and item_mrp2 IN (:stationNos) AND m_franchisee.fr_id IN(:frList)\n" + 
			"    order by\n" + 
			"    m_franchisee.fr_id,\n" + 
			"        item_grp2 asc ,\n" + 
			"        item_sort_id asc\n" + 
			"",nativeQuery=true)
	List<DispatchStationItem> getItemByFrIdAndDateMin(@Param("stationNos")List<Integer> stationNos,@Param("date") String date,@Param("frList") List<Integer> frList,
			@Param("menuIds")List<Integer> menuIds);
	
	@Query(value="select UUID() as uuid, \n" + 
			"			        CONCAT(i.id, m_franchisee.fr_id, i.item_mrp2) AS id, \n" + 
			"			        i.id as item_id, \n" + 
			"			        i.item_name, \n" + 
			"			        i.item_mrp2, \n" + 
			"			        m_franchisee.fr_id, \n" + 
			"			        m_franchisee.fr_name,  \n" + 
			"			        SUM(t_order.order_qty) AS   order_qty, m_franchisee.fr_route_id,i.item_grp2 \n" + 
			"			        from \n" + 
			"			            t_order,m_item i, \n" + 
			"			       m_franchisee\n" + 
			"  \n" + 
			"			            where \n" + 
			"			               t_order.delivery_date=:date          \n" + 
			"			                and  t_order.fr_id=m_franchisee.fr_id \n" + 
			"			                and t_order.menu_id in (:menuIds)             \n" + 
			"			                 \n" + 
			"			            and i.id=t_order.item_id AND\n" + 
			"			         \n" + 
			"			          \n" + 
			"			       i.del_status=0    \n" + 
			"			        and item_mrp2 IN (:stationNos) AND m_franchisee.fr_id IN(:frList) \n" + 
			"                   group by  t_order.item_id,t_order.fr_id"+
			"			    order by \n" + 
			"			    m_franchisee.fr_id, \n" + 
			"			        item_grp2 asc , \n" + 
			"			        item_sort_id asc \n" + 
			"			",nativeQuery=true)
	List<DispatchStationItem> getItemByFrIdAndDateMin1(@Param("stationNos")List<Integer> stationNos,@Param("date") String date,@Param("frList") List<Integer> frList,
			@Param("menuIds")List<Integer> menuIds);
	
	//Sachin 23-02-2021
	@Query(value="select UUID() as uuid, \n" + 
			"			        CONCAT(i.id, m_franchisee.fr_id, i.item_mrp2) AS id, \n" + 
			"			        i.id as item_id, \n" + 
			"			        i.item_name, \n" + 
			"			        i.item_mrp2,i.item_grp1, \n" + 
			"			        m_franchisee.fr_id, \n" + 
			"			        m_franchisee.fr_name,  \n" + 
			"			        SUM(t_order.order_qty) AS   order_qty, m_franchisee.fr_route_id,i.item_grp2 \n" + 
			"			        from \n" + 
			"			            t_order,m_item i, \n" + 
			"			       m_franchisee\n" + 
			"  \n" + 
			"			            where \n" + 
			"			               t_order.delivery_date=:date          \n" + 
			"			                and  t_order.fr_id=m_franchisee.fr_id \n" + 
			"			                and t_order.menu_id in (:menuIds)             \n" + 
			"			                 \n" + 
			"			            and i.id=t_order.item_id AND i.item_grp1 IN (:catId) AND " + 
			"			         \n" + 
			"			          \n" + 
			"			       i.del_status=0    \n" + 
			"			      AND m_franchisee.fr_id IN(:frList) \n" + 
			"                   group by  t_order.item_id,t_order.fr_id"+
			"			    order by \n" + 
			"			    m_franchisee.fr_id, \n" + 
			"			        item_grp2  \n" + 
			"			         \n" + 
			"			",nativeQuery=true)
	List<DispatchStationItem> getItemByFrIdAndDateMin1New(@Param("date") String date,@Param("frList") List<Integer> frList,
			@Param("menuIds")List<Integer> menuIds, 	@Param("catId") List<Integer> catId);

	@Query(value="select UUID() as uuid, \n" + 
			"			        CONCAT(i.id, m_franchisee.fr_id, i.item_mrp2) AS id, \n" + 
			"			        i.id as item_id, \n" + 
			"			        i.item_name, \n" + 
			"			        i.item_mrp2,i.item_grp1, \n" + 
			"			        m_franchisee.fr_id, \n" + 
			"			        m_franchisee.fr_name,  \n" + 
			"			        SUM(t_regular_sp_cake.qty) AS   order_qty, m_franchisee.fr_route_id,i.item_grp2 \n" + 
			"			        from \n" + 
			"			            t_regular_sp_cake,m_item i, \n" + 
			"			       m_franchisee\n" + 
			"  \n" + 
			"			            where \n" + 
			"			               t_regular_sp_cake.rsp_delivery_dt=:date          \n" + 
			"			                and  t_regular_sp_cake.fr_id=m_franchisee.fr_id \n" + 
			"			                and t_regular_sp_cake.menu_id in (:menuIds)             \n" + 
			"			                 \n" + 
			"			            and i.id=t_regular_sp_cake.item_id AND i.item_grp1 IN (:catId) AND " + 
			"			         \n" + 
			"			          \n" + 
			"			       i.del_status=0    \n" + 
			"			      AND m_franchisee.fr_id IN(:frList) \n" + 
			"                   group by  t_regular_sp_cake.item_id,t_regular_sp_cake.fr_id"+
			"			    order by \n" + 
			"			    m_franchisee.fr_id, \n" + 
			"			        item_grp2  \n" + 
			"			         \n" + 
			"			",nativeQuery=true)
	List<DispatchStationItem> getItemByFrIdAndDateMin1New_REGSP(@Param("date") String date,@Param("frList") List<Integer> frList,
			@Param("menuIds")List<Integer> menuIds, 	@Param("catId") List<Integer> catId);

	
	@Query(value=" 	select t1.uuid,t1.id,t1.item_id,t1.item_name,t1.item_mrp2,t1.item_grp1,t1.fr_id,t1.fr_name,\n" + 
			"  IFNULL((t1.t_order_qty+t1.reg_sp_cake_qty),0) as order_qty,t1.fr_route_id,t1.item_grp2\n" + 
			" from( " + 
			" select " + 
			"        UUID() as uuid,\n" + 
			"        CONCAT(i.id,\n" + 
			"        m_franchisee.fr_id,\n" + 
			"        i.item_mrp2) AS id,\n" + 
			"        i.id as item_id,\n" + 
			"        i.item_name,\n" + 
			"        i.item_mrp2,\n" + 
			"        i.item_grp1,\n" + 
			"        m_franchisee.fr_id,\n" + 
			"        m_franchisee.fr_name,\n" + 
			"        coalesce(( select IFNULL(SUM(t_order.order_qty),0) from t_order where\n" + 
			"        t_order.delivery_date=:date                             \n" + 
			"        and  t_order.fr_id=m_franchisee.fr_id                     \n" + 
			"        and t_order.menu_id in (" + 
			"            :menuIds" + 
			"        )                                                  \n" + 
			"        and i.id=t_order.item_id group by t_order.item_id,t_order.fr_id )) as t_order_qty ,\n" + 
			" " + 
			" coalesce((select IFNULL(SUM(t_regular_sp_cake.qty),0) from t_regular_sp_cake where " + 
			"        t_regular_sp_cake.rsp_delivery_dt=:date                              \n" + 
			"        and  t_regular_sp_cake.fr_id=m_franchisee.fr_id                     \n" + 
			"        and t_regular_sp_cake.menu_id in ( " + 
			"           :menuIds " + 
			"        )                                                  \n" + 
			"        and i.id=t_regular_sp_cake.item_id group by t_regular_sp_cake.item_id,t_regular_sp_cake.fr_id  )) as reg_sp_cake_qty , " + 
			"\n" + 
			"\n" + 
			"\n" + 
			"        m_franchisee.fr_route_id,\n" + 
			"        i.item_grp2             \n" + 
			"    from\n" + 
			"       \n" + 
			"        m_item i,\n" + 
			"        m_franchisee                   \n" + 
			"    where\n" + 
			"       \n" + 
			"        i.item_grp1 IN (" + 
			"             :catId " + 
			"        ) \n" + 
			"        AND                                      i.del_status=0              \n" + 
			"        AND m_franchisee.fr_id IN( " + 
			"            :frList " + 
			"        )                     \n" + 
			/*
			 * "    group by\n" + "        i.item_id,\n" +
			 * "        m_franchisee.fr_id       \n" +
			 */
			"    order by\n" + 
			"        m_franchisee.fr_id,\n" + 
			"        item_grp2   )  t1        ",nativeQuery=true)
	List<DispatchStationItem> getItemByFrIdAndDateMin1New_REGSP_combined(@Param("date") String date,@Param("frList") List<Integer> frList,
			@Param("menuIds")List<Integer> menuIds, 	@Param("catId") List<Integer> catId);

}

package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.GetConsultationOrder;

public interface GetConsultationOrderRepo extends JpaRepository<GetConsultationOrder, Integer> {

	@Query(value="SELECT\n" + 
			"                SUM(t_order.order_qty) AS order_qty,\n" + 
			"                t_order.item_id\n" + 
			"            FROM\n" + 
			"                t_order              \n" + 
			"            WHERE\n" + 
			"                t_order.production_date = :selecDate                \n" + 
			"                AND t_order.is_bill_generated = 0                  \n" + 
			"                AND t_order.menu_id IN(:menuId)  \n" + 
			"                AND t_order.order_type=:catId            \n" + 
			"            GROUP BY\n" + 
			"                t_order.item_id              \n" + 
			"            ORDER BY\n" + 
			"                t_order.item_id ASC", nativeQuery=true)
	List<GetConsultationOrder> getConsultationOrder(@Param("catId") int catId, @Param("menuId") List<String> menuId, 
			@Param("selecDate") String selecDate);
}

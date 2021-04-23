package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.ChangeOrderRecord;

public interface ChangeOrderRecordRepo extends JpaRepository<ChangeOrderRecord, Integer> {

	@Query(value="SELECT t_change_order.change_id,t_change_order.user_id,t_change_order.date_time,t_change_order.change_date,\n" + 
			"t_change_order.orig_qty,t_change_order.change_qty,t_change_order.change_type,t_change_order.change_name,t_change_order.order_id,t_change_order.item_id,t_change_order.fr_id,t_change_order.ex_var1,t_change_order.delivery_date,\n" + 
			"m_user.usr_name as user_name,m_item.item_name, m_franchisee.fr_name\n" + 
			"FROM t_change_order,m_user,m_item,m_franchisee\n" + 
			"WHERE t_change_order.user_id=m_user.usr_id AND t_change_order.item_id=m_item.id and t_change_order.fr_id=m_franchisee.fr_id and t_change_order.fr_id IN (:frIdList) and t_change_order.delivery_date between :fromDate and :toDate ",nativeQuery=true)
	List<ChangeOrderRecord> getChangeOrderRecListSpecFr(@Param("frIdList") List<String> frIdList, @Param("fromDate") String fromDate,
			@Param("toDate") String toDate);
	
	
	@Query(value="SELECT t_change_order.change_id,t_change_order.user_id,t_change_order.date_time,t_change_order.change_date,\n" + 
			"t_change_order.orig_qty,t_change_order.change_qty,t_change_order.change_type,t_change_order.change_name,t_change_order.order_id,t_change_order.item_id,t_change_order.fr_id,t_change_order.ex_var1,t_change_order.delivery_date,\n" + 
			"m_user.usr_name as user_name,m_item.item_name, m_franchisee.fr_name\n" + 
			"FROM t_change_order,m_user,m_item,m_franchisee\n" + 
			"WHERE t_change_order.user_id=m_user.usr_id AND t_change_order.item_id=m_item.id and t_change_order.fr_id=m_franchisee.fr_id   and t_change_order.delivery_date between :fromDate and :toDate ",nativeQuery=true)
	List<ChangeOrderRecord> getChangeOrderRecListAllFr(  @Param("fromDate") String fromDate,
			@Param("toDate") String toDate);
}

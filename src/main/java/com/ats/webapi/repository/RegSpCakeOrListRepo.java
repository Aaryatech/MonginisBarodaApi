package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.RegularSpCake;


public interface RegSpCakeOrListRepo extends JpaRepository<RegularSpCake, Integer>{
	
	@Query(value="select qty,rsp_cust_name,rsp_cust_mobile_no,m_franchisee.fr_name from t_regular_sp_cake,m_franchisee,m_item where t_regular_sp_cake.fr_id IN (:frId) and t_regular_sp_cake.rsp_produ_date between :fromDate and t_regular_sp_cake.fr_id= m_franchisee.fr_id and :toDate and del_status=0",nativeQuery=true)
	List<RegularSpCake> getRegSpCakeList(@Param("spMenuId")List<Integer> spMenuId,@Param("frId")List<Integer> frId,@Param("fromDate")String fromDate,@Param("toDate")String toDate);
	
	@Query(value="select qty,rsp_cust_name,rsp_cust_mobile_no,,m_franchisee.fr_name from t_regular_sp_cake,m_franchisee,m_item.item_id where t_regular_sp_cake.fr_id=:frId and t_regular_sp_cake.rsp_produ_date between :fromDate and :toDate and t_regular_sp_cake.fr_id= m_franchisee.fr_id and del_status=0",nativeQuery=true)
	List<RegularSpCake> getRegSpCakeList1(@Param("spMenuId")List<Integer> spMenuId,@Param("fromDate")String fromDate,@Param("toDate")String toDate);

}

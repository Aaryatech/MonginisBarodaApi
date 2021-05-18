package com.ats.webapi.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.RegSpCakeReportResponse;
import com.ats.webapi.model.RegularSpCake;


public interface RegSpCakeOrListRepo extends JpaRepository<RegSpCakeReportResponse, Integer>{
	
//	@Query(value="select qty,rsp_cust_name,rsp_cust_mobile_no,m_franchisee.fr_name,m_item.item_name from t_regular_sp_cake,m_franchisee,m_item where t_regular_sp_cake.menu_id IN(:spMenuId ) AND t_regular_sp_cake.fr_id IN (:frId) and  m_franchisee.fr_id=m_item.id and t_regular_sp_cake.rsp_produ_date between :frmDate and :tDate  and t_regular_sp_cake.fr_id= m_franchisee.fr_id and del_status=0",nativeQuery=true)
	//	List<RegSpCakeReportResponse> getRegSpCakeList(@Param("spMenuId")List<Integer> spMenuId,@Param("frId")List<Integer> frId,@Param("fromDate")String fromDate,@Param("toDate")String toDate);
		
//	@Transactional
//	@Modifying
//	@Query("UPDATE t_regular_sp_cake t SET t.qty=:orderQty  WHERE AND t.rsp_id,t.del_status=0")
//	int updateOrderQty(@Param("rspId") int orderId,@Param("orderQty") int orderQty);
		
		@Query(value="SELECT m_item.item_name,m_franchisee.fr_name,t_regular_sp_cake.rsp_id,t_regular_sp_cake.qty,t_regular_sp_cake.rsp_cust_name,t_regular_sp_cake.rsp_cust_mobile_no FROM m_fr_menu_show,t_regular_sp_cake,m_franchisee,m_item WHERE t_regular_sp_cake.fr_id IN (:frId) AND t_regular_sp_cake.menu_id IN(:spMenuId) AND t_regular_sp_cake.item_id=m_item.id AND t_regular_sp_cake.rsp_produ_date BETWEEN :frmDate AND :tDate AND t_regular_sp_cake.del_status=0 AND t_regular_sp_cake.fr_id = m_franchisee.fr_id AND t_regular_sp_cake.menu_id=m_fr_menu_show.menu_id  "
				,nativeQuery=true)
		List<RegSpCakeReportResponse> getRegSpCakeList(@Param("spMenuId")List<Integer> spMenuId,@Param("frId")List<Integer> frId,@Param("frmDate")String frmDate,@Param("tDate")String tDate);
		
		
		@Query(value="SELECT m_item.item_name,m_franchisee.fr_name,t_regular_sp_cake.rsp_id,t_regular_sp_cake.qty,t_regular_sp_cake.rsp_cust_name,t_regular_sp_cake.rsp_cust_mobile_no FROM m_fr_menu_show,t_regular_sp_cake,m_franchisee,m_item WHERE t_regular_sp_cake.menu_id IN(:spMenuId) AND t_regular_sp_cake.item_id=m_item.id AND t_regular_sp_cake.rsp_produ_date BETWEEN :frmDate AND :tDate AND t_regular_sp_cake.del_status=0 AND t_regular_sp_cake.fr_id = m_franchisee.fr_id AND t_regular_sp_cake.menu_id=m_fr_menu_show.menu_id  "
				,nativeQuery=true)
		List<RegSpCakeReportResponse> getRegSpCakeList1(@Param("spMenuId")List<Integer> spMenuId,@Param("frmDate")String frmDate,@Param("tDate")String tDate);

		@Transactional
		@Modifying
		@Query(value="UPDATE t_regular_sp_cake SET del_status=1 WHERE rsp_id=:rspId",nativeQuery=true)
		int deleteRspById(@Param("rspId")int rspId);
		
		@Transactional
		@Modifying
		@Query(value="UPDATE t_regular_sp_cake SET qty=:qty WHERE rsp_id=:rspId",nativeQuery=true)
		int updateRspById(@Param("rspId")int rspId,@Param("qty")int qty);

}

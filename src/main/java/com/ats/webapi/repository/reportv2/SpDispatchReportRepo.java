package com.ats.webapi.repository.reportv2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.report.SpDispatchReport;

//Sachin 24-02-2021
public interface SpDispatchReportRepo extends JpaRepository<SpDispatchReport, Integer> {

	@Query(value = "SELECT UUID() as uuid, CONCAT(m_sp_cake.sp_name,' [', m_sp_flavour.spf_name,'] ',t_sp_cake.sp_selected_weight) "
			+ " as sp_name,COUNT(t_sp_cake.sp_order_no) AS order_qty, GROUP_CONCAT(t_sp_cake.sp_order_no)AS ord_nos, \n"
			+ " t_sp_cake.sp_id as sp_ids, "
			+ " sp_selected_weight,fr_id,sp_flavour_id,"
			+ " CONCAT(t_sp_cake.sp_id\n" + 
			"        ,sp_flavour_id,sp_selected_weight) as new_item "
			+ " FROM t_sp_cake,m_sp_cake,m_sp_flavour WHERE t_sp_cake.del_status=0 and  "
			+ " m_sp_cake.sp_id=t_sp_cake.sp_id and m_sp_flavour.spf_id=t_sp_cake.sp_flavour_id "
			+ " AND t_sp_cake.sp_delivery_date=:deliveryDateYMD AND t_sp_cake.fr_id in (:frId) AND t_sp_cake.menu_id in (:menu)\n"
			+ " GROUP by t_sp_cake.sp_id, t_sp_cake.sp_flavour_id, sp_selected_weight,fr_id "
			+ " ORDER by fr_id, t_sp_cake.sp_id ", nativeQuery = true)
	List<SpDispatchReport> getSpCakeOrderListForDispReportPdf(@Param("deliveryDateYMD") String deliveryDateYMD,
			@Param("frId") List<Integer> frId, @Param("menu") List<Integer> menu);

	@Query(value = "SELECT  UUID() as uuid, CONCAT(m_sp_cake.sp_name,' [', m_sp_flavour.spf_name,'] ',t_sp_cake.sp_selected_weight) " + 
			"		 as sp_name,COUNT(t_sp_cake.sp_order_no) AS order_qty, GROUP_CONCAT(t_sp_cake.sp_order_no)AS ord_nos,  " + 
			"		 t_sp_cake.sp_id as sp_ids," + 
			"			 sp_selected_weight,fr_id,sp_flavour_id, " + 
			"			  CONCAT(t_sp_cake.sp_id "
			+ "    ,sp_flavour_id,sp_selected_weight) as new_item FROM t_sp_cake,"
			+ "    m_sp_cake, m_sp_flavour WHERE t_sp_cake.del_status=0 and m_sp_cake.sp_id=t_sp_cake.sp_id "
			+ "    and m_sp_flavour.spf_id=t_sp_cake.sp_flavour_id "
			+ "    AND t_sp_cake.sp_delivery_date=:deliveryDateYMD AND t_sp_cake.fr_id in (:frId) "
			+ " AND t_sp_cake.menu_id in (:menu)  "
			+ " GROUP by new_item ", nativeQuery = true)
	List<SpDispatchReport> getSpCakeOrderListuniqueItem(@Param("deliveryDateYMD") String deliveryDateYMD,
			@Param("frId") List<Integer> frId, @Param("menu") List<Integer> menu);

}

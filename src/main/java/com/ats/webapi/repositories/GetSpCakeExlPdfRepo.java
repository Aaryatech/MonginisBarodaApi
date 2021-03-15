package com.ats.webapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.webapi.model.spprod.GetSpCakeExlPdf;

public interface GetSpCakeExlPdfRepo extends JpaRepository<GetSpCakeExlPdf, Integer> {

	@Query(value="SELECT\n" + 
			"    t1.*,\n" + 
			"    t2.shape_name,\n" + 
			"    t3.event_name,\n" + 
			"    t4.flavour\n" + 
			"FROM\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        sp.sp_id,\n" + 
			"        CONCAT(sp.sp_name, '-', sp.sp_code) AS sp_name,\n" + 
			"        c.type_name,\n" + 
			"        sp.sp_book_b4,\n" + 
			"        sp.sp_min_wt,\n" + 
			"        sp.sp_max_wt,\n" + 
			"        sp.sp_tax3,\n" + 
			"        sp.is_slot_used AS increamented_by,\n" + 
			"        sp.mrp_rate1,\n" + 
			"        sp.mrp_rate2,\n" + 
			"        sp.mrp_rate3,\n" + 
			"        sp.is_cust_choice_ck,\n" + 
			"        sp.is_addon_rate_appli,\n" + 
			"        sp.is_used,\n" + 
			"        u.uom,\n" + 
			"        sup.sp_hsncd\n" + 
			"    FROM\n" + 
			"        m_sp_cake sp,\n" + 
			"        m_cake_type c,\n" + 
			"        m_spcake_sup sup,\n" + 
			"        m_rm_uom u\n" + 
			"    WHERE\n" + 
			"        sp.sp_id = sup.sp_id AND sup.cut_section = c.cake_type_id AND sp.del_status = 0 		AND u.uom_id = sup.uom_id\n" + 
			") t1\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        m_spcake_sup.sp_id,\n" + 
			"        GROUP_CONCAT(m_shape.shape_name) AS shape_name\n" + 
			"    FROM\n" + 
			"        m_shape,\n" + 
			"        m_spcake_sup\n" + 
			"    WHERE\n" + 
			"        FIND_IN_SET(\n" + 
			"            m_shape.shape_id,\n" + 
			"            m_spcake_sup.sp_uom\n" + 
			"        )\n" + 
			"    GROUP BY\n" + 
			"        m_spcake_sup.sp_id\n" + 
			") t2\n" + 
			"ON\n" + 
			"    t1.sp_id = t2.sp_id\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        m_sp_cake.sp_id,\n" + 
			"        GROUP_CONCAT(m_sp_event.spe_name) AS event_name\n" + 
			"    FROM\n" + 
			"        m_sp_event,\n" + 
			"        m_sp_cake\n" + 
			"    WHERE\n" + 
			"        FIND_IN_SET(\n" + 
			"            m_sp_event.spe_id,\n" + 
			"            m_sp_cake.spe_id_list\n" + 
			"        )\n" + 
			"    GROUP BY\n" + 
			"        m_sp_cake.sp_id\n" + 
			") t3\n" + 
			"ON\n" + 
			"    t1.sp_id = t3.sp_id\n" + 
			"LEFT JOIN(\n" + 
			"    SELECT\n" + 
			"        m_sp_cake.sp_id,\n" + 
			"        GROUP_CONCAT(m_sp_flavour.spf_name) AS flavour\n" + 
			"    FROM\n" + 
			"        m_sp_flavour,\n" + 
			"        m_sp_cake\n" + 
			"    WHERE\n" + 
			"        FIND_IN_SET(\n" + 
			"            m_sp_flavour.spf_id,\n" + 
			"            m_sp_cake.erp_link_code\n" + 
			"        )\n" + 
			"    GROUP BY\n" + 
			"        m_sp_cake.sp_id\n" + 
			") t4\n" + 
			"ON\n" + 
			"    t1.sp_id = t4.sp_id", nativeQuery=true)
	
	List<GetSpCakeExlPdf> getSpCakeListExlPdf();
}

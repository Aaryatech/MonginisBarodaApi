package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.AlbumEnquiry;

public interface AlbumEnquiryRepository extends JpaRepository<AlbumEnquiry, Integer>{
	
	@Query(value="SELECT * FROM t_album_enquiry WHERE del_status=0 AND enquiry_no=:enqId",nativeQuery=true)
	AlbumEnquiry getEnquiryByDelStatusAndId(@Param("enqId") int enqId );
	
	@Query(value="SELECT\n" + 
			"    `enquiry_no`,\n" + 
			"  t_album_enquiry.fr_id,\n" + 
			"    `cust_name`,\n" + 
			"    `mob_no`,\n" + 
			"    `photo`,\n" + 
			"    `enq_dt`,\n" + 
			"    `enq_dt_time`,\n" + 
			"    `approve_dt_time`,\n" + 
			"    `approve_user_id`,\n" + 
			"    `approve_user_name`,\n" + 
			"    `album_id`,\n" + 
			"    `status`,\n" + 
			"    `weight`,\n" + 
			"    `min_weight`,\n" + 
			"    `flavour_id`,\n" + 
			"    `shape_id`,\n" + 
			"    `selected_sp_id`,\n" + 
			"    `message`,\n" + 
			"    `instruction`,\n" + 
			"    `rs_per_kg`,\n" + 
			"    `add_to_prod`,\n" + 
			"    `reject_id`,\n" + 
			"    `reject_remark`,\n" + 
			"    `ex_int1`,\n" + 
			"    `ex_int2`,\n" + 
			"    `ex_int3`,\n" + 
			"   m_franchisee.fr_name AS   `ex_var1`,\n" + 
			" m_sp_flavour.spf_name AS    `ex_var2`,\n" + 
			"    `ex_var3`,\n" + 
			" t_album_enquiry.del_status\n" + 
			"FROM\n" + 
			"    `t_album_enquiry`,\n" + 
			"    m_franchisee,\n" + 
			"   m_sp_flavour\n" + 
			"WHERE\n" + 
			"t_album_enquiry.fr_id=m_franchisee.fr_id AND t_album_enquiry.flavour_id=m_sp_flavour.spf_id\n" + 
			"ORDER BY   t_album_enquiry.enquiry_no   DESC LIMIT 100",nativeQuery=true)
	List<AlbumEnquiry> get100Enquiry();

	@Query(value="SELECT\n" + 
			"    `enquiry_no`,\n" + 
			"  t_album_enquiry.fr_id,\n" + 
			"    `cust_name`,\n" + 
			"    `mob_no`,\n" + 
			"    `photo`,\n" + 
			"    `enq_dt`,\n" + 
			"    `enq_dt_time`,\n" + 
			"    `approve_dt_time`,\n" + 
			"    `approve_user_id`,\n" + 
			"    `approve_user_name`,\n" + 
			"    `album_id`,\n" + 
			"    `status`,\n" + 
			"    `weight`,\n" + 
			"    `min_weight`,\n" + 
			"    `flavour_id`,\n" + 
			"    `shape_id`,\n" + 
			"    `selected_sp_id`,\n" + 
			"    `message`,\n" + 
			"    `instruction`,\n" + 
			"    `rs_per_kg`,\n" + 
			"    `add_to_prod`,\n" + 
			"    `reject_id`,\n" + 
			"    `reject_remark`,\n" + 
			"    `ex_int1`,\n" + 
			"    `ex_int2`,\n" + 
			"    `ex_int3`,\n" + 
			"   m_franchisee.fr_name AS   `ex_var1`,\n" + 
			" m_sp_flavour.spf_name AS    `ex_var2`,\n" + 
			"    `ex_var3`,\n" + 
			" t_album_enquiry.del_status\n" + 
			"FROM\n" + 
			"    `t_album_enquiry`,\n" + 
			"    m_franchisee,\n" + 
			"   m_sp_flavour\n" + 
			"WHERE\n" + 
			" t_album_enquiry.fr_id=:frId AND\n" + 
			"t_album_enquiry.fr_id=m_franchisee.fr_id AND t_album_enquiry.flavour_id=m_sp_flavour.spf_id\n" + 
			"ORDER BY   t_album_enquiry.enquiry_no   DESC LIMIT 50",nativeQuery=true)
	List<AlbumEnquiry> get50Enquiry(@Param("frId") int frId);
	
	
	
}

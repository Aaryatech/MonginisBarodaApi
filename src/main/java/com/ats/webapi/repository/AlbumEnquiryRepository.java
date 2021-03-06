package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.AlbumEnquiry;

public interface AlbumEnquiryRepository extends JpaRepository<AlbumEnquiry, Integer>{
	
	@Query(value="SELECT * FROM t_album_enquiry WHERE del_status=0 AND enquiry_no=:enqId",nativeQuery=true)
	AlbumEnquiry getEnquiryByDelStatusAndId(@Param("enqId") int enqId );
	
	@Query(value="SELECT * FROM t_album_enquiry  ORDER BY   t_album_enquiry.enquiry_no   DESC LIMIT 100",nativeQuery=true)
	List<AlbumEnquiry> get100Enquiry();

	@Query(value="SELECT * FROM t_album_enquiry WHERE  t_album_enquiry.fr_id=:frId  ORDER BY   t_album_enquiry.enquiry_no   DESC LIMIT 100",nativeQuery=true)
	List<AlbumEnquiry> get50Enquiry(@Param("frId") int frId);
	
	
	
}

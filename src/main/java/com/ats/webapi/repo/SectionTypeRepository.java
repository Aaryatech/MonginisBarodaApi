package com.ats.webapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.webapi.model.SectionType;

public interface SectionTypeRepository  extends JpaRepository<SectionType, Integer>{
	
	
	@Query(value="SELECT * FROM m_section_type WHERE del_status=0",nativeQuery=true)
	List<SectionType> getAllSecByDelStatus();
	

}

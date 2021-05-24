package com.ats.webapi.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.NewSectionWithType;

public interface NewSectionWithTypeRepo extends JpaRepository<NewSectionWithType, Integer> {
	
	@Query(value="SELECT\n" + 
			"    m_section.*,\n" + 
			"    m_section_type.sectiontype_name AS sec_type_name\n" + 
			"FROM\n" + 
			"    `m_section`,\n" + 
			"    m_section_type\n" + 
			"WHERE\n" + 
			"    m_section.section_type = m_section_type.id AND m_section.del_status = 0",nativeQuery=true)
	List<NewSectionWithType> getAllSection();
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_section SET del_status=1  WHERE section_id IN (:secId)",nativeQuery=true)
	int deleteMultiSection(@Param("secId") List<String> secId);

}

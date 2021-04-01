package com.ats.webapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

}

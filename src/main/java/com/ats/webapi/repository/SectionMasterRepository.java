package com.ats.webapi.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.SectionMaster;

public interface SectionMasterRepository extends JpaRepository<SectionMaster, Integer>{

	SectionMaster save(SectionMaster sectionMaster);

	List<SectionMaster> findByDelStatus(int i);

	SectionMaster findBySectionIdAndDelStatus(int sectionId, int i);

	@Modifying
	@Transactional
	@Query("Update SectionMaster  SET del_status=1 WHERE section_id=:sectionId")
	int deleteSection(@Param("sectionId") int sectionId);

	@Query(value="SELECT * FROM m_section WHERE del_status=0 AND section_id=:sectionId",nativeQuery=true)
	SectionMaster getSingleSectionById(@Param("sectionId") int sectionId);
	
	
	@Query(value="SELECT\n" + 
			"        * \n" + 
			"    FROM\n" + 
			"        m_section \n" + 
			"    WHERE\n" + 
			"        del_status=0 \n" + 
			"        AND section_type=:sectionId",nativeQuery=true)
	List<SectionMaster> getSectionById(@Param("sectionId") int sectionId);
 

}

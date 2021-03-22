package com.ats.webapi.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.SectionMaster;
import com.ats.webapi.model.SectionMasterNew;

public interface SectionMasterNewRepository extends JpaRepository<SectionMasterNew, Integer> {
	
	
	
	SectionMasterNew save(SectionMasterNew sectionMaster);

	List<SectionMasterNew> findByDelStatus(int i);

	SectionMasterNew findBySectionIdAndDelStatus(int sectionId, int i);

	@Modifying
	@Transactional
	@Query("Update SectionMasterNew  SET del_status=1 WHERE section_id=:sectionId")
	int deleteSection(@Param("sectionId") int sectionId);

	@Query(value="SELECT * FROM m_section WHERE del_status=0 AND section_id=:sectionId",nativeQuery=true)
	SectionMasterNew getSingleSectionById(@Param("sectionId") int sectionId);

}

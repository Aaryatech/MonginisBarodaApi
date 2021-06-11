package com.ats.webapi.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.SubCategoryRes;
import com.ats.webapi.model.SubCategoryResNew;

public interface SubCategoryResNewRepository extends JpaRepository<SubCategoryResNew, Integer> {

	
	
	@Modifying
	@Transactional
	@Query("Update SubCategoryResNew  SET del_status=1 WHERE subCatId=:subCatId")
	int deleteSubCategory(@Param("subCatId")int subCatId);

	SubCategoryResNew findBySubCatId(int subCatId);
	
	@Query(value="SELECT * FROM `m_cat_sub` WHERE m_cat_sub.del_status=0",nativeQuery=true)
	List<SubCategoryResNew> getAllSubcatByDelStatus();
	
	
	
	
	
	
}

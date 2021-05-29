package com.ats.webapi.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.*;

public interface TestFrRepository extends CrudRepository<ConfigureFranchisee, Long>{
	
	
	
	 @Query(value = "SELECT * FROM m_fr_configure ,m_fr_menu_show WHERE m_fr_configure.menu_id = m_fr_menu_show.menu_id", nativeQuery = true)
	    List<ConfigureFranchisee> findAllProjectedNativeQuery();
	 
	 
	 @Query(value = "SELECT * FROM m_fr_configure ,m_fr_menu_show WHERE m_fr_configure.menu_id = m_fr_menu_show.menu_id AND m_fr_menu_show.menu_id IN (:menuIds) ", nativeQuery = true)
	    List<ConfigureFranchisee> findAllMenusWhereMenuidIn(@Param("menuIds") List<String> menuIds);
	
	
}



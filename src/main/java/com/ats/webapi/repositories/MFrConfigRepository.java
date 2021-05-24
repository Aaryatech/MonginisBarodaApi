package com.ats.webapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.webapi.model.MFrConfigBean;

public interface MFrConfigRepository extends JpaRepository<MFrConfigBean, Integer> {
	
	
	@Query(value="SELECT\n" + 
			"    m_fr_configure.*,\n" + 
			"    'NA' AS fr_name,\n" + 
			"    m_fr_menu_show.menu_title,\n" + 
			"    m_category.cat_name\n" + 
			"FROM\n" + 
			"    m_fr_configure,\n" + 
			"    m_fr_menu_show,\n" + 
			"    m_category\n" + 
			"WHERE\n" + 
			"    m_fr_menu_show.menu_id = m_fr_configure.menu_id AND m_category.cat_id = m_fr_configure.cat_id",nativeQuery=true)
	List<MFrConfigBean> getAllConfigMenus();

}

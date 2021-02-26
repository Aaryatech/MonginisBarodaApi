package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.GetFrMenuConfigure;
@Repository
public interface GetFrMenuConfigureRepository extends JpaRepository<GetFrMenuConfigure, Integer>{

	@Query(value="select c.setting_id,c.fr_id,cf.menu_id,cf.cat_id,cf.sub_cat_id,cf.from_time,cf.to_time,cf.setting_type,cf.day,cf.date, f.fr_name, m.menu_title,c.is_del,cat.cat_name from m_fr_menu_configure c,m_franchisee f,m_fr_menu_show m,m_fr_configure cf,m_category cat where c.fr_id=f.fr_id and c.menu_id=cf.setting_id and m.menu_id=cf.menu_id and cat.cat_id=cf.cat_id and  c.is_del=0",nativeQuery=true)
	List<GetFrMenuConfigure> getFrMenuConfigureList();

	
	
	//Akhilesh 2021-02-25 To Get Fr Configured Menu For Selected Franchisees And Menus
	@Query(value="SELECT\n" + 
			"    c.setting_id,\n" + 
			"    c.fr_id,\n" + 
			"    cf.menu_id,\n" + 
			"    cf.cat_id,\n" + 
			"    cf.sub_cat_id,\n" + 
			"    cf.from_time,\n" + 
			"    cf.to_time,\n" + 
			"    cf.setting_type,\n" + 
			"    cf.day,\n" + 
			"    cf.date,\n" + 
			"    f.fr_name,\n" + 
			"    m.menu_title,\n" + 
			"    c.is_del,\n" + 
			"    cat.cat_name\n" + 
			"FROM\n" + 
			"    m_fr_menu_configure c,\n" + 
			"    m_franchisee f,\n" + 
			"    m_fr_menu_show m,\n" + 
			"    m_fr_configure cf,\n" + 
			"    m_category cat\n" + 
			"WHERE\n" + 
			"    c.fr_id = f.fr_id AND c.menu_id = cf.setting_id AND m.menu_id = cf.menu_id AND cat.cat_id = cf.cat_id AND c.is_del = 0\n" + 
			"    AND f.fr_id IN (:frIds)  AND  m.menu_id IN (:menuIds)\n" + 
			"    ",nativeQuery=true)
	List<GetFrMenuConfigure> getFrMenuConfgedForSelctedFrAndMenu(@Param("frIds") List<String>  frIds ,@Param("menuIds") List<String>  menuIds);
	
	
}

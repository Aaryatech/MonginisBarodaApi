package com.ats.webapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.MenuType;

public interface MenuTypeRepository extends JpaRepository<MenuType, Integer> {
	
	
	@Query(value="SELECT * FROM m_menu_type WHERE is_active=0 AND del_status=0",nativeQuery=true)
	public List<MenuType> getMenuTypeByIsActiveDelStatus();

}

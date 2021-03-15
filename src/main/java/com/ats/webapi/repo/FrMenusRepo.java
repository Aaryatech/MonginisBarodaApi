package com.ats.webapi.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.MenuShow;

public interface FrMenusRepo extends JpaRepository<MenuShow, Integer> {
	
	List<MenuShow> findByDelStatusOrderByCatId(int del); 
	
	MenuShow findByMenuId(@Param("menuId") int menuId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE `m_fr_menu_show` SET del_status=1 WHERE menu_id=:menuId",nativeQuery=true)
	int deleteMenuById(@Param("menuId") int menuId);
}

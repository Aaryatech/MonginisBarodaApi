package com.ats.webapi.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.CakeType;

public interface CakeTypeRepo extends JpaRepository<CakeType, Integer> {

	List<CakeType> findByDelStatusOrderByCakeTypeIdDesc(int del);
	
	CakeType findBycakeTypeId(int cakeTypeId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_cake_type SET del_status=1 WHERE cake_type_id=:cakeTypeId",nativeQuery=true)
	int delCakeType(@Param("cakeTypeId") int cakeTypeId);
}

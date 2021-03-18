package com.ats.webapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.webapi.model.Setting;


public interface settingSpCakeRepository extends JpaRepository<Setting, Integer>{

	@Transactional
	@Modifying
	@Query(value="UPDATE t_setting_new s SET s.ex_varchar2=:itemId  WHERE s.setting_key='SP_Cake_IDS_FOR_APP'",nativeQuery=true)
	public int updateItems(@Param("itemId") String itemId);
}

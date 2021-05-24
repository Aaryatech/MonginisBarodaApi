package com.ats.webapi.repository.OpsAccessRight;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.ops.access.OpsAccessRight;

public interface OpsAccessRightRepo extends JpaRepository<OpsAccessRight, Integer>{	

	@Query(value="SELECT * FROM `ops_module` WHERE  ops_module.setting_value=:isFrPosAppicale ORDER BY `ops_module`.`ex_int1` ASC ",nativeQuery=true)
	List<OpsAccessRight> findBySettingValueorderbyExint1AccessRights(@Param("isFrPosAppicale") int isFrPosAppicale);
	
	
	@Query(value="SELECT * FROM `ops_module` ORDER BY `ops_module`.`ex_int1` ASC ",nativeQuery=true)
	List<OpsAccessRight> finAccessRightsOrderByExint1();
	
}

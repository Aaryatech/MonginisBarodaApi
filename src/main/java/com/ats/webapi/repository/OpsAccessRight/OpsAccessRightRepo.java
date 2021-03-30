package com.ats.webapi.repository.OpsAccessRight;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.ops.access.OpsAccessRight;

public interface OpsAccessRightRepo extends JpaRepository<OpsAccessRight, Integer>{	

	List<OpsAccessRight> findBySettingValue(@Param("isFrPosAppicale") int isFrPosAppicale);
}

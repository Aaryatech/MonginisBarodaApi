package com.ats.webapi.repository.OpsAccessRight;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.webapi.model.ops.access.OpsRoles;

public interface OpsRoleRepo extends JpaRepository<OpsRoles, Integer>{

}

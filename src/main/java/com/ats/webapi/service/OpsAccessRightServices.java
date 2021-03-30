package com.ats.webapi.service;

import java.util.List;

import com.ats.webapi.model.ops.access.OpsAccessRight;
import com.ats.webapi.model.ops.access.OpsRoles;

public interface OpsAccessRightServices {
	List<OpsAccessRight> getAllModules();	
	List<OpsRoles> getAllOpsRoles();
	
	
}

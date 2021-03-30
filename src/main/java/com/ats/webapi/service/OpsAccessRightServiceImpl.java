package com.ats.webapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ats.webapi.model.ops.access.OpsAccessRight;
import com.ats.webapi.model.ops.access.OpsRoles;
import com.ats.webapi.repository.OpsAccessRight.OpsAccessRightRepo;

public class OpsAccessRightServiceImpl implements OpsAccessRightServices{

	@Autowired OpsAccessRightRepo opsAccessRepo;
	
	@Override
	public List<OpsAccessRight> getAllModules() {
		List<OpsAccessRight> list = new ArrayList<OpsAccessRight>();
		try {
			list = opsAccessRepo.findAll();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public List<OpsRoles> getAllOpsRoles() {
		// TODO Auto-generated method stub
		return null;
	}

}

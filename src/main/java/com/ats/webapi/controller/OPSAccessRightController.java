package com.ats.webapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.ops.access.OpsAccessRight;
import com.ats.webapi.repository.OpsAccessRight.OpsAccessRightRepo;
import com.ats.webapi.service.OpsAccessRightServices;

@RestController
public class OPSAccessRightController {

	@Autowired OpsAccessRightRepo opsAccessRepo;
	@RequestMapping(value = { "/getAllOpsAccessRole" }, method = RequestMethod.GET)
	public @ResponseBody List<OpsAccessRight> getAllPosAccessRole() {
		
		List<OpsAccessRight> opsModlList=new ArrayList<OpsAccessRight>();	
		
		opsModlList=opsAccessRepo.finAccessRightsOrderByExint1();		
		
		return opsModlList;
	}
	
	
	@RequestMapping(value = { "/getAllowedOpsMappings" }, method = RequestMethod.POST)
	public @ResponseBody List<OpsAccessRight> getallowedMappings(@RequestParam int isFrPosAppicale) {
		
		List<OpsAccessRight> opsModlList=new ArrayList<OpsAccessRight>();	
		if(isFrPosAppicale==1) {
			opsModlList=opsAccessRepo.finAccessRightsOrderByExint1();		
		}else {
			opsModlList=opsAccessRepo.findBySettingValueorderbyExint1AccessRights(isFrPosAppicale);	
		}
		
		return opsModlList;
	}
}

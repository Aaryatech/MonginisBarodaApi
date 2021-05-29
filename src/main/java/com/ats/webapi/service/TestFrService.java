package com.ats.webapi.service;

import java.util.List;

import com.ats.webapi.model.ConfigureFranchisee;
import com.ats.webapi.model.Franchisee;

public interface TestFrService {

	
   public List<ConfigureFranchisee> findFrMenus();
   
   public List<ConfigureFranchisee> findFrMenusBuMenuIdsIn(List<String> menuIds);
   
   

}

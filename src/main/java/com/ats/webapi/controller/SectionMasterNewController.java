package com.ats.webapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.AllMenus;
import com.ats.webapi.model.Info;
import com.ats.webapi.model.SectionMasterNew;
import com.ats.webapi.repo.SectionMasterNewRepository;
import com.ats.webapi.repository.MainMenuConfigurationRepository;
import com.mysql.fabric.xmlrpc.base.Array;

@RestController
public class SectionMasterNewController {
	
	@Autowired
	SectionMasterNewRepository sectionMasterNewRepository;
	
	
	@Autowired
	MainMenuConfigurationRepository mainMenuConfigRepo;
	
	
	
	@RequestMapping(value="/saveSectionMAsterNew",method=RequestMethod.POST)
	public @ResponseBody SectionMasterNew saveSectionMAsterNew(@RequestBody SectionMasterNew sectionMaster) {
		SectionMasterNew res=new SectionMasterNew();
		try {
			res=sectionMasterNewRepository.save(sectionMaster);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("Exception Occuered In /saveSectionMAsterNew");
		}
		return res;
	}
	
	
	@RequestMapping(value="/getSectionNewById",method=RequestMethod.POST)
	public @ResponseBody SectionMasterNew getSectionNewById(@RequestParam int sectionId) {
		SectionMasterNew res=new SectionMasterNew();
		try {
			
			List<AllMenus> menuList=new ArrayList<>();
			res=sectionMasterNewRepository.getSingleSectionById(sectionId);
			String [] menuIds=res.getMenuIds().split(",");
			menuList=mainMenuConfigRepo.findByMenuIdIn(menuIds);
			res.setMenuList(menuList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("Exception Occuered In /getSectionNewById");
		}
		return res;
	}
	
	
	
	public @ResponseBody Info deleteSectionMasterNew(@RequestParam int sectionId) {
		Info res=new Info();
		int flag=0;
		try {
			
			flag = sectionMasterNewRepository.deleteSection(sectionId);
			if(flag>0) {
				res.setError(false);
				res.setMessage("Section Deleted");
			}else {
				res.setError(true);
				res.setMessage("Unable To Delete Section");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			res.setError(true);
			res.setMessage("Unable To Delete Section Exception Occuered");
			e.printStackTrace();
			System.err.println("Exception Occuered In /getSectionNewById");
		}
		return res;
		
	}
	
	
	
	
	
	

}

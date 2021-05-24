package com.ats.webapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.MenuType;
import com.ats.webapi.repositories.MenuTypeRepository;

@RestController
public class MenuTypeControllerApi {
	
	@Autowired
	MenuTypeRepository menuTypeRepo;
	
	@RequestMapping(value="/getAllMenuType",method=RequestMethod.GET)
	public @ResponseBody List<MenuType> getAllMenuType(){
		List<MenuType> resp=new ArrayList<>();
		System.err.println("In /getAllMenuType");
		try {
			resp=menuTypeRepo.getMenuTypeByIsActiveDelStatus();
		} catch (Exception e) {
			System.err.println("Exception In /getAllMenuType");
			e.printStackTrace();
		}
		
		return resp;
	}

}

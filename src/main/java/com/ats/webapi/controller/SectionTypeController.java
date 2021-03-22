package com.ats.webapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.SectionType;
import com.ats.webapi.repo.SectionTypeRepository;

@RestController
public class SectionTypeController {

	
	@Autowired
	SectionTypeRepository sectionTypeRepository;
	
	@RequestMapping(value="/getAllSectionTypeByDelStatus",method=RequestMethod.GET)
	public @ResponseBody List<SectionType> getAllSectionTypeByDelStatus(){
		List<SectionType> allSectionType=new ArrayList<>();
		try {
			
			allSectionType=sectionTypeRepository.getAllSecByDelStatus();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Exception Occuered in /getAllSectionTypeByDelStatus");
			// TODO: handle exception
		}
		return allSectionType;
	}
	
	
}

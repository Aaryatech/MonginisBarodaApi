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

import com.ats.webapi.model.Info;
import com.ats.webapi.model.SubCategoryResNew;
import com.ats.webapi.repository.SubCategoryResNewRepository;

@RestController
public class SubCategoryNewControllerApi {
	
	
	@Autowired
	SubCategoryResNewRepository subCategoryResNewRepository;
	
	
	@RequestMapping(value="/getAllSubcatNewByDelStatus",method=RequestMethod.GET)
	public @ResponseBody  List<SubCategoryResNew>  getAllSubcatNewByDelStatus(){
		List<SubCategoryResNew> subcatList=new ArrayList<>();
	try {
		subcatList=subCategoryResNewRepository.getAllSubcatByDelStatus();
	} catch (Exception e) {
		// TODO: handle exception
		System.err.println("Excp In /getAllSubcatNewByDelStatus");
		e.printStackTrace();
	}
		
		return subcatList;
	}
	
	
	@RequestMapping(value="/InsertSubcatNew",method=RequestMethod.POST)
	public @ResponseBody  SubCategoryResNew  InsertSubcatNew(@RequestBody SubCategoryResNew  subcatNew ){
		SubCategoryResNew subcatResp=new SubCategoryResNew();
	try {
		subcatResp=subCategoryResNewRepository.save(subcatNew);
	} catch (Exception e) {
		// TODO: handle exception
		System.err.println("Excp In /InsertSubcatNew");
		e.printStackTrace();
	}
		
		return subcatResp;
	}
	
	
	
	@RequestMapping(value="/GetSubcatNewById",method=RequestMethod.POST)
	public @ResponseBody  SubCategoryResNew  GetSubcatNewById(@RequestParam int  subcatId ){
		SubCategoryResNew subcatResp=new SubCategoryResNew();
	try {
		subcatResp=subCategoryResNewRepository.findBySubCatId(subcatId);
	} catch (Exception e) {
		// TODO: handle exception
		System.err.println("Excp In /GetSubcatNewById");
		e.printStackTrace();
	}
		
		return subcatResp;
	}
	
	
	@RequestMapping(value="/DeleteSubcatNewById",method=RequestMethod.POST)
	public @ResponseBody  Info  DeleteSubcatNewById(@RequestParam int  subcatId ){
		Info subcatResp=new Info();
		int flag=0;
	try {
		flag=subCategoryResNewRepository.deleteSubCategory(subcatId);
		if(flag>0) {
			subcatResp.setError(false);
			subcatResp.setMessage("Subcategory Deteled");
		}else {
			subcatResp.setError(true);
			subcatResp.setMessage("Unable To Detele Subcategory");
		}
	} catch (Exception e) {
		// TODO: handle exception
		subcatResp.setError(true);
		subcatResp.setMessage("Unable To Detele Subcategory Exp Occuered");
		System.err.println("Excp In /DeleteSubcatNewById");
		e.printStackTrace();
	}
		
		return subcatResp;
	}
	
	
	

}

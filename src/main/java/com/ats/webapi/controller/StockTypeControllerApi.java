package com.ats.webapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.StockType;
import com.ats.webapi.repo.StockTypeRepository;

@RestController
public class StockTypeControllerApi {

	@Autowired
	StockTypeRepository StockTypeRepo;
	
	
	@RequestMapping(value="/getAllStockType",method=RequestMethod.GET)
	public @ResponseBody List<StockType> getAllStockType(){
		List<StockType> StockTypeList=new ArrayList<>();
				
			try {
				StockTypeList=StockTypeRepo.findAllStockTypesBydelStatus();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.err.println("Exception In /getAllStockType");
			}	
				
		return StockTypeList;
	}
	
	
	
}

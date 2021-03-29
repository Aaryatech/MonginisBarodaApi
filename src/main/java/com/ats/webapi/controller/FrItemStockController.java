package com.ats.webapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.GetReorderByStockType;
import com.ats.webapi.repository.GetReorderByStockTypeRepository;

@RestController
public class FrItemStockController {
	
	
	@Autowired
	GetReorderByStockTypeRepository itemStockRepo;
	
	
	
	@RequestMapping(value="/getItemStockByType",method=RequestMethod.POST)
	public @ResponseBody List<GetReorderByStockType> getItemStockByType(@RequestParam List<String> type){
		List<GetReorderByStockType> resp=new ArrayList<>();
		try {
			resp=itemStockRepo.GetRegSpCakeOrderQtyByStocktype(type);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("Exception Occured In /getItemStockByType");
		}
		
		return resp;
	}
	
	
	
	
	
	

}

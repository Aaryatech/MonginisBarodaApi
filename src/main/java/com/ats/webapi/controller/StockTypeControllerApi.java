package com.ats.webapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.Item;
import com.ats.webapi.model.StockType;
import com.ats.webapi.model.StockTypeConfigResponse;
import com.ats.webapi.repo.StockTypeRepository;
import com.ats.webapi.service.ItemService;

@RestController
public class StockTypeControllerApi {

	@Autowired
	StockTypeRepository StockTypeRepo;
	
	
	@Autowired
	private ItemService itemService;
	
	
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
	
	
	
	
	@RequestMapping(value = "/getStocktypeWithItems",method=RequestMethod.POST)
	public @ResponseBody StockTypeConfigResponse getStocktypeWithItems(@RequestParam String subcatIds,@RequestParam List<String> sTypeIds) {
		System.err.println("In /getStocktyWithItems");
		StockTypeConfigResponse resp=new StockTypeConfigResponse();
		List<Item> items=new ArrayList<>();
		List<StockType> stockTypes=new ArrayList<>();
		try {
			
			items=itemService.getItemsBySubCatIdForConfiguration(subcatIds);
			stockTypes=StockTypeRepo.findAllBydelStatusAndIdIn(sTypeIds);
			resp.setItemlist(items);
			resp.setStockTypelist(stockTypes);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("Exception Occuered In /getStocktypeWithItems");
		}
		
		
		return resp;
		
	}
	
	
	
	
	
	
	
	
	
}

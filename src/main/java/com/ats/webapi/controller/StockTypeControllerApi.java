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

import com.ats.webapi.model.ErrorMessage;
import com.ats.webapi.model.GetitemStockConfig;
import com.ats.webapi.model.Item;
import com.ats.webapi.model.StockType;
import com.ats.webapi.model.StockTypeConfigResponse;
import com.ats.webapi.repo.StockTypeRepository;
import com.ats.webapi.repository.GetitemStockConfigRepository;
import com.ats.webapi.repository.ItemStockRepository;
import com.ats.webapi.service.ItemService;

@RestController
public class StockTypeControllerApi {

	@Autowired
	StockTypeRepository StockTypeRepo;
	
	
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	GetitemStockConfigRepository itemStockrepo;
	
	
	
	
	
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
	

  	@RequestMapping(value="/postStockType",method=RequestMethod.POST)
public @ResponseBody StockType postStockType(@RequestBody StockType stock){
  		StockType StockTypeList=new StockType();
			
		try {
			StockTypeList=StockTypeRepo.save(stock);
			System.out.println("stock"+stock);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("Exception In /getAllStockType");
		}	
			
	return StockTypeList;
}
 
  	
  	@RequestMapping(value="/getAllStockTypeById",method=RequestMethod.POST)
public @ResponseBody StockType getAllStockTypeById(@RequestParam int id){
	StockType  stock=new StockType();
	try {
		stock=StockTypeRepo.getAllStockTypeById(id);
		System.out.println("api id"+stock);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		System.err.println("Exception In /getAllStockTypeById");
	}
	
	return stock;
}

  	
  	// Delete Item
@RequestMapping(value ="/deleteStockType", method = RequestMethod.POST)
public @ResponseBody ErrorMessage deleteItem(@RequestParam Integer rejectId) {
System.err.println("error");
	ErrorMessage errorMessage = new ErrorMessage();

	int isUpdated = StockTypeRepo.deleteItems(rejectId);
	if (isUpdated == 1) {

		errorMessage.setError(false);
		errorMessage.setMessage("Items Deleted Successfully");
	} else {
		errorMessage.setError(false);
		errorMessage.setMessage("Items Deletion Failed");

	}
	return errorMessage;
}




@RequestMapping(value = "/getStocktypeWithItems",method=RequestMethod.POST)
public @ResponseBody StockTypeConfigResponse getStocktypeWithItems(@RequestParam List<String> subcatIds,@RequestParam List<String> sTypeIds) {
	System.err.println("In /getStocktyWithItems");
	StockTypeConfigResponse resp=new StockTypeConfigResponse();
	List<Item> items=new ArrayList<>();
	List<StockType> stockTypes=new ArrayList<>();
	List<GetitemStockConfig> itemStresp=new ArrayList<>();
	try {
		
		items=itemService.getItemsBySubCatIdForConfiguration(subcatIds);
		stockTypes=StockTypeRepo.findAllStockTypesByIndelStatus(sTypeIds);
		resp.setItemlist(items);
		resp.setStockTypelist(stockTypes);
		itemStresp=itemStockrepo.GetItemStockByType(sTypeIds);
		resp.setItemStockList(itemStresp);
	
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		System.err.println("Exception Occuered In /getStocktypeWithItems");
	}
	
	
	return resp;
	
}

@RequestMapping(value="/submitConfigApi",method=RequestMethod.POST)
public @ResponseBody List<GetitemStockConfig> submitConfigApi(@RequestBody List<GetitemStockConfig> configlist) {
	List<GetitemStockConfig> respList=new ArrayList<>();
	try {
		respList= itemStockrepo.save(configlist);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		System.err.println("Exception In /submitConfigApi");
	}
	
	
	return respList;
	
}






}
	


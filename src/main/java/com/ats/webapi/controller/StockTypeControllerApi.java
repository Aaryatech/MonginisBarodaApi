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

}
	


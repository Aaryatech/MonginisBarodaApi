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
import com.ats.webapi.model.PaymentMode;
import com.ats.webapi.model.PaymentType;
import com.ats.webapi.model.ShowPaymentType;
import com.ats.webapi.repositories.PayModeRepo;
import com.ats.webapi.repositories.PayTypeRepo;
import com.ats.webapi.repositories.ShowPaymentTypeRepo;

@RestController
public class PaymentTypesApiController {
	


		@Autowired
		PayTypeRepo payTm;

		@RequestMapping(value="/getAllPaymentType",method=RequestMethod.GET)
		public @ResponseBody List<PaymentType> getAllPaymentType(){
			List<PaymentType>  remarks=new ArrayList<>();
			try {
				remarks=payTm.getAllPaymentTypeByDelStatus();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.err.println("Exception In /getAllPaymentType");
			}
			
			return remarks;
		}
		

		@Autowired
		ShowPaymentTypeRepo showT;
		
		  @RequestMapping(value = { "/showPaymentType" }, method = RequestMethod.GET)
	      public @ResponseBody List<ShowPaymentType> showStateCountry() {
 
		      List<ShowPaymentType> list = new ArrayList<ShowPaymentType>();
		        try {

		          	list = showT.showType();

		            } catch (Exception e) {

			     e.printStackTrace();
		       }

		     return list;

	      }
		
		@RequestMapping(value="/postAllPaymentType",method=RequestMethod.POST)
		public @ResponseBody PaymentType postAllPaymentType(@RequestBody PaymentType pay ){
			PaymentType  remarks=new PaymentType();

			System.out.println("Excep in /savePaymentType save : "+pay);
			try {
				remarks=payTm.save(pay);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.err.println("Exception In /postAllPaymentType");
			}

			System.out.println("Excep in /savePaymentType save : "+remarks);
			return remarks;
		}
		

		@RequestMapping(value="/getAllPaymentTypeById",method=RequestMethod.POST)
		public @ResponseBody PaymentType getAllPaymentTypeById(@RequestParam int typeId){
			PaymentType  remarks=new PaymentType();
			try {
				remarks=payTm.getAllPaymentTypeById(typeId);
				System.out.println("remarks"+remarks);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.err.println("Exception In /getAllPaymentTypeById");
			}
			
			return remarks;
		}
		

		
		
			// Delete Item
		@RequestMapping(value ="/deletePaymentType", method = RequestMethod.POST)
		public @ResponseBody ErrorMessage deletePaymentType(@RequestParam Integer typeId) {
	System.err.println("error");
			ErrorMessage errorMessage = new ErrorMessage();

			int isUpdated = payTm.deleteById(typeId);
			if (isUpdated == 1) {

				errorMessage.setError(false);
				errorMessage.setMessage("Items Deleted Successfully");
			} else {
				errorMessage.setError(false);
				errorMessage.setMessage("Items Deletion Failed");

			}
			return errorMessage;
		}
	

	@Autowired
	PayModeRepo paymode;

	@RequestMapping(value="/getAllPaymentMode",method=RequestMethod.GET)
	public @ResponseBody List<PaymentMode> getAllPaymentMode(){
		List<PaymentMode>  remarks=new ArrayList<>();
		try {
			remarks=paymode.getAllPaymentModeByDelStatus();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("Exception In /getAllPaymentMode");
		}
		
		return remarks;
	}
	
	@RequestMapping(value="/postAllPaymentMode",method=RequestMethod.POST)
	public @ResponseBody PaymentMode postAllPaymentMode(@RequestBody PaymentMode pay ){
		PaymentMode  remarks=new PaymentMode();
		try {
			remarks=paymode.save(pay);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("Exception In /postAllPaymentMode");
		}
		
		return remarks;
	}
	

	@RequestMapping(value="/getAllPaymentModeById",method=RequestMethod.POST)
	public @ResponseBody PaymentMode getAllPaymentModeById(@RequestParam int modeId){
		PaymentMode  remarks=new PaymentMode();
		try {
			remarks=paymode.getAllPaymentModeById(modeId);
			System.out.println("remarks"+remarks);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("Exception In /getAllPaymentModeById");
		}
		
		return remarks;
	}
	

	
	
		// Delete Item
	@RequestMapping(value ="/deletePaymentMode", method = RequestMethod.POST)
	public @ResponseBody ErrorMessage deletePaymentMode(@RequestParam Integer modeId) {
System.err.println("error");
		ErrorMessage errorMessage = new ErrorMessage();

		int isUpdated = paymode.deleteById(modeId);
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

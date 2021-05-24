package com.ats.webapi.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.Franchisee;
import com.ats.webapi.repository.FranchiseeRepository;

@RestController
public class TempCronjobController {
	
	
	@Autowired
	FranchiseeRepository franRepo;

	@RequestMapping(value="/getExpFdaLicenceDate",method=RequestMethod.GET)
	public @ResponseBody List<Franchisee> getExpFdaLicenceDate(){
	System.err.println("In /getExpFdaLicenceDate");
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	Date date=new Date();
	 Calendar c = Calendar.getInstance();
     c.setTime(date);
     c.add(Calendar.DAY_OF_MONTH, 30);
     Date currentDatePlusOne = c.getTime();
	System.err.println("Date-->"+sdf.format(currentDatePlusOne)+"\t"+sdf.format(date));
		List<Franchisee> resp=new ArrayList<>();
		try {
		resp=franRepo.getExpFdaLicenceDate(sdf.format(date),sdf.format(currentDatePlusOne));
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Execp In /getExpFdaLicenceDate");
			e.printStackTrace();
		}
		
		return resp;
	}
	
	
	
	@RequestMapping(value="/getExpAgreementDate",method=RequestMethod.GET)
	public @ResponseBody List<Franchisee> getExpAgreementDate(){
	System.err.println("In /getExpAgreementDate");
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	Date date=new Date();
	 Calendar c = Calendar.getInstance();
     c.setTime(date);
     c.add(Calendar.DAY_OF_MONTH, 30);
     Date currentDatePlusOne = c.getTime();
	System.err.println("Date-->"+sdf.format(currentDatePlusOne)+"\t"+sdf.format(date));
		List<Franchisee> resp=new ArrayList<>();
		try {
		resp=franRepo.getExpAgreementDate(sdf.format(date),sdf.format(currentDatePlusOne));
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Execp In /getExpAgreementDate");
			e.printStackTrace();
		}
		
		return resp;
	}
	
	
	@RequestMapping(value="/getOwnerBirthDate",method=RequestMethod.GET)
	public @ResponseBody List<Franchisee> getExpOwnerBirthDate(){
	System.err.println("In /getOwnerBirthDate");
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	Date date=new Date();
	 Calendar c = Calendar.getInstance();
     c.setTime(date);
     c.add(Calendar.DAY_OF_MONTH, 1);
     Date currentDatePlusOne = c.getTime();
	System.err.println("Date-->"+sdf.format(currentDatePlusOne)+"\t"+sdf.format(date));
		List<Franchisee> resp=new ArrayList<>();
		try {
		resp=franRepo.getOwnerBirthDate(sdf.format(currentDatePlusOne));
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Execp In /getOwnerBirthDate");
			e.printStackTrace();
		}
		
		return resp;
	}
	
	@RequestMapping(value="/getShopOpningDate",method=RequestMethod.GET)
	public @ResponseBody List<Franchisee> getShopOpningDate(){
	System.err.println("In /getShopOpningDate");
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	Date date=new Date();
	 Calendar c = Calendar.getInstance();
     c.setTime(date);
     c.add(Calendar.DAY_OF_MONTH, 1);
     Date currentDatePlusOne = c.getTime();
	System.err.println("Date-->"+sdf.format(currentDatePlusOne)+"\t"+sdf.format(date));
		List<Franchisee> resp=new ArrayList<>();
		try {
		resp=franRepo.getShopOpningDate(sdf.format(currentDatePlusOne));
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Execp In /getShopOpningDate");
			e.printStackTrace();
		}
		
		return resp;
	}
	
	
	
	
}

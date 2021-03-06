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

import com.ats.webapi.model.AlbumEnquiry;
import com.ats.webapi.model.SpecialCake;
import com.ats.webapi.model.rejectRemark;
import com.ats.webapi.model.newsetting.NewSetting;
import com.ats.webapi.repository.AlbumEnquiryRepository;
import com.ats.webapi.repository.NewSettingRepository;
import com.ats.webapi.repository.SpecialCakeRepository;
import com.ats.webapi.repository.rejectRemarkRepository;
import com.ats.webapi.service.SpecialCakeService;

//Akhilesh 2021-03-06  
@RestController
public class NewAppControllerApi {

	@Autowired
	SpecialCakeService spCakeService;

	@Autowired
	SpecialCakeRepository spCakeRepo;

	@Autowired
	NewSettingRepository newSettingRepository;

	@Autowired
	AlbumEnquiryRepository enquiryRepo;
	
	
	@Autowired
	rejectRemarkRepository rejectRemarkRepo; 
	
	
	

	@RequestMapping(value = "/getSpCakeByFrid", method = RequestMethod.POST)
	public @ResponseBody List<SpecialCake> getSpCakeByFrid(@RequestParam int frId) {
		List<SpecialCake> spList = new ArrayList<>();
		try {
			spList = spCakeService.getSpcakeByFrId(frId);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("Exception Occuered In /getSpCakeByFrid");
		}

		return spList;
	}

	@RequestMapping(value = "/getSpCakeByTsetting", method = RequestMethod.GET)
	public @ResponseBody List<SpecialCake> getSpCakeByTsetting() {
		List<SpecialCake> spList = new ArrayList<>();
		try {
			NewSetting newSetting = newSettingRepository.findBySettingKeyAndDelStatus("SP_CAKE_IDS_FOR_APP", 0);
			String spId = newSetting.getExVarchar2();
			String[] spIdArr = spId.split(",");
			// System.err.println(spIdArr.length);
			List<Integer> spIdList = new ArrayList<>();
			for (int i = 0; i < spIdArr.length; i++) {
				// System.err.println(spIdArr[i]);
				spIdList.add(Integer.parseInt(spIdArr[i]));
			}
			System.err.println("Spids-->" + spIdList);
			spList = spCakeRepo.findByDelStatusAndSpIdIn(0, spIdList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("Exception Occuered in /getSpCakeByTsetting");
		}

		return spList;
	}

	@RequestMapping(value = "/getEnquiryByEnqId", method = RequestMethod.POST)
	public @ResponseBody AlbumEnquiry getEnquiryByEnqId(@RequestParam int enqId) {
		AlbumEnquiry enq = new AlbumEnquiry();
		try {
			enq = enquiryRepo.getEnquiryByDelStatusAndId(enqId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("Exception Occuered in /getEnquiryByEnqId");
		}

		return enq;
	}

	@RequestMapping(value = "/addNewInquiry", method = RequestMethod.POST)
	public @ResponseBody AlbumEnquiry addNewInquiry(@RequestBody AlbumEnquiry enquiry) {
		AlbumEnquiry enq = new AlbumEnquiry();
		try {
			enq = enquiryRepo.save(enquiry);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("Exception Occuered in /addNewInquiry");
		}

		return enq;
	}

	// For Admin End Top 100 Enq
	@RequestMapping(value = "/get100Enquiries", method = RequestMethod.GET)
	public @ResponseBody List<AlbumEnquiry> get100Enquiries() {
		List<AlbumEnquiry> enquiries = new ArrayList<>();
		try {
			enquiries = enquiryRepo.get100Enquiry();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("Exception Occuered in /get100Enquiries");
		}
		return enquiries;
	}

	// For Franchisee End Top 50 Enq
	@RequestMapping(value = "/get50Enquiries", method = RequestMethod.POST)
	public @ResponseBody List<AlbumEnquiry> get50Enquiries(@RequestParam int frId) {

		List<AlbumEnquiry> enquiries = new ArrayList<>();
		try {
			enquiries = enquiryRepo.get50Enquiry(frId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("Exception Occuered in /get50Enquiries");
		}
		return enquiries;

	}
	
	
	
	
	
	@RequestMapping(value="/getAllRejectRemark",method=RequestMethod.GET)
	public @ResponseBody List<rejectRemark> getAllRejectRemark(){
		List<rejectRemark>  remarks=new ArrayList<>();
		try {
			remarks=rejectRemarkRepo.getAllRejectRemarksByDelStatus();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("Exception In /getAllRejectRemark");
		}
		
		return remarks;
	}
	
	
	

}

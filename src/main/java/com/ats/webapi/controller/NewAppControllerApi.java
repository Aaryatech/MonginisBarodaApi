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

import com.ats.webapi.commons.Firebase;
import com.ats.webapi.model.AlbumEnquiry;
import com.ats.webapi.model.Info;
import com.ats.webapi.model.SpecialCake;
import com.ats.webapi.model.rejectRemark;
import com.ats.webapi.model.newsetting.NewSetting;
import com.ats.webapi.repository.AlbumEnquiryRepository;
import com.ats.webapi.repository.FranchiseSupRepository;
import com.ats.webapi.repository.NewSettingRepository;
import com.ats.webapi.repository.SpecialCakeRepository;
import com.ats.webapi.repository.UserRepository;
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
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	FranchiseSupRepository franSuprepo;
	
	
	
	
	

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
			//System.err.println("Req="+enquiry.getEnquiryNo());
			//System.err.println("Res="+enq.getEnquiryNo());
			//NEW Enquiry Nofification
			if(enq.getStatus()==0 && enq.getEnquiryNo()>0) {
				List<String> userTokens=userRepo.findTokens();
				for(String token : userTokens) {
					if(token!=null) {
						Firebase.sendPushNotifForCommunicationBoth(token, "New Enquiry", "New Enquiry Added By"+enq.getExVar1(), "inbox", 1);
						System.err.println("New Nottification");
					}
					
				}
			}
			//Approve Enquiry Notification
			if(enquiry.getEnquiryNo()>0 && enq.getStatus()==1) {
			String frToken =franSuprepo.findTokenByFrId(enq.getFrId());
			if(frToken!=null) {
				Firebase.sendPushNotifForCommunicationBoth(frToken, "Enquiry Approved", "Enquiry Approved  For \t "+enq.getCustName(), "inbox", 0);
				System.err.println("Approve Nottification");
			}
			}
			
			
			//Reject Enquiry Notification
			if(enquiry.getEnquiryNo()>0 && enq.getStatus()==2) {
			String frToken =franSuprepo.findTokenByFrId(enq.getFrId());
			if(frToken!=null) {
				Firebase.sendPushNotifForCommunicationBoth(frToken, "Enquiry Rejected", "Enquiry Rejected  Because \t "+enq.getRejectRemark()+"For"+enq.getCustName(), "inbox", 0);
				System.err.println("Approve Nottification");
			}
			}
			
			
			
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
	
	
	
	//To Place New Order From Enq App
	@RequestMapping(value="/placeSpcakeOrderFromApp",method=RequestMethod.POST)
	public @ResponseBody Info placeSpcakeOrderFromApp(@RequestParam int frid,@RequestParam int menuId,@RequestParam int spId,
													@RequestParam int flavourId,@RequestParam int shapeId, @RequestParam String message,
													@RequestParam String  spInstruction,@RequestParam int extraCharg,@RequestParam int selWeight,
													@RequestParam int enqId )  {
		Info info=new Info();
		int flag=0;
		try {
			//Check Condition  Sp Cake Opreder Placed 
			int orederNo=111111;
			if(true) {
				flag=enquiryRepo.updateAddToProdFlag(enqId, String.valueOf(orederNo));
				if(flag>0) {
					info.setError(false);
					info.setMessage("Special Cake Order Placed Successfully");
				}else {
					info.setError(true);
					info.setMessage("Ubable To Place Special Cake Order");
				}
				
				
				if(!info.isError()) {
					String frToken =franSuprepo.findTokenByFrId(frid);
					if(frToken!=null) {
						Firebase.sendPushNotifForCommunicationBoth(frToken, "Oder Placed Successfully", "Order Place Oder Placed Successfully For Enquiry No:"+enqId, "inbox", 0);
						System.err.println("Approve Nottification");
					}
				}
				
				
			}
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("Exception Occuered In /placeSpcakeOrderFromApp");
			info.setError(true);
			info.setMessage("Ubable To Place Special Cake Order Exception Occuered");
		}
		
		return info;
	}
	
	
	
	
	
	

}

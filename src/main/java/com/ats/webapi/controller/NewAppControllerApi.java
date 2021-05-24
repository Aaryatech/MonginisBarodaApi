package com.ats.webapi.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.ats.webapi.commons.DateConvertor;
import com.ats.webapi.commons.Firebase;
import com.ats.webapi.model.AlbumEnquiry;
import com.ats.webapi.model.ErrorMessage;
import com.ats.webapi.model.Flavour;
import com.ats.webapi.model.FlavourConf;
import com.ats.webapi.model.FrMenus;
import com.ats.webapi.model.Info;
import com.ats.webapi.model.OrderSpecialCake;
import com.ats.webapi.model.SearchSpCakeResponse;
import com.ats.webapi.model.Setting;
import com.ats.webapi.model.Shape;
import com.ats.webapi.model.SpCakeOrders;
import com.ats.webapi.model.SpecialCake;
import com.ats.webapi.model.rejectRemark;
import com.ats.webapi.model.frsetting.FrSetting;
import com.ats.webapi.model.newsetting.NewSetting;
import com.ats.webapi.repo.ShapeRepo;
import com.ats.webapi.repository.AlbumEnquiryRepository;
import com.ats.webapi.repository.FlavourConfRepository;
import com.ats.webapi.repository.FlavourRepository;
import com.ats.webapi.repository.FrMenusRepository;
import com.ats.webapi.repository.FranchiseSupRepository;
import com.ats.webapi.repository.NewSettingRepository;
import com.ats.webapi.repository.SpCakeOrdersRepository;
import com.ats.webapi.repository.SpecialCakeRepository;
import com.ats.webapi.repository.UserRepository;
import com.ats.webapi.repository.rejectRemarkRepository;
import com.ats.webapi.repository.settingSpCakeRepository;
import com.ats.webapi.repository.frsetting.FrSettingRepo;
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
	
	@Autowired
	settingSpCakeRepository setting;
	
	

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
			System.err.println("Size Of Responce--->"+enquiries.size());
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
			System.err.println("Size Of Responce--->"+enquiries.size());
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
	

	@RequestMapping(value="/getAllRejectRemarkById",method=RequestMethod.POST)
	public @ResponseBody rejectRemark getAllRejectRemark(@RequestParam int rejectId){
		rejectRemark  remarks=new rejectRemark();
		try {
			remarks=rejectRemarkRepo.getAllRejectRemarkById(rejectId);
			System.out.println("remarks"+remarks);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("Exception In /getAllRejectRemark");
		}
		
		return remarks;
	}
	
	@RequestMapping(value="/postAllRejectRemark",method=RequestMethod.POST)
	public @ResponseBody rejectRemark postAllRejectRemark(@RequestBody rejectRemark map ){
		rejectRemark  remarks=new rejectRemark();
		try {
			remarks=rejectRemarkRepo.save(map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("Exception In /postAllRejectRemark");
		}
		
		return remarks;
	}
	
	
		// Delete Item
	@RequestMapping(value ="/deleteRemark", method = RequestMethod.POST)
	public @ResponseBody ErrorMessage deleteItem(@RequestParam Integer rejectId) {
System.err.println("error");
		ErrorMessage errorMessage = new ErrorMessage();

		int isUpdated = rejectRemarkRepo.deleteItems(rejectId);
		if (isUpdated == 1) {

			errorMessage.setError(false);
			errorMessage.setMessage("Items Deleted Successfully");
		} else {
			errorMessage.setError(false);
			errorMessage.setMessage("Items Deletion Failed");

		}
		return errorMessage;
	}
	
	
	@RequestMapping(value="/postUpdateSpCake",method=RequestMethod.POST)
	public @ResponseBody int UpdateSettingCake(@RequestParam String itemId ){
		int  settings=0;
		try {
			System.err.println(itemId);
			System.out.println("set"+itemId);
			settings=setting.updateItems(itemId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("Exception In /postAllRejectRemark");
		}
		
		return settings;
	}
	
	
	@Autowired
	private SpecialCakeService specialcakeService;
@Autowired FlavourRepository flavRepo;

@Autowired FrMenusRepository frMenuRepo;

@Autowired
FlavourConfRepository flavourConfRepository;
@Autowired SpCakeOrdersRepository saveSpOrdRepo;

@Autowired FrSettingRepo frSetRepo;

@Autowired ShapeRepo shapeRepo;

	//To Place New Order From Enq App
	@RequestMapping(value="/placeSpcakeOrderFromApp",method=RequestMethod.POST)
	public @ResponseBody Info placeSpcakeOrderFromApp(@RequestParam int frid,@RequestParam int menuId,@RequestParam int spId,
													@RequestParam int flavourId,@RequestParam int shapeId, @RequestParam String message,
													@RequestParam String  spInstruction,@RequestParam int extraCharg,@RequestParam float selWeight,
													@RequestParam int enqId,
		@RequestParam String delDate,@RequestParam String  custMobNo,@RequestParam String custName,
		@RequestParam String orderPhoto,@RequestParam float advAmt,@RequestParam int custId)  {
		Info info=new Info();
		int flag=0;
		SpCakeOrders saveOrderRes=new SpCakeOrders();
		try {
			
			SearchSpCakeResponse searchSpCakeResponse = specialcakeService.searchSpecialCakeBySpId(spId);
			//A M_SP_CAKE
			OrderSpecialCake spCake=searchSpCakeResponse.getSpecialCake();
			System.err.println("spCake " +spCake);
			//B flavor
			Flavour flavor=flavRepo.findOne(flavourId);
			
			spCake.setSpSelectedWt(selWeight);
			spCake.setExCharges(extraCharg);
			spCake.setAdvAmt(advAmt);
			
			//C Menu
			FrMenus menu=frMenuRepo.findByMenuId(menuId);
			SpCakeOrders order=new SpCakeOrders();
			spCake=	setSpCakeOrderData(spCake,flavor,menu);
			
			DateConvertor dateConv=new DateConvertor();
			
			
			
			order.setFrId(frid);
			
			order.setItemId(spCake.getSpCode());
			order.setSpId(spId);
			order.setMenuId(menuId);
			
			order.setOrderDate(new Date());//cur date
			
			order.setSpCustDob(new Date());
			order.setSpCustMobNo(custMobNo);
			order.setSpCustName(custName);
			
			Date deliveryDate=dateConv.getUtilDateFromStrYMDDate(delDate);
			order.setSpDeliveryDate(deliveryDate);
		    
			order.setIsBillGenerated(0);
			order.setIsAllocated(1);
			order.setIsSlotUsed(0);
			order.setSlipNo("0");
			order.setSpBookedForName("1");
			order.setSpBookForDob(new Date());
			order.setSpBookForMobNo("0");
			

			FrSetting frSetting=frSetRepo.findByFrId(frid);
			String spNoNewStr= "";
			int spNo = frSetting.getSpNo();

			int length = String.valueOf(spNo).length();
			length = 5 - length;
			StringBuilder spNoNew = new StringBuilder(frSetting.getFrCode()+"-");

			for (int i = 0; i < length; i++) {
				String j = "0";
				spNoNew.append(j);
			}
			spNoNew.append(String.valueOf(spNo));
			spNoNewStr=""+spNoNew;
			
			order.setSpDeliveryPlace(spNoNewStr); //ie orderNo For User
			order.setFrCode(frSetting.getFrCode()); //get from Service
			
			order.setSpFlavourId(flavourId);
			order.setSpSelectedWeight(selWeight);
			String currDate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			
			Date estDeliDate=DateConvertor.getUtilDateByAddSubGivenDays(currDate, Integer.parseInt(spCake.getSpBookb4()));
			order.setSpEstDeliDate(estDeliDate);
			
			order.setSpEvents("--");
			
			order.setSpType(flavor.getSpType());
			order.setSpProdTime(Integer.parseInt(spCake.getSpBookb4()));
			
			Date prodDate=DateConvertor.getUtilDateByAddSubGivenDays(delDate, -Integer.parseInt(spCake.getSpBookb4()));

			order.setSpProdDate(prodDate);
			order.setSpOrderNo(0);
			order.setSpMaxWeight(Float.parseFloat(spCake.getSpMaxwt()));
			order.setSpInstructions(spInstruction);
			order.setSpEventsName(message);
			
			order.setOrderPhoto(orderPhoto);
			order.setOrderPhoto2("");
			order.setCustEmail("-");
			order.setCustGstin("-");
			
			Shape shape=shapeRepo.findByShapeId(shapeId);
			order.setExInt1(custId);
			order.setExInt2(0);
			order.setExVar1(""+shape.getShapeName());//ie ctype in frontend
			order.setExVar2("ORDER From APP");
			
			order= spCalc( spCake,
					order);
			 saveOrderRes=	saveSpOrdRepo.save(order);
			
			System.err.println("order " +order);
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		try {
			//Check Condition  Sp Cake Opreder Placed 
			int orederNo=0;
			try {
			 orederNo=saveOrderRes.getSpOrderNo();
			}catch (Exception e) {
				orederNo=0;
			}
			if(orederNo>0) {
				info.setError(false);
				info.setMessage("Special Cake Order Placed Successfully");
				int	updateResponse = frSetRepo.updateFrSettingSpNo(frid);
				try {
				flag=enquiryRepo.updateAddToProdFlag(enqId, String.valueOf(orederNo),flavourId);
				}catch (Exception e) {
					e.printStackTrace();
				}
				System.err.println("flag " +flag);
				if(flag>-1) {
					info.setError(false);
					info.setMessage("Special Cake Order Placed Successfully Enq Updated");
				}else {
					info.setError(true);
					info.setMessage("Order Placed. Ubable To Update Enq");
				}
				
				
				if(!info.isError()) {
					String frToken =franSuprepo.findTokenByFrId(frid);
					if(frToken!=null) {
						Firebase.sendPushNotifForCommunicationBoth(frToken, "Oder Placed Successfully", "Order Place Oder Placed Successfully For Enquiry No:"+enqId, "inbox", 0);
						System.err.println("Approve Nottification");
					}
				}
				
			}else {
				info.setError(true);
				info.setMessage("Failed to Place Special Cake Order");
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
	
	public SpCakeOrders spCalc(OrderSpecialCake mspCake,   
			SpCakeOrders spCakeOrder) {
		float wt = mspCake.getSpSelectedWt();
		//1
		float flavourAdonRate=mspCake.getSprAddOnRate();
		float mrp=mspCake.getSprRateMrp();
		float profitPer=mspCake.getProfitPer();
		
		float spTotAddonRate=flavourAdonRate*wt;
		//console.log("spTotAddonRate",spTotAddonRate)
		float tax3 =(float) mspCake.getSpTax3();
		float tax1 =(float) mspCake.getSpTax1();
		float tax2 =(float) mspCake.getSpTax2();
		
		float sp_ex_charges =mspCake.getExCharges();
		float sp_disc = 0;
		float advAmt=mspCake.getAdvAmt();
		float spPrice=mrp*wt;
		float spSubTotal=(spTotAddonRate+spPrice+sp_ex_charges);
		float spBackEndRate=(spSubTotal-(spSubTotal*profitPer)/100);
		
		float spBackEndRateNew=spBackEndRate;
		float discAmt=spSubTotal*(sp_disc/100);
		//tc
		float disc_amt_entered=0f;
		float menu_disc_per=mspCake.getMenuDiscPer();
		float menuDISCAMT=0f;
		menuDISCAMT=spSubTotal*(menu_disc_per/100);
		//document.getElementById("menu_disc_rs").setAttribute('value',
				//menuDISCAMT.toFixed(2));
		
		discAmt=menuDISCAMT;
		if(sp_disc>0){
			float tot_disc_per=menu_disc_per+sp_disc;
			discAmt=spSubTotal*(tot_disc_per/100);
			float discAmt2=spSubTotal*(sp_disc/100);
			 //document.getElementById("sp_disc_rs").value=discAmt2.toFixed(2);
		}else if(disc_amt_entered>0){
			discAmt=disc_amt_entered+menuDISCAMT;
			float discPer=(disc_amt_entered/(spSubTotal/100));
			// document.getElementById("sp_disc").value=discPer.toFixed(2);
		}
		float spGrandTot=(spTotAddonRate+spPrice+sp_ex_charges)-discAmt;
		float taxableAmt= ((spGrandTot*100)/100+tax3);
		float spSubTotalTemp=spSubTotal-discAmt;
		float mrpBaseRate = (spSubTotalTemp * 100) / (tax3 + 100);

		float gstInRs = 0;
		float taxPerPerc1 = 0;
		float taxPerPerc2 = 0;
		float tax1Amt = 0f;
		float tax2Amt = 0f;
		
		if (tax3 == 0) {
			gstInRs = 0;
		} else {
			gstInRs = (mrpBaseRate * tax3) / 100;

			if (tax1 == 0) {
				taxPerPerc1 = 0;
			} else {
				taxPerPerc1 = (tax1 * 100) / tax3;
				tax1Amt = (gstInRs * taxPerPerc1) / 100;
			}
			if (tax2 == 0) {
				taxPerPerc2 = 0;
			} else {
				taxPerPerc2 = (tax2 * 100) / tax3;
				tax2Amt = (gstInRs * taxPerPerc2) / 100;
			}
		}
		float mGstAmt = mrpBaseRate;
		float gst_rs=taxableAmt;
		float m_gst_amt=mGstAmt;
		
		float sp_calc_price=spPrice;
		float sp_add_rate=spTotAddonRate;
		float sp_sub_total=spSubTotal;
		
		float sp_grand=spGrandTot;
		float total_amt=spSubTotal;
		float rm_amount=spGrandTot-advAmt;
		float t1amt=tax1Amt;
		float t2amt=tax2Amt;
		DecimalFormat df = new DecimalFormat("#.00");

		spCakeOrder.setSpGrandTotal(Float.parseFloat(df.format(sp_grand)));
		spCakeOrder.setSpMinWeight(mspCake.getMenuDiscPer());
		spCakeOrder.setRmAmount(Float.parseFloat(df.format(rm_amount)));
		spCakeOrder.setSpTotalAddRate(Float.parseFloat(df.format(sp_add_rate)));
		spCakeOrder.setSpAdvance(Float.parseFloat(df.format(advAmt)));
		spCakeOrder.setSpSubTotal(Float.parseFloat(df.format(sp_sub_total)));
		spCakeOrder.setSpPrice(Float.parseFloat(df.format(sp_calc_price)));
		spCakeOrder.setTax1(Float.parseFloat(df.format(tax1)));
		spCakeOrder.setTax1Amt(Float.parseFloat(df.format(t1amt)));
		spCakeOrder.setTax2Amt(Float.parseFloat(df.format(t2amt)));
		spCakeOrder.setTax2(Float.parseFloat(df.format(tax2)));

		spCakeOrder.setExtraCharges(Float.parseFloat(df.format(sp_ex_charges)));
		spCakeOrder.setDisc(sp_disc);
		spCakeOrder.setSpBackendRate(Float.parseFloat(df.format(spBackEndRateNew)));
		
		return spCakeOrder;
		
	}
	
	public OrderSpecialCake setSpCakeOrderData(OrderSpecialCake spCake, Flavour flavor, FrMenus menu) {
		System.err.println("Menu  " + menu);
		float spBackEndRate = 0.0f;
		float mrp_sprRate = 0.0f;
		float addOnRate = 0.0f;
		float profitPer = menu.getProfitPer();

		if (menu.getRateSettingFrom() == 0) {
			// By Master
			if (menu.getRateSettingType() == 1) {
				mrp_sprRate = spCake.getMrpRate1();
			} else if (menu.getRateSettingType() == 2) {
				mrp_sprRate = spCake.getMrpRate2();
			} else {
				mrp_sprRate = spCake.getMrpRate3();
			}
		} else {
			// By Flavor Confi
			// Get Flavor Configuration By SpId
			FlavourConf spFlavConf = new FlavourConf();

			if (flavor != null) {
				try {
					System.err.println("flavor In " + flavor);
					spFlavConf = flavourConfRepository.findBySpIdAndSpfIdAndDelStatus(spCake.getSpId(),flavor.getSpfId(),0);
				} catch (HttpClientErrorException e) {
					System.err.println("at here " + e.getResponseBodyAsString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (menu.getRateSettingType() == 1) {
				mrp_sprRate = spFlavConf.getMrp1();
			} else if (menu.getRateSettingType() == 2) {
				mrp_sprRate = spFlavConf.getMrp2();
			} else {
				mrp_sprRate = spFlavConf.getMrp3();
			}
		}
		if (menu.getRateSettingFrom() == 0 && spCake.getIsAddonRateAppli() == 1) {
			addOnRate = (float) flavor.getSpfAdonRate();
		}
		System.err.println("addOnRate " + addOnRate);
		//mrp_sprRate = mrp_sprRate + addOnRate;
		spBackEndRate = (mrp_sprRate - (mrp_sprRate * profitPer) / 100);

		spCake.setSprRateMrp(mrp_sprRate);
		spCake.setSpBackendRate(spBackEndRate);
		spCake.setSprAddOnRate(addOnRate);
		spCake.setProfitPer(profitPer);
		spCake.setMenuDiscPer(menu.getDiscPer());
		System.err.println("mrp_sprRate  " + mrp_sprRate + "spBackEndRate " + spBackEndRate + "addOnRate " + addOnRate);
		
		return spCake;
	}

}

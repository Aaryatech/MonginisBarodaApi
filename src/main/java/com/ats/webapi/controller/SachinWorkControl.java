package com.ats.webapi.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.commons.Common;
import com.ats.webapi.model.AllFrIdName;
import com.ats.webapi.model.AllFrIdNameList;
import com.ats.webapi.model.AllMenuJsonResponse;
import com.ats.webapi.model.AllMenus;
import com.ats.webapi.model.ChangeOrderRecord;
import com.ats.webapi.model.ConfigureFranchisee;
import com.ats.webapi.model.ErrorMessage;
import com.ats.webapi.model.Flavour;
import com.ats.webapi.model.FlavourConf;
import com.ats.webapi.model.FlavourList;
import com.ats.webapi.model.GetBillDetails;
import com.ats.webapi.model.GetBillsForFrList;
import com.ats.webapi.model.GetGrnItemConfig;
import com.ats.webapi.model.GetGrnItemConfigList;
import com.ats.webapi.model.Info;
import com.ats.webapi.model.Item;
import com.ats.webapi.model.ItemForMOrder;
import com.ats.webapi.model.Orders;
import com.ats.webapi.model.newsetting.NewSetting;
import com.ats.webapi.repository.AllFrIdNameRepository;
import com.ats.webapi.repository.ChangeOrderRecordRepo;
import com.ats.webapi.repository.ConfiSpCodeRepository;
import com.ats.webapi.repository.ConfigureFrRepository;
import com.ats.webapi.repository.FlavourConfRepository;
import com.ats.webapi.repository.FlavourRepository;
import com.ats.webapi.repository.GetBillDetailsRepository;
import com.ats.webapi.repository.GetGrnItemConfigRepository;
import com.ats.webapi.repository.ItemForMOrderRepository;
import com.ats.webapi.repository.ItemRepository;
import com.ats.webapi.repository.MainMenuConfigurationRepository;
import com.ats.webapi.repository.NewSettingRepository;
import com.ats.webapi.repository.OrderRepository;
import com.ats.webapi.service.GetBillsForFrService;

@RestController
public class SachinWorkControl {

	@Autowired NewSettingRepository newSettRepo;
	
	@RequestMapping(value = { "/getNewSettingByKey" }, method = RequestMethod.POST)
	public @ResponseBody NewSetting getNewSettingByKey(@RequestParam String settingKey,
			@RequestParam int delStatus) {
		NewSetting sett=new NewSetting();
		
		sett=newSettRepo.findBySettingKeyAndDelStatus(settingKey, delStatus);
		return sett;
	}

	@Autowired
	FlavourRepository flavourRepository;
	@RequestMapping(value = { "/saveFlavourConf" }, method = RequestMethod.POST)
	public @ResponseBody List<FlavourConf> saveFlavourConf(@RequestBody List<FlavourConf> flavourConfList) {

		List<FlavourConf> flList = new ArrayList<FlavourConf>();
		try {
			for (FlavourConf flavourConf : flavourConfList) {
				FlavourConf isPresent = flavourConfRepository.findByDelStatusAndSpfIdAndSpId(0, flavourConf.getSpfId(),
						flavourConf.getSpId());
				if (isPresent != null) {
					flavourConf.setSpFlavConfId(isPresent.getSpFlavConfId());
					FlavourConf flr = flavourConfRepository.save(flavourConf);
					flList.add(flr);
				} else {
					FlavourConf flr = flavourConfRepository.save(flavourConf);
					flList.add(flr);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return flList;
	}

	@Autowired
	FlavourConfRepository flavourConfRepository;
	
	@RequestMapping(value = "/updateFlavourConf", method = RequestMethod.POST)
	public Info updateFlavourConf(@RequestParam("spFlavConfId") int spFlavConfId, @RequestParam("rate") float rate,
			@RequestParam("mrp1") float mrp1, @RequestParam("mrp2") float mrp2, @RequestParam("mrp3") float mrp3) {
		Info info = new Info();
		try {
			int isUpdated = flavourConfRepository.updateFlavourConf(spFlavConfId, rate, mrp1, mrp2, mrp3);
			if (isUpdated > 0) {
				info.setError(false);
				info.setMessage("FlavourConf Updated Successfully.");
			} else {
				info.setError(true);
				info.setMessage("FlavourConf Updation Failed.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return info;
	}

	@RequestMapping(value = "/deleteFlavourConf", method = RequestMethod.POST)
	public @ResponseBody Info deleteFlavourConf(@RequestParam int spFlavConfId) {

		Info info = new Info();
		int isDelete = flavourConfRepository.deleteBySpFlavConfId(spFlavConfId);

		if (isDelete != 0) {
			info.setError(false);
			info.setMessage("Success");
		} else {
			info.setError(true);
			info.setMessage("Fail");
		}
		return info;

	}
	
	// 11-03-2021
		// Get MenuBy Section Ids
		@Autowired
		MainMenuConfigurationRepository mainMenuConfRepo;

		@RequestMapping(value = { "/getMenuListByMenuIds" }, method = RequestMethod.POST)
		public @ResponseBody AllMenuJsonResponse getFrMenuConfigureByMenuFrId(
				@RequestParam("menuIds") List<Integer> menuIds) {

			AllMenuJsonResponse menuJsonResponse = new AllMenuJsonResponse();
			ErrorMessage errorMessage = new ErrorMessage();
			try {
				List<AllMenus> menuList = mainMenuConfRepo.findByMenuIdIn(menuIds);
				menuJsonResponse.setMenuConfigurationPage(menuList);
				errorMessage.setError(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			errorMessage.setMessage("Menus shown successfully");
			menuJsonResponse.setErrorMessage(errorMessage);

			return menuJsonResponse;
		}
		
		@Autowired ItemRepository itemRepository;
		@Autowired
		ConfigureFrRepository configureFrRepository;
		@RequestMapping("/getItemAvailByMenuId")
		public @ResponseBody List<Item> getItemAvailByMenuId(
				@RequestParam int menuId) {
			
			ConfigureFranchisee menuConf = configureFrRepository.findByMenuIdAndDelStatus(menuId,0);

			
			List<Integer> ids = Stream.of(menuConf.getItemShow().split(",")).map(Integer::parseInt)
					.collect(Collectors.toList());
			List<Item> items=itemRepository.findAllItems(ids);	

			return items;

		}
		
		//Sac 14-04-2021
		@RequestMapping("/getItemAvailByMultiMenuIds")
		public @ResponseBody List<Item> getItemAvailByMultiMenuIds(
				@RequestParam List<String> menuIdList) {
			
			List<Item> items=itemRepository.getItemsConfiguredToMenuIdIn(menuIdList);	

			return items;

		}
		
		//Sac 11-03-2021
		@Autowired
		AllFrIdNameRepository frNameIdRepo;

		@RequestMapping(value = "/getAllFrIdNameByMenuIdConfigured", method = RequestMethod.POST)
		public @ResponseBody AllFrIdNameList getAllFrIdName(@RequestParam("menuId") int menuId) {

			AllFrIdNameList allFrIdNameList = new AllFrIdNameList();
			try {
				List<AllFrIdName> allFrIdNames = frNameIdRepo.getAllFrIdNameByMenuId(menuId);
				Info info = new Info();

				if (allFrIdNames != null) {
					allFrIdNameList.setFrIdNamesList(allFrIdNames);
					info.setError(false);
					info.setMessage("Successfully displayed all fr Name and Id");
					allFrIdNameList.setInfo(info);
				} else {
					info.setError(true);
					info.setMessage("error in getting fr Id and Names");
					allFrIdNameList.setInfo(info);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return allFrIdNameList;

		}

		
		// Sachin 12-03-2021 25-01-2021 For ManualOrder page Menus
		@RequestMapping(value = { "/getMenuListByFrAndSectionId" }, method = RequestMethod.POST)
		public @ResponseBody AllMenuJsonResponse getMenuListByFrAndSectionId(@RequestParam("frId") int frId,
				@RequestParam("sectionId") int sectionId) {

			AllMenuJsonResponse menuJsonResponse = new AllMenuJsonResponse();
			ErrorMessage errorMessage = new ErrorMessage();
			try {
				List<AllMenus> menuList = mainMenuConfRepo.findByFrIdAndSectionId(sectionId, frId);
				menuJsonResponse.setMenuConfigurationPage(menuList);
				errorMessage.setError(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			errorMessage.setMessage("Menus shown successfully");
			menuJsonResponse.setErrorMessage(errorMessage);

			return menuJsonResponse;
		}
		
		@Autowired
		ItemForMOrderRepository itemRepositoryForMOrderRepository;

		@RequestMapping(value = "/getItemListForMOrder", method = RequestMethod.POST)
		public @ResponseBody List<ItemForMOrder> getItemListForMOrder(@RequestParam("itemGrp1") int itemGrp1,
				@RequestParam("frId") int frId, @RequestParam("menuId") int menuId,
				@RequestParam("ordertype") int ordertype, @RequestParam("prodDate") String prodDate) {

			List<ItemForMOrder> itemList;
			try {

				itemList = itemRepositoryForMOrderRepository.getItemListForMOrder(menuId);

				/*
				 * if(ordertype==0) {
				 * System.err.println("itemGrp1"+itemGrp1+"frId"+menuId+"ordertype"+ordertype+
				 * "prodDate"+prodDate); itemList =
				 * itemRepositoryForMOrderRepository.getItemListForMOrder(itemGrp1,frId,menuId,
				 * prodDate); } else if(ordertype==2) { itemList =
				 * itemRepositoryForMOrderRepository.getItemListForMOrderMul(itemGrp1);
				 * 
				 * }else { itemList =
				 * itemRepositoryForMOrderRepository.getItemListForMOrderPrev(itemGrp1,frId);
				 * 
				 * }
				 */
			} catch (Exception e) {
				itemList = new ArrayList<>();
				e.printStackTrace();

			}
			return itemList;

		}
		

		@RequestMapping(value = { "/getFrMenuConfigureByMenuFrId" }, method = RequestMethod.POST)
		public @ResponseBody ConfigureFranchisee getFrMenuConfigureByMenuFrId1(@RequestParam("menuId") int menuId,
				@RequestParam("frId") int frId) {
			ConfigureFranchisee menuConf = configureFrRepository.findByMenuIdFrIdCustomeQuery(menuId, frId);
			return menuConf;
		}
		
		//Sac 12-03-2021
		@Autowired ConfiSpCodeRepository confSpCodeRepo;
		@RequestMapping("/getSPCodesByMenuId")
		public @ResponseBody List<String> getSPCodesByMenuId(
				@RequestParam int menuId) {

			List<String> spCakeCodesResponse = confSpCodeRepo.findSpCodeAdminSpOrder(menuId);

			return spCakeCodesResponse;

		}
		
		
		// new Method to display at frontEnd ordersp cake flavor list 12-03-2021
		@RequestMapping(value = { "/getFlavorsAndSpConfBySpId" }, method = RequestMethod.POST)
		@ResponseBody
		public FlavourList showFlavorsAndSpConfBySpId(@RequestParam("spId") int spId) {

			List<Flavour> jsonFlavourtList = flavourRepository.findBySpId(spId);
			FlavourList flavourList = new FlavourList();
			flavourList.setFlavour(jsonFlavourtList);
			Info info = new Info();
			info.setError(false);
			info.setMessage("Flavour list displayed Successfully");
			flavourList.setInfo(info);

			return flavourList;
		}
		
		
		
		//sac 22-04-2021
		
		@Autowired
		ChangeOrderRecordRepo changeOrdeRecRepo;
		@Autowired
		OrderRepository ordRepo;

		@RequestMapping(value = "/saveChangeOrderRecord", method = RequestMethod.POST)
		public @ResponseBody ChangeOrderRecord saveChangeOrderRecord(@RequestBody ChangeOrderRecord reqBody) {
			System.out.println("inside REST Sachin Work Controller saveChangeOrderRecord");
			ChangeOrderRecord res = new ChangeOrderRecord();
			try {
				Orders ord = ordRepo.getOneOrder(reqBody.getOrderId());
				System.err.println("Orders " + ord);
				reqBody.setItemId(Integer.parseInt(ord.getItemId()));
				reqBody.setFrId(ord.getFrId());
				res = changeOrdeRecRepo.save(reqBody);

			} catch (Exception e) {
				e.printStackTrace();
				res = new ChangeOrderRecord();
			}

			return res;
		}
		
		
		/*27-04-2021-For Acc GRN GVN Qty Validation for Approval
		 */
		
		/*SELECT SUM(gg.apr_qty_acc) as all_apr_qty,bd.bill_qty, gg.bill_detail_no
		FROM t_grn_gvn gg,t_bill_detail bd 
		WHERE gg.bill_detail_no IN(12249) and gg.grn_gvn_id NOT IN (3369) and gg.grn_gvn_status=6 and bd.bill_detail_no=gg.bill_detail_no 
		GROUP by gg.bill_detail_no*/
		@Autowired AprQtyGGRepo aprQtyGgRepo;
		
		@RequestMapping(value = { "/getBillDetailForGGAccAproval" }, method = RequestMethod.POST)
		public @ResponseBody List<AprQtyGG> getBillDetailForGGAccAproval(
				@RequestParam("billDetailNoList") List<Integer> billDetailNoList,
				@RequestParam("grnGvnIdList") List<Integer> grnGvnIdList
				) {
			
			List<AprQtyGG> aprGGQtyList=new ArrayList<AprQtyGG>();
			try {
				aprGGQtyList=aprQtyGgRepo.getAprQtyGG(billDetailNoList, grnGvnIdList);
				if(aprGGQtyList.isEmpty()) {
					aprGGQtyList=new ArrayList<AprQtyGG>();
				}
			}catch (Exception e) {
				System.err.println("Ex in getBillDetailForGGAccAproval "+e.getMessage());
				e.printStackTrace();
			}
					return aprGGQtyList;
			
		}
		
		//SAC 05-05-2021
		@Autowired
		GetBillDetailsRepository getBillDetailsRepository;
		@RequestMapping(value = "/getGrnItemsByExpiryDate", method = RequestMethod.POST)
		public @ResponseBody List<GetBillDetails> getGrnItemsByExpiryDate(@RequestParam("frId") int frId,
				@RequestParam("expiryDate") String expiryDate) {
			List<GetBillDetails> getBillDetailsrList = null;
			try {

				getBillDetailsrList = getBillDetailsRepository.getGrnItemsByExpiryDate(frId, expiryDate);
			} catch (Exception e) {

				e.printStackTrace();
			}

			return getBillDetailsrList;
		}

		@RequestMapping(value = "/getGrnItemsByIds", method = RequestMethod.POST)
		public @ResponseBody List<GetBillDetails> getGrnItemsByIds(@RequestParam("frId") int frId,
				@RequestParam("ids") List<Integer> idList) {
			List<GetBillDetails> getBillDetailsrList = null;
			try {

				getBillDetailsrList = getBillDetailsRepository.getGrnItemsByIds(frId, idList);
			} catch (Exception e) {

				e.printStackTrace();
			}

			return getBillDetailsrList;
		}
		
		
		
		@RequestMapping(value = "/getGrnItemsByExpDateAndItems", method = RequestMethod.POST)
		public @ResponseBody List<GetBillDetails> getGrnItemsByExpDateAndItems(@RequestParam("frId") int frId,
				@RequestParam("expiryDate") String expiryDate,@RequestParam("ids") List<Integer> idList,
				@RequestParam("catId") int catId) {
			List<GetBillDetails> getBillDetailsrList = new ArrayList<GetBillDetails>();
			try {
				if(catId<1)
				getBillDetailsrList = getBillDetailsRepository.getGrnItemsByExpDateAndItems(frId, expiryDate,idList);
				else
					getBillDetailsrList = getBillDetailsRepository.getGrnItemsByExpDateAndCatId(frId, expiryDate, catId);
			} catch (Exception e) {

				e.printStackTrace();
			}

			return getBillDetailsrList;
		}
		
		
		@Autowired
		GetBillsForFrService getBillsForFrService;

		
		@RequestMapping(value = "/getBillsForFrBetDate", method = RequestMethod.POST)
		public @ResponseBody GetBillsForFrList getBillsForFrService(@RequestParam("frId") int frId,
				@RequestParam("fd") String fd,@RequestParam("td") String td,
				@RequestParam("x") int x) {
			GetBillsForFrList billsForFrLisr=null;
			try {
			if(x==1) {	
			NewSetting sett=new NewSetting();
			
			java.util.Date cDate = new java.util.Date();
			String curDate = new SimpleDateFormat("dd-MM-yyyy").format(cDate);
			
			sett=newSettRepo.findBySettingKeyAndDelStatus("GVN_DATE_DIFF_DAYS", 0);
			
			String backDays = incrementDate(curDate, -Integer.parseInt(sett.getSettingValue1()));
			
			java.sql.Date cDate1 = Common.convertToSqlDate(curDate);
			java.sql.Date back15Date = Common.convertToSqlDate(backDays);

			  billsForFrLisr = getBillsForFrService.getBillForFr(frId, back15Date, cDate1);
			}else {
				  billsForFrLisr = getBillsForFrService.getBillForFrBetDate(frId, fd, td);
				
			}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return billsForFrLisr;
		}
		
		public String incrementDate(String date, int day) {

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Calendar c = Calendar.getInstance();
			try {
				c.setTime(sdf.parse(date));

			} catch (ParseException e) {
				System.out.println("Exception while incrementing date " + e.getMessage());
				e.printStackTrace();
			}
			c.add(Calendar.DATE, day); // number of days to add
			date = sdf.format(c.getTime());
			return date;

		}
		
		//Sac 11-05-2021
		
		
		@Autowired GetGrnItemConfigRepository getGvnItemConfRepo;
		
		@RequestMapping(value = "/getGvnItemConfigAsPerProd", method = RequestMethod.POST)
		public @ResponseBody GetGrnItemConfigList getGvnItemConfig(@RequestParam("catId") int catId,
				@RequestParam("fd") String fd,@RequestParam("td") String td,@RequestParam("itemString") List<Integer>itemString,
				@RequestParam("frId") int frId) {
			GetGrnItemConfigList gvnItemConfigList = new GetGrnItemConfigList();
			try {
				List<GetGrnItemConfig> gvnList=new ArrayList<GetGrnItemConfig>(); 
					if(itemString.get(0)==-1)
				gvnList = getGvnItemConfRepo.getGvnItemConfigByAllItemOfCatId(catId, fd, td,frId);
					else
						gvnList = getGvnItemConfRepo.getGvnItemConfigByItems(itemString, fd, td,frId);

				gvnItemConfigList.setGetGrnItemConfigs(gvnList);

				System.out.println("List getGvnItemConfig " + gvnItemConfigList.toString());
			} catch (Exception e) {
				System.out.println("inside rest: getGvnItemConfig  Error " + e.getMessage());

				e.printStackTrace();

			}

			return gvnItemConfigList;

		}
}
		
		


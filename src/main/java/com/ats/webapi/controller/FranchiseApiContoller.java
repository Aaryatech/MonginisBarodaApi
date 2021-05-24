package com.ats.webapi.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.CakeType;
import com.ats.webapi.model.ErrorMessage;
import com.ats.webapi.model.GetFrMenuExlPdf;
import com.ats.webapi.model.GetMenuIdAndType;
import com.ats.webapi.model.Info;
import com.ats.webapi.model.MenuShow;
import com.ats.webapi.model.RouteAbcVal;
import com.ats.webapi.model.RouteSection;
import com.ats.webapi.model.ShowFrMenuConfExlPdf;
import com.ats.webapi.model.State;
import com.ats.webapi.model.bill.Company;
import com.ats.webapi.model.prod.GetProductListExlPdf;
import com.ats.webapi.model.spprod.GetSpCakeExlPdf;
import com.ats.webapi.repo.FrMenusRepo;
import com.ats.webapi.repo.RouteAbcValRepo;
import com.ats.webapi.repo.ShapeRepo;
import com.ats.webapi.repo.StateRepository;
import com.ats.webapi.repositories.CakeTypeRepo;
import com.ats.webapi.repositories.FrMenuExlPdfRepo;
import com.ats.webapi.repositories.GetMenuIdAndTypeRepo;
import com.ats.webapi.repositories.GetSpCakeExlPdfRepo;
import com.ats.webapi.repositories.RoiteSectionRepository;
import com.ats.webapi.repositories.ShowFrMenuConfRepo;
import com.ats.webapi.repository.CompanyRepository;
import com.ats.webapi.repository.FranchiseeRepository;
import com.ats.webapi.repository.RouteMasterRepository;
import com.ats.webapi.repository.getproddetailbysubcat.GetProductListExlPdfRepo;

@RestController
public class FranchiseApiContoller {
	@Autowired RouteAbcValRepo abcRepo;
	
	@Autowired FranchiseeRepository franchRepo;
	
	@Autowired
	RouteMasterRepository routeMasterRepository;
	
	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	RoiteSectionRepository routeSecRepo;
	
	@Autowired 
	CakeTypeRepo cakeTypeRepo;
	
	@RequestMapping(value = { "/showRouteAbcValList" }, method = RequestMethod.GET)
	@ResponseBody
	public List<RouteAbcVal> showRouteAbcValList() {

		List<RouteAbcVal> val = abcRepo.findByDelStatusOrderByAbcIdAsc(0);

		return val;
	}
	
	
	@RequestMapping("/getFrRouteItList")
	public @ResponseBody List<Integer> getFrRouteItList() {

		List<Integer> res = new ArrayList<Integer>();
		try {
			res = franchRepo.getfrRouteIds();

		} catch (Exception e) {			
			e.printStackTrace();
		}
		return res;

	}
	
	
	
	@RequestMapping("/getInUseVehicleList")
	public @ResponseBody List<Integer> getInUseVehicleList() {

		List<Integer> res = new ArrayList<Integer>();
		try {
			res = franchRepo.getInUseVehicleList();

		} catch (Exception e) {			
			e.printStackTrace();
		}
		return res;

	}
	
	
	@RequestMapping("/getInUseStockType")
	public @ResponseBody List<Integer> getInUseStockType() {

		List<Integer> res = new ArrayList<Integer>();
		try {
			res = franchRepo.getInUseStockType();

		} catch (Exception e) {			
			e.printStackTrace();
		}
		return res;

	}
	
	
	
	
	
	@RequestMapping(value = { "/validateRouteShortName" }, method = RequestMethod.POST)
	public @ResponseBody RouteMaster validateRouteShortName(@RequestParam String shortName, @RequestParam int routeId) {

		
		RouteMaster route = new RouteMaster();
		if(routeId==0) {
			route = routeMasterRepository.findByShortNameIgnoreCase(shortName);
		}else {
			route = routeMasterRepository.findByShortNameIgnoreCaseAndRouteIdNotIn(shortName, routeId);
		}
		
		return route;
	}
	
	@RequestMapping(value = { "/validateRoutePrefix" }, method = RequestMethod.POST)
	public @ResponseBody RouteMaster validateRoutePrefix(@RequestParam String routePrefix, @RequestParam int routeId) {

		
		RouteMaster route = new RouteMaster();
		if(routeId==0) {
			route = routeMasterRepository.findByRoutePrefixIgnoreCase(routePrefix);
		}else {
			route = routeMasterRepository.findByRoutePrefixIgnoreCaseAndRouteIdNotIn(routePrefix, routeId);
		}		
		return route;
	}
	
	@RequestMapping(value = { "/showRouteListAndAbcType" }, method = RequestMethod.GET)
	@ResponseBody
	public List<RouteMaster> showRouteListAndAbcType() {

		List<RouteMaster> routeList = routeMasterRepository.getFrRouteAndAbcType();
		System.err.println("Route-------------"+routeList);
		return routeList;
	}
	
	// ---------------State --------------------------

	@RequestMapping(value = { "/saveState" }, method = RequestMethod.POST)
	public @ResponseBody State saveState(@RequestBody State state) {

		State res = new State();

		try {

			res = stateRepository.saveAndFlush(state);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return res;

	}

	@RequestMapping(value = { "/getStateByStateId" }, method = RequestMethod.POST)
	public @ResponseBody State getStateByStateId(@RequestParam("stateId") int stateId) {

		State state = null;
		try {
			state = stateRepository.findByStateId(stateId);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return state;

	}

	@RequestMapping(value = { "/getAllStates" }, method = RequestMethod.GET)
	public @ResponseBody List<State> getAllStates() {

		List<State> stateList = new ArrayList<State>();

		try {

			stateList = stateRepository.findAllByIsUsed(1);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return stateList;

	}

	@RequestMapping(value = { "/deleteState" }, method = RequestMethod.POST)
	public @ResponseBody ErrorMessage deleteState(@RequestParam("stateId") int stateId) {

		ErrorMessage errorMessage = new ErrorMessage();

		try {
			int delete = stateRepository.deleteState(stateId);

			if (delete == 1) {
				errorMessage.setError(false);
				errorMessage.setMessage(" Deleted Successfully");
			} else {
				errorMessage.setError(true);
				errorMessage.setMessage("Deletion Failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
			errorMessage.setError(true);
			errorMessage.setMessage("Deletion Failed :EXC");

		}
		return errorMessage;
	}
	
	
	/******************************************************************************/
	@RequestMapping(value="/addRouteSection",method=RequestMethod.POST)
	public @ResponseBody RouteSection addRouteSection(@RequestBody RouteSection section) {
		RouteSection sec=new RouteSection();
		try {
			sec=routeSecRepo.save(section);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception Occuered In /addRouteSection");
			e.printStackTrace();
		}
		
		return sec;
	}
	
	@RequestMapping(value="/getAllRouteSection",method=RequestMethod.GET)
	public @ResponseBody List<RouteSection> getAllRouteSection(){
		List<RouteSection> sectionResp=new ArrayList<>();
		try {
			sectionResp=routeSecRepo.getAllSection();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception Occuered In /getAllSection");
			e.printStackTrace();
		}
		
		return sectionResp;
	}
	
	@RequestMapping(value="/getRouteSectionById",method=RequestMethod.POST)
	public @ResponseBody RouteSection getRouteSectionBId(@RequestParam int sectionId) {
		RouteSection secResp=new RouteSection();
		try {
			secResp = routeSecRepo.getSingleSectionById(sectionId);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception Occuered In /getRouteSectionBId");
			e.printStackTrace();
		}
		return secResp;
	}
	
	
	@RequestMapping(value="/deleteRouteSection",method=RequestMethod.POST)
	public @ResponseBody Info deleteRouteSection(@RequestParam int sectionId) {
		Info info=new Info();
		int flag=0;
		try {
			flag=routeSecRepo.deleteRouteSection(sectionId);
			if(flag>0) {
				info.setError(false);
				info.setMessage("Route Section Deleted Successfully!!!");
			}else {
				info.setError(true);
				info.setMessage("Unable To Delete Route Section!!!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			info.setError(true);
			info.setMessage("Unable To Delete Route Section Exception Occuered !!!");
			System.err.println("Exception Occuered In /deleteRouteSection");
			e.printStackTrace();
		}		
		
		return info;
	}
	
	@RequestMapping(value="/getRouteSectionList",method=RequestMethod.GET)
	public @ResponseBody List<RouteSection> getRouteSectionList(){
		List<RouteSection> secRouteList=new ArrayList<RouteSection>();
		
		try {
			secRouteList=routeSecRepo.getSectionAndRouteList();
		} catch (Exception e) {			
			System.err.println(e.getMessage());
			e.printStackTrace();
		}		
		return secRouteList;		
	}
	
	//----------------------------------------------------------------
			@Autowired GetProductListExlPdfRepo prodExpRepo;
			@RequestMapping(value = { "/getAllProdctExlPdf" }, method = RequestMethod.GET)
			public @ResponseBody List<GetProductListExlPdf> getAllProdctExlPdf() {

				List<GetProductListExlPdf> prod = new ArrayList<GetProductListExlPdf>();

				try {

					prod = prodExpRepo.getProductExlPdfList();

				} catch (Exception e) {

					e.printStackTrace();

				}
				return prod;

			}
			
			
			// Show Cake Type List
			@RequestMapping(value = { "/showCakeTypeList" }, method = RequestMethod.GET)
			@ResponseBody
			public List<CakeType> showCakeTypeList() {

				List<CakeType> cakeType = cakeTypeRepo.findByDelStatusOrderByCakeTypeIdDesc();

				return cakeType;
			}
			
			@RequestMapping(value = { "/getCakeTypeById" }, method = RequestMethod.POST)
			public @ResponseBody CakeType getCakeTypeById(@RequestParam("cakeTypeId") int cakeTypeId) {

				CakeType cakeType = new CakeType();
				try {
					cakeType = cakeTypeRepo.findBycakeTypeId(cakeTypeId);

				} catch (Exception e) {
					e.printStackTrace();
				}
				return cakeType;

			}
			
			@RequestMapping(value = { "/deleteCakeType" }, method = RequestMethod.POST)
			public @ResponseBody ErrorMessage deleteCakeType(@RequestParam("cakeTypeId") int cakeTypeId) {

				ErrorMessage errorMessage = new ErrorMessage();

				try {
					int res = cakeTypeRepo.delCakeType(cakeTypeId);

					if (res > 0) {
						errorMessage.setError(false);
						errorMessage.setMessage(" Deleted Successfully");
					} else {
						errorMessage.setError(true);
						errorMessage.setMessage("Deletion Failed");
					}

				} catch (Exception e) {

					e.printStackTrace();
					errorMessage.setError(true);
					errorMessage.setMessage("Deletion Failed :EXC");

				}
				return errorMessage;
			}
			
			@RequestMapping(value = { "/insertCakeType" }, method = RequestMethod.POST)
			public @ResponseBody CakeType insertCakeType(@RequestBody CakeType cakeType) {

				CakeType res = new CakeType();

				try {

					res = cakeTypeRepo.saveAndFlush(cakeType);

				} catch (Exception e) {

					e.printStackTrace();

				}
				return res;

			}
			
		@Autowired
		GetSpCakeExlPdfRepo spCakeExlRepo;
		@RequestMapping(value = { "/getSpCakeListPdfExl" }, method = RequestMethod.GET)
			public @ResponseBody List<GetSpCakeExlPdf> getSpCakeListPdfExl() {

				Info info = new Info();
				
				List<GetSpCakeExlPdf> res=new ArrayList<GetSpCakeExlPdf>();
			
			
				res=spCakeExlRepo.getSpCakeListExlPdf();
					return  res;
				
			}
	//--------------------------------------------------------------------
		
		
		@Autowired
		FrMenuExlPdfRepo frMenuExlRepo;
		
		@RequestMapping(value = { "/getAllFrMenusList" }, method = RequestMethod.GET)
		public @ResponseBody List<GetFrMenuExlPdf> getAllFrMenusList() {

			List<GetFrMenuExlPdf> menuList = new ArrayList<GetFrMenuExlPdf>();

			menuList = frMenuExlRepo.getAllFrMenus();
			
			return menuList;

		}
		
		@Autowired
		GetMenuIdAndTypeRepo sMenuRepo;	
		@RequestMapping(value = { "/getAllSavedMenuIds" }, method = RequestMethod.GET)
		public @ResponseBody List<GetMenuIdAndType> getAllSavedMenuIds() {

			List<GetMenuIdAndType> menuIds = new ArrayList<GetMenuIdAndType>();

			menuIds = sMenuRepo.getSavedMenuIds();
			return menuIds;

		}
		
		@Autowired
		ShowFrMenuConfRepo frMenuConfRepo;
		@RequestMapping(value = { "/getFrMenuCogigDetails" }, method = RequestMethod.GET)
		public @ResponseBody List<ShowFrMenuConfExlPdf> getFrMenuCogigDetails() {

			List<ShowFrMenuConfExlPdf> menuList = new ArrayList<ShowFrMenuConfExlPdf>();

			menuList = frMenuConfRepo.getfrMenuConfigList();
			
			return menuList;

		}
		
		
		
		@RequestMapping(value = { "/getFrMenuCogigDetailsByIds" }, method = RequestMethod.POST)
		public @ResponseBody List<ShowFrMenuConfExlPdf> getFrMenuCogigDetailsyIds(@RequestParam List<String> menuIds, @RequestParam List<String> frIds) {

			List<ShowFrMenuConfExlPdf> menuList = new ArrayList<ShowFrMenuConfExlPdf>();

			menuList = frMenuConfRepo.getAllFrMenusExlPdfList(menuIds, frIds);
			
			System.err.println("List--------------------"+menuList);
			
			return menuList;

		}
		
		
		//--------------------------------------------------------------------
		@Autowired
		FrMenusRepo frMenuRepo;
		
		@RequestMapping(value = { "/saveNewMenu" }, method = RequestMethod.POST)
		public @ResponseBody MenuShow saveNewMenu(@RequestBody MenuShow menu) {
			
			Info info=new Info();
			MenuShow saveMenu = frMenuRepo.save(menu);	    
			
			if(saveMenu.getMenuId()>0)
			{
				info.setError(false);
				info.setMessage("Data Insert Successfully");
			}
			else
			{
				info.setError(true);
				info.setMessage("Data Insert Failed");
			}		
			return saveMenu;
		}
		
		@RequestMapping(value = { "/getAllFrMenus" }, method = RequestMethod.GET)
		public @ResponseBody List<MenuShow> getAllFrMenus() {

			List<MenuShow> menuList = new ArrayList<MenuShow>();

			menuList = frMenuRepo.findByDelStatusOrderByCatId(0);
			return menuList;

		} 
		@RequestMapping(value = { "/getFrMenuById" }, method = RequestMethod.POST)
		public @ResponseBody MenuShow getFrMenuById(@RequestParam int menuId) {

			
			MenuShow menu = frMenuRepo.findByMenuId(menuId);
			return menu;
			 
		}
		
		@RequestMapping(value = { "/deleteFrMenu" }, method = RequestMethod.POST)
		public @ResponseBody Info deleteFrMenu(@RequestParam int menuId) {
			Info info = new Info();
			int res = 0;
			try {
				res = frMenuRepo.deleteMenuById(menuId);
				if (res > 0) {
					info.setError(false);
					info.setMessage("Fr Menu Deleted");

				} else {
					info.setError(true);
					info.setMessage("Unable To  Delete Fr Menu");

				}
			} catch (Exception e) {

				e.printStackTrace();
				info.setError(true);
				info.setMessage("Unable To  Delete Fr Menu");
				System.err.println("Exception In /deleteFrMenu : " + e.getMessage());
			}

			return info;

		}
		
		@RequestMapping(value="/deleteMultiFr",method=RequestMethod.POST)
		public @ResponseBody Info deleteMultiFr(@RequestParam List<String> frIds) {
		Info info=new Info();
		int flag=0;
		try {
			flag=franchRepo.deleteMultiFrByFrId(frIds);
			if(flag>0) {
				info.setError(false);
				info.setMessage("Franchisees Deleted!!");
			}else {
				info.setError(true);
				info.setMessage("Unable To  Delete Franchisees!!!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			info.setError(true);
			info.setMessage("Unable To  Delete Franchisees Exception Occuerd.!!!");
			System.err.println("Excep In /deleteMultiFr");
			e.printStackTrace();
		}
		return info;
		
		}
		
		
		
		
		
		
		
}

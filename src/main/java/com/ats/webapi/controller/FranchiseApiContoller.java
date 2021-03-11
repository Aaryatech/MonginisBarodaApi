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
import com.ats.webapi.model.Info;
import com.ats.webapi.model.RouteAbcVal;
import com.ats.webapi.model.RouteSection;
import com.ats.webapi.model.State;
import com.ats.webapi.repo.RouteAbcValRepo;
import com.ats.webapi.repo.StateRepository;
import com.ats.webapi.repositories.RoiteSectionRepository;
import com.ats.webapi.repository.FranchiseeRepository;
import com.ats.webapi.repository.RouteMasterRepository;

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
}

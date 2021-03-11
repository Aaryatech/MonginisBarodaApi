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
import com.ats.webapi.model.RouteAbcVal;
import com.ats.webapi.model.State;
import com.ats.webapi.repo.RouteAbcValRepo;
import com.ats.webapi.repo.StateRepository;
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
}

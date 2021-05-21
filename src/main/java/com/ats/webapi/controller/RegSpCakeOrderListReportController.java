package com.ats.webapi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//import com.ats.webapi.controller.ConfigureFrBean;
import com.ats.webapi.model.ChangeOrderRecord;
import com.ats.webapi.model.ConfigureFrBean;
import com.ats.webapi.model.Info;
import com.ats.webapi.repository.ChangeOrderRecordRepo;
import com.ats.webapi.repository.ConfigureFrListRepository;
import com.ats.webapi.repository.RegSpCakeOrListRepo;

@RestController
public class RegSpCakeOrderListReportController {

    @Autowired 
	RegSpCakeOrListRepo reg; 
	@RequestMapping(value = { "/deleteRegSpCkReport" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteState(@RequestParam("rspId") int rspId) {
		System.out.println("delete api rspId: "+rspId);
		Info info = new Info();

		try {

			int delete = reg.deleteRspById(rspId);

			if (delete > 0) {
				info.setError(false);
				info.setMessage("State Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed To Delete State");
			}

		} catch (Exception e) {
			info.setError(true);
			info.setMessage("Failed To Delete State");
			System.err.println("Excep in deleteState : " + e.getMessage());
			e.printStackTrace();
		}

		return info;

	}
	
	@RequestMapping(value = { "/updateRegSpCkReport" }, method = RequestMethod.POST)
	public @ResponseBody Info updateRegSpCkReport(@RequestParam("rspId") int rspId,@RequestParam("qty") int qty) {
		System.out.println("delete api rspId: "+rspId);
		Info info = new Info();

		try {

			int delete = reg.updateRspById(rspId,qty);

			if (delete > 0) {
				info.setError(false);
				info.setMessage("State Deleted Successfully");
			} else {
				info.setError(true);
				info.setMessage("Failed To Delete State");
			}

		} catch (Exception e) {
			info.setError(true);
			info.setMessage("Failed To Delete State");
			System.err.println("Excep in deleteState : " + e.getMessage());
			e.printStackTrace();
		}

		return info;

	}
	
	@Autowired
	ChangeOrderRecordRepo changeOrdeRecRepo;
	
	@RequestMapping(value = "/getChangedOrdersRecordList", method = RequestMethod.POST)
	public @ResponseBody List<ChangeOrderRecord> getChangedOrdersRecordList(@RequestParam List<String> frIdList,
			@RequestParam String fromDate, @RequestParam String toDate) {
		
		
		System.out.println("inside REST  getChangedOrdersRecordList");
		List<ChangeOrderRecord> resList = new ArrayList<ChangeOrderRecord>();
		try {

			if (frIdList.contains("-1")) {
				
				resList = changeOrdeRecRepo.getChangeOrderRecListAllFr(fromDate, toDate);
			} else {
			
				resList = changeOrdeRecRepo.getChangeOrderRecListSpecFr(frIdList, fromDate, toDate);
				System.out.println("esle" + resList);
			}

		} catch (Exception e) {
			resList = new ArrayList<ChangeOrderRecord>();
			e.printStackTrace();
		}
	
		return resList;
	}
	
	@Autowired
	ConfigureFrListRepository typeRepo;
	@RequestMapping(value = { "/callConfigMenuByType" }, method = RequestMethod.POST)
	public @ResponseBody List<ConfigureFrBean> callConfigMenuByType(@RequestParam("catId") List<Integer> catId) {
		//ConfigureFrBean beanList = new ConfigureFrBean();
		
		System.out.println("catId"+catId);
		List<ConfigureFrBean> configBean = typeRepo.findConfiFrListByType(catId);

		System.out.println("ConfigureFrBean"+configBean);

		return configBean;
	}
}

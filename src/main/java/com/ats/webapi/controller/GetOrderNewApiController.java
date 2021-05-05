package com.ats.webapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.commons.Common;
import com.ats.webapi.model.GetOrder;
import com.ats.webapi.model.GetOrderList;
import com.ats.webapi.model.GetOrderNew;
import com.ats.webapi.model.Info;
import com.ats.webapi.repository.GetOrderNewRepository;

@RestController
public class GetOrderNewApiController {

	
	@Autowired
	GetOrderNewRepository getOrderNewRepo;
	
	@RequestMapping(value = { "/getOrderListForAllFrNew" }, method = RequestMethod.POST)
	@ResponseBody
	public List<GetOrderNew> getOrderListForAllFrNew(@RequestParam List<String> menuId, @RequestParam String date) {
		List<GetOrderNew> orderList = new ArrayList<>();
		try {

			String strDate = Common.convertToYMD(date);
			System.out.println("Converted date " + strDate);

			orderList = getOrderNewRepo.findAllNativeAllFr(menuId, strDate);

		System.err.println("orderr list displayed Successfully");
			

		} catch (Exception e) {

			System.out.println("exception in /getOrderListForAllFrNew" + e.getMessage());
		}
		return orderList;

	}
}

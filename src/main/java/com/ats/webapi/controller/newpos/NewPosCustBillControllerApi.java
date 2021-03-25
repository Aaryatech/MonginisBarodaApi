package com.ats.webapi.controller.newpos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.newpos.NewPosBillItem;
import com.ats.webapi.repository.newpos.NewPosBillItemRepo;

@RestController
public class NewPosCustBillControllerApi {

	@Autowired
	NewPosBillItemRepo custBillRepo;

	@RequestMapping(value = "/getItemListWithCS", method = RequestMethod.POST)
	public @ResponseBody List<NewPosBillItem> getItemListWithCS(@RequestParam int frId, @RequestParam String fromDt,
			@RequestParam String toDt, @RequestParam int month, @RequestParam int year, @RequestParam int frStockType,
			@RequestParam List<Integer> itemList,@RequestParam int flag) {

		List<NewPosBillItem> itemListResp = new ArrayList<NewPosBillItem>();
		// System.err.println("dates====="+fromDt+"/t"+toDt);

		try {
 
			if (flag == 1) {
				itemListResp = custBillRepo.getNewPosBillItems(frId, fromDt, toDt, month, year, frStockType, itemList);
			} else {
				itemListResp = custBillRepo.getNewPosBillItems(frId,itemList);
			}

		} catch (Exception e) {
			// TODO: handle exception
			itemListResp = new ArrayList<NewPosBillItem>();
			System.err.println("Exception Occuered In Catch Block Of /getItemListWithCS ");
			e.printStackTrace();
		}

		return itemListResp;
	}

}

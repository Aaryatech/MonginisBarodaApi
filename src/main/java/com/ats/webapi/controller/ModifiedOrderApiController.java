package com.ats.webapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.ChangeOrderRecord;
import com.ats.webapi.repository.ChangeOrderRecordRepo;

@RestController
public class ModifiedOrderApiController {

//	@Autowired
//	ChangeOrderRecordRepo changeOrdeRecRepo;
//	
//	@RequestMapping(value = "/getChangedOrdersRecordList", method = RequestMethod.POST)
//	public @ResponseBody List<ChangeOrderRecord> getChangedOrdersRecordList(@RequestParam List<String> frIdList,
//			@RequestParam String fromDate, @RequestParam String toDate) {
//
//		System.out.println("inside REST  getChangedOrdersRecordList");
//		List<ChangeOrderRecord> resList = new ArrayList<ChangeOrderRecord>();
//		try {
//
//			if (frIdList.contains("-1")) {
//				resList = changeOrdeRecRepo.getChangeOrderRecListAllFr(fromDate, toDate);
//			} else {
//				resList = changeOrdeRecRepo.getChangeOrderRecListSpecFr(frIdList, fromDate, toDate);
//			}
//
//		} catch (Exception e) {
//			resList = new ArrayList<ChangeOrderRecord>();
//			e.printStackTrace();
//		}
//		System.out.println("few fr selected" + resList);
//		return resList;
//	}
}

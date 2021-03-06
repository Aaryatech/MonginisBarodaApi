package com.ats.webapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.ErrorMessage;
import com.ats.webapi.model.einv.EInvBillDetail;
import com.ats.webapi.model.einv.EInvBillDetailRepo;
import com.ats.webapi.model.einv.EInvBillHeader;
import com.ats.webapi.model.einv.EInvBillHeaderRepo;

@RestController
public class EInvoiceApiController {

	@Autowired EInvBillHeaderRepo eInvBillHeader;
	
	@Autowired EInvBillDetailRepo eInvBillDetailRepo;
	
	
	@RequestMapping(value = { "/getBillListForEInvoice" }, method = RequestMethod.POST)
	public @ResponseBody List<EInvBillHeader> getBillListForEInvoice(
			@RequestParam("billIdList") List<String> billIdList) {
		System.err.println("In getBillListForEInvoice");
		List<EInvBillHeader> billHeadList = new ArrayList<>();
		try {

			billHeadList = eInvBillHeader.getBillHeaderforEInv(billIdList);

			for (int i = 0; i < billHeadList.size(); i++) {
				try {

					List<EInvBillDetail> billDetail = eInvBillDetailRepo
							.getEInvBillDetail(billHeadList.get(i).getBillNo());


					billHeadList.get(i).seteInvBillDetail(billDetail);

				} catch (Exception e) {
					e.printStackTrace();

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return billHeadList;
	}
	
	
	
	@RequestMapping(value = { "/updateEInvDataInBill" }, method = RequestMethod.POST)
	public @ResponseBody ErrorMessage updateEInvDataInBill(@RequestParam int billNo, @RequestParam String irnAckData) {
		System.err.println("In Update "+irnAckData);
		ErrorMessage errorMessage = null;
		try {
			int res = eInvBillHeader.updateEInvDataInBill(billNo, irnAckData);
			
			if(res>0) {
				errorMessage = new ErrorMessage();
				errorMessage.setError(false);
				errorMessage.setMessage("Success");
			}else {
				errorMessage = new ErrorMessage();
				errorMessage.setError(true);
				errorMessage.setMessage("Failed");

			}
			
			// errorMessage.set
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = new ErrorMessage();
		}
		return errorMessage;
	}
}

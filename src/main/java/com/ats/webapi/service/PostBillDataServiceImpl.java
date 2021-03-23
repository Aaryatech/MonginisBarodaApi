package com.ats.webapi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ats.webapi.model.AllMenus;
import com.ats.webapi.model.FrItemStockConfigureList;
import com.ats.webapi.model.PostBillDetail;
import com.ats.webapi.model.PostBillHeader;
import com.ats.webapi.model.bill.Company;
import com.ats.webapi.repository.CompanyRepository;
import com.ats.webapi.repository.FrItemStockConfigureRepository;
import com.ats.webapi.repository.MainMenuConfigurationRepository;
import com.ats.webapi.repository.OrderRepository;
import com.ats.webapi.repository.PostBillDetailRepository;
import com.ats.webapi.repository.PostBillHeaderRepository;
import com.ats.webapi.repository.RegularSpCkOrderRepository;
import com.ats.webapi.repository.SpCakeOrdersRepository;
import com.ats.webapi.repository.UpdateBillDetailForGrnGvnRepository;
import com.ats.webapi.repository.UpdateSeetingForPBRepo;

@Service
public class PostBillDataServiceImpl implements PostBillDataService {

	@Autowired
	PostBillHeaderRepository postBillHeaderRepository;

	@Autowired
	PostBillDetailRepository postBillDetailRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	SpCakeOrdersRepository spCakeOrdersRepository;

	@Autowired
	RegularSpCkOrderRepository regularSpCkOrderRepository;

	@Autowired
	UpdateBillDetailForGrnGvnRepository updateBillDetailForGrnGvnRepository;

	@Autowired // added here on 3 march
	UpdateSeetingForPBRepo updateSeetingForPBRepo;

	@Autowired // added here 3 march
	FrItemStockConfigureRepository frItemStockConfRepo;

	/*
	 * @Override public List<PostBillDetail> saveBillDetails(List<PostBillDetail>
	 * postBillDetail) {
	 * 
	 * List<PostBillDetail> billDetail=new ArrayList<PostBillDetail>();
	 * for(PostBillDetail pBDetails:postBillDetail) {
	 * 
	 * billDetail=postBillDataService.saveBillDetails(postBillDetail);
	 * 
	 * }
	 * 
	 * return billDetail; }
	 */
	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	JdbcTemplate jdbcTemp;

	@Autowired MainMenuConfigurationRepository menuShowRepo;
	@Override
	public List<PostBillHeader> saveBillHeader(List<PostBillHeader> postBillHeader) {

		List<PostBillHeader> pbHeaderList = new ArrayList<>();
		
		List<AllMenus> menuShowList=menuShowRepo.findByDelStatus(0);
		
		PostBillHeader postBillHeaders = new PostBillHeader();
		for (int i = 0; i < postBillHeader.size(); i++) {
			String invoiceNo = null;
			int settingValue = 0;
			Company company = new Company();
			try {
				String pattern = "yyyy-MM-dd";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

				String date = simpleDateFormat.format(postBillHeader.get(i).getBillDate());

				company = companyRepository.findByBillDate(date);

				invoiceNo = company.getExVar4();
				// settingValue=Integer.parseInt(company.getExVar5());
			} catch (Exception e) {
				// TODO: handle exception
			}
			settingValue = frItemStockConfRepo.findBySettingKey("PB");

			System.out.println("Setting Value Received " + settingValue);

			int length = String.valueOf(settingValue).length();

			invoiceNo = invoiceNo + "" + String.format("%06d", settingValue);

			/*
			 * if (length == 1) { invoiceNo =invoiceNo+"/0000"+settingValue; } else if
			 * (length == 2) {
			 * 
			 * invoiceNo =invoiceNo+"/000"+settingValue; }else if (length == 3) { invoiceNo
			 * =invoiceNo+"/00"+settingValue; }else if (length == 4) { invoiceNo
			 * =invoiceNo+"/0"+settingValue; } else {
			 * 
			 * invoiceNo =invoiceNo+"/"+settingValue; }
			 */
			System.out.println(invoiceNo + "*** settingValue= " + settingValue);

			postBillHeader.get(i).setInvoiceNo(invoiceNo);

			postBillHeaders = postBillHeaderRepository.save(postBillHeader.get(i));

			if (postBillHeaders != null && postBillHeaders.getBillNo() > 0) {

				settingValue = settingValue + 1;
				int result = updateSeetingForPBRepo.updateSeetingForPurBill(settingValue, "PB");

				System.err.println("PB setting value updated " + result);

			}

			int billNo = postBillHeader.get(i).getBillNo();

			List<PostBillDetail> postBillDetailList = postBillHeader.get(i).getPostBillDetailsList();
			String myString = "INSERT INTO t_bill_detail (bill_detail_no, bill_no, order_id, menu_id, cat_id, item_id, order_qty, bill_qty, mrp, rate_type, rate, base_rate, taxable_amt, sgst_per, sgst_rs, cgst_per, cgst_rs, igst_per, igst_rs, total_tax, grand_total, remark, del_status, grn_type, expiry_date, is_grngvn_applied, disc_per, cess_per, cess_rs, hsn_code) VALUES";

			StringBuilder querySb = new StringBuilder();
			querySb = new StringBuilder();
			querySb.append(myString);
			
			List<String> tOrderIdList=new  ArrayList<String>();
			List<String> tSpCakeOrdIdList=new  ArrayList<String>();
			List<String> tRegSpCakeOrderIdList=new  ArrayList<String>();
			
			for (int j = 0; j < postBillDetailList.size(); j++) {

				// querySb.append(myString);
				PostBillDetail billDetail = postBillDetailList.get(j);

				billDetail.setBillNo(billNo);

				if (billDetail.getOrderQty() == 0) {

					System.out.println("order qty 0 received ");

					int updateOrderStatus = orderRepository.updateBillStatus(billDetail.getOrderId(), 1);

				}

				else {

					//SAC COM postBillDetailRepository.save(billDetail);

					int res = 0;
					if (billDetail.getCatId() != 5) {
						int menuType=-1;
						for(int x=0;x<menuShowList.size();x++) {
							if(menuShowList.get(x).getMenuId()==billDetail.getMenuId()) {
								menuType=Integer.parseInt(menuShowList.get(x).getIsSameDayApplicable());
								break;
							}
						}
						if(menuType==0) {
							tOrderIdList.add(""+billDetail.getOrderId());
						} 
						if(menuType==3) {
							tRegSpCakeOrderIdList.add(""+billDetail.getOrderId());
						}
						/*SAC OLD if (billDetail.getMenuId() != 42 && billDetail.getMenuId() != 80
								&& billDetail.getMenuId() != 30) {

							//SAC COM res = orderRepository.updateBillStatus(billDetail.getOrderId(), 2);
							tOrderIdList.add(""+billDetail.getOrderId());

						} else { // regular sp cake
							// SAC COM regularSpCkOrderRepository.updateRegSpCakeBillStatus(billDetail.getOrderId(), 2);
							tRegSpCakeOrderIdList.add(""+billDetail.getOrderId());
						}*/

					} else if (billDetail.getCatId() == 5) { // special cake
						// SAC COM res = spCakeOrdersRepository.updateSpBillStatus(billDetail.getOrderId(), 2);
						tSpCakeOrdIdList.add(""+billDetail.getOrderId());
					}

					//System.out.println("Result set for" + billDetail.getOrderId() + " status " + res);

				}
				// postBillDetail.get(i).setBillNo(billNo);
				// postBillDetailRepository.save(postBillDetail);

				querySb.append("('" + billDetail.getBillDetailNo() + "', '" + billNo + "','" + billDetail.getOrderId()
						+ "'," + "'" + billDetail.getMenuId() + "', '" + billDetail.getCatId() + "','"
						+ billDetail.getItemId() + "'," + "'" + billDetail.getOrderQty() + "', '"
						+ billDetail.getBillQty() + "','" + billDetail.getMrp() + "'," + "'" + billDetail.getRateType()
						+ "','" + billDetail.getRate() + "','" + billDetail.getBaseRate() + "','"
						+ billDetail.getTaxableAmt() + "'," + "'" + billDetail.getSgstPer() + "','"
						+ billDetail.getSgstRs() + "','" + billDetail.getCgstPer() + "'," + "'" + billDetail.getCgstRs()
						+ "','" + billDetail.getIgstPer() + "','" + billDetail.getIgstRs() + "'," + "'"
						+ billDetail.getTotalTax() + "','" + billDetail.getGrandTotal() + "','" + billDetail.getRemark()
						+ "'," + "'" + billDetail.getDelStatus() + "','" + billDetail.getGrnType() + "','"
						+ convertToSqlDate(billDetail.getExpiryDate()) + "'," + "'" + billDetail.getIsGrngvnApplied()
						+ "','" + billDetail.getDiscPer() + "'," + "'" + billDetail.getCessPer() + "','"
						+ billDetail.getCessRs() + "'," + "'" + billDetail.getHsnCode() + "'),");
			}//End of bill detail for loop.
			pbHeaderList.add(postBillHeaders);

			String finalInsertQuery = querySb.toString().substring(0, querySb.toString().length() - 1);
			//System.err.println("getCurDateTimeYmD " + getCurDateTimeYmD());
			//System.err.println("querySb " + querySb);
			jdbcTemp.batchUpdate(finalInsertQuery);
			//System.err.println("getCurDateTimeYmD " + getCurDateTimeYmD());
		
			String commaSepTOrder = tOrderIdList.stream()
                    .collect(Collectors.joining(","));
			
			String commaSepTRegSpOrder = tRegSpCakeOrderIdList.stream()
                    .collect(Collectors.joining(","));
			
			String commaSepTSPCakeOrder = tSpCakeOrdIdList.stream()
                    .collect(Collectors.joining(","));
			
			
			String tOrdUpdateStr="UPDATE t_order set is_bill_generated=2 where order_id IN ("+commaSepTOrder+")";
			String tRegSpOrdUpdateStr="UPDATE t_regular_sp_cake set is_bill_generated=2 where rsp_id IN ("+commaSepTRegSpOrder+")";
			String tSpCakeOrdUpdateStr="UPDATE t_sp_cake set is_bill_generated=2 where sp_order_no IN ("+commaSepTSPCakeOrder+")";
			if(!tOrderIdList.isEmpty()) {
			try {
			jdbcTemp.batchUpdate(tOrdUpdateStr);
			}catch (Exception e) {
				e.printStackTrace();
			}
			}
			if(!tRegSpCakeOrderIdList.isEmpty()) {
			try {
			jdbcTemp.batchUpdate(tRegSpOrdUpdateStr);
			}catch (Exception e) {
				e.printStackTrace();
			}
			}
			
			if(!tSpCakeOrdIdList.isEmpty()) {
			try {
			jdbcTemp.batchUpdate(tSpCakeOrdUpdateStr);
			}catch (Exception e) {
				e.printStackTrace();
			}
			}
		}

		return pbHeaderList;
	}

	public static java.util.Date convertToSqlDate(Date dmyDate) {
		//System.err.println("Input date " + dmyDate);
		java.util.Date convertedDate = null;
		SimpleDateFormat ymdSDF = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat dmySDF = new SimpleDateFormat("dd-MM-yyyy");

		// Date dmyDate = dmySDF.parse(date);

		//System.out.println("converted util date commons " + dmyDate);

		convertedDate = new java.sql.Date(dmyDate.getTime());
		//System.out.println("converted sql date commons " + convertedDate);

		return convertedDate;

	}

	public static String getCurDateTimeYmD() {

		int interval = 0;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar curCal = Calendar.getInstance();

		// System.out.println("Time " + String.valueOf(df.format(curCal.getTime())));

		curCal.add(Calendar.MINUTE, interval);
		return String.valueOf(df.format(curCal.getTime()));
		// return curCal;

	}

	@Override
	public List<PostBillHeader> updateBillHeader(List<PostBillHeader> postBillHeader) {

		List<PostBillHeader> postBillHeaders = new ArrayList<PostBillHeader>();
		for (int i = 0; i < postBillHeader.size(); i++) {

			postBillHeaders = postBillHeaderRepository.save(postBillHeader);

			int billNo = postBillHeader.get(i).getBillNo();

			List<PostBillDetail> postBillDetailList = postBillHeader.get(i).getPostBillDetailsList();

			for (int j = 0; j < postBillDetailList.size(); j++) {

				PostBillDetail billDetail = postBillDetailList.get(j);

				billDetail.setBillNo(billNo);

				postBillDetailRepository.save(billDetail);

				if (billDetail.getCatId() != 5) {

					if (billDetail.getMenuId() != 42 && billDetail.getMenuId() != 80 && billDetail.getMenuId() != 30) {// item

						orderRepository.updateBillStatus(billDetail.getOrderId(), 2);

					} else { // regular sp cake
						regularSpCkOrderRepository.updateRegSpCakeBillStatus(billDetail.getOrderId(), 2);
					}

				}
			}
			// postBillDetail.get(i).setBillNo(billNo);
			// postBillDetailRepository.save(postBillDetail);

		}

		return postBillHeaders;

	}

}

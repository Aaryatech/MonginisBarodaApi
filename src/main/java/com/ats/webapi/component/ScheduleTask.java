package com.ats.webapi.component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ats.webapi.commons.ApiConstants;
import com.ats.webapi.commons.Common;
import com.ats.webapi.commons.EmailUtility;
import com.ats.webapi.commons.Firebase;
import com.ats.webapi.controller.UserUtilApi;
import com.ats.webapi.model.Franchisee;
import com.ats.webapi.model.GetMenuShow;
import com.ats.webapi.model.GetTotalAmt;
import com.ats.webapi.model.Info;
import com.ats.webapi.model.SellBillHeader;
import com.ats.webapi.model.Setting;
import com.ats.webapi.model.ShopAnivData;
import com.ats.webapi.model.bill.Company;
import com.ats.webapi.model.otheritemstock.OtherSpItemDtl;
import com.ats.webapi.model.pettycash.GetCashAdvAndExpAmt;
import com.ats.webapi.model.pettycash.PettyCashManagmt;
import com.ats.webapi.model.posdashboard.BillHeaderDashCount;
import com.ats.webapi.model.posdashboard.BillTransactionDetailDashCount;
import com.ats.webapi.model.posdashboard.CreaditAmtDash;
import com.ats.webapi.model.posdashboard.DashAdvanceOrderCounts;
import com.ats.webapi.model.posdashboard.PosDashCounts;
import com.ats.webapi.model.posdashboard.SellBillHeaderDashCounts;
import com.ats.webapi.repo.GetTotalAmtRepo;
import com.ats.webapi.repo.OtherBillDetailAdvRepo;
import com.ats.webapi.repo.PettyCashManagmtRepo;
import com.ats.webapi.repo.SellBillDetailAdvRepo;
import com.ats.webapi.repo.SpCakeAdvRepo;
import com.ats.webapi.repo.posdashboard.BillHeaderDashCountRepo;
import com.ats.webapi.repo.posdashboard.BillTransactionDetailDashCountRepo;
import com.ats.webapi.repo.posdashboard.CreaditAmtDashRepo;
import com.ats.webapi.repo.posdashboard.DashAdvanceOrderCountsRepo;
import com.ats.webapi.repo.posdashboard.SellBillHeaderDashCountsRepo;
import com.ats.webapi.repository.CompanyRepository;
import com.ats.webapi.repository.FrAniversaryRepository;
import com.ats.webapi.repository.FranchiseSupRepository;
import com.ats.webapi.repository.FranchiseeRepository;
import com.ats.webapi.repository.GetCashAdvAndExpAmtRepo;
import com.ats.webapi.repository.GetMenuShowRepo;
import com.ats.webapi.repository.SellBillHeaderRepository;
import com.ats.webapi.repository.SettingRepository;
import com.ats.webapi.repository.othitmstock.OtherSpItemDtlRepo;

import ch.qos.logback.classic.pattern.DateConverter;

@Component
public class ScheduleTask {

	@Autowired
	FranchiseSupRepository franchiseSupRepository;

	@Autowired
	FrAniversaryRepository frAniversaryRepository;
	
	@Autowired
	OtherSpItemDtlRepo spItemRepo;
	
	@Autowired
	SettingRepository settingRepository;
	
	@Autowired
	GetMenuShowRepo getMenuShowRepo;
	
	
	@Autowired
	FranchiseeRepository franRepo;
	
	@Autowired
	CompanyRepository companyRepo;

	private static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Scheduled(cron = "0 0 7 * * *")
	public void scheduleTaskWithCronExpression() {
		logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

		List<String> frTokens = franchiseSupRepository.findTokensByBirthdate(new Date());
		logger.info("frTokens" + frTokens);
		List<ShopAnivData> frOPTokens = frAniversaryRepository.findTokensByFrOpeningDate(new Date());
		logger.info("frOPTokens" + frOPTokens);
		// -----------------------For Notification-----------------

		if (!frTokens.isEmpty()) {

			try {
				for (String token : frTokens) {
					Firebase.sendPushNotifForCommunication(token, "HAPPY BIRTHDAY",
							"Team Monginis wishes you a very happy birthday and many many happy returns of the day.",
							"inbox");
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		if (!frOPTokens.isEmpty()) {

			try {
				for (ShopAnivData token : frOPTokens) {
					Firebase.sendPushNotifForCommunication(token.getToken(), "Shop Anniversary",
							"Congratulations on successful completion of " + token.getNoOfYears()
									+ "years with Monginis. Thank you for being part of our family. Team Monginis",
							"inbox");
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		// -----------------------------------------------------
	}

	@Scheduled(cron = "0 0 6 1 * ?")
	public void scheduleTaskWithCronExpressionOnMonthStart() {
		logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

		List<String> frTokens = franchiseSupRepository.findTokens();

		if (!frTokens.isEmpty()) {

			try {
				for (String token : frTokens) {
					Firebase.sendPushNotifForCommunication(token, "Close Your Month",
							"Since today is first day of the month, please close the last month in your software.",
							"inbox");
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

	}

	// ------------PETTY CASH------------------------

	@Autowired
	FranchiseeRepository franchiseeRepository;

	@Autowired
	GetTotalAmtRepo getTotalAmtRepo;

	@Autowired
	GetCashAdvAndExpAmtRepo getCashAdvAndExpAmtRepo;

	@Autowired
	PettyCashManagmtRepo pettyRepo;

	@Autowired
	SpCakeAdvRepo spRepo;

	@Autowired
	SellBillDetailAdvRepo sellBillRepo;

	@Autowired
	OtherBillDetailAdvRepo otherBillRepo;

	@Autowired
	SellBillHeaderRepository sellBillHeaderRepository;

	// Petty Cash Day End Process every morning 6.00 am

	@Scheduled(cron = "0 0 7 * * *")
	//@Scheduled(cron = "2 * * * * *")
	public void crownForPettyCashDayEnd() {

		List<Franchisee> franchisee = new ArrayList<Franchisee>();
		franchisee = franchiseeRepository.findAllByDelStatusOrderByFrNameAsc(0);

		if (franchisee != null) {

			for (int j = 0; j < franchisee.size(); j++) {

				Franchisee fr = franchisee.get(j);
				System.err.println("FRA ------------------" + fr);

				int empId = 0;
				try {
					SellBillHeader res = sellBillHeaderRepository.getLastBillHeaderByFrId(fr.getFrId());
					if (res != null) {
						empId = res.getExtInt1();
					}
				} catch (Exception e) {
					e.printStackTrace();
					empId = 0;
				}

				System.err.println("EMP ID = " + empId + "    FOR - FR =" + fr.getFrId());

				PettyCashManagmt petty = new PettyCashManagmt();
				try {

					petty = pettyRepo.findByFrIdAndStatusLimit1(fr.getFrId(), 0);
					System.err.println("OLD PETTY CASH ENTRY = " + petty);

					Calendar cal = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(cal.getTime());

					if (petty != null) {

						cal = Calendar.getInstance();

						cal.setTime(petty.getDate());

						// Add 1 day
						cal.add(Calendar.DAY_OF_MONTH, 1);
						date = sdf.format(cal.getTime());

					}

					GetCashAdvAndExpAmt data = new GetCashAdvAndExpAmt();
					data = getCashAdvAndExpAmtRepo.getAmt(fr.getFrId(), date);

					GetTotalAmt creditNoteAmt = new GetTotalAmt();
					creditNoteAmt = getTotalAmtRepo.getTotalPOSCreditNoteAmt(fr.getFrId(), date);

					float creditNtAmt = 0;
					if (creditNoteAmt != null) {
						creditNtAmt = creditNoteAmt.getTotalAmt();
					}

					if (petty != null) {
						petty.setTotalAmt(data.getTrCashAmt() + data.getAdvAmt() - data.getExpAmt() - creditNtAmt);
					}

					Calendar cal2 = Calendar.getInstance();

					System.err.println("DATE 1 --------------------- " + sdf.format(cal.getTime()));
					System.err.println("DATE 2 --------------------- " + sdf.format(cal2.getTime()));

					if (cal.compareTo(cal2) <= 0) {

						String d1 = sdf.format(cal.getTime());
						String d2 = sdf.format(cal2.getTime());

						if (!d1.equalsIgnoreCase(d2)) {

							PettyCashManagmt pettycash = new PettyCashManagmt();

							float cashAmt = 0;
							float closAmt = 0;
							float withdrawAmt = 0;
							float opnAmt = 0;
							float cashEdtAmt = 0;
							try {
								if (petty != null) {
									cashAmt = petty.getTotalAmt();
									withdrawAmt = cashAmt;
									opnAmt = petty.getClosingAmt();
									cashEdtAmt = petty.getCashAmt();
									closAmt = opnAmt + cashAmt - withdrawAmt;
								}
							} catch (Exception e) {
								e.printStackTrace();
							}

							SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
							Calendar cal1 = Calendar.getInstance();

							pettycash.setPettycashId(0);
							pettycash.setCardAmt(0);
							pettycash.setCashAmt(cashAmt);
							pettycash.setClosingAmt(closAmt);
							pettycash.setDate(sdf.parse(date));
							pettycash.setExFloat1(0);
							pettycash.setExInt1(empId);
							pettycash.setExVar1("" + sdf1.format(cal1.getTime()));
							pettycash.setExVar2("NA");
							pettycash.setFrId(fr.getFrId());
							pettycash.setOpeningAmt(opnAmt);
							pettycash.setCardEditAmt(0);
							pettycash.setTtlEditAmt(0);
							pettycash.setOtherAmt(0);
							pettycash.setStatus(0);
							pettycash.setTotalAmt(0);
							pettycash.setTtlEditAmt(0);
							pettycash.setWithdrawalAmt(withdrawAmt);
							pettycash.setOpnEditAmt(0);
							pettycash.setCashEditAmt(cashEdtAmt);
							pettycash.setExFloat1(0);

							System.err.println("SAVE PETTY CASH = " + pettycash);

							PettyCashManagmt cash = new PettyCashManagmt();
							try {
								cash = pettyRepo.save(pettycash);
								if (cash != null) {

									String senderEmail = UserUtilApi.senderEmail;
									String senderPassword = UserUtilApi.senderPassword;

									// Franchisee frDetails = franchiseeRepository.findOne(fr.getFrId());

									String fromDate = sdf.format(cal.getTime());
									String toDate = sdf.format(cal.getTime());

									PosDashCounts posDetails = getPosDashData(fromDate, toDate, fr.getFrId(),
											fr.getFrRateCat());
									System.out.println("POS Details----------" + posDetails);

									if (posDetails != null) {

										String msg = "Total summary for (" + fr.getFrCode() + ") at ("
												+ Common.convertToDMY(fromDate) + ")\n" + "E-Pay - ("
												+ posDetails.getEpayAmt() + ")\n" + "Cash - (" + posDetails.getCashAmt()
												+ ")\n" + "Card - (" + posDetails.getCardAmt() + ")\n" + "Sales - ("
												+ posDetails.getSaleAmt() + ")\n" + "Discount - ("
												+ posDetails.getDiscountAmt() + ")\n" + "Purchase  - ("
												+ posDetails.getPurchaseAmt() + ")\n" + "Advance - ("
												+ posDetails.getAdvanceAmt() + ")\n" + "Credit Bill - ("
												+ posDetails.getCreditAmt() + ")\n" + "Expenses - ("
												+ posDetails.getExpenseAmt() + ")";

										String mailSubject = "Total summary for (" + fr.getFrCode() + ") at ("
												+ Common.convertToDMY(fromDate) + ")";
										String defPass = "";

										System.err.println("Send Mail---------" + fr.getFrId() + " " + fr.getFrCode()
												+ " " + fromDate + " - " + toDate);
										Info info = EmailUtility.sendEmail(senderEmail, senderPassword, fr.getFrEmail(),
												mailSubject, msg, defPass);

										if (info.isError() == false) {
											EmailUtility.send(fr.getFrMob(), msg);
										}

									}
								}

							} catch (Exception e) {
								System.err.println("Exception in addPettyCash : " + e.getMessage());
								e.printStackTrace();
							}

						}

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}

	}

	/*****************************************************************/
	@Autowired
	SellBillHeaderDashCountsRepo sellBillHeaderDashCountsRepo;

	@Autowired
	BillTransactionDetailDashCountRepo billTransactionDetailDashCountRepo;

	@Autowired
	BillHeaderDashCountRepo billHeaderDashCountRepo;

	@Autowired
	CreaditAmtDashRepo creaditAmtDashRepo;

	@Autowired
	DashAdvanceOrderCountsRepo dashAdvanceOrderCountsRepo;

	public PosDashCounts getPosDashData(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("frId") int frId,
			@RequestParam("frRateCat") int frRateCat) {

		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Calcutta"));
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = df.format(date);
		PosDashCounts crnReport = new PosDashCounts();

		SellBillHeaderDashCounts headcount = new SellBillHeaderDashCounts();
		BillTransactionDetailDashCount tranCount = new BillTransactionDetailDashCount();
		BillHeaderDashCount billCountch = new BillHeaderDashCount();
		BillHeaderDashCount billCountpur = new BillHeaderDashCount();
		CreaditAmtDash daseqe = new CreaditAmtDash();

		List<DashAdvanceOrderCounts> dailyList = new ArrayList<DashAdvanceOrderCounts>();
		List<DashAdvanceOrderCounts> advOrderList = new ArrayList<DashAdvanceOrderCounts>();

		System.err.println("PARAM ------ " + fromDate + "       " + toDate + "         " + frId);

		System.err.println("DashBoardReporApi data is " + fromDate + toDate + frId);
		try {

			try {
				headcount = sellBillHeaderDashCountsRepo.getDataFordash(fromDate, toDate, frId);
			} catch (Exception e) {
				e.getMessage();
			}

			try {
				tranCount = billTransactionDetailDashCountRepo.getD1ataFordash(fromDate, toDate, frId);
			} catch (Exception e) {
				e.getMessage();
			}

			try {
				billCountch = billHeaderDashCountRepo.getD1ataFordash2Ch(fromDate, toDate, frId);
			} catch (Exception e) {
				e.getMessage();
			}

			try {
				billCountpur = billHeaderDashCountRepo.getD1ataFordash2pur(fromDate, toDate, frId);
			} catch (Exception e) {
				e.getMessage();
			}

			try {
				daseqe = creaditAmtDashRepo.getDataFordash(fromDate, toDate, frId);
			} catch (Exception e) {
				e.getMessage();
			}

			try {
				dailyList = dashAdvanceOrderCountsRepo.getAdvDetail(currentDate, frId, 2);
			} catch (Exception e) {
				e.getMessage();
			}

			try {
				advOrderList = dashAdvanceOrderCountsRepo.getAdvDetail(currentDate, frId, 1);
			} catch (Exception e) {
				e.getMessage();
			}

			System.err.println("DashBoardReporApi ***" + daseqe.toString());
			crnReport.setDailyMartList(dailyList);
			crnReport.setAdvOrderList(advOrderList);

			System.err.println("PURCHASE ====================== " + billCountpur);

			GetTotalAmt getAdvAmt = getTotalAmtRepo.getTotalAmount(frId, fromDate, toDate);
			float advAmt = 0;
			if (getAdvAmt != null) {
				advAmt = getAdvAmt.getTotalAmt();
			}

			GetTotalAmt getProfitAmt = getTotalAmtRepo.getTotalProfit(frId, fromDate, toDate);
			float profitAmt = 0;
			if (getProfitAmt != null) {
				profitAmt = getProfitAmt.getTotalAmt();
			}

			crnReport.setProfitAmt((int) profitAmt);

			// System.err.println( "DashBoardReporApi /tranCount" + tranCount.toString());
			// System.err.println( "DashBoardReporApi /billCountch" +
			// billCountch.toString());
			// System.err.println( "DashBoardReporApi /billCountpur" +
			// billCountpur.toString());

			// crnReport.setAdvanceAmt(headcount.getAdvanceAmt());
			crnReport.setAdvanceAmt(advAmt);

			if (tranCount.getCardAmt() == "" || tranCount.getCardAmt() == null) {
				crnReport.setCardAmt(0);
			} else {
				crnReport.setCardAmt(Float.parseFloat(tranCount.getCardAmt()));
			}
			if (tranCount.getCashAmt() == "" || tranCount.getCashAmt() == null) {
				crnReport.setCashAmt(0);
			} else {
				crnReport.setCashAmt(Float.parseFloat(tranCount.getCashAmt()));
			}

			if (tranCount.getePayAmt() == "" || tranCount.getePayAmt() == null) {
				crnReport.setEpayAmt(0);
			} else {
				crnReport.setEpayAmt(Float.parseFloat(tranCount.getePayAmt()));
			}

			if (daseqe.getCreditAmt() == "" || daseqe.getCreditAmt() == null) {
				crnReport.setCreditAmt(0);
			} else {
				// crnReport.setEpayAmt(Float.parseFloat(tranCount.getePayAmt()));
				crnReport.setCreditAmt(Float.parseFloat(daseqe.getCreditAmt()));
			}

			crnReport.setDiscountAmt(headcount.getDiscAmt());

			crnReport.setNoOfBillGenerated(headcount.getNoBillGen());
			crnReport.setSaleAmt(headcount.getSellAmt());

			// crnReport.setProfitAmt(headcount.getProfitAmt());

			try {
				crnReport.setPurchaseAmt(Float.parseFloat(billCountpur.getPurchaeAmt()));
			} catch (Exception e) {
				crnReport.setPurchaseAmt(0);
			}
			/*
			 * if (billCountpur.getPurchaeAmt() == "" || billCountpur.getPurchaeAmt() ==
			 * null || billCountpur.getPurchaeAmt() == "0") { crnReport.setPurchaseAmt(0); }
			 * else {
			 * crnReport.setPurchaseAmt(Float.parseFloat(billCountpur.getPurchaeAmt())); }
			 */

			if (billCountch.getChAmt() == "" || billCountch.getChAmt() == null || billCountch.getChAmt() == "0") {
				crnReport.setExpenseAmt(0);
			} else {
				crnReport.setExpenseAmt(Float.parseFloat(billCountch.getChAmt()));
			}

			System.err.println("DashBoardReporApi /getCredNoteReport" + crnReport.toString());

		} catch (Exception e) {

			System.err.println("Exception in DashBoardReporApi /getCredNoteReport" + e.getMessage());

			e.printStackTrace();
		}

		return crnReport;
	}

		
	@Scheduled(cron = "0 0 10 * * ?")
	public void scheduleSendMailWithOtherSpItemDtm() {

		try {
			Setting setting = settingRepository.findBySettingKey("run_other_item_cron");

			if (setting.getSettingValue() == 1) {

				List<Franchisee> franchisee = new ArrayList<Franchisee>();
				franchisee = franchiseeRepository.findAllByDelStatusOrderByFrNameAsc(0);
				String orderDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

				List<Integer> frIds = new ArrayList<Integer>();
				for (int i = 0; i < franchisee.size(); i++) {
					frIds.add(franchisee.get(i).getFrId());
				}

				System.out.println("FrId List - : " +orderDate+"-"+frIds);

				//List<GetMenuShow> menuList = getMenuShowRepo.getMenuListData();

				String senderEmail = "atsinfosoft@gmail.com";
				String senderPassword = "atsinfosoft#123";
				String mailSubject = null;
				String userName = null;
				String frEmail = null;
				String emailContent = null;
				int srno = 0;

				if (frIds != null) {
					for (int i = 0; i < frIds.size(); i++) {
						
						List<OtherSpItemDtl> spItmList = spItemRepo.getOtherSpItemDtls(orderDate, frIds.get(i));
						// System.err.println("OtherSpItemDtl================"+spItmList);

						int flag = 0;

						emailContent = "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "<style>\n" + "#customers {\n"
								+ "	font-family: Arial, Helvetica, sans-serif;\n" + "	border-collapse: collapse;\n"
								+ "	width: 80%;\n" + "}\n" + "\n" + "h5 {\n"
								+ "	font-family: Arial, Helvetica, sans-serif;\n" + "	border-collapse: collapse;\n"
								+ "	width: 78%;\n" + "	border: 1px solid #ddd;\n" + "	padding: 8px;\n"
								+ "	background-color: #ec268f;\n" + "	color: white;\n" + "	text-align: center;\n"
								+ "	font-size: 17px;\n" + "}\n" + "\n" + "#customers td, #customers th {\n"
								+ "	border: 1px solid #ddd;\n" + "	padding: 8px;\n" + "}\n" + "\n"
								+ "#customers tr:nth-child(even) {\n" + "	background-color: #f2f2f2;\n" + "}\n" + "\n"
								+ "#customers tr:hover {\n" + "	background-color: #ddd;\n" + "}\n" + "\n"
								+ "#customers th {\n" + "	padding-top: 12px;\n" + "	padding-bottom: 12px;\n"
								+ "	text-align: left;\n" + "	background-color: #ec268f;\n" + "	color: white;\n"
								+ "}\n" + "\n" + ".container {\n" + "	margin-left: 100px;\n" + "	width: 100%;\n"
								+ "}\n" + "</style>\n" + "</head>\n" + "<body>\n" + "	<div class=\"container\">";
						emailContent += "<table id=\"customers\">\n" + "			<thead>\n" + "				<tr>\n"
								+ "					<th>Sr No.</th>\n" + "					<th>Item Name</th>\n"
								+ "					<th>Flavour</th>\n" + "					<th>Qty.</th>\n"
								+ "					<th>Amount</th>\n" + "				</tr>\n"
								+ "			</thead>\n" + "			<tbody>";

						if (spItmList != null) {
							for (int a = 0; a < spItmList.size(); a++) {

								mailSubject = "Other Special Item Order on : "+ Common.convertToDMY(orderDate);
								frEmail = spItmList.get(a).getFrEmail();
								flag = 1;
								srno = srno + 1;

								emailContent += "<tr>\n" + "	<td>" + srno + "</td>\n" + "	<td>"
										+ spItmList.get(a).getItemName() + "</td>\n" + "	<td>"
										+ spItmList.get(a).getSpfName() + "</td>\n" + "	<td>"
										+ spItmList.get(a).getOrderQty() + "</td>\n" + "	<td>"
										+ spItmList.get(a).getOrderAmt() + "</td>\n" + "	</tr>";

							}
						} else {
							flag = 0;
						}
						emailContent += "</tbody>\n" + "	</table>\n" + "	</div>\n" + "	</body>\n" + "	</html>";

						if (flag == 1) {
							System.err.println("fr : " + frIds.get(i) + " -- " + frEmail);
							Info mailInfo = EmailUtility.sendEmailHtmlContent(senderEmail, senderPassword, frEmail,
									mailSubject, userName, emailContent);
						}

					} // Franchise End
				}

			}
		} catch (Exception e) {
			System.err.println("Exception : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	//Akhilesg Cron Job For FDA Lic Exp FR Email
	
/*	@Scheduled(cron = "0/20 * * * * ?")*/
	public void scheduleSendMailToFrExpFdaLic() {
System.err.println("In FDA Cron");
		try {
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			 Calendar c = Calendar.getInstance();
		     c.setTime(date);
		     c.add(Calendar.DAY_OF_MONTH, 30);
		     Date currentDatePlusOne = c.getTime();
			System.err.println("Date-->"+sdf.format(currentDatePlusOne)+"\t"+sdf.format(date));
				List<Franchisee> resp=new ArrayList<>();
			
				resp=franRepo.getExpFdaLicenceDate(sdf.format(date),sdf.format(currentDatePlusOne));
			
				Company companyInfo =companyRepo.findOne(1);
			
			
			
			

				//List<GetMenuShow> menuList = getMenuShowRepo.getMenuListData();

				String senderEmail = "atsinfosoft@gmail.com";
				String senderPassword = "atsinfosoft#123";
				String mailSubject = null;
				String userName = null;
				String frEmail = null;
				String emailContent = null;
				int srno = 0;

				
				if(resp.size()>0) {
					
					
					
					
for (int i = 0; i < resp.size(); i++) {
						

						// System.err.println("OtherSpItemDtl================"+spItmList);

						int flag = 0;

						emailContent="<!doctype html>\n" + 
								"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + 
								
								"<head>\n" + 
								"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" + 
								"<title>:: Monginis ::</title>\n" + 
								"</head>\n" + 
								"<body leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\"\n" + 
								"	yahoo=\"fix\"\n" + 
								"	style=\"font-family: Arial, sans-serif; background: #e3ebef;\">\n" + 
								"	\n" + 
								"		<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" + 
								"		align=\"center\" style=\"margin-top: 10px; margin-bottom: 10px;\">\n" + 
								"		<tr>\n" + 
								"			\n" + 
								"				<td width=\"100%\" valign=\"top\" bgcolor=\"#e3ebef\">\n" + 
								"				\n" + 
								"					<table width=\"700\" id=\"tborder\" class=\"deviceWidth\"\n" + 
								"					bgcolor=\"#f5f5f5\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\"\n" + 
								"					style=\"position: relative; border: 2px solid #d8e0e4;\">\n" + 
								"					\n" + 
								"					<tr>\n" + 
								"					\n" + 
								"						<td cellspacing=\"0\" cellpadding=\"0\" style=\"padding: 0;\">\n" + 
								"							\n" + 
								"							<table\n" + 
								"								width=\"100%\" id=\"\" class=\"\" cellspacing=\"0\" cellpadding=\"0\"\n" + 
								"								align=\"center\" background=\"#000\" border=\"0\" style=\"padding: 0;\">\n" + 
								"								\n" + 
								"								<tr>\n" + 
								"									<td align=\"center\" style=\"background: #FFF;\"><img\n" + 
								"										src=\"http://107.180.72.86:8080/uploads/baroda/MSPCAKE/logo.png\" alt=\"logo\"\n" + 
								"										style=\"border: none; max-width: 100%; padding: 15px 0; float: none;\">\n" + 
								"									</td>\n" + 
								"								</tr>\n" + 
								"								<tr>\n" + 
								"									<td style=\"background: #FFF;\"><img\n" + 
								"										src=\"http://107.180.72.86:8080/uploads/baroda/MSPCAKE/seprator.jpg\" alt=\"seprator\"\n" + 
								"										style=\"border: none; max-width: 100%; float: left; padding: 0 0 28px 0;\"></td>\n" + 
								"								</tr>\n" + 
								"								<tr>\n" + 
								"									<td cellspacing=\"0\" cellpadding=\"0\"\n" + 
								"										style=\"position: relative; padding: 0 40px 10px 40px; background: #FFF;\"\n" + 
								"										border=\"0\">\n" + 
								"										\n" + 
								"										\n" + 
								"										<table width=\"100%\" border=\"0\" cellspacing=\"0\"\n" + 
								"											cellpadding=\"0\" align=\"center\">\n" + 
								"\n" + 
								"											<tr>\n" + 
								"												<td\n" + 
								"													style=\"text-align: center; font-size: 14px; text-transform: uppercase; padding: 0 0 5px 0; color: #ec268f;\"><strong>DEAR "+resp.get(i).getFrOwner()+"</strong></td>\n" + 
								"											</tr>\n" + 
								"											<tr>\n" + 
								"												<td\n" + 
								"													style=\"text-align: center; padding: 0 0 0 0; font-size: 14px; line-height: 22px; color: #333333;\">Your Following Document Is Expire Soon Please Renew It,If Already Renewed Please Update In Edit Profile Or Contact Admin</td>\n" + 
								"											</tr>\n" + 
								"											<tr>\n" + 
								"												\n" + 
								"													\n" + 
								"													\n" + 
								"													<td style=\"text-align: center;\">\n" + 
								"																<table width=\"100%\" border=\"0\" cellspacing=\"0\"\n" + 
								"																	cellpadding=\"0\" style=\"border: 1px solid #CCC;\">\n" + 
								"																	<tr\n" + 
								"																		style=\"background: #ec268f; color: #FFF; padding: 6px; font-size: 14px;\">\n" + 
								"																		<th style=\"padding: 8px;\">Sr. No.</th>\n" + 
								"																		<th style=\"padding: 8px;\">Franchisee Name</th>\n" + 
								"																		<th style=\"padding: 8px;\">Document Name</th>\n" + 
								"																		<th style=\"padding: 8px;\">Expiry Date</th>\n" + 
								"																		\n" + 
								"																	</tr>\n" + 
								"																	 <tr style=\"font-size: 14px; background: #f5f5f5;\">\n" + 
								"																		<td style=\"padding: 8px; text-align: center;\">"+i+1+"</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">"+resp.get(i).getFrName()+"\n" + 
								"																			</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">FDA Licenses</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">"+resp.get(i).getFbaLicenseDate()+"\n" + 
								"																		</td>\n" + 
								"																		\n" + 
								"																	</tr>\n" + 
								"																	<!-- <tr style=\"font-size: 14px; background: #FFFFFF;\">\n" + 
								"																		<td style=\"padding: 8px; text-align: center;\">01</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">Akhilesh\n" + 
								"																			Dani</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">akhilesh@gmail.com</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">Ashoka\n" + 
								"																			Marg, Nashik</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: center;\"><a\n" + 
								"																			href=\"#\"\n" + 
								"																			style=\"text-decoration: none; color: #3a3c8b;\">Edit</a></td>\n" + 
								"																	</tr>\n" + 
								"																	<tr style=\"font-size: 14px; background: #f5f5f5;\">\n" + 
								"																		<td style=\"padding: 8px; text-align: center;\">01</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">Akhilesh\n" + 
								"																			Dani</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">akhilesh@gmail.com</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">Ashoka\n" + 
								"																			Marg, Nashik</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: center;\"><a\n" + 
								"																			href=\"#\"\n" + 
								"																			style=\"text-decoration: none; color: #3a3c8b;\">Edit</a></td>\n" + 
								"																	</tr> \n" + 
								"																	<tr style=\"font-size: 14px; background: #FFFFFF;\">\n" + 
								"																		<td style=\"padding: 8px; text-align: center;\">01</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">Akhilesh\n" + 
								"																			Dani</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">akhilesh@gmail.com</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">Ashoka\n" + 
								"																			Marg, Nashik</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: center;\"><a\n" + 
								"																			href=\"#\"\n" + 
								"																			style=\"text-decoration: none; color: #3a3c8b;\">Edit</a></td>\n" + 
								"																	</tr>\n" + 
								"																	<tr style=\"font-size: 14px; background: #f5f5f5;\">\n" + 
								"																		<td style=\"padding: 8px; text-align: center;\">01</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">Akhilesh\n" + 
								"																			Dani</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">akhilesh@gmail.com</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">Ashoka\n" + 
								"																			Marg, Nashik</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: center;\"><a\n" + 
								"																			href=\"#\"\n" + 
								"																			style=\"text-decoration: none; color: #3a3c8b;\">Edit</a></td>\n" + 
								"																	</tr> -->\n" + 
								"																</table>\n" + 
								"															</td>\n" + 
								"													\n" + 
								"													\n" + 
								"													\n" + 
								"											</tr>\n" + 
								"\n" + 
								"										</table></td>\n" + 
								"								</tr>\n" + 
								"\n" + 
								"								<tr>\n" + 
								"									<td cellspacing=\"0\" cellpadding=\"0\" style=\"position: relative;\"\n" + 
								"										border=\"0\"><table width=\"100%\" border=\"0\" cellspacing=\"0\"\n" + 
								"											cellpadding=\"0\">\n" + 
								"											<tr>\n" + 
								"												<td\n" + 
								"													style=\"line-height: 24px; padding: 10px 40px 40px 40px; color: #262626; text-align: center; font-size: 14px; background: #FFF;\">\n" + 
								"													\n" + 
								"												<b>Thank You For Being Part Of Monginis Family!!!</b>	\n" + 
								"													</td>\n" + 
								"											</tr>\n" + 
								"											<tr>\n" + 
								"												<td\n" + 
								"													style=\"background: #edf2f6; font-size: 12px; text-align: center; color: #66696c; padding: 10px 0; line-height: 20px;\">\n" + 
								"													<!-- isn't that you? <a href=\"#\"\n" + 
								"													style=\"text-decoration: underline; color: #33358f;\">Unsubscibe\n" + 
								"														from this email</a> --> <br>"+companyInfo.getCompName()+"<br>"+companyInfo.getFactAddress()+companyInfo.getPhoneNo1()+"\n" + 
								"												</td>\n" + 
								"											</tr>\n" + 
								"								\n" + 
								"							</table>\n" + 
								"						\n" + 
								"						\n" + 
								"						</td>\n" + 
								"					\n" + 
								"					</tr>\n" + 
								"					\n" + 
								"					\n" + 
								"					</table>\n" + 
								"				\n" + 
								"			\n" + 
								"			\n" + 
								"				</td>\n" + 
								"		\n" + 
								"		</tr>\n" + 
								"		\n" + 
								"		\n" + 
								"		\n" + 
								"		</table>\n" + 
								"\n" + 
								"</body>\n" + 
								"</html>";

						
						
						frEmail=resp.get(i).getFrEmail();
						mailSubject = "FDA  Expieres : ";
						
						flag = 1;
						srno = srno + 1;
					
							System.err.println("fr  Email: "+ frEmail);
							Info mailInfo = EmailUtility.sendEmailHtmlContent(senderEmail, senderPassword, frEmail,
									mailSubject, userName, emailContent);
						

					// Franchise End
	

			}
					
					
					
					
				}
				
				
				
				
						
		} catch (Exception e) {
			System.err.println("Exception : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	
	/*@Scheduled(cron = "0/20 * * * * ?")*/
	public void scheduleSendMailToFrExpAgree() {
System.err.println("In Agreement Cron");
		try {
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			 Calendar c = Calendar.getInstance();
		     c.setTime(date);
		     c.add(Calendar.DAY_OF_MONTH, 30);
		     Date currentDatePlusOne = c.getTime();
			System.err.println("Date-->"+sdf.format(currentDatePlusOne)+"\t"+sdf.format(date));
				List<Franchisee> resp=new ArrayList<>();
			
				resp=franRepo.getExpAgreementDate(sdf.format(date),sdf.format(currentDatePlusOne));
			
			System.err.println("resp-->"+resp.toString());
			
			Company companyInfo =companyRepo.findOne(1);
			
			

				//List<GetMenuShow> menuList = getMenuShowRepo.getMenuListData();

				String senderEmail = "atsinfosoft@gmail.com";
				String senderPassword = "atsinfosoft#123";
				String mailSubject = null;
				String userName = null;
				String frEmail = null;
				String emailContent = null;
				int srno = 0;

				
					for (int i = 0; i < resp.size(); i++) {
						
						
						// System.err.println("OtherSpItemDtl================"+spItmList);

						int flag = 0;

						emailContent="<!doctype html>\n" + 
								"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + 
								
								"<head>\n" + 
								"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" + 
								"<title>:: Monginis ::</title>\n" + 
								"</head>\n" + 
								"<body leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\"\n" + 
								"	yahoo=\"fix\"\n" + 
								"	style=\"font-family: Arial, sans-serif; background: #e3ebef;\">\n" + 
								"	\n" + 
								"		<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" + 
								"		align=\"center\" style=\"margin-top: 10px; margin-bottom: 10px;\">\n" + 
								"		<tr>\n" + 
								"			\n" + 
								"				<td width=\"100%\" valign=\"top\" bgcolor=\"#e3ebef\">\n" + 
								"				\n" + 
								"					<table width=\"700\" id=\"tborder\" class=\"deviceWidth\"\n" + 
								"					bgcolor=\"#f5f5f5\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\"\n" + 
								"					style=\"position: relative; border: 2px solid #d8e0e4;\">\n" + 
								"					\n" + 
								"					<tr>\n" + 
								"					\n" + 
								"						<td cellspacing=\"0\" cellpadding=\"0\" style=\"padding: 0;\">\n" + 
								"							\n" + 
								"							<table\n" + 
								"								width=\"100%\" id=\"\" class=\"\" cellspacing=\"0\" cellpadding=\"0\"\n" + 
								"								align=\"center\" background=\"#000\" border=\"0\" style=\"padding: 0;\">\n" + 
								"								\n" + 
								"								<tr>\n" + 
								"									<td align=\"center\" style=\"background: #FFF;\"><img\n" + 
								"										src=\"http://107.180.72.86:8080/uploads/baroda/MSPCAKE/logo.png\" alt=\"logo\"\n" + 
								"										style=\"border: none; max-width: 100%; padding: 15px 0; float: none;\">\n" + 
								"									</td>\n" + 
								"								</tr>\n" + 
								"								<tr>\n" + 
								"									<td style=\"background: #FFF;\"><img\n" + 
								"										src=\"http://107.180.72.86:8080/uploads/baroda/MSPCAKE/seprator.jpg\" alt=\"seprator\"\n" + 
								"										style=\"border: none; max-width: 100%; float: left; padding: 0 0 28px 0;\"></td>\n" + 
								"								</tr>\n" + 
								"								<tr>\n" + 
								"									<td cellspacing=\"0\" cellpadding=\"0\"\n" + 
								"										style=\"position: relative; padding: 0 40px 10px 40px; background: #FFF;\"\n" + 
								"										border=\"0\">\n" + 
								"										\n" + 
								"										\n" + 
								"										<table width=\"100%\" border=\"0\" cellspacing=\"0\"\n" + 
								"											cellpadding=\"0\" align=\"center\">\n" + 
								"\n" + 
								"											<tr>\n" + 
								"												<td\n" + 
								"													style=\"text-align: center; font-size: 14px; text-transform: uppercase; padding: 0 0 5px 0; color: #ec268f;\"><strong>DEAR "+resp.get(i).getFrOwner()+"</strong></td>\n" + 
								"											</tr>\n" + 
								"											<tr>\n" + 
								"												<td\n" + 
								"													style=\"text-align: center; padding: 0 0 0 0; font-size: 14px; line-height: 22px; color: #333333;\">Your Following Document Is Expire Soon Please Renew It,If Already Renewed Please Update In Edit Profile Or Contact Admin</td>\n" + 
								"											</tr>\n" + 
								"											<tr>\n" + 
								"												\n" + 
								"													\n" + 
								"													\n" + 
								"													<td style=\"text-align: center;\">\n" + 
								"																<table width=\"100%\" border=\"0\" cellspacing=\"0\"\n" + 
								"																	cellpadding=\"0\" style=\"border: 1px solid #CCC;\">\n" + 
								"																	<tr\n" + 
								"																		style=\"background: #ec268f; color: #FFF; padding: 6px; font-size: 14px;\">\n" + 
								"																		<th style=\"padding: 8px;\">Sr. No.</th>\n" + 
								"																		<th style=\"padding: 8px;\">Franchisee Name</th>\n" + 
								"																		<th style=\"padding: 8px;\">Document Name</th>\n" + 
								"																		<th style=\"padding: 8px;\">Expiry Date</th>\n" + 
								"																		\n" + 
								"																	</tr>\n" + 
								"																	 <tr style=\"font-size: 14px; background: #f5f5f5;\">\n" + 
								"																		<td style=\"padding: 8px; text-align: center;\">"+i+1+"</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">"+resp.get(i).getFrName()+"\n" + 
								"																			</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">FDA Licenses</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">"+resp.get(i).getFbaLicenseDate()+"\n" + 
								"																		</td>\n" + 
								"																		\n" + 
								"																	</tr>\n" + 
								"																	<!-- <tr style=\"font-size: 14px; background: #FFFFFF;\">\n" + 
								"																		<td style=\"padding: 8px; text-align: center;\">01</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">Akhilesh\n" + 
								"																			Dani</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">akhilesh@gmail.com</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">Ashoka\n" + 
								"																			Marg, Nashik</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: center;\"><a\n" + 
								"																			href=\"#\"\n" + 
								"																			style=\"text-decoration: none; color: #3a3c8b;\">Edit</a></td>\n" + 
								"																	</tr>\n" + 
								"																	<tr style=\"font-size: 14px; background: #f5f5f5;\">\n" + 
								"																		<td style=\"padding: 8px; text-align: center;\">01</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">Akhilesh\n" + 
								"																			Dani</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">akhilesh@gmail.com</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">Ashoka\n" + 
								"																			Marg, Nashik</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: center;\"><a\n" + 
								"																			href=\"#\"\n" + 
								"																			style=\"text-decoration: none; color: #3a3c8b;\">Edit</a></td>\n" + 
								"																	</tr> \n" + 
								"																	<tr style=\"font-size: 14px; background: #FFFFFF;\">\n" + 
								"																		<td style=\"padding: 8px; text-align: center;\">01</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">Akhilesh\n" + 
								"																			Dani</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">akhilesh@gmail.com</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">Ashoka\n" + 
								"																			Marg, Nashik</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: center;\"><a\n" + 
								"																			href=\"#\"\n" + 
								"																			style=\"text-decoration: none; color: #3a3c8b;\">Edit</a></td>\n" + 
								"																	</tr>\n" + 
								"																	<tr style=\"font-size: 14px; background: #f5f5f5;\">\n" + 
								"																		<td style=\"padding: 8px; text-align: center;\">01</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">Akhilesh\n" + 
								"																			Dani</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">akhilesh@gmail.com</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: left;\">Ashoka\n" + 
								"																			Marg, Nashik</td>\n" + 
								"																		<td style=\"padding: 8px; text-align: center;\"><a\n" + 
								"																			href=\"#\"\n" + 
								"																			style=\"text-decoration: none; color: #3a3c8b;\">Edit</a></td>\n" + 
								"																	</tr> -->\n" + 
								"																</table>\n" + 
								"															</td>\n" + 
								"													\n" + 
								"													\n" + 
								"													\n" + 
								"											</tr>\n" + 
								"\n" + 
								"										</table></td>\n" + 
								"								</tr>\n" + 
								"\n" + 
								"								<tr>\n" + 
								"									<td cellspacing=\"0\" cellpadding=\"0\" style=\"position: relative;\"\n" + 
								"										border=\"0\"><table width=\"100%\" border=\"0\" cellspacing=\"0\"\n" + 
								"											cellpadding=\"0\">\n" + 
								"											<tr>\n" + 
								"												<td\n" + 
								"													style=\"line-height: 24px; padding: 10px 40px 40px 40px; color: #262626; text-align: center; font-size: 14px; background: #FFF;\">\n" + 
								"													\n" + 
								"												<b>Thank You For Being Part Of Monginis Family!!!</b>	\n" + 
								"													</td>\n" + 
								"											</tr>\n" + 
								"											<tr>\n" + 
								"												<td\n" + 
								"													style=\"background: #edf2f6; font-size: 12px; text-align: center; color: #66696c; padding: 10px 0; line-height: 20px;\">\n" + 
								"													<!-- isn't that you? <a href=\"#\"\n" + 
								"													style=\"text-decoration: underline; color: #33358f;\">Unsubscibe\n" + 
								"														from this email</a> --> <br>"+companyInfo.getCompName()+"<br>"+companyInfo.getFactAddress()+companyInfo.getPhoneNo1()+"\n" + 
								"												</td>\n" + 
								"											</tr>\n" + 
								"								\n" + 
								"							</table>\n" + 
								"						\n" + 
								"						\n" + 
								"						</td>\n" + 
								"					\n" + 
								"					</tr>\n" + 
								"					\n" + 
								"					\n" + 
								"					</table>\n" + 
								"				\n" + 
								"			\n" + 
								"			\n" + 
								"				</td>\n" + 
								"		\n" + 
								"		</tr>\n" + 
								"		\n" + 
								"		\n" + 
								"		\n" + 
								"		</table>\n" + 
								"\n" + 
								"</body>\n" + 
								"</html>";

						
						
						frEmail=resp.get(i).getFrEmail();
						mailSubject = "FDA  Expieres : ";
						
						flag = 1;
						srno = srno + 1;

					
							System.err.println("fr  Email: "+ frEmail);
							Info mailInfo = EmailUtility.sendEmailHtmlContent(senderEmail, senderPassword, frEmail,
									mailSubject, userName, emailContent);
						

					// Franchise End
	

					}
		} catch (Exception e) {
			System.err.println("Exception : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/*@Scheduled(cron = "0/20 * * * * ?")*/
	public void scheduleSendMailToFrBirthdayWish() {
System.err.println("In Birthday Cron");
		try {
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			 Calendar c = Calendar.getInstance();
		     c.setTime(date);
		     c.add(Calendar.DAY_OF_MONTH, 1);
		     Date currentDatePlusOne = c.getTime();
			System.err.println("Date-->"+sdf.format(currentDatePlusOne)+"\t"+sdf.format(date));
				List<Franchisee> resp=new ArrayList<>();
			
				resp=franRepo.getOwnerBirthDate(sdf.format(currentDatePlusOne));
			
				Company companyInfo =companyRepo.findOne(1);
			
			
			
			

				//List<GetMenuShow> menuList = getMenuShowRepo.getMenuListData();

				String senderEmail = "atsinfosoft@gmail.com";
				String senderPassword = "atsinfosoft#123";
				String mailSubject = null;
				String userName = null;
				String frEmail = null;
				String emailContent = null;
				int srno = 0;

				
				if(resp.size()>0) {
					
					
					
					
					for (int i = 0; i < resp.size(); i++) {
											

											// System.err.println("OtherSpItemDtl================"+spItmList);

											int flag = 0;

											emailContent="<!doctype html>\n" + 
													"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + 
													
													"<head>\n" + 
													"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" + 
													"<title>:: Monginis ::</title>\n" + 
													"</head>\n" + 
													"<body leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\"\n" + 
													"	yahoo=\"fix\"\n" + 
													"	style=\"font-family: Arial, sans-serif; background: #e3ebef;\">\n" + 
													"	\n" + 
													"		<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" + 
													"		align=\"center\" style=\"margin-top: 10px; margin-bottom: 10px;\">\n" + 
													"		<tr>\n" + 
													"			\n" + 
													"				<td width=\"100%\" valign=\"top\" bgcolor=\"#e3ebef\">\n" + 
													"				\n" + 
													"					<table width=\"700\" id=\"tborder\" class=\"deviceWidth\"\n" + 
													"					bgcolor=\"#f5f5f5\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\"\n" + 
													"					style=\"position: relative; border: 2px solid #d8e0e4;\">\n" + 
													"					\n" + 
													"					<tr>\n" + 
													"					\n" + 
													"						<td cellspacing=\"0\" cellpadding=\"0\" style=\"padding: 0;\">\n" + 
													"							\n" + 
													"							<table\n" + 
													"								width=\"100%\" id=\"\" class=\"\" cellspacing=\"0\" cellpadding=\"0\"\n" + 
													"								align=\"center\" background=\"#000\" border=\"0\" style=\"padding: 0;\">\n" + 
													"								\n" + 
													"								<tr>\n" + 
													"									<td align=\"center\" style=\"background: #FFF;\"><img\n" + 
													"										src=\"http://107.180.72.86:8080/uploads/baroda/MSPCAKE/logo.png\" alt=\"logo\"\n" + 
													"										style=\"border: none; max-width: 100%; padding: 15px 0; float: none;\">\n" + 
													"									</td>\n" + 
													"								</tr>\n" + 
													"								<tr>\n" + 
													"									<td style=\"background: #FFF;\"><img\n" + 
													"										src=\"http://107.180.72.86:8080/uploads/baroda/MSPCAKE/seprator.jpg\" alt=\"seprator\"\n" + 
													"										style=\"border: none; max-width: 100%; float: left; padding: 0 0 28px 0;\"></td>\n" + 
													"								</tr>\n" + 
													"								<tr>\n" + 
													"									<td cellspacing=\"0\" cellpadding=\"0\"\n" + 
													"										style=\"position: relative; padding: 0 40px 10px 40px; background: #FFF;\"\n" + 
													"										border=\"0\">\n" + 
													"										\n" + 
													"										\n" + 
													"										<table width=\"100%\" border=\"0\" cellspacing=\"0\"\n" + 
													"											cellpadding=\"0\" align=\"center\">\n" + 
													"\n" + 
													"											<tr>\n" + 
													"												<td\n" + 
													"													style=\"text-align: center; font-size: 14px; text-transform: uppercase; padding: 0 0 5px 0; color: #ec268f;\"><strong>HAPPY BIRTHDAY "+resp.get(i).getFrOwner()+"</strong></td>\n" + 
													"											</tr>\n" + 
													"											<tr>\n" + 
													"												<td\n" + 
													"													style=\"text-align: center; padding: 0 0 0 0; font-size: 14px; line-height: 22px; color: #333333;\">Here\n" + 
													"													Best wishes to you on your special day, sir. I hope this birthday brings you all the strength needed for a successful year ahead.</td>\n" + 
													"											</tr>\n" + 
													"											<tr>\n" + 
													"												\n" + 
													"													\n" + 
													"													\n" + 
													"													<td style=\"text-align: center;\">\n" + 
													"																<td style=\"text-align: center; padding: 30px 0 10px 0\"><!-- <a\n" + 
													"													href=\"#\"\n" + 
													"													style=\"background: #3a3c8b; padding: 9px 30px; color: #FFF; font-size: 30px; text-transform: uppercase; letter-spacing: 5px; text-decoration: none;\">722948</a> -->\n" + 
													"													<img\n" + 
													"										src=\"http://107.180.72.86:8080/uploads/baroda/MSPCAKE/emailerCake.jpg\" alt=\"logo\"\n" + 
													"										style=\"border: none; max-width: 50%; padding: 15px 0; float: none;\">\n" + 
													"													\n" + 
													"													</td>\n" + 
													"															</td>\n" + 
													"													\n" + 
													"													\n" + 
													"													\n" + 
													"											</tr>\n" + 
													"\n" + 
													"										</table></td>\n" + 
													"								</tr>\n" + 
													"\n" + 
													"								<tr>\n" + 
													"									<td cellspacing=\"0\" cellpadding=\"0\" style=\"position: relative;\"\n" + 
													"										border=\"0\"><table width=\"100%\" border=\"0\" cellspacing=\"0\"\n" + 
													"											cellpadding=\"0\">\n" + 
													"											<tr>\n" + 
													"												<td\n" + 
													"													style=\"line-height: 24px; padding: 10px 40px 40px 40px; color: #262626; text-align: center; font-size: 14px; background: #FFF;\">\n" + 
													"													\n" + 
													"												<b>Thank You For Being Part Of Monginis Family!!!</b>	\n" + 
													"													</td>\n" + 
													"											</tr>\n" + 
													"											<tr>\n" + 
													"												<td\n" + 
													"													style=\"background: #edf2f6; font-size: 12px; text-align: center; color: #66696c; padding: 10px 0; line-height: 20px;\">\n" + 
													"													<!-- isn't that you? <a href=\"#\"\n" + 
													"													style=\"text-decoration: underline; color: #33358f;\">Unsubscibe\n" + 
													"														from this email</a> --> <br>"+companyInfo.getCompName()+"<br>"+companyInfo.getFactAddress()+companyInfo.getPhoneNo1()+"\n" + 
													"												</td>\n" + 
													"											</tr>\n" + 
													"								\n" + 
													"							</table>\n" + 
													"						\n" + 
													"						\n" + 
													"						</td>\n" + 
													"					\n" + 
													"					</tr>\n" + 
													"					\n" + 
													"					\n" + 
													"					</table>\n" + 
													"				\n" + 
													"			\n" + 
													"			\n" + 
													"				</td>\n" + 
													"		\n" + 
													"		</tr>\n" + 
													"		\n" + 
													"		\n" + 
													"		\n" + 
													"		</table>\n" + 
													"\n" + 
													"</body>\n" + 
													"</html>";

											
											
											frEmail=resp.get(i).getFrEmail();
											mailSubject = "FDA  Expieres : ";
											
											flag = 1;
											srno = srno + 1;
										
												System.err.println("fr  Email: "+ frEmail);
												Info mailInfo = EmailUtility.sendEmailHtmlContent(senderEmail, senderPassword, frEmail,
														mailSubject, userName, emailContent);
											

										// Franchise End
						

								}
										
										
										
										
									}
	

			
		} catch (Exception e) {
			System.err.println("Exception : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	/*@Scheduled(cron = "0/20 * * * * ?")*/
	public void scheduleSendMailToFrShopOpening() {
System.err.println("In Shop Opening Cron");
		try {
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			 Calendar c = Calendar.getInstance();
		     c.setTime(date);
		     c.add(Calendar.DAY_OF_MONTH, 1);
		     Date currentDatePlusOne = c.getTime();
			System.err.println("Date-->"+sdf.format(currentDatePlusOne)+"\t"+sdf.format(date));
				List<Franchisee> resp=new ArrayList<>();
			
				resp=franRepo.getOwnerBirthDate(sdf.format(currentDatePlusOne));
			
			
			
			
			
			

				//List<GetMenuShow> menuList = getMenuShowRepo.getMenuListData();

				String senderEmail = "atsinfosoft@gmail.com";
				String senderPassword = "atsinfosoft#123";
				String mailSubject = null;
				String userName = null;
				String frEmail = null;
				String emailContent = null;
				int srno = 0;

				
					for (int i = 0; i < resp.size(); i++) {
						
						
						// System.err.println("OtherSpItemDtl================"+spItmList);

						int flag = 0;

						emailContent = "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "<style>\n" + "#customers {\n"
								+ "	font-family: Arial, Helvetica, sans-serif;\n" + "	border-collapse: collapse;\n"
								+ "	width: 80%;\n" + "}\n" + "\n" + "h5 {\n"
								+ "	font-family: Arial, Helvetica, sans-serif;\n" + "	border-collapse: collapse;\n"
								+ "	width: 78%;\n" + "	border: 1px solid #ddd;\n" + "	padding: 8px;\n"
								+ "	background-color: #ec268f;\n" + "	color: white;\n" + "	text-align: center;\n"
								+ "	font-size: 17px;\n" + "}\n" + "\n" + "#customers td, #customers th {\n"
								+ "	border: 1px solid #ddd;\n" + "	padding: 8px;\n" + "}\n" + "\n"
								+ "#customers tr:nth-child(even) {\n" + "	background-color: #f2f2f2;\n" + "}\n" + "\n"
								+ "#customers tr:hover {\n" + "	background-color: #ddd;\n" + "}\n" + "\n"
								+ "#customers th {\n" + "	padding-top: 12px;\n" + "	padding-bottom: 12px;\n"
								+ "	text-align: left;\n" + "	background-color: #ec268f;\n" + "	color: white;\n"
								+ "}\n" + "\n" + ".container {\n" + "	margin-left: 100px;\n" + "	width: 100%;\n"
								+ "}\n" + "</style>\n" + "</head>\n" + "<body>\n" + "	<div class=\"container\">";
						emailContent += "<table id=\"customers\">\n" + "			<thead>\n" + "				<tr>\n"
								+ "					<th>Sr No.</th>\n" + "					<th>Item Name</th>\n"
								+ "					<th>Flavour</th>\n" + "					<th>Qty.</th>\n"
								+ "					<th>Amount</th>\n" + "				</tr>\n"
								+ "			</thead>\n" + "			<tbody>";

						

								mailSubject = "Shop Opening Birthday : "+resp.get(i).getFrOwner();
								frEmail = resp.get(i).getFrEmail();
								flag = 1;
								srno = srno + 1;

								emailContent += "Happy Birthday";

							}
						
						emailContent += "</tbody>\n" + "	</table>\n" + "	</div>\n" + "	</body>\n" + "	</html>";

					
							System.err.println("fr  Email: "+ frEmail);
							Info mailInfo = EmailUtility.sendEmailHtmlContent(senderEmail, senderPassword, frEmail,
									mailSubject, userName, emailContent);
						

					// Franchise End
	

			
		} catch (Exception e) {
			System.err.println("Exception : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
}

package com.ats.webapi.component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ats.webapi.model.ConfigureFranchisee;
import com.ats.webapi.model.Franchisee;
import com.ats.webapi.model.GetCurrentStockDetails;
import com.ats.webapi.model.MCategory;
import com.ats.webapi.model.PostFrItemStockDetail;
import com.ats.webapi.model.PostFrItemStockHeader;
import com.ats.webapi.repository.CategoryRepository;
import com.ats.webapi.repository.ConfigureFrRepository;
import com.ats.webapi.repository.FrStockBetweenMonthRepository;
import com.ats.webapi.repository.FranchiseeRepository;
import com.ats.webapi.repository.PostFrOpStockDetailRepository;
import com.ats.webapi.repository.PostFrOpStockHeaderRepository;

//@Component
public class MonthEndScheduler {

	// SAC 10-08-2021
	@Autowired
	CategoryRepository catRepo;
	@Autowired
	ConfigureFrRepository configureFrRepository;

	@Autowired
	PostFrOpStockHeaderRepository postFrOpStockHeaderRepository;
	@Autowired
	FrStockBetweenMonthRepository stockDetailRepository;
	@Autowired
	PostFrOpStockDetailRepository postFrOpStockDetailRepository;
	@Autowired
	FranchiseeRepository frRepo;

	// Every month date 1 12 noon 0 0 12 1 1/1 ? *
	// 0 0 1 * *
//*/2 * * * *
	@Scheduled(cron = "2 * * * * *")
	// @Scheduled(cron = "*/2 * * * *")
	public void autoMonthEnd() {
		DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");

		DateFormat yearFormat = new SimpleDateFormat("yyyy");

		Date date = new Date();
		System.out.println(dateFormat1.format(date));
		String currentYear = yearFormat.format(date);
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		int dayOfMonth = cal1.get(Calendar.DATE);
		int calCurrentMonth = cal1.get(Calendar.MONTH) + 1;

		List<Integer> catList = new ArrayList<Integer>();
		catList.add(1);
		catList.add(2);
		catList.add(3);
		catList.add(4);
		int frId = 0;

		List<Franchisee> frList = new ArrayList<Franchisee>();
		frList = frRepo.findAllByDelStatusOrderByFrNameAsc(0);
		for (int f = 0; f < frList.size(); f++) {
			frId = frList.get(f).getFrId();

			for (int c = 0; c < catList.size(); c++) {

				PostFrItemStockHeader stockHeader = postFrOpStockHeaderRepository
						.findByFrIdAndCatIdAndIsMonthClosed(frId, catList.get(c), 0);
				if (stockHeader == null) {

				}
				if (stockHeader.getMonth() == calCurrentMonth) {
					// Month End Already done for catId
				} else {
					List<PostFrItemStockDetail> postFrItemStockDetailList = new ArrayList<PostFrItemStockDetail>();
					// detailList =
					// postFrOpStockDetailRepository.getFrDetail(stockHeader.getOpeningStockHeaderId());

					int runningMonth = stockHeader.getMonth();

					String strDate;
					int year;
					if (runningMonth == 12) {
						year = (Calendar.getInstance().getWeekYear() - 1);
					} else {
						year = Calendar.getInstance().getWeekYear();
					}

					strDate = year + "-0" + runningMonth + "-01";
					System.err.println("str date " + strDate);

					List<GetCurrentStockDetails> currentStockDetailList = stockDetailRepository.getMinOpeningStock2(
							stockHeader.getMonth(), Integer.parseInt(currentYear), frId, catList.get(c), strDate,
							getMonthFirstDate(), frList.get(f).getStockType(), getItemList(frId, catList.get(c)));

					System.err.println("currentStockDetailList " + currentStockDetailList);
					for (int i = 0; i < currentStockDetailList.size(); i++) {

						GetCurrentStockDetails stockDetails = currentStockDetailList.get(i);

						PostFrItemStockDetail postFrItemStockDetail = new PostFrItemStockDetail();
						int intPhysicalStock = stockDetails.getCurrentRegStock();
						postFrItemStockDetail.setItemId(stockDetails.getId());
						postFrItemStockDetail.setItemName(stockDetails.getItemName());
						postFrItemStockDetail.setRegOpeningStock(stockDetails.getRegOpeningStock());
						postFrItemStockDetail.setOpeningStockDetailId(stockDetails.getStockDetailId());
						postFrItemStockDetail.setOpeningStockHeaderId(stockDetails.getStockHeaderId());
						postFrItemStockDetail.setPhysicalStock(intPhysicalStock);
						postFrItemStockDetail.setRemark("Auto Month End");

						int intStockDiff = 0;

						int currentStock = (stockDetails.getCurrentRegStock() + stockDetails.getRegTotalPurchase())
								- (stockDetails.getRegTotalGrnGvn() + stockDetails.getRegTotalSell());

						if (currentStock > intPhysicalStock) {
							intStockDiff = currentStock - intPhysicalStock;
						} else {
							intStockDiff = intPhysicalStock - currentStock;
						}

						postFrItemStockDetail.setStockDifference(intStockDiff);
						postFrItemStockDetail.setRegTotalGrnGvn(stockDetails.getRegTotalGrnGvn());
						postFrItemStockDetail.setRegTotalPurchase(stockDetails.getRegTotalPurchase());
						postFrItemStockDetail.setRegTotalSell(stockDetails.getRegTotalSell());

						postFrOpStockDetailRepository.save(postFrItemStockDetail);

						postFrItemStockDetailList.add(postFrItemStockDetail);
					} // end of currentStockDetailList loop
					System.err.println("postFrItemStockDetailList for update " + postFrItemStockDetailList);

					int headerId = stockHeader.getOpeningStockHeaderId();

					int updateResult = postFrOpStockHeaderRepository.endMonth(headerId);
					// new opening month entry
					PostFrItemStockHeader newHeader = new PostFrItemStockHeader();
					if (stockHeader.getMonth() == 12) {
						newHeader.setYear(stockHeader.getYear() + 1);
						newHeader.setMonth(1);

					} else {
						newHeader.setMonth(stockHeader.getMonth() + 1);
						newHeader.setYear(stockHeader.getYear());

					}

					newHeader.setFrId(stockHeader.getFrId());
					newHeader.setCatId(stockHeader.getCatId());

					newHeader.setIsMonthClosed(0);

					PostFrItemStockHeader postFrItemStockHeaders = new PostFrItemStockHeader();

					postFrItemStockHeaders = postFrOpStockHeaderRepository.save(newHeader);

					for (int j = 0; j < postFrItemStockDetailList.size(); j++) {

						PostFrItemStockDetail oldStockDetail = postFrItemStockDetailList.get(j);

						PostFrItemStockDetail newStockDetail = new PostFrItemStockDetail();
						newStockDetail.setOpeningStockHeaderId(postFrItemStockHeaders.getOpeningStockHeaderId());
						newStockDetail.setItemId(oldStockDetail.getItemId());
						newStockDetail.setItemName(oldStockDetail.getItemName());
						newStockDetail.setRegOpeningStock(oldStockDetail.getPhysicalStock());
						newStockDetail.setSpOpeningStock(oldStockDetail.getSpOpeningStock());
						newStockDetail.setPhysicalStock(0);
						newStockDetail.setRemark("new Entry Auto month end");
						newStockDetail.setStockDifference(0);
						newStockDetail.setRegTotalGrnGvn(0);
						newStockDetail.setRegTotalPurchase(0);
						newStockDetail.setSpTotalPurchase(0);
						newStockDetail.setRegTotalSell(0);
						newStockDetail.setSpTotalSell(0);

						postFrOpStockDetailRepository.save(newStockDetail);

					} // end of for postFrItemStockDetailList

				} // end of else

			} // End of catId For
		} // End of frList For Loop
	}// End of autoMonthEnd() function

	public List<Integer> getItemList(int frId, int catId) {
		String itemShow = "";

		List<ConfigureFranchisee> frConfList = null;
		frConfList = configureFrRepository.getAllFrConfByFrIdAndCat(frId, catId);
		StringBuilder itemIdStr = new StringBuilder();

		if (frConfList != null) {
			for (ConfigureFranchisee data : frConfList) {
				itemIdStr.append(data.getItemShow());
				itemIdStr.append(",");
			}
		}

		if (itemIdStr.length() > 0) {
			itemShow = itemIdStr.substring(0, itemIdStr.length() - 1);
		}
		List<Integer> itemList = Stream.of(itemShow.split(",")).map(String::trim).map(Integer::parseInt)
				.collect(Collectors.toList());
		itemList.clear();
		// itemList.add(4);
		return itemList;
	}

	public String getMonthFirstDate() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date todaysDate = new Date();
		System.out.println(dateFormat.format(todaysDate));

		Calendar cal = Calendar.getInstance();
		cal.setTime(todaysDate);

		cal.set(Calendar.DAY_OF_MONTH, 1);

		Date firstDay = cal.getTime();

		System.out.println("First Day of month " + firstDay);

		String strFirstDay = dateFormat.format(firstDay);
		return strFirstDay;
	}

	public String getCurDate() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

		Date todaysDate = new Date();
		System.out.println(dateFormat.format(todaysDate));

		Calendar cal = Calendar.getInstance();
		cal.setTime(todaysDate);

		System.out.println("todaysDate Day   " + todaysDate);

		String strTodayDate = dateFormat.format(todaysDate);
		return strTodayDate;
	}

}

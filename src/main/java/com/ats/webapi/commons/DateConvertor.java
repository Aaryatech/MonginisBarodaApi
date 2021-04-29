package com.ats.webapi.commons;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateConvertor {

	public static Date getUtilDateFromStrYMDDate(String strYmdDate) {
		
		LocalDate date = LocalDate.parse(strYmdDate.trim());
	    
		Date returnUtilDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return returnUtilDate;
	}
	
	public static Date getUtilDateByAddSubGivenDays(String strYmdDate,int noOfDays) {
		
		LocalDate date = LocalDate.parse(strYmdDate.trim());
		LocalDate date2 = date.plusDays(noOfDays);
		Date returnUtilDate = Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		return returnUtilDate;
	}
	
}

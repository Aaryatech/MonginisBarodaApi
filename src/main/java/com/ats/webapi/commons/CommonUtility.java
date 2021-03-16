package com.ats.webapi.commons;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.ats.webapi.model.Info;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.mail.smtp.SMTPSendFailedException;

public class CommonUtility {

	// #1
	// Convert given String DDMMYYYY Date to YYYYMMDD Date.
	public static String convertToYMD(String date) {

		String convertedDate = null;
		try {
			SimpleDateFormat ymdSDF = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dmySDF = new SimpleDateFormat("dd-MM-yyyy");
			Date dmyDate = dmySDF.parse(date);

			convertedDate = ymdSDF.format(dmyDate);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convertedDate;
	}

	// #2
	// Convert given String YYYYMMDD Date to DDMMYYYY Date.
	public static String convertToDMY(String utildate) {
		String convertedDate = null;
		try {
			SimpleDateFormat ymdSDF = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat ymdSDF2 = new SimpleDateFormat("yyyy-MM-dd");

			SimpleDateFormat dmySDF = new SimpleDateFormat("dd-MM-yyyy");
			Date ymdDate = ymdSDF2.parse(utildate);
			convertedDate = dmySDF.format(ymdDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return convertedDate;
	}

	// #3
	// String DDMMYYYY to SQL Date DDMMYYYY
	public static java.sql.Date convertToSqlDate(String date) {
		java.sql.Date convertedDate = null;
		try {
			SimpleDateFormat ymdSDF = new SimpleDateFormat("yyyy-mm-dd");
			SimpleDateFormat dmySDF = new SimpleDateFormat("dd-MM-yyyy");

			Date dmyDate = dmySDF.parse(date);
			convertedDate = new java.sql.Date(dmyDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return convertedDate;
	}

	// #4
	// get Calendar object with given interval added with time
	public static Calendar getTimePlusSpecMin(int interval) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, interval);
		// return String.valueOf(df.format(cal30.getTime()));
		return cal;
	}

	// #5
	// get current Calendar object instance
	public static Calendar getCurTime() {
		int interval = 0;
		Calendar curCal = Calendar.getInstance();
		curCal.add(Calendar.MINUTE, interval);
		// return String.valueOf(df.format(curCal.getTime()));
		return curCal;
	}

	// #6
	// Get From to Date with months first day date to current day's date in DDMMYYYY
	public static String getFromToDate() {

		String leaveDateRange = null;
		String fromDate = null;
		String toDate = null;

		Calendar c = Calendar.getInstance(); // this takes current date
		Date toDate1 = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		toDate = sdf.format(toDate1);

		c.set(Calendar.DAY_OF_MONTH, 1);
		Date fromDate1 = c.getTime();
		fromDate = sdf.format(fromDate1);

		leaveDateRange = fromDate.concat(" to ").concat(toDate);
		return leaveDateRange;
	}

	// #7
	// get current months start date and end date. in DDMMYYYY
	public static String getMonthsStartEnd() {
		String dateRange = null;
		String fromDate = null;
		String toDate = null;
		Calendar c = Calendar.getInstance(); // this takes current date

		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DATE, -1);
		Date toDate1 = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		toDate = sdf.format(toDate1);
		c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		Date fromDate1 = c.getTime();

		fromDate = sdf.format(fromDate1);
		dateRange = fromDate.concat(" to ").concat(toDate);
		return dateRange;
	}

	// #8
	// Adding noOfDays to Current Date and return that date
	public static String addDaystoCurrentDate(int noOfDays) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, noOfDays); // Adding 5 days
		String output = sdf.format(c.getTime());
		System.out.println(output);
		return output;
	}

	// #9
	// Adding noOfDays to Current Date and return that date
	public static String addDaystoGivenDate(int noOfDays, String inputDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(inputDate)); // Use inputDate date.
		c.add(Calendar.DATE, noOfDays); // Adding given noOfDays days
		String outputDate = sdf.format(c.getTime());
		System.out.println(outputDate);
		return outputDate;
	}

	// #10
	// get Current YMD Datetime
	public static String getCurrentYMDDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

//	public static void main(String[] args) {
//		// sendMessage("hhh","9404725912");
//		sendEmailWithSubMsgAndToAdd("Test", "hello ", "abc@kkk.com");
//
//		getCurrentYMDDateTime();
//		addDaystoCurrentDate(4);
//		try {
//			addDaystoGivenDate(-2, "15-08-2020");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		float x = roundHalfUpByScale(124.163445f, 2);
//		System.err.println(x);
//		Info info = new Info();
//		info.setError(false);
//		info.setMessage("Abcc sajdkfsk kf");
//		String jsonStringArray = toJSONString(info);
//		System.err.println("jsonStringArray " + jsonStringArray);
//	}

	// #11
	// get Round up of given float value with specified scale
	public static float roundHalfUpByScale(float d, int scale) {
		// return BigDecimal.valueOf(d).setScale(2,
		// BigDecimal.ROUND_HALF_UP).floatValue();
		return BigDecimal.valueOf(d).setScale(scale, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	// #12
	// Convert given hour string to minutes (hours and minutes with ':' as
	// separator)
	public static String convertHoursToMin(String stringHours) {
		String min = new String();

		try {

			String[] result = stringHours.split(":");
			int hours = Integer.parseInt(result[0]);
			int minutes = Integer.parseInt(result[1]);
			min = String.valueOf((hours * 60) + minutes);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return min;

	}

	// #13
	// Convert given minutes string to hour
	public static String convertMinToHours(String inputMinutes) {
		String min = new String();
		int minutes = Integer.parseInt(inputMinutes);
		try {
			String hrs = String.valueOf(minutes / 60);
			String rem = String.valueOf(minutes % 60);
			if (String.valueOf(hrs).length() == 1) {
				hrs = "0".concat(hrs);
			}
			if (String.valueOf(rem).length() == 1) {
				rem = "0".concat(rem);
			}
			min = hrs.concat(":").concat(rem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return min;
	}

	// #14
	// change session key for each request
	public static void changeSessionKey(HttpServletRequest request) {
		try {

			UUID uuid = UUID.randomUUID();
			MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

			byte[] messageDigest = md.digest(String.valueOf(uuid).getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);

			HttpSession session = request.getSession();
			session.setAttribute("generatedKey", hashtext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ObjectMapper mapper = new ObjectMapper();

	// #15
	// Convert given bean object to JSON String
	public static String toJSONString(Object o) {
		String result = "";
		if (o != null) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			try {
				mapper.writeValue(out, o);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				result = out.toString("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// #16
	// To get OTP String of Integer for specified length
	public static String getOTP(int length) {

		String AlphaNumericString = "0123456789";
		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

	// #17
	// send Email -parameters as subject,msgContent, toAddress
	public static Info sendEmailWithSubMsgAndToAdd(String mailSubjet, String msgContent, String toAddress) {

		Info info = new Info();

		try {

			final String emailSMTPserver = "smtp.gmail.com";
			final String mailStoreType = "imaps";
			String username = "atsinfosoft@gmail.com";
			String password = "atsinfosoft@123";

			System.out.println("username" + username);
			System.out.println("password" + password);

			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.starttls.enable", "true");

			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {
				Store mailStore = session.getStore(mailStoreType);
				mailStore.connect(emailSMTPserver, username, password);

				Message mimeMessage = new MimeMessage(session);
				mimeMessage.setFrom(new InternetAddress(username));
				mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
				mimeMessage.setSubject(mailSubjet);
				mimeMessage.setText(msgContent);

				Transport.send(mimeMessage);
			} catch (SMTPSendFailedException e) {
				System.err.println("ANNNN");
				System.err.println(e.getValidUnsentAddresses().toString());
				info.setError(true);
				info.setMessage("email_exce");
			}

			info.setError(false);
			info.setMessage("success_email");
		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMessage("email_exce");
		}

		return info;

	}

	// #18
	// send text Message -parameters as message and deliveryNo
	public static void sendMessage(String message, String deliveryNo) {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		RestTemplate restTemplate = new RestTemplate();

		Info info = new Info();
		String msg = " Hello Sachin";

		map.add("username", "rusamah-wb");
		map.add("password", "Rus@@123456");
		map.add("senderid", "MHRUSA");
		map.add("mobileno", deliveryNo);
		map.add("content", msg);
		map.add("smsservicetype", "singlemsg");

		ParameterizedTypeReference<String> typeRef = new ParameterizedTypeReference<String>() {
		};

		ResponseEntity<String> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange("https://msdgweb.mgov.gov.in/esms/sendsmsrequest" + "",
					HttpMethod.POST, new HttpEntity<>(map), typeRef);
			System.err.println("Message Service Response for  9404725912 " + responseEntity.getBody());
		} catch (HttpClientErrorException e) {
			System.err.println("responseEntity " + e.getResponseBodyAsString());
		}

		/*
		 * String sms =
		 * restTemplate.postForObject("https://msdgweb.mgov.gov.in/esms/sendsmsrequest",
		 * map, String.class);
		 */

	}

	// #19
	// Return String with capitalizing first letter of each word for given String
	// parameter
	public static String capitalizeWord(String str) {
		String words[] = str.split("\\s");
		String capitalizeWord = "";
		for (String w : words) {
			String first = w.substring(0, 1);
			String afterfirst = w.substring(1);
			capitalizeWord += first.toUpperCase() + afterfirst + " ";
		}
		return capitalizeWord.trim();
	}

	// #20
	// Return String in Upper case for given String parameter
	public static String toUpperCase(String str) {

		return str.toUpperCase();
	}

//#21
	// Return String in Lower case for given String parameter
	public static String toLowerCase(String str) {

		return str.toLowerCase();

	}

//#22
	// Return OTP
	public static char[] OTP(int len) {
		char[] otp = new char[len];
		try {
			System.out.println("Generating OTP using random() : ");

			// Using numeric values
			String numbers = "0123456789";

			// Using random method
			Random rndm_method = new Random();

			for (int i = 0; i < len; i++) {
				// Use of charAt() method : to get character value
				// Use of nextInt() as it is scanning the value as int
				otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
			}
		} catch (Exception e) {
			System.err.println("ex in comm OTP gen " + e.getMessage());
			e.printStackTrace();
		}
		return otp;
	}

//#23
	public static String getAlphaNumericString(int n) {

		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

}

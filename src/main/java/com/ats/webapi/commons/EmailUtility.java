package com.ats.webapi.commons;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.ats.webapi.model.Info;

public class EmailUtility {

	public static Info sendEmail(String senderEmail, String senderPassword, String recipientEmail, String mailsubject,
			String defUsrName, String defPass) {

		Info info = new Info();

		try {

			final String emailSMTPserver = "smtp.gmail.com";
			final String emailSMTPPort = "587";
			final String mailStoreType = "imaps";
			final String username = senderEmail;// "atsinfosoft@gmail.com";
			final String password = senderPassword;// "atsinfosoft@123";

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

				String address = recipientEmail;// "atsinfosoft@gmail.com";// address of to

				String subject = mailsubject;// " Login Credentials For RUSA Login ";

				Message mimeMessage = new MimeMessage(session);
				mimeMessage.setFrom(new InternetAddress(username));
				mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
				mimeMessage.setSubject(subject);
				mimeMessage.setText(defUsrName + defPass);

				Transport.send(mimeMessage);
			} catch (Exception e) {
				e.printStackTrace();
				info.setError(true);
				info.setMessage("email_exce");
			}

			info.setError(false);
			info.setMessage("success_email");
		} catch (Exception e) {

			info.setError(true);
			info.setMessage("email_exce");
		}

		return info;

	}
	
	
	
	
	public static Info sendOrderEmail(String senderEmail, String
			senderPassword, String frEmail, String mailsubject,
			String mailText) {
			Info info=new Info();
			try {
			final String emailSMTPserver = "smtp.gmail.com";
			final String emailSMTPPort = "587";
			final String mailStoreType = "imaps";
			final String username = senderEmail;//"atsinfosoft@gmail.com";
			final String password =senderPassword;//"atsinfosoft@123";

			System.out.println("username" + username);
			System.out.println("password" + password);

			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.starttls.enable", "true");


			Session session = Session.getDefaultInstance(props, new
			javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
			}
			});

			try {
			Store mailStore = session.getStore(mailStoreType);
			mailStore.connect(emailSMTPserver, username, password);

			String address =frEmail;

			String subject = mailsubject;

			Message mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(username));
			mimeMessage.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(address));
			mimeMessage.setSubject(subject);
			mimeMessage.setContent(mailText, "text/html");
			Transport.send(mimeMessage);
			} catch (Exception e) {
			e.printStackTrace();
			info.setError(true);
			info.setMessage("email_exce");
			//System.err.println("Mail Response1 : "+info);
			System.err.println("Ex1"+e.getMessage());
			e.printStackTrace();
			}
			info.setError(false);
			info.setMessage("success_email");
			//System.err.println("Mail Response2 : "+info);
			}catch (Exception e) {
			info.setError(true);
			info.setMessage("email_exce");
			//System.err.println("Mail Response3 : "+info);
			System.err.println("Ex2"+e.getMessage());
			e.printStackTrace();
			}
			return info;
			}

	// OTP EMAIL
	public static Info sendEmailOTP(String senderEmail, String senderPassword, String recipientEmail,
			String mailsubject, String defUsrName, String defPass) {

		Info info = new Info();

		try {

			final String emailSMTPserver = "smtp.gmail.com";
			final String emailSMTPPort = "587";
			final String mailStoreType = "imaps";
			final String username = senderEmail;// "atsinfosoft@gmail.com";
			final String password = senderPassword;// "atsinfosoft@123";

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

				String address = recipientEmail;// "atsinfosoft@gmail.com";// address of to

				String subject = mailsubject;// " Login Credentials For RUSA Login ";

				Message mimeMessage = new MimeMessage(session);
				mimeMessage.setFrom(new InternetAddress(username));
				mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
				mimeMessage.setSubject(subject);
				mimeMessage.setText("OTP Verification for  RUSA Software \n OTP is: " + defPass);

				Transport.send(mimeMessage);
			} catch (Exception e) {
				e.printStackTrace();
				info.setError(true);
				info.setMessage("email_exce");
			}

			info.setError(false);
			info.setMessage("success_email");
		} catch (Exception e) {

			info.setError(true);
			info.setMessage("email_exce");
		}

		return info;

	}

	public static Info sendMsg(String userName, String pass, String phoneNo) {

		Info info = new Info();

		try {

			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			/*
			 * map.add("authkey", "74499AcqeCdljW5ae561dd"); map.add("mobiles", phoneNo);
			 * map.add("message", "RUSA CREDENTIAL Your User Name :" + userName
			 * +" Your Password :" + pass +" Plz Dont Share To Any One "); map.add("sender",
			 * "ESYRTO"); map.add("route", "4"); map.add("country", "91");
			 */

			//

			/*
			 * map.add("senderID", "RUSAMH"); map.add("user",
			 * "spdrusamah@gmail.com:Cyber@mva"); map.add("receipientno", phoneNo.trim());
			 * map.add("dcs", "0"); map.add(
			 * "msgtxt","User Registration Successful for RUSA Software \n Username: " +
			 * userName + "\n Password: " + pass); map.add("state", "4");
			 * 
			 * 
			 * //String response =
			 * restTemplate.postForObject("http://control.bestsms.co.in/api/sendhttp.php",
			 * map, String.class);
			 * 
			 * String response = restTemplate.postForObject(
			 * "http://api.mVaayoo.com/mvaayooapi/MessageCompose", map, String.class);
			 */
			String msg = "User Registration Successful for RUSA Software \n Username: " + userName + "\n Password: "
					+ pass;

			map.add("username", "rusamah-wb");
			map.add("password", "Rus@@123456");
			map.add("senderid", "MHRUSA");
			map.add("mobileno", phoneNo.trim());
			map.add("content", msg);
			map.add("smsservicetype", "singlemsg");
			String sms = restTemplate.postForObject("https://msdgweb.mgov.gov.in/esms/sendsmsrequest", map,
					String.class);

			info.setError(false);
			info.setMessage(sms);

		} catch (Exception e) {

			info.setError(true);
			info.setMessage("sendMsg");
		}

		return info;
	}

	public static Info sendOtp(String OTP, String phoneNo, String msg) {

		Info info = new Info();

		try {

			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			/*
			 * map.add("senderID", "RUSAMH"); map.add("user",
			 * "spdrusamah@gmail.com:Cyber@mva"); map.add("receipientno", phoneNo.trim());
			 * map.add("dcs", "0"); map.add("msgtxt",msg + " " +OTP); map.add("state", "4");
			 * 
			 * String response = restTemplate.postForObject(
			 * "http://api.mVaayoo.com/mvaayooapi/MessageCompose", map, String.class);
			 */
			String message = msg + " " + OTP;
			String mob = phoneNo.trim();

			String sms="";
//			String sms = restTemplate.getForObject(
//					"http://control.bestsms.co.in/api/sendhttp.php?authkey=314773AiTXKWgYX9R5e2aa5c1P1&mobiles=" + mob
//							+ "&message=" + message + "&sender=MONGII&route=4&country=91",
//					String.class);

			info.setError(false);
			info.setMessage(sms);

		} catch (Exception e) {

			info.setError(true);
			info.setMessage("sendMsg");
		}

		return info;
	}

	public static Info sendOtp1(String OTP, String phoneNo) {

		Info info = new Info();

		try {

			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String msg = "We welcome You to Madhvi!\n" + "Your OTP to change your password is (" + OTP + ").";

			String message = msg;
			String mob = phoneNo.trim();

			String sms ="";
			
//			String sms = restTemplate
//					.getForObject(
//							"https://smsapi.24x7sms.com/api_2.0/SendSMS.aspx?APIKEY=pJMAaVPuGbh&MobileNo=" + mob
//									+ "&SenderID=MADHVI&Message=" + message + "&ServiceName=TEMPLATE_BASED",
//							String.class);

			info.setError(false);
			info.setMessage(sms);

		} catch (Exception e) {

			info.setError(true);
			info.setMessage("sendMsg");
		}

		return info;
	}

	public static Info send(String phoneNo, String msg) {

		Info info = new Info();

		try {

			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

//		String msg = "We welcome You to Madhvi!\n"+
//					 "Your OTP to change your password is ("+OTP+").";

			String message = msg;
			String mob = phoneNo.trim();

			String sms = "";
//			String sms = restTemplate
//					.getForObject(
//							"https://smsapi.24x7sms.com/api_2.0/SendSMS.aspx?APIKEY=pJMAaVPuGbh&MobileNo=" + mob
//									+ "&SenderID=MADHVI&Message=" + message + "&ServiceName=TEMPLATE_BASED",
//							String.class);

			info.setError(false);
			info.setMessage(sms);
			System.err.println("SMS Resp : " + info);

		} catch (Exception e) {

			info.setError(true);
			info.setMessage("sendMsg");
			System.err.println("SMS Resp : " + info);
			System.err.println("SMS Ex : " + e.getMessage());
		}

		return info;
	}
	
	public static Info sendEmailer(String senderEmail,String senderPassword,String recipientEmail,String mailsubject,
			String userName, String defPass, String compName, String compAdd) {
			
			Info info=new Info();
			
			try {
				
			final String emailSMTPserver = "smtp.gmail.com";
			final String emailSMTPPort = "587";
			final String mailStoreType = "imaps";
			final String username = senderEmail;//"atsinfosoft@gmail.com";
			final String password =senderPassword;//"atsinfosoft@123";

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

				String address =recipientEmail;// "atsinfosoft@gmail.com";// address of to

				String subject = mailsubject;//" Login Credentials For RUSA Login  ";

				Message mimeMessage = new MimeMessage(session);
				mimeMessage.setFrom(new InternetAddress(username));
				mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
				mimeMessage.setSubject(subject);
				//mimeMessage.setText(defUsrName + defPass);
				
				String htmlStr = "<!doctype html>\n" + 
						"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + 
						"<head>\n" + 
						"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" + 
						"<title>Monginis :: OTP Emailer</title>\n" + 
						"</head>\n" + 
						"<body leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" yahoo=\"fix\" style=\"font-family:Arial, sans-serif; background:#e3ebef;\">\n" + 
						"<!-- Wrapper -->\n" + 
						"<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"margin-top:10px; margin-bottom:10px;\">\n" + 
						"  <tr>\n" + 
						"    <td width=\"100%\" valign=\"top\" bgcolor=\"#e3ebef\"><!-- Start Header--> <!--style=\"padding-top:20px; padding-bottom:20px;\"-->\n" + 
						"      \n" + 
						"      <table width=\"700\" id=\"tborder\" class=\"deviceWidth\" bgcolor=\"#fff\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\"  style=\"position:relative;\">\n" + 
						"        <!--f07d00-->\n" + 
						"        <tr>\n" + 
						"          <td cellspacing=\"0\" cellpadding=\"0\"  style=\" padding:0;\"><table width=\"100%\" id=\"\" class=\"\" cellspacing=\"0\" cellpadding=\"0\"  align=\"center\" background=\"#000\" border=\"0\" style=\"padding:0;\">\n" + 
						"              <tr>\n" + 
						"                <td align=\"center\" style=\"background:#e3ebef; padding:10px 0;\"><img src=\"${pageContext.request.contextPath}/resources/img/monginislogo.png\" alt=\"Monginis\" style=\"border:none; max-width:100%; float:none;\"></td>\n" + 
						"              </tr>\n" + 
						"              <tr>\n" + 
						"                <td cellspacing=\"0\" cellpadding=\"0\"  style=\"position:relative; padding:30px 40px;\" border=\"0\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" + 
						"                    <tr>\n" + 
						"                      <td style=\"text-align:left; font-size:14px; text-transform:uppercase; padding:0 0 5px 0; color:#f26a90;\"><strong> Hello "+userName+"</strong></td>\n" + 
						"                    </tr>\n" + 
						"                    <tr>\n" + 
						"                       <td style=\"text-align:left; padding:0 0 15px 0; font-size:12px; line-height:22px; color:#333333;\">Your request for change password is received. Here is the One Time Password(OTP) for change password </td>\n" + 
						"                    </tr>\n" + 
						"                    <tr>\n" + 
						"                      <td style=\"text-align:center; padding:30px 0 10px 0\"><a href=\"#\" style=\"background:#9ccd2b; padding:9px 30px; color:#FFF; font-size:30px; text-transform:uppercase; letter-spacing:5px; text-decoration:none;\">"+defPass+"</a></td>\n" + 
						"                    </tr>\n" + 
						"                  </table></td>\n" + 
						"              </tr>\n" + 
						"              \n" + 
						"              \n" + 
						"              \n" + 
						"              <tr>\n" + 
						"                <td cellspacing=\"0\" cellpadding=\"0\"  style=\"position:relative;\" border=\"0\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" + 
						"                    <tr>\n" + 
						"                      <td style=\"line-height:24px; padding:0 40px 40px 40px; color:#262626; text-align:center; font-size: 12px;\">All you have to do is copy the confirmation code and paste it to your form to complete the change password process.</td>\n" + 
						"                    </tr>\n" + 
						"                    <tr>\n" + 
						"                      <td style=\"background:#edf2f6; font-size:12px; text-align:center; color:#66696c; padding:10px 0; line-height:20px;\">\n" + 
						"                      "+compName+"<br>\n" + 
						"					   "+compAdd+" <br>\n" + 
						"                      Copyright 2021. All rights reserved. Monginis</td>\n" + 
						"                    </tr>\n" + 
						"                    \n" + 
						"                    \n" + 
						"                  </table></td>\n" + 
						"              </tr>\n" + 
						"            </table>\n" + 
						"            \n" + 
						"            <!--bottom start---> \n" + 
						"            <!--bottom end---></td>\n" + 
						"        </tr>\n" + 
						"      </table>\n" + 
						"      \n" + 
						"      <!--end--></td>\n" + 
						"  </tr>\n" + 
						"</table>\n" + 
						"<!-- End Wrapper https://www.mytatasky.com/delegate/EmailCampaignAction/CampaignAction?campaignID=liUFBUl1mbM=&vendor=oBEMvseSmG0= -->\n" + 
						"</body>\n" + 
						"</html>\n" + 
						"";
				
				mimeMessage.setContent(htmlStr, "text/html");
				Transport.send(mimeMessage);
			} catch (Exception e) {
				e.printStackTrace();
				info.setError(true);
				info.setMessage("email_exce");
			}
				
				info.setError(false);
				info.setMessage("success_email");
			}catch (Exception e) {
				
				info.setError(true);
				info.setMessage("email_exce");
			}
			
			return info;
			
		}
	
	public static Info sendEmailHtmlContent(String senderEmail,String senderPassword,String recipientEmail,String mailsubject,
			String userName, String content) {
			
			Info info=new Info();
			
			try {
				
			final String emailSMTPserver = "smtp.gmail.com";
			final String emailSMTPPort = "587";
			final String mailStoreType = "imaps";
			final String username = senderEmail;//"atsinfosoft@gmail.com";
			final String password =senderPassword;//"atsinfosoft@123";

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

				String address =recipientEmail;// "atsinfosoft@gmail.com";// address of to

				String subject = mailsubject;//" Login Credentials For RUSA Login  ";

				Message mimeMessage = new MimeMessage(session);
				mimeMessage.setFrom(new InternetAddress(username));
				mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
				mimeMessage.setSubject(subject);
				//mimeMessage.setText(defUsrName + defPass);
				
				String htmlStr = content ;
						
				
				mimeMessage.setContent(htmlStr, "text/html");
				Transport.send(mimeMessage);
			} catch (Exception e) {
				e.printStackTrace();
				info.setError(true);
				info.setMessage("email_exce");
			}
				
				info.setError(false);
				info.setMessage("success_email");
			}catch (Exception e) {
				
				info.setError(true);
				info.setMessage("email_exce");
			}
			
			return info;
			
		}

}
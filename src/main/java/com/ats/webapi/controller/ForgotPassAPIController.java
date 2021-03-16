package com.ats.webapi.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ats.webapi.commons.CommonUtility;
import com.ats.webapi.commons.EmailUtility;
import com.ats.webapi.model.Info;
import com.ats.webapi.model.OTPVerification;
import com.ats.webapi.model.User;
import com.ats.webapi.model.bill.Company;
import com.ats.webapi.repository.CompanyRepository;
import com.ats.webapi.repository.UserRepository;


@RestController
public class ForgotPassAPIController {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CompanyRepository companyRepository;

	public static String senderEmail = "atsinfosoft@gmail.com";
	public static String senderPassword = "atsinfosoft#123";
	static String mailsubject = "";
	String otp1 = null;

	@RequestMapping(value = { "/getMnUserDetailByMobNo" }, method = RequestMethod.POST)
	public @ResponseBody User getMnUserDetailByMobNo(@RequestParam String mobEmail) {
		OTPVerification.setConNumber(null);
		OTPVerification.setEmailId(null);
		OTPVerification.setOtp(null);
		OTPVerification.setPass(null);
		Info info = new Info();

		User user = new User();
		try {

			user = userRepo.findByContactAndDelStatus(mobEmail, 0);
			if (user == null) {
				user = userRepo.findByUsernameAndDelStatus(mobEmail,0);
			}
			if (user != null) {
				OTPVerification.setUserId(user.getId());

				String emailId = user.getEmail();
				String conNumber = user.getContact();
				String userName = user.getUsername();
			//	System.err.println("User Found----------" + conNumber);
				char[] otp = CommonUtility.OTP(6);
				otp1 = String.valueOf(otp);
				System.err.println("User otp is : " + otp1);

				String otpMsg = "Monginis Patna - Your \n"
								+ "OTP for update password is "+otp1+".";
				RestTemplate rest=new RestTemplate();
				
				
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String billDate = simpleDateFormat.format(new java.util.Date());
				
				
				
				Company comp =null;
				String compName = new String();
				String compAdd = new String();
				try {
				comp=companyRepository.findByBillDate(billDate);
				compName = comp.getCompName();
				compAdd = comp.getFactAddress();
				
				}catch (Exception e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
					
					compName = "Monginis";
					compAdd = "Patna";
				}
				
				String url="http://smspatna.com/API/WebSMS/Http/v1.0a/index.php?username=MONGNS2222&password=MONGNS2222&sender=MONGNS&to="+conNumber+"&message="+otpMsg+"&route_id=328";
				
				String smsRes = rest.getForObject(url,String.class);			

				mailsubject = " OTP  Verification ";	
				Info emailRes = EmailUtility.sendEmailer(senderEmail, senderPassword, emailId, mailsubject, userName, otp1, compName, compAdd);
				
				
				OTPVerification.setConNumber(conNumber);
				OTPVerification.setEmailId(emailId);
				OTPVerification.setOtp(otp1);
				OTPVerification.setPass(user.getPassword());
			} else {			
				info.setError(true);
				info.setMessage("not Matched");
				System.err.println(" not Matched ");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@RequestMapping(value = { "/verifyOTP" }, method = RequestMethod.POST)
	public @ResponseBody User verifyOTP(@RequestParam String otp) {
		Info info = new Info();

		Object object = new Object();
		HashMap<Integer, User> hashMap = new HashMap<>();

		User user = new User();

		try {
			// System.err.println("OTP
			// Found------------------"+otp+"------"+OTPVerification.getOtp()+"
			// "+OTPVerification.getUserId());
			if (otp.equals(OTPVerification.getOtp()) == true) {
				info.setError(false);
				info.setMessage("success");

				String mobile = OTPVerification.getConNumber();
				String email = OTPVerification.getEmailId();
				String pass = CommonUtility.getAlphaNumericString(7);
				// System.out.println("pass");
				// int res = staffrepo.chagePass(pass, OTPVerification.getUserId());

				user = userRepo.findById(OTPVerification.getUserId());
			//	System.out.println("user---------" + user);
				hashMap.put(1, user);

			} else {
				info.setError(true);
				info.setMessage("failed");
			}

		} catch (Exception e) {

			System.err.println("Exce in verifyOTP " + e.getMessage());
			e.printStackTrace();
			info.setError(true);
			info.setMessage("excep");
		}

		return user;

	}

	@RequestMapping(value = { "/updateToNewPassword" }, method = RequestMethod.POST)
	public @ResponseBody Info updateToNewPassword(@RequestParam int userId, @RequestParam String newPass) {

		Info res = new Info();

		int a = userRepo.changePassword(userId, newPass);
		if (a > 0) {

			mailsubject = " New Credentials ";
			String text = "\n Your new username and password are : \n";

			User usr = new User();
			usr = userRepo.findById(userId);
			if (usr != null) {
				String emailId = usr.getEmail();
				String password = "\n Username : " + usr.getUsername() + " \n Password : " + usr.getPassword();

				Info emailRes = EmailUtility.sendEmail(senderEmail, senderPassword, emailId, mailsubject, text,
						password);
			}
			res.setError(false);
			res.setMessage("success");
		} else {
			res.setError(true);
			res.setMessage("fail");
		}

		return res;
	}
}

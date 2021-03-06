package com.ats.webapi.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ats.webapi.commons.ApiConstants;
import com.ats.webapi.model.Info;

import javassist.bytecode.stackmap.BasicBlock.Catch;

@RestController
public class ImageUploadController {

	/*
	 * private static String SUGGESTION_URL =
	 * "/opt/apache-tomcat-8.5.37/webapps/uploadspune/SUGGESTION/"; private static
	 * String COMPLAINT_URL =
	 * "/opt/apache-tomcat-8.5.37/webapps/uploadspune/COMPLAINT/"; private static
	 * String NOTIFICATION_URL =
	 * "/opt/apache-tomcat-8.5.37/webapps/uploadspune/NOTIFICATION/"; private static
	 * String FEEDBACK_URL =
	 * "/opt/apache-tomcat-8.5.37/webapps/uploadspune/FEEDBACK/";
	 * 
	 * public static final String M_SP_CAKE_FOLDER =
	 * "/opt/apache-tomcat-8.5.37/webapps/uploadspune/MSPCAKE/"; public static final
	 * String MSG_FOLDER = "/opt/apache-tomcat-8.5.37/webapps/uploadspune/MSG/";
	 * public static final String FR_FOLDER =
	 * "/opt/apache-tomcat-8.5.37/webapps/uploadspune/FR/"; public static final
	 * String ITEM_FOLDER = "/opt/apache-tomcat-8.5.37/webapps/uploadspune/ITEM/";
	 * public static final String RAW_MAT_IMAGE_FOLDER =
	 * "/opt/apache-tomcat-8.5.37/webapps/uploadspune/RAWMAT/"; public static final
	 * String GATE_ENTRY_IMAGE_FOLDER =
	 * "/opt/apache-tomcat-8.5.37/webapps/uploadspune/GATEENTRY/"; private static
	 * final String SP_CAKE_FOLDER =
	 * "/opt/apache-tomcat-8.5.37/webapps/uploadspune/SPCAKE/"; private static final
	 * String CUST_CHOICE_PHOTO_CAKE_FOLDER =
	 * "/opt/apache-tomcat-8.5.37/webapps/uploadspune/CUSTCHOICEPHOTOCAKE/";
	 */
//public static final String fileUploadPath = "/opt/cpanel/ea-tomcat85/webapps/uploads/baroda/";
	//public static final String fileUploadPath = "/home/lenovo/AkhileshWorkspace/MOBILEUPLOADS/"; 
		
	//public static final String fileUploadPath = "/opt/cpanel/ea-tomcat85/webapps/uploads/kolhapur/";
	
	
	private static String SUGGESTION_URL = ApiConstants.fileUploadPath + "SUGGESTION/";
	private static String COMPLAINT_URL = ApiConstants.fileUploadPath + "COMPLAINT/";
	private static String NOTIFICATION_URL = ApiConstants.fileUploadPath + "NOTIFICATION/";
	private static String FEEDBACK_URL = ApiConstants.fileUploadPath + "FEEDBACK/";

	public static final String M_SP_CAKE_FOLDER = ApiConstants.fileUploadPath + "MSPCAKE/";
	public static final String MSG_FOLDER = ApiConstants.fileUploadPath + "MSG/";
//	public static final String FR_FOLDER = fileUploadPath + "FR/";
//	public static final String ITEM_FOLDER = fileUploadPath + "ITEM/";
	public static final String RAW_MAT_IMAGE_FOLDER = ApiConstants.fileUploadPath + "RAWMAT/";
	public static final String GATE_ENTRY_IMAGE_FOLDER = ApiConstants.fileUploadPath + "GATEENTRY/";
	private static final String SP_CAKE_FOLDER = ApiConstants.fileUploadPath + "SPCAKE/";
	private static final String CUST_CHOICE_PHOTO_CAKE_FOLDER = ApiConstants.fileUploadPath + "CUSTCHOICEPHOTOCAKE/";
	public static final String M_UPLOAD_CAKE_FOLDER = ApiConstants.fileUploadPath + "MOBILEUPLOADS/";
	// private static String BILL_FOLDER ="/home/maxadmin/Desktop/photos/";

	public static final String FR_FOLDER = ApiConstants.fileUploadPath;
	public static final String ITEM_FOLDER = ApiConstants.fileUploadPath;
	
	@RequestMapping(value = { "/photoUpload" }, method = RequestMethod.POST)
	public @ResponseBody Info getFarmerContract(@RequestParam("file") MultipartFile uploadfile,
			@RequestParam("imageName") String imageName, @RequestParam("type") String type) {

		Info info = new Info();
		System.out.println("File Name " + uploadfile.getOriginalFilename());
		String a=	imageName.replaceAll("^\"|\"$", "");//Akhilesh ," " In Image Name Problm 2021-03-11
		System.out.println("imageName Name1 " +a);
		
		
		
		try {
			//saveUploadedFiles(Arrays.asList(uploadfile), imageName, type);//Akhilesh ," " In Image Name Problm 2021-03-11
			saveUploadedFiles(Arrays.asList(uploadfile), a, type);

			//saveUploadedFiles(Arrays.asList(uploadfile), imageName, type);

			info.setError(false);
			info.setMessage("File uploaded successfully");

		} catch (IOException e) {

			e.printStackTrace();
			info.setError(true);
			info.setMessage("File upload failed");
		}

		return info;
	}
	
	
	
	@RequestMapping(value = { "/photoUploadServ" }, method = RequestMethod.POST)
	public @ResponseBody Info photoUploadServ(@RequestParam("file") List<MultipartFile> uploadfile,
			@RequestParam("imageName") List<String> imageName, @RequestParam("type") String type) {

		Info info = new Info();
		//System.out.println("File Name " + uploadfile.getOriginalFilename());
	//	String a=	imageName.replaceAll("^\"|\"$", "");//Akhilesh ," " In Image Name Problm 2021-03-11
		//System.out.println("imageName Name1 " +a);
		
		
		
		try {
			
			for(int i=0;i<uploadfile.size();i++) {
				String a=	imageName.get(i).replaceAll("^\"|\"$", "");
				saveUploadedFiles(Arrays.asList(uploadfile.get(i)), a, type);	
		}
			//saveUploadedFiles(Arrays.asList(uploadfile), imageName, type);//Akhilesh ," " In Image Name Problm 2021-03-11
		//	saveUploadedFiles(Arrays.asList(uploadfile), a, type);

			//saveUploadedFiles(Arrays.asList(uploadfile), imageName, type);

			info.setError(false);
			info.setMessage("File uploaded successfully");

		} catch (IOException e) {

			e.printStackTrace();
			info.setError(true);
			info.setMessage("File upload failed");
		}

		return info;
	}

	// save file
	private void saveUploadedFiles(List<MultipartFile> files, String imageName, String type) throws IOException {

		try {
			for (MultipartFile file : files) {
				Path path = null;
				if (file.isEmpty()) {
					continue;
				}
				if (type.equalsIgnoreCase("s")) {
					path = Paths.get(SUGGESTION_URL + imageName);
				} else if (type.equalsIgnoreCase("c")) {
					path = Paths.get(COMPLAINT_URL + imageName);

				} else if (type.equalsIgnoreCase("nf")) {
					path = Paths.get(NOTIFICATION_URL + imageName);

				} else if (type.equalsIgnoreCase("f")) {
					path = Paths.get(FEEDBACK_URL + imageName);

				} else if (type.equalsIgnoreCase("sp")) {
					path = Paths.get(M_SP_CAKE_FOLDER + imageName);

				} else if (type.equalsIgnoreCase("fr")) {
					path = Paths.get(FR_FOLDER + imageName);

				} else if (type.equalsIgnoreCase("item")) {
					path = Paths.get(ITEM_FOLDER + imageName);

				} else if (type.equalsIgnoreCase("ph1")) {
					path = Paths.get(SP_CAKE_FOLDER + imageName);

				} else if (type.equalsIgnoreCase("ph2")) {
					path = Paths.get(CUST_CHOICE_PHOTO_CAKE_FOLDER + imageName);

				} else if (type.equalsIgnoreCase("msg")) {
					path = Paths.get(MSG_FOLDER + imageName);

				}
				else if (type.equalsIgnoreCase("mu")) {
					path = Paths.get(M_UPLOAD_CAKE_FOLDER + imageName);

				}
				byte[] bytes = file.getBytes();

				Files.write(path, bytes);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

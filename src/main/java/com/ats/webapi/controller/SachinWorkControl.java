package com.ats.webapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.FlavourConf;
import com.ats.webapi.model.Info;
import com.ats.webapi.model.newsetting.NewSetting;
import com.ats.webapi.repository.FlavourConfRepository;
import com.ats.webapi.repository.FlavourRepository;
import com.ats.webapi.repository.NewSettingRepository;

@RestController
public class SachinWorkControl {

	@Autowired NewSettingRepository newSettRepo;
	
	@RequestMapping(value = { "/getNewSettingByKey" }, method = RequestMethod.POST)
	public @ResponseBody NewSetting getNewSettingByKey(@RequestParam String settingKey,
			@RequestParam int delStatus) {
		NewSetting sett=new NewSetting();
		
		sett=newSettRepo.findBySettingKeyAndDelStatus(settingKey, delStatus);
		return sett;
	}

	@Autowired
	FlavourRepository flavourRepository;
	@RequestMapping(value = { "/saveFlavourConf" }, method = RequestMethod.POST)
	public @ResponseBody List<FlavourConf> saveFlavourConf(@RequestBody List<FlavourConf> flavourConfList) {

		List<FlavourConf> flList = new ArrayList<FlavourConf>();
		try {
			for (FlavourConf flavourConf : flavourConfList) {
				FlavourConf isPresent = flavourConfRepository.findByDelStatusAndSpfIdAndSpId(0, flavourConf.getSpfId(),
						flavourConf.getSpId());
				if (isPresent != null) {
					flavourConf.setSpFlavConfId(isPresent.getSpFlavConfId());
					FlavourConf flr = flavourConfRepository.save(flavourConf);
					flList.add(flr);
				} else {
					FlavourConf flr = flavourConfRepository.save(flavourConf);
					flList.add(flr);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return flList;
	}

	@Autowired
	FlavourConfRepository flavourConfRepository;
	
	@RequestMapping(value = "/updateFlavourConf", method = RequestMethod.POST)
	public Info updateFlavourConf(@RequestParam("spFlavConfId") int spFlavConfId, @RequestParam("rate") float rate,
			@RequestParam("mrp1") float mrp1, @RequestParam("mrp2") float mrp2, @RequestParam("mrp3") float mrp3) {
		Info info = new Info();
		try {
			int isUpdated = flavourConfRepository.updateFlavourConf(spFlavConfId, rate, mrp1, mrp2, mrp3);
			if (isUpdated > 0) {
				info.setError(false);
				info.setMessage("FlavourConf Updated Successfully.");
			} else {
				info.setError(true);
				info.setMessage("FlavourConf Updation Failed.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return info;
	}

	@RequestMapping(value = "/deleteFlavourConf", method = RequestMethod.POST)
	public @ResponseBody Info deleteFlavourConf(@RequestParam int spFlavConfId) {

		Info info = new Info();
		int isDelete = flavourConfRepository.deleteBySpFlavConfId(spFlavConfId);

		if (isDelete != 0) {
			info.setError(false);
			info.setMessage("Success");
		} else {
			info.setError(true);
			info.setMessage("Fail");
		}
		return info;

	}
	
}
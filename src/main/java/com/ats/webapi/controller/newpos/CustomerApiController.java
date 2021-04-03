package com.ats.webapi.controller.newpos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.CustomerForOps;
import com.ats.webapi.model.PaymentMode;
import com.ats.webapi.model.PaymentType;
import com.ats.webapi.model.newpos.Customer;
import com.ats.webapi.repo.CustomerRepo;
import com.ats.webapi.repo.PaymentModeRepository;
import com.ats.webapi.repository.CustomerForOpsRepo;
import com.ats.webapi.repository.PaymentTypeRepository;
import com.ats.webapi.repository.newpos.CustomerRepository;

@RestController
public class CustomerApiController {

	@Autowired
	CustomerRepository custRepo;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	PaymentModeRepository paymentModeRepository;

	@Autowired
	PaymentTypeRepository paymentTypeRepository;
	
	@RequestMapping(value = "/getAllCustomer", method = RequestMethod.GET)
	public @ResponseBody List<Customer> getAllCustomer() {
		List<Customer> customerList = new ArrayList<Customer>();
		try {
			customerList = custRepo.getAllCustomer();

			String sql = "SELECT * FROM CUSTOMER WHERE ID = ?";

		} catch (Exception e) {
			customerList = new ArrayList<Customer>();
			// TODO: handle exception
			e.printStackTrace();
		}
		return customerList;
	}

	@RequestMapping(value = "/getAllCustomerForPos", method = RequestMethod.POST)
	public @ResponseBody List<Customer> getAllCustomerForPos(@RequestParam("frId") int frId) {
		List<Customer> customerList = new ArrayList<Customer>();
		try {
			System.out.println(frId);
			customerList = custRepo.getAllCustomer(frId);

		} catch (Exception e) {
			customerList = new ArrayList<Customer>();
			// TODO: handle exception
			e.printStackTrace();
		}
		return customerList;
	}

	@Autowired
	CustomerForOpsRepo customerForOpsRepo;

	@RequestMapping(value = { "/getAllCustomersForOps" }, method = RequestMethod.GET)
	public @ResponseBody List<CustomerForOps> getAllCustomers() {
		List<CustomerForOps> servicsList = new ArrayList<CustomerForOps>();
		try {
			servicsList = customerForOpsRepo.findByDelStatusOrderByCustIdDesc(0);
		} catch (Exception e) {
			// System.err.println("Exce in getAllServices " + e.getMessage());
		}
		return servicsList;
	}

	@RequestMapping(value = { "/saveCustomerForOps" }, method = RequestMethod.POST)
	public @ResponseBody CustomerForOps saveCustomer(@RequestBody CustomerForOps service) {

		CustomerForOps serv = new CustomerForOps();

		int id = service.getCustId();

		try {
			serv = customerForOpsRepo.saveAndFlush(service);

		} catch (Exception e) {
			// System.err.println("Exce in saving saveCustomer " + e.getMessage());
			e.printStackTrace();

		}
		return serv;
	}

	@RequestMapping(value = { "/getPaymentModeList" }, method = RequestMethod.GET)
	public @ResponseBody List<PaymentMode> getPaymentModeList() {

		List<PaymentMode> serv = new ArrayList<>();

		try {
			serv = paymentModeRepository.findByDelStatus(1);

		} catch (Exception e) {
			// System.err.println("Exce in saving saveCustomer " + e.getMessage());
			e.printStackTrace();

		}
		return serv;
	}

	@RequestMapping(value = { "/getPaymentTypeList" }, method = RequestMethod.POST)
	public @ResponseBody List<PaymentType> getPaymentTypeList(@RequestParam("modeId") int modeId) {

		List<PaymentType> serv = new ArrayList<>();

		try {
			serv = paymentTypeRepository.findByDelStatusAndPaymentModeId(1,modeId);

		} catch (Exception e) {
			// System.err.println("Exce in saving saveCustomer " + e.getMessage());
			e.printStackTrace();

		}
		return serv;
	}

}

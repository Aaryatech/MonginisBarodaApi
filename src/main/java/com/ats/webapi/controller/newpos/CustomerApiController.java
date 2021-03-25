package com.ats.webapi.controller.newpos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.newpos.Customer;
import com.ats.webapi.repository.newpos.CustomerRepository;

@RestController
public class CustomerApiController {

	@Autowired
	CustomerRepository custRepo;

	@Autowired
	private JdbcTemplate jdbcTemplate;

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

}

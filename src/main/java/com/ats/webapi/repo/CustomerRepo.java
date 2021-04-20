package com.ats.webapi.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {

	@Query(value="SELECT * FROM  m_customer WHERE del_status=:i ORDER BY cust_id DESC ",nativeQuery=true)
	List<Customer> findByDelStatusOrderByCustIdDesc(@Param("i")  int i);

	@Query(value="SELECT * FROM  m_customer WHERE cust_id=:custId AND del_status=:i ",nativeQuery=true)
	Customer findByCustIdAndDelStatus(@Param("custId")int custId,@Param("i") int i);

 	
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_customer SET del_status=1  WHERE cust_id=:custId",nativeQuery=true)
	int deleteCustomer(@Param("custId") int custId);

	List<Customer> findByFrIdAndDelStatus(int frId, int i);
	@Query(value="select * from m_customer where phone_number=:phoneNo  and del_status=:i",nativeQuery=true)
	List<Customer> findByPhoneNumberAndDelStatus(@Param("phoneNo")String phoneNo,@Param("i") int i);
	
	
	
	@Query(value="select * from m_customer where phone_number=:phoneNo and fr_id=:frId  and del_status=:i",nativeQuery=true)
	List<Customer>	findByPhoneNumberFridAndDelStatus(@Param("phoneNo")String phoneNo,@Param("i") int i,@Param("frId") int frId );

	@Transactional
	@Modifying
	@Query(value="UPDATE m_customer SET address=:address, ex_var1=:km  WHERE cust_id=:custId",nativeQuery=true)
	int updateAddressAndKm(@Param("address") String address,@Param("km") String km,@Param("custId") int custId);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_customer SET address=:address, ex_var1=:km, ex_var2=:pincode  WHERE cust_id=:custId",nativeQuery=true)
	int updateAddressAndKmAndPincode(@Param("address") String address,@Param("km") String km,@Param("pincode") String pincode,@Param("custId") int custId);
}

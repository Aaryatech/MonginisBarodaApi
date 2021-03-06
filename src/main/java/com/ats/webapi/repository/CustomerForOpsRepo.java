package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.Customer;
import com.ats.webapi.model.CustomerForOps;

public interface CustomerForOpsRepo extends JpaRepository<CustomerForOps, Integer> {

	List<CustomerForOps> findByDelStatusOrderByCustName(int i);

	CustomerForOps findByCustIdAndDelStatus(int custId, int i);
	
	@Query(value="SELECT * FROM m_customer1 WHERE del_status=0 AND (fr_id=:frId or fr_id=0) ORDER BY cust_name ",nativeQuery=true)
	List<CustomerForOps> findByDelStatusAndFrid(@Param("frId") int frId);
	
	@Query(value="select * from m_customer1 where phone_number=:phoneNo and fr_id=:frId  and del_status=:i",nativeQuery=true)
	List<CustomerForOps>	findByPhoneNumberFridAndDelStatus(@Param("phoneNo")String phoneNo,@Param("i") int i,@Param("frId") int frId );
	
	
	@Query(value="SELECT * FROM  m_customer1 WHERE cust_id=:custId AND del_status=:i",nativeQuery=true)
	CustomerForOps getCustById(@Param("custId")int custId,@Param("i") int i);
	

}

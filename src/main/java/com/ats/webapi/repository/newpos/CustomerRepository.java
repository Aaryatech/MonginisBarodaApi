package com.ats.webapi.repository.newpos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.newpos.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

	@Query(value = "SELECT uuid() AS id, a.* FROM (\n"
			+ "SELECT user_name AS user_name,user_gst_no AS gst_no ,user_phone AS phone_no FROM t_sell_bill_header GROUP BY user_phone UNION ALL SELECT sp_cust_name AS user_name, cust_gstin AS gst_no,sp_cust_mob_no AS phone_no FROM t_sp_cake  GROUP BY  sp_cust_mob_no) a WHERE a.user_name != '' GROUP BY a.phone_no ORDER BY  a.user_name ASC", nativeQuery = true)
	List<Customer> getAllCustomer();

	@Query(value = "SELECT\n"
			+ "     uuid() AS id,\n"
			+ "     a.* \n"
			+ " FROM\n"
			+ "     ( SELECT\n"
			+ "         user_name AS user_name,\n"
			+ "         user_gst_no AS gst_no ,\n"
			+ "         user_phone AS phone_no \n"
			+ "     FROM\n"
			+ "         t_sell_bill_header where fr_id=:frId\n"
			+ "     GROUP BY\n"
			+ "         user_phone \n"
			+ "     UNION\n"
			+ "     ALL SELECT\n"
			+ "         sp_cust_name AS user_name,\n"
			+ "         cust_gstin AS gst_no,\n"
			+ "         sp_cust_mob_no AS phone_no \n"
			+ "     FROM\n"
			+ "         t_sp_cake  where fr_id=:frId\n"
			+ "     GROUP BY\n"
			+ "         sp_cust_mob_no\n"
			+ " ) a \n"
			+ "WHERE\n"
			+ " a.user_name != '' \n"
			+ "GROUP BY\n"
			+ " a.phone_no \n"
			+ "ORDER BY\n"
			+ " a.user_name ASC", nativeQuery = true) 
	List<Customer> getAllCustomer(@Param("frId") int frId);

}

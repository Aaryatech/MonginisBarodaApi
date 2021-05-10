package com.ats.webapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.webapi.model.ShowPaymentType;

public interface ShowPaymentTypeRepo extends JpaRepository<ShowPaymentType, Integer>{
	@Query(value="SELECT\n" + 
			"    t.payment_type_id,\n" + 
			"    t.type_name,\n" + 
			"    m.mode_name\n" + 
			"FROM\n" + 
			"    m_payment_type t,\n" + 
			"    m_payment_mode m\n" + 
			"WHERE\n" + 
			"    t.payment_mode_id=m.mode_id \n" + 
			"    AND t.del_status=0\n" + 
			"ORDER BY t.payment_type_id DESC\n" + 
			"    ",nativeQuery=true)	
	List<ShowPaymentType> showType();
}

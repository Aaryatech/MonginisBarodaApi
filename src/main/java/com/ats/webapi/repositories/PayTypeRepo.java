package com.ats.webapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.webapi.model.PaymentType;

public interface PayTypeRepo extends JpaRepository<PaymentType, Integer> {
	@Query(value="SELECT * FROM m_payment_type WHERE del_status=0",nativeQuery=true)
	List<PaymentType> getAllPaymentTypeByDelStatus();

	@Transactional
	@Modifying
	@Query(value="UPDATE m_payment_type r SET r.del_status=1  WHERE r.payment_type_id =:typeId",nativeQuery=true)
	public int deleteById(@Param("typeId") Integer typeId);
	
	@Query(value="SELECT * FROM m_payment_type WHERE payment_type_id =:typeId",nativeQuery=true)	
	PaymentType getAllPaymentTypeById(@Param("typeId") Integer typeId);
	
}

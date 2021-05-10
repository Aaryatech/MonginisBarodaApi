package com.ats.webapi.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.PaymentMode;

public interface PayModeRepo extends JpaRepository<PaymentMode, Integer> {
	
	@Query(value="SELECT * FROM m_payment_mode WHERE del_status=0",nativeQuery=true)	
	List<PaymentMode> getAllPaymentModeByDelStatus();

	@Transactional
	@Modifying
	@Query(value="UPDATE m_payment_mode r SET r.del_status=1  WHERE r.mode_id =:modeId",nativeQuery=true)
	public int deleteById(@Param("modeId") Integer modeId);
	
	@Query(value="SELECT * FROM m_payment_mode WHERE mode_id =:modeId",nativeQuery=true)	
	PaymentMode getAllPaymentModeById(@Param("modeId") Integer modeId);
}

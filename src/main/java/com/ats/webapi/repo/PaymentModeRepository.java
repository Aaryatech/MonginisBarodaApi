package com.ats.webapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.webapi.model.PaymentMode;

public interface PaymentModeRepository extends JpaRepository<PaymentMode, Integer> {

	List<PaymentMode> findByDelStatus(int i);

	

}

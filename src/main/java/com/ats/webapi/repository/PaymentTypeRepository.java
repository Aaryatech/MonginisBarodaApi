package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.webapi.model.PaymentType;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Integer> {

	List<PaymentType> findByDelStatusAndPaymentModeId(int i, int modeId);

}

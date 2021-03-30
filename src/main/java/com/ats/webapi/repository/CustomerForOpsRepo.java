package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.webapi.model.CustomerForOps;

public interface CustomerForOpsRepo extends JpaRepository<CustomerForOps, Integer> {

	List<CustomerForOps> findByDelStatusOrderByCustIdDesc(int i);

}

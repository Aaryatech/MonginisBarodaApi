package com.ats.webapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.webapi.model.RouteAbcVal;

public interface RouteAbcValRepo extends JpaRepository<RouteAbcVal, Integer> {

	List<RouteAbcVal> findByDelStatusOrderByAbcIdAsc(int i);

}

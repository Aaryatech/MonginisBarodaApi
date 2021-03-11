package com.ats.webapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.webapi.model.State;


public interface StateRepository extends JpaRepository<State, Integer> {

	State findByStateId(int stateId);

	List<State> findAllByIsUsed(int i);

	@Transactional
	@Modifying
	@Query("UPDATE State SET isUsed=0  WHERE state_id=:stateId")
	int deleteState(@Param("stateId") int stateId);

}

package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ats.webapi.model.FlavourConf;

@Repository
public interface FlavourConfRepository extends JpaRepository<FlavourConf, Integer>{

	FlavourConf findByDelStatusAndSpfIdAndSpId(int i, int spfId, int spId);

	List<FlavourConf> findByDelStatus(int i);

	FlavourConf findBySpIdAndSpfIdAndDelStatus(int spId, int spfId, int i);

	@Transactional
	@Modifying
	@Query(" UPDATE FlavourConf  SET rate=:rate ,mrp=:mrp  WHERE  flavId=:flavId ")
	int updateFlavourConf(@Param("flavId")int flavId,@Param("rate") float rate,@Param("mrp") float mrp);

	/*
	 * @Transactional int deleteByFlavId(@Param("flavId")int flavId);
	 */

	@Transactional
	@Modifying
	@Query(" UPDATE FlavourConf  SET rate=:rate ,mrp1=:mrp1,mrp2=:mrp2,mrp3=:mrp3  WHERE  spFlavConfId=:spFlavConfId ")
	int updateFlavourConf(@Param("spFlavConfId")int spFlavConfId,@Param("rate") float rate,
			@Param("mrp1") float mrp1,@Param("mrp2") float mrp2,@Param("mrp3") float mrp3);

	@Transactional
	@Modifying
	int deleteBySpFlavConfId(@Param("spFlavConfId")int spFlavConfId);
	
	
}

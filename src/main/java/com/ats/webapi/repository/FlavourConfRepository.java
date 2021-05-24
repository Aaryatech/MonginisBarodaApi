package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ats.webapi.model.FlavourConf;
import com.ats.webapi.model.SpCakeOrders;

@Repository
public interface FlavourConfRepository extends JpaRepository<FlavourConf, Integer>{

	FlavourConf findByDelStatusAndSpfIdAndSpId(int i, int spfId, int spId);

	List<FlavourConf> findByDelStatus(int i);
	
	@Query(value="SELECT\n" + 
			"    `sp_flav_conf_id`,\n" + 
			"    `spf_id`,\n" + 
			"    m_sp_flavour_conf.sp_id,\n" + 
			"    `sp_type`,\n" + 
			"    `rate`,\n" + 
			"    `mrp1`,\n" + 
			"    `mrp2`,\n" + 
			"    `mrp3`,\n" + 
			"   m_sp_flavour_conf.del_status,\n" + 
			"    m_spcake_sup.cut_section AS `ex_var1`,\n" + 
			"     `ex_int1`\n" + 
			"FROM\n" + 
			"    `m_sp_flavour_conf`,\n" + 
			"    m_spcake_sup\n" + 
			"WHERE\n" + 
			"  m_sp_flavour_conf.del_status=0\n" + 
			"  AND m_spcake_sup.sp_id=m_sp_flavour_conf.sp_id",nativeQuery=true)
	List<FlavourConf> findByDelStatusWithCakeType();

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
	
	
	@Query(value="SELECT\n" + 
			"    `sp_flav_conf_id`,\n" + 
			"   m_sp_flavour_conf.spf_id,\n" + 
			"    m_sp_flavour_conf.sp_id,\n" + 
			"    m_sp_flavour_conf.sp_type,\n" + 
			"    `rate`,\n" + 
			"    `mrp1`,\n" + 
			"    `mrp2`,\n" + 
			"    `mrp3`,\n" + 
			"    m_sp_flavour_conf.del_status,\n" + 
			"    CONCAT(\n" + 
			"        m_sp_cake.sp_name\n" + 
			"        ,\n" + 
			"        '+',\n" + 
			"        m_sp_flavour.spf_name\n" + 
			"    ) ex_var1,\n" + 
			"    `ex_int1`\n" + 
			"FROM\n" + 
			"    `m_sp_flavour_conf`,\n" + 
			"    m_sp_flavour,\n" + 
			"    m_sp_cake\n" + 
			"WHERE\n" + 
			"    m_sp_flavour_conf.spf_id IN(:flavIds) AND m_sp_flavour_conf.spf_id = m_sp_flavour.spf_id AND m_sp_cake.sp_id = m_sp_flavour_conf.sp_id",nativeQuery=true)
	List<FlavourConf> getSpByFlavours(@Param("flavIds")  List<String> flavIds);
	
	
	
	
	
	@Query(value="SELECT\n" + 
			"    `sp_flav_conf_id`,\n" + 
			"   m_sp_flavour_conf.spf_id,\n" + 
			"    m_sp_flavour_conf.sp_id,\n" + 
			"    m_sp_flavour_conf.sp_type,\n" + 
			"    `rate`,\n" + 
			"    `mrp1`,\n" + 
			"    `mrp2`,\n" + 
			"    `mrp3`,\n" + 
			"    m_sp_flavour_conf.del_status,\n" + 
			"    CONCAT(\n" + 
			"        m_sp_cake.sp_name\n" + 
			"        ,\n" + 
			"        '+',\n" + 
			"        m_sp_flavour.spf_name\n" + 
			"    ) ex_var1,\n" + 
			"    `ex_int1`\n" + 
			"FROM\n" + 
			"    `m_sp_flavour_conf`,\n" + 
			"    m_sp_flavour,\n" + 
			"    m_sp_cake\n" + 
			"WHERE\n" + 
			"    m_sp_flavour_conf.sp_id IN(:spIds) AND m_sp_flavour_conf.spf_id = m_sp_flavour.spf_id AND m_sp_cake.sp_id = m_sp_flavour_conf.sp_id",nativeQuery=true)
	List<FlavourConf> getSpBySpId(@Param("spIds")  List<String> spIds);
	
	@Query(value="SELECT\n" + 
			"    `sp_flav_conf_id`,\n" + 
			"    m_sp_flavour_conf.spf_id,\n" + 
			"    m_sp_flavour_conf.sp_id,\n" + 
			"    m_sp_flavour_conf.sp_type,\n" + 
			"    `rate`,\n" + 
			"    `mrp1`,\n" + 
			"    `mrp2`,\n" + 
			"    `mrp3`,\n" + 
			"    m_sp_flavour_conf.del_status,\n" + 
			"    m_sp_flavour.spf_name  AS ex_var1,\n" + 
			"    `ex_int1`\n" + 
			"FROM\n" + 
			"    `m_sp_flavour_conf`,\n" + 
			"    m_sp_flavour\n" + 
			"  \n" + 
			"WHERE\n" + 
			"    m_sp_flavour_conf.sp_id =:spId AND m_sp_flavour_conf.spf_id = m_sp_flavour.spf_id  ",nativeQuery=true)
	List<FlavourConf> getSpBySingleSpId(@Param("spId")  int spId);
	
	
	
	
	
}

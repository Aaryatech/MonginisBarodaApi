package com.ats.webapi.repository.logistics;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.webapi.model.logistics.VehicalMaster;

public interface VehicalMasterRepository extends JpaRepository<VehicalMaster, Integer> {

	@Transactional
	@Modifying
	@Query(" UPDATE VehicalMaster SET del_status=1 WHERE veh_id=:vehicalId")
	int deleteVehicalMaster(@Param("vehicalId") int vehicalId);

	List<VehicalMaster> findByDelStatus(int delStatus);

	VehicalMaster findByVehId(int vehicalId);

	/*
	 * @Query(
	 * value="select amc_id,mech_name,mech_id,dealer_name,dealer_id,type_id,amc_from_date,amc_to_date,amc_alert_date,DATEDIFF(amc_to_date,:today) AS remaining_day "
	 * +
	 * "from m_logis_amc where (amc_to_date >= :today and amc_alert_date <= :today) or amc_to_date < :today"
	 * ,nativeQuery=true) List<VehicalMaster> getAlertVehicleRecord();
	 */
	//Sachin 26-02-2021
	@Query(value = " select m_logis_vehical.*"
			+ " from m_logis_vehical,m_franchisee where m_franchisee.kg_1=m_logis_vehical.veh_id and "
			+ " m_franchisee.fr_id=:frId ", nativeQuery = true)
	VehicalMaster getVehicleByFrId(@Param("frId") int frId);
	
	
	@Query(value="SELECT\r\n" + 
			"    veh_id,\r\n" + 
			"    veh_no,\r\n" + 
			"    make_id,\r\n" + 
			"    veh_eng_no,\r\n" + 
			"    veh_chesi_no,\r\n" + 
			"  GROUP_CONCAT( m_franchisee.fr_name) As  veh_color,\r\n" + 
			"    purchase_date,\r\n" + 
			"    reg_date,\r\n" + 
			"    dealer_id,\r\n" + 
			"    fuel_type,\r\n" + 
			"    veh_type_id,\r\n" + 
			"    variant_id,\r\n" + 
			"    veh_comp_avg,\r\n" + 
			"    veh_stand_avg,\r\n" + 
			"    veh_mini_avg,\r\n" + 
			"    m_logis_vehical.del_status,\r\n" + 
			"    m_logis_vehical.int1,\r\n" + 
			"    m_logis_vehical.int2,\r\n" + 
			"    varchar1,\r\n" + 
			"    varchar2,\r\n" + 
			"    boolean1,\r\n" + 
			"    freq_km,\r\n" + 
			"    wheel_change_freq,\r\n" + 
			"    battary_change_freq,\r\n" + 
			"    ac_change_freq,\r\n" + 
			"    current_running_km,\r\n" + 
			"    last_servicing_km,\r\n" + 
			"    next_servicing_km,\r\n" + 
			"    alert_next_servicing_km,\r\n" + 
			"    last_amc_date,\r\n" + 
			"    next_amc_date,\r\n" + 
			"    alert_amc_date\r\n" + 
			"   \r\n" + 
			"FROM\r\n" + 
			"    m_logis_vehical,\r\n" + 
			"    m_franchisee\r\n" + 
			"WHERE\r\n" + 
			"m_logis_vehical.del_status=0 AND \r\n" + 
			"  m_logis_vehical.veh_id=m_franchisee.kg_1\r\n" + 
			"  GROUP BY m_logis_vehical.veh_id",nativeQuery=true)
	List<VehicalMaster> getAllVehicleWithFranchiseeName();
	
	@Transactional
	@Modifying
	@Query(value="UPDATE m_logis_vehical SET  del_status=1 WHERE veh_id IN(:vehIds)  ",nativeQuery=true)
	int deleteMultipleVehicle(@Param("vehIds") List<String> vehIds);
	
	
	
	
	

}

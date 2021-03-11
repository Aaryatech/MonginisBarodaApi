package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.GetFranchiseSup;

@Repository
public interface GetFranchiseSupRepository extends JpaRepository<GetFranchiseSup, Integer>{

	@Query(value=" Select\n" + 
			"        s.id,\n" + 
			"        s.fr_id,\n" + 
			"        CONCAT(f.fr_name,'-', f.fr_code) AS fr_name,\n" + 
			"        s.fr_pan_no,\n" + 
			"        CONCAT(state.state_name,'-',state.state_code) AS fr_state,\n" + 
			"        s.fr_country,\n" + 
			"        s.no_in_route,\n" + 
			"        s.pest_control_date,\n" + 
			"        s.frequency,\n" + 
			"        s.remainder_date,\n" + 
			"        s.pass1,\n" + 
			"        s.pass2,\n" + 
			"        s.pass3,\n" + 
			"        s.pass4,\n" + 
			"        s.pass5,\n" + 
			"        s.del_status,\n" + 
			"        s.is_tally_sync \n" + 
			"    from\n" + 
			"        m_franchise_sup s,\n" + 
			"        m_franchisee f,\n" + 
			"        m_state state\n" + 
			"    where\n" + 
			"        f.fr_id=s.fr_id \n" + 
			"        and s.del_status=0 AND\n" + 
			"        s.fr_state=state.state_id\n" + 
			"    order by\n" + 
			"        f.fr_name Asc",nativeQuery=true)
	 List<GetFranchiseSup> findByDelStatus();
	//@Query(value="Select s.id,s.fr_id,f.fr_name,s.fr_pan_no,s.fr_state,s.fr_country,s.no_in_route,s.pest_control_date,s.frequency,s.remainder_date,s.pass1,s.pass2,s.pass3,s.pass4,s.pass5,s.del_status,s.is_tally_sync from m_franchise_sup s,m_franchisee f where f.fr_id=s.fr_id and s.del_status=0 order by f.fr_name Asc",nativeQuery=true)
	
}

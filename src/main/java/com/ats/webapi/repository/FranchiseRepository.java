package com.ats.webapi.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.Franchise;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Integer> {

	@Query(value="select f.*,r.route_name from m_franchisee f,m_fr_route r where f.fr_route_id=r.route_id And f.fr_code=:frCode And f.del_status=:delStatus",nativeQuery=true)
	public Franchise findByFrCodeAndDelStatus(@Param("frCode")String frCode,@Param("delStatus") int delStatus);

	@Transactional
	@Modifying
	@Query(value="UPDATE m_franchisee SET fr_password_key=:token WHERE fr_code=:frCode",nativeQuery=true)
	int updateFranchiseeToken(@Param("frCode") String frCode,@Param("token") String token ); 



}

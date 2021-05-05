package com.ats.webapi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.webapi.model.SpCakeOrders;


public interface SpCakeOrdersRepository extends JpaRepository<SpCakeOrders,Integer>{
	
	
	
	SpCakeOrders save(SpCakeOrders spCakeOrders);
	
	List<SpCakeOrders> findByFrIdInAndSpProdDate(List<Integer> frId, Date prodDate);
	
	@Query(value="SELECT\n" + 
			"    sp_order_no,\n" + 
			"    sp_id,\n" + 
			"    fr_id,\n" + 
			"    fr_code,\n" + 
			"    item_id,\n" + 
			"    menu_id,\n" + 
			"     t_sp_cake.sp_type,\n" + 
			"    sp_flavour_id,\n" + 
			"    sp_total_add_rate,\n" + 
			"    sp_selected_weight,\n" + 
			"    sp_backend_rate,\n" + 
			"    sp_delivery_place,\n" + 
			"    sp_min_weight,\n" + 
			"    sp_max_weight,\n" + 
			"    sp_prod_time,\n" + 
			"    sp_est_deli_date,\n" + 
			"    sp_prod_date,\n" + 
			"    sp_events,\n" + 
			"    sp_events_name,\n" + 
			"    sp_instructions,\n" + 
			"    sp_delivery_date,\n" + 
			"    sp_cust_name,\n" + 
			"    sp_cust_dob,\n" + 
			"    sp_cust_mob_no,\n" + 
			"    sp_booked_for_name,\n" + 
			"    sp_book_for_dob,\n" + 
			"    sp_book_for_mob_no,\n" + 
			"    sp_grand_total,\n" + 
			"    sp_price,\n" + 
			"    sp_sub_total,\n" + 
			"    sp_advance,\n" + 
			"    rm_amount,\n" + 
			"    tax_1,\n" + 
			"    tax_2,\n" + 
			"    tax_1_amt,\n" + 
			"    tax_2_amt,\n" + 
			"    order_photo,\n" + 
			"    order_date,\n" + 
			"    order_photo2,\n" + 
			"    is_slot_used,\n" + 
			"    is_bill_generated,\n" + 
			"    is_allocated,\n" + 
			"    t_sp_cake.del_status,\n" + 
			"    extra_charges,\n" + 
			"    disc,\n" + 
			"    ex_int1,\n" + 
			"    ex_int2,\n" + 
			"   m_sp_flavour.spf_name  AS ex_var1,\n" + 
			"    ex_var2,\n" + 
			"    cust_gstin,\n" + 
			"    cust_email,\n" + 
			"    slip_no\n" + 
			"FROM\n" + 
			"    t_sp_cake,\n" + 
			"    m_sp_flavour\n" + 
			"WHERE\n" + 
			"    t_sp_cake.sp_flavour_id IN(:flavIds) AND t_sp_cake.fr_id = :frId AND m_sp_flavour.spf_id=t_sp_cake.sp_flavour_id AND t_sp_cake.del_status=0",nativeQuery=true)
	List<SpCakeOrders> getSpOrderByFlavours(@Param("flavIds")  List<String> flavIds,@Param("frId") int frId);
	
	
	@Query(value="select count(*) from t_sp_cake where t_sp_cake.del_status=0 and  sp_prod_date=:sqlSpProduDate AND is_slot_used=1",nativeQuery=true)
	int findCountByProduDateAndIsSlotUsed(@Param("sqlSpProduDate")String sqlSpProduDate);
	

	@Transactional
	@Modifying	
	@Query("UPDATE SpCakeOrders t SET t.isBillGenerated =:status,t.slipNo=:slipNo  WHERE t.spOrderNo=:orderId")
	int updateSpBillStatusMul(@Param("orderId") int orderId,@Param("status") int status,@Param("slipNo") String slipNo);

	
	@Transactional
	@Modifying	
	@Query("UPDATE SpCakeOrders t SET t.isBillGenerated =:status  WHERE t.spOrderNo=:orderId")
	int updateSpBillStatus(@Param("orderId") int orderId,@Param("status") int status);

	
	@Transactional
	@Modifying	
	@Query("UPDATE SpCakeOrders t SET t.isAllocated=1  WHERE t.spOrderNo=:gettSpCakeId")	
	int updateSpCkAllocDId(@Param("gettSpCakeId")int gettSpCakeId);

	SpCakeOrders findBySpOrderNo(int spOrderNo);

	@Transactional
	@Modifying	
	@Query("UPDATE SpCakeOrders t SET t.spBookForMobNo=:invoiceNo  WHERE t.spOrderNo=:spOrderNo")	
	int generateSpBillOps(@Param("spOrderNo")int spOrderNo,@Param("invoiceNo") String invoiceNo);
	



}

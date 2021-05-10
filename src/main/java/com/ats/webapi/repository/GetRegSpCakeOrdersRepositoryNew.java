package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.GetRegSpCakeOrders;
import com.ats.webapi.model.GetRegSpCakeOrdersNew;

public interface GetRegSpCakeOrdersRepositoryNew extends JpaRepository<GetRegSpCakeOrdersNew, Integer>{

	
	@Query(value="SELECT r.rsp_id, f.fr_name, f.fr_mob, t.item_name, r.order_date, r.mrp, r.qty, r.rsp_sub_total, r.rsp_advance_amt,"
			+" r.rsp_remaining_amt, r.rsp_delivery_dt,r.rsp_place, r.rsp_cust_name, r.rsp_cust_mobile_no from t_regular_sp_cake r, m_item t, m_franchisee f where"
			+" r.rsp_id IN (:orderNo) AND  r.del_status=0 AND r.fr_id=f.fr_id AND r.item_id=t.id",nativeQuery=true)
	
	List<GetRegSpCakeOrdersNew> getRegSpOrders(@Param("orderNo")List<String> orderNo);

	@Query(value="SELECT r.rsp_id, f.fr_name, f.fr_mob, t.item_name, r.order_date, r.mrp, r.qty, r.rsp_sub_total, r.rsp_advance_amt,\r\n" + 
			"r.rsp_remaining_amt, r.rsp_delivery_dt,r.rsp_place, r.rsp_cust_name, r.rsp_cust_mobile_no,m_fr_menu_show.menu_title,m_fr_configure.grn_per,m_fr_configure.disc_per from t_regular_sp_cake r, m_item t, m_franchisee f,m_fr_menu_show,m_fr_configure     where r.del_status=0 And r.rsp_delivery_dt=:spDeliveryDt and r.fr_id=:frId AND r.menu_id In(:catId) and r.del_status=0 AND r.fr_id=f.fr_id AND r.item_id=t.id AND r.menu_id=m_fr_menu_show.menu_id AND r.menu_id=m_fr_configure.menu_id",nativeQuery=true)
	List<GetRegSpCakeOrdersNew> findRegularCakeOrderHistory(@Param("spDeliveryDt") String spDeliveryDt, @Param("frId")int frId,@Param("catId")List<String> catId);

	
	@Query(value="SELECT r.rsp_id, f.fr_name, f.fr_mob, t.item_name, r.order_date, r.mrp, r.qty, r.rsp_sub_total, r.rsp_advance_amt,\r\n" + 
			"r.rsp_remaining_amt, r.rsp_delivery_dt,r.rsp_place, r.rsp_cust_name, r.rsp_cust_mobile_no,m_fr_menu_show.menu_title,m_fr_configure.grn_per,m_fr_configure.disc_per from t_regular_sp_cake r, m_item t, m_franchisee f , m_fr_menu_show,m_fr_configure  where r.del_status=0 And r.rsp_delivery_dt=:spDeliveryDt and r.fr_id=:frId AND r.del_status=0 AND r.fr_id=f.fr_id AND r.item_id=t.id AND r.menu_id=m_fr_menu_show.menu_id AND r.menu_id=m_fr_configure.menu_id",nativeQuery=true)
	List<GetRegSpCakeOrdersNew> findRegularCakeOrderHistoryAllMEnu(@Param("spDeliveryDt") String spDeliveryDt, @Param("frId")int frId);
	
	
	@Query(value="SELECT r.rsp_id, f.fr_name, f.fr_mob, t.item_name, r.order_date, r.mrp, r.qty, r.rsp_sub_total, r.rsp_advance_amt,\r\n" + 
			"r.rsp_remaining_amt, r.rsp_delivery_dt,r.rsp_place, r.rsp_cust_name, r.rsp_cust_mobile_no from t_regular_sp_cake r, m_item t, m_franchisee f where r.del_status=0 And   r.rsp_place=:orderNo AND  r.del_status=0 AND r.fr_id=f.fr_id AND r.item_id=t.id",nativeQuery=true)
	List<GetRegSpCakeOrdersNew> findByOrderNo(@Param("orderNo")String orderNo);

	@Query(value="SELECT r.rsp_id, f.fr_name, f.fr_mob, t.item_name, r.order_date, r.mrp, r.qty, r.rsp_sub_total, r.rsp_advance_amt,\r\n" + 
			"r.rsp_remaining_amt, r.rsp_delivery_dt,r.rsp_place, r.rsp_cust_name, r.rsp_cust_mobile_no from t_regular_sp_cake r, m_item t, m_franchisee f where r.del_status=0 And  r.rsp_delivery_dt=:date  AND r.fr_id=:frId AND r.menu_id In(:menuId) and r.del_status=0 AND r.fr_id=f.fr_id AND r.item_id=t.id",nativeQuery=true)
	List<GetRegSpCakeOrdersNew> findByOrdersForExBill(@Param("date")String date,@Param("menuId") List<String> menuId,@Param("frId") int frId);


 
	
	
}

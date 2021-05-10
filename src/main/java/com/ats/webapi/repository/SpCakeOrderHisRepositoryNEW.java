package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.ats.webapi.model.SpCkOrderHisNew;


public interface SpCakeOrderHisRepositoryNEW extends JpaRepository<SpCkOrderHisNew, Long> {
	@Query(value="select t_sp_cake.*,m_sp_flavour.spf_name,m_sp_cake.sp_name,m_fr_menu_show.menu_title,m_fr_configure.disc_per,m_fr_configure.grn_per     from t_sp_cake,m_sp_flavour,m_sp_cake,m_fr_menu_show,m_fr_configure  where t_sp_cake.del_status=0 and t_sp_cake.sp_delivery_date=:spDeliveryDate AND  t_sp_cake.fr_code=:frCode AND t_sp_cake.sp_flavour_id=m_sp_flavour.spf_id AND t_sp_cake.sp_id=m_sp_cake.sp_id  AND t_sp_cake.menu_id=m_fr_menu_show.menu_id   AND t_sp_cake.menu_id=m_fr_configure.menu_id" ,nativeQuery=true)
	List<SpCkOrderHisNew> findByMenuIdInAndSpDeliveryDt(@Param("spDeliveryDate")String spDeliveryDate,@Param("frCode")String frCode);

	@Query(value="select t_sp_cake.*,m_sp_flavour.spf_name,m_sp_cake.sp_name from t_sp_cake,m_sp_flavour,m_sp_cake where   t_sp_cake.del_status=0 and  sp_order_no=:orderNo And t_sp_cake.sp_flavour_id=m_sp_flavour.spf_id AND t_sp_cake.sp_id=m_sp_cake.sp_id",nativeQuery=true)
	SpCkOrderHisNew findByOrderNo(@Param("orderNo")int orderNo);

	@Query(value="select t_sp_cake.*,m_sp_flavour.spf_name,m_sp_cake.sp_name from t_sp_cake,m_sp_flavour,m_sp_cake where  t_sp_cake.del_status=0 and  sp_delivery_place=:orderNo And t_sp_cake.sp_flavour_id=m_sp_flavour.spf_id AND t_sp_cake.sp_id=m_sp_cake.sp_id",nativeQuery=true)
	List<SpCkOrderHisNew> findByOrderNoForEx(@Param("orderNo")String orderNo);

	@Query(value="select t_sp_cake.*,m_sp_flavour.spf_name,m_sp_cake.sp_name from t_sp_cake,m_sp_flavour,m_sp_cake where  t_sp_cake.del_status=0 and  t_sp_cake.sp_delivery_date=:date and t_sp_cake.fr_id=:frId And  t_sp_cake.menu_id In(:menuId) and t_sp_cake.sp_flavour_id=m_sp_flavour.spf_id AND t_sp_cake.sp_id=m_sp_cake.sp_id",nativeQuery=true)
	List<SpCkOrderHisNew> findByOrdersForExBill(@Param("date")String date,@Param("menuId")List<String> menuId,@Param("frId") int frId);

	@Query(value="select t_sp_cake.*,m_sp_flavour.spf_name,m_sp_cake.sp_name from t_sp_cake,m_sp_flavour,m_sp_cake where  t_sp_cake.del_status=0 and  t_sp_cake.sp_delivery_date=:spDeliveryDt AND t_sp_cake.menu_id IN(:menuList) AND  t_sp_cake.fr_code=:frCode AND t_sp_cake.sp_flavour_id=m_sp_flavour.spf_id AND t_sp_cake.sp_id=m_sp_cake.sp_id  AND t_sp_cake.menu_id=m_fr_menu_show.menu_id   AND t_sp_cake.menu_id=m_fr_configure.menu_id ",nativeQuery=true)
	List<SpCkOrderHisNew> findByMenuIdInAndSpDeliveryDtByMenu(@Param("menuList")List<String> menuList,@Param("spDeliveryDt") String spDeliveryDt,@Param("frCode") String frCode);

	@Query(value="select t_sp_cake.*,m_sp_flavour.spf_name,m_sp_cake.sp_name,m_fr_menu_show.menu_title,m_fr_configure.disc_per,m_fr_configure.grn_per   from t_sp_cake,m_sp_flavour,m_sp_cake,m_fr_menu_show,m_fr_configure  where  t_sp_cake.del_status=0 and  sp_order_no=:spOrderNo And t_sp_cake.sp_flavour_id=m_sp_flavour.spf_id AND t_sp_cake.sp_id=m_sp_cake.sp_id",nativeQuery=true)
	SpCkOrderHisNew findByOrderNoForExBillPrint(@Param("spOrderNo")int spOrderNo);
}

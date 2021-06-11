package com.ats.webapi.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.pettycash.GetCashAdvAndExpAmt;

public interface GetCashAdvAndExpAmtRepo extends JpaRepository<GetCashAdvAndExpAmt, Integer> {
	
	@Query(value=" SELECT UUID() as id, COALESCE((SELECT SUM(t.cash_amt) FROM t_transaction_detail t,t_sell_bill_header h "
			+ "WHERE t.sell_bill_no=h.sell_bill_no AND h.fr_id=:frId AND t.transaction_date=:date),0) as tr_cash_amt, "
			+ "COALESCE((SELECT SUM(advance_amt) FROM t_adv_order_header WHERE fr_id =:frId AND order_date =:date AND is_sell_bill_generated=0 ),0) as adv_amt, "
			+ "COALESCE((SELECT SUM(ch_amt) FROM m_expense WHERE fr_id =:frId AND del_status = 0 AND exp_date =:date AND exp_type=1 ),0) as exp_amt " + 
			"",nativeQuery=true)
	GetCashAdvAndExpAmt getAmt(@Param("frId") int frId, @Param("date") String date);
	
	
	
	@Query(value=" SELECT\r\n" + 
			"        UUID() as id,\r\n" + 
			"        0 AS tr_cash_amt,\r\n" + 
			"        COALESCE(SUM(t_sp_cake.sp_advance),0) AS adv_amt,\r\n" + 
			"        0 AS exp_amt\r\n" + 
			"        FROM\r\n" + 
			"        t_sp_cake\r\n" + 
			"        WHERE\r\n" + 
			"        t_sp_cake.order_date=:date AND t_sp_cake.fr_id=:frId",nativeQuery=true)
	GetCashAdvAndExpAmt getSpAdvAmt(@Param("frId") int frId, @Param("date") String date);
	
	
	
	@Query(value=" SELECT\r\n" + 
			"        UUID() as id,\r\n" + 
			"        0 AS tr_cash_amt,\r\n" + 
			"        0 AS adv_amt,\r\n" + 
			"       COALESCE(SUM(t_sp_cake.sp_grand_total-t_sp_cake.sp_advance),0) AS exp_amt\r\n" + 
			"        FROM\r\n" + 
			"        t_sp_cake\r\n" + 
			"        WHERE\r\n" + 
			"        t_sp_cake.sp_delivery_date=:date AND t_sp_cake.fr_id=:frId",nativeQuery=true)
	GetCashAdvAndExpAmt getSpRemainingAmt(@Param("frId") int frId, @Param("date") String date);

	
/*	@Query(value=" SELECT UUID() as id, COALESCE((SELECT SUM(t.cash_amt) as tr_cash_amt FROM t_transaction_detail as t,"
			+ "t_sell_bill_header h WHERE t.sell_bill_no=h.sell_bill_no AND h.bill_date=:date AND h.fr_id=:frId),0) as tr_cash_amt, "
			+ "COALESCE((SELECT SUM(advance_amt) FROM t_adv_order_header WHERE fr_id =:frId AND order_date =:date ),0) as adv_amt, "
			+ "COALESCE((SELECT SUM(ch_amt) FROM m_expense WHERE fr_id =:frId AND del_status = 0 AND exp_date =:date ),0) as exp_amt " + 
			"",nativeQuery=true)
	GetCashAdvAndExpAmt getAmt(@Param("frId") int frId, @Param("date") String date);*/

	
	
	

}

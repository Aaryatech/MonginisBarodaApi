package com.ats.webapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.webapi.model.SellBillHeaderNew;

public interface SellBillHeaderNewRepo extends JpaRepository<SellBillHeaderNew,Integer>{

	@Query(value =  "  SELECT\r\n" + 
			"    UUID() AS id, t_sell_bill_header.sell_bill_no, t_sell_bill_header.bill_type,t_sell_bill_header.discount_amt, t_sell_bill_header.invoice_no, t_sell_bill_header.bill_date, t_sell_bill_header.taxable_amt, t_sell_bill_header.total_tax, t_sell_bill_header.grand_total,(\r\n" + 
			"        t_transaction_detail.cash_amt + t_transaction_detail.card_amt + t_transaction_detail.e_pay_amt\r\n" + 
			"    ) paid_amt,\r\n" + 
			"    t_sell_bill_header.remaining_amt,\r\n" + 
			"    CONCAT(\r\n" + 
			"        t_transaction_detail.cash_amt,\r\n" + 
			"        '-cash ,',\r\n" + 
			"        t_transaction_detail.card_amt,\r\n" + 
			"        '-card ,',\r\n" + 
			"        t_transaction_detail.e_pay_amt,\r\n" + 
			"        ' -E-pay'\r\n" + 
			"    ) AS payment_mode,\r\n" + 
			"    t_sell_bill_header.discount_per,\r\n" + 
			"    t_sell_bill_header.payable_amt,\r\n" + 
			"    t_transaction_detail.ex_var1 as fr_name,\r\n" + 
			"    m_customer1.cust_id,\r\n" + 
			"    m_customer1.cust_name,\r\n" + 
			"    m_customer1.phone_number,\r\n" + 
			"    m_customer1.gst_no,\r\n" + 
			"    m_customer1.address,\r\n" + 
			"    t_transaction_detail.cash_amt as cash,\r\n" + 
			"    t_transaction_detail.card_amt as card,\r\n" + 
			"    t_transaction_detail.e_pay_amt as e_pay,\r\n" + 
			"	t_sell_bill_header.ext_float1 as round_off, t_sell_bill_header.ext_int2 as order_id  " +
			"FROM\r\n" + 
			"    t_sell_bill_header,\r\n" + 
			"    t_transaction_detail,\r\n" + 
			"    m_franchisee,\r\n" + 
			"    m_customer1\r\n" + 
			"WHERE\r\n" + 
			"    m_franchisee.fr_id = t_sell_bill_header.fr_id AND t_sell_bill_header.del_status = 0 AND t_sell_bill_header.fr_id IN(:frId) AND t_transaction_detail.sell_bill_no = t_sell_bill_header.sell_bill_no AND t_sell_bill_header.bill_date BETWEEN :fromDate AND :toDate\r\n" + 
			"    AND m_customer1.cust_id=t_sell_bill_header.cust_id AND t_sell_bill_header.cust_id IN(:custId)  ORDER BY t_sell_bill_header.sell_bill_no DESC", nativeQuery = true)
	
	List<SellBillHeaderNew> getFrSellBillHeader(@Param("fromDate") String fromDate ,@Param("toDate") String toDate ,@Param("frId") List<String> frId,@Param("custId") List<String> custId);
	
	
	@Query(value =  "  SELECT\r\n" + 
			"    UUID() AS id, t_sell_bill_header.sell_bill_no, t_sell_bill_header.bill_type, t_sell_bill_header.discount_amt, t_sell_bill_header.invoice_no, t_sell_bill_header.bill_date, t_sell_bill_header.taxable_amt, t_sell_bill_header.total_tax, t_sell_bill_header.grand_total,(\r\n" + 
			"        t_transaction_detail.cash_amt + t_transaction_detail.card_amt + t_transaction_detail.e_pay_amt\r\n" + 
			"    ) paid_amt,\r\n" + 
			"    t_sell_bill_header.remaining_amt,\r\n" + 
			"    CONCAT(\r\n" + 
			"        t_transaction_detail.cash_amt,\r\n" + 
			"        '-cash ,',\r\n" + 
			"        t_transaction_detail.card_amt,\r\n" + 
			"        '-card ,',\r\n" + 
			"        t_transaction_detail.e_pay_amt,\r\n" + 
			"        ' -E-pay'\r\n" + 
			"    ) AS payment_mode,\r\n" + 
			"    t_sell_bill_header.discount_per,\r\n" + 
			"    t_sell_bill_header.payable_amt,\r\n" + 
			"    t_transaction_detail.ex_var1 as fr_name,\r\n" + 
			"    m_customer1.cust_id,\r\n" + 
			"    m_customer1.cust_name,\r\n" + 
			"    m_customer1.phone_number,\r\n" + 
			"    m_customer1.gst_no,\r\n" + 
			"    m_customer1.address,\r\n" + 
			"    t_transaction_detail.cash_amt as cash,\r\n" + 
			"    t_transaction_detail.card_amt as card,\r\n" + 
			"    t_transaction_detail.e_pay_amt as e_pay,\r\n" + 
			"	t_sell_bill_header.ext_float1 as round_off, t_sell_bill_header.ext_int2 as order_id  " +
			"FROM\r\n" + 
			"    t_sell_bill_header,\r\n" + 
			"    t_transaction_detail,\r\n" + 
			"    m_franchisee,\r\n" + 
			"    m_customer1\r\n" + 
			"WHERE\r\n" + 
			"    m_franchisee.fr_id = t_sell_bill_header.fr_id AND t_sell_bill_header.del_status = 0 AND t_sell_bill_header.fr_id IN(:frId) AND t_transaction_detail.sell_bill_no = t_sell_bill_header.sell_bill_no AND t_sell_bill_header.bill_date BETWEEN :fromDate AND :toDate\r\n" + 
			"    AND m_customer1.cust_id=t_sell_bill_header.cust_id AND t_sell_bill_header.del_status=0  ORDER BY t_sell_bill_header.sell_bill_no DESC", nativeQuery = true)
	
	List<SellBillHeaderNew> getFrSellBillHeaderAllCust(@Param("fromDate") String fromDate ,@Param("toDate") String toDate ,@Param("frId") List<String> frId);


	@Query(value="SELECT\r\n" + 
			"        UUID() AS id,\r\n" + 
			"        t_sell_bill_header.sell_bill_no,\r\n" + 
			"        t_sell_bill_header.bill_type,\r\n" + 
			"        t_sell_bill_header.discount_amt,\r\n" + 
			"        t_sell_bill_header.invoice_no,\r\n" + 
			"        t_sell_bill_header.bill_date,\r\n" + 
			"        t_sell_bill_header.taxable_amt,\r\n" + 
			"        t_sell_bill_header.total_tax,\r\n" + 
			"        t_sell_bill_header.grand_total,\r\n" + 
			"        (          t_transaction_detail.cash_amt + t_transaction_detail.card_amt + t_transaction_detail.e_pay_amt      ) paid_amt,\r\n" + 
			"        SUM(t_sell_bill_header.remaining_amt) AS remaining_amt,\r\n" + 
			"        CONCAT(          t_transaction_detail.cash_amt,\r\n" + 
			"        '-cash ,',\r\n" + 
			"        t_transaction_detail.card_amt,\r\n" + 
			"        '-card ,',\r\n" + 
			"        t_transaction_detail.e_pay_amt,\r\n" + 
			"        ' -E-pay'      ) AS payment_mode,\r\n" + 
			"        t_sell_bill_header.discount_per,\r\n" + 
			"        t_sell_bill_header.payable_amt,\r\n" + 
			"        m_franchisee.fr_name,\r\n" + 
			"        m_customer1.cust_id,\r\n" + 
			"        m_customer1.cust_name,\r\n" + 
			"        m_customer1.phone_number,\r\n" + 
			"        m_customer1.gst_no,\r\n" + 
			"        m_customer1.address,\r\n" + 
			"        t_transaction_detail.cash_amt as cash,\r\n" + 
			"        t_transaction_detail.card_amt as card,\r\n" + 
			"        t_transaction_detail.e_pay_amt as e_pay,  \r\n" + 
			"	t_sell_bill_header.ext_float1 as round_off, t_sell_bill_header.ext_int2 as order_id  " +
			"    FROM\r\n" + 
			"        t_sell_bill_header,\r\n" + 
			"        t_transaction_detail,\r\n" + 
			"        m_franchisee,\r\n" + 
			"        m_customer1  \r\n" + 
			"    WHERE\r\n" + 
			"        m_franchisee.fr_id = t_sell_bill_header.fr_id  AND\r\n" + 
			"        t_sell_bill_header.remaining_amt>0 AND\r\n" + 
			"        t_sell_bill_header.del_status = 0 \r\n" + 
			"        AND t_sell_bill_header.fr_id IN(:frId) \r\n" + 
			"        AND t_transaction_detail.sell_bill_no = t_sell_bill_header.sell_bill_no \r\n" + 
			"        AND m_customer1.cust_id=t_sell_bill_header.cust_id \r\n" + 
			"    GROUP BY m_customer1.cust_id\r\n" + 
			"    ORDER BY\r\n" + 
			"        t_sell_bill_header.sell_bill_no DESC",nativeQuery=true)
	List<SellBillHeaderNew> getRemainingAmtAllCust(@Param("frId") List<String> frId);

	
	@Query(value="SELECT\r\n" + 
			"        UUID() AS id,\r\n" + 
			"        t_sell_bill_header.sell_bill_no,\r\n" + 
			"        t_sell_bill_header.bill_type,\r\n" + 
			"        t_sell_bill_header.discount_amt,\r\n" + 
			"        t_sell_bill_header.invoice_no,\r\n" + 
			"        t_sell_bill_header.bill_date,\r\n" + 
			"        t_sell_bill_header.taxable_amt,\r\n" + 
			"        t_sell_bill_header.total_tax,\r\n" + 
			"        t_sell_bill_header.grand_total,\r\n" + 
			"        (          t_transaction_detail.cash_amt + t_transaction_detail.card_amt + t_transaction_detail.e_pay_amt      ) paid_amt,\r\n" + 
			"        t_sell_bill_header.remaining_amt,\r\n" + 
			"        CONCAT(          t_transaction_detail.cash_amt,\r\n" + 
			"        '-cash ,',\r\n" + 
			"        t_transaction_detail.card_amt,\r\n" + 
			"        '-card ,',\r\n" + 
			"        t_transaction_detail.e_pay_amt,\r\n" + 
			"        ' -E-pay'      ) AS payment_mode,\r\n" + 
			"        t_sell_bill_header.discount_per,\r\n" + 
			"        t_sell_bill_header.payable_amt,\r\n" + 
			"        m_franchisee.fr_name,\r\n" + 
			"        m_customer1.cust_id,\r\n" + 
			"        m_customer1.cust_name,\r\n" + 
			"        m_customer1.phone_number,\r\n" + 
			"        m_customer1.gst_no,\r\n" + 
			"        m_customer1.address,\r\n" + 
			"        t_transaction_detail.cash_amt as cash,\r\n" + 
			"        t_transaction_detail.card_amt as card,\r\n" + 
			"        t_transaction_detail.e_pay_amt as e_pay,  \r\n" + 
			"	t_sell_bill_header.ext_float1 as round_off, t_sell_bill_header.ext_int2 as order_id  " +
			"    FROM\r\n" + 
			"        t_sell_bill_header,\r\n" + 
			"        t_transaction_detail,\r\n" + 
			"        m_franchisee,\r\n" + 
			"        m_customer1  \r\n" + 
			"    WHERE\r\n" + 
			"	    t_sell_bill_header.remaining_amt>0 AND\r\n" + 
			"        m_franchisee.fr_id = t_sell_bill_header.fr_id \r\n" + 
			"        AND t_sell_bill_header.del_status = 0 \r\n" + 
			"        AND t_sell_bill_header.fr_id IN(:frId) \r\n" + 
			"        AND t_transaction_detail.sell_bill_no = t_sell_bill_header.sell_bill_no \r\n" + 
			"        AND m_customer1.cust_id=t_sell_bill_header.cust_id \r\n" + 
			"        AND t_sell_bill_header.cust_id=:custId \r\n" + 
			"    ORDER BY\r\n" + 
			"        t_sell_bill_header.sell_bill_no DESC",nativeQuery=true)
	List<SellBillHeaderNew> getRemainingAmtByCustId(@Param("custId") int custId, @Param("frId") List<String> frId);


	@Query(value="SELECT\r\n" + 
			"    UUID() AS id, t_sell_bill_header.sell_bill_no, t_sell_bill_header.bill_type, t_sell_bill_header.discount_amt, t_sell_bill_header.invoice_no, t_sell_bill_header.bill_date, t_sell_bill_header.taxable_amt, t_sell_bill_header.total_tax, t_sell_bill_header.grand_total,(\r\n" + 
			"        t_transaction_detail.cash_amt + t_transaction_detail.card_amt + t_transaction_detail.e_pay_amt\r\n" + 
			"    ) paid_amt,\r\n" + 
			"    t_sell_bill_header.remaining_amt,\r\n" + 
			"    CONCAT(\r\n" + 
			"        t_transaction_detail.cash_amt,\r\n" + 
			"        '-cash ,',\r\n" + 
			"        t_transaction_detail.card_amt,\r\n" + 
			"        '-card ,',\r\n" + 
			"        t_transaction_detail.e_pay_amt,\r\n" + 
			"        ' -E-pay'\r\n" + 
			"    ) AS payment_mode,\r\n" + 
			"    t_sell_bill_header.discount_per,\r\n" + 
			"    t_sell_bill_header.payable_amt,\r\n" + 
			"    t_transaction_detail.ex_var1 as fr_name,\r\n" + 
			"    m_customer1.cust_id,\r\n" + 
			"    m_customer1.cust_name,\r\n" + 
			"    m_customer1.phone_number,\r\n" + 
			"    m_customer1.gst_no,\r\n" + 
			"    m_customer1.address,\r\n" + 
			"    t_transaction_detail.cash_amt AS cash,\r\n" + 
			"    t_transaction_detail.card_amt AS card,\r\n" + 
			"    t_transaction_detail.e_pay_amt AS e_pay, \r\n" +
			"	t_sell_bill_header.ext_float1 as round_off, t_sell_bill_header.ext_int2 as order_id  " +
			"FROM\r\n" + 
			"    t_sell_bill_header,\r\n" + 
			"    t_transaction_detail,\r\n" + 
			"    m_franchisee,\r\n" + 
			"    m_customer1\r\n" + 
			"WHERE\r\n" + 
			"    m_franchisee.fr_id = t_sell_bill_header.fr_id AND t_sell_bill_header.del_status = 0 AND t_sell_bill_header.fr_id IN(:frId) AND t_transaction_detail.sell_bill_no = t_sell_bill_header.sell_bill_no AND t_sell_bill_header.bill_date BETWEEN :fromDate AND :toDate AND m_customer1.cust_id = t_sell_bill_header.cust_id AND m_customer1.age_group LIKE :age\r\n" + 
			"ORDER BY\r\n" + 
			"    t_sell_bill_header.sell_bill_no\r\n" + 
			"DESC", nativeQuery=true)
	List<SellBillHeaderNew> getFrSellBillHeaderAllCustByAge(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("frId") List<String> frId,
			@Param("age") String age);
	

	
	@Query(value =  "SELECT\r\n" + 
			"    UUID() AS id, \r\n" + 
			"    t_sell_bill_header.sell_bill_no, \r\n" + 
			"    t_sell_bill_header.bill_type, \r\n" + 
			"    SUM(t_sell_bill_header.discount_amt) AS discount_amt, \r\n" + 
			"    t_sell_bill_header.invoice_no, \r\n" + 
			"    t_sell_bill_header.bill_date, \r\n" + 
			"    SUM(t_sell_bill_header.taxable_amt) AS taxable_amt, \r\n" + 
			"    SUM(t_sell_bill_header.total_tax) AS total_tax, \r\n" + 
			"    SUM(t_sell_bill_header.grand_total) AS grand_total,\r\n" + 
			"    SUM(t_transaction_detail.cash_amt + t_transaction_detail.card_amt + t_transaction_detail.e_pay_amt\r\n" + 
			"    ) AS paid_amt,\r\n" + 
			"    SUM(t_sell_bill_header.remaining_amt) AS remaining_amt,\r\n" + 
			"    CONCAT(\r\n" + 
			"        SUM(t_transaction_detail.cash_amt),\r\n" + 
			"        '-cash ,',\r\n" + 
			"         SUM(t_transaction_detail.card_amt),\r\n" + 
			"        '-card ,',\r\n" + 
			"         SUM(t_transaction_detail.e_pay_amt),\r\n" + 
			"        ' -E-pay'\r\n" + 
			"    ) AS payment_mode,\r\n" + 
			"    SUM(t_sell_bill_header.discount_per) as discount_per,\r\n" + 
			"    SUM(t_sell_bill_header.payable_amt) AS payable_amt,\r\n" + 
			"    m_franchisee.fr_name,\r\n" + 
			"    m_customer1.cust_id,\r\n" + 
			"    m_customer1.cust_name,\r\n" + 
			"    m_customer1.phone_number,\r\n" + 
			"    m_customer1.gst_no,\r\n" + 
			"    m_customer1.address,\r\n" + 
			"    SUM(t_transaction_detail.cash_amt) AS cash,\r\n" + 
			"   	SUM(t_transaction_detail.card_amt) AS card,\r\n" + 
			"    SUM(t_transaction_detail.e_pay_amt) AS e_pay, \r\n" + 
			"	t_sell_bill_header.ext_float1 as round_off, t_sell_bill_header.ext_int2 as order_id  " +
			"FROM\r\n" + 
			"    t_sell_bill_header,\r\n" + 
			"    t_transaction_detail,\r\n" + 
			"    m_franchisee,\r\n" + 
			"    m_customer1\r\n" + 
			"WHERE\r\n" + 
			"    m_franchisee.fr_id = t_sell_bill_header.fr_id AND\r\n" + 
			"    t_sell_bill_header.del_status = 0 AND\r\n" + 
			"    t_sell_bill_header.fr_id IN(:frId) AND \r\n" + 
			"    t_transaction_detail.sell_bill_no = t_sell_bill_header.sell_bill_no AND \r\n" + 
			"    t_sell_bill_header.bill_date BETWEEN :fromDate AND :toDate AND \r\n" + 
			"    m_customer1.cust_id = t_sell_bill_header.cust_id AND \r\n" + 
			"    t_sell_bill_header.cust_id IN(:custId)\r\n" + 
			"GROUP BY \r\n" + 
			"    	t_sell_bill_header.cust_id\r\n" + 
			"ORDER BY\r\n" + 
			"    t_sell_bill_header.sell_bill_no\r\n" + 
			"DESC", nativeQuery = true)
	
	List<SellBillHeaderNew> getCustSellReport(@Param("fromDate") String fromDate ,@Param("toDate") String toDate ,
			@Param("frId") List<String> frId,@Param("custId") List<String> custId);
	
	@Query(value =  "SELECT\r\n" + 
			"    UUID() AS id, \r\n" + 
			"    t_sell_bill_header.sell_bill_no, \r\n" + 
			"    t_sell_bill_header.bill_type, \r\n" + 
			"    SUM(t_sell_bill_header.discount_amt) AS discount_amt, \r\n" + 
			"    t_sell_bill_header.invoice_no, \r\n" + 
			"    t_sell_bill_header.bill_date, \r\n" + 
			"    SUM(t_sell_bill_header.taxable_amt) AS taxable_amt, \r\n" + 
			"    SUM(t_sell_bill_header.total_tax) AS total_tax, \r\n" + 
			"    SUM(t_sell_bill_header.grand_total) AS grand_total,\r\n" + 
			"    SUM(t_transaction_detail.cash_amt + t_transaction_detail.card_amt + t_transaction_detail.e_pay_amt\r\n" + 
			"    ) AS paid_amt,\r\n" + 
			"    SUM(t_sell_bill_header.remaining_amt) AS remaining_amt,\r\n" + 
			"    CONCAT(\r\n" + 
			"        SUM(t_transaction_detail.cash_amt),\r\n" + 
			"        '-cash ,',\r\n" + 
			"         SUM(t_transaction_detail.card_amt),\r\n" + 
			"        '-card ,',\r\n" + 
			"         SUM(t_transaction_detail.e_pay_amt),\r\n" + 
			"        ' -E-pay'\r\n" + 
			"    ) AS payment_mode,\r\n" + 
			"    SUM(t_sell_bill_header.discount_per) as discount_per,\r\n" + 
			"    SUM(t_sell_bill_header.payable_amt) AS payable_amt,\r\n" + 
			"    m_franchisee.fr_name,\r\n" + 
			"    m_customer1.cust_id,\r\n" + 
			"    m_customer1.cust_name,\r\n" + 
			"    m_customer1.phone_number,\r\n" + 
			"    m_customer1.gst_no,\r\n" + 
			"    m_customer1.address,\r\n" + 
			"    SUM(t_transaction_detail.cash_amt) AS cash,\r\n" + 
			"   	SUM(t_transaction_detail.card_amt) AS card,\r\n" + 
			"    SUM(t_transaction_detail.e_pay_amt) AS e_pay, \r\n" + 
			"	t_sell_bill_header.ext_float1 as round_off, t_sell_bill_header.ext_int2 as order_id  " +
			"FROM\r\n" + 
			"    t_sell_bill_header,\r\n" + 
			"    t_transaction_detail,\r\n" + 
			"    m_franchisee,\r\n" + 
			"    m_customer1\r\n" + 
			"WHERE\r\n" + 
			"    m_franchisee.fr_id = t_sell_bill_header.fr_id AND\r\n" + 
			"    t_sell_bill_header.del_status = 0 AND\r\n" + 
			"    t_sell_bill_header.fr_id IN(:frId) AND \r\n" + 
			"    t_transaction_detail.sell_bill_no = t_sell_bill_header.sell_bill_no AND \r\n" + 
			"    t_sell_bill_header.bill_date BETWEEN :fromDate AND :toDate AND \r\n" + 
			"    m_customer1.cust_id = t_sell_bill_header.cust_id AND \r\n" + 
			"    t_sell_bill_header.cust_id IN(:custId) AND t_sell_bill_header.ext_int2>0 \r\n" + 
			"GROUP BY \r\n" + 
			"    	t_sell_bill_header.cust_id\r\n" + 
			"ORDER BY\r\n" + 
			"    t_sell_bill_header.sell_bill_no\r\n" + 
			"DESC", nativeQuery = true)
	
	List<SellBillHeaderNew> getCustSellReportForOnline(@Param("fromDate") String fromDate ,@Param("toDate") String toDate ,
			@Param("frId") List<String> frId,@Param("custId") List<String> custId);

	@Query(value =  "SELECT\r\n" + 
			"    UUID() AS id, \r\n" + 
			"    t_sell_bill_header.sell_bill_no, \r\n" + 
			"    t_sell_bill_header.bill_type, \r\n" + 
			"    SUM(t_sell_bill_header.discount_amt) AS discount_amt, \r\n" + 
			"    t_sell_bill_header.invoice_no, \r\n" + 
			"    t_sell_bill_header.bill_date, \r\n" + 
			"    SUM(t_sell_bill_header.taxable_amt) AS taxable_amt, \r\n" + 
			"    SUM(t_sell_bill_header.total_tax) AS total_tax, \r\n" + 
			"    SUM(t_sell_bill_header.grand_total) AS grand_total,\r\n" + 
			"    SUM(t_transaction_detail.cash_amt + t_transaction_detail.card_amt + t_transaction_detail.e_pay_amt\r\n" + 
			"    ) AS paid_amt,\r\n" + 
			"    SUM(t_sell_bill_header.remaining_amt) AS remaining_amt,\r\n" + 
			"    CONCAT(\r\n" + 
			"        SUM(t_transaction_detail.cash_amt),\r\n" + 
			"        '-cash ,',\r\n" + 
			"         SUM(t_transaction_detail.card_amt),\r\n" + 
			"        '-card ,',\r\n" + 
			"         SUM(t_transaction_detail.e_pay_amt),\r\n" + 
			"        ' -E-pay'\r\n" + 
			"    ) AS payment_mode,\r\n" + 
			"    SUM(t_sell_bill_header.discount_per) as discount_per,\r\n" + 
			"    SUM(t_sell_bill_header.payable_amt) AS payable_amt,\r\n" + 
			"    m_franchisee.fr_name,\r\n" + 
			"    m_customer1.cust_id,\r\n" + 
			"    m_customer1.cust_name,\r\n" + 
			"    m_customer1.phone_number,\r\n" + 
			"    m_customer1.gst_no,\r\n" + 
			"    m_customer1.address,\r\n" + 
			"    SUM(t_transaction_detail.cash_amt) AS cash,\r\n" + 
			"   	SUM(t_transaction_detail.card_amt) AS card,\r\n" + 
			"    SUM(t_transaction_detail.e_pay_amt) AS e_pay, \r\n" + 
			"	t_sell_bill_header.ext_float1 as round_off, t_sell_bill_header.ext_int2 as order_id  " +
			"FROM\r\n" + 
			"    t_sell_bill_header,\r\n" + 
			"    t_transaction_detail,\r\n" + 
			"    m_franchisee,\r\n" + 
			"    m_customer1\r\n" + 
			"WHERE\r\n" + 
			"    m_franchisee.fr_id = t_sell_bill_header.fr_id AND\r\n" + 
			"    t_sell_bill_header.del_status = 0 AND\r\n" + 
			"    t_sell_bill_header.fr_id IN(:frId) AND \r\n" + 
			"    t_transaction_detail.sell_bill_no = t_sell_bill_header.sell_bill_no AND \r\n" + 
			"    t_sell_bill_header.bill_date BETWEEN :fromDate AND :toDate AND \r\n" + 
			"    m_customer1.cust_id = t_sell_bill_header.cust_id AND \r\n" + 
			"    t_sell_bill_header.cust_id IN(:custId) AND t_sell_bill_header.ext_int2=0 \r\n" + 
			"GROUP BY \r\n" + 
			"    	t_sell_bill_header.cust_id\r\n" + 
			"ORDER BY\r\n" + 
			"    t_sell_bill_header.sell_bill_no\r\n" + 
			"DESC", nativeQuery = true)
	
	List<SellBillHeaderNew> getCustSellReportForPOS(@Param("fromDate") String fromDate ,@Param("toDate") String toDate ,
			@Param("frId") List<String> frId,@Param("custId") List<String> custId);
	
	
	@Query(value =  "SELECT\r\n" + 
			"    UUID() AS id, \r\n" + 
			"    t_sell_bill_header.sell_bill_no, \r\n" + 
			"    t_sell_bill_header.bill_type, \r\n" + 
			"    SUM(t_sell_bill_header.discount_amt) AS discount_amt, \r\n" + 
			"    t_sell_bill_header.invoice_no, \r\n" + 
			"    t_sell_bill_header.bill_date, \r\n" + 
			"    SUM(t_sell_bill_header.taxable_amt) AS taxable_amt, \r\n" + 
			"    SUM(t_sell_bill_header.total_tax) AS total_tax, \r\n" + 
			"    SUM(t_sell_bill_header.grand_total) AS grand_total,\r\n" + 
			"    SUM(t_transaction_detail.cash_amt + t_transaction_detail.card_amt + t_transaction_detail.e_pay_amt\r\n" + 
			"    ) AS paid_amt,\r\n" + 
			"    SUM(t_sell_bill_header.remaining_amt) AS remaining_amt,\r\n" + 
			"    CONCAT(\r\n" + 
			"        SUM(t_transaction_detail.cash_amt),\r\n" + 
			"        '-cash ,',\r\n" + 
			"         SUM(t_transaction_detail.card_amt),\r\n" + 
			"        '-card ,',\r\n" + 
			"         SUM(t_transaction_detail.e_pay_amt),\r\n" + 
			"        ' -E-pay'\r\n" + 
			"    ) AS payment_mode,\r\n" + 
			"    SUM(t_sell_bill_header.discount_per) as discount_per,\r\n" + 
			"    SUM(t_sell_bill_header.payable_amt) AS payable_amt,\r\n" + 
			"    m_franchisee.fr_name,\r\n" + 
			"    m_customer1.cust_id,\r\n" + 
			"    m_customer1.cust_name,\r\n" + 
			"    m_customer1.phone_number,\r\n" + 
			"    m_customer1.gst_no,\r\n" + 
			"    m_customer1.address,\r\n" + 
			"    SUM(t_transaction_detail.cash_amt) AS cash,\r\n" + 
			"   	SUM(t_transaction_detail.card_amt) AS card,\r\n" + 
			"    SUM(t_transaction_detail.e_pay_amt) AS e_pay, \r\n" + 
			"	t_sell_bill_header.ext_float1 as round_off, t_sell_bill_header.ext_int2 as order_id  " +
			"FROM\r\n" + 
			"    t_sell_bill_header,\r\n" + 
			"    t_transaction_detail,\r\n" + 
			"    m_franchisee,\r\n" + 
			"    m_customer1\r\n" + 
			"WHERE\r\n" + 
			"    m_franchisee.fr_id = t_sell_bill_header.fr_id AND\r\n" + 
			"    t_sell_bill_header.del_status = 0 AND\r\n" + 
			"    t_sell_bill_header.fr_id IN(:frId) AND \r\n" + 
			"    t_transaction_detail.sell_bill_no = t_sell_bill_header.sell_bill_no AND \r\n" + 
			"    t_sell_bill_header.bill_date BETWEEN :fromDate AND :toDate AND \r\n" + 
			"    m_customer1.cust_id = t_sell_bill_header.cust_id\r\n" + 
			"GROUP BY \r\n" + 
			"    	t_sell_bill_header.cust_id\r\n" + 
			"ORDER BY\r\n" + 
			"    t_sell_bill_header.sell_bill_no\r\n" + 
			"DESC", nativeQuery = true)
	
	List<SellBillHeaderNew> getAllCustSellReport(@Param("fromDate") String fromDate ,@Param("toDate") String toDate ,
			@Param("frId") List<String> frId);
	

	@Query(value =  "SELECT\r\n" + 
			"    UUID() AS id, \r\n" + 
			"    t_sell_bill_header.sell_bill_no, \r\n" + 
			"    t_sell_bill_header.bill_type, \r\n" + 
			"    SUM(t_sell_bill_header.discount_amt) AS discount_amt, \r\n" + 
			"    t_sell_bill_header.invoice_no, \r\n" + 
			"    t_sell_bill_header.bill_date, \r\n" + 
			"    SUM(t_sell_bill_header.taxable_amt) AS taxable_amt, \r\n" + 
			"    SUM(t_sell_bill_header.total_tax) AS total_tax, \r\n" + 
			"    SUM(t_sell_bill_header.grand_total) AS grand_total,\r\n" + 
			"    SUM(t_transaction_detail.cash_amt + t_transaction_detail.card_amt + t_transaction_detail.e_pay_amt\r\n" + 
			"    ) AS paid_amt,\r\n" + 
			"    SUM(t_sell_bill_header.remaining_amt) AS remaining_amt,\r\n" + 
			"    CONCAT(\r\n" + 
			"        SUM(t_transaction_detail.cash_amt),\r\n" + 
			"        '-cash ,',\r\n" + 
			"         SUM(t_transaction_detail.card_amt),\r\n" + 
			"        '-card ,',\r\n" + 
			"         SUM(t_transaction_detail.e_pay_amt),\r\n" + 
			"        ' -E-pay'\r\n" + 
			"    ) AS payment_mode,\r\n" + 
			"    SUM(t_sell_bill_header.discount_per) as discount_per,\r\n" + 
			"    SUM(t_sell_bill_header.payable_amt) AS payable_amt,\r\n" + 
			"    m_franchisee.fr_name,\r\n" + 
			"    m_customer1.cust_id,\r\n" + 
			"    m_customer1.cust_name,\r\n" + 
			"    m_customer1.phone_number,\r\n" + 
			"    m_customer1.gst_no,\r\n" + 
			"    m_customer1.address,\r\n" + 
			"    SUM(t_transaction_detail.cash_amt) AS cash,\r\n" + 
			"   	SUM(t_transaction_detail.card_amt) AS card,\r\n" + 
			"    SUM(t_transaction_detail.e_pay_amt) AS e_pay, \r\n" + 
			"	t_sell_bill_header.ext_float1 as round_off, t_sell_bill_header.ext_int2 as order_id  " +
			"FROM\r\n" + 
			"    t_sell_bill_header,\r\n" + 
			"    t_transaction_detail,\r\n" + 
			"    m_franchisee,\r\n" + 
			"    m_customer1\r\n" + 
			"WHERE\r\n" + 
			"    m_franchisee.fr_id = t_sell_bill_header.fr_id AND\r\n" + 
			"    t_sell_bill_header.del_status = 0 AND\r\n" + 
			"    t_sell_bill_header.fr_id IN(:frId) AND \r\n" + 
			"    t_transaction_detail.sell_bill_no = t_sell_bill_header.sell_bill_no AND \r\n" + 
			"    t_sell_bill_header.bill_date BETWEEN :fromDate AND :toDate AND \r\n" + 
			"    m_customer1.cust_id = t_sell_bill_header.cust_id AND t_sell_bill_header.ext_int2>0 \r\n" + 
			"GROUP BY \r\n" + 
			"    	t_sell_bill_header.cust_id\r\n" + 
			"ORDER BY\r\n" + 
			"    t_sell_bill_header.sell_bill_no\r\n" + 
			"DESC", nativeQuery = true)
	
	List<SellBillHeaderNew> getAllCustSellReportForOnline(@Param("fromDate") String fromDate ,@Param("toDate") String toDate ,
			@Param("frId") List<String> frId);
	
	@Query(value =  "SELECT\r\n" + 
			"    UUID() AS id, \r\n" + 
			"    t_sell_bill_header.sell_bill_no, \r\n" + 
			"    t_sell_bill_header.bill_type, \r\n" + 
			"    SUM(t_sell_bill_header.discount_amt) AS discount_amt, \r\n" + 
			"    t_sell_bill_header.invoice_no, \r\n" + 
			"    t_sell_bill_header.bill_date, \r\n" + 
			"    SUM(t_sell_bill_header.taxable_amt) AS taxable_amt, \r\n" + 
			"    SUM(t_sell_bill_header.total_tax) AS total_tax, \r\n" + 
			"    SUM(t_sell_bill_header.grand_total) AS grand_total,\r\n" + 
			"    SUM(t_transaction_detail.cash_amt + t_transaction_detail.card_amt + t_transaction_detail.e_pay_amt\r\n" + 
			"    ) AS paid_amt,\r\n" + 
			"    SUM(t_sell_bill_header.remaining_amt) AS remaining_amt,\r\n" + 
			"    CONCAT(\r\n" + 
			"        SUM(t_transaction_detail.cash_amt),\r\n" + 
			"        '-cash ,',\r\n" + 
			"         SUM(t_transaction_detail.card_amt),\r\n" + 
			"        '-card ,',\r\n" + 
			"         SUM(t_transaction_detail.e_pay_amt),\r\n" + 
			"        ' -E-pay'\r\n" + 
			"    ) AS payment_mode,\r\n" + 
			"    SUM(t_sell_bill_header.discount_per) as discount_per,\r\n" + 
			"    SUM(t_sell_bill_header.payable_amt) AS payable_amt,\r\n" + 
			"    m_franchisee.fr_name,\r\n" + 
			"    m_customer1.cust_id,\r\n" + 
			"    m_customer1.cust_name,\r\n" + 
			"    m_customer1.phone_number,\r\n" + 
			"    m_customer1.gst_no,\r\n" + 
			"    m_customer1.address,\r\n" + 
			"    SUM(t_transaction_detail.cash_amt) AS cash,\r\n" + 
			"   	SUM(t_transaction_detail.card_amt) AS card,\r\n" + 
			"    SUM(t_transaction_detail.e_pay_amt) AS e_pay, \r\n" + 
			"	t_sell_bill_header.ext_float1 as round_off, t_sell_bill_header.ext_int2 as order_id  " +
			"FROM\r\n" + 
			"    t_sell_bill_header,\r\n" + 
			"    t_transaction_detail,\r\n" + 
			"    m_franchisee,\r\n" + 
			"    m_customer1\r\n" + 
			"WHERE\r\n" + 
			"    m_franchisee.fr_id = t_sell_bill_header.fr_id AND\r\n" + 
			"    t_sell_bill_header.del_status = 0 AND\r\n" + 
			"    t_sell_bill_header.fr_id IN(:frId) AND \r\n" + 
			"    t_transaction_detail.sell_bill_no = t_sell_bill_header.sell_bill_no AND \r\n" + 
			"    t_sell_bill_header.bill_date BETWEEN :fromDate AND :toDate AND \r\n" + 
			"    m_customer1.cust_id = t_sell_bill_header.cust_id AND t_sell_bill_header.ext_int2=0 \r\n" + 
			"GROUP BY \r\n" + 
			"    	t_sell_bill_header.cust_id\r\n" + 
			"ORDER BY\r\n" + 
			"    t_sell_bill_header.sell_bill_no\r\n" + 
			"DESC", nativeQuery = true)
	
	List<SellBillHeaderNew> getAllCustSellReportForPOS(@Param("fromDate") String fromDate ,@Param("toDate") String toDate ,
			@Param("frId") List<String> frId);
	
	
	
	//------------------------------------------------------------------------------------------------
	
	@Query(value =  "  SELECT\r\n" + 
			"    UUID() AS id, t_sell_bill_header.sell_bill_no, t_sell_bill_header.bill_type, t_sell_bill_header.discount_amt, t_sell_bill_header.invoice_no, t_sell_bill_header.bill_date, t_sell_bill_header.taxable_amt, t_sell_bill_header.total_tax, t_sell_bill_header.grand_total,(\r\n" + 
			"        t_transaction_detail.cash_amt + t_transaction_detail.card_amt + t_transaction_detail.e_pay_amt\r\n" + 
			"    ) paid_amt,\r\n" + 
			"    t_sell_bill_header.remaining_amt,\r\n" + 
			"    CONCAT(\r\n" + 
			"        t_transaction_detail.cash_amt,\r\n" + 
			"        '-cash ,',\r\n" + 
			"        t_transaction_detail.card_amt,\r\n" + 
			"        '-card ,',\r\n" + 
			"        t_transaction_detail.e_pay_amt,\r\n" + 
			"        ' -E-pay'\r\n" + 
			"    ) AS payment_mode,\r\n" + 
			"    t_sell_bill_header.discount_per,\r\n" + 
			"    t_sell_bill_header.payable_amt,\r\n" + 
			"    t_transaction_detail.ex_var1 as fr_name,\r\n" + 
			"    m_customer1.cust_id,\r\n" + 
			"    m_customer1.cust_name,\r\n" + 
			"    m_customer1.phone_number,\r\n" + 
			"    m_customer1.gst_no,\r\n" + 
			"    t_sell_bill_header.bill_type as address,\r\n" + 
			"    t_transaction_detail.cash_amt as cash,\r\n" + 
			"    t_transaction_detail.card_amt as card,\r\n" + 
			"    t_transaction_detail.e_pay_amt as e_pay,\r\n" + 
			"	t_sell_bill_header.ext_float1 as round_off, t_sell_bill_header.ext_int2 as order_id  " +
			"FROM\r\n" + 
			"    t_sell_bill_header,\r\n" + 
			"    t_transaction_detail,\r\n" + 
			"    m_franchisee,\r\n" + 
			"    m_customer1\r\n" + 
			"WHERE\r\n" + 
			"    m_franchisee.fr_id = t_sell_bill_header.fr_id AND t_sell_bill_header.del_status = 0 AND t_sell_bill_header.fr_id IN(:frId) AND t_transaction_detail.sell_bill_no = t_sell_bill_header.sell_bill_no AND t_sell_bill_header.bill_date BETWEEN :fromDate AND :toDate\r\n" + 
			"    AND m_customer1.cust_id=t_sell_bill_header.cust_id AND t_sell_bill_header.del_status=0  ORDER BY t_sell_bill_header.sell_bill_no DESC", nativeQuery = true)
	
	List<SellBillHeaderNew> getPosViewSellBillAll(@Param("fromDate") String fromDate ,@Param("toDate") String toDate ,@Param("frId") List<String> frId);

	@Query(value =  "  SELECT\r\n" + 
			"    UUID() AS id, t_sell_bill_header.sell_bill_no, t_sell_bill_header.bill_type, t_sell_bill_header.discount_amt, t_sell_bill_header.invoice_no, t_sell_bill_header.bill_date, t_sell_bill_header.taxable_amt, t_sell_bill_header.total_tax, t_sell_bill_header.grand_total,(\r\n" + 
			"        t_transaction_detail.cash_amt + t_transaction_detail.card_amt + t_transaction_detail.e_pay_amt\r\n" + 
			"    ) paid_amt,\r\n" + 
			"    t_sell_bill_header.remaining_amt,\r\n" + 
			"    CONCAT(\r\n" + 
			"        t_transaction_detail.cash_amt,\r\n" + 
			"        '-cash ,',\r\n" + 
			"        t_transaction_detail.card_amt,\r\n" + 
			"        '-card ,',\r\n" + 
			"        t_transaction_detail.e_pay_amt,\r\n" + 
			"        ' -E-pay'\r\n" + 
			"    ) AS payment_mode,\r\n" + 
			"    t_sell_bill_header.discount_per,\r\n" + 
			"    t_sell_bill_header.payable_amt,\r\n" + 
			"    t_transaction_detail.ex_var1 as fr_name,\r\n" + 
			"    m_customer1.cust_id,\r\n" + 
			"    m_customer1.cust_name,\r\n" + 
			"    m_customer1.phone_number,\r\n" + 
			"    m_customer1.gst_no,\r\n" + 
			"    t_sell_bill_header.bill_type as address,\r\n" + 
			"    t_transaction_detail.cash_amt as cash,\r\n" + 
			"    t_transaction_detail.card_amt as card,\r\n" + 
			"    t_transaction_detail.e_pay_amt as e_pay,\r\n" + 
			"	t_sell_bill_header.ext_float1 as round_off, t_sell_bill_header.ext_int2 as order_id   " +
			"FROM\r\n" + 
			"    t_sell_bill_header,\r\n" + 
			"    t_transaction_detail,\r\n" + 
			"    m_franchisee,\r\n" + 
			"    m_customer1\r\n" + 
			"WHERE\r\n" + 
			"    m_franchisee.fr_id = t_sell_bill_header.fr_id AND t_sell_bill_header.del_status = 0 AND t_sell_bill_header.fr_id IN(:frId) AND t_transaction_detail.sell_bill_no = t_sell_bill_header.sell_bill_no AND t_sell_bill_header.bill_date BETWEEN :fromDate AND :toDate\r\n" + 
			"    AND m_customer1.cust_id=t_sell_bill_header.cust_id AND t_sell_bill_header.del_status=0 AND t_sell_bill_header.ext_int2 = 0  ORDER BY t_sell_bill_header.sell_bill_no DESC", nativeQuery = true)
	
	List<SellBillHeaderNew> getPosViewSellBillPOS(@Param("fromDate") String fromDate ,@Param("toDate") String toDate ,@Param("frId") List<String> frId);

	@Query(value =  "  SELECT\r\n" + 
			"    UUID() AS id, t_sell_bill_header.sell_bill_no, t_sell_bill_header.bill_type, t_sell_bill_header.discount_amt, t_sell_bill_header.invoice_no, t_sell_bill_header.bill_date, t_sell_bill_header.taxable_amt, t_sell_bill_header.total_tax, t_sell_bill_header.grand_total,(\r\n" + 
			"        t_transaction_detail.cash_amt + t_transaction_detail.card_amt + t_transaction_detail.e_pay_amt\r\n" + 
			"    ) paid_amt,\r\n" + 
			"    t_sell_bill_header.remaining_amt,\r\n" + 
			"    CONCAT(\r\n" + 
			"        t_transaction_detail.cash_amt,\r\n" + 
			"        '-cash ,',\r\n" + 
			"        t_transaction_detail.card_amt,\r\n" + 
			"        '-card ,',\r\n" + 
			"        t_transaction_detail.e_pay_amt,\r\n" + 
			"        ' -E-pay'\r\n" + 
			"    ) AS payment_mode,\r\n" + 
			"    t_sell_bill_header.discount_per,\r\n" + 
			"    t_sell_bill_header.payable_amt,\r\n" + 
			"    t_transaction_detail.ex_var1 as fr_name,\r\n" + 
			"    m_customer1.cust_id,\r\n" + 
			"    m_customer1.cust_name,\r\n" + 
			"    m_customer1.phone_number,\r\n" + 
			"    m_customer1.gst_no,\r\n" + 
			"    t_sell_bill_header.bill_type as address,\r\n" + 
			"    t_transaction_detail.cash_amt as cash,\r\n" + 
			"    t_transaction_detail.card_amt as card,\r\n" + 
			"    t_transaction_detail.e_pay_amt as e_pay,\r\n" + 
			"	t_sell_bill_header.ext_float1 as round_off, t_sell_bill_header.ext_int2 as order_id   " +
			"FROM\r\n" + 
			"    t_sell_bill_header,\r\n" + 
			"    t_transaction_detail,\r\n" + 
			"    m_franchisee,\r\n" + 
			"    m_customer1\r\n" + 
			"WHERE\r\n" + 
			"    m_franchisee.fr_id = t_sell_bill_header.fr_id AND t_sell_bill_header.del_status = 0 AND t_sell_bill_header.fr_id IN(:frId) AND t_transaction_detail.sell_bill_no = t_sell_bill_header.sell_bill_no AND t_sell_bill_header.bill_date BETWEEN :fromDate AND :toDate\r\n" + 
			"    AND m_customer1.cust_id=t_sell_bill_header.cust_id AND t_sell_bill_header.del_status=0 AND t_sell_bill_header.ext_int2 > 0  ORDER BY t_sell_bill_header.sell_bill_no DESC", nativeQuery = true)
	
	List<SellBillHeaderNew> getPosViewSellBillOnline(@Param("fromDate") String fromDate ,@Param("toDate") String toDate ,@Param("frId") List<String> frId);
}

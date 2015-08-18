package com.wj.test;

import java.util.Arrays;

import org.junit.Test;
import org.kie.api.runtime.StatelessKieSession;

import com.wj.purchase.PaymentMethod;
import com.wj.purchase.Purchase;


/**
 * @Title: DiscountRulesTestCase.java 
 * @Package  
 * @Description: �ۿ۹�����ԣ�Ӧ�ù����ļ�rules/discount.drl������Stateless session��
 * @author wujiang
 * @version V1.0 
 */

public class DiscountRulesTestCase {

	private static final String DRL_PATH = "rules/discount.drl";

	@Test
	public void testStatelessSession() {
		
		//����һ����״̬session
		StatelessKieSession session = TestUtil.createStatelessKieSession(DRL_PATH);

		// ������Ҫ���ԵĹ������
		Purchase cashPurchase = new Purchase("john", 100, PaymentMethod.CASH);
		Purchase debitPurchase = new Purchase("peter", 100, PaymentMethod.DEBIT);
		Purchase creditPurchase = new Purchase("george", 100, PaymentMethod.CREDIT);

		// ִ�����еĹ������ƥ�����
		session.execute(Arrays.asList(cashPurchase, debitPurchase, creditPurchase));
	}
}

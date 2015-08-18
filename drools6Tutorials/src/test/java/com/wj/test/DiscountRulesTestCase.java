package com.wj.test;

import java.util.Arrays;

import org.junit.Test;
import org.kie.api.runtime.StatelessKieSession;

import com.wj.purchase.PaymentMethod;
import com.wj.purchase.Purchase;


/**
 * @Title: DiscountRulesTestCase.java 
 * @Package  
 * @Description: 折扣规则测试，应用规则文件rules/discount.drl，采用Stateless session，
 * @author wujiang
 * @version V1.0 
 */

public class DiscountRulesTestCase {

	private static final String DRL_PATH = "rules/discount.drl";

	@Test
	public void testStatelessSession() {
		
		//创建一个无状态session
		StatelessKieSession session = TestUtil.createStatelessKieSession(DRL_PATH);

		// 创建需要测试的购买对象
		Purchase cashPurchase = new Purchase("john", 100, PaymentMethod.CASH);
		Purchase debitPurchase = new Purchase("peter", 100, PaymentMethod.DEBIT);
		Purchase creditPurchase = new Purchase("george", 100, PaymentMethod.CREDIT);

		// 执行所有的购买对象匹配规则
		session.execute(Arrays.asList(cashPurchase, debitPurchase, creditPurchase));
	}
}

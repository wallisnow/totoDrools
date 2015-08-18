package com.wj.test;

import java.util.Collection;

import org.drools.core.ClassObjectFilter;
import org.drools.core.common.DefaultFactHandle;
import org.junit.Assert;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import com.wj.purchase.PaymentMethod;
import com.wj.purchase.PotentialCustomer;
import com.wj.purchase.Purchase;

/**
 * @Title: PotentialCustomerRulesTestCase.java 
 * @Package  
 * @Description: 潜在客户测试，应用规则文件potentialCustomer01.drl，采用stateful session，
 * @author wujiang
 * @version V1.0 
 */

public class PotentialCustomerRulesTestCase {

	private static final String DRL01_PATH = "rules/potentialCustomer01.drl";

	@SuppressWarnings("restriction")
	@Test
	public void testIdentifyPotentialCustomer() {
		
		// 创建一个stateful session
		KieSession session = TestUtil.createKieSession(DRL01_PATH);

		// 创建所有需要插入到session 的对象
		Purchase cashPurchaseLowAmount = new Purchase("john", 250, PaymentMethod.CASH);
		Purchase cashPurchasePotentialCustomer = new Purchase("mary", 450, PaymentMethod.CASH);
		Purchase debitPurchase = new Purchase("peter", 100, PaymentMethod.DEBIT);
		Purchase creditPurchase = new Purchase("george", 500, PaymentMethod.CREDIT);

		// 此时由于是stateful session，所以暂时对象没有被插入到workmemory，可以参考PPT图
		session.insert(cashPurchaseLowAmount);
		session.insert(cashPurchasePotentialCustomer);
		session.insert(debitPurchase);
		session.insert(creditPurchase);

		// 只有所有的规则被执行，那么 潜在客户对象就会被插入到workmemory
		// FactHandle是一个借口，它是一种取出memory中的对象的方法，但此方法已经过去，不过可以使用
		Collection<FactHandle> factHandles = session.getFactHandles(new ClassObjectFilter(PotentialCustomer.class));

		// 执行规则
		session.fireAllRules();

		// 潜在客户被插入到workmemory
		factHandles = session.getFactHandles(new ClassObjectFilter(PotentialCustomer.class));
		
		FactHandle fh = factHandles.iterator().next();
		PotentialCustomer pc = (PotentialCustomer) ((DefaultFactHandle) fh).getObject();
		
		Assert.assertEquals("mary", pc.getCustomerName());

		//释放资源
		session.dispose();
	}
		
}

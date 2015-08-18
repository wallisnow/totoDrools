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
 * @Description: Ǳ�ڿͻ����ԣ�Ӧ�ù����ļ�potentialCustomer01.drl������stateful session��
 * @author wujiang
 * @version V1.0 
 */

public class PotentialCustomerRulesTestCase {

	private static final String DRL01_PATH = "rules/potentialCustomer01.drl";

	@SuppressWarnings("restriction")
	@Test
	public void testIdentifyPotentialCustomer() {
		
		// ����һ��stateful session
		KieSession session = TestUtil.createKieSession(DRL01_PATH);

		// ����������Ҫ���뵽session �Ķ���
		Purchase cashPurchaseLowAmount = new Purchase("john", 250, PaymentMethod.CASH);
		Purchase cashPurchasePotentialCustomer = new Purchase("mary", 450, PaymentMethod.CASH);
		Purchase debitPurchase = new Purchase("peter", 100, PaymentMethod.DEBIT);
		Purchase creditPurchase = new Purchase("george", 500, PaymentMethod.CREDIT);

		// ��ʱ������stateful session��������ʱ����û�б����뵽workmemory�����Բο�PPTͼ
		session.insert(cashPurchaseLowAmount);
		session.insert(cashPurchasePotentialCustomer);
		session.insert(debitPurchase);
		session.insert(creditPurchase);

		// ֻ�����еĹ���ִ�У���ô Ǳ�ڿͻ�����ͻᱻ���뵽workmemory
		// FactHandle��һ����ڣ�����һ��ȡ��memory�еĶ���ķ��������˷����Ѿ���ȥ����������ʹ��
		Collection<FactHandle> factHandles = session.getFactHandles(new ClassObjectFilter(PotentialCustomer.class));

		// ִ�й���
		session.fireAllRules();

		// Ǳ�ڿͻ������뵽workmemory
		factHandles = session.getFactHandles(new ClassObjectFilter(PotentialCustomer.class));
		
		FactHandle fh = factHandles.iterator().next();
		PotentialCustomer pc = (PotentialCustomer) ((DefaultFactHandle) fh).getObject();
		
		Assert.assertEquals("mary", pc.getCustomerName());

		//�ͷ���Դ
		session.dispose();
	}
		
}

package com.wj.service;

import com.wj.purchase.PotentialCustomer;
/**
 * @Title: EmailService.java 
 * @Package  
 * @Description: 给潜在用户发送邮件
 * @author wujiang
 * @version V1.0 
 */
public class EmailService {
	
	private static EmailService instance = new EmailService();

	public static EmailService getInstance() {
		return instance;
	}

	public void sendCreditCardOffer(PotentialCustomer pc) {
		System.out.println("send offer to " + pc.getCustomerName() + ", credit limit is " + pc.getCreditLimit());
	}
}

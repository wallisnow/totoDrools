package com.wj.purchase;
/**
 * @Title: Purchase.java 
 * @Package  
 * @Description: 购买信息对象
 * @author wujiang
 * @version V1.0 
 */
public class Purchase {

	private String customerName;
	private double subtotal;
	private PaymentMethod paymentMethod;
	private double discount;

	public Purchase(String customerName, double subtotal, PaymentMethod paymentMethod) {
		this.customerName = customerName;
		this.subtotal = subtotal;
		this.paymentMethod = paymentMethod;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	/**
	 * Returns the total amount of this Purchase. Total = Subtotal - Discount
	 * 
	 * @return the Total Amount of this Purchase
	 */
	public double getTotal() {
		return subtotal - (subtotal * discount);
	}

	public String toString() {
		return "Purchase [Customer: " + customerName + " | Subtotal: " + subtotal + " | Payment Method: "
				+ paymentMethod + " | Discount: " + discount + " | Total : " + getTotal() + "]";
	}

}

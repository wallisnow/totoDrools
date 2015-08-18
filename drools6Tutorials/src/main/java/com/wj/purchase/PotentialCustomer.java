package com.wj.purchase;
/**
 * @Title: PotentialCustomer.java 
 * @Package  
 * @Description: 潜在用户对象
 * @author wujiang
 * @version V1.0 
 */
public class PotentialCustomer {
    private String customerName;
    private double creditLimit;

    public PotentialCustomer(String customerName, double creditLimit) {
        this.customerName = customerName;
        this.creditLimit = creditLimit;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String toString() {
        return "Potential Customer [Name: " + customerName + " | Credit Limit: " + creditLimit + "]";
    }
}

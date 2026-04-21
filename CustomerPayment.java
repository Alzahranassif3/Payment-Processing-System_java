package project;

import java.text.SimpleDateFormat;
import java.util.Date;


public abstract class CustomerPayment implements Comparable<CustomerPayment> {
    protected String customerName;
    protected int customerId;
    protected double amount;

    public CustomerPayment() {
    }

    public CustomerPayment(String customerName, int customerId, double amount) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.amount = amount;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getAmount() {
        return amount;
    }

    protected abstract double calculatePayment();

    public void printPaymentInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.format("Customer: %s (ID: %d), Amount: %.2f",
                customerName, customerId, amount);
    }

    @Override
    public int compareTo(CustomerPayment custom) {
        // sort by final payment (highest first)
        if (calculatePayment() > custom.calculatePayment())
            return -1;
        else if (calculatePayment() < custom.calculatePayment())
            return 1;
        else
            return 0;
    }
}
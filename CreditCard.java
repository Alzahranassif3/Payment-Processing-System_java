package project;

import java.util.Date;

public class CreditCard extends CustomerPayment implements Payable {
    private double chargingFee;
    private Date expiryDate;

    public CreditCard() {
    }

    public CreditCard(String customerName, int customerId, double amount, 
                      double chargingFee, Date expiryDate) {
        super(customerName, customerId, amount);
        this.chargingFee = chargingFee;
        this.expiryDate = expiryDate;
    }

    public void setChargingFee(double chargingFee) {
        this.chargingFee = chargingFee;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public double getChargingFee() {
        return chargingFee;
    }

    @Override
    protected double calculatePayment() {
        return getAmount() + chargingFee;
    }

    @Override
    public boolean isAuthorized() {
        // card is authorized if expire date is today or in the future
        Date today = new Date();
        return expiryDate.compareTo(today) >= 0;
    }

    @Override
    public String toString() {
        return String.format("CreditCard [%s, Fee: %.2f, Expiry: %tY-%tm-%td, Final Payment: %.2f]",
                super.toString(), chargingFee, expiryDate, expiryDate, expiryDate, calculatePayment());
    }
}
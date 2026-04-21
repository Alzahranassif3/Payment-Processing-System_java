package project;


public class Cash extends CustomerPayment {
    private double discountRate;

    public Cash() {
    }

    public Cash(String customerName, int customerId, double amount, double discountRate) {
        super(customerName, customerId, amount);
        this.discountRate = discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    @Override
    protected double calculatePayment() {
        return getAmount() - ((discountRate * getAmount()) / 100);
    }

    @Override
    public String toString() {
        return String.format("Cash [%s, Discount: %.1f%%, Final Payment: %.2f]",
                super.toString(), discountRate, calculatePayment());
    }
}
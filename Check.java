package project;

public class Check extends CustomerPayment implements Payable {
    public static final int CASHIER = 1;
    public static final int CERTIFIED = 2;
    public static final int PERSONAL = 3;

    private int accountNumber;
    private double accountBalance;
    private int type;

    public Check() {
    }

    public Check(String customerName, int customerId, double amount, 
                 int accountNumber, double accountBalance, int type) {
        super(customerName, customerId, amount);
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.type = type;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public int getType() {
        return type;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void deductAmountFromBalance() {
        if (type == PERSONAL || type == CERTIFIED) {
            accountBalance -= getAmount();
        }
    }

    @Override
    protected double calculatePayment() {
        return getAmount();
    }

    @Override
    public boolean isAuthorized() {
        if (type == CASHIER || calculatePayment() <= accountBalance) {
            deductAmountFromBalance();
            return true;
        }
        return false;
    }

    private String getTypeName() {
        switch (type) {
            case CASHIER: return "CASHIER";
            case CERTIFIED: return "CERTIFIED";
            case PERSONAL: return "PERSONAL";
            default: return "UNKNOWN";
        }
    }

    @Override
    public String toString() {
        return String.format("Check [%s, Account: %d, Balance: %.2f, Type: %s, Final Payment: %.2f]",
                super.toString(), accountNumber, accountBalance, getTypeName(), calculatePayment());
    }
}
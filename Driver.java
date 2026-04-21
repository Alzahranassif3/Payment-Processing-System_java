package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Driver {

    public static void main(String[] args) {

        ArrayList<CustomerPayment> payments = new ArrayList<>();

        CustomerPayment check1 = new Check("Rana", 7777, 400, 1111, 350, Check.PERSONAL);
        if (((Check) check1).isAuthorized()) {
            payments.add(check1);
        }

        CustomerPayment cash = new Cash("Ahmad", 4444, 150, 5.0);
        payments.add(cash);

        CustomerPayment check2 = new Check("Suha", 5555, 100, 1111, 200, Check.CASHIER);
        if (((Check) check2).isAuthorized()) {
            payments.add(check2);
        }

        CustomerPayment check3 = new Check("Rania", 7777, 600.0, 1111, 750, Check.CERTIFIED);
        if (((Check) check3).isAuthorized()) {
            payments.add(check3);
        }

        CustomerPayment creditCard1 = new CreditCard("Randa", 9999, 170, 20, new Date(124, 05, 03));
        if (((CreditCard) creditCard1).isAuthorized()) {
            payments.add(creditCard1);
        }

        CustomerPayment creditCard2 = new CreditCard("Hani", 6666, 150, 10, new Date(120, 06, 07));
        if (((CreditCard) creditCard2).isAuthorized()) {
            payments.add(creditCard2);
        }

        Collections.sort(payments);
        printFormattedTable(payments);
    }

    private static void printFormattedTable(ArrayList<CustomerPayment> payments) {
        System.out.println("\n" + "=".repeat(95));
        System.out.printf("%-12s %-18s %-10s %-15s %-15s %-12s%n",
                "Method", "Customer Name", "ID", "Original Amount", "Final Payment", "Status");
        System.out.println("-".repeat(95));

        for (CustomerPayment payment : payments) {
            String method = getPaymentMethod(payment);
            double finalPayment = payment.calculatePayment();
            String status = getAuthorizationStatus(payment);

            System.out.printf("%-12s %-18s %-10d %-15.2f %-15.2f %-12s%n",
                    method,
                    payment.getCustomerName(),
                    payment.getCustomerId(),
                    payment.getAmount(),
                    finalPayment,
                    status);
        }
        System.out.println("=".repeat(95));

        printSummary(payments);
    }

    private static String getPaymentMethod(CustomerPayment payment) {
        if (payment instanceof Cash) {
            return "Cash";
        } else if (payment instanceof Check) {
            return "Check";
        } else if (payment instanceof CreditCard) {
            return "Credit";
        }
        return "Unknown";
    }

    private static String getAuthorizationStatus(CustomerPayment payment) {
        if (payment instanceof Payable) {
            return "Authorized";
        }
        return "N/A";
    }

    private static void printSummary(ArrayList<CustomerPayment> payments) {
        double totalOriginal = 0;
        double totalFinal = 0;
        int cashCount = 0, checkCount = 0, creditCount = 0;

        for (CustomerPayment p : payments) {
            totalOriginal += p.getAmount();
            totalFinal += p.calculatePayment();

            if (p instanceof Cash) cashCount++;
            else if (p instanceof Check) checkCount++;
            else if (p instanceof CreditCard) creditCount++;
        }

        System.out.println("\nSUMMARY");
        System.out.println("-".repeat(40));
        System.out.printf("Total payments accepted: %d%n", payments.size());
        System.out.printf("Total original amount: %.2f%n", totalOriginal);
        System.out.printf("Total final amount: %.2f%n", totalFinal);
        System.out.printf("Total discounts/fees: %.2f%n", totalOriginal - totalFinal);
        System.out.println("\nPayment methods breakdown:");
        System.out.printf("  Cash: %d payment(s)%n", cashCount);
        System.out.printf("  Check: %d payment(s)%n", checkCount);
        System.out.printf("  Credit: %d payment(s)%n", creditCount);
    }
}
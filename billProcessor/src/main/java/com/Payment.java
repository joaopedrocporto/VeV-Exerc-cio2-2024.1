package billProcessor.src.main.java.com;

import java.util.Date;

public class Payment {

    public Date date;
    public double value;
    public String paymentType;
    public Invoice invoice;
    public Bill account;
    public Payment(Date date, double paidValue, String payment, Bill account, Invoice invoice) {
        this.value = paidValue;
        this.date = date;
        this.paymentType = payment;
        this.invoice = invoice;
        this.account = account;
    }
}

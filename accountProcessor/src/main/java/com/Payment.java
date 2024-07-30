package accountProcessor.src.main.java.com;

import java.util.Date;

public class Payment {

    public Date date;
    public double value;
    public String paymentType;
    public Payment(Date date, double paidValue, String payment) {
        this.value = paidValue;
        this.date = date;
        this.paymentType = payment;
    }
}

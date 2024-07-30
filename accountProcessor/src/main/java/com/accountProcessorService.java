package accountProcessor.src.main.java.com;

import java.time.LocalDate;
import java.util.Date;

public class accountProcessorService {

    public Payment createPayment(Date paymentDate, double paidValue, String payment) {
        if( payment.equals("BOLETO") && (paidValue > 5000 || paidValue < 0.01)){
            throw new IllegalArgumentException();
        }
        Payment newPayment = new Payment( paymentDate,paidValue,payment);
        return newPayment;
    }
}

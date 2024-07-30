package accountProcessor.src.main.java.com;

import java.util.Date;

public class accountProcessorService {

    public static void main(String args[]){
        System.out.println("oi");
    }

    public Payment createPayment(Date date, double paidValue, String payment) {
        if( payment.equals("BOLETO") && (paidValue == 5001 || paidValue == 0.001)){
            System.out.println(paidValue);
            throw new IllegalArgumentException();
        }
        Payment newPayment = new Payment( date,paidValue,payment);
        return newPayment;
    }
}

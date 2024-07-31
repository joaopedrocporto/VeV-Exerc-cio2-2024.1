package accountProcessor.src.main.java.com;

import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class accountProcessorService {



    public Payment payBill(Invoice invoice, Account account, Date paymentDate, double value, String payment) {
        if (!account.date.before(invoice.date)) {
            throw new IllegalArgumentException("The date of the Invoice should be after the bill date");
        }
        Payment billPayment;
        if (payment.equals("BOLETO")) {
            billPayment = payBoleto(invoice,account,paymentDate,value);
        }else{
            if (payment.equals("CARTAO_CREDITO")) {
                billPayment = payCard(invoice,account,paymentDate,value);
            } else {
                billPayment = payTransaction(invoice,account,paymentDate,value);
            }
        }
        return billPayment;
    }

    private Payment payTransaction(Invoice invoice, Account account, Date paymentDate, double value) {
        return new Payment(paymentDate,value,"TRANSACAO_BANCARIA",account, invoice);
    }

    private Payment payCard(Invoice invoice, Account account, Date paymentDate, double value) {
        long diffInMillies = Math.abs(account.date.getTime() - invoice.date.getTime());
        long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        if (diffInDays < 15) {
            throw new IllegalArgumentException("The date of the Invoice should be at least 15 days before the bill date");
        }
        Payment payment = new Payment(paymentDate,value,"CARTAO_CREDITO",account, invoice);
        return payment;
    }

    private Payment payBoleto(Invoice invoice, Account account, Date paymentDate, double value) {
        double newValue =  value;
        if(value > 5000 || value < 0.01){
            throw new IllegalArgumentException();
        }
        if(paymentDate.after(account.date)){
            newValue = 1.1 * value;
        }
        Payment payment = new Payment(paymentDate,newValue,"BOLETO",account, invoice);
        return payment;
    }
}

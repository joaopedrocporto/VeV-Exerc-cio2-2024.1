package accountProcessor.src.main.java.com;

import java.sql.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class accountProcessorService {

    Map<Integer,Account> accounts;
    Map<String,Invoice> invoices;
    private SimpleDateFormat dateFormatedDate;

    public accountProcessorService(){
        this.accounts = new HashMap<>();
        this.invoices = new HashMap<>();
        this.dateFormatedDate  = new SimpleDateFormat("yyyy-MM-dd");

    }

    public void addInvoices(Double value, String InvoiceDate, String clientName) throws ParseException {
        Date dateInvoice = dateFormatedDate.parse(InvoiceDate);
        Invoice newInvoice = new Invoice(value,dateInvoice,clientName);
        invoices.put(clientName,newInvoice);
    }

    public void addAccoubts(Double value, String accountDate, Integer code) throws ParseException {
        Date dateAccount = dateFormatedDate.parse(accountDate);
        Account newAccount = new Account(value,code,dateAccount);
        accounts.put(newAccount.getId(),newAccount);
    }



    public Payment payBill(Invoice invoice, Account account, Date paymentDate, String payment) {
        if (!account.date.before(invoice.date)) {
            throw new IllegalArgumentException("The date of the Invoice should be after the bill date");
        }
        Payment billPayment;
        if (payment.equals("BOLETO")) {
            billPayment = payBoleto(invoice,account,paymentDate,account.value);
        }else{
            if (payment.equals("CARTAO_CREDITO")) {
                billPayment = payCard(invoice,account,paymentDate,account.value);
            } else {
                billPayment = payTransaction(invoice,account,paymentDate,account.value);
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

    public Invoice processBills(Invoice invoice, List<Account> billList, Map<Integer, String> billsPaymentsWay) {
        long accumulatedValue = 0;
        Date currentDate = new Date();
        Payment atualPayment;
        for(int i = 0; i < billList.size();i++){
            atualPayment = this.payBill(invoice,billList.get(i), currentDate,billsPaymentsWay.get(billList.get(i).accountCode));
            accumulatedValue += atualPayment.value;
        }if(invoice.value >= accumulatedValue){
            invoice.confirmPayment();
        }
        return invoice;
    }
}

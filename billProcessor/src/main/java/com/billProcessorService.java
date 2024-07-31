package billProcessor.src.main.java.com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class billProcessorService {

    Map<Integer, Bill> bills;
    Map<String, Invoice> invoices;
    private SimpleDateFormat dateFormatedDate;

    public billProcessorService(){
        this.bills = new HashMap<>();
        this.invoices = new HashMap<>();
        this.dateFormatedDate  = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void addInvoices(Double value, String InvoiceDate, String clientName) throws ParseException {
        Date dateInvoice = dateFormatedDate.parse(InvoiceDate);
        Invoice newInvoice = new Invoice(value, dateInvoice, clientName);
        invoices.put(clientName, newInvoice);
    }

    public void addBills(Double value, String billDate, Integer code) throws ParseException {
        Date dateBill = dateFormatedDate.parse(billDate);
        Bill newBill = new Bill(value, code, dateBill);
        bills.put(newBill.getId(), newBill);
    }

    public Payment payBill(Invoice invoice, Bill bill, Date paymentDate, String payment) {
        if (!bill.date.before(invoice.date)) {
            throw new IllegalArgumentException("The date of the Invoice should be after the bill date");
        }
        Payment billPayment;
        if (payment.equals("BOLETO")) {
            billPayment = payBoleto(invoice, bill, paymentDate, bill.value);
        } else {
            if (payment.equals("CARTAO_CREDITO")) {
                billPayment = payCard(invoice, bill, paymentDate, bill.value);
            } else {
                billPayment = payTransaction(invoice, bill, paymentDate, bill.value);
            }
        }
        return billPayment;
    }

    private Payment payTransaction(Invoice invoice, Bill bill, Date paymentDate, double value) {
        return new Payment(paymentDate, value, "TRANSACAO_BANCARIA", bill, invoice);
    }

    private Payment payCard(Invoice invoice, Bill bill, Date paymentDate, double value) {
        long diffInMillies = Math.abs(bill.date.getTime() - invoice.date.getTime());
        long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        if (diffInDays < 15) {
            throw new IllegalArgumentException("The date of the Invoice should be at least 15 days before the bill date");
        }
        Payment payment = new Payment(paymentDate, value, "CARTAO_CREDITO", bill, invoice);
        return payment;
    }

    private Payment payBoleto(Invoice invoice, Bill bill, Date paymentDate, double value) {
        double newValue = value;
        if (value > 5000 || value < 0.01) {
            throw new IllegalArgumentException();
        }
        if (paymentDate.after(bill.date)) {
            newValue = 1.1 * value;
        }
        Payment payment = new Payment(paymentDate, newValue, "BOLETO", bill, invoice);
        return payment;
    }

    public Invoice processBills(Invoice invoice, List<Bill> billList, Map<Integer, String> billsPaymentsWay) {
        long accumulatedValue = 0;
        Date currentDate = new Date();
        Payment atualPayment;
        for (int i = 0; i < billList.size(); i++) {
            atualPayment = this.payBill(invoice, billList.get(i), currentDate, billsPaymentsWay.get(billList.get(i).getId()));
            accumulatedValue += atualPayment.value;
        }
        if (invoice.value >= accumulatedValue) {
            invoice.confirmPayment();
        }
        return invoice;
    }
}

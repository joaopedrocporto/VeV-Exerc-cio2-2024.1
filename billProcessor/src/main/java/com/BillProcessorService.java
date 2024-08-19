package billProcessor.src.main.java.com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BillProcessorService {

    Map<Integer, Bill> bills;
        Map<Integer, Invoice> invoices;
    private SimpleDateFormat dateFormatedDate;

    public BillProcessorService(){
        this.bills = new HashMap<>();
        this.invoices = new HashMap<>();
        this.dateFormatedDate  = new SimpleDateFormat("yyyy-MM-dd");
    }

    public Invoice addInvoices(Double value, String InvoiceDate, String clientName) throws ParseException {
        Date dateInvoice = dateFormatedDate.parse(InvoiceDate);
        Integer newCode = this.invoices.size() + 1;
        Invoice newInvoice = new Invoice(newCode,value, dateInvoice, clientName);
        invoices.put(newCode, newInvoice);
        return newInvoice;
    }

    public Bill addBills(double value, String billDate, Integer code) throws ParseException {
        Date dateBill = dateFormatedDate.parse(billDate);
        Bill newBill = new Bill(value, code, dateBill);
        bills.put(newBill.getId(), newBill);
        return newBill;
    }

    public double payBill(Invoice invoice, Bill bill, Date paymentDate, String payment) {
        if (bill.date.after(invoice.date)) {
            return 0;
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
        return billPayment.value;
    }

    private Payment payTransaction(Invoice invoice, Bill bill, Date paymentDate, double value) {
        return new Payment(paymentDate, value, "TRANSACAO_BANCARIA", bill, invoice);
    }

    private Payment payCard(Invoice invoice, Bill bill, Date paymentDate, double value) {
        long diffInMillies = Math.abs(bill.date.getTime() - invoice.date.getTime());
        long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        Payment newPayment;
        if (diffInDays < 15) {
            newPayment =new Payment(paymentDate, 0, "CARTAO_CREDITO", bill, invoice);
        }else {
            newPayment = new Payment(paymentDate, value, "CARTAO_CREDITO", bill, invoice);
        }
        return newPayment;

    }

    private Payment payBoleto(Invoice invoice, Bill bill, Date paymentDate, double value) {
        double newValue = value;
        if (value > 5000 || value < 0.01) {
            throw new IllegalArgumentException();
        }else {
            if (paymentDate.after(bill.date)) {
                newValue = 1.1 * value;
            }
        }
        return new Payment(paymentDate, newValue, "BOLETO", bill, invoice);
    }

    public Invoice processBills(Invoice invoice, List<Integer> billListId, Map<Integer, String> billsPaymentsWay,Date paymentDate) {
        double accumulatedValue = 0;
        for (int i = 0; i < billListId.size(); i++) {
            accumulatedValue += this.payBill(invoice, this.bills.get(billListId.get(i)), paymentDate, billsPaymentsWay.get(billListId.get(i)));
        }
        if (invoice.value <= accumulatedValue) {
            invoice.confirmPayment();
        }
        return invoice;
    }
}

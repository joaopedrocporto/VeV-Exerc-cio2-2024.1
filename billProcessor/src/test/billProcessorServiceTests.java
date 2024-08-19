import billProcessor.src.main.java.com.Bill;
import billProcessor.src.main.java.com.BillProcessorService;
import billProcessor.src.main.java.com.Invoice;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class billProcessorServiceTests {

    private SimpleDateFormat dateFormatedDate;
    BillProcessorService billProcessorService;

    @Before
    public void setUp() {
        dateFormatedDate = new SimpleDateFormat("yyyy-MM-dd");
        billProcessorService = new BillProcessorService();
    }

    @Test
    public void testBillCreation() {
        String dateString = "2023-12-12";
        try {
            Date date = dateFormatedDate.parse(dateString);
            double billValue = 12.32;
            int billCode = 12;
            Bill newBill = new Bill(billValue, billCode, date);
            assertEquals(newBill.billCode, billCode);
            assertEquals(newBill.value, billValue, 12.32);
            assertEquals(newBill.date, date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInvoiceCreation() {
        String dateString = "2022-11-23";
        try {
            String clientName = "Valber Azevedo";
            double totalValue = 234.87;
            Date date = dateFormatedDate.parse(dateString);
            Invoice newInvoice = new Invoice(1,totalValue, date, clientName);
            assertEquals(Optional.of(totalValue), newInvoice.value);
            assertEquals("Valber Azevedo", newInvoice.name);
            assertEquals(date, newInvoice.date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInvoiceCreation2() {
        String dateString = "2022-11-23";
        try {
            String clientName = "Valber Azevedo";
            double totalValue = 234.87;
            Date date = dateFormatedDate.parse(dateString);
            Invoice newInvoice = new Invoice(1,totalValue, date, clientName);
            assertEquals("PENDENTE", newInvoice.status);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPaymentCreation() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice newInvoice = new Invoice(1,234.87, dateInvoice, "Valber Azevedo");
            Date billDate = dateFormatedDate.parse("2023-12-11");
            Bill bill = new Bill(12.32, 12, billDate);
            Date paymentDate = dateFormatedDate.parse("2023-12-12");
            String payment = "BOLETO";
            double paidValue = this.billProcessorService.payBill(newInvoice, bill, paymentDate, payment);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPaymentCreationTicket() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice newInvoice = new Invoice(1,234.87, dateInvoice, "Valber Azevedo");
            Date billDate = dateFormatedDate.parse("2023-12-11");
            Bill bill = new Bill(12.32, 12, billDate);
            Date paymentDate = dateFormatedDate.parse("2023-12-12");
            String payment = "BOLETO";
            double paidValue = this.billProcessorService.payBill(newInvoice, bill, paymentDate, payment);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPaymentCreationTransaction() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice newInvoice = new Invoice(1,234.87, dateInvoice, "Valber Azevedo");
            Date billDate = dateFormatedDate.parse("2023-12-11");
            Bill bill = new Bill(12.32, 12, billDate);
            Date paymentDate = dateFormatedDate.parse("2023-12-12");
            String payment = "TRANSFERENCIA_BANCARIA";
            double paidValue = this.billProcessorService.payBill(newInvoice, bill, paymentDate, payment);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPaymentCreationCreditCard() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice newInvoice = new Invoice(1,234.87, dateInvoice, "Valber Azevedo");
            Date billDate = dateFormatedDate.parse("2023-11-10");
            Bill bill = new Bill(12.32, 12, billDate);
            Date paymentDate = dateFormatedDate.parse("2023-12-12");
            String payment = "CARTAO_CREDITO";
            double paidValue = this.billProcessorService.payBill(newInvoice, bill, paymentDate, payment);
            assertEquals(bill.value, paidValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPaymentWrongTicketCreation() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice newInvoice = new Invoice(1,234.87, dateInvoice, "Valber Azevedo");
            Date billDate = dateFormatedDate.parse("2023-12-11");
            Bill bill = new Bill(5501.32, 11, billDate);
            Date paymentDate = dateFormatedDate.parse("2023-12-12");
            String payment = "BOLETO";
            assertThrows(IllegalArgumentException.class, () -> {
                billProcessorService.payBill(newInvoice, bill, paymentDate, payment);
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPaymentWrongValueCreation() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice newInvoice = new Invoice(1,234.87, dateInvoice, "Valber Azevedo");
            Date billDate = dateFormatedDate.parse("2023-12-11");
            Bill bill = new Bill(0.0032, 11, billDate);
            Date paymentDate = dateFormatedDate.parse("2023-12-12");
            String payment = "BOLETO";
            assertThrows(IllegalArgumentException.class, () -> {
                billProcessorService.payBill(newInvoice, bill, paymentDate, payment);
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPaymentWrongDateCreation() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-11");
            Invoice newInvoice = new Invoice(1,234.87, dateInvoice, "Valber Azevedo");
            Date billDate = dateFormatedDate.parse("2023-12-12");
            Bill bill = new Bill(0.001, 10, billDate);
            Date paymentDate = dateFormatedDate.parse("2023-12-12");
            String payment = "BOLETO";
            assertEquals(0,
                billProcessorService.payBill(newInvoice, bill, paymentDate, payment));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPaymentWithBillAfterInvoice() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-12");
            Invoice newInvoice = new Invoice(1,234.87, dateInvoice, "Valber Azevedo");
            Date billDate = dateFormatedDate.parse("2023-12-13");
            Bill bill = new Bill(10, 10, billDate);
            Date paymentDate = dateFormatedDate.parse("2023-12-13");
            String payment = "BOLETO";
            assertEquals(0,
                billProcessorService.payBill(newInvoice, bill, paymentDate, payment));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPaymentWrongDateTransactionCreation() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-12");
            Invoice newInvoice = new Invoice(1,234.87, dateInvoice, "Valber Azevedo");
            Date billDate = dateFormatedDate.parse("2023-12-13");
            Bill bill = new Bill(10, 10, billDate);
            Date paymentDate = dateFormatedDate.parse("2023-12-13");
            String payment = "TRANSFERENCIA_BANCARIA";
            assertEquals(0,billProcessorService.payBill(newInvoice, bill, paymentDate, payment));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testPaymentWrongDateCardCreation() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice newInvoice = new Invoice(1,234.87, dateInvoice, "Valber Azevedo");
            Date billDate = dateFormatedDate.parse("2023-12-10");
            Bill bill = new Bill(10, 10, billDate);
            Date paymentDate = dateFormatedDate.parse("2023-12-12");
            String payment = "CARTAO_CREDITO";
            assertEquals(0,billProcessorService.payBill(newInvoice, bill, paymentDate, payment));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPayment() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-31");
            Invoice newInvoice = new Invoice(1,24.87, dateInvoice, "Valber Azevedo");
            Date billDate1 = dateFormatedDate.parse("2023-12-10");
            Bill bill1 = new Bill(10, 1, billDate1);
            Bill bill2 = new Bill(10, 2, billDate1);
            Bill bill3 = new Bill(10, 3, billDate1);
            Date paymentDate1 = dateFormatedDate.parse("2023-12-12");
            String paymentType = "TRANSACAO_BANCARIA";
            double payment = billProcessorService.payBill(newInvoice, bill1, paymentDate1, paymentType);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPaymentInsuficietValue() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-31");
            Date datePayment = dateFormatedDate.parse("2023-12-14");
            Invoice invoice = new Invoice(1,34.87, dateInvoice, "Valber Azevedo");
            Bill bill1 = this.billProcessorService.addBills(10, "2023-12-10",1);
            Bill bill2 = this.billProcessorService.addBills(10, "2023-12-10",2);
            Bill bill3 = this.billProcessorService.addBills(10,  "2023-12-10",3);
            List<Integer> billList = new ArrayList<>();
            billList.add(bill1.billCode);
            billList.add(bill2.billCode);
            billList.add(bill3.billCode);
            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill1.billCode, "TRANSACAO_BANCARIA");
            billsPaymentsWay.put(bill2.billCode, "TRANSACAO_BANCARIA");
            billsPaymentsWay.put(bill3.billCode, "TRANSACAO_BANCARIA");
            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay,datePayment);
            assertEquals("PENDENTE", processedInvoice.status);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPaymentSuficietValue() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-31");
            Date datePayment = dateFormatedDate.parse("2023-12-14");
            Invoice invoice = new Invoice(1,534.87, dateInvoice, "Valber Azevedo");
            Bill bill1 = this.billProcessorService.addBills(220, "2023-12-10",1);
            Bill bill2 = this.billProcessorService.addBills(10, "2023-12-10",2);
            Bill bill3 = this.billProcessorService.addBills(10,  "2023-12-10",3);
            List<Integer> billList = new ArrayList<>();
            billList.add(bill1.billCode);
            billList.add(bill2.billCode);
            billList.add(bill3.billCode);
            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill1.billCode, "TRANSACAO_BANCARIA");
            billsPaymentsWay.put(bill2.billCode, "TRANSACAO_BANCARIA");
            billsPaymentsWay.put(bill3.billCode, "TRANSACAO_BANCARIA");
            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay,datePayment);
            assertEquals("PENDENTE", processedInvoice.status);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExample1() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-02-20");

            Invoice invoice = new Invoice(1,1500.00, dateInvoice, "Valber Azevedo");
            Bill bill1 = this.billProcessorService.addBills(500, "2023-02-20",1);
            Bill bill2 = this.billProcessorService.addBills(400, "2023-02-20",2);
            Bill bill3 = this.billProcessorService.addBills(600,  "2023-02-20",3);
            List<Integer> billList = new ArrayList<>();
            billList.add(bill1.billCode);
            billList.add(bill2.billCode);
            billList.add(bill3.billCode);
            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill1.billCode, "TRANSACAO_BANCARIA");
            billsPaymentsWay.put(bill2.billCode, "TRANSACAO_BANCARIA");
            billsPaymentsWay.put(bill3.billCode, "TRANSACAO_BANCARIA");
            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay,dateInvoice);
            assertEquals("PAGA", processedInvoice.status);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExample2() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-02-20");

            Invoice invoice = new Invoice(1, 1500.00, dateInvoice, "Valber Azevedo");
            Bill bill1 = this.billProcessorService.addBills(700, "2023-02-05", 1);
            Bill bill2 = this.billProcessorService.addBills(800, "2023-02-17", 2);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill1.billCode);
            billList.add(bill2.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill1.billCode, "CARTAO_CREDITO");
            billsPaymentsWay.put(bill2.billCode, "TRANSACAO_BANCARIA");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PAGA", processedInvoice.status);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExample3() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-02-20");

            Invoice invoice = this.billProcessorService.addInvoices(1500.00, "2023-02-20", "Valber Azevedo");
            Bill bill1 = this.billProcessorService.addBills(700, "2023-02-06", 1);
            Bill bill2 = this.billProcessorService.addBills(800, "2023-02-17", 2);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill1.billCode);
            //billList.add(bill2.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill1.billCode, "CARTAO_CREDITO");
            billsPaymentsWay.put(bill2.billCode, "TRANSACAO_BANCARIA");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PENDENTE", processedInvoice.status);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
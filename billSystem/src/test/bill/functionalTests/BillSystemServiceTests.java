package bill.functionalTests;


import bill.Bill;
import bill.BillSystemService;
import bill.Invoice;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class BillSystemServiceTests {

    private SimpleDateFormat dateFormatedDate;
    BillSystemService billProcessorService;

    @Before
    public void setUp() {
        dateFormatedDate = new SimpleDateFormat("yyyy-MM-dd");
        billProcessorService = new BillSystemService();
    }

    @Test
    public void testCaso1() {
        try {
            SimpleDateFormat dateFormatedDate = new SimpleDateFormat("yyyy-MM-dd");
            Date dateInvoice = dateFormatedDate.parse("2023-12-28");

            Invoice invoice = new Invoice(1, 234.87, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(234.88, "2023-12-13", 1);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "CARTAO_CREDITO");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PAGA", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCaso2() {
        try {
            SimpleDateFormat dateFormatedDate = new SimpleDateFormat("yyyy-MM-dd");
            Date dateInvoice = dateFormatedDate.parse("2023-12-28");

            Invoice invoice = new Invoice(2, 234.87, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(234.87, "2023-12-13", 2);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "CARTAO_CREDITO");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PAGA", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCaso3() {
        try {
            SimpleDateFormat dateFormatedDate = new SimpleDateFormat("yyyy-MM-dd");
            Date dateInvoice = dateFormatedDate.parse("2023-12-28");

            Invoice invoice = new Invoice(3, 234.87, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(234.86, "2023-12-13", 3);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "CARTAO_CREDITO");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PENDENTE", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCaso4() {
        try {
            SimpleDateFormat dateFormatedDate = new SimpleDateFormat("yyyy-MM-dd");
            Date dateInvoice = dateFormatedDate.parse("2023-12-28");

            Invoice invoice = new Invoice(4, 234.87, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(234.85, "2023-12-13", 4);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "CARTAO_CREDITO");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PENDENTE", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCaso5() {
        try {
            SimpleDateFormat dateFormatedDate = new SimpleDateFormat("yyyy-MM-dd");
            Date dateInvoice = dateFormatedDate.parse("2023-12-28");

            Invoice invoice = new Invoice(5, 234.87, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(1.00, "2023-12-13", 5);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "CARTAO_CREDITO");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PENDENTE", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casosTable1() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-28");

            Invoice invoice = new Invoice(1, 234.87, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(234.87, "2023-12-13", 1);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "CARTAO_CREDITO");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PAGA", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casosTable2() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-27");

            Invoice invoice = new Invoice(1, 234.87, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(234.87, "2023-12-13", 1);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "CARTAO_CREDITO");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PENDENTE", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casosTable3() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-26");

            Invoice invoice = new Invoice(1, 10.00, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(10.00, "2023-12-13", 1);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "CARTAO_CREDITO");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PENDENTE", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casosTable4() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-29");

            Invoice invoice = new Invoice(1, 234.87, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(234.87, "2023-12-13", 1);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "CARTAO_CREDITO");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PAGA", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casosTable5() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");

            Invoice invoice = new Invoice(1, 234.87, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(234.87, "2023-12-13", 1);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "CARTAO_CREDITO");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PENDENTE", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }@Test
    public void casoBoleto1() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice invoice = new Invoice(1, 234.87, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(234.87, "2023-12-12", 1);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "BOLETO");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PENDENTE", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casoBoleto2() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-27");
            Invoice invoice = new Invoice(2, 258.35, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(234.87, "2023-12-12", 2);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "BOLETO");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PAGA", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casoBoleto3() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-26");
            Invoice invoice = new Invoice(3, 234.87, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(10, "2023-12-12", 3);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "BOLETO");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PENDENTE", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casoBoleto4() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-29");
            Invoice invoice = new Invoice(4, 234.87, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(234.87, "2023-12-12", 4);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "BOLETO");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PAGA", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casoBoleto5() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice invoice = new Invoice(5, 234.87, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(234.87, "2023-12-12", 5);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "BOLETO");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PENDENTE", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void casoBoletoErro1() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice invoice = new Invoice(1, 10.00, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(0.009, "2023-12-12", 1);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "BOLETO");

            assertThrows(IllegalArgumentException.class, () -> {
                billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casoBoletoErro2() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice invoice = new Invoice(2, 10.00, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(0.001, "2023-12-12", 2);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "BOLETO");

            assertThrows(IllegalArgumentException.class, () -> {
                billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casoBoletoErro3() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-27");
            Invoice invoice = new Invoice(3, 10.00, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(5001.00, "2023-12-12", 3);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "BOLETO");

            assertThrows(IllegalArgumentException.class, () -> {
                billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casoBoletoErro4() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-26");
            Invoice invoice = new Invoice(4, 10.00, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(10000.00, "2023-12-12", 4);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "BOLETO");

            assertThrows(IllegalArgumentException.class, () -> {
                billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casoBoletoPago1() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-29");
            Invoice invoice = new Invoice(5, 0.01, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(0.01, "2023-12-12", 5);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "BOLETO");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PAGA", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casoBoletoPago2() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice invoice = new Invoice(6, 10.00, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(5000.00, "2023-12-12", 6);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "BOLETO");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PAGA", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casoBoletoPago3() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice invoice = new Invoice(7, 100.00, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(100.00, "2023-12-12", 7);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "BOLETO");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PAGA", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEquivalence1() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice invoice = new Invoice(1, 0.009, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(0.009, "2023-12-12", 1);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "BOLETO");

            Date paymentDate = dateFormatedDate.parse("2023-12-11");
            assertThrows(IllegalArgumentException.class, () -> {
                billProcessorService.processBills(invoice, billList, billsPaymentsWay, paymentDate);
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEquivalence2() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice invoice = new Invoice(1, 5001.00, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(5001.00, "2023-12-12", 1);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "BOLETO");

            Date paymentDate = dateFormatedDate.parse("2023-12-11");
            assertThrows(IllegalArgumentException.class, () -> {
                billProcessorService.processBills(invoice, billList, billsPaymentsWay, paymentDate);
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEquivalence3() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice invoice = new Invoice(1, 0.01, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(0.01, "2023-12-12", 1);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "BOLETO");

            Date paymentDate = dateFormatedDate.parse("2023-12-11");
            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, paymentDate);
            assertEquals("PAGA", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEquivalence4() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice invoice = new Invoice(1, 5000.00, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(5000.00, "2023-12-12", 1);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "BOLETO");

            Date paymentDate = dateFormatedDate.parse("2023-12-14");
            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, paymentDate);
            assertEquals("PAGA", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEquivalence5() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice invoice = new Invoice(1, 100.00, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(100.00, "2023-12-12", 1);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "BOLETO");

            Date paymentDate = dateFormatedDate.parse("2023-12-14");
            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, paymentDate);
            assertEquals("PAGA", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casosTransferenciaTabela1() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Date billDate = dateFormatedDate.parse("2023-12-14");
            Invoice invoice = new Invoice(1, 100.00, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(100, billDate.toString(), 1);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "TRANSFERENCIA_BANCARIA");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PENDENTE", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casosTransferenciaTabela2() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Date billDate = dateFormatedDate.parse("2023-12-12");
            Invoice invoice = new Invoice(1, 100.00, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(50, billDate.toString(), 1);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "TRANSFERENCIA_BANCARIA");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PENDENTE", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casosTransferenciaTabela3() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Date billDate = dateFormatedDate.parse("2023-12-13");
            Invoice invoice = new Invoice(1, 100.00, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(50, billDate.toString(), 1);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "TRANSFERENCIA_BANCARIA");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PENDENTE", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casosTransferenciaTabela4() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Date billDate = dateFormatedDate.parse("2023-12-13");
            Invoice invoice = new Invoice(1, 100.00, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(100, billDate.toString(), 1);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "TRANSFERENCIA_BANCARIA");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PAGA", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casosCartaoCreditoTabela1() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Date billDate = dateFormatedDate.parse("2023-12-01");
            Invoice invoice = new Invoice(1, 100.00, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(50, billDate.toString(), 1);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "CARTAO_CREDITO");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PENDENTE", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casosCartaoCreditoTabela2() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Date billDate = dateFormatedDate.parse("2023-12-01");
            Invoice invoice = new Invoice(1, 100.00, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(100, billDate.toString(), 1);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "CARTAO_CREDITO");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PAGA", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casosCartaoCreditoTabela3() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Date billDate = dateFormatedDate.parse("2023-12-01");
            Invoice invoice = new Invoice(1, 200.00, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(100, billDate.toString(), 1);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "CARTAO_CREDITO");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PENDENTE", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casosCartaoCreditoTABELA4() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Date billDate = dateFormatedDate.parse("2023-12-01");
            Invoice invoice = new Invoice(1, 100.00, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(100, billDate.toString(), 1);

            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);

            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "CARTAO_CREDITO");

            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, dateInvoice);
            assertEquals("PAGA", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void casosTabelaBoleto1() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice invoice = new Invoice(1, 100.00, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(5001.00, "2023-12-12", 1);
            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);
            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "BOLETO");
            Date paymentDate = dateFormatedDate.parse("2023-12-14");
            assertThrows(IllegalArgumentException.class, () -> {
                billProcessorService.payBill(invoice, bill, paymentDate, "BOLETO");
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casosTabelaBoleto2() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice invoice = new Invoice(1, 100.00, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(0.009, "2023-12-12", 1);
            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);
            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "BOLETO");
            Date paymentDate = dateFormatedDate.parse("2023-12-11");
            assertThrows(IllegalArgumentException.class, () -> {
                billProcessorService.payBill(invoice, bill, paymentDate, "BOLETO");
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casosTabelaBoleto3() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice invoice = new Invoice(1, 100.00, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(0.01, "2023-12-12", 1);
            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);
            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "BOLETO");
            Date paymentDate = dateFormatedDate.parse("2023-12-11");
            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, paymentDate);
            assertEquals("PAGA", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casosTabelaBoleto4() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice invoice = new Invoice(1, 100.00, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(5000.00, "2023-12-12", 1);
            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);
            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "BOLETO");
            Date paymentDate = dateFormatedDate.parse("2023-12-14");
            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, paymentDate);
            assertEquals("PAGA", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casosTabelaBoleto5() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice invoice = new Invoice(1, 100.00, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(100.00, "2023-12-12", 1);
            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);
            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "BOLETO");
            Date paymentDate = dateFormatedDate.parse("2023-12-14");
            Invoice processedInvoice = billProcessorService.processBills(invoice, billList, billsPaymentsWay, paymentDate);
            assertEquals("PAGA", processedInvoice.getStatus());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void casosTabelaBoleto6() {
        try {
            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice invoice = new Invoice(1, 100.00, dateInvoice, "Valber Azevedo");
            Bill bill = this.billProcessorService.addBills(100.00, "2023-12-12", 1);
            List<Integer> billList = new ArrayList<>();
            billList.add(bill.billCode);
            Map<Integer, String> billsPaymentsWay = new HashMap<>();
            billsPaymentsWay.put(bill.billCode, "BOLETO");
            Date paymentDate = dateFormatedDate.parse("2023-12-14");
            assertThrows(IllegalArgumentException.class, () -> {
                billProcessorService.payBill(invoice, bill, paymentDate, "BOLETO");
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
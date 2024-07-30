package accountProcessor.src.test.java.com;

import accountProcessor.src.main.java.com.Account;
import accountProcessor.src.main.java.com.Invoice;
import accountProcessor.src.main.java.com.Payment;
import accountProcessor.src.main.java.com.accountProcessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class accountProcessorServiceTests {

    private SimpleDateFormat dateFormatedDate;
    accountProcessorService accountProcessor;

    @BeforeEach
    public void setUp() {
        dateFormatedDate  = new SimpleDateFormat("yyyy-MM-dd");
        accountProcessor = new accountProcessorService();
    }
    @Test
    public void testAccountCreation(){
        String dateString = "2023-12-12";
        try {
            Date date = dateFormatedDate.parse(dateString);
            double accountValue = 12.32;
            int accountCode = 12;
            Account newAccount = new Account(accountValue, accountCode, date);
            assertEquals(newAccount.accountCode, accountCode);
            assertEquals(newAccount.value, accountValue,12.32);
            assertEquals(newAccount.date, date);


        }catch (ParseException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testInvoiceCreation(){
        String dateString = "2022-11-23";
        try{
            String clientName = "Valber Azevedo";
            double totalValue = 234.87;
            Date date = dateFormatedDate.parse(dateString);
            Invoice newInvoice = new Invoice(totalValue, date,clientName);
            assertEquals(totalValue,newInvoice.value);
            assertEquals("Valber Azevedo",newInvoice.name);
            assertEquals(date,newInvoice.date);

        }catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInvoiceCreation2(){
        String dateString = "2022-11-23";
        try{
            String clientName = "Valber Azevedo";
            double totalValue = 234.87;
            Date date = dateFormatedDate.parse(dateString);
            Invoice newInvoice = new Invoice(totalValue, date,clientName);
            assertEquals("Pendente",newInvoice.status);
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPaymentCreation(){
        String dateString = "2023-12-12";
        try {
            Date date = dateFormatedDate.parse(dateString);
            double paidValue = 123.12;
            String payment = "BOLETO";
            Payment newPayment = new Payment(date,paidValue,payment);
            assertEquals(newPayment.value,paidValue);
        }catch (ParseException e){
        e.printStackTrace();
        }
    }

    @Test
    public void testPaymentWrongticketCreation(){
        String dateString = "2023-12-12";
        try {
            Date date = dateFormatedDate.parse(dateString);
            double paidValue = 5001;
            String payment = "BOLETO";
            assertThrows(IllegalArgumentException.class, () -> {
                accountProcessor.createPayment(date,paidValue,payment);
            });

        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testPaymentWrongticketCreation2(){
        String dateString = "2023-12-12";
        try {
            Date date = dateFormatedDate.parse(dateString);
            double paidValue = 0.001;
            String payment = "BOLETO";
            assertThrows(IllegalArgumentException.class, () -> {
                accountProcessor.createPayment(date,paidValue,payment);
            });

        }catch (ParseException e){
            e.printStackTrace();
        }
    }
}

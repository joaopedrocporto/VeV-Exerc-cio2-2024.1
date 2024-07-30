package accountProcessor.src.test.java.com;

import accountProcessor.src.main.java.com.Payment;
import accountProcessor.src.main.java.com.accountProcessorService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import javax.security.auth.login.AccountException;
import java.security.KeyStore;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class accountProcessorServiceTests {

    private SimpleDateFormat dateFormatedDate;

    @BeforeEach
    public void setUp() {
        dateFormatedDate = new SimpleDateFormat("yyyy-MM-dd");
        accountProcessorService accountProcessor = new accountProcessorService();
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
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }
    ublic void testAccountCreation2(){
        String dateString = "2023-12-12";
        try {
            Date date = dateFormatedDate.parse(dateString);
            double accountValue = 12.32;
            int accountCode = 12;
            Account newAccount = new Account(accountValue, accountCode, date);
            assertEquals(newAccount.status, "Pendente");
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
            PaymentType payment = "BOLETO";
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
            PaymentType payment = "BOLETO";
            assertThrows(InvalidTicketValueException.class, () -> {
                accountProcessor.createPayment(date,paidValue,payment);
            });

        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testPaymentWrongticketCreation(){
        String dateString = "2023-12-12";
        try {
            Date date = dateFormatedDate.parse(dateString);
            double paidValue = 0.001;
            PaymentType payment = "BOLETO";
            assertThrows(InvalidTicketValueException.class, () -> {
                accountProcessor.createPayment(date,paidValue,payment);
            });

        }catch (ParseException e){
            e.printStackTrace();
        }
    }
}

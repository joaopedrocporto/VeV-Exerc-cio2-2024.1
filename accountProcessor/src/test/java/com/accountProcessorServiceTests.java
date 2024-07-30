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

        try {

            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice newInvoice = new Invoice(234.87, dateInvoice,"Valber Azevedo");
            Date accountDate = dateFormatedDate.parse("2023-12-11");
            Account account = new Account(12.32, 12, accountDate);
            Date paymentDate = dateFormatedDate.parse("2023-12-12");
            String payment = "BOLETO";
            Payment newPayment = this.accountProcessor.payBill(newInvoice,account, paymentDate,account.value,payment);
        }catch (ParseException e){
            e.printStackTrace();
        }
    }


    @Test
    public void testPaymentCreationTicket(){

        try {

            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice newInvoice = new Invoice(234.87, dateInvoice,"Valber Azevedo");
            Date accountDate = dateFormatedDate.parse("2023-12-11");
            Account account = new Account(12.32, 12, accountDate);
            Date paymentDate = dateFormatedDate.parse("2023-12-12");
            String payment = "BOLETO";
            Payment newPayment = this.accountProcessor.payBill(newInvoice,account, paymentDate,account.value,payment);
        }catch (ParseException e){
            e.printStackTrace();
        }
    }
    @Test
    public void testPaymentCreationTransaction(){

        try {

            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice newInvoice = new Invoice(234.87, dateInvoice,"Valber Azevedo");
            Date accountDate = dateFormatedDate.parse("2023-12-11");
            Account account = new Account(12.32, 12, accountDate);
            Date paymentDate = dateFormatedDate.parse("2023-12-12");
            String payment = "TRANSFERENCIA_BANCARIA";
            Payment newPayment = this.accountProcessor.payBill(newInvoice,account, paymentDate,account.value,payment);
        }catch (ParseException e){
            e.printStackTrace();
        }
    }
    @Test
    public void testPaymentCreationCCreditCard(){

        try {

            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice newInvoice = new Invoice(234.87, dateInvoice,"Valber Azevedo");
            Date accountDate = dateFormatedDate.parse("2023-11-10");
            Account account = new Account(12.32, 12, accountDate);
            Date paymentDate = dateFormatedDate.parse("2023-12-12");
            String payment = "CARTAO_CREDITO";
            Payment newPayment = this.accountProcessor.payBill(newInvoice,account, paymentDate,account.value,payment);
            assertEquals(newPayment.invoice, newInvoice);
        }catch (ParseException e){
            e.printStackTrace();
        }
    }
    @Test
    public void testPaymentWrongticketCreation(){

        try {

            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice newInvoice = new Invoice(234.87, dateInvoice,"Valber Azevedo");
            Date accountDate = dateFormatedDate.parse("2023-12-11");
            Account account = new Account(5012.32, 11, accountDate);
            Date paymentDate = dateFormatedDate.parse("2023-12-12");
            String payment = "BOLETO";
            assertThrows(IllegalArgumentException.class, () -> {
                accountProcessor.payBill(newInvoice,account, paymentDate,account.value,payment);
            });
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testPaymenTicketAfterDateCreation(){

        try {

            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice newInvoice = new Invoice(234.87, dateInvoice,"Valber Azevedo");
            Date accountDate = dateFormatedDate.parse("2023-12-12");
            Account account = new Account(0.001, 10, accountDate);
            Date paymentDate = dateFormatedDate.parse("2023-12-12");
            String paymentMethod = "BOLETO";
            Payment payment = accountProcessor.payBill(newInvoice,account, paymentDate,account.value,paymentMethod);
            double afterTimeValue = account.value *1.1;
            assertEquals(payment.value,afterTimeValue);

        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testPaymentWrongDateCreation(){

        try {

            Date dateInvoice = dateFormatedDate.parse("2023-12-12");
            Invoice newInvoice = new Invoice(234.87, dateInvoice,"Valber Azevedo");
            Date accountDate = dateFormatedDate.parse("2023-12-12");
            Account account = new Account(0.001, 10, accountDate);
            Date paymentDate = dateFormatedDate.parse("2023-12-12");
            String payment = "BOLETO";
            assertThrows(IllegalArgumentException.class, () -> {
                accountProcessor.payBill(newInvoice,account, paymentDate,account.value,payment);
            });
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testPaymentWrongDateCreation(){

        try {

            Date dateInvoice = dateFormatedDate.parse("2023-12-12");
            Invoice newInvoice = new Invoice(234.87, dateInvoice,"Valber Azevedo");
            Date accountDate = dateFormatedDate.parse("2023-12-13");
            Account account = new Account(10, 10, accountDate);
            Date paymentDate = dateFormatedDate.parse("2023-12-13");
            String payment = "BOLETO";
            assertThrows(IllegalArgumentException.class, () -> {
                accountProcessor.payBill(newInvoice,account, paymentDate,account.value,payment);
            });
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testPaymentWrongDateCreation(){

        try {

            Date dateInvoice = dateFormatedDate.parse("2023-12-12");
            Invoice newInvoice = new Invoice(234.87, dateInvoice,"Valber Azevedo");
            Date accountDate = dateFormatedDate.parse("2023-12-13");
            Account account = new Account(10, 10, accountDate);
            Date paymentDate = dateFormatedDate.parse("2023-12-13");
            String payment = "TRANSFERENCIA_BANCARIA";
            assertThrows(IllegalArgumentException.class, () -> {
                accountProcessor.payBill(newInvoice,account, paymentDate,account.value,payment);
            });
        }catch (ParseException e){
            e.printStackTrace();
        }
    }
    @Test
    public void testPaymentWrongDateCreation(){

        try {

            Date dateInvoice = dateFormatedDate.parse("2023-12-13");
            Invoice newInvoice = new Invoice(234.87, dateInvoice,"Valber Azevedo");
            Date accountDate = dateFormatedDate.parse("2023-12-10");
            Account account = new Account(10, 10, accountDate);
            Date paymentDate = dateFormatedDate.parse("2023-12-12");
            String payment = "CARTAO_CREDITO";
            assertThrows(IllegalArgumentException.class, () -> {
                accountProcessor.payBill(newInvoice,account, paymentDate,account.value,payment);
            });
        }catch (ParseException e){
            e.printStackTrace();
        }
    }


}

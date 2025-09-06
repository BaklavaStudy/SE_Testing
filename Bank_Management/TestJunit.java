package Bank_Management;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;


import java.io.ByteArrayInputStream;
import java.util.InputMismatchException;


public class TestJunit {

    // @BeforeEach
    // public void beforeAll() {
    //     Bank bank = new Bank();
    // }

    @Test
    public void CreateAccountNull() {
        Account account = new Account();
        assertEquals(account.name, null);
        assertEquals(account.account_number, 0);
        assertEquals(account.pin, null);
        assertEquals(account.Amount, 0, 0);

    }

    @Test
    public void CreateAccount() {
        Account account = new Account("Adnan", 12345678, "1234", 0);
        Account invalidInputAccount = new Account("%^&!*@!123\nRAHHHHHHHHH", 12345678, "))))", 0);

        assertAll("Ensuring that all values are valid and don't provide a false negative",
                () -> assertEquals("Adnan", account.getName()),
                assertEquals(12345678, account.getAccountNumber()),
                assertEquals("1234", account.getPIN()),
                assertEquals(1000, account.getAmount(), 0),
                assertEquals("%^&!*@!123\nRAHHHHHHHHH", invalidInputAccount.getName()),
                assertEquals(12345678, invalidInputAccount.getAccountNumber()),
                assertEquals("))))", invalidInputAccount.getPIN()),
                assertEquals(1000, invalidInputAccount.getAmount(), 0)
        );
    }

        assertEquals("Adnan", account.getName());
        assertEquals(12345678, account.getAccountNumber());
        assertEquals("1234", account.getPIN());
        assertEquals(1000, account.getAmount(), 0);
        
        Account invalidInputAccount = new Account("%^&!*@!123\nRAHHHHHHHHH", 12345678, "))))", 0);
        assertEquals("%^&!*@!123\nRAHHHHHHHHH", invalidInputAccount.getName());
        assertEquals(12345678, invalidInputAccount.getAccountNumber());
        assertEquals("))))", invalidInputAccount.getPIN());
        assertEquals(1000, invalidInputAccount.getAmount(), 0);        
    }
    
    @Test
    public void FailedAccount() {
        // False positive checker.
        Account account = new Account("Adnan", 12345678, "1234", 0);

        assertNotEquals("Raphael", account.getName());
        assertNotEquals(1, account.getAccountNumber());
        assertNotEquals("6754", account.getPIN());
        assertNotEquals(2000, account.getAmount(), 0);

        
        // Checking for obscure values (negatives & nulls)
        Account account1 = new Account("  Riley        ", 1, "", -2000);
        assertNotEquals("Riley", account1.getName());
        assertNotEquals(-1, account1.getAccountNumber());
        assertNotEquals(" ", account1.getPIN());
        assertNotEquals(1000, account1.getAmount());
    }


    // Come back to this laters
    @Test
    public void RecordAddition() {
        Bank bank = new Bank();
        System.setIn(new ByteArrayInputStream("%^&!*@!123\nRAHHHHHHHHH\n4738!!11\nHIII\nThisisaNumber!!".getBytes()));

        assertThrows(InputMismatchException.class, () -> bank.addNewRecord());
    }

    // Test cases for Transfers, testing high values, negative values, null values, then invalid data types
    @Test
    @SpiraTestCase(testCaseId = 12976)
    public void transferExcessivelyLargeFund() {
        Account account1 = new Account("Jane", 47382910, "4321", 500); // Sender
        Account account2 = new Account("Alice", 2147483647, "3241", 0);  // Receiver
        bank.AL.add(account1);
        bank.AL.add(account2);

        System.setIn(new ByteArrayInputStream("47382910\n4321\n2147483647\n2000".getBytes()));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        bank.transfer();

        assertTrue(output.toString().contains("\nSender does not have this much balance in his account"));
    }

    @Test
    @SpiraTestCase(testCaseId = 12977)
    public void accountNumAndTransferAmountWrongDataTypes() {
        Account account1 = new Account("Jane", 47382910, "4321", 500); // Sender
        Account account2 = new Account("Alice", 29384756, "3241", 0);  // Receiver
        bank.AL.add(account1);
        bank.AL.add(account2);

        System.setIn(new ByteArrayInputStream("wrong\ninput\ndata\ntype".getBytes()));

        assertThrows(InputMismatchException.class, () -> bank.transfer());
    }

}
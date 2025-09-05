package Bank_Management;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class TestJunit {
    @Test
    public void testCompare() {
        String str = "Junit is working fine";
        assertEquals("Junit is working fine",str);
    }

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

        assertEquals("Adnan", account.getName());
        assertEquals(12345678, account.getAccountNumber());
        assertEquals("1234", account.getPIN());
        assertEquals(1000, account.getAmount(), 0);
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
        Account account1 = new Account("Riley        ", 1, "", -2000);
        assertNotEquals("Riley", account1.getName());
        assertNotEquals(-1, account1.getAccountNumber());
        assertNotEquals(" ", account1.getPIN());
        assertNotEquals(1000, account1.getAmount());
    }
}
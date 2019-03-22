
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CustomerTest {
	Customer customer;
	@Before
	public void setup(){
		customer = new Customer("test", "password");
	}

	@Test
	public void testAddAccount(){
		customer.addAccount("chequing", "c_account");
		Assert.assertEquals(1, customer.getAccounts().size());
		Assert.assertTrue(customer.getAccounts().get(0) instanceof ChequingAcc);
	}

	@Test
	public void testGetFullSummary(){}

	@Test
	public void testGetNetTotal(){}

	@Test
	public void testGetAccountByName(){
		customer.addAccount("chequing", "c_account");
		Assert.assertEquals("c_account", customer.getAccountByName("c_account").name);
	}

}
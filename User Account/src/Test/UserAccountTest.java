package Test;


import Production.UserAccount;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserAccountTest  extends TestCase {

	private UserAccount userAccount;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
			userAccount = new UserAccount("admin", "123456", "Alex Esplin",
					"alexesplin1@u.boisestate.edu", "Chicken");
	}

	@Test
	public void testNewAccount(){
		assertNotNull(userAccount);
		assertEquals("admin", userAccount.getUserName());
		assertEquals("123456", userAccount.getPassword());
		assertEquals("Alex Esplin", userAccount.getName());
		assertEquals("alexesplin1@u.boisestate.edu", userAccount.getEmail());
		assertEquals("Chicken", userAccount.getSecurityQuestion());
	}

	@Test
	public void testValidCredential() {
		assertNotNull(userAccount);
		assertTrue(userAccount.matchUserName("admin"));
		assertTrue(userAccount.isValidCredential("admin", "123456"));
	}

	@Test
	public void testChangeAccount(){
		assertNotNull(userAccount);
		userAccount.setUserName("coolguy");
		assertEquals("coolguy", userAccount.getUserName());
		userAccount.setPassword("hunter2");
		assertEquals("hunter2", userAccount.getPassword());
		userAccount.setEmail("someguy@gmail.com");
		assertEquals("someguy@gmail.com", userAccount.getEmail());
		userAccount.setName("Kevin");
		assertEquals("Kevin", userAccount.getName());
		userAccount.setSecurityQuestion("Beef");
		assertEquals("Beef", userAccount.getSecurityQuestion());
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		userAccount = null;
		assertNull(userAccount);
	}
}

package test;


import production.UserAccount;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;

public class UserAccountTest  extends TestCase {

	private UserAccount userAccount;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
			userAccount = new UserAccount("admin", "123456", "Alex Esplin",
					"alexesplin1@u.boisestate.edu", "Chicken");
	}

	public void testNewAccount(){
		assertEquals("admin", userAccount.getUserName());
		assertEquals("123456", userAccount.getPassword());
		assertEquals("Alex Esplin", userAccount.getName());
		assertEquals("alexesplin1@u.boisestate.edu", userAccount.getEmail());
		assertEquals("Chicken", userAccount.getSecurityQuestion());
	}

	public void testSetUserName(){
        assertSame("admin", userAccount.getUserName());
	    userAccount.setUserName("user");
        assertSame("user", userAccount.getUserName());
    }

	public void testSetPassword(){
        assertSame("123456", userAccount.getPassword());
	    userAccount.setPassword("987654");
        assertSame("987654", userAccount.getPassword());
    }

    public void testSetName(){
        assertSame("Alex Esplin", userAccount.getName());
	    userAccount.setName("Kevin Brown");
        assertSame("Kevin Brown", userAccount.getName());
    }

    public void testSetEmail(){
        assertSame("alexesplin1@u.boisestate.edu", userAccount.getEmail());
	    userAccount.setEmail("newemail@email.com");
        assertSame("newemail@email.com", userAccount.getEmail());
    }

    public void testSetSecurityQuestion(){
        assertSame("Chicken", userAccount.getSecurityQuestion());
	    userAccount.setSecurityQuestion("Hamburger");
        assertSame("Hamburger", userAccount.getSecurityQuestion());
    }

	public void testValidCredential() {
		assertTrue(userAccount.matchUserName("admin"));
		assertTrue(userAccount.isValidCredential("admin", "123456"));
	}

	public void testChangeAccount(){
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
	}
}

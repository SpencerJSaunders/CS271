package Test;


import Production.UserAccountManager;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserAccountManagerTest extends TestCase {

	private UserAccountManager userAccountManager;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		userAccountManager = new UserAccountManager();
		userAccountManager.addUserAccount("admin", "123456", "Alex Esplin",
				"alexesplin1@u.boisestate.edu", "Chicken");
		userAccountManager.addUserAccount("phin", "secretpassword", "Bob",
				"bob@gmail.com", "hotdogs");
	}

	@Test
	public void testExistingUserAccount() {
		assertNotNull(userAccountManager);
		assertTrue(userAccountManager.doesUserNameExist("admin"));
		assertTrue(userAccountManager.doesAccountExist("admin", "123456"));
		assertTrue(userAccountManager.doesUserNameExist("phin"));
		assertTrue(userAccountManager.doesAccountExist("phin", "secretpassword"));
	}

	@Test
	public void testNonExistantUserAccount() {
		assertNotNull(userAccountManager);
		assertFalse(userAccountManager.doesUserNameExist("BSU"));
		assertFalse(userAccountManager.doesAccountExist("BSU", "hello"));
	}

	@Test
	public void testRightUsernameWrongPassword(){
		assertNotNull(userAccountManager);
		assertTrue(userAccountManager.doesUserNameExist("admin"));
		assertFalse(userAccountManager.doesAccountExist("admin", "hello"));
	}

	@Test
	public void testWrongUsernameRightPassword(){
		assertNotNull(userAccountManager);
		assertFalse(userAccountManager.doesUserNameExist("coolguy"));
		assertFalse(userAccountManager.doesAccountExist("coolguy", "123456"));
	}

	@Test
	public void testPasswordValidity(){
		assertNotNull(userAccountManager);
		assertFalse(userAccountManager.validPassword(""));
		assertFalse(userAccountManager.validPassword("jjjjjjjjjjjjjjjjjjjjjjjj"));
		assertTrue(userAccountManager.validPassword("123456"));
		assertTrue(userAccountManager.validPassword("hunter2"));
	}

	@Test
	public void testEmailValidity(){
		assertNotNull(userAccountManager);
		assertFalse(userAccountManager.validEmail(""));
		assertFalse(userAccountManager.validEmail("thisIsMyEmail"));
		assertTrue(userAccountManager.validEmail("someguy@gmail.com"));
	}

	@Test
	public void testSecurityQuestionValidity(){
		assertNotNull(userAccountManager);
		assertFalse(userAccountManager.validSecurityQuestion(""));
		assertTrue(userAccountManager.validSecurityQuestion("Chicken"));
		assertTrue(userAccountManager.validSecurityQuestion("burger and fries"));
	}

	@After
	public void tearDown() throws Exception{
		super.tearDown();
		userAccountManager = null;
	}
}

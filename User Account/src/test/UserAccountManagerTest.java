package test;



import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import production.UserAccountManager;


public class UserAccountManagerTest extends TestCase {

	private UserAccountManager userAccountManager;

	@Before
	protected void setUp() {
		userAccountManager = new UserAccountManager();
		userAccountManager.addUserAccount("admin", "123456", "Alex Esplin",
				"alexesplin1@u.boisestate.edu", "Chicken");
		userAccountManager.addUserAccount("phin", "secretpassword", "Bob",
				"bob@gmail.com", "hotdogs");
	}

	public void testExistingUserAccount() {
		assertTrue(userAccountManager.doesUserNameExist("admin"));
		assertTrue(userAccountManager.doesAccountExist("admin", "123456"));
		assertTrue(userAccountManager.doesUserNameExist("phin"));
		assertTrue(userAccountManager.doesAccountExist("phin", "secretpassword"));
	}

	public void testNonExistantUserAccount() {
		assertFalse(userAccountManager.doesUserNameExist("BSU"));
		assertFalse(userAccountManager.doesAccountExist("BSU", "hello"));
	}

	public void testRightUsernameWrongPassword(){
		assertTrue(userAccountManager.doesUserNameExist("admin"));
		assertFalse(userAccountManager.doesAccountExist("admin", "hello"));
	}

	public void testWrongUsernameRightPassword(){
		assertFalse(userAccountManager.doesUserNameExist("coolguy"));
		assertFalse(userAccountManager.doesAccountExist("coolguy", "123456"));
	}

	public void testPasswordValidity(){
		assertFalse(userAccountManager.validPassword(""));
		assertFalse(userAccountManager.validPassword("jjjjjjjjjjjjjjjjjjjjjjjj"));
		assertTrue(userAccountManager.validPassword("123456"));
		assertTrue(userAccountManager.validPassword("hunter2"));
	}

	public void testEmailValidity(){
		assertFalse(userAccountManager.validEmail(""));
		assertFalse(userAccountManager.validEmail("thisIsMyEmail"));
		assertTrue(userAccountManager.validEmail("someguy@gmail.com"));
	}

	public void testSecurityQuestionValidity(){
		assertFalse(userAccountManager.validSecurityQuestion(""));
		assertTrue(userAccountManager.validSecurityQuestion("Chicken"));
		assertTrue(userAccountManager.validSecurityQuestion("burger and fries"));
	}

	@After
	public void tearDown() {
	}
}

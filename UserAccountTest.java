package Test;


import Production.UserAccount;
import junit.framework.TestCase;

public class UserAccountTest extends TestCase {

	private UserAccount userAccount;
	
	public void testNewAccount() {
		userAccount = new UserAccount("admin", "123456", "Kevin Brown");
		assertNotNull(userAccount);
		assertTrue(userAccount.matchUserName("admin"));
		assertTrue(userAccount.isValidCredential("admin", "123456"));
	}

}

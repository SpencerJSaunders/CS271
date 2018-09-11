package Production;
import java.util.ArrayList;

/**
 *
 * Class to manage all the user accounts. Has a data structure to store the user account objects. Also has methods to
 * check that the accounts are valid.
 * @author Team 1
 */
public class UserAccountManager {
	
    private ArrayList<UserAccount> userAccounts;
    
    public UserAccountManager() {
        userAccounts = new ArrayList<>();
    }
    
    public void addUserAccount(String userName, String password, String name, String email, String securityQuestion){
    	if (!doesUserNameExist(userName) && validPassword(password) && validEmail(email))
    		userAccounts.add(new UserAccount(userName,password, name, email, securityQuestion));
    }
    
    public boolean doesAccountExist(String userName, String password) {
    	for (UserAccount userAccount: userAccounts)
    		if(userAccount.isValidCredential(userName, password))    
    			return true;   
       return false;
    }
    
    public boolean doesUserNameExist(String userName){
    	for (UserAccount userAccount: userAccounts)
    		if(userAccount.matchUserName(userName))   
    			return true;   
       return false;
    }

    public boolean validPassword(String password){
        boolean valid = true;
        if (password.length() <= 0 || password.length() > 20) {
            valid = false;
        }
        return valid;

    }
    public boolean validEmail(String email){
        boolean valid = true;
        if(email.length() <= 0){
            valid = false;
        }
        if(!email.contains("@")){
            valid = false;
        }


            return valid;
    }

    public boolean validSecurityQuestion(String securityQuestion){
        boolean valid = true;
        if(securityQuestion.length() <= 0){
            valid = false;
        }
        return valid;
    }
}

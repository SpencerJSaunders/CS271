package production;
import java.util.ArrayList;

/**
 *
 * Class to manage all the user accounts. Has a data structure to store the user account objects. Also has methods to
 * check that the accounts are valid.
 * @author Team 1
 */
public class UserAccountManager {
	//Data structure all user accounts are stored in
    private ArrayList<UserAccount> userAccounts;

    /**
     * Constructor
     * just initialized the userAccounts data structure.
     */
    public UserAccountManager() {
        userAccounts = new ArrayList<>();
    }

    /**
     * Adds a new user account if the username does not exist, and if it is given a valid email and password
     * @param userName desired username
     * @param password desired password
     * @param name the name of the user
     * @param email the users email
     * @param securityQuestion answer to security question asked on account creation
     */
    public void addUserAccount(String userName, String password, String name, String email, String securityQuestion){
    	if (!doesUserNameExist(userName) && validPassword(password) && validEmail(email))
    		userAccounts.add(new UserAccount(userName,password, name, email, securityQuestion));
    }

    /**
     * checks if an account with this username and password combo already exist
     * @param userName username to be checked
     * @param password password to be checkec
     * @return returns true if the account exists, false otherwise
     */
    public boolean doesAccountExist(String userName, String password) {
    	for (UserAccount userAccount: userAccounts)
    		if(userAccount.isValidCredential(userName, password))    
    			return true;   
       return false;
    }

    /**
     * Checks to see if the entered username already exists
     * @param userName desired username
     * @return true if the username exists, false otherwise
     */
    public boolean doesUserNameExist(String userName){
    	for (UserAccount userAccount: userAccounts)
    		if(userAccount.matchUserName(userName))   
    			return true;   
       return false;
    }
    
    public boolean newUserNameChecks(String newUsername, String currentUsername)
    {
    	if(newUsername != currentUsername && newUsername.length() > 0 && !doesUserNameExist(newUsername))
    			{
    				return true;
    			}
    	
    	return false;
    }
    
    public boolean newEmailChecks(String newEmail, String currentEmail)
    {
    	if(newEmail != currentEmail && validEmail(newEmail) && doesEmailExist(newEmail))
    	{
    		return true;
    	}
    	return false;
    }
    public boolean doesEmailExist(String email)
    {
    	for(UserAccount userAccount: userAccounts)
    	{
    		if(userAccount.getEmail().equals(email))
    		{
    			return false;
    		}
    	}
    	
    	return true;
    }

    /**
     * Updates an accounts password
     * @param i index of the account whose password is being changed
     * @param stringy the new password desired
     */
    public void updatePassword(int i, String stringy)
    {
    	userAccounts.get(i).setPassword(stringy);
    }

    /**
     * Recovers a password that belongs to an account. Checks if the username and security question answer match the
     * account, if so then the password is returned
     * @param userName entered username
     * @param securityQuestion enteres security question answer
     * @return account password if username and security question match, null otherwise
     */
    public String passwordRecovery(String userName, String securityQuestion)
    {
    	for(UserAccount userAccount: userAccounts)
    	{
    		if(userAccount.matchUserName(userName) && userAccount.getSecurityQuestion().equals(securityQuestion))
    		{
    			return userAccount.getPassword();
    		}
    	}
    		return null;
    }

    /**
     * Method to ensure that an inputted password is valid. It must be a length greater
     * than zero and less than or equal to 20
     * @param password password to be checked
     * @return true is it passes
     */
    public boolean validPassword(String password){
        boolean valid = true;
        if (password.length() <= 5 || password.length() > 20) {
            valid = false;
        }
        return valid;

    }

    /**
     * returns the index of the userAccount in the userAccounts data structure
     * @param userName username to search for
     * @return the index if found. If not found returns -1
     */
    public int getIndexOfUser(String userName)
    {
    	for(int i = 0; i < userAccounts.size(); i++)
    	{
    		if(userAccounts.get(i).getUserName().equals(userName))
    		{
    			return i; 
    		}
    	}
    	
    	return -1;
    }
    
    public UserAccount retrieveAccount(int index)
    {
    	if(userAccounts.size() > 0 && index >= 0 && index < userAccounts.size())
    	{
    		return userAccounts.get(index);
    	}
    	
    	return null;
    }

    /**
     * checks that an email is valid. To be valid is must be at least one character
     * and it must contain the '@' symbol
     * @param email email to check
     * @return true if its valid, false otherwise
     */
    public boolean validEmail(String email){
        boolean valid = true;
        if(email.length() <= 0){
            valid = false;
        }
        if(!email.contains("@") || !email.contains(".com")){
            valid = false;
        }
            return valid;
    }
    
    public String recoverUsername(String email, String securityQuestion)
    {
    	for (UserAccount userAccount: userAccounts)
		{
			if(userAccount.getEmail().equals(email) && userAccount.getSecurityQuestion().equals(securityQuestion))
			{
				return userAccount.getUserName();
			}
		}
		
		return null;
    }

    /**
     * Checks that the security question has been answered. Answer must be at least one character
     * @param securityQuestion answer to the security question
     * @return true if it has been answered, false otherwise
     */
    public boolean validSecurityQuestion(String securityQuestion){
        boolean valid = true;
        if(securityQuestion.length() <= 0){
            valid = false;
        }
        return valid;
    }
}
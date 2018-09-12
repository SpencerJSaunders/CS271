package Production;

/**
 * Class for the UserAccount object. Stores the username, password, email, and security question for the user
 * Has methods to check username and password, used to verify login attempts match stored username and password.
 * @author Team 1
 */
public class UserAccount {

    private String userName;
    private String password;
    private String name;
    private String email;
    private String securityQuestion;

    /**
     * Construtor. Stores the inputted user data when a user makes an account.
     * @param userName the username given on signup
     * @param password password given on sign up
     * @param name the name of the user
     * @param email the users email
     * @param securityQuestion answer to the security question
     */
    public UserAccount(String userName, String password, String name, String email, String securityQuestion) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
        this.securityQuestion = securityQuestion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getSecurityQuestion(){
        return securityQuestion;
    }
    public void setSecurityQuestion(String securityQuestion){
        this.securityQuestion = securityQuestion;
    }

    /**
     * Method to check that the entered username and password are
     * the correct username and password for the account
     * @param userName the username being checked for validity
     * @param password the password being checked for validity
     * @return true if the credential is valid, false otherwise
     */
    public boolean isValidCredential(String userName, String password) {
        return matchUserName(userName) && matchPassword(password);
    }

    /**
     * Checks that the username inputted is a match to the username already stored
     * @param userName the entered username on login attemt
     * @return true if the username entered matches, false otherwise
     */
    public boolean matchUserName(String userName) {
        return userName != null && userName.equals(this.userName);
    }

    /**
     * Checks that the password inputted is a match to the password already stored
     * @param password the entered password on login attempt
     * @return true if the password entered matches, false otherwise
     */
    private boolean matchPassword(String password) {
        return password != null && password.equals(this.password);
    }

}
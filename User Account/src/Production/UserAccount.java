package Production;

public class UserAccount {

    private String userName;
    private String password;
    private String name;
    private String email;
    private String securityQuestion;

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

    public boolean isValidCredential(String userName, String password) {
        return matchUserName(userName) && matchPassword(password);
    }

    public boolean matchUserName(String userName) {
        return userName != null && userName.equals(this.userName);
    }

    private boolean matchPassword(String password) {
        return password != null && password.equals(this.password);
    }

}
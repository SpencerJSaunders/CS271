package Production;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Production.UserAccountManager;

/**
 * 
 * This program creates the UserAccount GUI Panel.
 * 
 * @author Team One
 *
 */

public class UserAccountGUIPanel extends JPanel
{
	//variables initialized
	private static final long serialVersionUID = 1L;
	private UserAccountManager accountManager;
	private JButton loginButton;
	private JButton signupButton;
	private JButton forgotPasswordButton; 
	private JButton forgotUsernameButton;
	private JLabel usernameExists;
	private JLabel invalidPassword;
	private JLabel invalidEmail;
	private JLabel invalidSecurityQuestion;
	private JPanel statusPanel;
	private JPanel loginSignupPanel;
	
	/**
	 * Initializes the main GUI panel.
	 */
	public UserAccountGUIPanel()
	{
		
		//stores all UserAccounts
		accountManager = new UserAccountManager();
		
		setLayout(new BorderLayout());
		loginSignupPanel = new JPanel();
		add(loginSignupPanel, BorderLayout.NORTH);
		
		//login button added
		loginButton = new JButton("Login");
		JButtonListener listener = new JButtonListener();
		loginButton.addActionListener(listener);
		
		//sign up button added
		signupButton = new JButton("Sign Up");
		listener = new JButtonListener();
		signupButton.addActionListener(listener);
		
		forgotPasswordButton = new JButton("Forgot Password");
		listener = new JButtonListener();
		forgotPasswordButton.addActionListener(listener);
		
		forgotUsernameButton = new JButton("Forgot Username");
		listener = new JButtonListener();
		forgotUsernameButton.addActionListener(listener);
		
		//add buttons to the loginSignupPanel JPanel
		loginSignupPanel.setLayout(new BoxLayout(loginSignupPanel, BoxLayout.X_AXIS));
		loginSignupPanel.add(loginButton);
		loginSignupPanel.add(signupButton);
		loginSignupPanel.add(forgotPasswordButton);
		loginSignupPanel.add(forgotUsernameButton);
	
	}
	
	/**
	 * 
	 * Handles events from the JButtons.
	 * Does various tasks, depending on which JButton was pushed.
	 * 
	 */
	private class JButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent event) 
		{
			
			//if user clicks on the login button, open a new dialogPanel
			if(event.getSource() == forgotUsernameButton)
			{
				JTextField emailField = new JTextField(1);
				JTextField securityField = new JTextField(5);
				JPanel dialogPanel = new JPanel();
				dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
				dialogPanel.add(new JLabel("Enter your email: "));
				dialogPanel.add(emailField);
				dialogPanel.add(new JLabel("Enter your security question: "));
				dialogPanel.add(securityField);
				
				int result = JOptionPane.showConfirmDialog(null, dialogPanel, 
		                 "Recover your Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);	
				
				if(result == JOptionPane.OK_OPTION)
				{
					if(accountManager.recoverUsername(emailField.getText(), securityField.getText()) != null)
					{
					statusPanel = new JPanel();
					statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
					String yourUsername = accountManager.recoverUsername(emailField.getText(), securityField.getText());
					statusPanel.add(new JLabel("Your username is " + yourUsername));
					JOptionPane.showConfirmDialog(null, statusPanel, 
         					"Success!", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

					}
					else
					{
						statusPanel = new JPanel();
						statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
						statusPanel.add(new JLabel("Sorry, we're unable to match your provided info with any accounts. Please try entering your information again or register for a new account!"));
						JOptionPane.showConfirmDialog(null, statusPanel, 
	         					"Uh oh!", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
					}
				}

				}
			if(event.getSource() == forgotPasswordButton)
			{
				JTextField usernameField = new JTextField(1);
				JTextField securityField = new JTextField(5);
				JPanel dialogPanel = new JPanel();
				dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
				dialogPanel.add(new JLabel("Enter your username: "));
				dialogPanel.add(usernameField);
				dialogPanel.add(new JLabel("Enter your security question: "));
				dialogPanel.add(securityField);
				
				int result = JOptionPane.showConfirmDialog(null, dialogPanel, 
		                 "Recover your Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
				if(result == JOptionPane.OK_OPTION)
				{
					if(accountManager.passwordRecovery(usernameField.getText(), securityField.getText()) != null)
					{
						JTextField newPassword = new JTextField(1);
						JTextField newPasswordConfirm = new JTextField(5);
						statusPanel = new JPanel();
						statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
						
						statusPanel.add(new JLabel("Enter your new password: "));
						statusPanel.add(newPassword);
						statusPanel.add(new JLabel("Enter your new password again for confirmation: "));
						statusPanel.add(newPasswordConfirm);
						int index = accountManager.getIndexOfUser(usernameField.getText());
						
						JOptionPane.showConfirmDialog(null, statusPanel,
								"Enter your new password", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
						
						if(newPassword.getText().equals(newPasswordConfirm.getText()))
						{
							statusPanel = new JPanel();
							statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
							statusPanel.add(new JLabel("Password updated"));
							accountManager.updatePassword(index, newPassword.getText());
							JOptionPane.showConfirmDialog(null, statusPanel, 
		         					"Success", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							statusPanel = new JPanel();
							statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
							statusPanel.add(new JLabel("New Passwords Don't Match"));
							statusPanel.add(new JLabel("Passwords Don't Match"));

							JOptionPane.showConfirmDialog(null, statusPanel, 
		         					"Success", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else
					{
						statusPanel = new JPanel();
	         			statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
	         			usernameExists = new JLabel("Incorret information entered! Please try again!");
	         			statusPanel.add(usernameExists);
	         			JOptionPane.showConfirmDialog(null, statusPanel, 
	         					"Incorrect Info", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
				
				
				
			}
			if(event.getSource() == loginButton)
			{
				
				JTextField usernameField = new JTextField(1);
				JTextField passwordField = new JTextField(5);
				JPanel dialogPanel = new JPanel();
				dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
				dialogPanel.add(new JLabel("Username:"));
				dialogPanel.add(usernameField);
				dialogPanel.add(new JLabel("Password:"));
				dialogPanel.add(passwordField);

				int result = JOptionPane.showConfirmDialog(null, dialogPanel, 
			                 "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
				if(result == JOptionPane.OK_OPTION)
				{
					
					if(accountManager.doesAccountExist(usernameField.getText(), passwordField.getText()))
	         	    {
	         	    	
						statusPanel = new JPanel();
	         			statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
	         			usernameExists = new JLabel("Login Successful");
	         			statusPanel.add(usernameExists);
	         			JOptionPane.showConfirmDialog(null, statusPanel, 
	         					"Success", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
	         			remove(loginSignupPanel);
	         			add(new JLabel("Welcome!"));
	         			revalidate();
	         			repaint();


						
	         	    }
	        	    else
	        	    {
	           	    	
	         			statusPanel = new JPanel();
	         			statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
	         			usernameExists = new JLabel("Username/Password doesn't exist! Please try again.");
	         			statusPanel.add(usernameExists);
	         			JOptionPane.showConfirmDialog(null, statusPanel, 
	         					"Error", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
	        	    	
	        	    }
					
				}
			}
			
			
			
			if(event.getSource() == signupButton)
			{
				JTextField nameField = new JTextField(1);
				JTextField usernameField = new JTextField(1);
				JTextField passwordField = new JTextField(5);
				JTextField emailField = new JTextField(2);
				JTextField securityField = new JTextField(2);
				JPanel dialogPanel = new JPanel();
				dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
				dialogPanel.add(new JLabel("Name:"));
				dialogPanel.add(nameField);
				dialogPanel.add(new JLabel("Username:"));
				dialogPanel.add(usernameField);
				dialogPanel.add(new JLabel("Password:"));
				dialogPanel.add(passwordField);
				dialogPanel.add(new JLabel("Email:"));
				dialogPanel.add(emailField);
				dialogPanel.add(new JLabel("Security Question: Favorite Food?"));
				dialogPanel.add(securityField);

				int result = JOptionPane.showConfirmDialog(null, dialogPanel, 
			                 "Sign Up", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
				if(result == JOptionPane.OK_OPTION)
				{
					
	         	    if(accountManager.doesUserNameExist(usernameField.getText()))
	         	    {
	        	    	
	         			statusPanel = new JPanel();
	         			statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
	         			usernameExists = new JLabel("Username already exists! Please enter a different one.");
	         			statusPanel.add(usernameExists);
	         			JOptionPane.showConfirmDialog(null, statusPanel, 
	         					"Error", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
	         	    	
	         	    } else if(!accountManager.validPassword(passwordField.getText())){
	         	    	statusPanel = new JPanel();
	         	    	statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
	         	    	invalidPassword = new JLabel("Invalid password: Length must be > 0 and <=20");
	         	    	statusPanel.add(invalidPassword);
	         	    	JOptionPane.showConfirmDialog(null, statusPanel,
								"Error", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

					} else if(!accountManager.validEmail(emailField.getText())){
						statusPanel = new JPanel();
						statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
						invalidEmail = new JLabel("Invalid Email");
						statusPanel.add(invalidEmail);
						JOptionPane.showConfirmDialog(null, statusPanel,
								"Error", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
					} else if(!accountManager.validSecurityQuestion(securityField.getText())){
						statusPanel = new JPanel();
						statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
						invalidSecurityQuestion = new JLabel("Security question cannot be left blank");
						statusPanel.add(invalidSecurityQuestion);
						JOptionPane.showConfirmDialog(null, statusPanel,
								"Error", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
					}
	        	    else
	        	    {
	           	    	
	        	    	accountManager.addUserAccount(usernameField.getText(), passwordField.getText(), nameField.getText(),
								emailField.getText(), securityField.getText());
	        	    	
	        	    }
					
					
				}
			}
		}
	}
}
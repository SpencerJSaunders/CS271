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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserAccountManager accountManager;
	private JButton loginButton;
	private JButton signupButton;
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
		
		accountManager = new UserAccountManager();
		
		setLayout(new BorderLayout());
		loginSignupPanel = new JPanel();
		add(loginSignupPanel, BorderLayout.NORTH);
		
		loginButton = new JButton("Login");
		JButtonListener listener = new JButtonListener();
		loginButton.addActionListener(listener);
		signupButton = new JButton("Sign Up");
		listener = new JButtonListener();
		signupButton.addActionListener(listener);
		
		loginSignupPanel.setLayout(new BoxLayout(loginSignupPanel, BoxLayout.X_AXIS));
		loginSignupPanel.add(loginButton);
		loginSignupPanel.add(signupButton);
	
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
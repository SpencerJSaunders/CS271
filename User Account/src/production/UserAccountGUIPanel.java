package production;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * This program creates the UserAccount GUI Panel.
 * 
 * @author Team One
 *
 */

public class UserAccountGUIPanel extends JPanel {
	// variables initialized
	private static final long serialVersionUID = 1L;
	private UserAccountManager accountManager;
	private JButton loginButton;
	private JButton signupButton;
	private JLabel forgotPasswordButton;
	private JLabel forgotUsernameButton;
	private JButton editAccount;
	private JLabel usernameExists;
	private JLabel invalidPassword;
	private JLabel invalidEmail;
	private JLabel invalidSecurityQuestion;
	private JPanel statusPanel;
	private JPanel loginSignupPanel;
	private GridBagConstraints cs;

	/**
	 * Initializes the main GUI panel.
	 */
	public UserAccountGUIPanel() {

		// stores all UserAccounts
		accountManager = new UserAccountManager();

		setLayout(new BorderLayout());
		loginSignupPanel = new JPanel(new GridBagLayout());
		cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.CENTER;
		add(loginSignupPanel, BorderLayout.CENTER);

		// login button added
		loginButton = new JButton("Login");
		JButtonListener listener = new JButtonListener();
		loginButton.addActionListener(listener);

		// sign up button added
		signupButton = new JButton("Sign Up");
		listener = new JButtonListener();
		signupButton.addActionListener(listener);

		MouseAdapter mouseAdapter = new MouseAdapter();
		forgotPasswordButton = new JLabel("Forgot Password?");
		forgotPasswordButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		forgotPasswordButton.addMouseListener(mouseAdapter);

		forgotUsernameButton = new JLabel("Forgot Username?");
		forgotUsernameButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		forgotUsernameButton.addMouseListener(mouseAdapter);

		editAccount = new JButton("Edit Account");
		listener = new JButtonListener();
		editAccount.addActionListener(listener);

		// add buttons to the loginSignupPanel JPanel
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		loginSignupPanel.add(loginButton, cs);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 1;
		loginSignupPanel.add(signupButton, cs);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 1;
		loginSignupPanel.add(forgotPasswordButton, cs);
		cs.gridx = 1;
		cs.gridy = 2;
		cs.gridwidth = 1;
		loginSignupPanel.add(forgotUsernameButton, cs);
	}

	private class MouseAdapter implements MouseListener {

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// if user clicks on the forgot username link, open a new dialogPanel to recover
			// username
			if (e.getSource() == forgotUsernameButton) {
				JTextField emailField = new JTextField(1);
				JTextField securityField = new JTextField(5);
				JPanel dialogPanel = new JPanel();
				dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
				dialogPanel.add(new JLabel("Enter your email: "));
				dialogPanel.add(emailField);
				dialogPanel.add(new JLabel("What is your favorite food: "));
				dialogPanel.add(securityField);

				int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Recover your username",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {
					if (accountManager.recoverUsername(emailField.getText(), securityField.getText()) != null) {
						statusPanel = new JPanel();
						statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
						String yourUsername = accountManager.recoverUsername(emailField.getText(),
								securityField.getText());
						statusPanel.add(new JLabel("Your username is " + yourUsername));
						JOptionPane.showConfirmDialog(null, statusPanel, "Success!", JOptionPane.CANCEL_OPTION,
								JOptionPane.INFORMATION_MESSAGE);

					} else {
						statusPanel = new JPanel();
						statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
						statusPanel.add(new JLabel(
								"Sorry, we're unable to match your provided info with any accounts. Please try entering your information again or register for a new account!"));
						JOptionPane.showConfirmDialog(null, statusPanel, "Uh oh!", JOptionPane.CANCEL_OPTION,
								JOptionPane.INFORMATION_MESSAGE);
					}
				}

			}
			// if user clicks on the forgot password link, open a new dialogPanel to reset
			// password
			if (e.getSource() == forgotPasswordButton) {
				JTextField usernameField = new JTextField(1);
				JTextField securityField = new JTextField(5);
				JPanel dialogPanel = new JPanel();
				dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
				dialogPanel.add(new JLabel("Enter your username: "));
				dialogPanel.add(usernameField);
				dialogPanel.add(new JLabel("What is your favorite food: "));
				dialogPanel.add(securityField);

				int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Reset your Password",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {
					if (accountManager.passwordRecovery(usernameField.getText(), securityField.getText()) != null) {
						JTextField newPassword = new JTextField(1);
						JTextField newPasswordConfirm = new JTextField(5);
						statusPanel = new JPanel();
						statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));

						statusPanel.add(new JLabel("Enter your new password: "));
						statusPanel.add(newPassword);
						statusPanel.add(new JLabel("Enter your new password again for confirmation: "));
						statusPanel.add(newPasswordConfirm);
						int index = accountManager.getIndexOfUser(usernameField.getText());

						JOptionPane.showConfirmDialog(null, statusPanel, "Enter your new password",
								JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

						if (newPassword.getText().equals(newPasswordConfirm.getText())) {
							statusPanel = new JPanel();
							statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
							statusPanel.add(new JLabel("Password updated"));
							accountManager.updatePassword(index, newPassword.getText());
							JOptionPane.showConfirmDialog(null, statusPanel, "Success", JOptionPane.CANCEL_OPTION,
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							statusPanel = new JPanel();
							statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
							statusPanel.add(new JLabel("New Passwords Don't Match"));
							statusPanel.add(new JLabel("Passwords Don't Match"));

							JOptionPane.showConfirmDialog(null, statusPanel, "Success", JOptionPane.CANCEL_OPTION,
									JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						statusPanel = new JPanel();
						statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
						usernameExists = new JLabel("Incorret information entered! Please try again!");
						statusPanel.add(usernameExists);
						JOptionPane.showConfirmDialog(null, statusPanel, "Incorrect Info", JOptionPane.CANCEL_OPTION,
								JOptionPane.INFORMATION_MESSAGE);
					}
				}

			}
		}
	}

	/**
	 * 
	 * Handles events from the JButtons. Does various tasks, depending on which
	 * JButton was pushed.
	 * 
	 */
	private class JButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == editAccount) {

				JTextField usernameField = new JTextField(1);
				JTextField passwordField = new JTextField(5);
				JPanel dialogPanel = new JPanel();
				dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
				dialogPanel.add(new JLabel("Login to modify account information: "));
				dialogPanel.add(new JLabel("Username:"));
				dialogPanel.add(usernameField);
				dialogPanel.add(new JLabel("Password:"));
				dialogPanel.add(passwordField);

				int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Login to modify account",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {
					if (accountManager.doesAccountExist(usernameField.getText(), passwordField.getText())) {
						int index = accountManager.getIndexOfUser(usernameField.getText());
						UserAccount myAccount = accountManager.retrieveAccount(index);

						JTextField modifyUsername = new JTextField(1);
						JTextField modifyEmail = new JTextField(5);
						JTextField modifySecurityQuestion = new JTextField(1);
						JTextField modifyName = new JTextField(5);

						JPanel dialogPanel2 = new JPanel();
						dialogPanel2.setLayout(new BoxLayout(dialogPanel2, BoxLayout.Y_AXIS));
						dialogPanel2.add(
								new JLabel("To change any of your account information, type it into the boxes below!"));
						dialogPanel2.add(new JLabel(
								"Any boxes that are left blank, containing dupliacte info, or not valid info. will update"));
						dialogPanel2.add(new JLabel("Modify Username"));
						dialogPanel2.add(modifyUsername);
						dialogPanel2.add(new JLabel("Modify Email"));
						dialogPanel2.add(modifyEmail);
						dialogPanel2.add(new JLabel("Modify Security Question"));
						dialogPanel2.add(modifySecurityQuestion);
						dialogPanel2.add(new JLabel("Modify Name"));
						dialogPanel2.add(modifyName);

						JOptionPane.showConfirmDialog(null, dialogPanel2, "Modify Info", JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.INFORMATION_MESSAGE);
						String printUsername;
						String printSecurityQuestion;
						String printName;
						String printEmail;
						int counter = 0;

						if (accountManager.newUserNameChecks(modifyUsername.getText(), myAccount.getUserName())) {
							myAccount.setUserName(modifyUsername.getText());
							printUsername = "Your new username is: " + myAccount.getUserName();
							counter = counter + 1;
						} else {
							printUsername = null;
						}

						if (accountManager.newEmailChecks(modifyEmail.getText(), myAccount.getEmail())) {
							myAccount.setEmail(modifyEmail.getText());
							printEmail = "Your new e-mail is: " + myAccount.getEmail();
							counter = counter + 1;

						} else {
							printEmail = null;

						}

						if (myAccount.getName() != modifyName.getText() && modifyName.getText().length() > 0) {
							myAccount.setName(modifyName.getText());
							printName = "Your new name is: " + myAccount.getName();
							counter = counter + 1;

						} else {
							printName = null;
						}

						if (myAccount.getSecurityQuestion() != modifySecurityQuestion.getText()
								&& modifySecurityQuestion.getText().length() > 0) {
							myAccount.setSecurityQuestion(modifySecurityQuestion.getText());
							printSecurityQuestion = "Your new security question is: " + myAccount.getSecurityQuestion();
							counter = counter + 1;

						} else {
							printSecurityQuestion = null;
						}

						if (counter > 0) {
							JPanel dialogPanel3 = new JPanel();
							dialogPanel3.setLayout(new BoxLayout(dialogPanel3, BoxLayout.Y_AXIS));

							dialogPanel3.add(new JLabel(printName));
							dialogPanel3.add(new JLabel(printEmail));
							dialogPanel3.add(new JLabel(printSecurityQuestion));
							dialogPanel3.add(new JLabel(printUsername));

							JOptionPane.showConfirmDialog(null, dialogPanel3, "Updated info.",
									JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
						} else {
							JPanel dialogPanel3 = new JPanel();
							dialogPanel3.setLayout(new BoxLayout(dialogPanel3, BoxLayout.Y_AXIS));
							JOptionPane.showConfirmDialog(null, dialogPanel3, "No info updated",
									JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
						}

					} else {
						JPanel dialogPanel2 = new JPanel();
						dialogPanel2.setLayout(new BoxLayout(dialogPanel2, BoxLayout.Y_AXIS));
						JOptionPane.showConfirmDialog(null, dialogPanel2, "Failed to login!",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
					}

				}
			}
			if (event.getSource() == loginButton) {

				JTextField usernameField = new JTextField(1);
				JTextField passwordField = new JTextField(5);
				JPanel dialogPanel = new JPanel();
				dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
				dialogPanel.add(new JLabel("Username:"));
				dialogPanel.add(usernameField);
				dialogPanel.add(new JLabel("Password:"));
				dialogPanel.add(passwordField);

				int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Login", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {

					if (accountManager.doesAccountExist(usernameField.getText(), passwordField.getText())) {

						statusPanel = new JPanel();
						statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
						usernameExists = new JLabel("Login Successful");
						statusPanel.add(usernameExists);
						JOptionPane.showConfirmDialog(null, statusPanel, "Success", JOptionPane.CANCEL_OPTION,
								JOptionPane.INFORMATION_MESSAGE);
						loginSignupPanel.removeAll();
						loginSignupPanel.add(new JLabel("Welcome!"));
						cs.gridx = 0;
						cs.gridy = 1;
						cs.gridwidth = 1;
						loginSignupPanel.add(editAccount, cs);
						revalidate();
						repaint();
					} else {

						statusPanel = new JPanel();
						statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
						usernameExists = new JLabel("Username/Password doesn't exist! Please try again.");
						statusPanel.add(usernameExists);
						JOptionPane.showConfirmDialog(null, statusPanel, "Error", JOptionPane.CANCEL_OPTION,
								JOptionPane.INFORMATION_MESSAGE);

					}

				}
			}

			if (event.getSource() == signupButton) {
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

				int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Sign Up", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {

					if (accountManager.doesUserNameExist(usernameField.getText())) {

						statusPanel = new JPanel();
						statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
						usernameExists = new JLabel("Username already exists! Please enter a different one.");
						statusPanel.add(usernameExists);
						JOptionPane.showConfirmDialog(null, statusPanel, "Error", JOptionPane.CANCEL_OPTION,
								JOptionPane.INFORMATION_MESSAGE);

					} else if (!accountManager.validPassword(passwordField.getText())) {
						statusPanel = new JPanel();
						statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
						invalidPassword = new JLabel("Invalid password: Length must be > 0 and <=20");
						statusPanel.add(invalidPassword);
						JOptionPane.showConfirmDialog(null, statusPanel, "Error", JOptionPane.CANCEL_OPTION,
								JOptionPane.INFORMATION_MESSAGE);

					} else if (!accountManager.validEmail(emailField.getText())) {
						statusPanel = new JPanel();
						statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
						invalidEmail = new JLabel("Invalid Email");
						statusPanel.add(invalidEmail);
						JOptionPane.showConfirmDialog(null, statusPanel, "Error", JOptionPane.CANCEL_OPTION,
								JOptionPane.INFORMATION_MESSAGE);
					} else if (!accountManager.validSecurityQuestion(securityField.getText())) {
						statusPanel = new JPanel();
						statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
						invalidSecurityQuestion = new JLabel("Security question cannot be left blank");
						statusPanel.add(invalidSecurityQuestion);
						JOptionPane.showConfirmDialog(null, statusPanel, "Error", JOptionPane.CANCEL_OPTION,
								JOptionPane.INFORMATION_MESSAGE);
					} else {

						accountManager.addUserAccount(usernameField.getText(), passwordField.getText(),
								nameField.getText(), emailField.getText(), securityField.getText());

					}

				}
			}
		}
	}
}

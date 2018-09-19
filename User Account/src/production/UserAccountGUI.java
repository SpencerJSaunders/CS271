package production;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;
/**
 * The driver class for UserAccount GUI.
 * 
 * @author Team One
 * @version 1.0
 */
public class UserAccountGUI
{
	
	/**
	 * Creates a JFrame and adds the main JPanel to the JFrame.
	 * @param args (unused)
	 */
	public static void main(String args[])
	{
		
		// So it looks consistent on Mac/Windows/Linux
		try 
		{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		JFrame frame = new JFrame("Welcome");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new UserAccountGUIPanel());
		frame.setPreferredSize(new Dimension(300, 200));
		frame.pack();
		frame.setVisible(true);
	}
}

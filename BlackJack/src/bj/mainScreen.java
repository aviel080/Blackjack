package bj;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mainScreen extends JPanel  {
	private JTextField textField;
	private JTextField textField_1;
	public mainScreen() {
		
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JLabel lblUsername = new JLabel("UserName:");
		add(lblUsername);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		add(lblPassword);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		add(textField_1);
		add(btnNewButton);
	}

}

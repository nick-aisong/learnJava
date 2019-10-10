package org.ch05.uri;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Authenticator;
import java.net.PasswordAuthentication;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Test;

// 5-11
public class DialogAuthenticator extends Authenticator {

	private JDialog passwordDialog;
	private JTextField usernameField = new JTextField(20);
	private JPasswordField passwordField = new JPasswordField(20);
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");
	private JLabel mainLabel = new JLabel("Please enter username and password: ");

	public DialogAuthenticator() {
		this("", new JFrame());
	}

	public DialogAuthenticator(String username) {
		this(username, new JFrame());
	}

	public DialogAuthenticator(JFrame parent) {
		this("", parent);
	}

	public DialogAuthenticator(String username, JFrame parent) {
		this.passwordDialog = new JDialog(parent, true);
		Container pane = passwordDialog.getContentPane();
		pane.setLayout(new GridLayout(4, 1));

		JLabel userLabel = new JLabel("Username: ");
		JLabel passwordLabel = new JLabel("Password: ");
		pane.add(mainLabel);
		JPanel p2 = new JPanel();
		p2.add(userLabel);
		p2.add(usernameField);
		usernameField.setText(username);
		pane.add(p2);
		JPanel p3 = new JPanel();
		p3.add(passwordLabel);
		p3.add(passwordField);
		pane.add(p3);
		JPanel p4 = new JPanel();
		p4.add(okButton);
		p4.add(cancelButton);
		pane.add(p4);
		passwordDialog.pack();

		ActionListener al = new OKResponse();
		okButton.addActionListener(al);
		usernameField.addActionListener(al);
		passwordField.addActionListener(al);
		cancelButton.addActionListener(new CancelResponse());
	}

	private void show() {
//		String prompt = this.getRequestingPrompt();
//		if (prompt == null) {
//			String site = this.getRequestingSite().getHostName();
//			String protocol = this.getRequestingProtocol();
//			int port = this.getRequestingPort();
//			if (site != null && protocol != null) {
//				prompt = protocol + "://" + port;
//				if (port > 0) {
//					prompt += ":" + port;
//				} else {
//					prompt = "";
//				}
//			}
//			mainLabel.setText("Please enter uesrname and password for " + prompt + ": ");
			passwordDialog.pack();
			passwordDialog.setVisible(true);
//		}
	}

	PasswordAuthentication response = null;

	class OKResponse implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			passwordDialog.setVisible(false);
			// 出于安全原因，口令作为char数组返回
			char[] password = passwordField.getPassword();
			String username = usernameField.getText();
			// 清除口令，以防再次使用
			passwordField.setText("");
			response = new PasswordAuthentication(username, password);
		}
	}

	class CancelResponse implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			passwordDialog.setVisible(false);
			// 清除口令，以防再次使用
			passwordField.setText("");
			response = null;
		}
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		this.show();
		return this.response;
	}

	public static void main(String[] args) {
		DialogAuthenticator dialogAuthenticator = new DialogAuthenticator();
		dialogAuthenticator.show();
	}

	@Test
	public void demo1() {
		DialogAuthenticator dialogAuthenticator = new DialogAuthenticator();
		dialogAuthenticator.show();
	}

}

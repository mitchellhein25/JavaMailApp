package email_package;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class email_generator {
	private static JPanel panel1, panel2, panel3, panel4, container;
	private static JTextField sender, recipient, subject;
	private static JTextArea text;
	private static JButton send;
	private static MimeMessage message;
	public static void main(String[] args) {
		
		//Create Main JFrame
		JFrame frame = new JFrame("Email Generator");
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create a Panel for each Field
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		
		//Sender Panel
		sender = new JTextField(20); // number of rows
		sender.setHorizontalAlignment(JTextField.CENTER);
		sender.setText("Sent From");
		panel1.add(sender);
		
		//Recipient Panel
		recipient = new JTextField(20);
		recipient.setHorizontalAlignment(JTextField.CENTER);
		recipient.setText("Send To");
		panel2.add(recipient);
		
		//Subject Panel
		subject = new JTextField(20);
		subject.setHorizontalAlignment(JTextField.CENTER);
		subject.setText("Subject");
		panel3.add(subject);
		
		//Text Panel
		text = new JTextArea(20, 20); //Number of rows, number of columns
		text.setText("Insert Text");
		panel4.add(text);
		JPanel container = new JPanel();
		
		//Place panels into the container with BoxLayout
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.add(panel1);
		container.add(panel2);
		container.add(panel3);
		container.add(panel4);
		
		//Add Send Button
		send = new JButton("Send");
		container.add(send);
		frame.add(container);
		frame.pack();
		
		//Sender selection focus listener
		sender.addFocusListener(new FocusField(sender));
		
		//recipient selection focus listener
		recipient.addFocusListener(new FocusField(recipient));

		//subject selection focus listener
		subject.addFocusListener(new FocusField(subject));

		//text selection focus listener
		text.addFocusListener(new FocusArea(text));
		
		//Change cursor to hand when hovering over button
		send.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		//Configure the email SMTP server using the properties Hashtable class
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com"); // set up gmail as host
		properties.put("mail.smtp.auth", "true"); // enable the authentication
		properties.put("mail.smtp.starttls.enable", "true"); // enable TLS connection
		properties.put("mail.smtp.port", "587"); //set up TLS port
		
		//The Session class represents a mail session, It collects together properties and defaults used by the mail API's.
		Session session = Session.getDefaultInstance(properties);
		
		//This class represents a MIME style email message. It implements the Message abstract class and the MimePart interface.
		//Basically helps you format and send the email message
		message = new MimeMessage(session);
		send.addActionListener(new ActionListener() { // sending the MimeMessage when the button is clicked

			@Override
			public void actionPerformed(ActionEvent arg0) { // Called when button pressed
				try {
					message.setFrom(sender.getText());
					message.setRecipients(RecipientType.TO, recipient.getText());
					message.setSubject(subject.getText());
					message.setSentDate(new Date());
					message.setText(text.getText());
					Transport.send(message, sender.getText(), "Hein25CFG#"); // Transport is an abstract class that models a message transport.
				} catch (MessagingException e) {
					e.printStackTrace();
				}
				
			}
			
			
		});
	}
}

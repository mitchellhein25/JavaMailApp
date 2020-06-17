package email_package;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class FocusField implements FocusListener{
	private JTextField field;
	private final String senderm = "Sent From";
	private final String recipientm = "Send To";
	private final String subjectm = "Subject";
	private String textm = "";
	
	public FocusField(JTextField field) {
		this.field = field;
	}
	@Override
	public void focusGained(FocusEvent arg0) {
		if (field.getText().equals(senderm) || field.getText().equals(recipientm) || field.getText().equals(subjectm)) {
			textm = field.getText();
			field.setText("");
			field.setHorizontalAlignment(JTextField.LEFT);
		}
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		if (field.getText().equals("")) {
			field.setText(textm);
			field.setHorizontalAlignment(JTextField.CENTER);
		}
		
	}

}

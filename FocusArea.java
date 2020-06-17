package email_package;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;

public class FocusArea implements FocusListener{
	private JTextArea area;

	public FocusArea(JTextArea area) {
		this.area = area;
	}
	
	@Override
	public void focusGained(FocusEvent arg0) { //when text box is selected
		if (area.getText().equals("Insert Text")) {
			area.setText("");
			
		}
	}
	
	@Override
	public void focusLost(FocusEvent arg0) { //when text box is deselected
		if (area.getText().equals("")) {
			area.setText("Insert Text");
		}
	}
}

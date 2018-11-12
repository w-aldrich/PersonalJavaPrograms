package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Day extends JPanel{
	
	Border blackline = BorderFactory.createLineBorder(Color.black);
	

	public Day(String dayOfWeek) {
		
		Border titledDayOfWeek = BorderFactory.createTitledBorder(blackline,
				"<html> <strong> <font size=\"5\"> " + dayOfWeek + " </font> </strong> </html>");
		
		setBorder(titledDayOfWeek);
		this.setVisible(true);
	}
	
}

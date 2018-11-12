package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MasterList extends JPanel{
	Border blackline = BorderFactory.createLineBorder(Color.black);
	
	public MasterList () {
		Border titledDayOfWeek = BorderFactory.createTitledBorder(blackline,
				"<html> <strong> <font size=\"5\"> MasterList </font> </strong> </html>");
		
		setBorder(titledDayOfWeek);
		this.setVisible(true);
	}

}

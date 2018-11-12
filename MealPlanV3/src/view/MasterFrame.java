package view;


import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MasterFrame extends JFrame{

	public MasterFrame() {
		BorderLayout layout = new BorderLayout();
		layout.addLayoutComponent(new MasterList(), layout.WEST);
		this.setVisible(true);
	}
	
}

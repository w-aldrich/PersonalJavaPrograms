package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class OptionPanel extends JPanel
{
	OptionPanel(RandomWeekView week)
	{
		setLayout(new GridLayout(1,1));
		
		JButton newMenu = new JButton("I Want A New Menu!");
		newMenu.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				week.updateRandomWeek(week);
			}
		});
//		newMenu.setSize(50, 50);
		
//		JButton change = new JButton("Change part of the Menu");
//		change.addActionListener(new ActionListener() 
//		{
//			@Override
//			public void actionPerformed(ActionEvent e) 
//			{
//				new ChangeButton(week.returnWeekDays(), week.returnWeekEnd());
//			}
//			
//		});
//		change.setSize(50, 50);
		
		add(newMenu);
//		add(change);
		setVisible(true);
	}
	
	
}

package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import application.LoadMenu;
import application.SaveMenu;

public class RandomWeek{
	public RandomWeek() {
		
		JFrame randomWeek = new JFrame();
		
		randomWeek.setLayout(new BorderLayout());
		randomWeek.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		RandomWeekView week = new RandomWeekView();
		OptionPanel option = new OptionPanel(week);
		
		JMenuBar menuBar = new JMenuBar();
		JMenuItem back = new JMenuItem("Main Menu");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				randomWeek.setVisible(false);
				new MainMenu();
			}
		});
		JMenu menu = new JMenu("File");
		JMenuItem save = new JMenuItem("Save Current Menu");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SaveMenu(RandomWeekView.returnWeekDays(), false);
			}
		});
		JMenuItem load = new JMenuItem("Load a Past Menu");
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoadMenu();
			}
		});
		JMenuItem print = new JMenuItem("Print Current Menu");
		print.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Print");
			}
		});
		JMenuItem setAsCurrent = new JMenuItem("Set as Current Menu");
		setAsCurrent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainMenu.CurrentMenu = randomWeek;
				new SaveMenu(RandomWeekView.returnWeekDays(), true);
			}
		});
		
		
		menu.add(save);
		menu.add(load);
		menu.add(print);
		menu.add(setAsCurrent);
		menuBar.add(menu);
		menuBar.add(back);

		randomWeek.setJMenuBar(menuBar);
		randomWeek.add(option, BorderLayout.NORTH);
		randomWeek.add(week, BorderLayout.CENTER);
		randomWeek.setExtendedState(JFrame.MAXIMIZED_BOTH);
		randomWeek.setResizable(true);
		//setMinimumSize(new Dimension(800, 700));
		randomWeek.setVisible(true);
	}
}

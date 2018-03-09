package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import application.LoadMenu;

@SuppressWarnings("serial")
public class MainMenu extends JFrame {

	public static JPanel currentDay;
	public static JFrame CurrentMenu;

	public MainMenu() {
		new LoadMenu(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		JPanel left = new JPanel();
		left.setLayout(new GridLayout(2, 1));
		JPanel right = new JPanel();
		JPanel top = new JPanel();
		JLabel blank = new JLabel();
		blank.setPreferredSize(new Dimension(0, 100));
		top.add(blank);

		JPanel holdRandomCreate = new JPanel();
		JButton randomize = new JButton("Random Week");
		// JButton randomize = new RoundButton("Random Week");
		randomize.setSize(150, 150);
		randomize.setMinimumSize(new Dimension(150, 150));
		randomize.setPreferredSize(new Dimension(150, 150));
		randomize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new RandomWeek();
			}
		});
		holdRandomCreate.add(randomize);
		JLabel blankSpace = new JLabel();
		blankSpace.setPreferredSize(new Dimension(100, 0));
		holdRandomCreate.add(blankSpace);
		JButton createOwn = new JButton("Create my own menu");
		createOwn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new CustomWeek();
			}
		});
		createOwn.setSize(150, 150);
		createOwn.setMinimumSize(new Dimension(150, 150));
		createOwn.setPreferredSize(new Dimension(150, 150));
		holdRandomCreate.add(createOwn);
		JPanel holdMonth = new JPanel();
		JButton currentMenu = new JButton("Current Menu");
		currentMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				if (CurrentMenu == null) {
					new LoadMenu();
				} else {
					CurrentMenu.setVisible(true);
				}
			}
		});
		currentMenu.setSize(150, 150);
		currentMenu.setMinimumSize(new Dimension(150, 150));
		currentMenu.setPreferredSize(new Dimension(150, 150));
		holdMonth.add(currentMenu);

		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(2, 2));
		JButton settings = new JButton("Settings");
		settings.setPreferredSize(new Dimension(100, 100));
		JButton about = new JButton("About");
		about.setPreferredSize(new Dimension(100, 100));
		JButton something = new JButton("Something");
		something.setPreferredSize(new Dimension(100, 100));
		JButton somethingElse = new JButton("Something Else");
		somethingElse.setPreferredSize(new Dimension(100, 100));

		bottom.add(settings);
		bottom.add(about);
		bottom.add(something);
		bottom.add(somethingElse);

		left.add(holdRandomCreate);
		left.add(holdMonth);

		if (currentDay == null) {
			Date now = new Date();
			SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out
																				// completely
			JButton placeHolder = new JButton(simpleDateformat.format(now));
			placeHolder.setPreferredSize(new Dimension(200, 200));
			right.add(placeHolder);
		} else {
			currentDay.setPreferredSize(new Dimension(500, 500));
			right.add(currentDay);
		}

		JPanel holdLeftRight = new JPanel();
		holdLeftRight.setLayout(new GridLayout(1, 1));
		holdLeftRight.add(left);
		holdLeftRight.add(right);

		add(holdLeftRight, BorderLayout.CENTER);
		add(top, BorderLayout.NORTH);
		add(bottom, BorderLayout.SOUTH);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		pack();
		setVisible(true);
	}
}

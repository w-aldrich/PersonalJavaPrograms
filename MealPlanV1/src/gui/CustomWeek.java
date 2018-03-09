package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import application.LoadMenu;
import application.SaveMenu;
import application.WeekDay;
import meals.Breakfast;
import meals.Dinner;
import meals.Lunch;
import meals.Snack;

public class CustomWeek {

	private static WeekDay monday, tuesday, wednesday, thursday, friday, saturday, sunday;

	CustomWeek() {
		JFrame custom = new JFrame();
		custom.setLayout(new GridLayout(1, 7));
		custom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String[] weekday = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };

		for (String s : weekday) {
			custom.add(createDay(s));
		}

		JMenuBar menuBar = new JMenuBar();
		JMenuItem back = new JMenuItem("Main Menu");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				custom.setVisible(false);
				new MainMenu();
			}
		});
		JMenu menu = new JMenu("File");
		JMenuItem save = new JMenuItem("Save Current Menu");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<WeekDay> days = returnWeekDays();
				new SaveMenu(days, false);
			}
		});
		JMenuItem load = new JMenuItem("Load a Past Menu");
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				custom.setVisible(false);
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
				MainMenu.CurrentMenu = custom;
				new SaveMenu(returnWeekDays(), true);
			}
		});

		menu.add(save);
		menu.add(load);
		menu.add(print);
		menu.add(setAsCurrent);
		menuBar.add(menu);
		menuBar.add(back);

		custom.setJMenuBar(menuBar);

		custom.setExtendedState(JFrame.MAXIMIZED_BOTH);
		custom.setResizable(true);
		custom.setVisible(true);
	}

	public JPanel createDay(String dayName) {
		
		final String currentDayName = dayName;
		
		Border blackline = BorderFactory.createLineBorder(Color.black);

		JPanel currentDay = new JPanel();
		currentDay.setLayout(new BorderLayout());
		currentDay.setBorder(blackline);

		JLabel name = new JLabel(dayName, SwingConstants.CENTER);
		name.setText("<html> <strong> " + dayName + " </strong> </html>");
		name.setBorder(blackline);

		currentDay.add(name, BorderLayout.NORTH);

		JPanel allMeals = new JPanel();
		allMeals.setLayout(new GridLayout(5, 1));

		JButton breakfast = new JButton("Add Breakfast");
		breakfast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Breakfast.showAllBreakfast("Empty Breakfast", breakfast, returnDayOfWeek(currentDayName));
			}
		});
		allMeals.add(breakfast);

		JButton morningSnack = new JButton("Add Snack");
		morningSnack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Snack.showAllSnacks("Empty Snack", morningSnack, returnDayOfWeek(currentDayName));
			}
		});
		allMeals.add(morningSnack);

		JButton lunch = new JButton("Add Lunch");
		lunch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Lunch.showAllLunch("Empty Lunch", lunch, returnDayOfWeek(currentDayName));
			}
		});
		allMeals.add(lunch);

		JButton afternoonSnack = new JButton("Add Snack");
		afternoonSnack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Snack.showAllSnacks("Empty Snack", afternoonSnack, returnDayOfWeek(currentDayName));
			}
		});
		allMeals.add(afternoonSnack);

		JButton dinner = new JButton("Add Dinner");
		dinner.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Dinner.showAllDinner("Empty Dinner", dinner, returnDayOfWeek(currentDayName));
			}
		});
		allMeals.add(dinner);

		currentDay.add(allMeals, BorderLayout.CENTER);

		switch (dayName) {
		case "Monday":
			monday = new WeekDay(dayName, breakfast, morningSnack, lunch, afternoonSnack, dinner, null, null, null,
					null, null);
			break;
		case "Tuesday":
			tuesday = new WeekDay(dayName, breakfast, morningSnack, lunch, afternoonSnack, dinner, null, null, null,
					null, null);
			break;
		case "Wednesday":
			wednesday = new WeekDay(dayName, breakfast, morningSnack, lunch, afternoonSnack, dinner, null, null, null,
					null, null);
			break;
		case "Thursday":
			thursday = new WeekDay(dayName, breakfast, morningSnack, lunch, afternoonSnack, dinner, null, null, null,
					null, null);
			break;
		case "Friday":
			friday = new WeekDay(dayName, breakfast, morningSnack, lunch, afternoonSnack, dinner, null, null, null,
					null, null);
			break;
		case "Saturday":
			saturday = new WeekDay(dayName, breakfast, morningSnack, lunch, afternoonSnack, dinner, null, null, null,
					null, null);
			break;
		case "Sunday":
			sunday = new WeekDay(dayName, breakfast, morningSnack, lunch, afternoonSnack, dinner, null, null, null,
					null, null);
			break;
		}

		return currentDay;
	}
	
	public static ArrayList<WeekDay> returnWeekDays(){
		ArrayList<WeekDay> allWeekDays = new ArrayList<>();
		
		if(monday == null) {
			monday = new WeekDay("Monday");
			createWeekDayMeals(monday);
		} else if (tuesday == null) {
			tuesday = new WeekDay("Tuesday");
			createWeekDayMeals(tuesday);
		} else if (wednesday == null) {
			wednesday = new WeekDay("Wednesday");
			createWeekDayMeals(wednesday);
		} else if (thursday == null) {
			thursday = new WeekDay("Thursday");
			createWeekDayMeals(thursday);
		} else if (friday == null) {
			friday = new WeekDay("Friday");
			createWeekDayMeals(friday);
		} else if (saturday == null) {
			saturday = new WeekDay("Saturday");
			createWeekDayMeals(saturday);
		} else if (sunday == null) {
			sunday = new WeekDay("Sunday");
			createWeekDayMeals(sunday);
		}

		allWeekDays.add(monday);
		allWeekDays.add(tuesday);
		allWeekDays.add(wednesday);
		allWeekDays.add(thursday);
		allWeekDays.add(friday);
		allWeekDays.add(saturday);
		allWeekDays.add(sunday);
		
		return allWeekDays;
	}
	
	
	private static void createWeekDayMeals(WeekDay wd) {
		wd.breakfast = new Breakfast();
		wd.morningSnack = new Snack();
		wd.lunch = new Lunch();
		wd.afternoonSnack = new Snack();
		wd.dinner = new Dinner();
	}
	
	
	private WeekDay returnDayOfWeek(String name) {
		switch (name) {
		case "Monday":
			return monday;
		case "Tuesday":
			return tuesday;
		case "Wednesday":
			return wednesday;
		case "Thursday":
			return thursday;
		case "Friday":
			return friday;
		case "Saturday":
			return saturday;
		default:
			return sunday;
		}
	}

}

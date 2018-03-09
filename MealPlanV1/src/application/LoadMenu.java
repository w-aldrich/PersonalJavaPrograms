package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import gui.MainMenu;
import meals.Breakfast;
import meals.Dinner;
import meals.Lunch;
import meals.Snack;

public class LoadMenu {
	
	private static WeekDay monday, tuesday, wednesday, thursday, friday, saturday, sunday;
	
	public LoadMenu(Boolean startUp) {
		loadOnStart();
	}

	public LoadMenu() {
		JFileChooser choose = new JFileChooser("Saved_Meals");
		choose.setDialogTitle("Please just work");

		int result = choose.showOpenDialog(null);
		if (result == JFileChooser.CANCEL_OPTION) {
			return;
		}
		
		JFrame week = new JFrame();
		week.setLayout(new GridLayout(1, 7));
		week.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Date now = new Date();
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
		
        String currentDate = simpleDateformat.format(now);

		File inputFile = choose.getSelectedFile();
		try {
			Scanner s = new Scanner(inputFile);
			while (s.hasNextLine()) {
				String currentLine = s.nextLine();
				if (currentLine.equals("Monday") || currentLine.equals("Tuesday") || currentLine.equals("Wednesday") || currentLine.equals("Thursday") 
						|| currentLine.equals("Friday") ||currentLine.equals("Saturday") || currentLine.equals("Sunday")) {
					JPanel currentDay = setupDays(currentLine, s);
					week.add(currentDay);
					if (currentLine.equals(currentDate)) {
						MainMenu.currentDay = currentDay;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		JMenuBar menuBar = new JMenuBar();
		JMenuItem back = new JMenuItem("Main Menu");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				week.setVisible(false);
				new MainMenu();
			}
		});
		JMenu menu = new JMenu("File");

		JMenuItem print = new JMenuItem("Print Current Menu");
		print.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		JMenuItem setAsCurrent = new JMenuItem("Set as Current Menu");
		setAsCurrent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainMenu.CurrentMenu = week;
				new SaveMenu(allDays(), true);
			}
		});

		menu.add(print);
		menu.add(setAsCurrent);
		menuBar.add(menu);
		menuBar.add(back);

		week.setJMenuBar(menuBar);

		
		week.setExtendedState(JFrame.MAXIMIZED_BOTH);
		week.setResizable(true);
		week.setVisible(true);
		
		MainMenu.CurrentMenu = week;
		
	}
	
	private void loadOnStart() {
		JFrame week = new JFrame();
		week.setLayout(new GridLayout(1, 7));
		week.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Date now = new Date();
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
		
        String currentDate = simpleDateformat.format(now);

		File inputFile = new File("Saved_Meals/CurrentMenu.txt");
		try {
			Scanner s = new Scanner(inputFile);
			while (s.hasNextLine()) {
				String currentLine = s.nextLine();
				if (currentLine.equals("Monday") || currentLine.equals("Tuesday") || currentLine.equals("Wednesday") || currentLine.equals("Thursday") 
						|| currentLine.equals("Friday") ||currentLine.equals("Saturday") || currentLine.equals("Sunday")) {
					JPanel currentDay = setupDays(currentLine, s);
					week.add(currentDay);
					if (currentLine.equals(currentDate)) {
						MainMenu.currentDay = currentDay;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		JMenuBar menuBar = new JMenuBar();
		JMenuItem back = new JMenuItem("Main Menu");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				week.setVisible(false);
				new MainMenu();
			}
		});
		JMenu menu = new JMenu("File");

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
				MainMenu.CurrentMenu = week;
				new SaveMenu(allDays(), true);
			}
		});

		menu.add(print);
		menu.add(setAsCurrent);
		menuBar.add(menu);
		menuBar.add(back);

		week.setJMenuBar(menuBar);

		
		week.setExtendedState(JFrame.MAXIMIZED_BOTH);
		week.setResizable(true);
		
		MainMenu.CurrentMenu = week;
	}
	
	private JPanel setupDays(String dayName, Scanner s) {
		
		final String currentDayName = dayName;

		Border blackline = BorderFactory.createLineBorder(Color.black);
		Border breakfasttxt = BorderFactory.createTitledBorder(blackline,
				"<html> <strong> <font size=\"5\"> Breakfast </font> </strong> </html>");
		Border lunchtxt = BorderFactory.createTitledBorder(blackline,
				"<html> <strong> <font size=\"5\"> Lunch </font> </strong> </html>");
		Border snacktxt = BorderFactory.createTitledBorder(blackline,
				"<html> <strong> <font size=\"5\"> Snack </font> </strong> </html>");
		Border dinnertxt = BorderFactory.createTitledBorder(blackline,
				"<html> <strong> <font size=\"5\"> Dinner </font> </strong> </html>");

		JPanel currentDay = new JPanel();
		currentDay.setLayout(new BorderLayout());
		currentDay.setBorder(blackline);

		JLabel name = new JLabel(dayName, SwingConstants.CENTER);
		name.setText("<html> <strong> " + dayName + " </strong> </html>");
		name.setBorder(blackline);

		currentDay.add(name, BorderLayout.NORTH);

		JPanel allMeals = new JPanel();
		allMeals.setLayout(new GridLayout(5, 1));
		
		String title = s.nextLine();
		String ingredients = s.nextLine();
		String recipe = s.nextLine();
		
		Breakfast breakfast = new Breakfast();
		if(!title.equals("X")) {
			breakfast.title = title;
			breakfast.ingredients = ingredients;
			breakfast.recipe = recipe;
		}

		JButton actualBreakfast = null;
		if(title.equals("X")) {
			JButton breakfastButton = new JButton("Add Breakfast");
			breakfastButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Breakfast.showAllBreakfast("Empty Breakfast", breakfastButton, returnDayOfWeek(currentDayName));
				}
			});
			actualBreakfast = breakfastButton;
			allMeals.add(actualBreakfast);
		}
		else {
			JButton breakfastButton = new JButton(title);
			final String thisTitle = title;
			breakfastButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Breakfast.showAllBreakfast(thisTitle, breakfastButton, returnDayOfWeek(currentDayName));
				}	
			});
			actualBreakfast = breakfastButton;
			actualBreakfast.setBorder(breakfasttxt);
			allMeals.add(actualBreakfast);
		}
		
		title = s.nextLine();
		ingredients = s.nextLine();
		recipe = s.nextLine();
		
		Snack morningSnack = new Snack();
		if(!title.equals("X")) {
			morningSnack.title = title;
			morningSnack.ingredients = ingredients;
			morningSnack.recipe = recipe;
		}
		
		JButton actualMorningSnack = null;
		if(title.equals("X")) {
			JButton blah = new JButton("Add Snack");
			blah.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Snack.showAllSnacks("Empty Snack", blah, returnDayOfWeek(currentDayName));
				}
			});
			actualMorningSnack = blah;
			allMeals.add(actualMorningSnack);
		}
		else {
			JButton blah = new JButton(title);
			final String thisTitle = title;
			blah.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Snack.showAllSnacks(thisTitle, blah, returnDayOfWeek(currentDayName));
				}	
			});
			actualMorningSnack = blah;
			actualMorningSnack.setBorder(snacktxt);
			allMeals.add(actualMorningSnack);
		}
		
		title = s.nextLine();
		ingredients = s.nextLine();
		recipe = s.nextLine();
		
		Lunch lunch = new Lunch();
		if(!title.equals("X")) {
			lunch.title = title;
			lunch.ingredients = ingredients;
			lunch.recipe = recipe;
		}
		
		JButton actualLunch = null;
		if(title.equals("X")) {
			JButton blah = new JButton("Add Lunch");
			blah.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Lunch.showAllLunch("Empty Lunch", blah, returnDayOfWeek(currentDayName));
				}
			});
			actualLunch = blah;
			allMeals.add(actualLunch);
		}
		else {
			JButton blah = new JButton(title);
			final String thisTitle = title;
			blah.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Lunch.showAllLunch(thisTitle, blah, returnDayOfWeek(currentDayName));
				}	
			});
			actualLunch = blah;
			actualLunch.setBorder(lunchtxt);
			allMeals.add(actualLunch);
		}
		
		title = s.nextLine();
		ingredients = s.nextLine();
		recipe = s.nextLine();
		
		Snack afternoonSnack = new Snack();
		if(!title.equals("X")) {
			afternoonSnack.title = title;
			afternoonSnack.ingredients = ingredients;
			afternoonSnack.recipe = recipe;
		}
		
		JButton actualAfternoonSnack = null;
		if(title.equals("X")) {
			JButton blah = new JButton("Add Snack");
			blah.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Snack.showAllSnacks("Empty Snack", blah, returnDayOfWeek(currentDayName));
				}
			});
			actualAfternoonSnack = blah;
			allMeals.add(actualAfternoonSnack);
		}
		else {
			JButton blah = new JButton(title);
			final String thisTitle = title;
			blah.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Snack.showAllSnacks(thisTitle, blah, returnDayOfWeek(currentDayName));
				}	
			});
			actualAfternoonSnack = blah;
			actualAfternoonSnack.setBorder(snacktxt);
			allMeals.add(actualAfternoonSnack);
		}
		
		title = s.nextLine();
		ingredients = s.nextLine();
		recipe = s.nextLine();
		
		Dinner dinner = new Dinner();
		if(!title.equals("X")) {
			dinner.title = title;
			dinner.ingredients = ingredients;
			dinner.recipe = recipe;
		}
		
		JButton actualDinner = null;
		if(title.equals("X")) {
			JButton blah = new JButton("Add Dinner");
			blah.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Dinner.showAllDinner("Empty Dinner", blah, returnDayOfWeek(currentDayName));
				}
			});
			actualDinner = blah;
			allMeals.add(actualDinner);
		}
		else {
			JButton blah = new JButton(title);
			final String thisTitle = title;
			blah.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Dinner.showAllDinner(thisTitle, blah, returnDayOfWeek(currentDayName));
				}	
			});
			actualDinner = blah;
			actualDinner.setBorder(dinnertxt);
			allMeals.add(actualDinner);
		}

		currentDay.add(allMeals, BorderLayout.CENTER);

		switch (dayName) {
		case "Monday":
			monday = new WeekDay(dayName, actualBreakfast, actualMorningSnack, actualLunch, actualAfternoonSnack, actualDinner, breakfast, morningSnack, lunch,
					afternoonSnack, dinner);
			break;
		case "Tuesday":
			tuesday = new WeekDay(dayName, actualBreakfast, actualMorningSnack, actualLunch, actualAfternoonSnack, actualDinner, breakfast, morningSnack, lunch,
					afternoonSnack, dinner);
			break;
		case "Wednesday":
			wednesday = new WeekDay(dayName, actualBreakfast, actualMorningSnack, actualLunch, actualAfternoonSnack, actualDinner, breakfast, morningSnack, lunch,
					afternoonSnack, dinner);
			break;
		case "Thursday":
			thursday = new WeekDay(dayName, actualBreakfast, actualMorningSnack, actualLunch, actualAfternoonSnack, actualDinner, breakfast, morningSnack, lunch,
					afternoonSnack, dinner);
			break;
		case "Friday":
			friday = new WeekDay(dayName, actualBreakfast, actualMorningSnack, actualLunch, actualAfternoonSnack, actualDinner, null, null, null,
					null, null);
			break;
		case "Saturday":
			saturday = new WeekDay(dayName, actualBreakfast, actualMorningSnack, actualLunch, actualAfternoonSnack, actualDinner, null, null, null,
					null, null);
			break;
		case "Sunday":
			sunday = new WeekDay(dayName, actualBreakfast, actualMorningSnack, actualLunch, actualAfternoonSnack, actualDinner, null, null, null,
					null, null);
			break;
		}

		return currentDay;
	}
	
	private ArrayList<WeekDay> allDays(){
		ArrayList<WeekDay> allDays = new ArrayList<>();
		
		allDays.add(monday);
		allDays.add(tuesday);
		allDays.add(wednesday);
		allDays.add(thursday);
		allDays.add(friday);
		allDays.add(saturday);
		allDays.add(sunday);
		
		return allDays;
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
	
//	public int print(Graphics g, PageFormat pf, int page, JFrame frame)
//		    throws PrinterException {
//		    if (page > 0) {
//		        return 1;
//		    }
//
//		    Graphics2D g2d = (Graphics2D)g;
//		    g2d.translate(pf.getImageableX(), pf.getImageableY());
//
//		    // Print the entire visible contents of a
//		    // java.awt.Frame.
//		    frame.printAll(g);
//
//		    return 0;
//		}
}

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import application.WeekDay;
import meals.Breakfast;
import meals.CrockPot;
import meals.Dinner;
import meals.Lunch;
import meals.Snack;

@SuppressWarnings("serial")
public class RandomWeekView extends JPanel {
	JPanel mon, tues, wed, thurs, fri, sat, sun;

	private static WeekDay monday, tuesday, wednesday, thursday, friday, saturday, sunday;
	private static Breakfast breakfast;
	private static Snack morningSnack;
	private static Lunch lunch;
	private static Snack afternoonSnack;
	private static Dinner dinner;
	private static CrockPot crock;

	/**
	 * Default constructor, will give a new setup for the week.
	 */
	RandomWeekView() {
		updateRandomWeek(this);
	}
	
	/**
	 * Will completely redo the current week (or create one) with new random meals
	 * basically the constructor of this class but you can change the current week
	 * 
	 * @param week
	 *            --what week you want to redraw
	 */
	public void updateRandomWeek(RandomWeekView week) {
		week.setVisible(false);
		week.removeAll();
		week.repaint();
		week.setLayout(new GridLayout(1, 7));
		mon = new JPanel();
		tues = new JPanel();
		wed = new JPanel();
		thurs = new JPanel();
		fri = new JPanel();
		sat = new JPanel();
		sun = new JPanel();

		week.setupRandomWeek();

		week.add(mon);
		week.add(tues);
		week.add(wed);
		week.add(thurs);
		week.add(fri);
		week.add(sat);
		week.add(sun);
		week.setVisible(true);
	}

	/**
	 * Does all (most) of the work. This will go through and give the gui details on
	 * all of the days of the week. Will set up with random meals in the week. Then
	 * creates weekday and weekend objects with the details given.
	 */
	private void setupRandomWeek() {
		// if this is being called not from the constructor
		// does not need to create new variables best to
		// reuse the old variables
		if (breakfast == null)
			breakfast = new Breakfast();
		if (morningSnack == null)
			morningSnack = new Snack();
		if (afternoonSnack == null)
			afternoonSnack = new Snack();
		if (lunch == null)
			lunch = new Lunch();
		if (dinner == null)
			dinner = new Dinner();
		if (crock == null)
			crock = new CrockPot();

		// Create borders used later
		Border blackline = BorderFactory.createLineBorder(Color.black);
		Border lunchtxt = BorderFactory.createTitledBorder(blackline,
				"<html> <strong> <font size=\"5\"> Lunch </font> </strong> </html>");
		Border snacktxt = BorderFactory.createTitledBorder(blackline,
				"<html> <strong> <font size=\"5\"> Snack </font> </strong> </html>");
		Border dinnertxt = BorderFactory.createTitledBorder(blackline,
				"<html> <strong> <font size=\"5\"> Dinner </font> </strong> </html>");

		// All names of days of the week
		String[] weekday = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };

		// variable to keep track of what day of the week used in the for loop below
		int weeknum = 0;

		// All of the jpanels
		JPanel[] week = { mon, tues, wed, thurs, fri, sat, sun };

		for (JPanel day : week) {
			// Set up outer part of the individual day jpanel
			day.setLayout(new BorderLayout());
			day.setSize(50, 200);
			day.setBorder(blackline);
			JLabel dayname = new JLabel(weekday[weeknum], SwingConstants.CENTER);
			dayname.setText("<html> <strong> " + weekday[weeknum] + " </strong> </html>");
			// dayname.setSize(50, 50);
			dayname.setBorder(blackline);
			dayname.setPreferredSize(new Dimension(50, 50));
			day.add(dayname, BorderLayout.NORTH);

			// Set up the interior of the jpanel
			JPanel center = new JPanel();
			// 4,1 gives snack lunch afternoon snack and dinner
			center.setLayout(new GridLayout(5, 1));

			JButton breakfastButton = new JButton("Add Breakfast");
			final JButton currentBreakfastButton = breakfastButton;
			final int breakNum = weeknum;
			breakfastButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Breakfast.showAllBreakfast("Empty Breakfast", currentBreakfastButton, returnDayOfWeek(breakNum));
				}
			});
			center.add(breakfastButton);

			// create jbuttons used later
			JButton snackButton = null;
			JButton lunchButton = null;
			JButton afternoonSnackButton = null;
			JButton dinnerButton = null;

			// Due to how the client wanted it set up, mon-fri they did not want meals
			// created for the morning snack or lunch (kids were in school)
			if (weeknum < 5) {
				// create default snack button as placeholder (and option to add snack later)
				snackButton = new JButton("Add Snack");
				final JButton currentSnackButton = snackButton;
				final int snackNum = weeknum;
				snackButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Snack.showAllSnacks("Empty Snack", currentSnackButton, returnDayOfWeek(snackNum));
					}
				});
				center.add(snackButton);

				// create default lunch button as placeholder (and option to add lunch later)
				lunchButton = new JButton("Add Lunch");
				final JButton currentLunchButton = lunchButton;
				final int lunchNum = weeknum;
				lunchButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Lunch.showAllLunch("Empty Lunch", currentLunchButton, returnDayOfWeek(lunchNum));
					}
				});
				center.add(lunchButton);
			}

			// Create a random snack and make a button from it (will make either first snack
			// of day or afternoon snack)
			afternoonSnack = afternoonSnack.getRandomSnack();
			final Snack currentSnack = afternoonSnack;
			// make afternoon snack
			if (weeknum < 5) {
				afternoonSnackButton = new JButton("<html>" + afternoonSnack.title + "</html>");
				final JButton asb = afternoonSnackButton;
				final int snackNum = weeknum;
				afternoonSnackButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Snack.showAllSnacks(currentSnack.title, asb, returnDayOfWeek(snackNum));
					}
				});
				afternoonSnackButton.setBorder(snacktxt);
				center.add(afternoonSnackButton);
			}
			// make first snack
			else {
				morningSnack = afternoonSnack;
				snackButton = new JButton("<html>" + morningSnack.title + "</html>");
				final JButton currentSnackButton = snackButton;
				final int snackNum = weeknum;
				snackButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Snack.showAllSnacks(currentSnack.title, currentSnackButton, returnDayOfWeek(snackNum));
					}
				});
				snackButton.setBorder(snacktxt);
				center.add(snackButton);
			}

			// for the weekend the client wanted a lunch added
			if (weeknum > 4) {
				// create the random lunch and make a button from it
				lunch = lunch.getRandomLunch();
				final Lunch currentLunch = lunch;
				lunchButton = new JButton("<html><center>" + lunch.title + "</center></html>");
				final JButton currentLunchButton = lunchButton;
				final int lunchNum = weeknum;
				lunchButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Lunch.showAllLunch(currentLunch.title, currentLunchButton, returnDayOfWeek(lunchNum));
					}
				});
				lunchButton.setBorder(lunchtxt);
				center.add(lunchButton);

				// create a random afternoon snack for the weekend and make a button from it
				afternoonSnack = afternoonSnack.getRandomSnack();
				final Snack currentAfternoon = afternoonSnack;
				afternoonSnackButton = new JButton("<html>" + afternoonSnack.title + "</html>");
				final JButton asb = afternoonSnackButton;
				final int snackNum = weeknum;
				afternoonSnackButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Snack.showAllSnacks(currentAfternoon.title, asb, returnDayOfWeek(snackNum));
					}
				});
				afternoonSnackButton.setBorder(snacktxt);
				center.add(afternoonSnackButton);
			}

			// The client wanted crockpot meals on monday, wednesday and thursday
			if (weeknum == 0 || weeknum == 2 || weeknum == 3) {

				// create a random crock pot meal and make a button from it
				crock = crock.getRandomCrockPot();
				final CrockPot currentCrock = crock;
				dinnerButton = new JButton("<html>" + crock.title + "</html>");
				final JButton cdb = dinnerButton;
				final int crockNum = weeknum;
				dinnerButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dinnerOrCrock(currentCrock.title, cdb, returnDayOfWeek(crockNum));
					}
				});
				dinnerButton.setBorder(dinnertxt);
				center.add(dinnerButton);
			}
			// if it was not a crockpot day make a regular dinner
			else {

				// create a random dinner and make a button from it
				dinner = dinner.getRandomDinner();
				final Dinner currentDinner = dinner;
				dinnerButton = new JButton("<html>" + dinner.title + "</html>");
				final JButton cdb = dinnerButton;
				final int dinnerNum = weeknum;
				dinnerButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dinnerOrCrock(currentDinner.title, cdb, returnDayOfWeek(dinnerNum));
					}
				});
				dinnerButton.setBorder(dinnertxt);
				center.add(dinnerButton);
			}

			// add the jpanel created to the outer jpanel
			day.add(center, BorderLayout.CENTER);

			// create weekday and weekend from given details
			switch (weeknum) {
			case 0:
				// null morning snack and lunch
				monday = new WeekDay("Monday", breakfastButton, snackButton, lunchButton, afternoonSnackButton,
						dinnerButton, breakfast, morningSnack, lunch, afternoonSnack, crock);
				break;
			case 1:
				// null morning snack and lunch
				tuesday = new WeekDay("Tuesday", breakfastButton, snackButton, lunchButton, afternoonSnackButton,
						dinnerButton, breakfast, morningSnack, lunch, afternoonSnack, dinner);
				break;
			case 2:
				// null morning snack and lunch
				wednesday = new WeekDay("Wednesday", breakfastButton, snackButton, lunchButton, afternoonSnackButton,
						dinnerButton, breakfast, morningSnack, lunch, afternoonSnack, crock);
				break;
			case 3:
				// null morning snack and lunch
				thursday = new WeekDay("Thursday", breakfastButton, snackButton, lunchButton, afternoonSnackButton,
						dinnerButton, breakfast, morningSnack, lunch, afternoonSnack, crock);
				break;
			case 4:
				// null morning snack and lunch
				friday = new WeekDay("Friday", breakfastButton, snackButton, lunchButton, afternoonSnackButton,
						dinnerButton, breakfast, morningSnack, lunch, afternoonSnack, dinner);
				break;
			case 5:
				saturday = new WeekDay("Saturday", breakfastButton, snackButton, lunchButton, afternoonSnackButton,
						dinnerButton, breakfast, morningSnack, lunch, afternoonSnack, dinner);
				break;
			case 6:
				sunday = new WeekDay("Sunday", breakfastButton, snackButton, lunchButton, afternoonSnackButton,
						dinnerButton, breakfast, morningSnack, lunch, afternoonSnack, dinner);
				break;
			}

			weeknum++;
		}
	}

	/**
	 * Will return a list of all of the current weekDays
	 * 
	 * @return --An arraylist of the current weekdays
	 */
	public static ArrayList<WeekDay> returnWeekDays() {

		ArrayList<WeekDay> allWeekDays = new ArrayList<>();

		allWeekDays.add(monday);
		allWeekDays.add(tuesday);
		allWeekDays.add(wednesday);
		allWeekDays.add(thursday);
		allWeekDays.add(friday);
		allWeekDays.add(saturday);
		allWeekDays.add(sunday);

		return allWeekDays;
	}


	/**
	 * Show a option panel for dinner options or crock pot options
	 * 
	 * @param oldMeal
	 *            --The name of the meal to change
	 */
	private void dinnerOrCrock(String oldMeal, JButton buttonToChange, WeekDay weekday) {
		JFrame option = new JFrame("Change to:");
		option.setLayout(new GridLayout());

		// This will be the button the user pushes for dinner
		JButton dinner = new JButton("Dinner Options");
		dinner.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				option.setVisible(false);
				Dinner.showAllDinner(oldMeal, buttonToChange, weekday);
			}
		});

		// This will be the button the user pushes for crockpots
		JButton crock = new JButton("CrockPot Options");
		crock.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				option.setVisible(false);
				CrockPot.showAllCrockPot(oldMeal, buttonToChange, weekday);
			}

		});
		option.add(dinner);
		option.add(crock);
		option.setMinimumSize(new Dimension(100, 100));
		option.pack();
		option.setLocationRelativeTo(null);
		option.setVisible(true);
		
	}
	
	
	private WeekDay returnDayOfWeek(int weeknum) {
		switch (weeknum) {
		case 0:
			return monday;
		case 1:
			return tuesday;
		case 2:
			return wednesday;
		case 3:
			return thursday;
		case 4:
			return friday;
		case 5:
			return saturday;
		default:
			return sunday;
		}
	}

}

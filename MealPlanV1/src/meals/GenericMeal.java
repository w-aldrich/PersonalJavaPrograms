package meals;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import application.WeekDay;
import gui.ChangeOptionInWeek;


@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
public class GenericMeal {

	private static String newMeal;

	
	public static <T> ArrayList createAll(char type, File f) {

		ArrayList createAll = null;

		switch (type) {
		case 'b':
			createAll = new ArrayList<Breakfast>();
			break;
		case 's':
			createAll = new ArrayList<Snack>();
			break;
		case 'l':
			createAll = new ArrayList<Lunch>();
			break;
		case 'd':
			createAll = new ArrayList<Dinner>();
			break;
		case 'c':
			createAll = new ArrayList<CrockPot>();
			break;
		default:
			throw new NullPointerException();
		}

//		T previousMeal = null;
		try {
			Scanner s = new Scanner(f);
			while (s.hasNext()) {
				String currentLine = s.nextLine();
//				if (currentLine.startsWith("{{")) {
//					addRecipe(previousMeal, s);
//					continue;
//				} else if (currentLine.startsWith("{")) {
//					addIngredients(previousMeal, s);
//					continue;
//				}
				switch (type) {
				case 'b':
					Breakfast breakfast = new Breakfast();
					breakfast.title = currentLine;
					createAll.add(breakfast);
//					previousMeal = (T) breakfast;
					break;
				case 's':
					Snack snack = new Snack();
					snack.title = currentLine;
					createAll.add(snack);
//					previousMeal = (T) snack;
					break;
				case 'l':
					Lunch lunch = new Lunch();
					lunch.title = currentLine;
					createAll.add(lunch);
//					previousMeal = (T) lunch;
					break;
				case 'd':
					Dinner dinner = new Dinner();
					dinner.title = currentLine;
					createAll.add(dinner);
//					previousMeal = (T) dinner;
					break;
				case 'c':
					CrockPot crock = new CrockPot();
					crock.title = currentLine;
					createAll.add(crock);
//					previousMeal = (T) crock;
					break;
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return createAll;
	}

	private static <T> void addRecipe(T previousMeal, Scanner s) {
		if (previousMeal instanceof Snack) {
			while (s.hasNext()) {
				String nextLine = s.nextLine();
				if (nextLine.contains("}}"))
					return;
				((Snack) previousMeal).recipe += (nextLine);
			}
		} else if (previousMeal instanceof Lunch) {
			while (s.hasNext()) {
				String nextLine = s.nextLine();
				if (nextLine.contains("}}"))
					return;
				((Lunch) previousMeal).recipe += (nextLine);
			}
		} else if (previousMeal instanceof Dinner) {
			while (s.hasNext()) {
				String nextLine = s.nextLine();
				if (nextLine.contains("}}"))
					return;
				((Dinner) previousMeal).recipe += (nextLine);
			}
		} else if (previousMeal instanceof CrockPot) {
			while (s.hasNext()) {
				String nextLine = s.nextLine();
				if (nextLine.contains("}}"))
					return;
				((CrockPot) previousMeal).recipe += (nextLine);
			}
		}
	}

	private static <T> void addIngredients(T previousMeal, Scanner s) {
		if (previousMeal instanceof Snack) {
			while (s.hasNext()) {
				String nextLine = s.nextLine();
				if (nextLine.equals("}"))
					return;
				((Snack) previousMeal).ingredients +=(nextLine);
			}
		} else if (previousMeal instanceof Lunch) {
			while (s.hasNext()) {
				String nextLine = s.nextLine();
				if (nextLine.equals("}"))
					return;
				((Lunch) previousMeal).ingredients += (nextLine);
			}
		} else if (previousMeal instanceof Dinner) {
			while (s.hasNext()) {
				String nextLine = s.nextLine();
				if (nextLine.equals("}"))
					return;
				((Dinner) previousMeal).ingredients += (nextLine);
			}
		} else if (previousMeal instanceof CrockPot) {
			while (s.hasNext()) {
				String nextLine = s.nextLine();
				if (nextLine.equals("}"))
					return;
				((CrockPot) previousMeal).ingredients += (nextLine);
			}
		}
	}

	public static <T> T getRandom(ArrayList<T> used, ArrayList<T> unUsed) {

		T meal = unUsed.get((int) (Math.random() * unUsed.size() - 1));
		if (used.contains(meal)) {
			while (!used.contains(meal)) {
				meal = unUsed.get((int) (Math.random() * unUsed.size()));
			}
		}
		used.add(meal);
		unUsed.remove(meal);

		return meal;
	}

	public static <T> void showAllOptions(String typeOfMeal, ArrayList<T> usedMeals, ArrayList<T> unUsed,
			String oldMeal, JButton buttonToChange, WeekDay weekday) {
		ArrayList<T> toRemove = new ArrayList<T>();
		JFrame genericFrame = new JFrame("All " + typeOfMeal + " Options");
		genericFrame.setLayout(new BorderLayout());
		JPanel holdOldAndNew = new JPanel();
		holdOldAndNew.setLayout(new GridLayout(1, 3));
		JPanel used = new JPanel();
		used.setLayout(new GridLayout(usedMeals.size() + 1, 1));
		JLabel usedDesc = new JLabel("Used " + typeOfMeal);
		ButtonGroup allMeals = new ButtonGroup();
		used.add(usedDesc);
		for (T meal : usedMeals) {
			if (meal.toString() != null && !meal.toString().equals("") && !meal.toString().contains("<html>")) {
				JRadioButton currentMeal = new JRadioButton("<html>" + meal.toString() + "<br></html>");
				currentMeal.addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent e) {
						JRadioButton source = (JRadioButton) e.getSource();
						if (source.isSelected()) {
							newMeal = source.getText();
						}
					}
				});
				used.add(currentMeal);
				allMeals.add(currentMeal);
			} else {
				toRemove.add(meal);
			}
		}
		usedMeals.removeAll(toRemove);
		JPanel newMeals = new JPanel();
		newMeals.setLayout(new GridLayout(unUsed.size() + 2, 1));
		newMeals.setMaximumSize(new Dimension(400, 400));
		JLabel newDesc = new JLabel("New " + typeOfMeal);
		newMeals.add(newDesc);
		for (T meal : unUsed) {
			JRadioButton currentMeal = new JRadioButton(meal.toString());
			currentMeal.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					JRadioButton source = (JRadioButton) e.getSource();
					if (source.isSelected()) {
						newMeal = source.getText();
					}
				}
			});
			newMeals.add(currentMeal);
			allMeals.add(currentMeal);
		}
		JRadioButton blank = new JRadioButton("Add " + typeOfMeal);
		blank.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JRadioButton source = (JRadioButton) e.getSource();
				if (source.isSelected()) {
					newMeal = source.getText();
				}
			}
		});
		newMeals.add(blank);
		allMeals.add(blank);
		JTextArea addCustom = new JTextArea();
		JButton confirm = new JButton("Confirm Selection");
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (newMeal.equals("Add a new Meal")) {
					newMeal = addCustom.getText();
				}
				ChangeOptionInWeek.changeDay(oldMeal, newMeal, usedMeals, unUsed, genericFrame, buttonToChange, weekday);
			}
		});
		Border blackline = BorderFactory.createLineBorder(Color.black);
		JPanel customPanel = new JPanel();
		customPanel.setLayout(new GridLayout(2, 1));
		JRadioButton customButton = new JRadioButton("Add a new Meal");
		customButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JRadioButton source = (JRadioButton) e.getSource();
				if (source.isSelected()) {
					newMeal = source.getText();
				}
			}
		});
		allMeals.add(customButton);
		customPanel.add(customButton);
		customPanel.add(addCustom);
		customPanel.setBorder(blackline);

		holdOldAndNew.add(newMeals);
		holdOldAndNew.add(used);
		holdOldAndNew.add(confirm);
		genericFrame.add(holdOldAndNew, BorderLayout.CENTER);
		genericFrame.add(customPanel, BorderLayout.SOUTH);
		genericFrame.pack();
		genericFrame.setLocationRelativeTo(null);
		genericFrame.setVisible(true);
	}

}

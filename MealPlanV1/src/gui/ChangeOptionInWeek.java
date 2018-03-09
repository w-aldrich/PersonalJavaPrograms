package gui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;

import application.WeekDay;
import meals.Breakfast;
import meals.CrockPot;
import meals.Dinner;
import meals.Lunch;
import meals.Snack;

public class ChangeOptionInWeek {

	/**
	 * Driver for switchInList Changes the button, creates borders for empty cells,
	 * and sends all information to switchInList
	 * 
	 * 
	 * @param oldMeal
	 *            --What the old meals name was
	 * @param newMeal
	 *            --The new meals name
	 * @param used
	 *            --an array list of all of the used meals for that category
	 * @param unused
	 *            --an array list of all of the unused meals for that category
	 * @param genericFrame
	 *            --The frame that shows all of the meal options
	 * @param buttonToChange
	 *            --The button you want to change
	 */
	public static <T> void changeDay(String oldMeal, String newMeal, ArrayList<T> used, ArrayList<T> unused,
			JFrame genericFrame, JButton buttonToChange, WeekDay weekday) {

		Border blackline = BorderFactory.createLineBorder(Color.black);
		Border breaktxt = BorderFactory.createTitledBorder(blackline,
				"<html> <strong> <font size=\"5\"> Breakfast </font> </strong> </html>");
		Border snacktxt = BorderFactory.createTitledBorder(blackline,
				"<html> <strong> <font size=\"5\"> Snack </font> </strong> </html>");
		Border lunchtxt = BorderFactory.createTitledBorder(blackline,
				"<html> <strong> <font size=\"5\"> Lunch </font> </strong> </html>");
		Border dinnertxt = BorderFactory.createTitledBorder(blackline,
				"<html> <strong> <font size=\"5\"> Dinner </font> </strong> </html>\"");

		// create proper frame for empty cells
		if (oldMeal.equals("Empty Breakfast")) {
			buttonToChange.setBorder(breaktxt);
		} else if (oldMeal.equals("Empty Snack")) {
			buttonToChange.setBorder(snacktxt);
		} else if (oldMeal.equals("Empty Lunch")) {
			buttonToChange.setBorder(lunchtxt);
		} else if (oldMeal.equals("Empty Dinner")) {
			buttonToChange.setBorder(dinnertxt);
		}

		if (buttonToChange.getBorder().toString().startsWith("com")) {
			T meal = unused.get(0);
			if (meal instanceof Snack) {
				buttonToChange.setBorder(snacktxt);
			} else if (meal instanceof Lunch) {
				buttonToChange.setBorder(lunchtxt);
			} else if (meal instanceof Dinner) {
				buttonToChange.setBorder(dinnertxt);
			}
		}

		if (newMeal.startsWith("Add ")) {
			buttonToChange.setBorder(null);
		}

		// Set the text for the button that changes
		buttonToChange.setText(newMeal);
		// switch the items in the proper lists
		switchInList(oldMeal, newMeal, used, unused, weekday);
		// get rid of frame that shows all meal options

		genericFrame.setVisible(false);
	}

	@SuppressWarnings("unchecked")
	public static <T> void switchInList(String oldMeal, String newMeal, ArrayList<T> used, ArrayList<T> unused,
			WeekDay weekday) {
		// flag that shows if anything has been changed
		boolean changedFlag = false;
		// a variable that shows what kind of meal currently changing
		T typeOfMeal = null;
		// go through all unused meals
		for (T meal : unused) {
			typeOfMeal = meal;
			if (meal.toString().contains(newMeal)) {
				unused.remove(meal);
				if (!used.contains(meal))
					used.add(meal);
				changedFlag = true;
				break;
			}
		}
		// if the meal was not found in the unused meals, check used meals
		if (!changedFlag) {
			for (T meal : used) {
				if (meal.toString().contains(oldMeal)) {
					used.remove(meal);
					unused.add(meal);
					changedFlag = true;
					break;
				}
			}
		}
		// if something was changed in the used or unused meals
		else {
			// // if changing empty cells, done at this point
			// if (oldMeal.equals("Empty Breakfast") || oldMeal.equals("Empty Snack") ||
			// oldMeal.equals("Empty Lunch")
			// || oldMeal.equals("Empty Dinner")) {
			// return;
			// }
			// otherwise go through the used section and remove the meal used
			for (T usedMeal : used) {
				if (usedMeal.toString().contains(oldMeal)) {
					used.remove(usedMeal);
					unused.add(usedMeal);
					// return;
					break;
				}
			}
		}

		if (changedFlag) {
			for (T meal : used) {
				if (meal.toString().equals(newMeal)) {
					if (typeOfMeal instanceof Breakfast) {
						if (weekday.breakfast == null) {
							weekday.breakfast = new Breakfast();
						}
						weekday.breakfast = (Breakfast) meal;
						break;
					} else if (typeOfMeal instanceof Snack) {
						if (weekday.morningSnack == null) {
							weekday.morningSnack = new Snack();
						}
						if (weekday.afternoonSnack == null) {
							weekday.afternoonSnack = new Snack();
						}
						if (weekday.morningSnack.toString().equals(oldMeal)
								|| weekday.morningSnack.toString().equals("")) {
							weekday.morningSnack = (Snack) meal;
						} else {
							weekday.afternoonSnack = (Snack) meal;
						}
						break;
					} else if (typeOfMeal instanceof Lunch) {
						if (weekday.lunch == null) {
							weekday.lunch = new Lunch();
						}
						if (meal instanceof String) {
							Lunch templunch = new Lunch();
							templunch.title = (String) meal;
							templunch.recipe = "";
							templunch.ingredients = "";
							weekday.lunch = templunch;
						} else {
							weekday.lunch = (Lunch) meal;
						}
						break;
					} else if (typeOfMeal instanceof Dinner) {
						if (weekday.dinner == null) {
							weekday.dinner = new Dinner();
						}
						weekday.dinner = (Dinner) meal;
						break;
					} else if (typeOfMeal instanceof CrockPot) {
						if (weekday.dinner == null) {
							weekday.dinner = new CrockPot();
						}
						weekday.dinner = (CrockPot) meal;
						break;
					}
				}
			}
			return;
		}

		// This section is to add a new meal
		if (typeOfMeal instanceof Breakfast) {
			Breakfast b = new Breakfast();
			b.title = newMeal;
			used.add((T) b);
		} else if (typeOfMeal instanceof Snack) {
			Snack s = new Snack();
			s.title = newMeal;
			used.add((T) s);
		} else if (typeOfMeal instanceof Lunch) {
			Lunch l = new Lunch();
			l.title = newMeal;
			used.add((T) newMeal);
		} else if (typeOfMeal instanceof Dinner) {
			Dinner d = new Dinner();
			d.title = newMeal;
			used.add((T) d);
		} else if (typeOfMeal instanceof CrockPot) {
			CrockPot c = new CrockPot();
			c.title = newMeal;
			used.add((T) c);
		}

		for (T meal : used) {
			if (meal.toString().equals(newMeal)) {

				if (typeOfMeal instanceof Breakfast) {
					if (weekday.breakfast == null) {
						weekday.breakfast = new Breakfast();
					}
					weekday.breakfast = (Breakfast) meal;
					break;
				} else if (typeOfMeal instanceof Snack) {
					if (weekday.morningSnack == null) {
						weekday.morningSnack = new Snack();
					}
					if (weekday.afternoonSnack == null) {
						weekday.afternoonSnack = new Snack();
					}
					if (weekday.morningSnack.toString().equals(oldMeal) || weekday.morningSnack.toString().equals("")) {
						weekday.morningSnack = (Snack) meal;
					} else {
						weekday.afternoonSnack = (Snack) meal;
					}
					break;
				} else if (typeOfMeal instanceof Lunch) {
					if (weekday.lunch == null) {
						weekday.lunch = new Lunch();
					}
					if (meal instanceof String) {
						Lunch templunch = new Lunch();
						templunch.title = (String) meal;
						templunch.recipe = "";
						templunch.ingredients = "";
						weekday.lunch = templunch;
					} else {
						weekday.lunch = (Lunch) meal;
					}
					break;
				} else if (typeOfMeal instanceof Dinner) {
					if (weekday.dinner == null) {
						weekday.dinner = new Dinner();
					}
					weekday.dinner = (Dinner) meal;
					break;
				} else if (typeOfMeal instanceof CrockPot) {
					if (weekday.dinner == null) {
						weekday.dinner = new CrockPot();
					}
					weekday.dinner = (CrockPot) meal;
					break;
				}
			}

		}
	}

}

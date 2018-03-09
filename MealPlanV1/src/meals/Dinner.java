package meals;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;

import application.WeekDay;

public class Dinner extends Meals {
	private static ArrayList<Dinner> dinner;
	private static ArrayList<Dinner> usedDinner;

	public Dinner() {
		super();
	}

	@SuppressWarnings("unchecked")
	private static void createAllDinner() {
		usedDinner = new ArrayList<>();
		dinner = GenericMeal.createAll('d', new File("MealIdeas/Dinner.txt"));
	}

	public Dinner getRandomDinner() {
		if (dinner == null) {
			createAllDinner();
		}
		if (dinner.size() <= 0) {
			createAllDinner();
		}
		return GenericMeal.getRandom(usedDinner, dinner);
	}

	public static void showAllDinner(String oldMeal, JButton buttonToChange, WeekDay weekday) {
		if (usedDinner == null || dinner == null) {
			createAllDinner();
		}
		GenericMeal.showAllOptions("Dinner", usedDinner, dinner, oldMeal, buttonToChange, weekday);
	}
}

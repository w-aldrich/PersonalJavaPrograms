package meals;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;

import application.WeekDay;

public class Breakfast extends Meals {
	private static ArrayList<Breakfast> breakfast;
	private static ArrayList<Breakfast> usedBreakfast;

	public Breakfast() {
		super();
	}

	public Breakfast(String title, ArrayList<String> Ingredients) {
		super();
		this.title = title;
	}

	@SuppressWarnings("unchecked")
	private static void createAllBreakfast() {
		usedBreakfast = new ArrayList<>();

		breakfast = GenericMeal.createAll('b', new File("MealIdeas/Breakfast.txt"));
	}

	public Breakfast getRandomBreakfast() {
		if (breakfast == null)
			createAllBreakfast();

		if (breakfast.size() <= 0) {
			createAllBreakfast();
		}
		return GenericMeal.getRandom(usedBreakfast, breakfast);
	}

	public static void showAllBreakfast(String oldMeal, JButton buttonToChange, WeekDay weekday) {
		if (usedBreakfast == null || breakfast == null) {
			createAllBreakfast();
		}
		GenericMeal.showAllOptions("Breakfast", usedBreakfast, breakfast, oldMeal, buttonToChange, weekday);
	}

}

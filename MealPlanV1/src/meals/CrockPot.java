package meals;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;

import application.WeekDay;

public class CrockPot extends Meals {
	private static ArrayList<CrockPot> crockPot;
	private static ArrayList<CrockPot> usedCrockPot;

	public CrockPot() {
		super();
	}

	@SuppressWarnings("unchecked")
	private static void createAllCrockPot() {
		usedCrockPot = new ArrayList<>();
		crockPot = GenericMeal.createAll('c', new File("MealIdeas/CrockPot.txt"));
	}

	public CrockPot getRandomCrockPot() {

		if (crockPot == null) {
			createAllCrockPot();
		}
		if (crockPot.size() <= 0) {
			createAllCrockPot();
		}

		return GenericMeal.getRandom(usedCrockPot, crockPot);
	}

	public static void showAllCrockPot(String oldMeal, JButton buttonToChange, WeekDay weekday) {
		if (usedCrockPot == null || crockPot == null) {
			createAllCrockPot();
		}
		GenericMeal.showAllOptions("CrockPot", usedCrockPot, crockPot, oldMeal, buttonToChange, weekday);
	}
}

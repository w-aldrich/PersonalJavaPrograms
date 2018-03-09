package meals;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;

import application.WeekDay;

public class Lunch extends Meals {
	private static ArrayList<Lunch> lunch;
	private static ArrayList<Lunch> usedLunch;

	public Lunch() {
		super();
	}

	@SuppressWarnings("unchecked")
	private static void createAllLunch() {
		usedLunch = new ArrayList<>();
		lunch = GenericMeal.createAll('l', new File("MealIdeas/Lunch.txt"));
	}

	public Lunch getRandomLunch() {
		if (lunch == null) {
			createAllLunch();
		}
		if (lunch.size() <= 0) {
			createAllLunch();
		}
		return GenericMeal.getRandom(usedLunch, lunch);
	}

	public static void showAllLunch(String oldMeal, JButton buttonToChange, WeekDay weekday) {
		if(usedLunch == null || lunch == null) {
			createAllLunch();
		}
		GenericMeal.showAllOptions("Lunch", usedLunch, lunch, oldMeal, buttonToChange, weekday);
	}
	
	public ArrayList<Lunch> getUnused(){
		return lunch;
	}
	
	public ArrayList<Lunch> getUsed(){
		return usedLunch;
	}

}

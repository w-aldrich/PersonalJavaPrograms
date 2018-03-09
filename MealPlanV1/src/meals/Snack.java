package meals;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;

import application.WeekDay;

public class Snack extends Meals {
	private static ArrayList<Snack> snacks;
	private static ArrayList<Snack> usedSnacks;

	public Snack() {
		super();
	}

	public Snack(String title, ArrayList<String> Ingredients) {
		super();
		this.title = title;
	}
	@SuppressWarnings("unchecked")
	private static void createAllSnacks() {
		usedSnacks = new ArrayList<>();

		snacks = GenericMeal.createAll('s', new File("MealIdeas/Snack.txt"));
	}

	public Snack getRandomSnack() {
		if (snacks == null)
			createAllSnacks();

		if (snacks.size() <= 0) {
			createAllSnacks();
		}
		return GenericMeal.getRandom(usedSnacks, snacks);
	}

	public static <T> void showAllSnacks(String oldMeal, JButton buttonToChange, WeekDay weekday) {
		if(usedSnacks == null || snacks == null) {
			createAllSnacks();
		}
		GenericMeal.showAllOptions("Snack", usedSnacks, snacks, oldMeal, buttonToChange, weekday);
	}
	
	public ArrayList<Snack> getUnused(){
		return snacks;
	}
	
	public ArrayList<Snack> getUsed(){
		return usedSnacks;
	}

}

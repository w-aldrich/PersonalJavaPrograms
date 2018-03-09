package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import meals.Breakfast;
import meals.Dinner;
import meals.Lunch;
import meals.Meals;
import meals.Snack;

public class SaveMenu {

	public SaveMenu(ArrayList<WeekDay> allDays, boolean currentMenu) {
		String fileName = "";
		if (!currentMenu) {
			fileName = JOptionPane.showInputDialog("What would you like to name the file?");
			if (fileName == null) {
				return;
			}
			String prefix = "Saved_Meals/";
			prefix += fileName;
			fileName = prefix;
			fileName += ".txt";
		} else {
			fileName = "Saved_Meals/CurrentMenu.txt";
		}

		File f = new File(fileName);
		try {
			PrintWriter p = new PrintWriter(f);

			for (WeekDay wd : allDays) {
				p.write(wd.name + "\n");

				if(wd.breakfast == null) {
					wd.breakfast = new Breakfast();
				}
				checkMeal(wd.breakfast);
				writeMeal(wd.breakfast, p);

				if(wd.morningSnack == null) {
					wd.morningSnack = new Snack();
				}
				checkMeal(wd.morningSnack);
				writeMeal(wd.morningSnack, p);

				if(wd.lunch == null) {
					wd.lunch = new Lunch();
				}
				checkMeal(wd.lunch);
				writeMeal(wd.lunch, p);

				if(wd.afternoonSnack == null) {
					wd.afternoonSnack = new Snack();
				}
				checkMeal(wd.afternoonSnack);
				writeMeal(wd.afternoonSnack, p);

				if(wd.dinner == null) {
					wd.dinner = new Dinner();
				}
				checkMeal(wd.dinner);
				writeMeal(wd.dinner, p);
			}

			p.flush();
			p.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void checkMeal(Meals meal) {
		if (meal.title.equals("")) {
			meal.title = "X";
			meal.ingredients = "X";
			meal.recipe = "X";
		}
	}

	private void writeMeal(Meals meal, PrintWriter p) {
		if (meal == null) {
			p.write("X" + "\n" + "X" + "\n" + "X" + "\n");
			return;
		}

		p.write(meal.title + "\n");

		if (meal.ingredients.equals("")) {
			p.write("X" + "\n" + "X" + "\n");
			return;
		}

		p.write(meal.ingredients + "\n");
		p.write(meal.recipe + "\n");
	}

}

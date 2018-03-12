package behindTheScenes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class UploadMeals {
	
	public ArrayList<Meal> uploadBreakfast(String filename) throws FileNotFoundException {
		File f = new File(filename);
		Scanner s = new Scanner(f);
		ArrayList<Meal> allSnacks = readFromFile(s);
		return allSnacks;
	}
	
	public ArrayList<Meal> uploadSnack(String filename) throws FileNotFoundException {
		File f = new File(filename);
		Scanner s = new Scanner(f);
		ArrayList<Meal> allSnacks = readFromFile(s);
		return allSnacks;
	}
	
	public ArrayList<Meal> uploadLunch(String filename) throws FileNotFoundException {
		File f = new File(filename);
		Scanner s = new Scanner(f);
		ArrayList<Meal> allSnacks = readFromFile(s);
		return allSnacks;
	}
	
	public ArrayList<Meal> uploadDinner(String filename) throws FileNotFoundException {
		File f = new File(filename);
		Scanner s = new Scanner(f);
		ArrayList<Meal> allSnacks = readFromFile(s);
		return allSnacks;
	}
	
	
	private ArrayList<Meal> readFromFile(Scanner s) {
		ArrayList<Meal> listOfMeals = new ArrayList<>();
		Meal newMeal = new Meal();
		boolean nameFlag = false;
		boolean ingredientFlag = false;
		boolean recipeFlag = false;
		while(s.hasNext()) {
			String nextLine = s.nextLine();
			if(nextLine.equals("Title:")) {
				if(newMeal.getName() != "") {
					listOfMeals.add(newMeal);
					newMeal = new Meal();
				}
				nameFlag = true;
				continue;
			} else if (nextLine.equals("Ingredients:")) {
				ingredientFlag = true;
				continue;
			} else if (nextLine.equals("Recipe:")) {
				ingredientFlag = false;
				recipeFlag = true;
				continue;
			}
			if(nameFlag) {
				newMeal.setName(nextLine);
				nameFlag = false;
			} else if (ingredientFlag) {
				newMeal.addToIngredients(nextLine + " ");
			} else if (recipeFlag) {
				newMeal.addToRecipe(nextLine + " ");
			}
		}
		listOfMeals.add(newMeal);
		return listOfMeals;
	}
}

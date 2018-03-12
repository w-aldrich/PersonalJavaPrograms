package behindTheScenes;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MainDriver {
	
	public static void main(String[] args) {
		FileManipulation upload = new FileManipulation();
		try {
			ArrayList<Meal> breakfastFullList = upload.uploadFile("MealIdeas/Breakfast.txt");
			ArrayList<Meal> dinnerFullList = upload.uploadFile("MealIdeas/Dinner.txt");
			ArrayList<Meal> soupFullList = upload.uploadFile("MealIdeas/Soups.txt");
			ArrayList<Meal> crockFullList = upload.uploadFile("MealIdeas/CrockPot.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}

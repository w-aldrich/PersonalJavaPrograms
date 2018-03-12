package behindTheScenes;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ManualTesting {

//	@Test
//	void Visualtest() {
//		ArrayList<Meal> meals = new ArrayList<>();
//		FileManipulation upload = new FileManipulation();
//		try {
//			meals = upload.uploadFile("MealIdeas/Dinner.txt");
//		} catch (FileNotFoundException e) {
//			assert(false);
//			e.printStackTrace();
//		}
//		for(Meal m: meals) {
//			System.out.println(m.getName());
//			System.out.println(m.getIngredients());
//			System.out.println(m.getRecipe());
//		}
//	}
	
	@Test
	void testSizes() {
		FileManipulation upload = new FileManipulation();
		try {
			ArrayList<Meal> breakfastFullList = upload.uploadFile("MealIdeas/Breakfast.txt");
			ArrayList<Meal> dinnerFullList = upload.uploadFile("MealIdeas/Dinner.txt");
			ArrayList<Meal> soupFullList = upload.uploadFile("MealIdeas/Soups.txt");
			ArrayList<Meal> crockFullList = upload.uploadFile("MealIdeas/CrockPot.txt");
			
			//These tests should pass as of 3-12-18
			//These will change as more are added, must compensate for that
			
			//Check Size
			assertTrue(breakfastFullList.size() == 4);
			assertTrue(dinnerFullList.size() == 29);
			assertTrue(soupFullList.size() == 10);
			assertTrue(crockFullList.size() == 2);
			
			//Check first entry
			assertTrue(crockFullList.get(0).getName().equals("Crock Pot Sweet & Sour Pork Loin with Pineapple"));
			assertTrue(breakfastFullList.get(0).getName().equals("Eggs and Bacon"));
			assertTrue(dinnerFullList.get(0).getName().equals("Coconut Chicken Curry"));
			assertTrue(soupFullList.get(0).getName().equals("Crockpot Chicken Enchilada Soup"));
		} catch (FileNotFoundException e) {
			assert(false);
			e.printStackTrace();
		}
	}

}

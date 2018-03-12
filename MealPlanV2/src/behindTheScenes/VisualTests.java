package behindTheScenes;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class VisualTests {

	@Test
	void test() {
		ArrayList<Meal> meals = new ArrayList<>();
		UploadMeals upload = new UploadMeals();
		try {
			meals = upload.uploadBreakfast("MealIdeas/Breakfast.txt");
		} catch (FileNotFoundException e) {
			assert(false);
			e.printStackTrace();
		}
		assert(meals.size() == 3);
		for(Meal m: meals) {
			System.out.println(m.getName());
			System.out.println(m.getIngredients());
			System.out.println(m.getRecipe());
		}
	}

}

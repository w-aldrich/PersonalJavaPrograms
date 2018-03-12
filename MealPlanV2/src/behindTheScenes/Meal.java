package behindTheScenes;

public class Meal {
	
	private String name;
	private String ingredients;
	private String recipe;
	
	Meal() {
		name = "";
		ingredients = "";
		recipe = "";
	}
	
	Meal(String name) {
		this.name = name;
		ingredients = "";
		recipe = "";
	}
	void setName(String name) {
		this.name = name;
	}
	
	void addToIngredients(String ingredients) {
		this.ingredients += ingredients;
	}
	
	void addToRecipe(String recipe) {
		this.recipe += recipe;
	}
	
	String getName() {
		return name;
	}
	
	String getIngredients() {
		return ingredients;
	}
	
	String getRecipe() {
		return recipe;
	}

}

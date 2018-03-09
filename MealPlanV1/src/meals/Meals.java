package meals;


public abstract class Meals 
{
	public String title;
	public String ingredients;
	public String recipe;
	
	Meals()
	{
		title = "";
		ingredients = "";
		recipe = "";
	}
	
	@Override
	public String toString() {
		return this.title;
	}
	
}

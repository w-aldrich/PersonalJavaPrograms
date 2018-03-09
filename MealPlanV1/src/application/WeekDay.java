package application;

import javax.swing.JButton;

import meals.Meals;

public class WeekDay {

	public String name;
	public JButton breakfastButton;
	public JButton morningSnackButton;
	public JButton lunchButton;
	public JButton afternoonSnackButton;
	public JButton dinnerButton;
	public Meals breakfast;
	public Meals morningSnack;
	public Meals lunch;
	public Meals afternoonSnack;
	public Meals dinner;

	public WeekDay(String name, JButton breakfastButton, JButton morningSnackButton, JButton lunchButton,
			JButton afternoonSnackButton, JButton dinnerButton, Meals breakfast, Meals morningSnack, Meals lunch,
			Meals afternoonSnack, Meals dinner) {
		this.name = name;
		this.breakfastButton = breakfastButton;
		this.morningSnackButton = morningSnackButton;
		this.lunchButton = lunchButton;
		this.afternoonSnackButton = afternoonSnackButton;
		this.dinnerButton = dinnerButton;
		this.breakfast = breakfast;
		this.morningSnack = morningSnack;
		this.lunch = lunch;
		this.afternoonSnack = afternoonSnack;
		this.dinner = dinner;
	}
	
	public WeekDay(String name) {
		this.name = name;
	}

}

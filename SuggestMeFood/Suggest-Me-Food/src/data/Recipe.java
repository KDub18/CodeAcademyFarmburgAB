package data;

import java.util.ArrayList;

public class Recipe {
	String name;
	String imageURL;
	String recipeURL;
	ArrayList<String> ingredients;
	ArrayList<Nutrient> nutrients;
	ArrayList<Fats> fats;
	ArrayList<String> carbs;
	int calories;
	
	public Recipe(){
		name = "NONE";
		ingredients = new ArrayList<String>();
		imageURL = "NONE";
		recipeURL = "NONE";
		nutrients = new ArrayList<Nutrient>();
		calories = 0;
		fats = 	new ArrayList<Fats>();
		carbs = new ArrayList<String>();
	}
	
	Recipe(String recipeName, String theRecipeURL, String recipeImageURL, ArrayList<String> listOfIngredients, ArrayList<Nutrient> listOfNutrients, int numOfCalories){
		name = recipeName;
		imageURL = recipeImageURL;
		recipeURL = theRecipeURL;
		ingredients = listOfIngredients;
		nutrients = listOfNutrients;
		calories = numOfCalories;
		
	}
	
	/**
	 * sets the link to the recipe of the URL
	 * @param url
	 */
	public void setRecipeURL(String url){
		recipeURL = url;
	}
	/**
	 * @return the recipie url as a string
	 */
	public String getRecipieURL(){
		return recipeURL;
	}
		
	/**
	 * set the image URL for this recipie
	 */
	public void setImageURL(String url){
		imageURL = url;
	}
	/**
	 * returns the url of this image
	 * @return url of image for this recipie
	 */
	public String getImageURL(){
		return imageURL;
	}
	/**
	 * @return - name of this recipie
	 */
	public String getName(){
		return name;
	}
	/**
	 * set a new name for the recipie
	 * @param newName - new name of recipie
	 */
	public void setName(String newName){
		name = newName;
	}
	/**
	 * set the ingredients of the recipie
	 * @param ingreds -  array list of ingredients to add
	 */
	public void setIngredients(ArrayList<String> ingreds){
		ingredients = ingreds;
	}
	/**
	 * get ingredient by a specified index in the list of ingredients
	 * @param index -index of ingredient in list of ingredients
	 * @return the string representation of ingredient
	 */
	public String getIngredient(int index){
		return ingredients.get(index);
	}
	/**
	 * adds single ingredient to the list of ingredients
	 * @param ingredient to add
	 */
	public void addIngredient(String ingredient){
		ingredients.add(ingredient);
	}
	/**
	 * @return the array list of ingredients
	 */
	public ArrayList<String> getIngredients(){
		return ingredients;
	}
	/**
	 * returns if a recipie contains an ingredient (food)
	 * @return boolean
	 */
	public boolean containsIngredient(String food){
		for(String ingred: ingredients){
			if(ingred.contains(food)){
				return true;
			}
		}
		return false;
		
	}
	/**
	 * given array of ingredients that are being searched for, output if recipe contains all
	 * @param listOfFoods
	 * @return
	 */
	public boolean containsIngredients(ArrayList<String> listOfFoods){
		for(String food: listOfFoods){
			String s = food.toLowerCase();
			if(!containsIngredient(s)){
				return false;
			}
		}
		return true;
	}
	/**
	 * @return array list of nutrition elements for recipe
	 */
	public ArrayList<Nutrient> getNutrients(){
		return nutrients;
	}
	/**
	 * sets the list of nutrion facts aside from fats and carbs
	 * @param info - list of isolated nutrition facts
	 */
	public void setNutrient(ArrayList<Nutrient> info){
		nutrients = info;
	}
	/**
	 * adds a nutrient to the list of isolated nutrients
	 * @param info -  the string of that nutrient
	 */
	public void addNutrient(Nutrient info){
		nutrients.add(info);
	}
	/**
	 * returns the calorie count of recipe
	 * @return cal count
	 */
	public int getCalorieCount(){
		return calories;
	}
	/**
	 * sets the calories of the recipe
	 * @param cals
	 */
	public void setCalories(int cals){
		calories = cals;
	}
	
	/**
	 * @return the array list of fats
	 */
	public ArrayList<Fats> getFatsList(){return fats;}
	public boolean containsFat(Fats fat){return fats.contains(fat);}
	public void setFatList(ArrayList<Fats> fatsList){fats = fatsList;}
	public void addFat(Fats fat){fats.add(fat);}
	public void addFact(Fats fat){fats.add(fat);}
	
	public ArrayList<String> getCarbsList(){return carbs;}
	public boolean containsCarb(String carb){return carbs.contains(carbs);}
	public void setCarbsList(ArrayList<String> carbsList){carbs = carbsList;}
	public void addCarb(String carb){carbs.add(carb);}
	
	public String toString(){
		String returnMe = "Name: " + name + '\n' +
				"Img URL: " + imageURL + '\n' +
				"Recipe URL: " + recipeURL + '\n' +
				"Calories: " + calories + '\n' +
				"Ingredients: " + ingredients.toString() + '\n' +
				"Fats: " + fats.toString() + '\n' +
				"Carbs: " + carbs.toString() + '\n' ;
				for(Nutrient nutrient: nutrients){
					returnMe += "Nutrition Fact: " + nutrient.toString() + '\n';
				}
		return returnMe;
	}
}

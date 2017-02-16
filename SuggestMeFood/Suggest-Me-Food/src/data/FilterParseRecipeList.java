package data;

import java.io.*;
import java.util.ArrayList;

import data.Recipe;

public class FilterParseRecipeList {
	private String recipeFileLoc;
	private Recipe recipe;
	private ArrayList<Recipe> recipesList;
	private ArrayList<String> searchIngredientsList;
	private ArrayList<String> searchNegativeIngredientsList;
	private boolean goodRecipe = false;
	BufferedReader br = null;


	public FilterParseRecipeList(String recipeFileLocation, ArrayList<String> searchIngredients, ArrayList<String> searchNegIngredients){
		recipeFileLoc = recipeFileLocation;
		searchIngredientsList = searchIngredients;
		searchNegativeIngredientsList = searchNegIngredients;
		recipe = new Recipe();
		recipesList = new ArrayList<Recipe>();

		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(recipeFileLoc));

			while ((sCurrentLine = br.readLine()) != null) {
                if (recipesList.size() > 50){
                    break;
                }
				parseLine(sCurrentLine);
			}
//            System.out.println(recipesList);

		} catch (IOException e) {
			System.out.println("RECIPE TXT FILE NOT FOUND, Please Try Again");
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	public void parseLine(String curLine) {
        String lineID = curLine.split(":")[0];
        String lineVAL = curLine.split(": ")[1];
//System.out.println("lineID: " + lineID);
//System.out.println("lineVAL: " + lineVAL);

        switch (lineID) {
            case "name":
                String theName = lineVAL;
                recipe.setName(theName);
                break;
            case "img_url":
                String url = lineVAL;
                recipe.setImageURL(url);
                break;
            case "recipe_url":
                String url1 = lineVAL;
                recipe.setRecipeURL(url1);
                break;
            case "ingredient":
                String ingred = lineVAL;
                recipe.addIngredient(lineVAL);
                break;
            case "calories":
                String cals = lineVAL;
                recipe.setCalories(Integer.parseInt(lineVAL));
                break;
            case "nutrition":
                String[] vals = lineVAL.split(" ");
                Nutrient nutrient = new Nutrient();
                if (vals[0].equals("Vitamin")) {
                    nutrient.setName(vals[0] + " " + vals[1]);
                    nutrient.setAmount(vals[2]);
                    nutrient.setPercent(Integer.parseInt(vals[3].substring(0, vals[3].length() - 1)));
                } else {
                    nutrient.setName(vals[0]);
                    nutrient.setAmount(vals[1]);
                    nutrient.setPercent(Integer.parseInt(vals[2].substring(0, vals[2].length() - 1)));
                }
                recipe.addNutrient(nutrient);
                break;
            case "nutritionF":
                String fat = lineVAL;
                String[] valsFats = lineVAL.split(" ");
                Fats fatC = new Fats();
                fatC.setFat(valsFats[0]);
                fatC.setAmount(valsFats[1]);
                fatC.setPercent(Integer.parseInt(valsFats[2].substring(0, valsFats[2].length() - 1)));
                recipe.addFat(fatC);
                break;
            case "nutritionC":
                String carb = lineVAL;
                recipe.addCarb(carb);
                break;
            case "header":
                recipe = new Recipe();
                break;
            case "footer":
//System.out.println(recipe.containsIngredients(searchIngredientsList));
                if (recipe.containsIngredients(searchIngredientsList)) {
                    if (searchNegativeIngredientsList != null && !searchNegativeIngredientsList.isEmpty()) {
                        if (!recipe.containsIngredients(searchNegativeIngredientsList)) {
                            recipesList.add(recipe);
                        }
                    } else {
                        recipesList.add(recipe);
                    }
                }
                recipe = new Recipe();
                //accelerateToNextRecipe();
                break;

        }
		
	}	
	
	/*private void accelerateToNextRecipe() {
		// TODO Auto-generated method stub
		String sCurrentLine;
		try{
			sCurrentLine = br.readLine();
			while(!sCurrentLine.equals("header: RECIPE")){
					
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}*/

	public ArrayList<Recipe> getRecipes(){
		return recipesList;
	}
	
}

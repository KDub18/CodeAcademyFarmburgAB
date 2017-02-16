import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.MainScreenController;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

public class Driver extends Application{

    public static void main(String[] args){
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\weilin\\Desktop\\chromedriver.exe");
//        ChromeDriver main = new ChromeDriver();
//        String[] foods = {"chinese food", "apple", "ketchup", "lime", "sour cream", "ginger", "pepper", "peanut",
//                "chicken", "mint", "peppermint", "butter", "alfredo", "penne", "vodka", "wine", "cream", "creamy",
//                "turkey", "crab", "Cheese", "eggs", "soup", "broth", "pasta", "beef", "corn", "broccoli", "lamb",
//                    "rice", "macaroni", "pizza", "bagel", "cookie", "salmon", "fruit", "tuna", "sushi",
//                "pig", "pork", "organic", "liver", "chops", "kidney", "bacon", "cereal", "cake", "pie", "rice", "beans",
//                "flour", "brad crumbs", "barley", "ramen", "japanese", "taco", "burrito",
//                    "fries", "ham", "balogna", "potato", "salad", "sub", "burger", "chinese", "korean", "indian", "curry",
//                    "avacado", "cabbage", "mushroom", "olives", "pumpkin", "smoothie", "shark", "shellfish", "clams", "mussel",
//                "mullet", "squid", "flounder", "cat fish", "cod", "anchovies", "mackerel", "fillet", "soy sauce", "oyster sauce", "chocolate", "hoisin sauce",
//                "abalone", "coconut", "beer", "vanilla", "mayonaise", "sriracha", "buffalo", "new york", "jello", "soda", "tofu", "tea"};
//
//        HashSet<String> set = new HashSet<>();
//
//        BufferedWriter bufferedWriter = null;
//        try {
//            bufferedWriter = new BufferedWriter(new FileWriter(new File("data")));
//
//            for (String food : foods) {
//
//                main.get("https://www.edamam.com/recipes/" + food);
//                try {
//                    for (int i = 1; i < 200; i++) {
//                        WebElement webElement = new WebDriverWait(main, 3).until(ExpectedConditions.elementToBeClickable(By.cssSelector(
//                                "#search-results > div.meals-list > div > ul > li:nth-child(" + i + ") > div.box > div > div.object > a > span.image > span > img")));
//                        String imageURL = webElement.getAttribute("src");
//                        WebElement linkElement = new WebDriverWait(main, 3).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#search-results > div.meals-list > div > ul > li:nth-child(" + i + ") > div.box > div > div.object > a")));
//
//                        String urlLink = linkElement.getAttribute("href");
//
//                        String title = null;
//                        ArrayList<String> ingredients = new ArrayList<>();
//                        int calories = -1;
//
//                        try {
//                            URL url = new URL(urlLink);
//                            URLConnection connection = url.openConnection();
//                            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
//                            String inputLine;
//                            while ((inputLine = in.readLine()) != null) {
//                                if (title == null) {
//                                    if (inputLine.startsWith("<title")) {
//                                        title = inputLine.substring(inputLine.indexOf(">") + 1);
//                                        title = title.substring(0, title.lastIndexOf("- Edamam"));
//                                    }
//                                }
//    //                    System.out.println(i/nputLine);
//                                if (inputLine.trim().startsWith("<li itemprop=\"ingredients\">")) {
//                                    String ingredient = inputLine.substring("<li itemprop=\"ingredients\">".length());
//                                    ingredient = ingredient.substring(0, ingredient.lastIndexOf("<"));
//                                    ingredients.add(ingredient);
//                                }
//
//                                if (inputLine.trim().startsWith("<span itemprop=\"calories\" id=\"kcal-val\" class=\"num\">")) {
//                                    String calorieText = inputLine.trim().substring("<span itemprop=\"calories\" id=\"kcal-val\" class=\"num\">".length());
//                                    calorieText = calorieText.substring(0, calorieText.lastIndexOf("<"));
//                                    calories = Integer.parseInt(calorieText);
//                                }
//                            }
//                            in.close();
//                            if (set.contains(title)) {
//                                System.out.println("Already Contained " + title);
//                            } else {
//                                set.add(title);
//                                System.out.println("Stored: " + title);
//                                bufferedWriter.write("header: RECIPE");
//                                bufferedWriter.newLine();
//                                bufferedWriter.write("name: " + title);
//                                bufferedWriter.newLine();
//                                bufferedWriter.write("img_url: " + imageURL);
//                                bufferedWriter.newLine();
//                                bufferedWriter.write("recipe_url: " + urlLink);
//                                bufferedWriter.newLine();
//                                for (String ingredient : ingredients) {
//                                    bufferedWriter.write("ingredient: " + ingredient);
//                                    bufferedWriter.newLine();
//                                }
//                                bufferedWriter.write("calories: " + calories);
//                                bufferedWriter.newLine();
//                                bufferedWriter.write("footer: END");
//                            }
//                        } catch (Exception e) {
//
//                        }
//                        bufferedWriter.newLine();
//
//                    }
//                } catch (NoSuchElementException e) {
//                    System.out.println("Finished Searching: " + food);
//                    bufferedWriter.flush();
//                } catch (org.openqa.selenium.TimeoutException e){
//                    System.out.println("Finished Searching: " + food);
//                    bufferedWriter.flush();
//                }
//            }
//            bufferedWriter.close();
//        } catch (IOException e){
//            System.out.println("FUCKED U ON UFILE");
//            return;
//        }
//        main.close();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Suggest Me Food!");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ui/Main Screen.fxml"));
        Parent root = fxmlLoader.load();
        ((MainScreenController)fxmlLoader.getController()).setStage(stage);
        Scene scene = new Scene(root, Color.TRANSPARENT);

//        scene.setFill(null);
        stage.setScene(scene);
        stage.show();
    }
}

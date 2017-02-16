package ui;

import data.Fats;
import data.Nutrient;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ObservableRecipe {

    private String recipeName, imageURL, recipeURL;
    private ArrayList<String> ingredients, carbs;
    private int calories;
    private ArrayList<Fats> fats;
    private ArrayList<Nutrient> nutrients;

    public ObservableRecipe(String recipeName, String imageURL, String recipeURL, int calories,
                            ArrayList<String> ingredients, ArrayList<Nutrient>  nutrients, ArrayList<Fats> fats, ArrayList<String> carbs){
        this.recipeName = recipeName;
        this.imageURL = imageURL;
        this.ingredients = ingredients;
        this.nutrients = nutrients;
        this.fats = fats;
        this.calories = calories;
        this.carbs = carbs;
        this.recipeURL = recipeURL;
    }


    public Pane getPane(){
        Pane pane = new Pane();
//        System.out.println(imageURL);
        ImageView imageView = new ImageView(new Image(imageURL));
        pane.getChildren().add(imageView);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        pane.setPrefWidth(700);
        pane.setPrefHeight(100);
        imageView.setLayoutX(0);
        imageView.setLayoutY(0);
        Label title = new Label(recipeName);
        title.setFont(Font.font("Segoe Print", 20));
        pane.getChildren().add(title);
        title.setLayoutX(110);
        title.setLayoutY(5);

        Label ingredientsLabel = new Label();
        String text = "";
        for (String ingredient : ingredients){
            text = text + ", " + ingredient;
        }

        ingredientsLabel.setText(text.substring(1, text.length()));
        pane.getChildren().add(ingredientsLabel);
        ingredientsLabel.setLayoutX(110);
        ingredientsLabel.setLayoutY(40);
        ingredientsLabel.setMaxWidth(250);

        Label calorieLabel = new Label("Calories: " + calories);
        pane.getChildren().add(calorieLabel);
        calorieLabel.setLayoutX(115);
        calorieLabel.setLayoutY(65);
        pane.setStyle("-fx-background-color: #dbcea2; -fx-border-color:#af9d83; -fx-border-width: 1; -fx-border-style: solid;");
        pane.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.setStyle("-fx-background-color: #efe7cb; -fx-border-color:#af9d83; -fx-border-width: 1; -fx-border-style: solid;");
            }
        });
        pane.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.setStyle("-fx-background-color: #dbcea2; -fx-border-color:#af9d83; -fx-border-width: 1; -fx-border-style: solid;");
            }
        });
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Web View.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    ((WebViewController) fxmlLoader.getController()).load(recipeURL);
                    Stage stage = new Stage();
                    stage.setTitle(recipeName);
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (Exception e){

                }
            }
        });
        return pane;
    }


}

package ui;

import data.FilterParseRecipeList;
import data.Recipe;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainScreenController {

    private static double xOffset = 0;
    private static double yOffset = 0;

    @FXML
    private TreeView<String> treeView;

    @FXML
    private AnchorPane recipesAnchorPane;

    @FXML
    private TextField searchBar;

    @FXML
    private Pane close;

    @FXML
    private Pane minimize;

    @FXML
    private ProgressBar progressBar;

    private Stage primaryStage;
    @FXML
    public void initialize(){
        treeView.setRoot(new TreeItem<>("Foods"));

        progressBar.setVisible(false);
        ImageView chickenImage = new ImageView(new Image(getClass().getResourceAsStream("/assets/chicken.png")));
        chickenImage.setFitHeight(24);
        chickenImage.setFitWidth(24);
        TreeItem<String> meat = new TreeItem<>("Meat", chickenImage);
        meat.getChildren().addAll(
                new TreeItem<>("Beef"),
                new TreeItem<>("Chicken"),
                new TreeItem<>("Duck"),
                new TreeItem<>("Lamb"),
                new TreeItem<>("Pork"),
                new TreeItem<>("Salmon"),
                new TreeItem<>("Shrimp"),
                new TreeItem<>("Tuna")
        );

        ImageView vegetableImage = new ImageView(new Image(getClass().getResourceAsStream("/assets/vegetable.png")));
        vegetableImage.setFitHeight(24);
        vegetableImage.setFitWidth(24);
        TreeItem<String> vegetables = new TreeItem<>("Vegetable", vegetableImage);
        vegetables.getChildren().addAll(new TreeItem<>("Broccoli"),
                new TreeItem<>("Brussels Sprouts"),
                new TreeItem<>("Cabbage"),
                new TreeItem<>("Carrot"),
                new TreeItem<>("Corn"),
                new TreeItem<>("Kale"),
                new TreeItem<>("Lettuce"),
                new TreeItem<>("Onion")
        );


        ImageView seasoningImage = new ImageView(new Image(getClass().getResourceAsStream("/assets/Seasoning.png")));
        seasoningImage.setFitHeight(24);
        seasoningImage.setFitWidth(24);
        TreeItem<String> seasoning = new TreeItem<>("Seasoning", seasoningImage);
        seasoning.getChildren().addAll(new TreeItem<>("Chili"),
                new TreeItem<>("Chives"),
                new TreeItem<>("Garlic"),
                new TreeItem<>("Honey"),
                new TreeItem<>("Oregano"),
                new TreeItem<>("Pepper"),
                new TreeItem<>("Salt"),
                new TreeItem<>("Sugar"),
                new TreeItem<>("Thyme"),
                new TreeItem<>("Vinegar")

        );


        ImageView pastaImage = new ImageView(new Image(getClass().getResourceAsStream("/assets/Pasta.png")));
        pastaImage.setFitHeight(24);
        pastaImage.setFitWidth(24);
        TreeItem<String> pasta = new TreeItem<>("Pasta", pastaImage);
        pasta.getChildren().addAll(
                new TreeItem<>("Elbows"),
                new TreeItem<>("Farfalloni"),
                new TreeItem<>("Fettuccine"),
                new TreeItem<>("Gigli"),
                new TreeItem<>("Lasagna"),
                new TreeItem<>("Linguine"),
                new TreeItem<>("Macaroni"),
                new TreeItem<>("Penne"),
                new TreeItem<>("Rotini"),
                new TreeItem<>("Spaghetti"),
                new TreeItem<>("Vermicello"),
                new TreeItem<>("Ziti"),
                new TreeItem<>("Zitoni")
        );


        ImageView grainsImage = new ImageView(new Image(getClass().getResourceAsStream("/assets/Rice.png")));
        grainsImage.setFitHeight(24);
        grainsImage.setFitWidth(24);
        TreeItem<String> rice = new TreeItem<>("Grains", grainsImage);
        rice.getChildren().addAll(
                new TreeItem<>("Brown Rice"),
                new TreeItem<>("Forbidden Rice"),
                new TreeItem<>("Jasmine Rice"),
                new TreeItem<>("Sticky Rice"),
                new TreeItem<>("White Rice"),
                new TreeItem<>("Wild Rice")

        );

        treeView.getRoot().getChildren().add(meat);
        treeView.getRoot().getChildren().add(vegetables);
        treeView.getRoot().getChildren().add(seasoning);
        treeView.getRoot().getChildren().add(pasta);
        treeView.getRoot().getChildren().add(rice);

        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.getChildren().size() > 0){
                return;
            }
            String search = searchBar.getText().trim().toLowerCase();
            if (search.contains(newValue.getValue().toLowerCase())){
                return;
            }
            if (search.length() > 0 && !search.substring(search.length() - 1).equals(",")){
                searchBar.setText(searchBar.getText() + ", ");
            }
            searchBar.setText(searchBar.getText() + newValue.getValue() + ", ");
        });

    }

    @FXML
    public void mouseDraggedRoot(MouseEvent event) {
        primaryStage.setX(event.getScreenX() - xOffset);
        primaryStage.setY(event.getScreenY() - yOffset);
    }

    @FXML
    public void mousePressedRoot(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    @FXML
    public void mouseEnteredX(Event event) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(0), new KeyValue(close.scaleXProperty(), close.getScaleX())),
                new KeyFrame(Duration.millis(0), new KeyValue(close.scaleYProperty(), close.getScaleY())),
                new KeyFrame(Duration.millis(200), new KeyValue(close.scaleXProperty(), 0.13)),
                new KeyFrame(Duration.millis(200), new KeyValue(close.scaleYProperty(), 0.13)));
        timeline.play();
    }

    @FXML
    public void mouseExitedX(Event event) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(0), new KeyValue(close.scaleXProperty(), close.getScaleX())),
                new KeyFrame(Duration.millis(0), new KeyValue(close.scaleYProperty(), close.getScaleY())),
                new KeyFrame(Duration.millis(200), new KeyValue(close.scaleXProperty(), 0.1)),
                new KeyFrame(Duration.millis(200), new KeyValue(close.scaleYProperty(), 0.1)));
        timeline.play();
    }

    @FXML
    public void mouseEnteredMinimize(Event event) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(0), new KeyValue(minimize.scaleXProperty(), minimize.getScaleX())),
                new KeyFrame(Duration.millis(0), new KeyValue(minimize.scaleYProperty(), minimize.getScaleY())),
                new KeyFrame(Duration.millis(200), new KeyValue(minimize.scaleXProperty(), 0.13)),
                new KeyFrame(Duration.millis(200), new KeyValue(minimize.scaleYProperty(), 0.13)));
        timeline.play();
    }

    @FXML
    public void mouseExitedMinimize(Event event) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(0), new KeyValue(minimize.scaleXProperty(), minimize.getScaleX())),
                new KeyFrame(Duration.millis(0), new KeyValue(minimize.scaleYProperty(), minimize.getScaleY())),
                new KeyFrame(Duration.millis(200), new KeyValue(minimize.scaleXProperty(), 0.1)),
                new KeyFrame(Duration.millis(200), new KeyValue(minimize.scaleYProperty(), 0.1)));
        timeline.play();
    }

    @FXML
    public void close(){
        primaryStage.close();
    }

    @FXML
    public void minimize(){
        primaryStage.setIconified(true);
    }

    @FXML
    public void keyPressedSearch(KeyEvent keyEvent){
        if (keyEvent.getCode() == KeyCode.ENTER){
            search();
        }
    }

    @FXML
    public void mouseClickedSearch(MouseEvent mouseEvent){
        if (mouseEvent.getX() > 655 && mouseEvent.getX() < 690){
            search();
        }
    }

    public void search(){
        System.out.println("Searching");
        progressBar.setVisible(true);
        Platform.runLater(() -> recipesAnchorPane.getChildren().clear());
        new Thread(){
            @Override
            public void run(){

                ArrayList<String> searchTerms = new ArrayList<>();
                for (String term : searchBar.getText().split(",")){
                    String normalized = term.trim().toLowerCase();
                    if (normalized.length() > 1){
                        searchTerms.add(normalized);
                    }
                }
                ArrayList<Recipe> recipes = new FilterParseRecipeList("data", searchTerms, null).getRecipes();

                System.out.println("Search Finished");

                List<Pane> panes = new ArrayList<>();
                int i = 0;
                for (Recipe recipe : recipes){
                    ObservableRecipe observableRecipe = new ObservableRecipe(recipe.getName(), recipe.getImageURL(), recipe.getRecipieURL(), recipe.getCalorieCount(), recipe.getIngredients(), recipe.getNutrients(), recipe.getFatsList(), recipe.getCarbsList());
                    panes.add(observableRecipe.getPane());
                    final double progress = i++/(double)recipes.size();
                    Platform.runLater(() -> progressBar.setProgress(progress));
                }

                Platform.runLater(() -> {
                    recipesAnchorPane.getChildren().clear();
                    long startTime = System.currentTimeMillis();
                    recipesAnchorPane.getChildren().addAll(panes);
                    System.out.println("Diff: " + (startTime - System.currentTimeMillis()));


                    recipesAnchorPane.setPrefWidth(350);
                    int sum = 0;
                    for (Node node : recipesAnchorPane.getChildren()) {
                        node.setLayoutY(sum);
                        sum += ((Pane) node).getPrefHeight();
                    }
                    recipesAnchorPane.setPrefHeight(sum);
                    progressBar.setVisible(false);
                });
            }
        }.start();

    }

    @FXML
    public void launchCalculator() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Web View.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        ((WebViewController)fxmlLoader.getController()).load("http://www.bodybuilding.com/fun/macronutrients_calculator.htm");
        Stage stage = new Stage();
        stage.setTitle("Macro Nutrients Calculator");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void setStage(Stage stage){
        this.primaryStage = stage;
    }
}

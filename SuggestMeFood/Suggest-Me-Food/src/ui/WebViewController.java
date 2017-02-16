package ui;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class WebViewController {

    @FXML private WebView webView;

    private WebEngine webEngine;

    @FXML
    public void initialize(){
        webEngine = webView.getEngine();
//        webEngine.load("http://www.bodybuilding.com/fun/macronutrients_calculator.html");
    }

    public void load(String url){
        webEngine.load(url);
    }

}

package com.example.project11.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class StartController extends Controller implements Initializable {

    @FXML
    ImageView statisticsImage;
    @FXML
    ImageView questionsImage;

    public StartController() {
        super();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img = new Image(getClass().getResource("/images/statisticsicon.png").toExternalForm());
        statisticsImage.setImage(img);

    }

    public void mouseEnterComponent(MouseEvent mouseEvent) {
        super.mouseEnterComponent(mouseEvent);
    }

    public void mouseExitComponent(MouseEvent mouseEvent) {
        super.mouseExitComponent(mouseEvent);
    }

    public void mouseClickedComponent(MouseEvent mouseEvent) throws IOException {
        super.mouseClickedComponent(mouseEvent);
    }

    public void changeScene(MouseEvent mouseEvent, String sceneName) throws IOException {
        super.changeScene(mouseEvent, sceneName);
    }
    @FXML
    public void loadNewScreen(MouseEvent mouseEvent) {
        try {
            // Load the FXML file for the new screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
            Scene newScene = new Scene(loader.load());

            // Get the current stage
            Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

            // Set the new scene on the current stage
            currentStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

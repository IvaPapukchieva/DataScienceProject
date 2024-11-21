package com.example.project11.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class StartController extends Controller implements Initializable {

    @FXML
    ImageView statisticsImage;

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


}

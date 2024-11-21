package com.example.project11.Controllers;

import com.example.project11.DataCallback;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GradesFilterController extends Controller implements Initializable {

    @FXML
    Slider sliderRange1
            ,sliderRange2
            ,slider;

    @FXML
    Label selectLabel
            ,selectMinLabel
            ,selectMaxLabel
            ,enterNumbersLabel;

    @FXML
    TextField textField;
    @FXML
    ToggleGroup gradesToggleGroup;

    @FXML
    Button addButton;

    private DataCallback dataCallback;

    // Method to set callback for
    public void setDataCallback(DataCallback dataCallback) {
        this.dataCallback = dataCallback;
    }

    public void sendDataToFirstWindow() {
        if (dataCallback != null) {


            Toggle selected = gradesToggleGroup.getSelectedToggle();
            if(selected != null) {
                RadioButton radioButton = (RadioButton) selected;
                if(radioButton.getText().equals("Pick Number")) {
                    dataCallback.onDataReceived(slider);
                } else if(radioButton.getText().equals("Pick Range")) {
                    dataCallback.onDataReceived(sliderRange1,sliderRange2);
                } else if(radioButton.getText().equals("Enter Multiple Numbers")) {
                    dataCallback.onDataReceived(textField);
                }

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void handleGradesToggleGroup() {
        Toggle selected = gradesToggleGroup.getSelectedToggle();
        if(selected != null) {
            RadioButton radioButton = (RadioButton) selected;
            if(radioButton.getText().equals("Pick Number")) {
                slider.setVisible(true);
                sliderRange1.setVisible(false);
                sliderRange2.setVisible(false);
                textField.setVisible(false);

                selectLabel.setVisible(true);
                selectMinLabel.setVisible(false);
                selectMaxLabel.setVisible(false);
                enterNumbersLabel.setVisible(false);
            } else if(radioButton.getText().equals("Pick Range")) {
                slider.setVisible(false);
                sliderRange1.setVisible(true);
                sliderRange2.setVisible(true);
                textField.setVisible(false);

                selectLabel.setVisible(false);
                selectMinLabel.setVisible(true);
                selectMaxLabel.setVisible(true);
                enterNumbersLabel.setVisible(false);
            } else if(radioButton.getText().equals("Enter Multiple Numbers")) {
                slider.setVisible(false);
                sliderRange1.setVisible(false);
                sliderRange2.setVisible(false);
                textField.setVisible(true);

                selectLabel.setVisible(false);
                selectMinLabel.setVisible(false);
                selectMaxLabel.setVisible(false);
                enterNumbersLabel.setVisible(true);
            }

        }

    }
    public void handleAddFilter() {
        Stage stage = (Stage) addButton.getScene().getWindow();
        sendDataToFirstWindow();
        stage.close();
    }
}

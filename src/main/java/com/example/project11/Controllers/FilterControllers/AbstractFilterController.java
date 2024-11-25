package com.example.project11.Controllers.FilterControllers;

import com.example.project11.Controllers.Controller;
import com.example.project11.DataCallback;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public abstract class AbstractFilterController extends Controller {

    @FXML
    protected Slider sliderRange1, sliderRange2, slider;

    @FXML
    protected Label selectLabel, selectMinLabel, selectMaxLabel, enterNumbersLabel;

    @FXML
    protected TextField textField;

    @FXML
    protected ToggleGroup toggleGroup;

    @FXML
    protected Button addButton;

    public void setDataCallback(DataCallback dataCallback) {
        super.setDataCallback(dataCallback);
    }

    public void handleAddFilter() {
        Stage stage = (Stage) addButton.getScene().getWindow();
        sendDataToFirstWindow();
        stage.close();
    }

    public void handleToggleGroupSelection() {
        Toggle selected = toggleGroup.getSelectedToggle();
        if (selected != null) {
            RadioButton radioButton = (RadioButton) selected;
            switch (radioButton.getText()) {
                case "Pick Number":
                    setVisibility(true, false, false, false, true, false, false, false);
                    break;
                case "Pick Range":
                    setVisibility(false, true, true, false, false, true, true, false);
                    break;
                case "Enter Multiple Numbers":
                    setVisibility(false, false, false, true, false, false, false, true);
                    break;
            }
        }
    }

    private void setVisibility(
            boolean sliderVisible, boolean sliderRange1Visible, boolean sliderRange2Visible, boolean textFieldVisible,
            boolean selectLabelVisible, boolean selectMinLabelVisible, boolean selectMaxLabelVisible, boolean enterNumbersLabelVisible
    ) {
        slider.setVisible(sliderVisible);
        sliderRange1.setVisible(sliderRange1Visible);
        sliderRange2.setVisible(sliderRange2Visible);
        textField.setVisible(textFieldVisible);

        selectLabel.setVisible(selectLabelVisible);
        selectMinLabel.setVisible(selectMinLabelVisible);
        selectMaxLabel.setVisible(selectMaxLabelVisible);
        enterNumbersLabel.setVisible(enterNumbersLabelVisible);
    }

    protected Toggle getSelectedToggle() {
        return toggleGroup.getSelectedToggle();
    }

    protected abstract void sendDataToFirstWindow();
}

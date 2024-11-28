package com.example.project11.Controllers.FilterControllers;

import com.example.project11.DataCallback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ByStudentIdFilterController extends AbstractFilterController {

    @FXML
    private TextField textFieldMin, textFieldMax, textFieldIndices;
    @FXML
    private Label minLabel, maxLabel, indicesLabel;

    @Override
    public void handleToggleGroupSelection() {
        Toggle selected = toggleGroup.getSelectedToggle();
        if (selected == null) return;
        RadioButton radioButton = (RadioButton) selected;
        switch (radioButton.getText()) {
            case "Pick Range":
                textFieldMin.setVisible(true);
                textFieldMax.setVisible(true);
                maxLabel.setVisible(true);
                minLabel.setVisible(true);
                indicesLabel.setVisible(false);
                textFieldIndices.setVisible(false);

                break;
            case "Enter Multiple Indices":
                textFieldMin.setVisible(false);
                textFieldMax.setVisible(false);
                maxLabel.setVisible(false);
                minLabel.setVisible(false);
                indicesLabel.setVisible(true);
                textFieldIndices.setVisible(true);
                break;
        }

    }

    public void handleAddFilter() {
        super.handleAddFilter();
    }

    @Override
    protected void sendDataToFirstWindow() {
        DataCallback dataCallback = super.getDataCallback();
        if (dataCallback == null) return;
        Toggle selected = getSelectedToggle();

        if(selected != null) {
            RadioButton radioButton = (RadioButton) selected;
            switch (radioButton.getText()) {
                case "Pick Range":
                    dataCallback.onDataReceived(textFieldMin, textFieldMax);
                    break;
                case "Enter Multiple Indices":
                    dataCallback.onDataReceived(textFieldIndices);
                    break;

            }
        }
    }
}

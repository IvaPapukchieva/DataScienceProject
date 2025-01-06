package com.example.project11.Controllers.FilterControllers;

import com.example.project11.DataCallback;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;

public class ByStudentNGFilterController  extends AbstractFilterController{


    public void setDataCallback(DataCallback dataCallback) {
        super.setDataCallback(dataCallback);
    }

    @Override
    public void sendDataToFirstWindow() {
        DataCallback dataCallback = super.getDataCallback();

        if (dataCallback != null) {


            Toggle selected = super.toggleGroup.getSelectedToggle();
            if (selected != null) {
                RadioButton radioButton = (RadioButton) selected;
                if (radioButton.getText().equals("Pick Number")) {
                    dataCallback.onDataReceived(slider);
                } else if (radioButton.getText().equals("Pick Range")) {
                    dataCallback.onDataReceived(sliderRange1, sliderRange2);
                } else if (radioButton.getText().equals("Enter Multiple Numbers")) {
                    dataCallback.onDataReceived(textField);
                }

            }
        }
    }

    public void handleAddFilter() {
        super.handleAddFilter();
    }

    public void handleStudentNGToggleGroup() {
        super.handleToggleGroupSelection();
    }

}

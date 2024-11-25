package com.example.project11.Controllers.FilterControllers;

import com.example.project11.DataCallback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ByPropertyFilterController extends AbstractFilterController {

    @FXML private TextField textFieldMin, textFieldMax;
    @FXML private Label minLabel, maxLabel, subCategoryLabel;
    @FXML private ComboBox subCategoryComboBox;

    @Override
    public void handleToggleGroupSelection() {
        Toggle selected = getSelectedToggle();
        if (selected != null) {
            RadioButton radioButton = (RadioButton) selected;
            switch (radioButton.getText()) {
                case "Neuro-Synaptic Interface Level", "Telepathic Synchronisation Index", "Chrono-Adaptation Rate",
                     "Aetheric Resonance Capacity":
                    setVisibility(false, false, false, false, true, true);
                    subCategoryComboBox.setItems(getItemList(radioButton.getText()));

                    break;
                case "Plasma Conductivity Quotient":
                    setVisibility(true, true, true, true, false, false);
                    break;

            }
        }
    }

    private ObservableList<String> getItemList(String category) {
        switch (category) {
            case "Neuro-Synaptic Interface Level":
                return FXCollections.observableArrayList("nothing", "low", "medium", "full", "high");
            case "Chrono-Adaptation Rate":
                return FXCollections.observableArrayList("1 tau", "2 tau", "3 tau");
            case "Telepathic Synchronisation Index":
                return FXCollections.observableArrayList("A", "B", "C", "D", "E", "F");
            case "Aetheric Resonance Capacity":
                return FXCollections.observableArrayList("0.1 Hz", "0.5 Hz", "1.0 Hz", "5.0 Hz");
            default:
                return null;
        }
    }

    private void setVisibility(boolean textFieldMinVisible, boolean textFieldMaxVisible,
                               boolean minLabelVisible, boolean maxLabelVisible,
                               boolean subCategoryLabelVisible, boolean subCategoryComboBoxVisible) {
        textFieldMin.setVisible(textFieldMinVisible);
        textFieldMax.setVisible(textFieldMaxVisible);
        minLabel.setVisible(minLabelVisible);
        maxLabel.setVisible(maxLabelVisible);
        subCategoryLabel.setVisible(subCategoryLabelVisible);
        subCategoryComboBox.setVisible(subCategoryComboBoxVisible);
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
                case "Neuro-Synaptic Interface Level", "Telepathic Synchronisation Index", "Chrono-Adaptation Rate",
                     "Aetheric Resonance Capacity":
                    dataCallback.onDataReceived(subCategoryComboBox, radioButton);
                    break;
                case "Plasma Conductivity Quotient":
                    dataCallback.onDataReceived(textFieldMin, textFieldMax, radioButton);
                    break;

            }

        }
    }
}

package com.example.project11.Controllers;


import com.example.project11.Controllers.FilterControllers.*;
import com.example.project11.FilterProcessing.Filter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SelectionsController extends Controller implements Initializable {

    @FXML private Button submit;
    @FXML private ComboBox<String> ngSelect;
    @FXML private ToggleGroup dataSets;
    @FXML private ToggleButton ngButton;
    @FXML private AnchorPane container;
    @FXML private ComboBox<String> addFilter;
    @FXML private VBox filtersContainer;
    @FXML private ScrollPane filtersScrollPane;
    private ArrayList<Filter> filters;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ngSelect.getItems().addAll("Weighted Randomization", "Replace With Average", "Remove NGs");
        addFilter.getItems().addAll("By Grade", "By Course", "By GPA", "By Property", "By Student ID");

        // Center the submit button
        centerButtonInAnchorPane();
    }

    private void centerButtonInAnchorPane() {
        AnchorPane anchorPane = (AnchorPane) submit.getParent();
        AnchorPane.setLeftAnchor(submit,  (anchorPane.getPrefWidth() - submit.getWidth()) / 2);
        AnchorPane.setTopAnchor(submit, (anchorPane.getPrefHeight() - submit.getHeight()) / 2);
    }

    public void submitButton() {
        System.out.println(dataSets.getSelectedToggle());
    }

    public void handleDataSetsToggle() {
        Toggle selectedToggle = dataSets.getSelectedToggle();
        if (selectedToggle != null) {
            RadioButton radioButton = (RadioButton) selectedToggle;
            boolean isUndergraduateGrades = radioButton.getText().equals("Undergraduate Grades");
            ngButton.setVisible(isUndergraduateGrades);
            ngSelect.setVisible(isUndergraduateGrades);
            if (!isUndergraduateGrades) {
                ngButton.setSelected(false);
                ngSelect.getSelectionModel().clearSelection();
                ngSelect.setValue(null);
            }
        }
    }
// START OF HANDLING RETRIEVAL OF USER INPUT OF FILTER OPTIONS
    public void handleAddFilter() {
        String selectedFilter = addFilter.getSelectionModel().getSelectedItem();
        if (selectedFilter == null) return;

        handleFilter(selectedFilter);
        openSmallWindow(selectedFilter);
    }

    private void handleFilter(String selectedFilter) {
        Controller controller = null;
        switch (selectedFilter) {
            case "By Grade":
                controller = (ByGradesFilterController) getController(selectedFilter);
                break;
            case "By Course":
                controller = (ByCourseFilterController) getController(selectedFilter);
                break;
            case "By GPA":
                controller = (ByGPAFilterController) getController(selectedFilter);
                break;
            case "By Property":
                controller = (ByPropertyFilterController) getController(selectedFilter);
                break;
            case "By Student ID":
                controller = (ByStudentIdFilterController) getController(selectedFilter);
                break;
        }
        controller.setDataCallback(nodes -> {
            String labelText = createLabelTextForFilter(nodes, selectedFilter);
            createFilterItem(labelText);
        });
    }

    private String createLabelTextForFilter(Node[] nodes, String selectedFilter) {
        String labelText = "";
        if (nodes.length == 1) {
            Node node = nodes[0];
            if (node instanceof Slider slider) labelText = "Filter "+ selectedFilter + ": " + slider.getValue();
            else if (node instanceof TextField textField) labelText = getTextFieldFilterText(textField, selectedFilter);
        }
        if (nodes.length == 2) {
            if (nodes[0] instanceof ComboBox comboBox) {
                RadioButton radioButton = (RadioButton) nodes[1];
                labelText = "Filter " + selectedFilter + ": " + radioButton.getText() + " [" + comboBox.getValue() + "]";
            } else if (nodes[0] instanceof Slider slider1) {
                Slider slider2 = (Slider) nodes[1];
                labelText = "Filter " + selectedFilter + "s: " + String.format("%.2f", slider1.getValue()) + " - " + String.format("%.2f", slider2.getValue());
            } else if (nodes[0] instanceof TextField min) {
                TextField max = (TextField) nodes[1];
                labelText = "Filter " + selectedFilter + "s: " + min.getText() + " - " + max.getText();
            }
        }
        if(nodes.length == 3) {
            if (nodes[0] instanceof TextField min) {
                TextField max = (TextField) nodes[1];
                RadioButton radioButton = (RadioButton) nodes[2];
                labelText = "Filter " + selectedFilter + ": " + radioButton.getText() + " [" + min.getText() + " - " + max.getText() + "]";
            }
        }
        return labelText;
    }

    private String getTextFieldFilterText(TextField textField, String selectedFilter) {
        String text = textField.getText().trim();
        if (text.matches("(\\d+\\s*,\\s*)*\\d+")) {
            return "Filter By Specific " + selectedFilter.substring(3) +"s: " + text;
        } else {
            return "Invalid Input: Please enter numbers separated by commas.";
        }
    }

    private void createFilterItem(String filterText) {
        checkForFirstFilter();
        HBox filterItem = new HBox(2);
        filterItem.setStyle("-fx-alignment: center-left;");  // Center the content of HBox vertically and horizontally

        Text filterLabel = new Text(filterText);
        filterLabel.setStyle("-fx-font-size: 12px;");  // Make the text size a bit bigger

        Button removeButton = createRemoveButton(filterItem);
        removeButton.setMinSize(30, 30);  // Set minimum size for button
        removeButton.setStyle("-fx-font-size: 12px; -fx-text-fill: red; -fx-background-color: transparent;");  // Make the "✖" symbol larger and red

        filterItem.getChildren().addAll(filterLabel, removeButton);
        filtersContainer.getChildren().add(filterItem);
    }

    private Button createRemoveButton(HBox filterItem) {
        Button removeButton = new Button("✖");
        removeButton.setOnAction(event -> {
            filtersContainer.getChildren().remove(filterItem);
            if(filtersContainer.getChildren().isEmpty()) {
                filtersScrollPane.setVisible(false);

                VBox parent = (VBox) container.getChildren().getFirst(); // Assuming first child is the parent VBox
                int currentIndex = parent.getChildren().indexOf(addFilter);
                parent.getChildren().remove(addFilter);
                parent.getChildren().add(currentIndex - 1, addFilter);
            }
        });
        return removeButton;
    }


    private void checkForFirstFilter() {
        if (filtersContainer.getChildren().isEmpty()) {
            filtersScrollPane.setVisible(true);
            VBox parent = (VBox) container.getChildren().getFirst(); // Assuming first child is the parent VBox
            int currentIndex = parent.getChildren().indexOf(addFilter);
            parent.getChildren().remove(addFilter);
            parent.getChildren().add(currentIndex + 1, addFilter);
        }
    }

    private void openSmallWindow(String scene) {
        try {
            Stage stage = new Stage();
            stage.setTitle("Filter Options");
            stage.setScene(getScene(scene));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
// END OF HANDLING FILTER STUFF

    public void createDropDown() {
        ngSelect.setVisible(!ngSelect.isVisible());
    }

    @Override
    public void mouseEnterComponent(MouseEvent mouseEvent) {
        super.mouseEnterComponent(mouseEvent);
    }

    @Override
    public void mouseExitComponent(MouseEvent mouseEvent) {
        super.mouseExitComponent(mouseEvent);
    }

    @Override
    public void mouseClickedComponent(MouseEvent mouseEvent) throws IOException {
        super.mouseClickedComponent(mouseEvent);
    }
}

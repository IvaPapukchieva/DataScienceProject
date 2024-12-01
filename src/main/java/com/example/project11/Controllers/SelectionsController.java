package com.example.project11.Controllers;


import com.example.project11.Controllers.Charts.ChartSelectionController;
import com.example.project11.Controllers.FilterControllers.*;
import com.example.project11.FilterProcessing.Filter;
import com.example.project11.FilterProcessing.FilterData;
import com.example.project11.ProjectInfo.GradeDistributionCalculator;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private String selectedSubType;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        filters = new ArrayList<>();

        if (ngSelect != null) {
            ngSelect.getItems().addAll("Weighted Randomization", "Replace With Average", "Remove NGs");
        } else {
            System.out.println("ngSelect is null! Check FXML and Controller association.");
        }

        if (addFilter != null) {
            addFilter.getItems().addAll("By Grade", "By Course", "By GPA", "By Property", "By Student ID");
        }

        ngButton.setSelected(true);
        // Center the submit button
        centerButtonInAnchorPane();
    }

    private void centerButtonInAnchorPane() {
        AnchorPane anchorPane = (AnchorPane) submit.getParent();
        AnchorPane.setLeftAnchor(submit,  (anchorPane.getPrefWidth() - submit.getWidth()) / 2);
        AnchorPane.setTopAnchor(submit, (anchorPane.getPrefHeight() - submit.getHeight()) / 2);
    }

    public void submitButton() throws FileNotFoundException {
        System.out.println(filters.toString());
        if(dataSets.getSelectedToggle() == null) return;
        RadioButton radioButton = (RadioButton) dataSets.getSelectedToggle();
        FilterData filterData = null;
        if(radioButton.getText().equals("Graduate Grades")) {filterData = new FilterData(filters, "graduatingGradesLoader");}
        else if(radioButton.getText().equals("Undergraduate Grades") && !ngButton.isSelected()) {filterData = new FilterData(filters, "currentGradeLoader");}
        else if(radioButton.getText().equals("Undergraduate Grades") && ngSelect.getSelectionModel().getSelectedItem().equals("Weighted Randomization")) {filterData = new FilterData(filters, "weightedBootstrappingLoader");}
        else if(radioButton.getText().equals("Undergraduate Grades") && ngSelect.getSelectionModel().getSelectedItem().equals("Replace With Average")) {filterData = new FilterData(filters, "currentGradeLoaderNG");}
        else if(radioButton.getText().equals("Undergraduate Grades") && ngSelect.getSelectionModel().getSelectedItem().equals("Remove NGs")) {filterData = new FilterData(filters, "currentGradeLoaderRemoveNG");}

        double [][]filteredData = filterData.applyFilters();

//        System.out.println(Arrays.deepToString(filteredData));
//
//        System.out.println("Reached end of method");
ChartSelectionController data=new ChartSelectionController();
data.setFilteredData(filteredData);
        Stage stage1 = (Stage) submit.getParent().getScene().getWindow();
        stage1.setScene(getScene("Chart Window"));

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
        Controller controller = switch (selectedFilter) {
            case "By Grade" -> (ByGradesFilterController) getController(selectedFilter);
            case "By Course" -> (ByCourseFilterController) getController(selectedFilter);
            case "By GPA" -> (ByGPAFilterController) getController(selectedFilter);
            case "By Property" -> (ByPropertyFilterController) getController(selectedFilter);
            case "By Student ID" -> (ByStudentIdFilterController) getController(selectedFilter);
            default -> null;
        };
        controller.setDataCallback(nodes -> {
            String labelText = createLabelTextForFilter(nodes, selectedFilter);
            createFilterItem(labelText);
            extractData(nodes, selectedFilter, selectedSubType);
        });
    }

    private void extractData(Node[] nodes, String type, String subType) {
        List<Object> list = new ArrayList<>();

        for(Node node : nodes) {
            if (node instanceof Slider slider) {
                list.add(slider.getValue());
            } else if (node instanceof TextField textField) {
                int[] extractedInts = extractIntegersFromString(textField.getText());
                list.add(extractedInts);
            } else if (node instanceof ComboBox comboBox) {
                list.add(comboBox.getValue());
            } else if (node instanceof RadioButton radioButton) {
                list.add(radioButton.getText());
            }
        }
        filters.add(new Filter(type,subType,list));
    }

    private int[] extractIntegersFromString(String string) {
        String[] parts = string.split(",");

        int[] numbers = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i].trim());
        }

        return numbers;
    }



    private String createLabelTextForFilter(Node[] nodes, String selectedFilter) {
        String labelText = "";
        if (nodes.length == 1) {
            Node node = nodes[0];
            if (node instanceof Slider slider)  {
                labelText = "Filter "+ selectedFilter + ": " + slider.getValue();
                selectedSubType = "Number";
            }

            else if (node instanceof TextField textField) {
                labelText = getTextFieldFilterText(textField, selectedFilter);
                selectedSubType = "Multiple";
            }
        }
        if (nodes.length == 2) {
            if (nodes[0] instanceof ComboBox comboBox) {
                RadioButton radioButton = (RadioButton) nodes[1];
                labelText = "Filter " + selectedFilter + ": " + radioButton.getText() + " [" + comboBox.getValue() + "]";
                selectedSubType = "Categorical";

            } else if (nodes[0] instanceof Slider slider1) {
                Slider slider2 = (Slider) nodes[1];
                labelText = "Filter " + selectedFilter + "s: " + String.format("%.2f", slider1.getValue()) + " - " + String.format("%.2f", slider2.getValue());
                selectedSubType = "Range";
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
                selectedSubType = "Numerical";
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
            filters.remove(filtersContainer.getChildren().indexOf(filterItem));
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

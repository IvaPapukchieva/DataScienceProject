package com.example.project11.Controllers;


import com.example.project11.Controllers.Charts.ChartSelectionController;
import com.example.project11.Controllers.Charts.PieChartController;
import com.example.project11.Controllers.FilterControllers.*;
import com.example.project11.Controllers.TreeVisualization.TreeLoadingScreenController;
import com.example.project11.Controllers.TreeVisualization.TreeVisualizationController;
import com.example.project11.FilterProcessing.Filter;
import com.example.project11.FilterProcessing.FilterData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

public class SelectionsController extends Controller implements Initializable {

    @FXML private Button submit;
    @FXML private Button treeButton;
    @FXML private Label treeToolLabel;
    @FXML private ComboBox<String> ngSelect;
    @FXML private ToggleGroup dataSets;
    @FXML private Label dataSetLabel;
    @FXML private AnchorPane container;
    @FXML private ComboBox<String> addFilter;
    @FXML private VBox filtersContainer;
    @FXML private ScrollPane filtersScrollPane;
    @FXML private ChoiceBox<String> Category1Selector, Category2Selector, Category3Selector, Category4Selector, Category5Selector, CourseSelector;
    private ChoiceBox<String>[] categorySelectors;
    private ArrayList<Filter> filters;
    private String selectedSubType;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Category1Selector.getItems().addAll("full", "medium", "high", "low", "nothing");
        for (int i = -42; i <= 147; i++) {
            Category2Selector.getItems().add(i + "");

        }
        Category3Selector.getItems().addAll("1 tau", "2 tau", "3 tau");
        Category4Selector.getItems().addAll("A", "B", "C", "D", "E", "F");
        Category5Selector.getItems().addAll("1.0 Hz ", "5.0 Hz ", "0.5 Hz ", "0.1 Hz ");

        for (int i = 1; i <= 33; i++) {
            CourseSelector.getItems().add(i + "");

        }

        categorySelectors = new ChoiceBox[]{Category1Selector, Category2Selector, Category3Selector, Category4Selector, Category5Selector, CourseSelector};
        for(ChoiceBox<String> categorySelector : categorySelectors) {
            categorySelector.getSelectionModel().selectFirst();
        }


        filters = new ArrayList<>();

        if (ngSelect != null) {
            ngSelect.getItems().addAll("Weighted Randomization", "Replace With Average", "Remove NGs", "Data with NG values");
        } else {
            System.out.println("ngSelect is null! Check FXML and Controller association.");
        }

        if (addFilter != null) {
            addFilter.getItems().addAll("By Grade", "By Course", "By GPA", "By Property","By Student NG","By Course GPA","By Course NG");
        }

        // Center the submit button
        centerButtonInAnchorPane();
    }

    private void centerButtonInAnchorPane() {
        AnchorPane anchorPane = (AnchorPane) treeButton.getParent();
        AnchorPane.setLeftAnchor(treeButton,  (anchorPane.getPrefWidth() - submit.getWidth()) / 2);
        AnchorPane.setTopAnchor(treeButton, (anchorPane.getPrefHeight() - submit.getHeight()) / 2);
    }

    public void submitButton() throws FileNotFoundException {
//        System.out.println(filters.toString());
        if(dataSets.getSelectedToggle() == null) {
            dataSetLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        RadioButton radioButton = (RadioButton) dataSets.getSelectedToggle();
        FilterData filterData = null;
        if(radioButton.getText().equals("Graduate Grades")) {filterData = new FilterData(filters, "graduatingGradesLoader");}
        else if(radioButton.getText().equals("Undergraduate Grades") && ngSelect.getSelectionModel().getSelectedItem().equals("Weighted Randomization")) {filterData = new FilterData(filters, "weightedBootstrappingLoader");}
        else if(radioButton.getText().equals("Undergraduate Grades") && ngSelect.getSelectionModel().getSelectedItem().equals("Replace With Average")) {filterData = new FilterData(filters, "currentGradeLoaderNG");}
        else if(radioButton.getText().equals("Undergraduate Grades") && ngSelect.getSelectionModel().getSelectedItem().equals("Remove NGs")) {filterData = new FilterData(filters, "currentGradeLoaderRemoveNG");}
        else if(radioButton.getText().equals("Undergraduate Grades") && ngSelect.getSelectionModel().getSelectedItem().equals("Data with NG values")) {filterData = new FilterData(filters, "currentGradeLoader");}

        Map<String,Double>filteredData = filterData.applyFilters();


    System.out.println(filteredData);

        System.out.println("Reached end of method");
ChartSelectionController data=new ChartSelectionController();
data.setFilteredData(filteredData);
        Stage stage1 = (Stage) submit.getParent().getScene().getWindow();
        stage1.setScene(getScene("Chart Window"));

    }
//    hey

    public void handleDataSetsToggle() {
        dataSetLabel.setStyle("-fx-text-fill: white;");

        Toggle selectedToggle = dataSets.getSelectedToggle();
        if (selectedToggle != null) {
            RadioButton radioButton = (RadioButton) selectedToggle;
            boolean isUndergraduateGrades = radioButton.getText().equals("Undergraduate Grades");
            ngSelect.setVisible(isUndergraduateGrades);
            if (!isUndergraduateGrades) {
                ngSelect.getSelectionModel().clearSelection();
                ngSelect.setValue(null);
            } else {
                ngSelect.getSelectionModel().selectFirst();
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
            case "By Student NG"->(ByStudentNGFilterController) getController(selectedFilter);
            case "By Course GPA"->(ByCourseGPAFilterController) getController(selectedFilter);
            case "By Course NG"->(ByCourseNGFilterController) getController(selectedFilter);



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
                parent.getChildren().remove(addFilter);
                parent.getChildren().add(5, addFilter);

                parent.getChildren().remove(submit);
                parent.getChildren().add(6, submit);

            }
        });
        return removeButton;
    }


    private void checkForFirstFilter() {
        if (filtersContainer.getChildren().isEmpty()) {
            VBox parent = (VBox) container.getChildren().getFirst(); // Assuming first child is the parent VBox



            parent.getChildren().remove(addFilter);
            parent.getChildren().add( 6, addFilter);

            parent.getChildren().remove(filtersScrollPane);
            parent.getChildren().add(6,filtersScrollPane);

            filtersScrollPane.setVisible(true);
            parent.getChildren().remove(submit);
            parent.getChildren().add(7,submit);
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

    public void loadTreeTool(ActionEvent actionEvent) throws IOException {
        for(ChoiceBox<String> categorySelector : categorySelectors) {
            if(categorySelector.getSelectionModel().getSelectedItem() == null) {
                return;
            }
        }

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        TreeLoadingScreenController controller = (TreeLoadingScreenController) controllers.get("Tree Loading Screen");
        super.changeScene(actionEvent, "Tree Loading Screen");
        controller.temp(stage, categorySelectors);
    }

}

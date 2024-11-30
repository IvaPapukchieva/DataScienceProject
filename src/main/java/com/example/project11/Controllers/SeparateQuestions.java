package com.example.project11.Controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import javafx.event.ActionEvent;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class SeparateQuestions extends Controller implements Initializable {
    // Track selected phases (if needed)
    public ArrayList<String> sequence = new ArrayList<>();

    {
        sequence.add(" ");
        sequence.add(" ");
        sequence.add(" ");
        sequence.add(" ");
    }

    public double currentsliderValue;

    @FXML
    private Slider mySlider; // Connect this to your Slider in FXML
    @FXML
    private Button button;

    @FXML
    private ToggleButton graduatingGradesButton;

    @FXML
    private ToggleButton currentGradesButton;

    @FXML
    private ToggleButton bootstrappedGradesButton;

    private ToggleGroup toggleGroup;


    @FXML
    private VBox childComboBox; // VBox for dynamically added ComboBoxes
    @FXML
    private MenuItem step1MenuItem;
    @FXML
    private MenuItem step2MenuItem;
    @FXML
    private MenuItem step3MenuItem;
    @FXML
    private MenuItem step4MenuItem;
    @FXML
    private Pagination pagination;
    @FXML

    private final ArrayList<VBox> pages = new ArrayList<>(); // List to hold page content
    @FXML

    private  Button createButton ;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        toggleGroup = new ToggleGroup();
        graduatingGradesButton.setToggleGroup(toggleGroup);
        currentGradesButton.setToggleGroup(toggleGroup);
        bootstrappedGradesButton.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            resetButtonStyles();
            if (newValue != null) {
                ToggleButton selectedButton = (ToggleButton) newValue;
                selectedButton.setStyle("-fx-background-color: #555555; -fx-text-fill: white;");
            }
        });

        // Set default styles
        resetButtonStyles();


        addNewPage();
        pagination.setPageCount(pages.size());
        pagination.setPageFactory(this::createPageContent) ;




    }
    private boolean isFilled(ArrayList<String> arrayList){
        for( int i = 0  ; i<=4; i++){
            if(arrayList.get(i).equals(" ")|| Objects.equals(arrayList.get(3), " ")){
                return false ;
            }
            else{
                return true ;
            }
        }
        return true ;
    }
    private void addNewPage() {
        // Create a new VBox for the page
        VBox pageBox = new VBox(10); // 10px spacing
        pageBox.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Create a "Create Graph" button
        Button createButton = new Button("Create Graph");
        createButton.setVisible(false); // Initially hidden

        // Dynamically check if the sequence array is filled
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (isFilled(sequence)) {
                createButton.setVisible(true); // Show the button when the array is filled
            } else {
                createButton.setVisible(false); // Hide the button otherwise
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Button action to add a new page
        createButton.setOnAction(event -> {
            if( sequence.get(0).equals("STEP 1")){
                switch (sequence.get(2)){
                    case "Easiest Classes":
                        break;

                    case "Hardest Classes":
                        break;

                    case "Similar Classes":
                        break;

                    case "CumLaude":
                        break;


                    case "Best And Worst Student":
                        break;
                }

            }
            if( sequence.get(0).equals("STEP 2")){
                switch (sequence.get(1)){
                    case "Which Students Are Graduating Soon ":
                         break ;

                    case "Graduating Soon ":
                        break;

                    case "Predicting Passing Percentages":
                        break;

                    case "What Year Come for Which class ":
                        break;
                }

            }
            if( sequence.get(0).equals("STEP 3")){

            }

            if( sequence.get(0).equals("STEP 4")){

            }
            // add your chart here




            sequence.clear();
            for (int i = 0; i < 4; i++) {
                sequence.add(" "); // Reinitialize the sequence array
            }
            resetButtonStyles();

            // Add a new page and navigate to it
            addNewPage(); // Add the new page
            pagination.setPageCount(pages.size()); // Update the total page count
            pagination.setCurrentPageIndex(pages.size() - 1); // Go to the new page
        });

        // Add the button to the page
        pageBox.getChildren().add(createButton);

        // Store the page in the list
        pages.add(pageBox);
    }

    // Method to add a new page with a graph or button


   // Page factory to display content for the current page
    private VBox createPageContent(int pageIndex) {
        if (pageIndex >= 0 && pageIndex < pages.size()) {
            return pages.get(pageIndex); // Return the corresponding page content
        }
        return new VBox(); // Return empty content if index is invalid
    }


    @FXML
    private void handleMenuSelection(ActionEvent event) {

        if (!sequence.isEmpty()) {

            MenuItem selectedMenuItem = (MenuItem) event.getSource();
            String subsection = selectedMenuItem.getText();
            String property = getParentMenuText(selectedMenuItem);
            String selectedFunction = "Property: " + property + ", Subsection: " + subsection;
            System.out.println("Selected Function: " + selectedFunction);
            sequence.set(0, (property));
            sequence.set(1, (selectedFunction));
        } else {
            MenuItem selectedMenuItem = (MenuItem) event.getSource();
            String subsection = selectedMenuItem.getText();
            String property = getParentMenuText(selectedMenuItem);
            String selectedFunction = "Property: " + property + ", Subsection: " + subsection;
            System.out.println("Selected Function: " + selectedFunction);
            sequence.add(0, (property));
            sequence.add(1, (selectedFunction));


        }
        System.out.println(sequence);

    }

    @FXML
    private void getSliderValue(ActionEvent event) {

        double sliderValue = mySlider.getValue();
        if (sequence.size() < 3) {
            sequence.add(String.valueOf(sliderValue));
        } else {
            sequence.set(2, String.valueOf(sliderValue));
        }
        System.out.println(sequence);
    }


    private void resetButtonStyles() {
        graduatingGradesButton.setStyle("-fx-background-color: #cccccc; -fx-text-fill: black;");
        currentGradesButton.setStyle("-fx-background-color: #cccccc; -fx-text-fill: black;");
        bootstrappedGradesButton.setStyle("-fx-background-color: #cccccc; -fx-text-fill: black;");
    }

    @FXML
    private void handleToggleButtonAction(ActionEvent event) {
        // Additional logic if needed when a button is pressed
        ToggleButton pressedButton = (ToggleButton) event.getSource();
        if (sequence.get(3) == null) {
            sequence.add(3, pressedButton.getText());
            System.out.println(sequence);


        } else {
            sequence.set(3, pressedButton.getText());
            System.out.println(sequence);


        }
    }



    private String getParentMenuText(MenuItem menuItem) {
        if (menuItem.getParentMenu() != null) {
            return menuItem.getParentMenu().getText(); // Direct parent is the Menu (Property)
        }
        return "Unknown";
    }


}

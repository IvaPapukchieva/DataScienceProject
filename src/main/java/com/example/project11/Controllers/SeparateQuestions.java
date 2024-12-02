package com.example.project11.Controllers;

import com.example.project11.Controllers.Charts.*;
import com.example.project11.ProjectInfo.STEP1.CumLaude;
import com.example.project11.ProjectInfo.STEP1.EasiestClasses;
import com.example.project11.ProjectInfo.STEP1.AverageGrades;
import com.example.project11.ProjectInfo.STEP2.GraduatePassingGradePredictionThreshold;
import com.example.project11.ProjectInfo.STEP2.GraduatingSoon;
import com.example.project11.ProjectInfo.STEP2.PredictPercentage;
import com.example.project11.ProjectInfo.STEP2.WhatYearWhatclass;
import com.example.project11.ProjectInfo.loaders.CurrentGradeLoader;
import com.example.project11.ProjectInfo.loaders.CurrentGradeLoaderNG;
import com.example.project11.ProjectInfo.loaders.GraduatingGradesLoader;
import com.example.project11.ProjectInfo.loaders.WeightedBootstrapping;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class SeparateQuestions extends Controller implements Initializable {
    private static final SequenceManager sequenceManager = new SequenceManager();

    private CurrentGradeLoaderNG currentGradeLoaderNG;
    private CurrentGradeLoader currentGradeLoader;
    private GraduatingGradesLoader graduatingGradesLoader;
    private WeightedBootstrapping weightedBootstrapping;

    @FXML private Slider mySlider;
    @FXML private Button button, createButton;
    @FXML private ToggleButton graduatingGradesButton, currentGradesButton, bootstrappedGradesButton;
    @FXML private VBox childComboBox;
    @FXML private Pagination pagination;
    @FXML private MenuItem step1MenuItem, step2MenuItem, step3MenuItem, step4MenuItem;
    @FXML private ToggleGroup toggleGroup;
    private final List<VBox> pages = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            initializeLoaders();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        addNewPage();
        setUpToggleGroup();
        setUpPagination();
    }
    private void initializeLoaders() throws FileNotFoundException {
        currentGradeLoaderNG = loadLoader(new CurrentGradeLoaderNG());
        currentGradeLoader = loadLoader(new CurrentGradeLoader());
        graduatingGradesLoader = loadLoader(new GraduatingGradesLoader());
        weightedBootstrapping = loadLoader(new WeightedBootstrapping());
    }

    private static <T> T loadLoader(T loader) {
        try {
            return loader;
        } catch (Exception e) {
            throw new RuntimeException("Error initializing loader", e);
        }
    }
    private void setUpToggleGroup() {
        toggleGroup.selectedToggleProperty().addListener((newValue) -> {
            resetButtonStyles();
            if(newValue instanceof ToggleButton) {
                ToggleButton toggleButton = (ToggleButton) newValue;
                toggleButton.setStyle("-fx-background-color: #555555; -fx-text-fill: white;");
            }
        });
    }

    private void setUpPagination() {
        pagination.setPageFactory(this::getPageContent);
    }

    private VBox getPageContent(int pageIndex) {
        return pageIndex >= 0 && pageIndex < pages.size() ? pages.get(pageIndex) : new VBox();
    }

    private void addNewPage() {
        VBox pageBox = new VBox(10);
        pageBox.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Button createButton = new Button("Create Graph");
        createButton.setVisible(false);
        setupDynamicButtonVisibility(createButton);
        createButton.setOnAction(actionEvent -> {
            try {
                handle(actionEvent);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        });


        pageBox.getChildren().add(createButton);
        pages.add(pageBox);
        pagination.setPageCount(pages.size());

    }

    private void setupDynamicButtonVisibility(Button button) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            button.setVisible(sequenceManager.isFilled());
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    private void handleMenuSelection(ActionEvent event) {
        if (event.getSource() instanceof MenuItem) {
            MenuItem selectedMenuItem = (MenuItem) event.getSource();
            String parentMenu = getParentMenuText(selectedMenuItem);
            sequenceManager.update(parentMenu, selectedMenuItem.getText());
            System.out.println(sequenceManager.getSequence());
        }
    }

    @FXML
    private void getSliderValue() {
        double sliderValue = mySlider.getValue();
        sequenceManager.updateSliderValue(sliderValue);
        System.out.println(sequenceManager.getSequence());
    }

    @FXML
    private void handleToggleButtonAction(ActionEvent event) {
        if (event.getSource() instanceof ToggleButton) {
            ToggleButton pressedButton = (ToggleButton) event.getSource();
            sequenceManager.updateGradeType(pressedButton.getText());
            System.out.println(sequenceManager.getSequence());
        }
    }

    private void resetButtonStyles() {
        graduatingGradesButton.setStyle("-fx-background-color: #cccccc; -fx-text-fill: black;");
        currentGradesButton.setStyle("-fx-background-color: #cccccc; -fx-text-fill: black;");
        bootstrappedGradesButton.setStyle("-fx-background-color: #cccccc; -fx-text-fill: black;");
    }

    private String getParentMenuText(MenuItem menuItem) {
        return menuItem.getParentMenu() != null ? menuItem.getParentMenu().getText() : "Unknown";
    }

    private void handle(ActionEvent event) throws FileNotFoundException {
        List<String> sequence = sequenceManager.getSequence();

        // Validate that STEP 1 is the first sequence item
        if (!"STEP 1".equalsIgnoreCase(sequence.get(0).trim()) &&
                !"STEP 2".equalsIgnoreCase(sequence.get(0).trim())&
                        !"STEP 3".equalsIgnoreCase(sequence.get(0).trim())&
                        !"STEP 4".equalsIgnoreCase(sequence.get(0).trim())) {
            return;
        }

        // Perform relevant action based on the selected function
        switch (sequence.get(1).trim()) {
            case "Average Grades":
                handleAverageGrades(sequence);
                break;
            case "CumLaude":
                handleCumLaude(sequence);
                break;
            case "What Year Come for Which class" :
                handleWhatYearWhatClass(sequence);
                break;
            case "Predicting Passing Percentages":
                handlePredictionPercentage(sequence);
                break;
            case "Graduating Soon":
                handleGraduatingSoon(sequence);
                break;
             case "Which Students Are Graduating Soon":
                 handleGraduatePassingGradesPredictionThreshold(sequence);
                 break;
            default:
                System.out.println("Unknown function: " + sequence.get(1));
        }
    }


    private void handleCumLaude(List<String> sequence) {
        try {
            CumLaude cumLaude;
            String gradesType = sequence.get(3).trim();

            if ("Graduating Grades".equalsIgnoreCase(gradesType)) {
                cumLaude = new CumLaude(graduatingGradesLoader.readAllStudents());
            } else if ("Current Grades".equalsIgnoreCase(gradesType)) {
                cumLaude = new CumLaude(currentGradeLoader.readAllStudents());
            } else if ("Bootstrapped Grades".equalsIgnoreCase(gradesType)) {
                cumLaude = new CumLaude(weightedBootstrapping.readAllStudents());
            } else {
                System.out.println("Invalid grades type for CumLaude.");
                return;
            }

            Map<String, Integer> honorsMap = cumLaude.getHonorsMap();
            BarChartController barChartController = (BarChartController) controllers.get("Bar Chart");
            barChartController.setChartData(honorsMap);

            displayChartOnCurrentPage(" Chart");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void handlePredictionPercentage(List<String> sequence) {
        try {
            PredictPercentage predictPercentage;
            String gradesType = sequence.get(3).trim();

            if ("Graduating Grades".equalsIgnoreCase(gradesType)) {
                predictPercentage = new PredictPercentage(graduatingGradesLoader.readAllStudents());
            } else if ("Current Grades".equalsIgnoreCase(gradesType)) {
                predictPercentage = new PredictPercentage(currentGradeLoader.readAllStudents());
            } else if ("Bootstrapped Grades".equalsIgnoreCase(gradesType)) {
                predictPercentage = new PredictPercentage(weightedBootstrapping.readAllStudents());
            } else {
                System.out.println("Invalid grades type for CumLaude.");
                return;
            }

            Map<String, Integer> honorsMap = predictPercentage.getCoursePassingMap();
            BarChartController barChartController = (BarChartController) controllers.get("Bar Chart");
            barChartController.setChartData(honorsMap);

            displayChartOnCurrentPage("Bar Chart");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleWhatYearWhatClass(List<String> sequence) {
        try {
            WhatYearWhatclass  whatyearclass;
            String gradesType = sequence.get(3).trim();
            if (!"Current Grades".equalsIgnoreCase(sequence.get(3).trim()) ) {
                System.out.println("Easiest Classes only supports Current Grades.");
                return;
            }
            else if ("Current Grades".equalsIgnoreCase(gradesType)) {
                whatyearclass = new WhatYearWhatclass(currentGradeLoader.readAllStudents());
           } else {
                System.out.println("Invalid grades type for CumLaude.");
                return;
           }

            Map<String, Integer> courseYearMap = whatyearclass.getCourseYearMap();
            BarChartController barChartController = (BarChartController) controllers.get("Bar Chart");
            barChartController.setChartData(courseYearMap);

            displayChartOnCurrentPage("Bar Chart");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleAverageGrades(List<String> sequence) {
        try {
            AverageGrades averageGrades;
            String gradesType = sequence.get(3).trim();

            if ("Graduating Grades".equalsIgnoreCase(gradesType)) {
                averageGrades = new AverageGrades(graduatingGradesLoader.readAllStudents());
            } else if ("Current Grades".equalsIgnoreCase(gradesType)) {
                averageGrades = new AverageGrades(currentGradeLoader.readAllStudents());
            } else if ("Bootstrapped Grades".equalsIgnoreCase(gradesType)) {
                averageGrades = new AverageGrades(weightedBootstrapping.readAllStudents());
            } else {
                System.out.println("Invalid grades type for Average Grades.");
                return;
            }

            Map<String, Integer> averageGradesMap = averageGrades.getAverageGradesMap();
            AreaChartController areaChartController = (AreaChartController) controllers.get("Area Chart");
            areaChartController.setChartData(averageGradesMap);

            displayChartOnCurrentPage("Area Chart");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void handleGraduatingSoon(List<String> sequence) {
        try {

            GraduatingSoon  graduatingSoon;
            String gradesType = sequence.get(3).trim();
            if (!"Current Grades".equalsIgnoreCase(sequence.get(3).trim()) ) {
                System.out.println("Easiest Classes only supports Current Grades.");
                return;
            }
            else if ("Current Grades".equalsIgnoreCase(gradesType)) {
                graduatingSoon = new GraduatingSoon(currentGradeLoader.readAllStudents());
            } else {
                System.out.println("Invalid grades type for CumLaude.");
                return;
            }

            Map<String, Integer> averageGradesMap = graduatingSoon.getStudentGroups();
            BarChartController barChartController = (BarChartController) controllers.get("Bar Chart");
            barChartController.setChartData(averageGradesMap);

            displayChartOnCurrentPage("Bar Chart");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void handleGraduatePassingGradesPredictionThreshold(List<String> sequence) {
        try {

            GraduatePassingGradePredictionThreshold GrPaPredictionThreshold;

            String gradesType = sequence.get(3).trim();
            if (!"Bootstrapped Grades".equalsIgnoreCase(sequence.get(3).trim())&&!"Graduating Grades".equalsIgnoreCase(sequence.get(3).trim()) ) {
                System.out.println("Easiest Classes only supports Current Grades.");
                return;
            }
            else if ("Graduating Grades".equalsIgnoreCase(gradesType)) {
                GrPaPredictionThreshold = new GraduatePassingGradePredictionThreshold(currentGradeLoader.readAllStudents());
            }
            else if ("Bootstrapped Grades".equalsIgnoreCase(gradesType)) {
                GrPaPredictionThreshold = new GraduatePassingGradePredictionThreshold(currentGradeLoader.readAllStudents());
            } else {
                System.out.println("Invalid grades type for CumLaude.");
                return;
            }

            Map<String, Integer> averageGradesMap = GrPaPredictionThreshold.getCoursePassingMap();
            ScatterChartController ScatterChartController = (ScatterChartController) controllers.get("Scatter Chart");
            ScatterChartController.setChartData(averageGradesMap);

            displayChartOnCurrentPage("Scatter Chart");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayChartOnCurrentPage(String chartSceneKey) {
        // Retrieve the preloaded scene from the cache
        Scene chartScene = Controller.getScene(chartSceneKey);

        // Check if the scene exists in the cache
        if (chartScene == null) {
            System.out.println("Scene not found in cache for key: " + chartSceneKey);
            return; // Exit if the scene is not found
        }

        // Get the root of the scene
        Parent newRoot = chartScene.getRoot();

        // Get the current page index
        int currentPageIndex = pagination.getCurrentPageIndex();

        // Ensure the pages list has enough pages
        while (pages.size() <= currentPageIndex) {
            addNewPage();
        }

        // Get or initialize the current page
        VBox currentPage = pages.get(currentPageIndex);
        if (currentPage == null) {
            currentPage = new VBox(10);
            pages.set(currentPageIndex, currentPage);
        }

        // Check if the chart is already displayed to avoid duplicates
        if (!currentPage.getChildren().contains(newRoot)) {
            currentPage.getChildren().add(newRoot);
        }

        // Ensure the "Create Graph" button remains at the bottom
        if (currentPage.getChildren().contains(createButton)) {
            currentPage.getChildren().remove(createButton);
        }
        //currentPage.getChildren().add(createButton);

        // Ensure there's always a new page available for future additions
        if (currentPageIndex == pages.size() - 1) {
            addNewPage();
        }

        // Reset the sequence manager after adding the chart
        sequenceManager.reset();
    }


    private VBox cloneVBox(VBox original) {
        if (original == null) {
            // If the original VBox is null, return a new empty VBox
            return new VBox(10);
        }

        VBox clonedVBox = new VBox(10);
        if (original.getStyle() != null) {
            clonedVBox.setStyle(original.getStyle());
        }
        if (original.getAlignment() != null) {
            clonedVBox.setAlignment(original.getAlignment());
        }

        for (Node child : original.getChildren()) {
            if (child instanceof Button) {
                Button originalButton = (Button) child;
                Button clonedButton = new Button(originalButton.getText() != null ? originalButton.getText() : "");
                if (originalButton.getOnAction() != null) {
                    clonedButton.setOnAction(originalButton.getOnAction());
                }
                clonedVBox.getChildren().add(clonedButton);
            } else if (child instanceof Label) {
                Label originalLabel = (Label) child;
                Label clonedLabel = new Label(originalLabel.getText() != null ? originalLabel.getText() : "");
                clonedVBox.getChildren().add(clonedLabel);
            } else if (child != null) {
                // Add other non-null node types directly
                clonedVBox.getChildren().add(child);
            }
        }

        return clonedVBox;
    }





}




// Helper Class to Manage Sequence
class SequenceManager {
    private final List<String> sequence = new ArrayList<>(List.of(" ", " ", " ", " "));

    public void update(String step, String function) {
        sequence.set(0, step);
        sequence.set(1, function);
    }

    public void updateSliderValue(double value) {
        sequence.set(2, String.valueOf(value));
    }

    public void updateGradeType(String gradeType) {
        sequence.set(3, gradeType);
    }

    public boolean isFilled() {
        return sequence.stream().noneMatch(String::isBlank);
    }

    public void reset() {
        for (int i = 0; i < sequence.size(); i++) {
            sequence.set(i, " ");
        }
    }

    public String getStep() {
        return sequence.get(0);
    }

    public List<String> getSequence() {
        return new ArrayList<>(sequence);
    }
}

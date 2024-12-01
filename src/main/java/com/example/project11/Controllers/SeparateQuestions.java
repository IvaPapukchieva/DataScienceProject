package com.example.project11.Controllers;

import com.example.project11.Controllers.Charts.AreaChartController;
import com.example.project11.Controllers.Charts.BarChartController;
import com.example.project11.Main;
import com.example.project11.ProjectInfo.STEP1.CumLaude;
import com.example.project11.ProjectInfo.STEP1.EasiestClasses;
import com.example.project11.ProjectInfo.STEP1.AverageGrades;
import com.example.project11.ProjectInfo.loaders.CurrentGradeLoader;
import com.example.project11.ProjectInfo.loaders.CurrentGradeLoaderNG;
import com.example.project11.ProjectInfo.loaders.GraduatingGradesLoader;
import com.example.project11.ProjectInfo.loaders.WeightedBootstrapping;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class SeparateQuestions extends Controller implements Initializable {
    private static final SequenceManager sequenceManager = new SequenceManager();

    private static final CurrentGradeLoaderNG currentGradeLoaderNG;
    private static final CurrentGradeLoader currentGradeLoader;
    private static final GraduatingGradesLoader graduatingGradesLoader;
    private static final WeightedBootstrapping weightedBootstrapping;

    static {
        try {
            currentGradeLoaderNG = loadLoader(new CurrentGradeLoaderNG());
            currentGradeLoader = loadLoader(new CurrentGradeLoader());
            graduatingGradesLoader = loadLoader(new GraduatingGradesLoader());
            weightedBootstrapping = loadLoader(new WeightedBootstrapping());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML private Slider mySlider;
    @FXML private Button button, createButton;
    @FXML private ToggleButton graduatingGradesButton, currentGradesButton, bootstrappedGradesButton;
    @FXML private VBox childComboBox;
    @FXML private Pagination pagination;
    @FXML private MenuItem step1MenuItem, step2MenuItem, step3MenuItem, step4MenuItem;

    private ToggleGroup toggleGroup = new ToggleGroup();
    private final List<VBox> pages = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupToggleGroup();
        setupPagination();
        addNewPage();
    }

    private static <T> T loadLoader(T loader) {
        try {
            return loader;
        } catch (Exception e) {
            throw new RuntimeException("Error initializing loader", e);
        }
    }

    private void setupToggleGroup() {
        graduatingGradesButton.setToggleGroup(toggleGroup);
        currentGradesButton.setToggleGroup(toggleGroup);
        bootstrappedGradesButton.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            resetButtonStyles();
            if (newValue instanceof ToggleButton) {
                ToggleButton selectedButton = (ToggleButton) newValue;
                selectedButton.setStyle("-fx-background-color: #555555; -fx-text-fill: white;");
            }
        });
    }

    private void setupPagination() {
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
        createButton.setOnAction(this::handle);

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

    private void handle(ActionEvent event) {
        List<String> sequence = sequenceManager.getSequence();

        // Validate that STEP 1 is the first sequence item
        if (!"STEP 1".equalsIgnoreCase(sequence.get(0).trim())) {
            return;
        }

        // Perform relevant action based on the selected function
        switch (sequence.get(1).trim()) {
            case "Average Grades":
                handleAverageGrades(sequence);
                break;
            case "Easiest Classes":
                handleEasiestClasses(sequence);
                break;
            case "CumLaude":
                handleCumLaude(sequence);
                break;
            default:
                System.out.println("Unknown function: " + sequence.get(1));
        }
    }

    private void handleEasiestClasses(List<String> sequence) {
        try {
            if (!"Current Grades".equalsIgnoreCase(sequence.get(3).trim())) {
                System.out.println("Easiest Classes only supports Current Grades.");
                return;
            }

            EasiestClasses easiestClasses = new EasiestClasses(currentGradeLoader.readAllStudents());
            Map<String, Integer> easiestClassesMap = easiestClasses.getEasiestClassesMap();



            displayChartOnCurrentPage("Easiest Hardest");


            addNewPage();
            sequenceManager.reset();

        } catch (IOException e) {
            e.printStackTrace();
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

    private void displayChartOnCurrentPage(String chartSceneKey) throws IOException {
        Scene chartScene = scenes.get(chartSceneKey);
        Parent newRoot = chartScene.getRoot();

        VBox currentPage = pages.get(pagination.getCurrentPageIndex());
        currentPage.setAlignment(Pos.CENTER);
        currentPage.getChildren().add(newRoot);
        currentPage.getChildren().remove(createButton);


        addNewPage();
        sequenceManager.reset();
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

package com.example.project11.Controllers;

import com.example.project11.Controllers.Charts.AreaChartController;
import com.example.project11.Controllers.Charts.BarChartController;
import com.example.project11.Main;
import com.example.project11.ProjectInfo.STEP1.CumLaude;
import com.example.project11.ProjectInfo.STEP1.EasiestClasses;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import com.example.project11.ProjectInfo.STEP1.AverageGrades;
import com.example.project11.ProjectInfo.loaders.CurrentGradeLoader;
import com.example.project11.ProjectInfo.loaders.CurrentGradeLoaderNG;
import com.example.project11.ProjectInfo.loaders.GraduatingGradesLoader;
import com.example.project11.ProjectInfo.loaders.WeightedBootstrapping;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx. scene. text. Text;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SeparateQuestions extends Controller implements Initializable {
    // Track selected phases (if needed)
    private static CurrentGradeLoaderNG currentGradeLoaderNG;

    static {
        try {
            currentGradeLoaderNG = new CurrentGradeLoaderNG();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static CurrentGradeLoader currentGradeLoader;

    static {
        try {
            currentGradeLoader = new CurrentGradeLoader();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static GraduatingGradesLoader graduatingGradesLoader;

    static {
        try {
            graduatingGradesLoader = new GraduatingGradesLoader();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //     CurrentGradeLoaderRemoveNG currentGradeLoaderRemoveNG=new CurrentGradeLoaderRemoveNG();
    private static WeightedBootstrapping weightedBootstrapping;

    static {
        try {
            weightedBootstrapping = new WeightedBootstrapping();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private static ArrayList<String> sequence = new ArrayList<>();

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
    private Label LABLE1;
    @FXML
    private Label LABLE2;
    @FXML
    private Label LABLE3;

    @FXML
    private final ArrayList<VBox> pages = new ArrayList<>(); // List to hold page content
    @FXML

    private Button createButton;
    private int pagenum = 0;

    public SeparateQuestions() throws FileNotFoundException {
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize ToggleGroup and set up toggle buttons
        toggleGroup = new ToggleGroup();


        if (graduatingGradesButton != null) {
            graduatingGradesButton.setToggleGroup(toggleGroup);
        } else {
            System.out.println("graduatingGradesButton is null. Check your FXML file.");
        }

        if (currentGradesButton != null) {
            currentGradesButton.setToggleGroup(toggleGroup);
        } else {
            System.out.println("currentGradesButton is null. Check your FXML file.");
        }

        if (bootstrappedGradesButton != null) {
            bootstrappedGradesButton.setToggleGroup(toggleGroup);
        } else {
            System.out.println("bootstrappedGradesButton is null. Check your FXML file.");
        }

        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            resetButtonStyles();
            if (newValue != null) {
                ToggleButton selectedButton = (ToggleButton) newValue;
                selectedButton.setStyle("-fx-background-color: #555555; -fx-text-fill: white;");
            }
        });

        // Set default styles
        //resetButtonStyles();

        // Add initial page
        addNewPage();
        pagination.setPageCount(pages.size());
        pagination.setPageFactory(this::createPageContent);
    }

    private boolean isFilled(ArrayList<String> arrayList) {
        for (int i = 0; i <= 4; i++) {
            if (arrayList.get(i).equals(" ") || Objects.equals(arrayList.get(3), " ")) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    private void addNewPage() {
        // Create a new VBox for the page
        VBox pageBox = new VBox(10); // 10px spacing
        pageBox.setStyle("-fx-padding: 20; -fx-alignment: center;");
        pages.add(pageBox);
        pagination.setPageCount(pages.size());
        // Create a "Create Graph" button
        Button createButton = new Button("Create Graph");
        createButton.setVisible(false); // Initially hidden

        // Dynamically check if the sequence array is filled
        setupDynamicButtonVisibility(createButton);

        // Button action to add a new page
        createButton.setOnAction(this::handle);

        // Add the button to the page
        pageBox.getChildren().add(createButton);

        // Store the page in the list
        //pages.add(pageBox);
    }

    // Method to add a new page with a graph or button

    private void resetSequence() {
        sequence.clear();
        for (int i = 0; i < 4; i++) {
            sequence.add(" ");
        }

    }

    private void setupDynamicButtonVisibility(Button createButton) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (isFilled(sequence)) {
                createButton.setVisible(true); // Show the button when the array is filled
            } else {
                createButton.setVisible(false); // Hide the button otherwise
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    // Page factory to display content for the current page
    private VBox createPageContent(int pageIndex) {
        if (pageIndex >= 0 && pageIndex < pages.size()) {
            return pages.get(pageIndex); // Return the corresponding page content
        }
        return new VBox(); // Return empty content if index is invalid
    }

    private void addNewEmptyPage() {
        addNewPage();

    }

    @FXML
    private void handleMenuSelection(ActionEvent event) {

        if (!sequence.isEmpty()) {

            MenuItem selectedMenuItem = (MenuItem) event.getSource();
            String subsection = selectedMenuItem.getText();
            String property = getParentMenuText(selectedMenuItem);
            String selectedFunction = subsection;
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

    public VBox loadFXMLIntoVBox(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();
        VBox vbox = new VBox();
        vbox.getChildren().add(root);
        return vbox;
    }


    private String getParentMenuText(MenuItem menuItem) {
        if (menuItem.getParentMenu() != null) {
            return menuItem.getParentMenu().getText(); // Direct parent is the Menu (Property)
        }
        return "Unknown";
    }


    private void handle(ActionEvent event) {
        if (sequence.get(0).trim().equalsIgnoreCase("STEP 1")) {
            System.out.println("STEP 1 ");
            if (sequence.get(1).trim().equalsIgnoreCase("Average Grades")) {

                if (sequence.get(3).trim().equalsIgnoreCase("Graduating Grades")) {
                    try {
                        AverageGrades obj = new AverageGrades(graduatingGradesLoader.readAllStudents());
                        Map<String, Integer> AverageList = obj.getAverageGradesMap();
                        System.out.println("Graduating Grades");

                        AreaChartController areaChartController = (AreaChartController) controllers.get("Area Chart");
                        areaChartController.setChartData(AverageList);
                        Scene chartScene = scenes.get("Area Chart");
                        Parent newroot = chartScene.getRoot();

                        // Add the chart to the current page
                        VBox currentPage = pages.get(pagination.getCurrentPageIndex());
                        currentPage.setAlignment(Pos.CENTER); // Center alignment
                        currentPage.getChildren().add(newroot);

                        // Create a new empty page
                        addNewEmptyPage();
                        resetSequence();


                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (sequence.get(3).trim().equalsIgnoreCase("Current Grades")) {
                    try {
                        AverageGrades obj = new AverageGrades(currentGradeLoader.readAllStudents());
                        Map<String, Integer> AverageList = obj.getAverageGradesMap();
                        System.out.println("Current Grades");

                        AreaChartController areaChartController = (AreaChartController) controllers.get("Area Chart");
                        areaChartController.setChartData(AverageList);
                        Scene chartScene = scenes.get("Area Chart");
                        Parent newroot = chartScene.getRoot();

                        // Add the chart to the current page
                        VBox currentPage = pages.get(pagination.getCurrentPageIndex());
                        currentPage.setAlignment(Pos.CENTER); // Center alignment
                        currentPage.getChildren().add(newroot);
                        // Create a new empty page
                        addNewEmptyPage();
                        resetSequence();


                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
                if (sequence.get(3).trim().equalsIgnoreCase("Bootstrapped Grades")) {
                    try {
                        AverageGrades obj = new AverageGrades(weightedBootstrapping.readAllStudents());
                        Map<String, Integer> AverageList = obj.getAverageGradesMap();
                        System.out.println("Graduating Grades");

                        AreaChartController areaChartController = (AreaChartController) controllers.get("Area Chart");
                        areaChartController.setChartData(AverageList);
                        Scene chartScene = scenes.get("Area Chart");
                        Parent newroot = chartScene.getRoot();

                        // Add the chart to the current page
                        VBox currentPage = pages.get(pagination.getCurrentPageIndex());
                        currentPage.setAlignment(Pos.CENTER); // Center alignment
                        currentPage.getChildren().add(newroot);

                        // Create a new empty page
                        addNewEmptyPage();
                        resetSequence();


                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            if (sequence.get(1).trim().equalsIgnoreCase("Easiest Classes")) {

                System.out.println("Step 1 easiest Classes ");
                if (sequence.get(3).trim().equalsIgnoreCase("Current Grades")) {
                    try {
                        EasiestClasses obj = new EasiestClasses(currentGradeLoader.readAllStudents());
                        Map<String, Integer> AverageList = obj.getEasiestClassesMap();

                        FXMLLoader separateLoader = new FXMLLoader(Main.class.getResource("EasiestHardest.fxml"));
                        Node contentNode = separateLoader.load(); // Load the content as a Node

                        // Add the contentNode to the current page in the pagination
                        VBox currentPage = pages.get(pagination.getCurrentPageIndex());
                        currentPage.setAlignment(Pos.CENTER); // Optional: Center the content
                        currentPage.getChildren().add(contentNode);

                        // Add a new empty page after loading the current content
                        addNewEmptyPage();
                        resetSequence(); // Reset the sequence if needed

                        // Add the chart to the current page


                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            if (sequence.get(1).trim().equalsIgnoreCase("CumLaude")) {
                System.out.println("cumlaude");
                if (sequence.get(3).trim().equalsIgnoreCase("Graduating Grades")) {
                    try {
                        CumLaude obj = new CumLaude(graduatingGradesLoader.readAllStudents());
                        Map<String, Integer> CumLaudeList = obj.getHonorsMap();
                        System.out.println("Graduating Grades");

                        BarChartController BarChartController = (BarChartController) controllers.get("Bar Chart");
                        BarChartController.setChartData(CumLaudeList);
                        Scene chartScene = scenes.get("Bar Chart");
                        Parent newroot = chartScene.getRoot();

                        // Add the chart to the current page
                        VBox currentPage = pages.get(pagination.getCurrentPageIndex());
                        currentPage.setAlignment(Pos.CENTER); // Center alignment
                        currentPage.getChildren().add(newroot);

                        // Create a new empty page
                        addNewEmptyPage();
                        resetSequence();


                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (sequence.get(3).trim().equalsIgnoreCase("Current Grades")) {
                    try {
                        CumLaude obj = new CumLaude(currentGradeLoader.readAllStudents());
                        Map<String, Integer> CumLaudeList = obj.getHonorsMap();

                        BarChartController BarChartController = (BarChartController) controllers.get("Bar Chart");
                        BarChartController.setChartData(CumLaudeList);
                        Scene chartScene = scenes.get("Bar Chart");
                        Parent newroot = chartScene.getRoot();

                        // Add the chart to the current page
                        VBox currentPage = pages.get(pagination.getCurrentPageIndex());
                        currentPage.setAlignment(Pos.CENTER); // Center alignment
                        currentPage.getChildren().add(newroot);

                        // Create a new empty page
                        addNewEmptyPage();
                        resetSequence();


                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (sequence.get(3).trim().equalsIgnoreCase("Bootstrapped Grades")) {
                    try {
                        CumLaude obj = new CumLaude(weightedBootstrapping.readAllStudents());
                        Map<String, Integer> CumLaudeList = obj.getHonorsMap();
                        System.out.println("Graduating Grades");

                        BarChartController BarChartController = (BarChartController) controllers.get("Bar Chart");
                        BarChartController.setChartData(CumLaudeList);
                        Scene chartScene = scenes.get("Bar Chart");
                        Parent newroot = chartScene.getRoot();

                        // Add the chart to the current page
                        VBox currentPage = pages.get(pagination.getCurrentPageIndex());
                        currentPage.setAlignment(Pos.CENTER); // Center alignment
                        currentPage.getChildren().add(newroot);

                        // Create a new empty page
                        addNewEmptyPage();
                        resetSequence();


                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        if (sequence.get(0).trim().equalsIgnoreCase("STEP 2")) {
            if (sequence.get(1).trim().equalsIgnoreCase("Which Students Are Graduating Soon ")){

            }

            }

                if (sequence.get(0).trim().equalsIgnoreCase("STEP 3")) {
                    System.out.println("STEP 3");


                }

                if (sequence.get(0).trim().equalsIgnoreCase("STEP 4")) {
                    System.out.println("STEP 4");


                }
                // add your chart here
                resetSequence();
                resetButtonStyles();

                // Add a new page and navigate to it
                pagination.setPageCount(pages.size()); // Update the total page count
                pagination.setCurrentPageIndex(pages.size() - 2);
                pagenum = pages.size() - 2;// Go to the new page


            }
        }
    }



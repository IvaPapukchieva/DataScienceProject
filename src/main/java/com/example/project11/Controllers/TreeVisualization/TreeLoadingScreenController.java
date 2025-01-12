package com.example.project11.Controllers.TreeVisualization;

import com.example.project11.Controllers.Controller;
import com.example.project11.GradePredictionTree.OfficialTreeAlgorithm.TreeAlgorithmUtil;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TreeLoadingScreenController extends Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void temp(Stage stage, ChoiceBox<String>[] selectedStudent) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> Platform.runLater(() -> {
            System.out.println("Delay of 5 seconds complete. Ready for scene switch.");
            try {
                changeScene(stage, selectedStudent); // Move the scene change here
            } catch (IOException e) {
                e.printStackTrace();
            }
        }), 5, TimeUnit.SECONDS);
        scheduler.shutdown();
    }



    public void changeScene(Stage stage, ChoiceBox<String>[] selectedStudent) throws IOException {
        TreeVisualizationController treeController = (TreeVisualizationController) controllers.get("Tree Visualizer");
        List<String> labels = Arrays.asList("A", "-34", "-39", "1 tau", "1.0 Hz", "low", "25", "30", "76", "52", "-29", "-8", "112",
                "0.1 Hz", "2 tau", "37", "10", "nothing", "18", "7", "90", "41", "40", "10", "115",
                "108", "medium", "nothing", "2 tau", "D", "-1", "58", "125", "69", "-16", "E", "-39",
                "-24", "-26", "-32", "133", "5.0 Hz", "-40", "full", "D", "3 tau", "medium", "0.5 Hz",
                "-37", "0.1 Hz", "D", "high", "-33", "B", "F", "-30", "0.5 Hz", "130", "test", "test", "test", "test", "test");

        List<String> student = new ArrayList<>();

        for(ChoiceBox<String> categorySelector : selectedStudent) {
            student.add(categorySelector.getValue());
        }
        System.out.println(student);

        treeController.passProperties(1,labels, 6, student, 7);
        treeController.passProperties(2,labels, 1, student, 2);
        treeController.passProperties(3,labels, 2, student, 4);
        treeController.passProperties(4,labels, 3, student, 5);
        treeController.passProperties(5,labels, 5, student, 4);
        treeController.openTree("1");

        stage.setScene(scenes.get("Tree Visualizer"));
        stage.centerOnScreen();
    }

}

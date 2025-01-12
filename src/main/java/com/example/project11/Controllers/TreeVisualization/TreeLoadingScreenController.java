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
        List<String> labels = List.of("One", "Two", "Three", "Four", "Five","One", "Two", "Three", "Four", "Five","One", "Two", "Three", "Four", "Five","One", "Two", "Three", "Four", "Five","One", "Two", "Three", "Four", "Five","One", "Two", "Three", "Four", "Five","One", "Two", "Three", "Four", "Five","One", "Two", "Three", "Four", "Five");
        List<String> student = new ArrayList<>();

        for(ChoiceBox<String> categorySelector : selectedStudent) {
            student.add(categorySelector.getValue());
        }
        System.out.println(student);

        treeController.passProperties(1,labels, 5, student, 7);
        treeController.passProperties(2,labels, 1, student, 2);
        treeController.passProperties(3,labels, 2, student, 4);
        treeController.passProperties(4,labels, 3, student, 5);
        treeController.passProperties(5,labels, 5, student, 4);

        stage.setScene(scenes.get("Tree Visualizer"));
        stage.centerOnScreen();
    }

}

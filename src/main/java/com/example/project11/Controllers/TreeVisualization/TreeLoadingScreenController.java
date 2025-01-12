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
        List<String> labels = Arrays.asList("medium","105", "B", "3 tau", "-5", "-37", "7.0leaf", "5.75leaf", "70",
                "7.666666666666667leaf", "6.0leaf", "1.0 Hz", "6.333333333333333leaf",
                "-30", "8.5leaf", "-18", "6.5leaf", "7.6leaf", "3 tau", "A", "44", "18",
                "6.833333333333333leaf", "6.0leaf", "7.5leaf", "E", "17", "6.875leaf",
                "6.333333333333333leaf", "-4", "6.625leaf", "6.290322580645161leaf",
                "5.0 Hz", "2", "5.0leaf", "A", "5.0leaf", "6.083333333333333leaf",
                "0.1 Hz", "-25", "5.25leaf", "6.294117647058823leaf", "C", "6.090909090909091leaf",
                "6.606060606060606leaf", "115", "1.0 Hz", "6.0leaf", "A", "8.666666666666666leaf",
                "5.0 Hz", "6.666666666666667leaf", "110", "8.0leaf", "8.0leaf", "127", "B",
                "5.333333333333333leaf", "A", "1.0 Hz", "6.0leaf", "7.0leaf", "6.25leaf",
                "3 tau", "130", "6.0leaf", "7.333333333333333leaf", "8.0leaf", "133", "C",
                "-33", "-38", "6.5leaf", "low", "6.0leaf", "8.25leaf", "0.5 Hz", "-12",
                "9.0leaf", "nothing", "7.375leaf", "6.5leaf", "8", "2 tau", "5.666666666666667leaf",
                "6.230769230769231leaf", "13", "8.0leaf", "6.476923076923077leaf", "14", "3",
                "3 tau", "low", "6.0leaf", "6.622222222222222leaf", "1", "6.803571428571429leaf",
                "7.4leaf", "1.0 Hz", "1 tau", "5.0leaf", "5.833333333333333leaf", "E",
                "7.5leaf", "6.523809523809524leaf", "66", "59", "high", "6.589743589743589leaf",
                "6.885350318471337leaf", "nothing", "7.7272727272727275leaf", "6.95leaf",
                "full", "122", "6.3076923076923075leaf", "7.444444444444445leaf", "123",
                "6.81025641025641leaf", "6.333333333333333leaf", "1 tau", "4.5leaf",
                "high", "4.0leaf", "nothing", "7.5leaf", "6.666666666666667leaf");

        List<String> student = new ArrayList<>();

        for(ChoiceBox<String> categorySelector : selectedStudent) {
            student.add(categorySelector.getValue());
        }
        System.out.println(student);

        treeController.passProperties(1,labels, 7, student, 7);
        treeController.passProperties(2,labels, 1, student, 2);
        treeController.passProperties(3,labels, 2, student, 4);
        treeController.passProperties(4,labels, 3, student, 5);
        treeController.passProperties(5,labels, 5, student, 4);
        treeController.openTree("1");

        stage.setScene(scenes.get("Tree Visualizer"));
        stage.centerOnScreen();
    }

}

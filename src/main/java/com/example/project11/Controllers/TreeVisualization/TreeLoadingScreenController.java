package com.example.project11.Controllers.TreeVisualization;

import com.example.project11.Controllers.Controller;
import com.example.project11.GradePredictionTree.OfficialTreeAlgorithm.Predictions;
import com.example.project11.GradePredictionTree.OfficialTreeAlgorithm.TreeAlgorithmUtil;
import com.example.project11.ProjectInfo.loaders.WeightedBootstrapping;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TreeLoadingScreenController extends Controller implements Initializable {
    private double[][] weightedBootstrappingArray;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public TreeLoadingScreenController() throws FileNotFoundException {
        WeightedBootstrapping weightedBootstrapping = new WeightedBootstrapping();
        weightedBootstrappingArray = weightedBootstrapping.readAllStudents();
    }

    public void temp(Stage stage, ChoiceBox<String>[] selectedStudent, int course) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> Platform.runLater(() -> {
            System.out.println("Delay of 5 seconds complete. Ready for scene switch.");
            try {
                changeScene(stage, selectedStudent, course); // Move the scene change here
            } catch (IOException e) {
                e.printStackTrace();
            }
        }), 5, TimeUnit.SECONDS);
        scheduler.shutdown();
    }



    public void changeScene(Stage stage, ChoiceBox<String>[] selectedStudent, int course) throws IOException {
        TreeVisualizationController treeController = (TreeVisualizationController) controllers.get("Tree Visualizer");
        List<String> labels = Arrays.asList(
                "1.0 Hz", "-42", "4.0leaf", "133", "C", "3 tau", "-10", "6.5leaf", "40", "36", "nothing", "5.0leaf", "5.75leaf",
                "4.0leaf", "medium", "9.0leaf", "5.75leaf", "17", "low", "8.5leaf", "3", "-1", "high", "7.0leaf", "6.25leaf",
                "5.0leaf", "7.666666666666667leaf", "1 tau", "medium", "8.0leaf", "69", "41", "6.0leaf", "7.0leaf", "5.5leaf",
                "full", "6.0leaf", "high", "6.0leaf", "nothing", "6.0leaf", "6.0leaf", "72", "57", "3 tau", "B", "low", "7.0leaf",
                "8.25leaf", "high", "17", "6.25leaf", "5.0leaf", "-10", "-20", "E", "8.0leaf", "6.5leaf", "8.333333333333334leaf",
                "-9", "5.0leaf", "-4", "8.0leaf", "6.6923076923076925leaf", "53", "-23", "D", "7.0leaf", "low", "5.5leaf", "A",
                "7.0leaf", "6.222222222222222leaf", "-21", "9.0leaf", "-16", "7.333333333333333leaf", "-1", "6.0leaf",
                "6.63265306122449leaf", "medium", "5.5leaf", "6.0leaf", "58", "8.5leaf", "60", "6.0leaf", "B", "6.5leaf", "A",
                "7.0leaf", "full", "7.0leaf", "66", "7.6leaf", "8.333333333333334leaf", "A", "83", "6.25leaf", "medium", "6.0leaf",
                "88", "7.0leaf", "low", "7.5leaf", "8.0leaf", "78", "5.5leaf", "80", "7.5leaf", "109", "high", "7.0leaf", "85",
                "84", "6.2leaf", "8.0leaf", "88", "5.0leaf", "6.15leaf", "medium", "3 tau", "8.0leaf", "6.75leaf", "3 tau", "126",
                "6.166666666666667leaf", "7.0leaf", "low", "7.333333333333333leaf", "6.6leaf", "4.0leaf", "130", "125", "-36",
                "-42", "7.5leaf", "1 tau", "0.1 Hz", "5.25leaf", "6.25leaf", "-40", "5.5leaf", "-39", "0.5 Hz",
                "6.333333333333333leaf", "7.25leaf", "medium", "7.0leaf", "0.1 Hz", "7.0leaf", "6.0leaf", "-26", "2 tau", "A",
                "4.0leaf", "D", "7.5leaf", "high", "8.0leaf", "5.75leaf", "F", "6.0leaf", "low", "-34", "6.0leaf", "5.0 Hz",
                "6.5leaf", "7.25leaf", "A", "6.75leaf", "full", "-33", "8.0leaf", "6.75leaf", "3 tau", "-35", "6.0leaf",
                "7.444444444444445leaf", "-30", "8.666666666666666leaf", "8.0leaf", "-25", "1 tau", "4.666666666666667leaf",
                "7.0leaf", "-7", "-12", "B", "3 tau", "-21", "6.0leaf", "low", "6.0leaf", "4.5leaf", "-18", "6.0leaf", "1 tau",
                "6.75leaf", "8.5leaf", "medium", "F", "6.0leaf", "7.0leaf", "-13", "-14", "7.083333333333333leaf", "5.0leaf",
                "7.75leaf", "C", "high", "7.666666666666667leaf", "7.0leaf", "-10", "high", "6.5leaf", "low", "5.5leaf",
                "4.333333333333333leaf", "full", "7.0leaf", "5.0 Hz", "6.4leaf", "5.875leaf", "nothing", "1 tau", "48", "24",
                "0.1 Hz", "6.333333333333333leaf", "7.2leaf", "0.1 Hz", "4.5leaf", "5.666666666666667leaf", "73", "D", "5.5leaf",
                "7.714285714285714leaf", "113", "6.230769230769231leaf", "7.2leaf", "10", "2", "A", "6.0leaf", "7.2leaf",
                "6.0leaf", "26", "25", "7.5leaf", "8.666666666666666leaf", "105", "6.925925925925926leaf", "7.363636363636363leaf",
                "D", "41", "34", "2 tau", "6.2727272727272725leaf", "7.0625leaf", "7.75leaf", "122", "44", "5.666666666666667leaf",
                "6.333333333333333leaf", "7.5leaf", "high", "E", "-5", "4.0leaf", "7.409090909090909leaf", "F",
                "6.388888888888889leaf", "6.838235294117647leaf", "A", "45", "6.612903225806452leaf", "7.235294117647059leaf",
                "3", "7.038461538461538leaf", "6.576923076923077leaf", "C", "medium", "8.0leaf", "6.666666666666667leaf", "1 tau",
                "5.0leaf", "126", "7.333333333333333leaf", "2 tau", "medium", "6.0leaf", "5.0leaf", "low", "7.0leaf", "E", "7.0leaf",
                "medium", "6.0leaf", "nothing", "6.0leaf", "6.0leaf", "low", "C", "6.0leaf", "6.666666666666667leaf", "high",
                "6.5leaf", "full", "D", "6.0leaf", "2 tau", "7.333333333333333leaf", "8.0leaf", "0.1 Hz", "8.5leaf", "1 tau",
                "8.0leaf", "7.333333333333333leaf"
        );
        List<String> student = new ArrayList<>();

        for(ChoiceBox<String> categorySelector : selectedStudent) {
            student.add(categorySelector.getValue());
        }

        // need to transform the stend into a 2D array for it to function with the TreeAlgorithm
        String [][] students = new String[1][5] ;
        for( int i = 0 ; i<5 ; i++){
            students[0][i] = student.get(i);
        }

        Predictions predictions = new Predictions(students,course, weightedBootstrappingArray);
        predictions.getCreateForest();
        predictions.getGradeList() ;
        System.out.println("Grade for student "+predictions.getGradeForStudent());

        Map<Integer, List<String>> TreeMap = predictions.getTrees(students);
        double[] depthlist = predictions.getDepthList();
        for( int i = 1; i<TreeMap.size() ; i++){
            treeController.passProperties(i,TreeMap.get(i-1), (int)(depthlist[i-1]), student, predictions.getGradeList()[i]);
        }

        treeController.openTree("1");

        stage.setScene(scenes.get("Tree Visualizer"));
        stage.centerOnScreen();
    }

}

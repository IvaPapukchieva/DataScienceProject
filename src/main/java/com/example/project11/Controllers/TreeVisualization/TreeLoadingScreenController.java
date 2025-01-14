package com.example.project11.Controllers.TreeVisualization;

import com.example.project11.Controllers.Controller;
import com.example.project11.GradePredictionTree.OfficialTreeAlgorithm.Predictions;
import com.example.project11.GradePredictionTree.OfficialTreeAlgorithm.TreeAlgorithmUtil;
import com.example.project11.ProjectInfo.loaders.WeightedBootstrapping;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TreeLoadingScreenController extends Controller implements Initializable {
    private double[][] weightedBootstrappingArray;

    @FXML
    private MediaView mediaView;
    public void initialize(URL location, ResourceBundle resources) {
        String videoPath = "src/main/resources/images/Tree.mp4";
        File videoFile = new File(videoPath);
        Media media = new Media(videoFile.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setCycleCount(5);
        mediaPlayer.setAutoPlay(true);

    }
    public TreeLoadingScreenController() throws FileNotFoundException {
        WeightedBootstrapping weightedBootstrapping = new WeightedBootstrapping();
        weightedBootstrappingArray = weightedBootstrapping.readAllStudents();
    }

    public void temp(Stage stage, ChoiceBox<String>[] selectedStudent, int course) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                // Perform the heavy computations here
                changeScene(stage, selectedStudent, course);
                return null;
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                System.out.println("Task completed successfully!");
            }

            @Override
            protected void failed() {
                super.failed();
                System.err.println("Task failed: " + getException().getMessage());
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true); // Ensures the thread will close when the application exits
        thread.start();
    }



    public void changeScene(Stage stage, ChoiceBox<String>[] selectedStudent, int course) throws IOException {
        TreeVisualizationController treeController = (TreeVisualizationController) controllers.get("Tree Visualizer");
        List<String> student = new ArrayList<>();

        for(ChoiceBox<String> categorySelector : selectedStudent) {
            student.add(categorySelector.getValue());
        }

        // need to transform the stend into a 2D array for it to function with the TreeAlgorithm
        String [][] students = new String[1][5] ;
        for( int i = 0 ; i<5 ; i++){
            students[0][i] = student.get(i);
        }

        Predictions predictions = new Predictions(students,course-1, weightedBootstrappingArray);
        predictions.getCreateForest();
        Map<Integer, List<String>> TreeMap = predictions.getTrees(students);
        double[]gradeList = predictions.getGradeList() ;
        System.out.println("Grade for student "+Arrays.toString(gradeList));

        double[] depthlist = predictions.getDepthList();
        for( int i = 0; i<TreeMap.size() ; i++){
            treeController.passProperties(i+1,TreeMap.get(i), (int)(depthlist[i]+1), student, gradeList[i]);

        }

        treeController.openTree("1");

        Platform.runLater(() -> {
            // Update the UI, e.g., setting the scene
            stage.setScene(scenes.get("Tree Visualizer"));
            stage.centerOnScreen();
        });
    }

}

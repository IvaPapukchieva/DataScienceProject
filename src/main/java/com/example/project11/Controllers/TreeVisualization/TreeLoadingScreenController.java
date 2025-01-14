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
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
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
        initialize(null, null);


        // Prepare a task for the long-running process
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                try {
                    List<String> student = new ArrayList<>();
                    for (ChoiceBox<String> categorySelector : selectedStudent) {
                        student.add(categorySelector.getValue());
                    }

                    // Transform student data into a 2D array
                    String[][] students = new String[1][5];
                    for (int i = 0; i < 5; i++) {
                        students[0][i] = student.get(i);
                    }

                    Predictions predictions = new Predictions(students, course - 1, weightedBootstrappingArray);
                    predictions.getCreateForest();
                    Map<Integer, List<String>> TreeMap = predictions.getTrees(students);
                    double[] gradeList = predictions.getGradeList();
                    System.out.println("Grade for student " + Arrays.toString(gradeList));

                    double[] depthList = predictions.getDepthList();
                    for (int i = 0; i < TreeMap.size(); i++) {
                        treeController.passProperties(i + 1, TreeMap.get(i), (int) (depthList[i] + 1), student, gradeList[i]);
                    }

                    // Simulate opening the tree
                    treeController.openTree("1");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };


        task.setOnSucceeded(event -> {
            Platform.runLater(() -> {
                stage.setScene(scenes.get("Tree Visualizer"));
                stage.centerOnScreen();
            });
        });

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

}

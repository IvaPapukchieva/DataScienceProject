package com.example.project11.Controllers.TreeVisualization;

import com.example.project11.Controllers.Controller;
import com.example.project11.GradePredictionTree.OfficialTreeAlgorithm.TreeAlgorithmUtil;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TreeLoadingScreenController extends Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void temp(Stage stage) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> Platform.runLater(() -> {
            System.out.println("Delay of 5 seconds complete. Ready for scene switch.");
            try {
                changeScene(stage); // Move the scene change here
            } catch (IOException e) {
                e.printStackTrace();
            }
        }), 5, TimeUnit.SECONDS);
        scheduler.shutdown();
    }


    public void changeScene(Stage stage) throws IOException {
        TreeVisualizationController treeController = (TreeVisualizationController) controllers.get("Tree Visualizer");
        List<String> categoriesList = List.of(
                "1.0 Hz", "96", "-42", "low", "-40", "-41", "full", "medium", "-32", "A", "10", "38",
                "-16", "B", "-14", "A", "41", "65", "133", "109", "C", "high", "107", "2 tau", "106",
                "2 tau", "110", "high", "medium", "126", "117", "medium", "high", "-41", "D", "2 tau",
                "medium", "-42", "high", "121", "44", "21", "-8", "medium", "full", "22", "5.0 Hz",
                "medium", "59", "0.5 Hz", "A", "2 tau", "89", "B", "5.0 Hz", "131", "high", "124", "123",
                "5.0 Hz", "128", "126", "2 tau", "132", "full", "B", "full", "133", "medium"
        );
                List<String> student = List.of("1 tau", "A", "17");

        treeController.passProperties(2,categoriesList, 9, student, 5);
        treeController.passProperties(3,categoriesList, 5, student, 8);
        treeController.passProperties(4,categoriesList, 2, student, 2);
        treeController.passProperties(5,categoriesList, 1, student, 0);

        treeController.openTree("1");

        stage.setScene(scenes.get("Tree Visualizer"));
        stage.centerOnScreen();
    }

}

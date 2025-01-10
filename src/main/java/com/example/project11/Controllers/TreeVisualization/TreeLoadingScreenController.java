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
    public void temp(ActionEvent actionEvent) throws IOException {
        // Delay logic using a ScheduledExecutorService
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> Platform.runLater(() -> {
            System.out.println("Delay of 5 seconds complete. Ready for scene switch.");
            // Optional: Trigger scene change or enable a button for user interaction
        }), 5, TimeUnit.SECONDS);
        scheduler.shutdown();

        changeScene(actionEvent);
    }


    public void changeScene(ActionEvent actionEvent) throws IOException {
        TreeVisualizationController treeController = (TreeVisualizationController) controllers.get("Tree Visualizer");
        List<String> labels = List.of("One", "Two", "Three", "Four", "Five","One", "Two", "Three", "Four", "Five","One", "Two", "Three", "Four", "Five","Four", "Five","One", "Two", "Three", "Four", "Five","Four", "Five","One", "Two", "Three", "Four", "Five","Four", "Five","One", "Two", "Three", "Four", "Five","One", "Two", "Three", "Four", "Five","One", "Two", "Three", "Four", "Five","One", "Two", "Three", "Four", "Five","Four", "Five","One","One", "Two", "Three", "Four", "Five","One", "Two", "Three", "Four", "Five","One", "Two", "Three", "Four", "Five","Four", "Five","One","One", "Two", "Three", "Four", "Five","One", "Two", "Three", "Four", "Five","One", "Two", "Three", "Four", "Five","Four", "Five","One","One", "Two", "Three", "Four", "Five","One", "Two", "Three", "Four", "Five","One", "Two", "Three", "Four", "Five","Four", "Five","One");
        List<String> student=List.of("1 tau","A","17","1 tau","A","17");

        treeController.passProperties(labels,5,student,7);

        // get current stage
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        // set the new scene on the stage
        stage.setScene(scenes.get("Tree Visualizer"));
        stage.centerOnScreen();
    }
}

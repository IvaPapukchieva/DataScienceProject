package com.example.project11.Controllers;

import com.example.project11.DataCallback;
import com.example.project11.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public abstract class Controller {
    private Stage stage ;
    private static final HashMap<String, Scene> scenes = new HashMap<>();
    private static final HashMap<String, Object> controllers = new HashMap<>();
    private DataCallback dataCallback;


    public static void cacheScenes() {
        try {
            //cache "start" scene and Controller
            FXMLLoader startLoader = new FXMLLoader(Main.class.getResource("start.fxml"));
            scenes.put("start", new Scene(startLoader.load(), 320, 480));
            controllers.put("start", startLoader.getController());

            FXMLLoader separateLoader = new FXMLLoader(Main.class.getResource("separateQuestions.fxml"));
            scenes.put("separateQuestions", new Scene(separateLoader.load(), 720, 482));
            controllers.put("separateQuestions", separateLoader.getController());

            //cache "selections" scene and Controller
            FXMLLoader selectionsLoader = new FXMLLoader(Main.class.getResource("selections.fxml"));
            scenes.put("selections", new Scene(selectionsLoader.load(), 320, 480));
            controllers.put("selections", selectionsLoader.getController());

            //cache "gradesFilter" scene and Controller
            FXMLLoader gradesFilterLoader = new FXMLLoader(Main.class.getResource("Filters/ByGradesFilter.fxml"));
            scenes.put("By Grade", new Scene(gradesFilterLoader.load(), 380, 250));
            controllers.put("By Grade", gradesFilterLoader.getController());

            //cache "courseFilter" scene and Controller
            FXMLLoader courseFilterLoader = new FXMLLoader(Main.class.getResource("Filters/ByCourseFilter.fxml"));
            scenes.put("By Course", new Scene(courseFilterLoader.load(), 380, 250));
            controllers.put("By Course", courseFilterLoader.getController());

            //cache "courseFilter" scene and Controller
            FXMLLoader gpaFilterLoader = new FXMLLoader(Main.class.getResource("Filters/ByGPAFilter.fxml"));
            scenes.put("By GPA", new Scene(gpaFilterLoader.load(), 380, 250));
            controllers.put("By GPA", gpaFilterLoader.getController());

            //cache "propertyFilter" scene and Controller
            FXMLLoader propertyFilterLoader = new FXMLLoader(Main.class.getResource("Filters/ByPropertyFilter.fxml"));
            scenes.put("By Property", new Scene(propertyFilterLoader.load(), 380, 250));
            controllers.put("By Property", propertyFilterLoader.getController());

            //cache "propertyFilter" scene and Controller
            FXMLLoader studentIdFilterLoader = new FXMLLoader(Main.class.getResource("Filters/ByStudentIdFilter.fxml"));
            scenes.put("By Student ID", new Scene(studentIdFilterLoader.load(), 380, 250));
            controllers.put("By Student ID", studentIdFilterLoader.getController());

            FXMLLoader chartWindowFilterLoader = new FXMLLoader(Main.class.getResource("Chart/ChartSelection.fxml"));
            scenes.put("Chart Window", new Scene( chartWindowFilterLoader.load(), 380, 250));
            controllers.put("Chart Window",  chartWindowFilterLoader.getController());





        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Scene getScene(String sceneName) {
        return scenes.get(sceneName);
    }

    public static Object getController(String controllerName) {
        return controllers.get(controllerName);
    }

    public void mouseEnterComponent(MouseEvent mouseEvent) {
        Node trigger = (Node) mouseEvent.getSource();
        if(trigger instanceof ImageView imageView) {

            imageView.setEffect(new ColorAdjust(0.03, 0.1,0.35,0.0));
            imageView.setOpacity(0.8);

        }
    }
    public void mouseExitComponent(MouseEvent mouseEvent) {
        Node trigger = (Node) mouseEvent.getSource();
        if(trigger instanceof ImageView imageView) {

            imageView.setEffect(new ColorAdjust(0.0, 0.0,0.0,0.0));
            imageView.setOpacity(1);

        }
    }
    public void mouseClickedComponent(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() instanceof ImageView) {
            if (((ImageView) mouseEvent.getSource()).getId().equals("statisticsImage")) {
                changeScene(mouseEvent, "selections");
            }
            if (((ImageView) mouseEvent.getSource()).getId().equals("questionsImage")) {
                changeScene(mouseEvent, "separateQuestions");


            }
            if (((ImageView) mouseEvent.getSource()).getId().equals("backButtonImage")) {
                changeScene(mouseEvent, "start");

            }


        }
    }

    public void changeScene(MouseEvent mouseEvent, String sceneName) throws IOException {
        Scene scene = scenes.get(sceneName);
        if (scene == null) {
            throw new IllegalArgumentException("Scene not found: " + sceneName);
        }

        // get current stage
        Stage stage = (Stage) ((javafx.scene.Node) mouseEvent.getSource()).getScene().getWindow();
        // set the new scene on the stage
        stage.setScene(scene);
    }
    public void setDataCallback(DataCallback dataCallback) {
        this.dataCallback = dataCallback;
    }


    public DataCallback getDataCallback() {
        return this.dataCallback;
    }
}

package com.example.project11;

import com.example.project11.Controllers.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Load all the scenes into a cache
        Controller.cacheScenes();

        //Import starting scene
        Scene scene = Controller.getScene("start");


        //Set scene and show to user
        stage.setTitle("Project 1-1");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();



    }







}
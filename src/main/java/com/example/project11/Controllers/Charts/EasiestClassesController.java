package com.example.project11.Controllers.Charts;

import com.example.project11.Controllers.SeparateQuestions;
import com.example.project11.ProjectInfo.loaders.CurrentGradeLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.util.List;

public class EasiestClassesController extends SeparateQuestions {
    @FXML
    private Label LABEL1;
    @FXML
    private Label LABEL2;
    @FXML
    private Label LABEL3;

    public void initializeSpecific(List<String> keys) throws FileNotFoundException {
        // Ensure keys contain at least three entries
        if (keys.size() < 3) {
            System.out.println("Insufficient data for labels.");
            return;
        }

        String firstKey = keys.get(0);
        String secondKey = keys.get(1);
        String thirdKey = keys.get(2);

        CurrentGradeLoader currentGradeLoader = new CurrentGradeLoader();
        double[][] allStudents = currentGradeLoader.readAllStudents();

        // Dynamically update the labels
        LABEL1.setText(String.valueOf(allStudents[Integer.parseInt(firstKey)][0]));
        LABEL2.setText(String.valueOf(allStudents[Integer.parseInt(secondKey)][0]));
        LABEL3.setText(String.valueOf(allStudents[Integer.parseInt(thirdKey)][0]));

        // Print for debugging
        System.out.println("Easiest Classes Labels Updated:");
        System.out.println("LABEL1: " + allStudents[Integer.parseInt(firstKey)][0]);
        System.out.println("LABEL2: " + allStudents[Integer.parseInt(secondKey)][0]);
        System.out.println("LABEL3: " + allStudents[Integer.parseInt(thirdKey)][0]);
    }
}

package com.example.project11.Controllers;

import com.example.project11.Controllers.SeparateQuestions;
import com.example.project11.ProjectInfo.loaders.CurrentGradeLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.util.List;

public class EasiestClassesController extends SeparateQuestions {
    @FXML
    private Label LABEL1;
    @FXML private Label LABEL2;
    @FXML private Label LABEL3;
    private static CurrentGradeLoader currentGradeLoader ;


    public void initializeSpecific(List<String> keys) throws FileNotFoundException {
        String firstKey = keys.get(0);
        String secondKey = keys.get(1);
        String thirdKey = keys.get(2);
        currentGradeLoader = new CurrentGradeLoader();
        double[][]allStudents = currentGradeLoader.readAllStudents();
        LABEL1.setText(String.valueOf(allStudents[Integer.parseInt(firstKey)][0]));
        System.out.println(allStudents[Integer.parseInt(firstKey)][0]);
        LABEL2.setText(String.valueOf(allStudents[Integer.parseInt(secondKey)][0]));
        System.out.println(allStudents[Integer.parseInt(secondKey)][0]);
        LABEL3.setText(String.valueOf(allStudents[Integer.parseInt(thirdKey)][0]));
        System.out.println(allStudents[Integer.parseInt(thirdKey)][0]);

    }
}
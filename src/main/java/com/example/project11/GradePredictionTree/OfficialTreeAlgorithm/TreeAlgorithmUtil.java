package com.example.project11.GradePredictionTree.OfficialTreeAlgorithm;

import com.example.project11.ProjectInfo.loaders.Loader;
import com.example.project11.ProjectInfo.loaders.StudentInfoLoader;
import com.example.project11.ProjectInfo.loaders.WeightedBootstrapping;
import com.sun.source.tree.Tree;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class TreeAlgorithmUtil {

    public static int course ;
    public static ArrayList<Double> gradePredictionArray ;
    public static double[][] weightedBootstrappingArray ;

    public static void main(String[] args) throws FileNotFoundException {
        // Example data encoded numerically
        course = 0 ;
        String[][] student  = {{"full", "3", "1 tau", "B", "0.1 Hz "}};


        StudentInfoLoader studentInfoLoader = new StudentInfoLoader();
        String[][] studentinfoArray = studentInfoLoader.readInfoString();
        String[][] studentInfoFormatted = new String[studentinfoArray.length-1][5];

        WeightedBootstrapping weightedBootstrapping = new WeightedBootstrapping();
        weightedBootstrappingArray = weightedBootstrapping.readAllStudents();

        for (int i = 1 ; i<studentinfoArray.length ; i++){
            for ( int j = 0 ; j<5 ; j++){
                studentInfoFormatted[i-1][j] = studentinfoArray[i][j+1];
            }
        }
        Predictions predictions = new Predictions(student, course, weightedBootstrappingArray) ;
        predictions.getCreateForest();
        gradePredictionArray = new ArrayList<>() ;
        for(int i = 0 ; i<15 ; i++){
            for (int j = 0 ; j<studentInfoFormatted[0].length ; j++){
                student[0][j] = studentInfoFormatted[i][j];
            }
                predictions.getTrees(student);
                predictions.getGradeList();
                double prediction = predictions.getGradeForStudent() ;
                System.out.println("Prediction Grade  : "+ prediction);
                gradePredictionArray.add(prediction);
                System.out.println("Margin of Error : "+ getMarginOfError());


        }
        System.out.println("Margin of Error : "+ getMarginOfError());
        System.out.println("Grade Prediction Array : "+ gradePredictionArray);


    }

    public static double getMarginOfError(){
        double marginOfError = 0 ;  ;
        double sum = 0  ;

        for(int i= 1 ; i< gradePredictionArray.size() ; i++){
            sum+= (double)Math.abs(weightedBootstrappingArray[i][course] - gradePredictionArray.get(i-1));

        }
        marginOfError = sum/ gradePredictionArray.size();

        return marginOfError ;
    }






}

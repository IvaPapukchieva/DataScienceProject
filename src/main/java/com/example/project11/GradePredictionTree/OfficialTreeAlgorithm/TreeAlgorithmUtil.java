package com.example.project11.GradePredictionTree.OfficialTreeAlgorithm;

import com.example.project11.ProjectInfo.loaders.*;
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
        String[][] student  = {{"medium", "124", "2 tau", "A", "0.5 Hz "}};


        StudentInfoLoader studentInfoLoader = new StudentInfoLoader();
        String[][] studentinfoArray = studentInfoLoader.readInfoString();
        String[][] studentInfoFormatted = new String[studentinfoArray.length-1][5];


//        PeersonCorrelation peersonCorrelation = new PeersonCorrelation();
//        weightedBootstrappingArray = peersonCorrelation.getFilledStudentArray();
        WeightedBootstrapping weightedBootstrapping = new WeightedBootstrapping();
        weightedBootstrappingArray = weightedBootstrapping.readAllStudents();

    for(int i = 0 ; i<33 ;i++){
        Predictions predictions = new Predictions(student, course, weightedBootstrappingArray) ;
        predictions.getCreateForest();
        gradePredictionArray = new ArrayList<>() ;
        predictions.getTrees(student);
        predictions.getGradeList();
        double prediction = predictions.getGradeForStudent() ;

        System.out.println("FInal Prediction Grade  : "+ prediction+ " for course "+ course);
        course++ ;


    }




    }

    public static double getMarginOfError(){
        double marginOfError = 0 ;  ;
        double sum = 0  ;

        for(int i= 1 ; i< gradePredictionArray.size() ; i++){
            sum+= Math.abs((double)weightedBootstrappingArray[i][course] - (double)gradePredictionArray.get(i));

        }

        marginOfError = sum/ (double)gradePredictionArray.size();

        return marginOfError ;
    }






}

package com.example.project11.GradePredictionTree.TreeAlgorithm;


import com.example.project11.ProjectInfo.loaders.StudentInfoLoader;
import com.example.project11.ProjectInfo.loaders.WeightedBootstrapping;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        WeightedBootstrapping weightedBootstrapping= new WeightedBootstrapping();
        double[][] dataSet = weightedBootstrapping.readAllStudents();

        StudentInfoLoader studentInfoLoader = new StudentInfoLoader();
        String[][] studentinfoArray = studentInfoLoader.readInfoString();


        // gets the partitioned dataSet based on Ids
        int[] dataSetOfID = new int[1329];
        for( int i = 1 ; i<studentinfoArray.length ; i++){
            dataSetOfID[i-1] = Integer.parseInt(studentinfoArray[i][0]);
        }

//        VarianceReduction varRed = new VarianceReduction(dataSetOfID, dataSet,"full" ,1,1);
//        System.out.println(varRed.getVarianceReduction());

        TreeBuilder tree = new TreeBuilder(dataSet,dataSetOfID,1 );
        System.out.println(tree.TreeBuilder().get(0).getProperty());


    }



}

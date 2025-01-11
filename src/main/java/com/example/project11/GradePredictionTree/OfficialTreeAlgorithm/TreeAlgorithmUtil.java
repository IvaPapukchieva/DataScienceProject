package com.example.project11.GradePredictionTree.OfficialTreeAlgorithm;

import com.example.project11.ProjectInfo.loaders.Loader;
import com.example.project11.ProjectInfo.loaders.StudentInfoLoader;
import com.example.project11.ProjectInfo.loaders.WeightedBootstrapping;
import com.sun.source.tree.Tree;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TreeAlgorithmUtil {
    public static String[][] studentInfoArray;
    public static double[][] weightedBootstrappingArray;

    public static int course ;




    public static void main(String[] args) throws FileNotFoundException {
        // Example data encoded numerically
        course  = 0 ;
        int amountofTrees = 25;
        String[][] student = {{"nothing","59","3 tau","A","0.5 Hz "}};

        StudentInfoLoader studentInfoLoader = new StudentInfoLoader();
        studentInfoArray = studentInfoLoader.readInfoString();

        WeightedBootstrapping weightedBootstrapping = new WeightedBootstrapping();
        weightedBootstrappingArray = weightedBootstrapping.readAllStudents()  ;

       ForestCreator forest  = new ForestCreator(amountofTrees , 40, course , studentInfoArray, weightedBootstrappingArray);
        //System.out.println(forest.getFilteredForest());

        ArrayList<TreeObj> FiltereForestList = new ArrayList<>(forest.getFilteredForest().values());
        double[] gradeList = new double[amountofTrees];
        for( int i  = 0  ; i<amountofTrees ; i++){
            DecisionTreeRegressor regressor = new DecisionTreeRegressor(2, FiltereForestList.get(i).getOptimalDepth());
            regressor.fit(FiltereForestList.get(i).getStudentProperty80percent(FiltereForestList.get(i).getRad80percentStudentIndex()), FiltereForestList.get(i).getGradesOf80percentStudents(FiltereForestList.get(i).getRad80percentStudentIndex()));
            gradeList[i] = regressor.predict(student)[0];
        }
        System.out.println(Arrays.toString(gradeList));

        double sum = 0 ;
        double average = 0 ;
        for( int i = 0 ; i < gradeList.length ; i++){
           sum+= gradeList[i];

        }
        average = (double) sum/(double)gradeList.length;

        System.out.println("GRADE FOR STUDENT : "+ Arrays.deepToString(student)+ " IS ... :"+ average);

    }






}

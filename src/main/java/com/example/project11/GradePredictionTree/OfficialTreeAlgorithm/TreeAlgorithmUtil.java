package com.example.project11.GradePredictionTree.OfficialTreeAlgorithm;

import com.example.project11.ProjectInfo.loaders.Loader;
import com.example.project11.ProjectInfo.loaders.StudentInfoLoader;
import com.example.project11.ProjectInfo.loaders.WeightedBootstrapping;
import com.sun.source.tree.Tree;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class TreeAlgorithmUtil {
    public static String[][] studentInfoArray;
    public static double[][] weightedBootstrappingArray;

    public static int course ;

    public static double[] gradeList ;


    public static void main(String[] args) throws FileNotFoundException {
        // Example data encoded numerically

        String[][] student = {{"nothing","59","3 tau","A","0.5 Hz "}};

        StudentInfoLoader studentInfoLoader = new StudentInfoLoader();
        studentInfoArray = studentInfoLoader.readInfoString();

        WeightedBootstrapping weightedBootstrapping = new WeightedBootstrapping();
        weightedBootstrappingArray = weightedBootstrapping.readAllStudents()  ;

        Map<Integer, List<String> > Thing = getTrees(student);
        System.out.println("Grade List  : "+Arrays.toString(getGradeList()));
        System.out.println("Grade for student : "+ Arrays.deepToString(student)+ " is  : "+ getGradeForStudent());

    }

    public static Map<Integer , List<String>> getTrees(String[][] students) throws FileNotFoundException {
        course  = 0 ;
        int amountofTrees = 30;

        ForestCreator forest  = new ForestCreator(amountofTrees , 80, course , studentInfoArray, weightedBootstrappingArray);
        List<TreeObj> FilterForestList = new ArrayList<>(forest.getFilteredForest().values());
        Map<Integer, List<String>> routMap = new HashMap<>(amountofTrees);


        gradeList = new double[amountofTrees];

        for( int i  = 0  ; i<amountofTrees ; i++){
            DecisionTreeRegressor regressor = new DecisionTreeRegressor(2, FilterForestList.get(i).getOptimalDepth());
            regressor.fit(FilterForestList.get(i).getStudentProperty80percent(FilterForestList.get(i).getRad80percentStudentIndex()), FilterForestList.get(i).getGradesOf80percentStudents(FilterForestList.get(i).getRad80percentStudentIndex()));
            gradeList[i] = regressor.predict(students)[0];
            List<String> temporaryList = new ArrayList<>();
            regressor.getTreeArrayList(regressor.getRoot() ,temporaryList);
        }



        return routMap;

    }
    public static double[] getGradeList(){
        // NEEDS TO RUN GET TREE BEFORE GETGRADELIST
        return gradeList;
    }

    public static double getGradeForStudent(){
    // NEEDS TO RUN GETGRADELIST BEFORE GETGRADEFORSTUDENT !!
        double sum = 0 ;
        double average = 0 ;
        for( int i = 0 ; i < gradeList.length ; i++){
            sum+= gradeList[i];
        }
        average = sum/(double)gradeList.length;
        return average;

    }








}

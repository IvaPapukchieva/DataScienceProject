package com.example.project11.GradePredictionTree.OfficialTreeAlgorithm;

import com.example.project11.ProjectInfo.loaders.StudentInfoLoader;
import com.example.project11.ProjectInfo.loaders.WeightedBootstrapping;

import javax.swing.plaf.IconUIResource;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Predictions {

    private String[][] students;
    private int course;
    public double[] gradeList;
    public double[] gradeListInteger ;


    private String[][] StudentPropertyArray;
    private double[][] WeightedBootsrappingArray;


    private List<TreeObj> FilterForestList;
    private  int amountofTrees = 20;


    //constructor that needs as input the Student
    public Predictions(String[][] students, int course, double[][] weightedBootsrappingArray) throws FileNotFoundException {

        StudentInfoLoader studentInfoLoader = new StudentInfoLoader();
        StudentPropertyArray = studentInfoLoader.readInfoString();

        this.WeightedBootsrappingArray = weightedBootsrappingArray;

        this.students = students;
        this.course = course;

    }

    public void getCreateForest() throws FileNotFoundException {
        course = 0;

        ForestCreator forest = new ForestCreator(amountofTrees, 60, course, StudentPropertyArray, WeightedBootsrappingArray);
         FilterForestList = new ArrayList<>(forest.getFilteredForest().values());


        gradeList = new double[amountofTrees];

    }
    public Map<Integer, List<String>> getTrees(String[][]student)  {
        Map<Integer, List<String>> routMap = new HashMap<>(amountofTrees);

        for (int i = 0; i < amountofTrees; i++) {
            DecisionTreeRegressor regressor = new DecisionTreeRegressor(5, FilterForestList.get(i).getOptimalDepth());
            regressor.fit(FilterForestList.get(i).getStudentProperty80percent(FilterForestList.get(i).getRad80percentStudentIndex()), FilterForestList.get(i).getGradesOf80percentStudents(FilterForestList.get(i).getRad80percentStudentIndex()));
            gradeList[i] = regressor.predict(student)[0];
            List<String> temporaryList = new ArrayList<>();
            regressor.getTreeArrayList(regressor.getRoot(), temporaryList);

            routMap.put(i, temporaryList);
            //System.out.println(temporaryList);
        }


        return routMap;
    }

    public  double[] getGradeList(){
        // NEEDS TO RUN GET TREE BEFORE GETGRADELIST
        gradeListInteger = new double[gradeList.length] ;
        for( int i = 0 ; i< gradeListInteger.length ; i++){
            gradeListInteger[i] = Math.round(gradeList[i]*100)/100;
        }
        return gradeListInteger;
    }

    public  double getGradeForStudent(){
        // NEEDS TO RUN GETGRADELIST BEFORE GETGRADEFORSTUDENT !!
        double sum = 0 ;
        double average = 0 ;
        for( int i = 0 ; i < gradeListInteger.length ; i++){
            sum+= gradeListInteger[i];
        }
        average = sum/(double)gradeListInteger.length;
        return Math.round(average);

    }

    // method that gets the prediction for a single student
    // gets in the student parameters and returns a map

    // method that gets the prediction from all the trees

    // method that gets the combines prediction from each tree


    //method that gets the margin of error.
}

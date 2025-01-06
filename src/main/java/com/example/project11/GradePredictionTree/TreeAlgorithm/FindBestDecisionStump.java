package com.example.project11.GradePredictionTree.TreeAlgorithm;




import com.example.project11.ProjectInfo.loaders.WeightedBootstrapping;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class FindBestDecisionStump {

    private double[][] BootstrappedArray;

    //Parameters that will be determined in the recursive method
    private int Course ;

    //Array that holds the grade distribution for the sepcific course
    // has to be metioned before the recursive method such as to save time on calculations
    private  ArrayList<Double> GradeDistributionForCourse ;



    // Categories array HashMap
    private HashMap<String, Integer> propertyHashMap = new HashMap<>() {{
        put("full", 1);
        put("medium", 1);
        put("nothing",1 );
        put("low", 1);
        put("high", 1);

        put("1 tau", 3);
        put("2 tau", 3);
        put("3 tau", 3);

        put("A", 4);
        put("B", 4);
        put("C", 4);
        put("D", 4);
        put("E", 4);
        put("F", 4);

        put( "0.1 HZ",5);
        put("0.5 HZ", 5);
        put("5.0 HZ", 5);
        put("1.0 HZ", 5);

    }};


    private double[] generalGradeDistribution ;


    public FindBestDecisionStump( int Course) throws FileNotFoundException {
        WeightedBootstrapping bootstrapping = new WeightedBootstrapping();
        BootstrappedArray = bootstrapping.readAllStudents();

        //gets the gradeDistribution object
        GradeDistribution gradeDistribution = new GradeDistribution(Course , true , 2);
        generalGradeDistribution = getPercentageDistribution(gradeDistribution.getGradeCount(Course, true, 2));
        this.Course = Course;


        // Filling up the Map for the Category2
        for (int i = -42 ; i<147  ; i++){
            propertyHashMap.put(String.valueOf(i),2);
        }


    }
    //gets the percentage of the disribution
    public double[] getPercentageDistribution(int[] gradeCount){

        double[] PercentageDistribution = new double[11];

        double sum = 0 ;

        //calculates the total amount of students in each property
        for(int i = 0 ; i<11; i++){
            sum +=gradeCount[i];
        }

        // gets the grade percentage distribution
        for(int i = 0 ; i<gradeCount.length;i++){
            PercentageDistribution[i] = ((double) gradeCount[i] /sum)*100;
        }
        return PercentageDistribution;
    }


    // this method gets the difference from the general array and the new grade count
    public double[] getDifferenceArray(int[] gradeCount){
        double[] percentageDistribution  = getPercentageDistribution(gradeCount);
        double[] differenceArray  = new double[gradeCount.length];

        for(int i = 0 ; i<differenceArray.length ; i++){
            differenceArray[i]= Math.pow((generalGradeDistribution[i] - percentageDistribution[i]),2);
        }
        return differenceArray;
    }
    // method that returns the value that indicates how well a gradeDistribution compares to the originial
    // it takes in a gradeCount ;

    public double getMeasure(int[] gradeCount){
        double[] differenceArray = getDifferenceArray(gradeCount);
        double sum  = 0 ;
        for(int i = 0 ; i < differenceArray.length ; i++){
            sum+= differenceArray[i];
        }
        return sum ;
    }









}


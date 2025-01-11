package com.example.project11.GradePredictionTree.OfficialTreeAlgorithm;

import java.util.Arrays;

public class TreeAnalyser {

    private double[] Grades ;
    private double[]weightedBootstrappingfull ;
    private double[] weightedBootstrappingCourse;
    private  int Course ;
    private double[] RoundedGrades;


    public TreeAnalyser(double[] Grades, double[]weightedBootstrapping , int Course){

        this.Grades = Grades ;
        this.Course = Course ;
       // this.weightedBootstrappingCourse = new double[weightedBootstrapping.length];
        this.weightedBootstrappingCourse = weightedBootstrapping;


        // rounding all the digits
        // we map the digits
        // we compare the digits to the main distribution
//        for (int i = 0 ; i< weightedBootstrapping.length; i++){
//            weightedBootstrappingCourse[i] = weightedBootstrappingfull[i][Course];
//        }

    }

    public double getMethodRunning(){
        getRoundedGrades();
        RoundedGrades = getRoundedGrades();
//        System.out.println("Prediction Grades"+ Arrays.toString(RoundedGrades));
//        System.out.println("Main test set Grades"+ Arrays.toString(weightedBootstrappingCourse));
        // gets the percentage distribution for the  course;
        double[] PercentageDisMain = getPercentageDistribution(weightedBootstrappingCourse);

        // gets the percentage distribution for Grades ;
        double[] GradesPercentageDistribution = getPercentageDistribution(RoundedGrades);

        //System.out.println("Distribution of main "+Arrays.toString(PercentageDisMain));
        //System.out.println("DIstribution of prediction"+Arrays.toString(GradesPercentageDistribution));

        double val = getSumOfDifference(PercentageDisMain,GradesPercentageDistribution);
       //System.out.println(val);

        return val;
    }

    public double[] getRoundedGrades(){
        double[]roundedGrades = new double[Grades.length];
        for(int i = 0; i < Grades.length; i++){
            roundedGrades[i] = (int) Math.round(Grades[i]);
        }
        return roundedGrades;
    }

    public double[] getCountDistribution(double[] grades){
        double[] gradeCount = new double[11];
        for( int i = 0 ; i<grades.length ; i++){
            gradeCount[(int)grades[i]]++;
        }
        return gradeCount;
    }
    public double[] getPercentageDistribution(double[] grades){
        double[] gradeCount = getCountDistribution(grades);

        double[] percentageDistribution = new double[11];
        int sum = 0 ;
        for( int i = 0 ; i<gradeCount.length ; i++){
            sum+= gradeCount[i];
        }
        for (int i = 0 ; i<gradeCount.length ;i++){
            percentageDistribution[i] =(double)Math.round(((gradeCount[i]/(double)sum)*100)*1000)/1000;
        }
        return percentageDistribution;
    }

    //Method to compute the absolute difference of the grade Distribution
    public double[] getDifferenceInPercentDistribution(double[] percentageDistribution, double[] realPercentageDistribution){
         double[] differenceInPercentDistribution = new double[percentageDistribution.length];
         for(int i = 0; i < percentageDistribution.length; i++){
             differenceInPercentDistribution[i] = Math.abs((percentageDistribution[i] - realPercentageDistribution[i]));
         }
         return differenceInPercentDistribution;
    }

    public double getSumOfDifference(double[] percentageDistribution, double[] realPercentageDistribution){
        double[] differenceInPercentDistribution = getDifferenceInPercentDistribution(percentageDistribution,realPercentageDistribution);
        double sum = 0;
        for(int i = 0; i < differenceInPercentDistribution.length; i++){
            sum += differenceInPercentDistribution[i];
        }
        return sum;
    }


}

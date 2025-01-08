package com.example.project11.GradePredictionTree.TreeAlgorithm;

import com.example.project11.ProjectInfo.loaders.StudentInfoLoader;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;

public class VarianceReduction {
    private double[][] dataSet;
    private int[] dataSetOfID;
    private String Property;
    private String[][] StudentInfoArray;
    private int Course;
    private int CategoryIndex;

    public VarianceReduction(@NotNull int[] dataSetOfID, double[][] dataSet, String Property1, int Course, int CategoryIndex)
            throws FileNotFoundException {
        this.dataSet = dataSet;
        this.Property = Property1;
        this.Course = Course;
        this.CategoryIndex = CategoryIndex;
        this.dataSetOfID = dataSetOfID;

        StudentInfoLoader studentInfoLoader = new StudentInfoLoader();
        String[][] StudentInfoArrayFull = studentInfoLoader.readInfoString();

        StudentInfoArray = new String[dataSetOfID.length][6];

        for (int i = 1; i < StudentInfoArray.length; i++) {
            if (dataSetOfID[i - 1] == Integer.parseInt(StudentInfoArrayFull[i][0])) {
                StudentInfoArray[i - 1] = StudentInfoArrayFull[i];
            } else {
                StudentInfoArray[i] = null; // Explicitly mark as null if no match
            }
        }
    }

    private double getAverage(@NotNull String Property1) {
        double count = 0;
        int sum = 0;

        if (Property1.equals("Complete")) {
            for (int i = 0; i < StudentInfoArray.length; i++) {
                sum += dataSet[i][Course];
            }
            return (double) sum / dataSet.length;
        }
        else {
            for (int i = 0; i < dataSet.length; i++) {
                if (StudentInfoArray[i] != null && StudentInfoArray[i][CategoryIndex] != null &&
                        StudentInfoArray[i][CategoryIndex].equals(Property1)) {
                    sum += dataSet[i][Course];
                    count++;
                }
            }

            return count > 0 ? (double) sum / count : 0;
        }
    }

    public double getAntiAverage(String Property1){

        double sum = 0 ;
        double count = 0 ;

        for( int i =0 ; i<dataSetOfID.length ; i++){
            if(StudentInfoArray[i] != null && StudentInfoArray[i][CategoryIndex] != null && !StudentInfoArray[i][CategoryIndex].equals(Property1)){
                sum += dataSet[i][Course];
                count++;
            }
        }
        return count > 0 ? (double) sum / count : 0;

    }

    public double getVariance(String Property1) {
        double average = getAverage(Property1)-1;
        double antiAverage= getAntiAverage(Property1)-1;
        double specificSum = 0, complementarySum = 0;
        int Count1 = 0, Count2 = 0;

        if (Property1.equals("Complete")) {
            for (int i = 0; i < dataSetOfID.length; i++) {
                specificSum += Math.pow((dataSet[i][Course] - average), 2);
            }
            return (double) (specificSum / dataSet.length);
        } else {
            for (int i = 0; i < dataSetOfID.length; i++) {
                if (StudentInfoArray[i] != null && StudentInfoArray[i][CategoryIndex] != null &&
                        StudentInfoArray[i][CategoryIndex].equals(Property1)) {
                    specificSum = Math.pow((dataSet[i][Course] - average), 2)+ specificSum;
                    Count1++;
                } else {
                    complementarySum = Math.pow((dataSet[i][Course] - antiAverage), 2)+ complementarySum;
                    Count2++;
                }
            }

            // Variance for Specific and Complementary Groups
            double VarianceSpecific = Count1 > 0 ? specificSum / Count1 : 0;
            double VarianceComplementary = Count2 > 0 ? complementarySum / Count2 : 0;

            // Calculate Weights
            double weight1 = (double) Count1 / (double)( dataSet.length);
            double weight2 = (double) Count2 / (double)(dataSet.length);


            // Weighted Variance
            return ((VarianceSpecific * weight1) + (VarianceComplementary * weight2));
        }
    }

    public double getVarianceReduction() {
        double VarianceGeneral = getVariance("Complete");
        double VarianceSpecific = getVariance(Property);

        // Handle small floating-point differences
        double varianceReduction = (VarianceGeneral - VarianceSpecific)*100;

//        System.out.println("Variance General : " + VarianceGeneral);
//        System.out.println("Variance Specific : " + VarianceSpecific);



        return varianceReduction;
    }
}

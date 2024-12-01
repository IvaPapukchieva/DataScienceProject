package com.example.project11.ProjectInfo.STEP2;//package CODE;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

/**The program counted how many grades are 6.0 and above (assuming 6.0 is the passing grade) and calculated the 
 * passing percentage by dividing the number counted over the total number of grades multiplied by 100 */

public class PredictPercentage {

    private HashMap<Integer, Double> coursePassingMap;
    private double[][] allStudents ;

    public PredictPercentage(double[][] allStudents) {
            this.allStudents = allStudents;
            coursePassingMap = new HashMap<>();

    }
    private double[] calculatePassingPercentages() {
        int numRows = allStudents.length;
        int numCols = allStudents[0].length;
        double[] passing = new double[numCols];

        for (int col = 0; col < numCols; col++) {
            int totalGrades = 0;
            int passingGrades = 0;

            for (int row = 0; row < numRows; row++) {
                if (allStudents[row][col] != 0.0) {
                    totalGrades++;
                    if (allStudents[row][col] >= 6.0) {
                        passingGrades++;
                    }
                }
            }

            passing[col] = (totalGrades > 0) ? ((double) passingGrades / totalGrades) * 100 : 0.0;
        }

        return passing;
    }

    // Getter for the HashMap to be used in a GUI
    public HashMap<Integer, Double> getCoursePassingMap() {
        calculatePassingPercentages();
        return coursePassingMap;
    }
}
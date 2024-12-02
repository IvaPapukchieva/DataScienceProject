package com.example.project11.ProjectInfo.STEP2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * The program counts how many grades are 6.0 and above (assuming 6.0 is the passing grade)
 * and calculates the passing percentage by dividing the number counted over the total number of grades multiplied by 100.
 */
public class PredictPercentage {

    private static Map<String, Integer> coursePassingMap;
    private double[][] allStudents;

    public PredictPercentage(double[][] allStudents) {
        this.allStudents = allStudents;
        coursePassingMap = new HashMap<>();
        orderByCourses(); // Ensure the array is ordered by courses
    }

    // Method to order the array by courses (columns)
    private void orderByCourses() {
        // Transpose the array: convert columns into rows
        double[][] transposed = transposeArray(allStudents);

        // Sort rows (now representing courses) based on the first grade in each course
        Arrays.sort(transposed, Comparator.comparingDouble(o -> o[0]));

        // Transpose back to original format
        allStudents = transposeArray(transposed);
    }

    // Transpose the 2D array
    private double[][] transposeArray(double[][] array) {
        int rows = array.length;
        int cols = array[0].length;
        double[][] transposed = new double[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = array[i][j];
            }
        }
        return transposed;
    }

    // Calculate passing percentages
    private void calculatePassingPercentages() {
        int numRows = allStudents.length;
        int numCols = allStudents[0].length;

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

            int passingPercentage = (totalGrades > 0) ? (int) (((double) passingGrades / totalGrades) * 100) : 0;
            // Store the passing percentage in the map
            coursePassingMap.put("Course " + (col + 1), passingPercentage);
        }
    }

    // Getter for the HashMap to be used in a GUI
    public Map<String, Integer> getCoursePassingMap() {
        calculatePassingPercentages();
        return coursePassingMap;
    }
}

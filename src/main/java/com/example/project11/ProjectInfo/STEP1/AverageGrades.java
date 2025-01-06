package com.example.project11.ProjectInfo.STEP1;


import java.util.LinkedHashMap;
import java.util.Map;

public class AverageGrades{

    private Map<String, Double> courseAvargeMap;
    private double[][] allStudents;

    public AverageGrades(double[][]allStudent) {
        this.allStudents = allStudent;
        this.courseAvargeMap = new LinkedHashMap<>();
    }

    public Map<String, Double> getAverageGradesMap() {
        try {
            double[] averageCourse = calculateCourseAverages(allStudents);

            for (int i = 0; i < averageCourse.length; i++) {
                String courseName =  Integer.toString((i + 1));
                courseAvargeMap.put(courseName, averageCourse[i]);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return courseAvargeMap;
    }

    public double[] calculateCourseAverages(double[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        double[] averageCourse = new double[numCols]; // Change the array type to double

        for (int j = 0; j < numCols; j++) {
            double sum = 0;
            for (int i = 0; i < numRows; i++) {
                sum += matrix[i][j];
            }
            averageCourse[j] = sum / numRows; // Store the average as a double
        }
        return averageCourse;
    }}
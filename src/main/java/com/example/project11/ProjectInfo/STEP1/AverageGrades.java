package com.example.project11.ProjectInfo.STEP1;


import java.util.LinkedHashMap;
import java.util.Map;

public class AverageGrades{

    private Map<String, Double> courseAvargeMap;

    public AverageGrades() {

        this.courseAvargeMap = new LinkedHashMap<>();
    }

    public Map<String, Double> findEasiestClasses(double[][] allStudents) {
        try {
            double[] averageCourse = calculateCourseAverages(allStudents);

            for (int i = 0; i < averageCourse.length; i++) {
                String courseName = "Course " + (i + 1);
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
        double[] averageCourse = new double[numCols];

        for (int j = 0; j < numCols; j++) {
            double sum = 0;
            for (int i = 0; i < numRows; i++) {
                sum += matrix[i][j];
            }
            averageCourse[j] = sum / numRows;
        }
        return averageCourse;
    }
}

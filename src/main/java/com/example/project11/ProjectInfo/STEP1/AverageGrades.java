package com.example.project11.ProjectInfo.STEP1;


import java.util.LinkedHashMap;
import java.util.Map;

public class AverageGrades{

    private Map<String, Integer> courseAvargeMap;
    private double[][] allStudents;

    public AverageGrades(double[][]allStudent) {
        this.allStudents = allStudent;
        this.courseAvargeMap = new LinkedHashMap<>();
    }

    public Map<String, Integer> getAverageGradesMap() {
        try {
            int[] averageCourse = calculateCourseAverages(allStudents);

            for (int i = 0; i < averageCourse.length; i++) {
                String courseName =  Integer.toString((i + 1));
                courseAvargeMap.put(courseName, averageCourse[i]);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return courseAvargeMap;
    }

    public int[] calculateCourseAverages(double[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        int[] averageCourse = new int[numCols];

        for (int j = 0; j < numCols; j++) {
            double sum = 0;
            for (int i = 0; i < numRows; i++) {
                sum += matrix[i][j];
            }
            averageCourse[j] = (int)sum / numRows;
        }
        return averageCourse;
    }
}

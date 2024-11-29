package com.example.project11.ProjectInfo.STEP1;

import java.util.HashMap;
import java.util.Map;

public class EasiestClasses {

    private Map<String,Double> courseAvargeMap;

    public EasiestClasses() {

        this.courseAvargeMap = new HashMap<>();
    }

    public Map<String, Double> findEasiestClasses(double[][] allStudents) {
        try {

            double[] averageCourse = calculateCourseAverages(allStudents);

            for (int i = 0; i < averageCourse.length; i++) {
                String courseName = "Course " + (i + 1);
                courseAvargeMap.put(courseName, averageCourse[i]);
            }


            findTopThreeEasiestCourses(averageCourse);

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

    public void findTopThreeEasiestCourses(double[] averageCourse) {
        int firstIndex = -1, secondIndex = -1, thirdIndex = -1;
        double first = -1.0, second = -1.0, third = -1.0;

        for (int i = 0; i < averageCourse.length; i++) {
            if (averageCourse[i] > first) {
                third = second;
                thirdIndex = secondIndex;
                second = first;
                secondIndex = firstIndex;
                first = averageCourse[i];
                firstIndex = i;
            } else if (averageCourse[i] > second) {
                third = second;
                thirdIndex = secondIndex;
                second = averageCourse[i];
                secondIndex = i;
            } else if (averageCourse[i] > third) {
                third = averageCourse[i];
                thirdIndex = i;
            }
        }

    }


}

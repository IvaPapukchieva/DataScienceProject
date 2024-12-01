package com.example.project11.ProjectInfo.STEP1;

import java.util.HashMap;
import java.util.Map;

public class EasiestClasses {

    private Map<String, Integer> easiestClassesMap; // Map to store course indices and their average scores
    private double[][] allStudents ;
    // Constructor to set up file details
    public EasiestClasses(double[][] allStudents) {
        this.allStudents = allStudents;
        this.easiestClassesMap = new HashMap<>();
    }

    public Map<String, Integer> getEasiestClassesMap() {
        findTopThreeEasiestCourses(calculateCourseAverages(allStudents));
        return easiestClassesMap;
    }



    // Method to calculate the average score for each course
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

    // Method to find the top 3 easiest courses based on average scores and store them in the HashMap
    public Map<String, Integer> findTopThreeEasiestCourses(double[] averageCourse) {
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

        // Store the results in the HashMap
        easiestClassesMap.put(Integer.toString(firstIndex), (int)first);
        easiestClassesMap.put(Integer.toString(secondIndex),(int) second);
        easiestClassesMap.put(Integer.toString(thirdIndex), (int)third);

        return easiestClassesMap;
    }




}

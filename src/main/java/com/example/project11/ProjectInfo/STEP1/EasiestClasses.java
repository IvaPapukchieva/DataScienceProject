package com.example.project11.ProjectInfo.STEP1;

import java.util.HashMap;

public class EasiestClasses {

    private HashMap<Integer, Double> easiestClassesMap; // Map to store course indices and their average scores

    // Constructor to set up file details
    public EasiestClasses() {
        this.easiestClassesMap = new HashMap<>();
    }

    // Main method to calculate and store the three easiest classes
    public HashMap<Integer, Double> findEasiestClasses(double[][] allStudents) {
        try {
            // Calculate average scores for each course
            double[] averageCourse = calculateCourseAverages(allStudents);

            // Identify the top three easiest courses
            findTopThreeEasiestCourses(averageCourse);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return easiestClassesMap; // Return the map with the easiest classes
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

        // Store the results in the HashMap
        easiestClassesMap.put(firstIndex, first);
        easiestClassesMap.put(secondIndex, second);
        easiestClassesMap.put(thirdIndex, third);

    }

    // Method to print the easiest classes stored in the HashMap
    private void printEasiestClasses() {
        System.out.println("Top 3 Easiest Classes:");
        for (Integer courseIndex : easiestClassesMap.keySet()) {
            System.out.println("Course Index: " + courseIndex + ", Average Score: " + easiestClassesMap.get(courseIndex));
        }
    }
}

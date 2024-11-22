package com.example.project11.ProjectInfo.STEP1;



public class EasiestClasses {



    // Constructor to set up file details
    public EasiestClasses() {

    }


    // Main method to calculate and return the three easiest classes
    public double[] findEasiestClasses(double [][] allStudents) {
        try {

            double[] averageCourse = calculateCourseAverages(allStudents);
            return findTopThreeEasiestCourses(averageCourse);

        } catch (Exception ex) {
            ex.printStackTrace();

        } return null;
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

    // Method to find the top 3 easiest courses based on average scores
    public double[] findTopThreeEasiestCourses(double[] averageCourse) {
        double[] EasiestClasses = new double[3];
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

        EasiestClasses[0] =  first;
        EasiestClasses[1] =  second;
        EasiestClasses[2] =  third;
        return EasiestClasses;
    }
}
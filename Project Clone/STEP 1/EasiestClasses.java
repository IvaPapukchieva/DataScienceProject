import java.io.File;
import java.util.Scanner;



public class EasiestClasses {



    // Constructor to set up file details
    public EasiestClasses() {

    }


    // Main method to calculate and return the three easiest classes
    public String[] findEasiestClasses(double [][] allStudents) {
        try {

            double[] averageCourse = calculateCourseAverages(allStudents);
            return findTopThreeEasiestCourses(averageCourse);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new String[]{"Error calculating easiest classes"};
        }
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
    public String[] findTopThreeEasiestCourses(double[] averageCourse) {
        String[] EasiestClasses = new String[3];
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

        EasiestClasses[0] = "The first easiest course: " + (firstIndex + 1) + " with an average of " + String.format("%.2f", first);
        EasiestClasses[1] = "The second easiest course: " + (secondIndex + 1) + " with an average of " + String.format("%.2f", second);
        EasiestClasses[2] = "The third easiest course: " + (thirdIndex + 1) + " with an average of " + String.format("%.2f", third);
        return EasiestClasses;
    }
}
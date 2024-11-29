package com.example.project11.ProjectInfo.STEP1;

import java.util.HashMap;
import java.util.Map;

/** This program calculates the average of every course and depending on that it finds the first 3 lowest
 * averages which will represent the first 3 hardest classes
*/

public class HardestClasses {

    private Map<String, Double> hardestClassesMap;

    // Constructor to set up file details
    public HardestClasses() {
        this.hardestClassesMap = new HashMap<>();

    }


//    public HashMap<Integer, Double>  findHardestClasses(double [][] allStudents) {
//        try {
//            double[] averageCourse = calculateCourseAverages(allStudents);
//            findTopThreeHardestCourses(averageCourse);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        return hardestClassesMap;
//    }


    //method to calculate and make an array of the courses's averages
    public static double[] calculateCourseAverages(double[][] matrix){
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        double[] averageCourse = new double[numCols];
        int i, j;
        double avg, sum;

        // Iterate over each column
        for (j = 0; j < numCols; j++) {
            sum = 0;
            // Sum the values in column 'j' and the caloculated the avearge
            for (i = 0; i < numRows; i++) {
                sum = sum + matrix[i][j];
            }
                avg = (double) sum/numRows;

                averageCourse[j] = avg;
            }
            return averageCourse;

}
    public  void findTopThreeHardestCourses(double[] averageCourse){

        int firstIndex = -1, secondIndex = -1, thirdIndex = -1;
        double first =100000, second =100000, third =100000;
        for(int i=0;i<averageCourse.length;i++){

            if(averageCourse[i] < first)
            {
            third = second;
            thirdIndex = secondIndex;
            second = first;
            secondIndex = firstIndex;
            first = averageCourse[i];
            firstIndex = i;
            }

            else if (averageCourse[i] < second)
            {
            third = second;
            thirdIndex = secondIndex;
            second = averageCourse[i];
            secondIndex = i;
            }
            else if (averageCourse[i] < third)
            {
                third = averageCourse[i];
                thirdIndex = i;
            }
    }


    }

    }

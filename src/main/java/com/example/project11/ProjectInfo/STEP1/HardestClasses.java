package com.example.project11.ProjectInfo.STEP1;

import java.util.HashMap;

/** This program calculates the average of every course and depending on that it finds the first 3 lowest
 * averages which will represent the first 3 hardest classes
*/

public class HardestClasses {

    private HashMap<Integer, Double> hardestClassesMap; // Map to store course indices and their average scores

    // Constructor to set up file details
    public HardestClasses() {
        this.hardestClassesMap = new HashMap<>();

    }


    public HashMap<Integer, Double>  findHardestClasses(double [][] allStudents) {
        try {
            double[] averageCourse = calculateCourseAverages(allStudents);
            findTopThreeHardestCourses(averageCourse);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new String[]{"Error calculating hardest classes"};
        }
        return hardestClassesMap;
    }


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

    // method to find which are the first 3 hardest courses
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
        // Store the results in the HashMap
        hardestClassesMap.put(firstIndex, first);
        hardestClassesMap.put(secondIndex, second);
        hardestClassesMap.put(thirdIndex, third);

        // Print the map for verification
        printHardestClasses();

        }
    private void printHardestClasses() {
        System.out.println("Top 3 Hardest Classes:");
        for (Integer courseIndex : hardestClassesMap.keySet()) {
            System.out.println("Course Index: " + (courseIndex + 1) + ", Average Score: " + String.format("%.2f", hardestClassesMap.get(courseIndex)));
        }
    }
    }

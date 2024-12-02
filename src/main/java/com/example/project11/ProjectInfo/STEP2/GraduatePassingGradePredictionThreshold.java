package com.example.project11.ProjectInfo.STEP2;

import java.util.HashMap;
import java.util.Map;

/**Counted how many grades are each of the grades starting from 6.0 and if a course has more 6’s than any
 * other grade then the passing percentage would be the division between the number of 6’s over the
 * total number of grades multiplied by 100, else the passing percentage would be the total number of
 * grades over the number of courses multiplied by 100 */

public class GraduatePassingGradePredictionThreshold {
    // Creating a hash map
    private Map<String, Double> coursePassingMap;
    private double[][] allStudents ;

    public GraduatePassingGradePredictionThreshold(double[][] allstudents) {
        this.allStudents = allstudents;

    }

    public void calculatePassingPercentagesArray() {
        coursePassingMap = new HashMap<>();
        // Calculate passing percentages
            double[] passingPercentages = calculatePassingPercentagesArray(allStudents);

            for (int i = 0; i < passingPercentages.length; i++) {
                coursePassingMap.put(Integer.toString(i), passingPercentages[i]); // Course numbers start at 1
            }


    }
    // a method for calculating a passing percentage
    private double[] calculatePassingPercentagesArray(double[][] matrix) {
        // we have determined that a passing student is one that has a 6 or higher based on the graduating grades
        double[]passingPercentages = new double[matrix[0].length];

        for( int j = 0 ; j< allStudents[0].length ; j++) {
            double sum =  0 ;
            double count = 0 ;
            for( int i = 0 ; i< allStudents.length ; i++) {
                count++;
                if(allStudents[i][j]>= 6.0){
                    sum ++;
                }

            }
            passingPercentages[j] = (sum/count)*100;
        }
        return passingPercentages ;
    }


    // a getter for the hash map
    public Map<String, Double> getCoursePassingMap() {
        calculatePassingPercentagesArray();


        return coursePassingMap;
    }
}

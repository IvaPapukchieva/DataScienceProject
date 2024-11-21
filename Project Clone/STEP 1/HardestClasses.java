import java.io.File;
import java.util.Scanner;

/** This program calculates the average of every course and depending on that it finds the first 3 lowest 
 * averages which will represent the first 3 hardest classes
*/

public class HardestClasses {


    // Constructor to set up file details
    public HardestClasses() {

    }


    public String[] findHardestClasses(double [][] allStudents) {
        try {
            double[] averageCourse = calculateCourseAverages(allStudents);
            return findTopThreeHardestCourses(averageCourse);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new String[]{"Error calculating easiest classes"};
        }
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
    public  String[] findTopThreeHardestCourses(double[] averageCourse){
        String [] HardestClasses = new String[3];

        int i;
        int firstIndex = -1, secondIndex = -1, thirdIndex = -1;
        double first =100000, second =100000, third =100000;
        for(i=0;i<averageCourse.length;i++){

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
        HardestClasses[0] = "The first hardest course: " + (firstIndex + 1) + " with an average of " + String.format("%.2f", first);
        HardestClasses[1] = "The second hardest course: " + (secondIndex + 1) + " with an average of " + String.format("%.2f", second);
        HardestClasses[2] = "The third hardest course: " + (thirdIndex + 1) + " with an average of " + String.format("%.2f", third);
return HardestClasses;
        }
    }

package com.example.project11.ProjectInfo.STEP2;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

/**Counted how many grades are each of the grades starting from 6.0 and if a course has more 6’s than any
 * other grade then the passing percentage would be the division between the number of 6’s over the 
 * total number of grades multiplied by 100, else the passing percentage would be the total number of 
 * grades over the number of courses multiplied by 100 */

public class GraduatePassingGradePredictionThreshold {
    // Creating a hash map
    private HashMap<Integer, Double> coursePassingMap;

    public GraduatePassingGradePredictionThreshold(String fName) {
        try {
        	// Adapt this when you want to read and display a different file
            File file=new File(fName);

            coursePassingMap = new HashMap<>();

            // This code uses two Scanners, one which scans the file line per line
            Scanner fileScanner = new Scanner(file);

            int linesDone = 0;
            double[][] allStudents = new double[19353][33];
			int coursesDone = 0;
            while (fileScanner.hasNextLine() && linesDone < 19353) {

            	String line = fileScanner.nextLine();
            	
            	// and one that scans the line entry per entry using the commas as delimiters
            	Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");
				// increments the rows of the data
				coursesDone = 0;
				while (lineScanner.hasNext()) {
            		// Separate commands can be used depending on the types of the entries
            		// (i) and (s) are added to the printout to show how each entry is recognized
            		if (lineScanner.hasNextInt()) {
            			int i = lineScanner.nextInt();
            			// System.out.print("(i)" + i + " ");
            		} else if (lineScanner.hasNextDouble()) {
						allStudents[linesDone][coursesDone++] = lineScanner.nextDouble();
            		} else {
            			String s = lineScanner.next();
            			// System.out.print("(s)" + s + " ");
            		}
            	}
				// increments the columns of the data
				linesDone++;
            	lineScanner.close();
            }

            // Calculate passing percentages
            double[] passingPercentages = calculatePassingPercentages(allStudents);

            for (int i = 0; i < passingPercentages.length; i++) {
                coursePassingMap.put(i + 1, passingPercentages[i]); // Course numbers start at 1
            }

            fileScanner.close();

        } catch (Exception ex) {
            ex.printStackTrace();
		}
    }
    // a method for calculating a passing percentage
    private double[] calculatePassingPercentages(double[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        double[] passing = new double[numCols];

        for (int col = 0; col < numCols; col++) {
            int count6 = 0, count7 = 0, count8 = 0, count9 = 0, count10 = 0;

            for (int row = 0; row < numRows; row++) {
                if (matrix[row][col] == 6.0) count6++;
                if (matrix[row][col] == 7.0) count7++;
                if (matrix[row][col] == 8.0) count8++;
                if (matrix[row][col] == 9.0) count9++;
                if (matrix[row][col] == 10.0) count10++;
            }

            int totalGrades = count6 + count7 + count8 + count9 + count10;

            if (totalGrades > 0) {
                if (count6 > count7 && count6 > count8 && count6 > count9 && count6 > count10) {
                    // Majority grades are 6.0
                    passing[col] = ((double) count6 / totalGrades) * 100;
                } else {
                    // Calculate total grades as a percentage of all rows
                    passing[col] = ((double) totalGrades / numRows) * 100;
                }
            } else {
                passing[col] = 0.0; // No grades found in this course
            }
        }

        return passing;
    }

    // a getter for the hash map
    public HashMap<Integer, Double> getCoursePassingMap() {
        return coursePassingMap;
    }
}

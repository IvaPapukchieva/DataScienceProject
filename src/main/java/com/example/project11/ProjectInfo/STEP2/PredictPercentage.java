package com.example.project11.ProjectInfo.STEP2;//package CODE;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

/**The program counted how many grades are 6.0 and above (assuming 6.0 is the passing grade) and calculated the 
 * passing percentage by dividing the number counted over the total number of grades multiplied by 100 */

public class PredictPercentage {

    private HashMap<Integer, Double> coursePassingMap;

    public PredictPercentage(String fileName) {

        try {
        	// Adapt this when you want to read and display a different file.
            coursePassingMap = new HashMap<>();

            File file=new File(fileName);
            
            // This code uses two Scanners, one which scans the file line per line
            Scanner fileScanner = new Scanner(file);
            double[][] allStudents = new double[1328][33];
			
           

            int linesDone = -1 ; 
            int coursesDone = 0 ;
            int newNg = -1 ;  
        
            while (fileScanner.hasNextLine() && linesDone <= 1328) {

            	String line = fileScanner.nextLine();
            	
            	// and one that scans the line entry per entry using the commas as delimiters
            	Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");
				// increments the rows of the data
				coursesDone = 0;
				while (lineScanner.hasNext()) {
            		if (lineScanner.hasNextInt()) {
            			int i = lineScanner.nextInt();
            		}  if (lineScanner.hasNextDouble()) {
						allStudents[linesDone][coursesDone++] = lineScanner.nextDouble();
            		} else {
            			String s = lineScanner.next();
                        if(s.equals("NG")){

                            allStudents[linesDone][coursesDone++] = newNg;

                        }
            		}
            	}
				linesDone++;
            	lineScanner.close();
            }

			// DEAL WITH NG's
			double total = 0.0;
			int count = 0;
			//cycles through matrix by column (class)
			for (int col = 0; col < allStudents[0].length; col++) {
				//adds up all the grades
				for (int row = 0; row < allStudents.length; row++) {if(allStudents[row][col] != -1.0) {total += allStudents[row][col]; count++;}}

				// replaces -1s with the average grade of the column (class)
				for (int row = 0; row < allStudents.length; row++) {if(allStudents[row][col] == -1.0) {allStudents[row][col] = Math.round(total/count * 10.0) / 10.0;}}
				
				//reset counter values
				total = 0.0;
				count = 0;
			}

            double[] passingPercentages = calculatePassingPercentages(allStudents);
            for (int i = 0; i < passingPercentages.length; i++) {
                coursePassingMap.put(i + 1, passingPercentages[i]); // Map course number to percentage
            }
            //System.out.println("Passing percentage for course " + p + " is: " + passing[i] + "%");}

            fileScanner.close();

        } catch (Exception ex) {
            ex.printStackTrace();
		}

    }
    private double[] calculatePassingPercentages(double[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        double[] passing = new double[numCols];

        for (int col = 0; col < numCols; col++) {
            int totalGrades = 0;
            int passingGrades = 0;

            for (int row = 0; row < numRows; row++) {
                if (matrix[row][col] != 0.0) {
                    totalGrades++;
                    if (matrix[row][col] >= 6.0) {
                        passingGrades++;
                    }
                }
            }

            passing[col] = (totalGrades > 0) ? ((double) passingGrades / totalGrades) * 100 : 0.0;
        }

        return passing;
    }

    // Getter for the HashMap to be used in a GUI
    public HashMap<Integer, Double> getCoursePassingMap() {
        return coursePassingMap;
    }
}
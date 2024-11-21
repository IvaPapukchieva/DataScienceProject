package com.example.project11.ProjectInfo.STEP2;//package CODE;

import java.io.File;
import java.util.Scanner;

/**The program counted how many grades are 6.0 and above (assuming 6.0 is the passing grade) and calculated the 
 * passing percentage by dividing the number counted over the total number of grades multiplied by 100 */

public class PredictPercentage {

    public PredictPercentage(String fileName) {
        try {
        	// Adapt this when you want to read and display a different file.

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

            double[] passing = pass(allStudents);
            for(int i=0;i<33;i++){
                int p=i+1;
            }
            //System.out.println("Passing percentage for course " + p + " is: " + passing[i] + "%");}

            fileScanner.close();

		

        } catch (Exception ex) {
            ex.printStackTrace();
		}

		

		


    } 


    public double[] pass(double[][] matrix){
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        double[] passing = new double[numCols];
        int i, j; 
        
        // Iterate over each column
        for (j = 0; j < numCols; j++) {
           int k = 0;
            int p=0;

            for (i = 0; i < numRows; i++) {
                if(matrix[i][j]!=0.0)
                //counting how many grades are
                p++;
                if(matrix[i][j]>=6.0)
                //assuming that 6.0 is the passing grade, counting how many passing grades are
                k++;
            }
            if(p>0){
                //caslculating the passing percentage
                passing[j]= ( (double)k/p) *100;}
                else{
                passing[j] = 0;}

            }
            return passing;
        
}
}
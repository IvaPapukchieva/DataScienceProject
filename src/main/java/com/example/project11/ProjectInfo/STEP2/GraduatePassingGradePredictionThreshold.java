package com.example.project11.ProjectInfo.STEP2;

import java.io.File;
import java.util.Scanner;

/**Counted how many grades are each of the grades starting from 6.0 and if a course has more 6’s than any
 * other grade then the passing percentage would be the division between the number of 6’s over the 
 * total number of grades multiplied by 100, else the passing percentage would be the total number of 
 * grades over the number of courses multiplied by 100 */

public class GraduatePassingGradePredictionThreshold {

    public GraduatePassingGradePredictionThreshold(String fName) {
        try {
        	// Adapt this when you want to read and display a different file
            File file=new File(fName);
            
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
		
			
            double[] passing = pass(allStudents);
			for(int i=0;i<33;i++){
                int p=i+1;
             //   System.out.println("The passing percentage the courses " + p + "is: " + passing[i] + "%");

            }

            double sum=0;
            for(int i=0;i<33;i++){
                sum=sum+passing[i];
            }
            double avg = sum/33;
           // System.out.println("The passing percentage for all courses is: " + avg + "%");


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
    double sum=0, avg;
    
    // Iterate over each column
    for (j = 0; j < numCols; j++) {
       int count6 = 0, count7=0, count8=0, count9=0,count10 =0;

        for (i = 0; i < numRows; i++) {
            if(matrix[i][j]==6.0)
            //assuming that 6.0 is the passing grade, counting how many passing grades are
            count6++;
            if(matrix[i][j]==7.0)
            count7++;
            if(matrix[i][j]==8.0)
            count8++;
            if(matrix[i][j]==9.0)
            count9++;
            if(matrix[i][j]==10.0)
            count10++;
        }
        int total = count6+count7+count8+count9+count10;
        if(count6>count7 && count6>count8 && count6>count9 && count6>count10)
            //there must be a lot of 5s
            passing[j]= ((double) count6/total)*100;
            else
            passing[j]= ((double) total/numRows)*100;
    }
        return passing;
    }
}

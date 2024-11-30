package com.example.project11.ProjectInfo.loaders;//package CODE;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CurrentGradeLoaderRemoveNG implements Loader {

		private int numRows;
		private int	numCols;
		private  String fileName;
		public double[][] allStudents;

		public CurrentGradeLoaderRemoveNG() {
			this.fileName ="src/main/resources/csv/CurrentGrades.csv";
			this.numRows = 1329;
			this.numCols = 33;
			this.allStudents = new double[numRows][numCols];
		}


			public double[][] readAllStudents() throws FileNotFoundException {
        	// Adapt this when you want to read and display a different file.
            File file=new File(fileName);
            
            // This code uses two Scanners, one which scans the file line per line
            Scanner fileScanner = new Scanner(file);
			
           

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
			ArrayList<ArrayList<Double>> allClasses = new ArrayList<ArrayList<Double>>(allStudents[0].length);
			//add all the classes
			for(int i = 0; i < allStudents[0].length; i++) {
				allClasses.add(new ArrayList<Double>());
			}
			//cycles through matrix by column (class)
			for (int col = 0; col < allStudents[0].length; col++) {
				for (int row = 0; row < allStudents.length; row++) {
					if(allStudents[row][col] != -1) {allClasses.get(col).add(allStudents[row][col]);}
				}
			}

//			System.out.println(allClasses.toString());


            /* PRINTS OUT THE ENTIRE 2D ARRAY */ 

            fileScanner.close();
			return (double[][]) allClasses.stream().toArray();
        }
	public int[]setStudentID() throws FileNotFoundException {

		File file = new File(fileName);
		Scanner fileScanner = new Scanner(file);

		int[] studentID = new int[numRows+1];

		fileScanner = new Scanner(file);
		int index = 0;

		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
			Scanner lineScanner = new Scanner(line);
			lineScanner.useDelimiter(",");

			if (lineScanner.hasNextInt()) {
				// Read the first column (ID) and add to the array
				studentID[index++] = lineScanner.nextInt();
			}

			lineScanner.close();
		}

		fileScanner.close();

		return studentID;


	}}

		

		






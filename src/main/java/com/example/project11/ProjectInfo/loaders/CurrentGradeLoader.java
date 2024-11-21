package com.example.project11.ProjectInfo.loaders;//package CODE;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CurrentGradeLoader {

	private static String fileName;
	private int numRows;
	private int	numCols;

	public double[][] allStudents ;
	public int [] StudentID;

	public CurrentGradeLoader () throws FileNotFoundException {
		this.fileName ="src/main/resources/csv/CurrentGrades.csv";
		this.numRows = 1329;
		this.numCols = 33;
		this.allStudents = new double[numRows][numCols];
		this.allStudents = readAllStudents();
		this.StudentID = setStudentID();


	}
			public double[][] readAllStudents()throws FileNotFoundException{
				try {


					// Adapt this when you want to read and display a different file.
					File file = new File(fileName);

					// This code uses two Scanners, one which scans the file line per line
					Scanner fileScanner = new Scanner(file);



					int linesDone = -1;
					int coursesDone = 0;
					int newNg = -1;

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
							}
							if (lineScanner.hasNextDouble()) {
								allStudents[linesDone][coursesDone++] = lineScanner.nextDouble();
							} else {
								String s = lineScanner.next();
								if (s.equals("NG")) {

									allStudents[linesDone][coursesDone++] = newNg;

								}
							}
						}
						linesDone++;
						lineScanner.close();
					}


					fileScanner.close();



				} catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
				return allStudents;

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


	}
		}


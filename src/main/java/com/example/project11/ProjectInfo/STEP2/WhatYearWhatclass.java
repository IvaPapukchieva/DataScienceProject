package com.example.project11.ProjectInfo.STEP2;// package CODE ;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

/**The program determined the course year based on missing data, assuming that absent grades indicated
 * students had not yet taken those classes, suggesting they are offered later in the academic year. 
 * It counted the NG (No Grade) values per class and identified classes with similar counts, ensuring a 
 * difference of about 11 or more to prevent overfitting our data. */

public class WhatYearWhatclass {

	private HashMap<String, int[]> courseYearMap;

	public WhatYearWhatclass(String fileName) {
		try {
			File file = new File(fileName);

			courseYearMap = new HashMap<>();

			// This code uses two Scanners, one which scans the file line per line
			Scanner fileScanner = new Scanner(file);
			double[][] allStudents = new double[1329][33];


			int linesDone = -1;
			int coursesDone = 0;
			int newNg = -1;

			while (fileScanner.hasNextLine() && linesDone <= 1328) {

				String line = fileScanner.nextLine();

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


			double[] arrayOfNumGrade = new double[allStudents[0].length];
			double count = 0;

			for (int i = 0; i < allStudents[0].length; i++) {
				for (int j = 0; j < allStudents.length; j++) {
					if (allStudents[j][i] == -1.0) {
						count++;
						arrayOfNumGrade[i] = count;
					}

				}
				count = 0;
			}

			int[] arrayFirstYear = new int[arrayOfNumGrade.length];
			int[] arraySecondYear = new int[arrayOfNumGrade.length];
			int[] arrayThirdYear = new int[arrayOfNumGrade.length];
			int[] arrayFourthYear = new int[arrayOfNumGrade.length];
			int[] arrayFifthYear = new int[arrayOfNumGrade.length];

			int firstYearCount = 0;
			int secondYearCount = 0;
			int thirdYearCount = 0;
			int fourthYearCount = 0;
			int fifthYearCount = 0;

			for (int i = 0; i < arrayOfNumGrade.length; i++) {
				if (arrayOfNumGrade[i] >= 0.0 && arrayOfNumGrade[i] <= 12.0) {
					arrayFifthYear[fifthYearCount++] = i;
				} else if (arrayOfNumGrade[i] >= 583.0 && arrayOfNumGrade[i] <= 595.0) {
					arrayFourthYear[fourthYearCount++] = i;
				} else if (arrayOfNumGrade[i] >= 599.0 && arrayOfNumGrade[i] <= 622.0) {
					arrayThirdYear[thirdYearCount++] = i;
				} else if (arrayOfNumGrade[i] >= 985.0 && arrayOfNumGrade[i] <= 1058.0) {
					arraySecondYear[secondYearCount++] = i;
				} else if (arrayOfNumGrade[i] >= 1317.0 && arrayOfNumGrade[i] <= 1339.0) {
					arrayFirstYear[firstYearCount++] = i;
				}
			}

			courseYearMap.put("FirstYear", trimArray(arrayFirstYear, firstYearCount));
			courseYearMap.put("SecondYear", trimArray(arraySecondYear, secondYearCount));
			courseYearMap.put("ThirdYear", trimArray(arrayThirdYear, thirdYearCount));
			courseYearMap.put("FourthYear", trimArray(arrayFourthYear, fourthYearCount));
			courseYearMap.put("FifthYear", trimArray(arrayFifthYear, fifthYearCount));

			fileScanner.close();


		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

		private int[] trimArray(int[] array, int size) {
			int[] trimmedArray = new int[size];
			System.arraycopy(array, 0, trimmedArray, 0, size);
			return trimmedArray;
		}

		public HashMap<String, int[]> getCourseYearMap() {
			return courseYearMap;
		}





}



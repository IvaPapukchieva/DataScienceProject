package com.example.project11.ProjectInfo.STEP2;// package CODE ;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**The program determined the course year based on missing data, assuming that absent grades indicated
 * students had not yet taken those classes, suggesting they are offered later in the academic year. 
 * It counted the NG (No Grade) values per class and identified classes with similar counts, ensuring a 
 * difference of about 11 or more to prevent overfitting our data. */

public class WhatYearWhatclass {

	private Map<String, Integer> courseYearMap;
	private double[][] AllStudents;

	public WhatYearWhatclass(double[][] allStudents) {
		this.AllStudents= allStudents;
	}

	public void hashCreator() {


			courseYearMap = new HashMap<>();

			double[] arrayOfNumGrade = new double[AllStudents[0].length];
			double count = 0;

			for (int i = 0; i < AllStudents[0].length; i++) {
				for (int j = 0; j < AllStudents.length; j++) {
					if (AllStudents[j][i] == -1.0) {
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

			courseYearMap.put("FirstYear", (firstYearCount));
			courseYearMap.put("SecondYear", (secondYearCount));
			courseYearMap.put("ThirdYear", (thirdYearCount));
			courseYearMap.put("FourthYear", (fourthYearCount));
			courseYearMap.put("FifthYear", (fifthYearCount));




	}



		public Map<String, Integer> getCourseYearMap() {
			hashCreator() ;
			return courseYearMap;
		}





}



package com.example.project11.ProjectInfo.STEP2;

import com.example.project11.ProjectInfo.loaders.CurrentGradeLoader;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * The program determined that students with 3 or fewer “No Grade” (NG) values are nearing graduation,
 * resulting in identifying 237 students by saving their indices.
 */
public class GraduatingSoon {
	private Map<Integer, Double> studentDataMap;
	private double[][] allStudents;

	public GraduatingSoon(double[][] allStudents) {
		this.allStudents = allStudents;
	}

	public static void graduatingSoon(double[][] allStudents) {
		// Initialize the list to store the number of NG values for each student
		ArrayList<Double> amountOfNgForEachStudent = new ArrayList<>();

		for (int i = 0; i < allStudents.length; i++) {
			double amountNG = 0;
			for (int j = 0; j < allStudents[i].length; j++) {
				if (allStudents[i][j] == -1.0) { // Could lead to error as not all arrays have NG as -1
					amountNG++;
				}
			}
			amountOfNgForEachStudent.add(amountNG);
		}

		// Sort the list in ascending order
		amountOfNgForEachStudent.sort(Comparator.naturalOrder());
		System.out.println("Sorted NG Values: " + amountOfNgForEachStudent);

		// Create year data from the sorted list
		Map<String, Double> yearData = generateYearData(amountOfNgForEachStudent);
		System.out.println("Year Data: " + yearData);
	}

	public static Map<String, Double> generateYearData(List<Double> sortedData) {
		// Map to store years and their respective counts
		Map<String, Double> yearDataMap = new LinkedHashMap<>();
		int year = 1;
		double previousValue = -1;
		double count = 0;

		for (double value : sortedData) {
			if (value != previousValue ) { // A new value appears
				if (count >20) { // Add previous count to the map
					yearDataMap.put("Year " + year, count);
					year++;
				}
				// Reset count for the new value
				count = 0;
				previousValue = value;
			} else {
				// Increment count for the same value
				count++;
			}
		}

		// Add the final year to the map
		if (count > 0) {
			yearDataMap.put("Year " + year, count);
		}

		return yearDataMap;
	}
}

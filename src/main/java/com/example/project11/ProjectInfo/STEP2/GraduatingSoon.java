package com.example.project11.ProjectInfo.STEP2;

import java.util.*;

/**
 * This program identifies students nearing graduation by counting the number of "No Grade" (NG) values
 * in their academic records and grouping them into sets based on the NG counts.
 */
public class GraduatingSoon {
	private static double[][] allStudents;

	public GraduatingSoon(double[][] allStudents) {
		this.allStudents = allStudents;
	}


	public Map<String, Integer> getStudentGroups() {
		Map<String, Integer> studentGroups = new LinkedHashMap<>();
		Map<Integer, Integer> ngCounts = new HashMap<>();

		// Count NG values for each student
		for (double[] student : allStudents) {
			int ngCount = 0;
			for (double grade : student) {
				if (grade == -1.0) { // Assuming -1.0 represents NG
					ngCount++;
				}
			}

			ngCounts.put(ngCount, ngCounts.getOrDefault(ngCount, 0) + 1);
		}

		List<Integer> sortedNgKeys = new ArrayList<>(ngCounts.keySet());
		Collections.sort(sortedNgKeys);

		int groupNumber = 1;
		for (int ngCount : sortedNgKeys) {
			String groupLabel = "StudentGroup" + Integer.toString(groupNumber);
			studentGroups.put(groupLabel, ngCounts.get(ngCount));
			groupNumber++;
		}

		return studentGroups;
	}
}

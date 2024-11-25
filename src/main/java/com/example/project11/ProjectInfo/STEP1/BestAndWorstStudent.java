package com.example.project11.ProjectInfo.STEP1;

import java.util.HashMap;
import java.util.Map;

/**
 * This program calculates GPAs, finds students eligible for honors by comparing their GPA values to
 * predefined thresholds for each honor level, and identifies the students
 * with the highest and lowest GPAs and prints their information.
 */
public class BestAndWorstStudent {
	private double[][] allStudents;
	private HashMap<Integer, Double> studentGPA; // HashMap to store student ID and their GPA

	public BestAndWorstStudent(double[][] allStudents) {
		this.allStudents = allStudents;
		this.studentGPA = new HashMap<>();
	}

	/**
	 * Finds the best and worst students and returns a Map containing their IDs and GPAs.
	 * @return A Map with keys "bestStudent" and "worstStudent", each mapping to a Map of student ID and GPA.
	 */
	public Map<String, Map<Integer, Double>> findBestAndWorstStudent() {
		double bestGPA = 0;
		double worstGPA = Double.POSITIVE_INFINITY;
		int bestStudent = -1;
		int worstStudent = -1;
		int subjectNumber = 33; // Number of subjects

		// Iterate through students and calculate their GPA
		for (int i = 1; i < allStudents.length - 1; i++) {
			double sum = 0;

			for (int j = 0; j < allStudents[i].length; j++) {
				sum += allStudents[i][j];
			}

			double gpa = sum / subjectNumber;
			studentGPA.put(i + 1, gpa); // Store GPA in the HashMap with student ID as key

			// Identify the best and worst students
			if (gpa > bestGPA) {
				bestGPA = gpa;
				bestStudent = i + 1; // Adjusting index to 1-based student ID
			}
			if (gpa < worstGPA) {
				worstGPA = gpa;
				worstStudent = i + 1; // Adjusting index to 1-based student ID
			}
		}

		// Prepare the result map for best and worst students
		Map<String, Map<Integer, Double>> result = new HashMap<>();
		if (bestStudent != -1) {
			Map<Integer, Double> bestStudentInfo = new HashMap<>();
			bestStudentInfo.put(bestStudent, bestGPA);
			result.put("bestStudent", bestStudentInfo);
		}

		if (worstStudent != -1) {
			Map<Integer, Double> worstStudentInfo = new HashMap<>();
			worstStudentInfo.put(worstStudent, worstGPA);
			result.put("worstStudent", worstStudentInfo);
		}

		return result;
	}

	// Method to print all students' GPAs
	public void printAllGPA() {
		System.out.println("All Students' GPAs:");
		for (int studentID : studentGPA.keySet()) {
			System.out.println("Student " + studentID + " -> GPA: " + String.format("%.2f", studentGPA.get(studentID)));
		}
	}

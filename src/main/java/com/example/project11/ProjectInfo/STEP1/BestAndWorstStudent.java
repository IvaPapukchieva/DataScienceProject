package com.example.project11.ProjectInfo.STEP1;
//package CODE ;

/**This program calculates GPAs, finds students eligible for honors by comparing their GPA values to
 * predefined thresholds for each honor level, and identifies the students 
 * with the highest and lowest GPAs and prints their information */

public class BestAndWorstStudent {
	private double [][] allStudents;
	private  double []GPA;

    public BestAndWorstStudent( double [][] allStudents){
		this.GPA = new double [allStudents.length-1];
        this.allStudents = allStudents;
	}


	// The highest and the lowest GPA
	public void findBestAndWorstStudent() {

		double bestGPA = 0;
		double worstGPA = Double.POSITIVE_INFINITY;
		double bestSum = 0;
		double worstSum = Double.POSITIVE_INFINITY;
		int BestStudent = -1;
		int WorstStudent = -1;
		int subjectNumber = 33;

		for (int i = 1; i < allStudents.length-1; i++) {
			double sum = 0;

			for (int j = 0; j < allStudents[i].length; j++) {

				sum += allStudents[i][j];

			}
			GPA[i] = sum / subjectNumber;

			if (sum > bestSum) {
				bestSum = sum;
				bestGPA = GPA[i];

				BestStudent = i;
			}
			if (sum < worstSum) {
				worstSum = sum;
				worstGPA = GPA[i];
				WorstStudent = i;
			}

		}
		if (BestStudent != -1) {
			System.out.println(
					"The best student is " + (BestStudent + 1) + " with a score of " + String.format("%.1f", bestGPA));
		} else {
			System.out.println("No best student found.");
		}

		if (WorstStudent != -1) {
			System.out.println("The worst student is " + (WorstStudent + 1) + " with a score of "
					+ String.format("%.1f", worstGPA));
		} else {
			System.out.println("No worst student found.");
		}

	}


}



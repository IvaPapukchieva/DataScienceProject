package com.example.project11.ProjectInfo.Filters;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FilterByGradeRange {

    private int lowerBound;
    private int upperBound;
    private double[][] allStudents;

    public FilterByGradeRange(int lowerBound, int upperBound, double[][] allStudents) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.allStudents = allStudents;
    }

    public double[][] getStudentsByGradeRange() {
        double[][] filteredStudents = new double[allStudents.length][allStudents[0].length];
        for (int i = 0; i < allStudents.length; i++) {
            for (int j = 0; j < allStudents[0].length; j++) {
                if (allStudents[i][j] >= lowerBound && allStudents[i][j] <= upperBound) {
                    filteredStudents[i][j] = allStudents[i][j];
                } else filteredStudents[i][j] = -1;
            }
        }
        return filteredStudents;

    }
    public Map<String, Integer> calculateGradeRangeDistribution() {
        String name;
        double[][] gradeRange = getStudentsByGradeRange();

        Map<String, Integer> gradeCounts = new HashMap<>();


        gradeCounts.put("In the range", 0);
        gradeCounts.put("Out of the range", 0);

        for (double[] studentGrades : gradeRange) {
            for (double grade : studentGrades) {
                String range = getGradeRange(grade);
                gradeCounts.put(range, gradeCounts.get(range) + 1);
            }
        }
        return gradeCounts;
    }

    private String getGradeRange(double grade) {
        if (grade != -1) return "In the range";
        return "Out of the range";



    }
}




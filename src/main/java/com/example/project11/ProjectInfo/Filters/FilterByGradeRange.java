package com.example.project11.ProjectInfo.Filters;

import java.util.Arrays;

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
}




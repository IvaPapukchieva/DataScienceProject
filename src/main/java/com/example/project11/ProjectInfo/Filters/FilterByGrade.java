package com.example.project11.ProjectInfo.Filters;

import java.util.HashMap;
import java.util.Map;

public class FilterByGrade {

    private final double[][] allStudents;

    public FilterByGrade(double[][] allStudents) {

        this.allStudents = allStudents;
    }

    // by range
    public double[][] filterStudents(int lowerBound, int upperBound) {
        if (lowerBound > upperBound) {
            int temp = upperBound;
            upperBound = lowerBound;
            lowerBound = temp;
        }
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

    // many grades
    public double[][] filterStudents(int[] grades) {
        double[][] filteredStudents = new double[allStudents.length][allStudents[0].length];
        for (int i = 0; i < allStudents.length; i++) {
            for (int j = 0; j < allStudents[0].length; j++) {
                boolean matches = false;
                for (int grade : grades) {
                    if (allStudents[i][j] == grade) {
                        matches = true;
                        break;
                    }
                }

                if (matches) {
                    filteredStudents[i][j] = allStudents[i][j];
                } else {
                    filteredStudents[i][j] = -1;
                }
            }
        }
        return filteredStudents;
    }

    public double[][] filterStudents(int grade) {

        double[][] filteredStudents = new double[allStudents.length][allStudents[0].length];
        for (int i = 0; i < allStudents.length; i++) {
            for (int j = 0; j < allStudents[i].length; j++) {
                if (allStudents[i][j] != grade) {
                    filteredStudents[i][j] = -1;
                } else {
                    filteredStudents[i][j] = allStudents[i][j];
                }
            }
        }

        return filteredStudents;
    }

    
}




package com.example.project11.ProjectInfo.Filters;

import com.example.project11.ProjectInfo.loaders.CurrentGradeLoader;


import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class FilterByStudentNG extends CurrentGradeLoader implements Filter{
//    the student id + the ng

    private final double[][] allStudents;
    private double[] numberOfNG;

    public FilterByStudentNG(double[][] allStudents) throws FileNotFoundException {
        super();
        this.allStudents = allStudents;
        numberOfNG = getAllTheNGByStudent();
    }


    public double[][] filterStudents(int amountNG) {
        double[][] filteredStudents = new double[numberOfNG.length+1][2];
        for (int i = 0; i < numberOfNG.length; i++) {
            filteredStudents[i][0] = StudentID[i];
            if (numberOfNG[i] == amountNG) {
                filteredStudents[i][1] = amountNG;
            } else {
                filteredStudents[i][1] = -1;
            }
        }
        return filteredStudents;
    }
    public double[][] filterStudents(int lowerBound, int upperBound) {
        if (lowerBound > upperBound) {
            int temp = lowerBound;
            lowerBound = upperBound;
            upperBound = temp;
        }

        double[][] filteredStudents = new double[numberOfNG.length+1][2];

        for (int i = 0; i < numberOfNG.length; i++) {
            filteredStudents[i][0] = StudentID[i];
            if (numberOfNG[i] >= lowerBound && numberOfNG[i] <= upperBound) {
                filteredStudents[i][1] = numberOfNG[i];
            } else {
                filteredStudents[i][1] = -1;
            }
        }

        return filteredStudents;
    }

    public double[][] filterStudents(int[] amountNGs) {
        double[][] filteredStudents = new double[numberOfNG.length+1][2];
        for (int i = 0; i < numberOfNG.length; i++) {
            filteredStudents[i][0] = StudentID[i];
            boolean matchFound = false;
            for (int amountNG: amountNGs) {
                if (numberOfNG[i] == amountNG) {
                    filteredStudents[i][1] = amountNG;
                    matchFound = true;
                    break;
                }
            }

            if (!matchFound) {filteredStudents[i][1] = -1;
            }
        }

        return filteredStudents;
    }


    public double[] getAllTheNGByStudent() {
        numberOfNG = new double[allStudents.length];

        for (int i = 0; i < allStudents.length; i++) {
            int ngCount = 0;
            for (double grade : allStudents[i]) {
                if (grade == -1.0) {
                    ngCount++;
                }
            }
            numberOfNG[i] = ngCount;
        }

        return numberOfNG;
    }


}
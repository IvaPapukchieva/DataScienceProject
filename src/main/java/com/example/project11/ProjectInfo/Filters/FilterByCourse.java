package com.example.project11.ProjectInfo.Filters;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;


import java.lang.reflect.Array;

import static java.util.Collections.swap;

public class FilterByCourse {

    private double [][] allStudents;

    public FilterByCourse(double [][] allStudents) {
        this.allStudents = allStudents;

    }

    public double[][] filterStudents(int[] courses) {
        double[][] filteredStudents = new double[allStudents.length][courses.length];
        for (int i = 0; i < allStudents.length; i++) {
            for (int j = 0 ; j < courses.length; j++) {
                filteredStudents[i][j] = allStudents[i][j];

            }
        }
        return filteredStudents;
    }

    public double[][] filterStudents(int lowerBound, int upperBound) {
        //adjust for indices
        lowerBound--;upperBound--;

        if (lowerBound > upperBound) {
            int temp = upperBound;
            upperBound = lowerBound;
            lowerBound = temp;
        }

        int rangeLength = upperBound - lowerBound + 1;
        double[][] filteredStudents = new double[allStudents.length][rangeLength];
        for (int i = 0; i < allStudents.length; i++) {
            for (int j = lowerBound, k = 0; j <= upperBound; j++, k++) {
                filteredStudents[i][k] = allStudents[i][j];
            }
        }
        return filteredStudents;
    }


    public double[][] filterStudents(int course) {
        double[] filteredStudents = new double[allStudents.length];
        for (int i = 0; i < allStudents.length; i++) {
            filteredStudents[i] = allStudents[i][course];
        }
        return new double[][]{filteredStudents};
    }
}

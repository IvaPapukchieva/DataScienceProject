package com.example.project11.ProjectInfo.Filters;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;


import java.lang.reflect.Array;

public class FilterByCourse {

    private double [][] allStudents;

    public FilterByCourse(double [][] allStudents) {
        this.allStudents = allStudents;

    }

    public double[][] getAllStudentsFilterCourse(int[] courses) {
        double[][] filteredStudents = new double[allStudents.length][courses.length];
        for (int i = 0; i < allStudents.length; i++) {
            for (int j = 0 ; j < courses.length; j++) {
                filteredStudents[i][j] = allStudents[i][j];

            }
        }
        return filteredStudents;
    }

    public double [] getSpecificCourse(int course) {
//        this can be 1D
        double[] filteredStudents = new double[allStudents.length];
        for (int i = 0; i < allStudents.length; i++) {
            filteredStudents[i] = allStudents[i][course];
        }
        return filteredStudents;
    }
}

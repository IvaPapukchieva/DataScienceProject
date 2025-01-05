package com.example.project11.ProjectInfo.Filters;

import com.example.project11.ProjectInfo.loaders.StudentInfoLoader;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class FilterByStudentNG extends StudentInfoLoader  implements Filter{

    private final double[][] allStudents;
    private double[] numberOfNG;

    public FilterByStudentNG(double[][] allStudents) throws FileNotFoundException {
        super();
        this.allStudents = allStudents;
        numberOfNG = getAllTheNGByStudent();
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

    public Map<String, Double> getFilterByStudentNG(double NG) {
        Map<String, Double> validCourses = new HashMap<>();

        for (int i = 0; i < numberOfNG.length; i++) {
            if (NG == numberOfNG[i]) {
                validCourses.put(StudentIDStrings[i], numberOfNG[i]);
            }
        }
        return validCourses;
    }

    public Map<String, Double> getFilterByStudentNG(int lowerBound, int upperBound) {
        if (lowerBound > upperBound) {
            int temp = lowerBound;
            lowerBound = upperBound;
            upperBound = temp;
        }
        Map<String, Double> validCourses = new HashMap<>();

        for (int i = 0; i < numberOfNG.length; i++) {
            if (numberOfNG[i] >= lowerBound && numberOfNG[i] <= upperBound) {
                validCourses.put(StudentIDStrings[i], numberOfNG[i]);
            }
        }
        return validCourses;
    }

    public Map<String, Double> getFilterByStudentNG(double[] NGs) {
        Map<String, Double> validCourses = new HashMap<>();

        for (int i = 0; i < numberOfNG.length; i++) {
            for (double NG : NGs) {
                if (NG == numberOfNG[i]) {
                    validCourses.put(StudentIDStrings[i], numberOfNG[i]);
                    break;
                }
            }
        }

        return validCourses;
    }

    @Override
    public double[][] filterStudents(int value) {
        return new double[0][];
    }

    @Override
    public double[][] filterStudents(int lowerBound, int upperBound) {
        return new double[0][];
    }

    @Override
    public double[][] filterStudents(int[] value) {
        return new double[0][];
    }
}
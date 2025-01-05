package com.example.project11.ProjectInfo.Filters;

import java.util.*;

public class FilterByStudentGPA {

    private double[][] allStudents;


    public FilterByStudentGPA(double[][] allStudents) {


        this.allStudents = allStudents;
    }

    public double[][] filterStudents(double lowerBound, double upperBound) {
        if (lowerBound > upperBound) {
            double temp = upperBound;
            upperBound = lowerBound;
            lowerBound = temp;
        }

        List<double[]> filteredStudents = new ArrayList<>();

        for (double[] student : allStudents) {
            double gpa = calculateGPA(student);
            if (gpa < lowerBound || gpa > upperBound) {

                filteredStudents.add(student);
            }
        }

        return filteredStudents.toArray(new double[0][0]);
    }

    private double calculateGPA(double[] student) {
        double total = 0;
        int size = 0;
        for (double grade : student) {
            if (grade != -1) {
                total += grade;
                size++;
            }
        }

        if (size == 0) {
            return 0;
        }

        return total / size;
    }
}

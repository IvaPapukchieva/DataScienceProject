package com.example.project11.ProjectInfo.Filters;

import java.util.*;

public class FilterByGPA {

    private double[][] allStudents;

    public FilterByGPA(double[][] allStudents) {
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
            // Check if the GPA is outside the bounds
            if (gpa < lowerBound || gpa > upperBound) {
                // Add the student directly to the filtered list
                filteredStudents.add(student);
            }
        }

        // Convert List<double[]> back to double[][]
        return filteredStudents.toArray(new double[0][0]);
    }

    private double calculateGPA(double[] student) {
        double total = 0;
        int size = 0;
        for (double grade : student) { // Use primitive type here
            if (grade != -1) {
                total += grade;
                size++;
            }
        }

        if (size == 0) { // Prevent division by zero
            return 0; // Or some default value indicating invalid GPA
        }

        return total / size;
    }
}

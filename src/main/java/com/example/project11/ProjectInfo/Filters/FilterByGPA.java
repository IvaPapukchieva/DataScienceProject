package com.example.project11.ProjectInfo.Filters;
import com.example.project11.ProjectInfo.STEP1.*;

import com.example.project11.ProjectInfo.STEP1.CumLaude;

import java.util.*;

public class FilterByGPA {

    private double[][] allStudents;

    public FilterByGPA(double[][] allStudents) {
        this.allStudents = allStudents;
    }

    public double[][] filterStudents(double lowerBound, double upperBound) {
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
        for(Double grade : student) {
            if(grade != -1)  {
                total += grade;
                size++;
            }
        }

        return total/size;
    }
}



package com.example.project11.ProjectInfo.Filters;
import com.example.project11.ProjectInfo.STEP1.*;

import com.example.project11.ProjectInfo.STEP1.CumLaude;

import java.util.HashMap;
import java.util.Map;

public class FilterByGPA {

    private double[][] allStudents;
    private double[] GPA;

    public FilterByGPA(double[][] allStudents) {
        this.allStudents = allStudents;
        this.GPA = CalculateGPA();
    }

    public double[] filterBySpecificGPA(double grade) {
        double[] filteredStudents = new double[GPA.length];
        for (int i = 0; i < GPA.length; i++) {
            if (GPA[i] == grade) {
                filteredStudents[i] = GPA[i];
            } else {
                filteredStudents[i] = -1;
            }
        }
        return filteredStudents;
    }

    public double[] getStudentsByGPARange(int lowerBound, int upperBound) {
        double[] filteredStudents = new double[GPA.length];
        for (int i = 0; i < GPA.length; i++) {
            if (GPA[i] >= lowerBound && GPA[i] <= upperBound) {
                filteredStudents[i] = GPA[i];
            } else {
                filteredStudents[i] = -1;
            }
        }
        return filteredStudents;
    }

    public double[] getAllStudentsFilterByGrades(int[] gpa) {
        double[] filteredStudents = new double[GPA.length];
        for (int i = 0; i < GPA.length; i++) {
            boolean matches = false;
            for (int grade : gpa) {
                if (GPA[i] == grade) {
                    matches = true;
                    break;
                }
            }
            if (matches) {
                filteredStudents[i] = GPA[i];
            } else {
                filteredStudents[i] = -1;
            }
        }
        return filteredStudents;
    }

    public double[] CalculateGPA() {
        int subjectNumber = 33;
        double[] GPA = new double[allStudents.length];
        for (int i = 0; i < allStudents.length; i++) {
            double sum = 0;
            for (int j = 0; j < allStudents[i].length; j++) {
                sum += allStudents[i][j];
            }
            GPA[i] = sum / subjectNumber;
        }
        return GPA;
    }
}


package com.example.project11.ProjectInfo.Filters;
import com.example.project11.ProjectInfo.STEP1.*;

import com.example.project11.ProjectInfo.STEP1.CumLaude;

import java.util.HashMap;
import java.util.Map;

public class FilterByGPA {
    private double GPA [];
    private double[][] allStudents;


    public FilterByGPA(double[][] allStudents) {
        this.allStudents = allStudents;
        this.GPA = CalculateGPA();
    }

    public double[] filterBySpecificGPA(double grade){
        double[] filteredStudents = new double[GPA.length];
        for (int i = 0; i <GPA.length; i++) {
                if (GPA[i] != grade) {
                    filteredStudents[i] = -1;
                } else {
                    filteredStudents[i] = GPA[i];
                }
            }
        return filteredStudents;
    }

    public double[] getStudentsByGPARange(int lowerBound, int upperBound) {
        double[] filteredStudents = new double[GPA.length];
        for (int i = 0; i <GPA.length; i++) {
            if (GPA[i] >= lowerBound && GPA[i] <= upperBound) {
                filteredStudents[i] = -1;
            } else {
                filteredStudents[i] = GPA[i];
            }
        return filteredStudents;
    }


    public double[][] getAllStudentsFilterByGrades(int [] gpa) {
            double[] filteredStudents = new double[GPA.length];
            for (int i = 0; i <GPA.length; i++) {
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
            }
            return filteredStudents;


        }
        return filteredStudents;
    }

    public double[] CalculateGPA() {
        int subjectNumber = 33; // Number of subjects per student
        for (int i = 0; i < allStudents.length; i++) {
            double sum = 0;
            for (int j = 0; j < allStudents[i].length; j++) {
                sum += allStudents[i][j];
            }
            GPA[i] = sum / subjectNumber;
        }
        return GPA;
    }

    public Map<String, Integer> calculateStudentsGradeDistribution(double[][]filteredStudents) {
        String name;


        Map<String, Integer> gradeCounts = new HashMap<>();

        // Initialize grade ranges
        gradeCounts.put("0", 0);
        gradeCounts.put("1", 0);
        gradeCounts.put("2", 0);
        gradeCounts.put("3", 0);
        gradeCounts.put("4", 0);
        gradeCounts.put("5", 0);
        gradeCounts.put("6", 0);
        gradeCounts.put("7", 0);
        gradeCounts.put("8", 0);
        gradeCounts.put("9", 0);
        gradeCounts.put("10", 0);

        for (double[] studentGrades : filteredStudents) {
            for (double grade : studentGrades) {
                String range = getGradeRange(grade);
                gradeCounts.put(range, gradeCounts.get(range) + 1);
            }
        }

        return gradeCounts;
    }


    private String getGradeRange(double grade) {
        if (grade == 10) return "10";
        if (grade >= 9) return "9";
        if (grade >= 8) return "8";
        if (grade >= 7) return "7";
        if (grade >= 6) return "6";
        if (grade >= 5) return "5";
        if (grade >= 4) return "4";
        if (grade >= 3) return "3";
        if (grade >= 2) return "2";
        if (grade >= 1) return "1";
        return "0";


    }




}}}


package com.example.project11.ProjectInfo.Filters;
import com.example.project11.ProjectInfo.STEP1.*;

import com.example.project11.ProjectInfo.STEP1.CumLaude;

import java.util.HashMap;
import java.util.Map;

public class FilterByGPA extends CumLaude {


    public FilterByGPA(double[][] allStudents

    ) {
        super(allStudents);
    }



    public double[][] filterByGPA(double GPA){

        double[][] filteredStudents = new double[allStudents.length][allStudents[0].length];
        for (int i = 0; i < allStudents.length; i++) {
            for (int j = 0; j < allStudents[i].length; j++) {
                if (allStudents[i][j] != GPA) {
                    filteredStudents[i][j] = -1;
                } else {
                    filteredStudents[i][j] = allStudents[i][j];
                }
            }
        }

        return filteredStudents;
    }

    public double[][] getStudentsByGPARange(int lowerBound, int upperBound) {
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

    public double[][] getAllStudentsFilterByGrades(int[] gpa) {
        double[][] filteredStudents = new double[allStudents.length][allStudents[0].length];
        for (int i = 0; i < allStudents.length; i++) {
            for (int j = 0; j < allStudents[0].length; j++) {
                boolean matches = false;
                for (int grade : gpa) {
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




}



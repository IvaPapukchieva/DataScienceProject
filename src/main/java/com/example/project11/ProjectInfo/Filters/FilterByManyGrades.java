package com.example.project11.ProjectInfo.Filters;

import java.util.HashMap;
import java.util.Map;

public class FilterByManyGrades {

    private int[] grades;
    private double [][] allStudents;

    public FilterByManyGrades(int[] grades, double [][] allStudents) {
        this.grades = grades;
        this.allStudents = allStudents;

    }

    public double[][] getAllStudentsFilterByGrades() {
        double[][] filteredStudents = new double[allStudents.length][allStudents[0].length];
        for (int i = 0; i < allStudents.length; i++) {
            for (int j = 0; j < allStudents[0].length; j++) {
                boolean matches = false;
                for (int grade : grades) {
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

    public Map<String, Integer> calculateStudentsGradeDistribution() {
        String name;
        double [][] StudentsGrades=getAllStudentsFilterByGrades();


        Map<String, Integer> gradeCounts = new HashMap<>();

        for(int Grade:grades) {
            gradeCounts.put(String.valueOf(Grade), 0);
        }
        gradeCounts.put("Out of the range", 0);

        for (double[] studentGrades :  StudentsGrades) {
            for (double grade : studentGrades) {
                if (grade != -1){
                String range = getGradeRange(grade);
                gradeCounts.put(range, gradeCounts.get(range) + 1);
            }}
        }

        return gradeCounts;
    }


    private String getGradeRange(double grade) {

        for(int Grade:grades){
            if (grade == Grade) return String.valueOf(Grade);
        }
        return "Out of the range";

    }
}

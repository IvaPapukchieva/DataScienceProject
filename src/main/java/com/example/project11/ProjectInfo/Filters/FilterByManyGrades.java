package com.example.project11.ProjectInfo.Filters;

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
}

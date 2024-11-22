package com.example.project11.ProjectInfo.Filters;


import java.util.Arrays;
import java.util.stream.IntStream;

public class FilterBySpecificGrade {
    private double[][] allStudents;
    private int grade;

    public void FilterByGrade(int grade, double[][] allStudents) {
        this.grade = grade;
        this.allStudents = allStudents;


    }

    public double[][] FilterByGrade(){

        double[][] filteredStudents = new double[allStudents.length][allStudents[0].length];
        for (int i = 0; i < allStudents.length; i++) {
            for (int j = 0; j < allStudents[i].length; j++) {
                if (allStudents[i][j] != grade) {
                    filteredStudents[i][j] = -1;
                } else {
                    filteredStudents[i][j] = allStudents[i][j];
                }
            }
        }

        return filteredStudents;
    }






}




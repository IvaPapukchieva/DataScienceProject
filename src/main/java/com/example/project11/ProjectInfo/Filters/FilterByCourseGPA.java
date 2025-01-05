package com.example.project11.ProjectInfo.Filters;

import java.util.HashMap;
import java.util.Map;

public class FilterByCourseGPA {

    private final double [][] allStudents;
    private final int []  averageByCourse;


    public FilterByCourseGPA(double[][] allStudents){
        this.allStudents=allStudents;
        this.averageByCourse=calculateCourseGPA();
    }


    public double[][] filterStudents(int grade) {
        double[][] filteredStudents = new double[averageByCourse.length][2];
        for (int i = 0; i < averageByCourse.length; i++) {
            filteredStudents[i][0] = i;
            if (averageByCourse[i] == grade) {
                filteredStudents[i][1] = grade;
            } else {
                filteredStudents[i][1] = -1;
            }
        }
        return filteredStudents;
    }

    public double[][] filterStudents(int lowerBound, int upperBound) {
        if (lowerBound > upperBound) {
            int temp = lowerBound;
            lowerBound = upperBound;
            upperBound = temp;
        }

        double[][] filteredStudents = new double[averageByCourse.length][2];

        for (int i = 0; i < averageByCourse.length; i++) {
            filteredStudents[i][0] = i;
            if (averageByCourse[i] >= lowerBound && averageByCourse[i] <= upperBound) {
                filteredStudents[i][1] = averageByCourse[i];
            } else {
                filteredStudents[i][1] = -1;
            }
        }

        return filteredStudents;
    }


    public double[][] filterStudents(int[] grades) {
        double[][] filteredStudents = new double[averageByCourse.length][2];
        for (int i = 0; i < averageByCourse.length; i++) {
            filteredStudents[i][0] = i;
            boolean matchFound = false;
            for (int grade : grades) {
                if (averageByCourse[i] == grade) {
                    filteredStudents[i][1] = grade;
                    matchFound = true;
                    break;
                }
            }

            if (!matchFound) {filteredStudents[i][1] = -1;
            }
        }

        return filteredStudents;
    }


    public int[] calculateCourseGPA() {
        int[] averageByCourse = new int[allStudents[0].length];

        for (int j = 0; j < allStudents[0].length; j++) {
            double sum = 0;
            for (int i = 0; i < allStudents.length; i++) {
                sum += allStudents[i][j];
            }

            double average = sum / allStudents.length;

            if (average - (int) average >= 0.5) {
                averageByCourse[j] = (int) average + 1;
            } else {
                averageByCourse[j] = (int) average;
            }
        }

        return averageByCourse;
    }

}

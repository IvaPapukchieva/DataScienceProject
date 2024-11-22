package com.example.project11.ProjectInfo.Filters;

import java.util.Arrays;
import java.util.stream.IntStream;

public class FilterBySpecificCourse {
    private int courseIndex;
    private double [][]allStudents;

    public FilterBySpecificCourse (int courseIndex, double [][]allStudents) {
        this.courseIndex = courseIndex;
        this.allStudents = allStudents;
    }

    public double [][] getSpecificCourse() {
        double[][] filteredStudents = new double[allStudents.length][0];
       for (int i = 0; i < allStudents.length; i++) {
         filteredStudents[i][courseIndex] = allStudents[i][courseIndex];
       }
       return filteredStudents;
    }



}

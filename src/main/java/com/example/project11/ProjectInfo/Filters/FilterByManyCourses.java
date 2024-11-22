package com.example.project11.ProjectInfo.Filters;
import java.util.Arrays;
import java.util.stream.IntStream;


import java.lang.reflect.Array;

public class FilterByManyCourses {


        private int[] courses;
        private double [][] allStudents;

        public FilterByManyCourses(int[] courses, double [][] allStudents) {
            this.courses = courses;
            this.allStudents = allStudents;

        }

        public double[][] getAllStudentsFilterByGrades() {
            double[][] filteredStudents = new double[allStudents.length][courses.length];
         for (int i = 0; i < allStudents.length; i++) {
             for (int j = 0 ; j < courses.length; j++) {
                 filteredStudents[i][j] = allStudents[i][j];

             }
         }
         return filteredStudents;
    }
}

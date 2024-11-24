package com.example.project11.ProjectInfo.Filters;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;


import java.lang.reflect.Array;

public class FilterByManyCourses {


        private int[] courses;
        private double [][] allStudents;

        public FilterByManyCourses(int[] courses, double [][] allStudents) {
            this.courses = courses;
            this.allStudents = allStudents;

        }

        public double[][] getAllStudentsFilterCourse() {
            double[][] filteredStudents = new double[allStudents.length][courses.length];
         for (int i = 0; i < allStudents.length; i++) {
             for (int j = 0 ; j < courses.length; j++) {
                 filteredStudents[i][j] = allStudents[i][j];

             }
         }
         return filteredStudents;
    }
    public Map<String, Integer> calculateCourseGradeDistribution() {

        double [][] courses=getAllStudentsFilterCourse();
        Map<String, Integer> gradeCounts = new HashMap<>();

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

        for (double[] studentGrades : courses) {
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

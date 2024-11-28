package com.example.project11.ProjectInfo;

import java.util.HashMap;
import java.util.Map;

public class GradeDistributionCalculator {


    public Map<String, Integer> calculateGradeDistribution(double [][] allStudents) {

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
        for (double[] studentGrades : allStudents) {
            for (double grade : studentGrades) {
                if (grade == -1) continue;
                String range = getGradeRange(grade);
                gradeCounts.put(range, gradeCounts.get(range) + 1);
            }
        }

        return gradeCounts;
    }


    public Map<String, Integer> calculateStudentsGradeDistribution(double []allStudents) {

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

            for (double grade : allStudents){
                if (grade == -1) continue;
                String range = getGradeRange(grade);
                gradeCounts.put(range, gradeCounts.get(range) + 1);
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

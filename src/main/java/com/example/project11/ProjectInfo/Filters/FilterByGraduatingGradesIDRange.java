package com.example.project11.ProjectInfo.Filters;

import com.example.project11.ProjectInfo.loaders.CurrentGradeLoader;
import com.example.project11.ProjectInfo.loaders.GraduatingGradesLoader;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class FilterByGraduatingGradesIDRange extends GraduatingGradesLoader {
    private int lowerBound;
    private int upperBound;
    private double[][] allStudents;

    public FilterByGraduatingGradesIDRange(int lowerBound, int upperBound, double[][] allStudents) throws FileNotFoundException {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.allStudents = allStudents;
    }


    public double[][] getStudentsGradesByIdRange() {
        List<double[]> resultsList = IntStream.range(0, StudentID.length)
                .filter(i -> StudentID[i] >= lowerBound && StudentID[i] <= upperBound)
                .mapToObj(i -> allStudents[i])
                .toList();

        double[][] results = new double[resultsList.size()][];
        for (int i = 0; i < resultsList.size(); i++) {
            results[i] = resultsList.get(i);
        }

        return results;
    }

    public Map<String, Integer> calculateStudentsGradeDistribution() {
        String name;
        double[][] StudentsResults = getStudentsGradesByIdRange();


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

        for (double[] studentGrades : StudentsResults) {
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
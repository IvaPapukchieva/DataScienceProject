package com.example.project11.ProjectInfo.Filters;
import java.util.ArrayList;
import java.util.List;

import com.example.project11.ProjectInfo.loaders.CurrentGradeLoader;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;


public class FilterByCurrentGradesID  extends CurrentGradeLoader {

private int id;
private int []ID;
    public FilterByCurrentGradesID(int id, double [][] allStudents ) throws FileNotFoundException {
        super();
        this.id = id;

    }

        public double[] getStudentGradesById() {
            // Find the index of the student ID
            return IntStream.range(0, StudentID.length)
                    .filter(i -> StudentID[i] == id)
                    .mapToObj(i -> allStudents[i])
                    .findFirst()
                    .orElse(new double[0]);
        }
        public double[][] getStudentsGradesById(int[] ID) {
        this.ID = ID;
            double[][] results = new double[ID.length][33];

            for (int j = 0; j < ID.length; j++) {
                int id = ID[j];

                results[j] = IntStream.range(0, StudentID.length)
                        .filter(i -> StudentID[i] == id)
                        .mapToObj(i -> allStudents[i])
                        .findFirst()
                        .orElse(new double[0]);
            }return results;


        }

    public Map<String, Integer> calculateStudentGradeDistribution() {
        String name;
        double [] StudentResults=getStudentGradesById();


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


        for (double grade : StudentResults) {
            String range = getGradeRange(grade); // Helper method to determine range
            gradeCounts.put(range, gradeCounts.get(range) + 1);
        }

        return gradeCounts;
    }
    public Map<String, Integer> calculateStudentsGradeDistribution() {
        String name;
        double [][] StudentsResults=getStudentsGradesById(ID);


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




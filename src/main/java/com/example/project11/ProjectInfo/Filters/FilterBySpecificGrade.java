package com.example.project11.ProjectInfo.Filters;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class FilterBySpecificGrade {
    private double[][] allStudents;
    private int grade;
    private String label;

    public  FilterBySpecificGrade(int grade, double[][] allStudents) {
        this.grade = grade;
        this.allStudents = allStudents;



    }

    public double[][] filterByGrade(){

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
    public String getLabel(){
        return String.valueOf(grade);
    }

    public Map<String, Integer> calculateStudentsGradeDistribution() {
        String name;
        double [][]  filteredGrades=filterByGrade();


        Map<String, Integer> gradeCounts = new HashMap<>();


            gradeCounts.put(String.valueOf(grade), 0);
            gradeCounts.put("Out of the range", 0);

        for (double[] studentGrades : filteredGrades) {
            for (double studentGrade : studentGrades) {
                if (studentGrade == this.grade) {
                    // Increment count for the specific grade
                    gradeCounts.put(String.valueOf(this.grade), gradeCounts.get(String.valueOf(this.grade)) + 1);
                } else if (studentGrade == -1) {
                    gradeCounts.put("Out of the range", gradeCounts.get("Out of the range") + 1);
                }
            }
        }

        return gradeCounts;
    }


    private String getGradeRange(double grade) {
        if (grade == this.grade) return String.valueOf(grade);
        return "Out of the range";

    }




}




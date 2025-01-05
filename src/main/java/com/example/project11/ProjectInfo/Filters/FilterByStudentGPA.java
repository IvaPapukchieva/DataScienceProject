package com.example.project11.ProjectInfo.Filters;

import java.util.*;

public class FilterByStudentGPA implements Filter {
//    filters so u can see the grades of the students with the GPA
//it does not work/ no window to pick a filter

    private double[][] allStudents;


    public FilterByStudentGPA(double[][] allStudents) {


        this.allStudents = allStudents;
    }

    public double[][] filterStudents(int GPA) {
        List<double[]> filteredStudents = new ArrayList<>();

        for (double[] student : allStudents) {
            int gpa = calculateGPA(student);
            if (gpa ==GPA) {

                filteredStudents.add(student);
            }
        }

        return filteredStudents.toArray(new double[0][0]);
    }



    public double[][] filterStudents(int lowerBound, int upperBound) {
        if (lowerBound > upperBound) {
            int temp = lowerBound;
            lowerBound = upperBound;
            upperBound = temp;
        }
        List<double[]> filteredStudents = new ArrayList<>();

        for (double[] student : allStudents) {
            int gpa = calculateGPA(student);
            if (gpa >= lowerBound && gpa <= upperBound) {

                filteredStudents.add(student);
            }
        }

        return filteredStudents.toArray(new double[0][0]);
    }


    public double[][] filterStudents(int[] GPAs) {

        List<double[]> filteredStudents = new ArrayList<>();

        for (double[] student : allStudents) {
            int gpa = calculateGPA(student);
            for (int GPA : GPAs) {
                if (GPA == gpa) {

                    filteredStudents.add(student);
                    break;
                }
            }


        }
        return filteredStudents.toArray(new double[0][0]);

    }


    private int calculateGPA(double[] student) {
        double total = 0;
        int count = 0;

        for (double grade : student) {
            if (grade != -1) {
                total += grade;
                count++;
            }
        }

        if (count == 0) return 0;

        double GPA = total / count;
        return (int) Math.round(GPA);
    }
}










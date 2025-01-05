package com.example.project11.ProjectInfo.Filters;

public interface Filter {


    double [][] filterStudents(int value);
    double [][] filterStudents(int lowerBound, int upperBound);
    double [][] filterStudents(int[] value);

}
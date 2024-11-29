package com.example.project11.ProjectInfo.Filters;

import com.example.project11.ProjectInfo.loaders.CurrentGradeLoader;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class FilterByCurrentGradesIDRange extends CurrentGradeLoader {
    private int lowerBound;
    private int upperBound;
    private double[][] allStudents;

    public FilterByCurrentGradesIDRange(int lowerBound, int upperBound, double[][] allStudents) throws FileNotFoundException {
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





}

package com.example.project11.ProjectInfo.Filters;

import com.example.project11.ProjectInfo.loaders.GraduatingGradesLoader;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class FilterByGraduateGradesID extends GraduatingGradesLoader {

    public FilterByGraduateGradesID() throws FileNotFoundException {
        super();



    }
    public double[] filterStudents(int id) {

        // Find the index of the student ID
        return IntStream.range(0, StudentID.length)
                .filter(i -> StudentID[i] == id)
                .mapToObj(i -> allStudents[i])
                .findFirst()
                .orElse(new double[0]);
    }
    public double[][] filterStudents(int[] ID) {
        double[][] results = new double[ID.length][33];

        for (int j = 0; j < ID.length; j++) {
            int id = ID[j];

            results[j] = IntStream.range(0, StudentID.length)
                    .filter(i -> StudentID[i] == id)
                    .mapToObj(i -> allStudents[i])
                    .findFirst()
                    .orElse(new double[0]);
        }
        return results;
    }
    public double[][] filterStudents(int lowerBound, int upperBound) {
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

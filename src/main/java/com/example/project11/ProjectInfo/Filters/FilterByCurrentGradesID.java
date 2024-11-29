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
private int[] ID;

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
            }
            return results;


        }

}




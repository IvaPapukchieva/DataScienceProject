package com.example.project11.ProjectInfo.loaders;

import java.io.FileNotFoundException;
import java.util.stream.IntStream;


public class FilterByID {


    public FilterByID(){


    }
    public static class FilterByStudentIDGraduatingGradesLoader extends GraduatingGradesLoader {

        public FilterByStudentIDGraduatingGradesLoader() throws FileNotFoundException {
            super();

        }

        public double[] getStudentGradesById(int id) {
            // Find the index of the student ID
            return IntStream.range(0, StudentID.length)
                    .filter(i -> StudentID[i] == id)
                    .mapToObj(i -> allStudents[i])
                    .findFirst()
                    .orElse(new double[0]);
        }


    }
    public static class FilterByStudentICurrentGradesLoader extends CurrentGradeLoader {

        public FilterByStudentICurrentGradesLoader() throws FileNotFoundException {
            super();

        }

        public double[] getStudentGradesById(int id) {
            // Find the index of the student ID
            return IntStream.range(0, StudentID.length)
                    .filter(i -> StudentID[i] == id)
                    .mapToObj(i -> allStudents[i])
                    .findFirst()
                    .orElse(new double[0]);
        }


    }
    public static class FilterByStudentIDWeightedBootstrappingGradesLoader extends WeightedBootstrapping {

        public FilterByStudentIDWeightedBootstrappingGradesLoader() throws FileNotFoundException {
            super();

        }

        public double[] getStudentGradesById(int id) {
            // Find the index of the student ID
            return IntStream.range(0, StudentID.length)
                    .filter(i -> StudentID[i] == id)
                    .mapToObj(i -> allStudents[i])
                    .findFirst()
                    .orElse(new double[0]);
        }


    }
    public static class FilterByStudentIDCurrentGradeLoaderNG extends CurrentGradeLoaderNG {

        public FilterByStudentIDCurrentGradeLoaderNG() throws FileNotFoundException {
            super();

        }

        public double[] getStudentGradesById(int id) {
            // Find the index of the student ID
            return IntStream.range(0, StudentID.length)
                    .filter(i -> StudentID[i] == id)
                    .mapToObj(i -> allStudents[i])
                    .findFirst()
                    .orElse(new double[0]);
        }





    }

}

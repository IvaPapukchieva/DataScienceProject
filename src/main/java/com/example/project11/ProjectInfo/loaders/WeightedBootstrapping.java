package com.example.project11.ProjectInfo.loaders;


import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Arrays;

public class WeightedBootstrapping implements Loader {

    private String fileName;
    private int numRows;
    private int numCols;
   public double[][] allStudents;
   public int [] StudentID;
    private int course ;


    public WeightedBootstrapping( ) throws FileNotFoundException {
        CurrentGradeLoader currentGradeLoader = new CurrentGradeLoader();
       this.allStudents = currentGradeLoader.readAllStudents();
        this.numRows = 1329;
        this.numCols = 33;
        this.course = course ;
        this.allStudents = readAllStudents();
        this.StudentID = currentGradeLoader.setStudentID();


    }

    public double[][] readAllStudents() throws FileNotFoundException {


        double[][] allCoursesCount = new double[33][11];


        for (int j = 0; j < allStudents[0].length; j++) {
            double [] ranges = reps(allStudents, j ) ;
            int sum = 0  ;
            for (int c = 0; c < ranges.length; c++) {
                sum += ranges[c];

            }
            for (int i = 0; i < allStudents.length; i++) {



                if(allStudents[i][j]!=-1){
                    continue;
                }

                int random = (int) (Math.random() * sum);
                if (allStudents[i][j] == -1.0) {
                    if (random < ranges[0] && random >= 0) {
                        allStudents[i][j] = 0.0;
                    }
                    if (random < ranges[1] && random >= 0) {
                        allStudents[i][j] = 1.0;
                    }
                    if (random < ranges[2] && random >= 0) {
                        allStudents[i][j] = 2.0;
                    }
                    if (random <= ranges[3] + ranges[2] && random >= ranges[2]) {
                        allStudents[i][j] = 3.0;

                    }
                    if (random <= ranges[4] + ranges[3] + ranges[2] && random >= ranges[3] + ranges[2]) {
                        allStudents[i][j] = 4.0;
                    }
                    if (random <= ranges[5] + ranges[4] + ranges[3] + ranges[2] && random >= ranges[4] + ranges[3] + ranges[2]) {
                        allStudents[i][j] = 5.0;
                    }
                    if (random <= ranges[6] + ranges[5] + ranges[4] + ranges[3] + ranges[2] && random >= ranges[5] + ranges[4] + ranges[3] + ranges[2]) {
                        allStudents[i][j] = 6.0;
                    }
                    if (random <= ranges[7] + ranges[6] + ranges[5] + ranges[4] + ranges[3] + ranges[2] && random >= ranges[6] + ranges[5] + ranges[4] + ranges[3] + ranges[2]) {
                        allStudents[i][j] = 7.0;
                    }
                    if (random <= ranges[8] + ranges[7] + ranges[6] + ranges[5] + ranges[4] + ranges[3] + ranges[2] && random > ranges[7] + ranges[6] + ranges[5] + ranges[4] + ranges[3] + ranges[2]) {
                        allStudents[i][j] = 8.0;
                    }
                    if (random <= ranges[9] + ranges[8] + ranges[7] + ranges[6] + ranges[5] + ranges[4] + ranges[3] + ranges[2] && random >= ranges[8] + ranges[7] + ranges[6] + ranges[5] + ranges[4] + ranges[3] + ranges[2]) {
                        allStudents[i][j] = 9.0;
                    }
                    if (random <= ranges[10] + ranges[9] + ranges[8] + ranges[7] + ranges[6] + ranges[5] + ranges[4] + ranges[3] + ranges[2] && random > ranges[9] + ranges[8] + ranges[7] + ranges[6] + ranges[5] + ranges[4] + ranges[3] + ranges[2]) {
                        allStudents[i][j] = 10.0;
                    }


                }

            }


        }
        return allStudents;

    }





    public double[] reps(double[][] allStudents, int classc) {
        double[] course = new double[11];

        for (int j = 1; j < allStudents.length; j++) {

            if(allStudents[j][classc] !=-1) {
                course[(int) allStudents[j][classc]]++;
            }

        }

        //System.out.println(Arrays.toString(course));
        return course;
    }



    }



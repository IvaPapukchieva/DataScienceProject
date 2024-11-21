package com.example.project11.ProjectInfo.loaders;


import java.io.FileNotFoundException;

public class WeightedBootstrapping {

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
            for (int i = 0; i < allStudents.length; i++) {


                double [] ranges = reps(allStudents, j ) ;
                int sum = 0  ;
                for (int c = 0; c < ranges.length; c++) {
                    sum += ranges[c];

                }

                int random = (int) (Math.random() * sum);
                if (allStudents[i][j] == -1.0) {
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


        int num0 = 0;
        int num1 = 0;
        int num2 = 0;
        int num3 = 0;
        int num4 = 0;
        int num5 = 0;
        int num6 = 0;
        int num7 = 0;
        int num8 = 0;
        int num9 = 0;
        int num10 = 0;


        for (int j = 0; j < allStudents.length; j++) {
            if (allStudents[j][classc] == 0) {
                course[0] = num0++;
            }
            if (allStudents[j][classc] == 1) {
                course[1] = num1++;
            }
            if (allStudents[j][classc] == 2) {
                course[2] = num2++;
            }
            if (allStudents[j][classc] == 3) {
                course[3] = num3++;
            }
            if (allStudents[j][classc] == 4) {
                course[4] = num4++;
            }
            if (allStudents[j][classc] == 5) {
                course[5] = num5++;
            }
            if (allStudents[j][classc] == 6) {
                course[6] = num6++;
                ;
            }
            if (allStudents[j][classc] == 7) {
                course[7] = num7++;
            }
            if (allStudents[j][classc] == 8) {
                course[8] = num8++;
            }
            if (allStudents[j][classc] == 9) {
                course[9] = num9++;
            }
            if (allStudents[j][classc] == 10) {
                course[10] = num10++;
            } else {

            }

        }
        return course;
    }



    }


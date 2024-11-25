

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class WeightedBootstrapping {

    private String fileName;
    private int numRows;
    private int numCols;
    private double[][] allStudents;
    private int course ; 

    public WeightedBootstrapping( int course , double[][] allStudents) {
        this.fileName = "C:\\Users\\marco\\Documents\\Project 1-1\\CODE\\STEP3 2\\Step3\\CurrentGrades.csv";
        this.numRows = 1329;
        this.numCols = 33;
        this.course = course ; 
        this.allStudents = allStudents ; 



    }

    public double[][] readAllStudents() throws FileNotFoundException {
        

        double[][] allCoursesCount = new double[33][11];

        
        double [] ranges = reps(allStudents, course ) ;


        int sum = 0  ; 
        for (int i = 0; i < ranges.length; i++) {
            sum += ranges[i];

        }


        for (int i = 0; i < allStudents.length; i++) {
            for (int j = 0; j < allStudents[0].length; j++) {
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

        for( int i = 0 ; i<10 ; i++){
            
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

package com.example.project11.ProjectInfo.STEP1;//package CODE ;

import java.util.ArrayList;

/**This program calculates GPAs, finds students eligible for honors by comparing their GPA values to
 * predefined thresholds for each honor level, and identifies the students
 * with the highest and lowest GPAs and prints their information */

public class CumLaude {
    private  double[] GPA;
    private double CumLaude;
    private double MagnaCumLaude;
    private double SummaCumLaude;
    private double[][] allStudents;


    public CumLaude(double[][] allStudents) {
        this.GPA = new double [allStudents.length];
        this.allStudents = new double [allStudents.length][allStudents[0].length];
        this.allStudents = allStudents;



    }

    public void FindCumLaudeStudents() {
        GPA = CalculateGPA();  // Calculate GPAs
        FindCumLaude();

        ArrayList<Integer> cumLaudeList = new ArrayList<>();
        ArrayList<Integer> magnaCumLaudeList = new ArrayList<>();
        ArrayList<Integer> summaCumLaudeList = new ArrayList<>();

        // Classify students based on GPA thresholds
        for (int i = 0; i < GPA.length; i++) {
            if (GPA[i] >= SummaCumLaude) {
                summaCumLaudeList.add(i);
            } else if (GPA[i] >= MagnaCumLaude) {
                magnaCumLaudeList.add(i);
            } else if (GPA[i] >= CumLaude) {
                cumLaudeList.add(i);
            }
        }


        // Output the lists of students in each honor category
        System.out.println("Cum Laude List: " + cumLaudeList);
        System.out.println("Magna Cum Laude List: " + magnaCumLaudeList);
        System.out.println("Summa Cum Laude List: " + summaCumLaudeList);
    }

    // Method to calculate GPAs from allStudents
    public double[] CalculateGPA() {
        int subjectNumber = 33;
        for (int i = 0; i < allStudents.length; i++) {
            double sum = 0;
            for (int j = 0; j < allStudents[i].length; j++) {
                sum += allStudents[i][j];
            }
            GPA[i] = sum / subjectNumber;
        }



        return GPA;
    }

    // Method to calculate Cum Laude, Magna Cum Laude, and Summa Cum Laude thresholds
    public void FindCumLaude() {
        // Sorting the GPA array in ascending order
        double[] GPAcopy = new double[GPA.length];
        System.arraycopy(GPA, 0, GPAcopy, 0, GPA.length);  // Copy GPA array
        double currentGPA;
        for (int i = 0; i < GPAcopy.length - 1; i++) {
            for (int j = 0; j < GPAcopy.length - 1 - i; j++) {
                if (GPAcopy[j] > GPAcopy[j + 1]) {
                    // Swap elements if they are in the wrong order
                    currentGPA = GPAcopy[j];
                    GPAcopy[j] = GPAcopy[j + 1];
                    GPAcopy[j + 1] = currentGPA;
                }
            }
        }



        CumLaude = GPAcopy[(int) (0.80 * GPA.length)];  // 80th percentile
        MagnaCumLaude = GPAcopy[(int) (0.90 * GPA.length)];  // 90th percentile
        SummaCumLaude = GPAcopy[(int) (0.95 * GPA.length)];


        System.out.println("Cum Laude GPA: " + String.format("%.1f", CumLaude));
        System.out.println("Magna Cum Laude GPA: " + String.format("%.1f", MagnaCumLaude));
        System.out.println("Summa Cum Laude GPA: " + String.format("%.1f", SummaCumLaude));


    }
}


package com.example.project11.ProjectInfo.STEP1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** This program calculates GPAs, finds students eligible for honors by comparing their GPA values to
 * predefined thresholds for each honor level, and identifies the students
 * with the highest and lowest GPAs, storing results in a HashMap. */

public class CumLaude {
    private double[] GPA;
    private double CumLaude;
    private double SummaCumLaude;
    private double MagnaCumLaude;
    protected double[][] allStudents;
    private Map<String,Integer>honorsMap;

    public CumLaude(double[][] allStudents) {

        this.GPA = new double[allStudents.length];
        this.allStudents = allStudents;
        this.honorsMap = new HashMap<>();
    }
    public Map<String, Integer> getHonorsMap() {
        FindCumLaudeStudents();
        return honorsMap;
    }
    public void FindCumLaudeStudents() {
        GPA = CalculateGPA();
        FindCumLaude();


        ArrayList<Integer> cumLaudeList = new ArrayList<>();
        ArrayList<Integer> magnaCumLaudeList = new ArrayList<>();
        ArrayList<Integer> summaCumLaudeList = new ArrayList<>();
        for (int i = 0; i < GPA.length; i++) {
            if (GPA[i] >= SummaCumLaude) {
                summaCumLaudeList.add(i);
            } else if (GPA[i] >= MagnaCumLaude) {
                magnaCumLaudeList.add(i);
            } else if (GPA[i] >= CumLaude) {
                cumLaudeList.add(i);
            }
        }

        double cumLaude= cumLaudeList.size();
        double magnaCumLaude=summaCumLaudeList.size();
        double summaCumLaude=summaCumLaudeList.size();

        honorsMap.put("Cum-Laude",(int)cumLaude);
        honorsMap.put("Magna Cum-Laude",(int)magnaCumLaude);
        honorsMap.put("Summa Cum-Laude",(int)summaCumLaude);


    }

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

    public void FindCumLaude() {
        double[] GPAcopy = new double[GPA.length];
        System.arraycopy(GPA, 0, GPAcopy, 0, GPA.length); // Copy GPA array
        java.util.Arrays.sort(GPAcopy); // Sort in ascending order

        CumLaude = GPAcopy[(int) (0.80 * GPA.length)]; // 80th percentile
        MagnaCumLaude = GPAcopy[(int) (0.90 * GPA.length)]; // 90th percentile
        SummaCumLaude = GPAcopy[(int) (0.95 * GPA.length)]; // 95th percentile

        System.out.println("Cum Laude GPA: " + String.format("%.1f", CumLaude));
        System.out.println("Magna Cum Laude GPA: " + String.format("%.1f", MagnaCumLaude));
        System.out.println("Summa Cum Laude GPA: " + String.format("%.1f", SummaCumLaude));
    }
}

package com.example.project11.ProjectInfo.STEP1;

import java.util.ArrayList;
import java.util.HashMap;

/** This program calculates GPAs, finds students eligible for honors by comparing their GPA values to
 * predefined thresholds for each honor level, and identifies the students
 * with the highest and lowest GPAs, storing results in a HashMap. */

public class CumLaude {
    private double[] GPA;
    private double CumLaude;
    private double MagnaCumLaude;
    private double SummaCumLaude;
    private double[][] allStudents;
    private HashMap<String, HashMap<Integer, Double>> honorsMap; // Map to store honor categories and students

    public CumLaude(double[][] allStudents) {
        this.GPA = new double[allStudents.length];
        this.allStudents = new double[allStudents.length][allStudents[0].length];
        this.allStudents = allStudents;
        this.honorsMap = new HashMap<>(); // Initialize the map
    }

    public void FindCumLaudeStudents() {
        GPA = CalculateGPA(); // Calculate GPAs
        FindCumLaude();

        // HashMaps for each category
        HashMap<Integer, Double> cumLaudeMap = new HashMap<>();
        HashMap<Integer, Double> magnaCumLaudeMap = new HashMap<>();
        HashMap<Integer, Double> summaCumLaudeMap = new HashMap<>();

        // Classify students based on GPA thresholds
        for (int i = 0; i < GPA.length; i++) {
            if (GPA[i] >= SummaCumLaude) {
                summaCumLaudeMap.put(i, GPA[i]);
            } else if (GPA[i] >= MagnaCumLaude) {
                magnaCumLaudeMap.put(i, GPA[i]);
            } else if (GPA[i] >= CumLaude) {
                cumLaudeMap.put(i, GPA[i]);
            }
        }

        // Store results in the main honors map
        honorsMap.put("Cum Laude", cumLaudeMap);
        honorsMap.put("Magna Cum Laude", magnaCumLaudeMap);
        honorsMap.put("Summa Cum Laude", summaCumLaudeMap);

        // Output the honors map
        printHonorsMap();
    }

    private void printHonorsMap() {
        System.out.println("Cum Laude Map: " + honorsMap.get("Cum Laude"));
        System.out.println("Magna Cum Laude Map: " + honorsMap.get("Magna Cum Laude"));
        System.out.println("Summa Cum Laude Map: " + honorsMap.get("Summa Cum Laude"));
    }

    // Method to calculate GPAs from allStudents
    public double[] CalculateGPA() {
        int subjectNumber = 33; // Number of subjects per student
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

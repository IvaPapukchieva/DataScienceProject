package com.example.project11.ProjectInfo.STEP1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SimilarClasses2 {

    private double[][] allStudents;
    private HashMap<String, Double> correlationMap;

    public SimilarClasses2(double[][] allStudents) {
        this.allStudents = allStudents;
        this.correlationMap = new HashMap<>();
    }

    public void calculateSimilarClasses() {
        try {
            // Compute correlation and populate the HashMap
            correlationMatrix(allStudents);

            // Print the correlation map for verification
            printCorrelationMap();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Populates the correlation map with class pair correlations
    public void correlationMatrix(double[][] allStudents) {
        for (int row = 0; row < allStudents[0].length; row++) {
            for (int col = 0; col < allStudents[0].length; col++) {
                String key = "Class " + (row + 1) + " & Class " + (col + 1);
                double correlation = correlationBetweenClasses(allStudents, row, col);
                correlationMap.put(key, correlation);
            }
        }
    }

    // Calculate the correlation between class A and class B
    public static double correlationBetweenClasses(double[][] allStudents, int classA, int classB) {
        ArrayList<ArrayList<Double>> inAandB = studentsInClassAandB(allStudents, classA, classB);

        double meanA = classMean(inAandB, true);
        double meanB = classMean(inAandB, false);

        double summation1 = 0, summation2 = 0, summation3 = 0;
        for (int i = 0; i < inAandB.get(0).size(); i++) {
            summation1 += (inAandB.get(0).get(i) - meanA) * (inAandB.get(1).get(i) - meanB);
            summation2 += Math.pow((inAandB.get(0).get(i) - meanA), 2);
            summation3 += Math.pow((inAandB.get(1).get(i) - meanB), 2);
        }

        // Formula for calculating r (correlation coefficient) rounded to two decimal places
        if (summation2 == 0 || summation3 == 0) return 0.0; // Avoid division by zero
        return Math.floor(100 * summation1 / Math.sqrt(summation2 * summation3)) / 100.0;
    }

    // Gets the students in both class A and class B
    public static ArrayList<ArrayList<Double>> studentsInClassAandB(double[][] allStudents, int classA, int classB) {
        ArrayList<ArrayList<Double>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        result.add(new ArrayList<>());

        for (double[] student : allStudents) {
            if (student[classA] != -1.0 && student[classB] != -1.0) {
                result.get(0).add(student[classA]);
                result.get(1).add(student[classB]);
            }
        }
        return result;
    }

    // Calculate the mean for a class
    public static double classMean(ArrayList<ArrayList<Double>> inAandB, boolean forA) {
        double sum = 0;
        int index = forA ? 0 : 1;
        for (int i = 0; i < inAandB.get(index).size(); i++) {
            sum += inAandB.get(index).get(i);
        }
        return sum / inAandB.get(index).size();
    }

    // Prints the correlation map
    private void printCorrelationMap() {
        System.out.println("Correlation Map:");
        for (String key : correlationMap.keySet()) {
            System.out.println(key + " -> " + correlationMap.get(key));
        }
    }

    // Exports the correlation map to a CSV file
    public void exportToCSV(String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);

        // Write the correlation map to CSV
        writer.write("Class Pair,Correlation\n");
        for (String key : correlationMap.keySet()) {
            writer.write(key + "," + correlationMap.get(key) + "\n");
        }

        writer.close();
    }
}

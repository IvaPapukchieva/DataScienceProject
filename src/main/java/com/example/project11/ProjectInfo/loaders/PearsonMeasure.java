package com.example.project11.ProjectInfo.loaders;

import java.io.FileNotFoundException;

public class PearsonMeasure {
    private double[][] graduatingGradesArray;
    private int courseX;
    private int courseY;

    public PearsonMeasure(int courseX, int courseY, double[][] graduatingGradesArray) throws FileNotFoundException {
        this.courseX = courseX;
        this.courseY = courseY;
        this.graduatingGradesArray = graduatingGradesArray;
    }

    public int getComplementingCourse() {
        return courseY;
    }

    public double getAverage(int course) {
        double sum = 0;
        int count = 0;

        for (int i = 0; i < graduatingGradesArray.length; i++) {
            if (graduatingGradesArray[i][course] != -1) { // Exclude missing values
                sum += graduatingGradesArray[i][course];
                count++;
            }
        }
        return count > 0 ? sum / count : 0; // Avoid division by zero
    }

    public double getNumerator() {
        double numerator = 0;
        double average_X = getAverage(courseX);
        double average_Y = getAverage(courseY);

        for (int i = 0; i < graduatingGradesArray.length; i++) {
            if (graduatingGradesArray[i][courseX] != -1 && graduatingGradesArray[i][courseY] != -1) {
                double dataPoint_X = graduatingGradesArray[i][courseX] - average_X;
                double dataPoint_Y = graduatingGradesArray[i][courseY] - average_Y;
                numerator += dataPoint_X * dataPoint_Y;
            }
        }
        return numerator;
    }

    public double getDenominator() {
        double sum_X = 0;
        double sum_Y = 0;
        double average_X = getAverage(courseX);
        double average_Y = getAverage(courseY);

        for (int i = 0; i < graduatingGradesArray.length; i++) {
            if (graduatingGradesArray[i][courseX] != -1) {
                sum_X += Math.pow((graduatingGradesArray[i][courseX] - average_X), 2);
            }
            if (graduatingGradesArray[i][courseY] != -1) {
                sum_Y += Math.pow((graduatingGradesArray[i][courseY] - average_Y), 2);
            }
        }
        return Math.sqrt(sum_X * sum_Y);
    }

    public double getR() {
        double numerator = getNumerator();
        double denominator = getDenominator();

        if (denominator == 0) {
            return 0; // Return 0 if no correlation can be determined
        }

        return numerator / denominator;
    }


}

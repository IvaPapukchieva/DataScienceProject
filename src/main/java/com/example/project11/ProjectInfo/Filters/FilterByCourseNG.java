package com.example.project11.ProjectInfo.Filters;

import java.util.HashMap;
import java.util.Map;

public class FilterByCourseNG  implements Filter{

    private final double[][] allStudents;
    private double[] numberOfNG;

    public FilterByCourseNG(double[][] allStudents) {
        this.allStudents = allStudents;
        numberOfNG=getAllTheNGByCourse();
    }

    public double[] getAllTheNGByCourse() {

        numberOfNG = new double[allStudents[0].length];
        double count = 0;

        for (int i = 0; i < allStudents[0].length; i++) {
            for (int j = 0; j < allStudents.length; j++) {
                if (allStudents[j][i] == -1.0) {
                    count++;
                }
            }
            numberOfNG[i] = count;
            count = 0;
        }
        return numberOfNG;
    }

    public double[][] filterStudents(int amountNG) {
        double[][] filteredStudents = new double[numberOfNG.length][2];
        for (int i = 0; i < numberOfNG.length; i++) {
            filteredStudents[i][0] = i;
            if (numberOfNG[i] == amountNG) {
                filteredStudents[i][1] = amountNG;
            } else {
                filteredStudents[i][1] = -1;
            }
        }
        return filteredStudents;
    }
    public double[][] filterStudents(int lowerBound, int upperBound) {
        if (lowerBound > upperBound) {
            int temp = lowerBound;
            lowerBound = upperBound;
            upperBound = temp;
        }

        double[][] filteredStudents = new double[numberOfNG.length][2];

        for (int i = 0; i < numberOfNG.length; i++) {
            filteredStudents[i][0] = i;
            if (numberOfNG[i] >= lowerBound && numberOfNG[i] <= upperBound) {
                filteredStudents[i][1] = numberOfNG[i];
            } else {
                filteredStudents[i][1] = -1;
            }
        }

        return filteredStudents;
    }

    public double[][] filterStudents(int[] amountNGs) {
        double[][] filteredStudents = new double[numberOfNG.length][2];
        for (int i = 0; i < numberOfNG.length; i++) {
            filteredStudents[i][0] = i;
            boolean matchFound = false;
            for (int amountNG: amountNGs) {
                if (numberOfNG[i] == amountNG) {
                    filteredStudents[i][1] = amountNG;
                    matchFound = true;
                    break;
                }
            }

            if (!matchFound) {filteredStudents[i][1] = -1;
            }
        }

        return filteredStudents;
    }






}
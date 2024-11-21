package com.example.project11.ProjectInfo.STEP1;//package CODE;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SimilarClasses2 {

    private double[][] allStudents; 


    public SimilarClasses2(ArrayList<ArrayList<Double>> allClasses){
       this.allStudents = allStudents;
    }

    public void calculateSimilarClasses() {
        try {

			double[][] correlationMatrix = correlationMatrix(allStudents);

            //print correlation matrix
            for (double[] array : correlationMatrix) {
                System.out.print("[");
                for (double element : array) {
                    System.out.print(element +", ");
                }
                System.out.print("]\n");
            }



        } catch (Exception ex) {
            ex.printStackTrace();
		}

    }
	// puts all students in class A in the first array list and in class B in the second array list
	public static ArrayList<ArrayList<Double>> studentsInClassAandB(double[][] allStudents, int classA, int classB) {
		ArrayList<ArrayList<Double>> result = new ArrayList<ArrayList<Double>>();
		//add classes A and B lists to the result
		result.add(new ArrayList<Double>());
		result.add(new ArrayList<Double>());
		for (double[] student : allStudents) {
			if(student[classA] != -1.0 && student[classB] != -1.0) {
				result.get(0).add(student[classA]);
				result.get(1).add(student[classB]);
			}
		}
		return result;
	}

	// Creates the correlation matrix
	public static double[][] correlationMatrix(double[][] allStudents) {
        //create 2d arraylist
        double[][] matrix = new double[allStudents[0].length][allStudents[0].length];

        for(int row = 0; row < allStudents[0].length; row++) {
            for (int col = 0; col < allStudents[0].length; col++) {
                matrix[row][col] = correlationBetweenClasses(allStudents, row, col);
            }
        }
        

        return matrix;
    }

	//Calculate the correlation between class A and Class B
	public static double correlationBetweenClasses(double[][] allStudents, int classA, int classB) {
		ArrayList<ArrayList<Double>> inAandB = studentsInClassAandB(allStudents, classA, classB);

        double meanA = classMean(inAandB, true);
        double meanB = classMean(inAandB, false);

        double summation1 = 0; double summation2 = 0; double summation3 = 0;
        for(int i = 0; i < inAandB.get(0).size(); i++) {
            summation1 += (inAandB.get(0).get(i) - meanA)*(inAandB.get(1).get(i) - meanB);
            summation2 += Math.pow((inAandB.get(0).get(i) - meanA), 2);
            summation3 += Math.pow((inAandB.get(1).get(i) - meanB), 2);
        }
        //formula for calculating r (correlation coefficient) (rounded to second digit)
        return (Math.floor((100*summation1/Math.sqrt(summation2*summation3))))/100;
    }

	// Calculate mean of the class
	public static double classMean(ArrayList<ArrayList<Double>> inAandB, boolean forA) {
		double sum = 0;
        int index = forA ? 0 : 1;
        for(int i = 0; i < inAandB.get(index).size(); i++) {
            sum += inAandB.get(index).get(i);
        }
        return sum / inAandB.get(index).size();
    }

	//Export the data as a csv file
	public static void exportToCSV(double[][] array, String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);

        // Loop through each row
        for (int i = 0; i < array.length; i++) {
            // Loop through each element in the row
            for (int j = 0; j < array[i].length; j++) {
                writer.write(String.valueOf(array[i][j]));
                if (j < array[i].length - 1) {
                    writer.write(",");
                }
            }
            writer.write("\n");
        }


        writer.close();
    }
}


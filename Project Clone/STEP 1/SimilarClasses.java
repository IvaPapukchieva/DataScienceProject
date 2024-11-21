import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class SimilarClasses {

    private ArrayList<ArrayList<Double>> allClasses;

    public SimilarClasses(ArrayList<ArrayList<Double>> allClasses){
                this.allClasses = allClasses;

    }

    public double[][] calculateSimilarClasses() {
        
            double[][] matrix = correlationMatrix(allClasses);

            return matrix;


    }

    public double[][] correlationMatrix(ArrayList<ArrayList<Double>> allClasses) {
        //create 2d arraylist
        double[][] matrix = new double[allClasses.size()][allClasses.size()];

        for(int row = 0; row < allClasses.size(); row++) {
            for (int col = 0; col < allClasses.size(); col++) {
                matrix[row][col] = correlationBetweenClasses(allClasses, row, col);
            }
        }
        

        return matrix;
    }

    public double correlationBetweenClasses(ArrayList<ArrayList<Double>> allClasses, int classA, int classB) {

        double meanA = classMean(allClasses, classA);
        double meanB = classMean(allClasses, classB);

        double summation1 = 0; double summation2 = 0; double summation3 = 0;
        int smallerSize = allClasses.get(classA).size() >= allClasses.get(classB).size() ? allClasses.get(classB).size() : allClasses.get(classA).size();
        for(int i = 0; i < smallerSize; i++) {
            summation1 += (allClasses.get(classA).get(i) - meanA)*(allClasses.get(classB).get(i) - meanB);
            summation2 += Math.pow((allClasses.get(classA).get(i) - meanA), 2);
            summation3 += Math.pow((allClasses.get(classB).get(i) - meanB), 2);
        }
        //formula for calculating r (correlation coefficient) (rounded to second digit)
        return (Math.floor((100*summation1/Math.sqrt(summation2*summation3))))/100;
    }

    public double classMean(ArrayList<ArrayList<Double>> allClasses, int index) {

        double sum = 0;
        for(int i = 0; i < allClasses.get(index).size(); i++) {
            sum += allClasses.get(index).get(i);
        }
        return sum / allClasses.get(index).size();
    }


    public void exportToCSV(double[][] array, String fileName) throws IOException {
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


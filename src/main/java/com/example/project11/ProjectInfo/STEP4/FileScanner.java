package com.example.project11.ProjectInfo.STEP4;

import java.io.File;
import java.util.Scanner;

public class FileScanner {
    private String fileName;
    private int numRows;
    private int numCols;
    private double[][] allStudents;
    private int course ; 

    public FileScanner( ) {
        this.fileName = "C:\\Users\\marco\\Documents\\Project 1-1\\CODE\\STEP3 2\\Step3\\CurrentGrades.csv";
        this.numRows = 1329;
        this.numCols = 33;
        this.course = course ; 
    }
    public double[][]  getScan() throws Exception{

        File file = new File(fileName);
        allStudents = new double[numRows][numCols];

        // This code uses two Scanners, one which scans the file line per line
        Scanner fileScanner = new Scanner(file);

        int linesDone = -1;
        int coursesDone = 0;
        int newNg = -1;

        while (fileScanner.hasNextLine() && linesDone <= 1328) {

            String line = fileScanner.nextLine();

            // and one that scans the line entry per entry using the commas as delimiters
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");
            // increments the rows of the data
            coursesDone = 0;
            while (lineScanner.hasNext()) {
                if (lineScanner.hasNextInt()) {
                    int i = lineScanner.nextInt();
                }
                if (lineScanner.hasNextDouble()) {
                    allStudents[linesDone][coursesDone++] = lineScanner.nextDouble();
                } else {
                    String s = lineScanner.next();
                    if (s.equals("NG")) {

                        allStudents[linesDone][coursesDone++] = newNg;

                    }
                }
            }
            linesDone++;
            lineScanner.close();
        }
        return allStudents ; 

    }
    
        
    
    }


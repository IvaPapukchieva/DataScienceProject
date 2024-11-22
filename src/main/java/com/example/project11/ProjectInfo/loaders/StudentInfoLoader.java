package com.example.project11.ProjectInfo.loaders;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StudentInfoLoader extends CurrentGradeLoader  {

  public String[][] infoInStrings;
  private String fileName;


    public StudentInfoLoader() throws FileNotFoundException {
        this.fileName="src/main/resources/csv/StudentInfo.csv";
        this.infoInStrings=readInfoString();

    }



    public String[][] readInfoString() throws FileNotFoundException {


        File file = new File(fileName);
        Scanner fileScanner = new Scanner(file);
    infoInStrings = new String[1329][6];



    int rowsDone = 0;
    int colsDone = 0 ;

        while (fileScanner.hasNextLine() && rowsDone <= 1329) {

        String line = fileScanner.nextLine();

        // and one that scans the line entry per entry using the commas as delimiters
        Scanner lineScanner = new Scanner(line);
        lineScanner.useDelimiter(",");
        // increments the rows of the data
        colsDone = 0;
        while (lineScanner.hasNext()) {
            if (lineScanner.hasNextInt()) {
                infoInStrings[rowsDone][colsDone++] = Integer.toString(lineScanner.nextInt());

            }  if (lineScanner.hasNextDouble()) {
                infoInStrings[rowsDone][colsDone++] = Double.toString(lineScanner.nextDouble());

            } else {
                infoInStrings[rowsDone][colsDone++] = lineScanner.next();
            }
        }
        rowsDone++;
        lineScanner.close();
    }
        return infoInStrings ;
}
}

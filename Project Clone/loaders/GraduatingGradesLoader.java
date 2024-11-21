import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GraduatingGradesLoader {

	private int numRows;
	private int	numCols;
	private  String fileName;
	public double[][] allStudents;
	public int [] StudentID;

	public GraduatingGradesLoader() throws FileNotFoundException {
		this.fileName ="/Users/ivapapukchieva/Desktop/ken_group10_2024/CSV/GraduateGrades.csv";
		this.numRows = 19353;
		this.numCols = 33;
		this.allStudents = readAllStudents();
		this.StudentID = setStudentID();


	}
	public double[][] readAllStudents() throws FileNotFoundException {
		allStudents = new double[numRows][numCols];

        	// Adapt this when you want to read and display a different file.
            File file=new File(fileName);

            // This code uses two Scanners, one which scans the file line per line
            Scanner fileScanner = new Scanner(file);

            int linesDone = -1;
			int coursesDone = 0;
            while (fileScanner.hasNextLine() && linesDone < 19353) {

            	String line = fileScanner.nextLine();

            	// and one that scans the line entry per entry using the commas as delimiters
            	Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");
				// increments the rows of the data
				coursesDone = 0;
				while (lineScanner.hasNext()) {
            		// Separate commands can be used depending on the types of the entries
            		// (i) and (s) are added to the printout to show how each entry is recognized
            		if (lineScanner.hasNextInt()) {
            			int i = lineScanner.nextInt();
            			// System.out.print("(i)" + i + " ");
            		} else if (lineScanner.hasNextDouble()) {
						allStudents[linesDone][coursesDone++] = lineScanner.nextDouble();
            		} else {
            			String s = lineScanner.next();
            			// System.out.print("(s)" + s + " ");
            		}
            	}
				// increments the columns of the data
				linesDone++;
            	lineScanner.close();
            }




			return allStudents;



		}

	public int[]setStudentID() throws FileNotFoundException {

		File file = new File(fileName);
		Scanner fileScanner = new Scanner(file);

		int[] studentID = new int[numRows+1];

		fileScanner = new Scanner(file);
		int index = 0;

		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
			Scanner lineScanner = new Scanner(line);
			lineScanner.useDelimiter(",");

			if (lineScanner.hasNextInt()) {
				// Read the first column (ID) and add to the array
				studentID[index++] = lineScanner.nextInt();
			}

			lineScanner.close();
		}

		fileScanner.close();

		return studentID;


	}

    }


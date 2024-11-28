package com.example.project11.ProjectInfo.STEP2;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**The program determined that students with 3 or fewer “No Grade” (NG) values are nearing graduation, 
 * resulting in identifying 237 students by saving their indices. */

public class GraduatingSoon {
	private HashMap<Integer, double[]> studentDataMap;
	public GraduatingSoon(String fName){
		try {
			studentDataMap = new HashMap<>();
			File file = new File(fName);

			// This code uses two Scanners, one which scans the file line per line
			Scanner fileScanner = new Scanner(file);

			int linesDone = 0;
			double[][] allStudents = new double[1328][33];
			int coursesDone = 0;
			while (fileScanner.hasNextLine() && linesDone < 1328) {

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
				studentDataMap.put(linesDone, allStudents[linesDone]);
				// increments the columns of the data
				linesDone++;
				lineScanner.close();
			}

			fileScanner.close();
			
			graduatingSoon(allStudents);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public String graduatingSoon(double[][] allStudents){

		int soonToGraduate = 0;
			ArrayList soonToGradStudents = new ArrayList<Integer>();
			for (int i = 0; i < allStudents.length; i++) {
				int notGraded = 0;
				for (int j = 0; j < allStudents[i].length; j++) {
					if (allStudents[i][j] == 0.0) {
						notGraded++;
					}
				}
				if (notGraded <= 3) {
					soonToGraduate++;
					soonToGradStudents.add(i);
				}
			}

		HashMap<Integer, double[]> filteredMap = new HashMap<>();
		for (Object studentIndex : soonToGradStudents) {
			filteredMap.put((Integer) studentIndex, studentDataMap.get(studentIndex));
		}

			return soonToGradStudents.toString();
//			System.out.println(soonToGraduate);
//			System.out.println(soonToGradStudents.toString());
	}

}
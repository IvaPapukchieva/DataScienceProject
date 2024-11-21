import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/** This program uses the Monte Carlo method to address the issue of missing values in the CurrentGrade database, 
 * which is weighted randomization for more pertinent bootstrapping  of the values. 
 * This consisted of counting how many times a student has received a specific grade, and then using the
 * Math.random function combined with if and else statements to create a weighted probability that
 * allows us to make reasonable replacements of data in the hopes of efficiently analyzing our data. */

public class HardestEasiestMonteCarlo {

    public static double[][] allStudents = new double[1328][33];

    public static void main(String[] args) {
        try {
            String fileName = "CurrentGrades.csv";
            File file = new File(fileName);

            // This code uses two Scanners, one which scans the file line per line
            Scanner fileScanner = new Scanner(file);
            int linesDone = 0; // Start at 0 to avoid skipping first line

            while (fileScanner.hasNextLine() && linesDone < 1328) {
                String line = fileScanner.nextLine();
                try (Scanner lineScanner = new Scanner(line)) {
                    lineScanner.useDelimiter(",");

                    int coursesDone = 0;
                    while (lineScanner.hasNext()) {
                        if (lineScanner.hasNextInt()) {
                            lineScanner.nextInt();
                        }
                        if (lineScanner.hasNextDouble()) {
                            allStudents[linesDone][coursesDone++] = lineScanner.nextDouble();
                        } else {
                            String s = lineScanner.next();
                            if (s.equals("NG")) {
                                allStudents[linesDone][coursesDone++] = -1.0; // Assign -1.0 for "NG"
                            }
                        }
                    }
                }
                linesDone++;
            }

            double[][] allCousesCount = new double[33][11];
            for (int i = 0; i < 33; i++) {
                double[] array = reps(allStudents, i);
                for (int j = 0; j < 11; j++) {
                    System.out.print(array[j] + "||");
                    allCousesCount[i][j] = array[j];
                }
                System.out.println();
            }

            for (int i = 0; i < 33; i++) {
                for (int j = 0; j < 11; j++) {
                    System.out.print(allCousesCount[i][j] + "||");
                }
                System.out.println();
            }

            // Making the margins for the randomization
            double[] ranges = add(allCousesCount, 0);
            int sum = 0; // Total number of grades
            System.out.println(Arrays.toString(ranges));

            for (double range : ranges) {
                sum += range;
            }

            for (int i = 0; i < allStudents.length; i++) {
                for (int j = 0; j < allStudents[0].length; j++) {
                    int random = (int) (Math.random() * sum);
                    if (allStudents[i][j] == -1.0) {
                        if (random < ranges[2] && random >= 0) {
                            allStudents[i][j] = 2.0;
                        }
                        if (random <= ranges[3] + ranges[2] && random >= ranges[2]) {
                            allStudents[i][j] = 3.0;
                        }
                        if (random <= ranges[4] + ranges[3] + ranges[2] && random >= ranges[3] + ranges[2]) {
                            allStudents[i][j] = 4.0;
                        }
                        if (random <= ranges[5] + ranges[4] + ranges[3] + ranges[2]
                                && random >= ranges[4] + ranges[3] + ranges[2]) {
                            allStudents[i][j] = 5.0;
                        }
                        if (random <= ranges[6] + ranges[5] + ranges[4] + ranges[3] + ranges[2]
                                && random >= ranges[5] + ranges[4] + ranges[3] + ranges[2]) {
                            allStudents[i][j] = 6.0;
                        }
                        if (random <= ranges[7] + ranges[6] + ranges[5] + ranges[4] + ranges[3] + ranges[2]
                                && random >= ranges[6] + ranges[5] + ranges[4] + ranges[3] + ranges[2]) {
                            allStudents[i][j] = 7.0;
                        }
                        if (random <= ranges[8] + ranges[7] + ranges[6] + ranges[5] + ranges[4] + ranges[3] + ranges[2]
                                && random > ranges[7] + ranges[6] + ranges[5] + ranges[4] + ranges[3] + ranges[2]) {
                            allStudents[i][j] = 8.0;
                        }
                        if (random <= ranges[9] + ranges[8] + ranges[7] + ranges[6] + ranges[5] + ranges[4] + ranges[3]
                                + ranges[2]
                                && random >= ranges[8] + ranges[7] + ranges[6] + ranges[5] + ranges[4] + ranges[3]
                                        + ranges[2]) {
                            allStudents[i][j] = 9.0;
                        }
                        if (random <= ranges[10] + ranges[9] + ranges[8] + ranges[7] + ranges[6] + ranges[5] + ranges[4]
                                + ranges[3] + ranges[2]
                                && random > ranges[9] + ranges[8] + ranges[7] + ranges[6] + ranges[5] + ranges[4]
                                        + ranges[3] + ranges[2]) {
                            allStudents[i][j] = 10.0;
                        }
                    }
                }
            }
            for (int i = 0; i < allStudents.length; i++) {
                for (int j = 0; j < allStudents[0].length; j++) {
                    System.out.print(allStudents[i][j] + "|");
                }
                System.out.println(" ");
            }

            double[] courseAverages = getAllCoursesAverage();

            // Get the three largest and smallest values
            double[][][] extremeValues = getThreeLargestAndSmallest(courseAverages);

            System.out.println("Three smallest values: " + Arrays.deepToString(extremeValues[0]));
            System.out.println("Three largest values: " + Arrays.deepToString(extremeValues[1]));

            fileScanner.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static double[] add(double[][] allCousesCount, int invalid) {
        double[] count = new double[11];
        double c = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 33; j++) {
                c += allCousesCount[j][i];
            }
            count[i] = c;
            c = 0;
        }
        return count;
    }

    public static double[] reps(double[][] allStudents, int classc) {
        double[] course = new double[11]; // Initialize array to count occurrences

        for (int j = 0; j < allStudents.length; j++) {
            if (allStudents[j][classc] >= 0 && allStudents[j][classc] <= 10) { // Count only valid scores
                course[(int) allStudents[j][classc]]++; // Increment the appropriate score count
            }
        }
        return course;
    }

    public static double[][][] getThreeLargestAndSmallest(double[] arrayAv) {

        double[][] indexedArray = new double[arrayAv.length][2];

        for (int i = 0; i < arrayAv.length; i++) {
            indexedArray[i][0] = arrayAv[i]; // Value
            indexedArray[i][1] = i; // Index
        }

        // Sort the array based on values
        Arrays.sort(indexedArray, (a, b) -> Double.compare(a[0], b[0]));
        // Sort the indexedArray by the values in the first column (a[0] and b[0]) of
        // each row.
        // Arrays.sort will arrange the rows in ascending order based on these values.
        // The lambda function (a, b) -> Double.compare(a[0], b[0]) specifies that:
        // - If a[0] < b[0], row 'a' comes before 'b'
        // - If a[0] == b[0], the order stays the same
        // - If a[0] > b[0], row 'b' comes before 'a'
        // This gives us indexedArray sorted by the values in the first column.

        double[][] smallest = new double[3][2];

        double[][] largest = new double[3][2];

        // Handle cases where the array might have fewer than 3 elements
        int length = indexedArray.length;
        for (int i = 0; i < 3; i++) {
            if (i < length) {
                smallest[i][0] = indexedArray[i][0];

                smallest[i][1] = indexedArray[i][1];

                largest[i][0] = indexedArray[length - 1 - i][0];

                largest[i][1] = indexedArray[length - 1 - i][1];

            }
        }

        return new double[][][] { smallest, largest };
    }

    public static double[] getAllCoursesAverage() {
        double[] arrayAv = new double[33];
        for (int courseIndex = 0; courseIndex < 33; courseIndex++) {
            arrayAv[courseIndex] = getCourseAverage(courseIndex);
        }
        return arrayAv;
    }

    public static double getCourseAverage(int course) {
        double sum = 0;
        double validCount = 0;

        for (int i = 0; i < allStudents.length; i++) {
            if (allStudents[i][course] != -1.0) { // Only consider valid scores
                sum += allStudents[i][course];
                validCount++;
            }
        }
        // Avoid division by zero  
        return validCount > 0 ? Math.floor(1000 * (sum / validCount)) / 1000 : 0.0;
    }
}

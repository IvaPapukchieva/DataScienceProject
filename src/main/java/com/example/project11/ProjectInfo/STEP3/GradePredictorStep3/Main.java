import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static String[][]infoInStrings ; 
    public static void main(String[] args) throws Exception{
        String fileName = "C:\\Users\\marco\\Documents\\Project 1-1\\CODE\\STEP3 2\\Step3\\StudentInfo.csv";
            File file=new File(fileName);
            
            // This code uses two Scanners, one which scans the file line per line
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
            for( int  j = 0  ; j<33 ; j++){
                for( int i = 0  ;i<infoInStrings.length; i++){
                    System.out.println((infoInStrings[i][0]));

                    Prediction prediction = new Prediction(infoInStrings[i][0], j);
                    System.out.println(prediction.getPrediction());
                }
            }
        }
        

    }
    


// package CODE ;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

/**The program determined the course year based on missing data, assuming that absent grades indicated
 * students had not yet taken those classes, suggesting they are offered later in the academic year. 
 * It counted the NG (No Grade) values per class and identified classes with similar counts, ensuring a 
 * difference of about 11 or more to prevent overfitting our data. */

public class WhatYearWhatclass {

    public WhatYearWhatclass(String fileName) {
        try {
            File file=new File(fileName);
            
            // This code uses two Scanners, one which scans the file line per line
            Scanner fileScanner = new Scanner(file);
            double[][] allStudents = new double[1329][33];
			
           

            int linesDone = -1 ; 
            int coursesDone = 0 ;
            int newNg = -1 ;  
        
            while (fileScanner.hasNextLine() && linesDone <= 1328) {

            	String line = fileScanner.nextLine();
            	
            	Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");
				// increments the rows of the data
				coursesDone = 0;
				while (lineScanner.hasNext()) {
            		if (lineScanner.hasNextInt()) {
            			int i = lineScanner.nextInt();
            		}  if (lineScanner.hasNextDouble()) {
						allStudents[linesDone][coursesDone++] = lineScanner.nextDouble();
            		} else {
            			String s = lineScanner.next();
                        if(s.equals("NG")){

                            allStudents[linesDone][coursesDone++] = newNg;

                        }
            		}
            	}
				linesDone++;
            	lineScanner.close();
            }
	
			
			
			double [] arrayOfNumGrade = new double[allStudents[0].length] ;
            double count = 0 ; 

			for(int i = 0; i < allStudents[0].length; i++) {
				for (int j = 0; j < allStudents.length; j++) {
                    if( allStudents[j][i] == -1.0){
                        count++ ; 
                        arrayOfNumGrade[i] = count ;



                    }
                    
				}

                count = 0 ; 

			
			}
			
			int [] arrayFirstYear = new int [arrayOfNumGrade.length];
			int [] arraySecondYear = new int [arrayOfNumGrade.length];
			int [] arrayThirdYear = new int [arrayOfNumGrade.length];
			int [] arrayFourthYear = new int[arrayOfNumGrade.length] ;
			int [] arrayFithYear = new int[arrayOfNumGrade.length];

			int firstyearcount = 0 ; 
			int secondyearcount = 0  ; 
			int thirdyearcount = 0 ; 
			int fourthyearcount = 0 ;
			int fithyearcount = 0 ; 
			
				int c = 0  ; 
				int d = 0 ; 
				int e = 0;
				int f = 0 ; 
			for( int i = 0 ; i<33 ; i++){
				if (arrayOfNumGrade[i]>=0.0 && arrayOfNumGrade[i]<=12.0){
					arrayFithYear[f] = i ; 
					f++ ;
					fithyearcount ++ ; 
				}
			}
			for( int i = 0 ; i<33 ; i++){	
				if ( arrayOfNumGrade[i]>=583.0 && arrayOfNumGrade[i]<=595.0){
					arrayFourthYear[c] = i;
					c++ ;

					fourthyearcount++ ; 
				// why is the a 610, is that another thing?
				}
			}
			for( int i = 0 ; i<33 ; i++){
				 if ( arrayOfNumGrade[i]>=599.0 && arrayOfNumGrade[i]<=622.0){
					arrayThirdYear[c] = i;
					c++ ;
					thirdyearcount++ ; 
				// why is the a 610, is that another thing?
				 }

			}
			for( int i = 0 ; i<33 ; i++){
				 if(arrayOfNumGrade[i]>=985.0 && arrayOfNumGrade[i]<=1058.0){
					arraySecondYear[d] = i ; 
					d++ ;
					secondyearcount++ ; 

				}
			}
			for( int i = 0 ; i<33 ; i++){
				 if (arrayOfNumGrade[i]>=1317.0 && arrayOfNumGrade[i]<=1339.0){
					arrayFirstYear[e] = i ; 
					e++;
					firstyearcount++;


				}

			}
//            System.out.println(Arrays.toString(arrayOfNumGrade));
//
//
//			System.out.println(Arrays.toString(arrayFirstYear));
//			System.out.println(firstyearcount);
//
//			System.out.println(Arrays.toString(arraySecondYear));
//			System.out.println(secondyearcount);
//			System.out.println(Arrays.toString(arrayThirdYear));
//			System.out.println(thirdyearcount);
//
//			System.out.println(Arrays.toString(arrayFourthYear));
//			System.out.println(fourthyearcount );
//
//			System.out.println(Arrays.toString(arrayFithYear));
//			System.out.println(fithyearcount );
            fileScanner.close();

		

        } catch (Exception ex) {
            ex.printStackTrace();
		}

		

		


    }
}



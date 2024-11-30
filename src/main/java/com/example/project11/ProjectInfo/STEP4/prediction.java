package com.example.project11.ProjectInfo.STEP4;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class prediction {
    protected static double[][] allStudents  ;
    protected static String[][] infoInStrings; 
    protected static double[][]bootstrapArray ; 
    protected static int course  = -1;
    protected static String STUDENTID = " ";  
    protected static double[] Average  = new double[11]; 
    public static String[] Category1 = {"full", "medium","nothing",  "low", "high"};
    protected static String[] Category3 = { "2 tau","1 tau", "3 tau"};
    protected static int[]    Category2 = {-45 ,-16, -15, 15, 16,  45,46, 75,76,  105,106, 136};
    protected static String[] Category4 = {  "C","A", "D","B", "E", "F"};
    protected static String[] Category5 = {"0.1 Hz ", "0.5 Hz ", "1.0 Hz ", "5.0 Hz "};
    // public static  double[][] bootstrapArray ;
    public static  WeightedBootstrapping BootstrapArray; 

    public prediction(int course , String Name){
        this.course = course ; 
        this.STUDENTID = Name ; 

    }
    public double getPrediction() throws Exception{
            infoInStrings = studentInfo();
            course = 0 ; 
            FileScanner AllStudents = new FileScanner();
            allStudents =  AllStudents.getScan();  
            BootstrapArray = new WeightedBootstrapping(allStudents) ; 
            bootstrapArray = BootstrapArray.getWeightedBootrsapp() ;
                  
    
        for( int c = 0 ; c<200 ; c++){
   
             BootstrapArray = new WeightedBootstrapping(allStudents) ; 
            bootstrapArray = BootstrapArray.getWeightedBootrsapp() ;
        
        double[] gradesCourse = new double[bootstrapArray.length];

        for( int i = 0 ;i<bootstrapArray.length ; i++){
            gradesCourse[i] = bootstrapArray[i][course];
        }

        double[] MainNormalDistribution ;
        NormalDis ND = new NormalDis(gradesCourse) ;
        MainNormalDistribution = ND.getNormalDistribution();
        for(int i = 0 ;i<Average.length; i++){
            Average[i]+= MainNormalDistribution[i];
        }

    }
        for (int i = 0 ; i<Average.length ; i++){
            Average[i] = Average[i]/200;
        }
        double sum = 0 ; 
        for( int i = 0 ; i<Average.length ; i++){
            sum+= Average[i];
        }

        for (int i = 0 ; i<Average.length; i++){
            BigDecimal bd = new BigDecimal((Average[i]/sum )*100);
            Average[i]  = bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
        System.out.println(" ");
        System.out.println(Arrays.toString(Average));

        double[][] Step4bootstrapp = new double[bootstrapArray.length][bootstrapArray[0].length]; 



            double[][] smallestArray = process();
            double[][] ratioArr  = getRatio(smallestArray)  ;

                double[][] Vals = new double[5][2];


                Vals[0] = (getWeightedGrade(Category1, null, ratioArr, 1, true, STUDENTID));
                Vals[1] = (getWeightedGrade(null, Category2, ratioArr, 2, false, STUDENTID));
                Vals[2] = (getWeightedGrade(Category3, null, ratioArr, 3, true, STUDENTID));
                Vals[3] = (getWeightedGrade(Category4, null, ratioArr, 4, true, STUDENTID));
                Vals[4] = (getWeightedGrade(Category5, null, ratioArr, 5, true, STUDENTID));
                System.out.println(Arrays.deepToString(Vals));


                double average = 0 ; 
                for (int c = 0 ; c<Vals.length ; c++){
                    for (int x = 0 ; x<Vals[0].length ; x++){
                        if( Vals[c][x]>0){
                            average+= Vals[c][x];
                        }
                    }
                }
                System.out.println(average/5);
                if((average/5)>10){
                    return 10 ;
                }
                else{
                    return (average/5);
                }

                 




        


    } 

    public static double[] getSmallest(double[] arr ){
        double min = 101;
        double index = -1 ;  
        for (int i = 0 ; i< arr.length ; i++){
            if(arr[i]<min){
                min = arr[i];
                index = i ; 
            }
        }
        double[] answer = new double[2];
        answer[0] = index; 
        answer[1] = min ; 
        return  answer; 

    }

    public static double[] getAverage(String Name , int CatgeroyNum) throws Exception{
        double[] average = new double[11] ; 
        int itterations = 50;

        for (int i = 0  ; i<itterations ; i++){
            BootstrapArray = new WeightedBootstrapping(allStudents) ; 
            bootstrapArray = BootstrapArray.getWeightedBootrsapp() ;
    
            Filter f = new Filter(Name, CatgeroyNum);
            ArrayList<Double> filter  = f.getFilter(); 
            double[] array = filter.stream().mapToDouble(Double::doubleValue).toArray();
            NormalDis ND = new NormalDis(array) ;
            double[] SpecificArrayGrades = ND.getNormalDistribution();
            for( int c = 0 ; c<SpecificArrayGrades.length ; c++){
                average[c]+= SpecificArrayGrades[c];
            }

        }
        for( int i = 0 ; i<average.length ; i++){
            average[i] = average[i]/itterations; 
        }
        double sum = 0 ; // to be able to get the percentages and then compare percantages 
        for( int i = 0 ; i<average.length ; i++){
            sum+= average[i];
        }

        for (int i = 0 ; i<average.length; i++){
            BigDecimal bd = new BigDecimal((average[i]/sum)*100);
            average[i]  = bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
          
        }
        return average;
   


    }

    public static double[] getAverage2(int indexRange)throws Exception{
        double[] average = new double[11];
        ArrayList<Double> grades = new ArrayList<>(); 
        int repetitions = 50; 
        for (int j = 0 ; j < repetitions ; j++){
            BootstrapArray = new WeightedBootstrapping(allStudents) ; 
            bootstrapArray = BootstrapArray.getWeightedBootrsapp() ;
            for( int i = 1 ; i<infoInStrings.length ; i++){
           

                if ( Integer.parseInt(infoInStrings[i][2]) >= Category2[indexRange] && Integer.parseInt(infoInStrings[i][2]) <= Category2[indexRange+1] ){
                    grades.add(bootstrapArray[i][course]);
                }
            }
            double[] array = grades.stream().mapToDouble(Double::doubleValue).toArray();
            NormalDis ND = new NormalDis(array) ;
            double[] SpecificArrayGrades = ND.getNormalDistribution();

            for( int c = 0 ; c<SpecificArrayGrades.length ; c++){
                average[c]+= SpecificArrayGrades[c];
            }

        }
        for( int i = 0 ; i<average.length ; i++){
            average[i] = average[i]/repetitions; 
        }
        double sum = 0 ; 
        for( int i = 0 ; i<average.length ; i++){
            sum+= average[i];
        }

        for (int i = 0 ; i<average.length; i++){
            BigDecimal bd = new BigDecimal((average[i]/sum )*100);
            average[i]  = bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
          
        }
        return average;

            
    }

    public static double getDiff(double[] AverageArr)throws Exception{
        double diff = 0; 
        for (int i = 0 ; i<AverageArr.length ; i++){
            diff+= Math.abs(Average[i]-AverageArr[i]);            
        }
        return diff; 
    }

    public static double[][] process()throws Exception{
        double[][] Category1Values = new double[Category1.length][11];
        double[][] Category2Values = new double[Category2.length/2][11];
        double[][] Category3Values = new double[Category3.length][11];
        double[][] Category4Values = new double[Category4.length][11];
        double[][] Category5Values = new double[Category5.length][11];

        for (int i = 0 ; i<Category1Values.length ; i++){
                Category1Values[i] = getAverage(Category1[i], 1);
            
        }
        System.out.println("Category 1 " + Arrays.deepToString(Category1Values));

        for (int i = 0 ; i<Category2Values.length ; i++){
                Category2Values[i] = getAverage2(i*2);
            
        }
        System.out.println("Category 2 " +Arrays.deepToString(Category2Values));

        for (int i = 0 ; i<Category3Values.length ; i++){
            for( int j = 0 ; j<Category3Values[0].length; j++){
                Category3Values[i] = getAverage(Category3[i], 3);
            }
        }
        System.out.println("Category 3 " +Arrays.deepToString(Category3Values));

        for (int i = 0 ; i<Category4Values.length ; i++){
            for( int j = 0 ; j<Category4Values[0].length; j++){
                Category4Values[i] = getAverage(Category4[i], 4);
            }
        }
        System.out.println("Category 4 " +Arrays.deepToString(Category4Values));

        for (int i = 0 ; i<Category5Values.length ; i++){
            for( int j = 0 ; j<Category5Values[0].length; j++){
                Category5Values[i] = getAverage(Category5[i],5);
            }
        }
        System.out.println("Category 5 " +Arrays.deepToString(Category5Values));

        double [] valDiff1 = new double[Category1.length];  
        double [] valDiff2 = new double[Category2Values.length];
        double [] valDiff3 = new double[Category3.length];
        double [] valDiff4= new double[Category4.length];
        double [] valDiff5 = new double[Category5.length];

        for (int i = 0 ; i<Category1Values.length ; i++){
            valDiff1[i] = getDiff(Category1Values[i]);
        }
        for( int i = 0 ; i<valDiff1.length ; i++){
                BigDecimal bd = new BigDecimal(valDiff1[i]);
                valDiff1[i]  = bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
        System.out.println(Arrays.toString(valDiff1));

        for (int i = 0 ; i<valDiff2.length ; i++){
            valDiff2[i] = getDiff(Category2Values[i]);
        }
        for( int i = 0 ; i<valDiff2.length ; i++){
            BigDecimal bd = new BigDecimal(valDiff2[i]);
            valDiff2[i]  = bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
        System.out.println(Arrays.toString(valDiff2));

        for (int i = 0 ; i<Category3Values.length ; i++){
            valDiff3[i] = getDiff(Category3Values[i]);
        }
        for( int i = 0 ; i<valDiff3.length ; i++){
            BigDecimal bd = new BigDecimal(valDiff3[i]);
            valDiff3[i]  = bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
        System.out.println(Arrays.toString(valDiff3));

        for (int i = 0 ; i<Category4Values.length ; i++){
            valDiff4[i] = getDiff(Category4Values[i]);
        }
        for( int i = 0 ; i<valDiff4.length ; i++){
            BigDecimal bd = new BigDecimal(valDiff4[i]);
            valDiff4[i]  = bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
        System.out.println(Arrays.toString(valDiff4));

        for (int i = 0 ; i<Category5Values.length ; i++){
            valDiff5[i] = getDiff(Category5Values[i]);
        }
        for( int i = 0 ; i<valDiff5.length ; i++){
            BigDecimal bd = new BigDecimal(valDiff5[i]);
            valDiff5[i]  = bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
        System.out.println(Arrays.toString(valDiff5));

        double[] small1 = getSmallest(valDiff1);
        System.out.println(Arrays.toString(small1));
        double[] small2= getSmallest(valDiff2);
        System.out.println(Arrays.toString(small2));
        double[] small3 = getSmallest(valDiff3);
        System.out.println(Arrays.toString(small3));
        double[] small4 = getSmallest(valDiff4);
        System.out.println(Arrays.toString(small4));
        double[] small5 = getSmallest(valDiff5);
        System.out.println(Arrays.toString(small5));

        double[][] smallestArray = {small1, small2, small3, small4, small5};

        return smallestArray; 
    }

public static double[][] getRatio(double[][] diffArray){
    double sum  = 0 ;
    double power = 2; 
    double[][] ratioArr = new double[diffArray.length ][2]; 
    for( int i = 0 ; i< diffArray.length ; i++){
        sum+=Math.pow(diffArray[i][1], 2); // amplification of the weight by using the power scaling method 


    }
    for (int i = 0 ;i<diffArray.length ; i++){
        ratioArr[i][1] =1-( Math.pow(diffArray[i][1],2))/sum ; // part of the power scaling method 
        ratioArr[i][0] = diffArray[i][0];
    }
    return ratioArr; 
}
public static double[] getWeightedGrade(String[] CategoryArr ,int[] CategoryInt, double[][] arr , int CategoryNum, boolean state , String StudentID){
    double[] grade = new double[2] ; 
    double max = 0 ; 
    double index = 0 ; 

    double[] gradeCount = new double[11];
    double[] NotgradeCount = new double[11];
    if(state ){
        for (int i = 0 ; i<infoInStrings.length ; i++){
            if( StudentID.equals(infoInStrings[i][0])){
                if( infoInStrings[i][CategoryNum].equals(CategoryArr[(int)arr[CategoryNum-1][0]])){
                    for (int c = 0 ; c<bootstrapArray.length ; c++){
                        gradeCount[(int)bootstrapArray[c][course]]++;
                    }
                   

                    // for (int j = 0  ;j<gradeCount.length ; j++){
                    //     if (gradeCount[j]>max){
                    //         max= gradeCount[j];
                    //         index = j  ;

                    //     }
                    // }
                    
                    grade [0] =  weight(gradeCount)*(1+(arr[CategoryNum-1][1]));
                }
            

            else{
                for (int c = 0 ; c<bootstrapArray.length ; c++){
                    gradeCount[(int)bootstrapArray[c][course]]++;
                }
                // for (int j = 0  ;j<gradeCount.length ; j++){
                //     if (gradeCount[j]>max){
                //         max= gradeCount[j];
                //         index = j  ;

                //     }
                // }

                grade[1]= weight(gradeCount)*(1+(1-arr[CategoryNum-1][1]));

                
            }
        }
        }
        return grade; 
    }


    if(!state){
        for( int i = 0  ; i<infoInStrings.length ; i++){
            if( StudentID.equals(infoInStrings[i][CategoryNum])){
                if(Integer.parseInt(infoInStrings[i][CategoryNum])>=CategoryInt[(int)arr[1][0]] && Integer.parseInt(infoInStrings[i][CategoryNum])<=CategoryInt[(int)arr[1][0]+1]){
                    // for (int j = 0  ;j<gradeCount.length ; j++){
                    //     if (gradeCount[j]>max){
                    //         max= gradeCount[j];
                    //         index = j  ; 

                    //     }
                    // }

                    grade[0] =  weight(gradeCount)*(1+(arr[1][1]));

                }
            }
            else{
                for (int c = 0 ; c<bootstrapArray.length ; c++){
                    gradeCount[(int)bootstrapArray[c][course]]++;
                }
                // for (int j = 0  ;j<gradeCount.length ; j++){
                //     if (gradeCount[j]>max){
                //         max= gradeCount[j];
                //         index = j  ;

                //     }
                // }

                grade[1]= weight(gradeCount)*(1+(1-arr[CategoryNum-1][1]));


            }
        }
        return grade ;

    }
    return null ;

}


    public static double weight(double[] arr){
        double totalWeight = 0;
        for (double weight : arr) {
            totalWeight += weight;
        }
        Random random = new Random();
        double randomValue = random.nextDouble() * totalWeight;
        
        // Find the interval corresponding to the random value
        double cumulativeSum = 0;
        for (int i = 0; i < arr.length; i++) {
            cumulativeSum += arr[i];
            if (randomValue < cumulativeSum) {
                return i-1;
            }
        }
        return 7 ; 
    }
    public static String[][] studentInfo() throws Exception{
            // Adapt this when you want to read and display a different file.
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
            return infoInStrings ; 
        }
}

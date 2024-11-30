package com.example.project11.ProjectInfo.STEP3;// package CODE ;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class STEP3 {
	//global 2d arrays
	public static double[][] allStudents;
	public static String[][] infoInStrings;

	//all students but with the ng values to simulate more efficiently
	public static double[][] allStudentsNG;
    public static void main(String[] args) {
        try {
            System.out.println("give me the course as a number, the number 0 is the first course ect");
            Scanner sc = new Scanner(System.in);
            int Num = sc.nextInt() ; 

            String[][] InfoArr = studentInfo() ; 
            double[][] allStudents = monteCarlo(true);

            double [] arr = new double [allStudents.length ];
            for( int i = 0 ; i<allStudents.length ; i++){
                arr[i] = allStudents[i][Num];
            }

			monteCarlo(false);
			//SIMULATES THE BEST CATEGORIES in StudentInfo.csv using montecarlo simulation
			String[] Category1 = {"full", "medium", "low", "nothing"};
			int[] Category2 = { -55 , 0 ,1,  50 , 51 , 100, 101, 150};
			String[] Category3 = {"1 tau", "2 tau", "3 tau"};
			String[] Category4 = {"A", "B", "C", "D", "E", "F"};
			String[] Category5 = {"0.1 Hz ", "0.5 Hz ", "1.0 Hz ", "5.0 Hz "};


            String[] Cate1  = categroy1class(allStudents, InfoArr, Num, Category1, 1); 
            String[] Cate2  = categroy2class(allStudents, InfoArr, Num, Category2, 1); 
            String[] Cate3  = categroy1class(allStudents, InfoArr, Num, Category3, 3); 
            String[] Cate4  = categroy1class(allStudents, InfoArr, Num, Category4, 4); 
            String[] Cate5  = categroy1class(allStudents, InfoArr, Num, Category1, 5); 
            if( Double.valueOf(Cate1[1])>Double.valueOf(Cate2[1]) &&  Double.valueOf(Cate1[1])>Double.valueOf(Cate3[1]) && Double.valueOf(Cate1[1])>Double.valueOf(Cate4[1]) && Double.valueOf(Cate1[1])>Double.valueOf(Cate5[1])){
                int o = 0; 
                double []ReplaceArr = new double[allStudents.length];
                for(int t = 0; t < allStudents.length; t++, o++) {
                    if (InfoArr[t][1].equals(Cate1[1])) {
                        ReplaceArr[o] = allStudents[t][Num];
    
                    }
                }
                double av = average(ReplaceArr);
                System.out.println(" For course"+Num + "the student has a grade of"+ av);// be

            }
            if( Double.valueOf(Cate2[1])>Double.valueOf(Cate1[1]) &&  Double.valueOf(Cate2[1])>Double.valueOf(Cate3[1]) && Double.valueOf(Cate2[1])>Double.valueOf(Cate4[1]) && Double.valueOf(Cate2[1])>Double.valueOf(Cate5[1])){
                int o = 0; 
                double []ReplaceArr = new double[allStudents.length];
                for(int t = 0; t < allStudents.length; t++, o++) {
                    if (InfoArr[t][1].equals(Cate2[1])) {
                        ReplaceArr[o] = allStudents[t][Num];
    
                    }
                }
                double av = average(ReplaceArr);
                System.out.println(" For course"+Num + "the student has a grade of"+ av);// be

            }
            if( Double.valueOf(Cate3[1])>Double.valueOf(Cate1[1]) &&  Double.valueOf(Cate3[1])>Double.valueOf(Cate2[1]) && Double.valueOf(Cate3[1])>Double.valueOf(Cate4[1]) && Double.valueOf(Cate3[1])>Double.valueOf(Cate5[1])){
                int o = 0; 
                double []ReplaceArr = new double[allStudents.length];
                for(int t = 0; t < allStudents.length; t++, o++) {
                    if (InfoArr[t][1].equals(Cate3[1])) {
                        ReplaceArr[o] = allStudents[t][Num];
    
                    }
                }
                double av = average(ReplaceArr);
                System.out.println(" For course"+Num + "the student has a grade of"+ av);// be

            }
            if( Double.valueOf(Cate4[1])>Double.valueOf(Cate1[1]) &&  Double.valueOf(Cate4[1])>Double.valueOf(Cate2[1]) && Double.valueOf(Cate4[1])>Double.valueOf(Cate3[1]) && Double.valueOf(Cate4[1])>Double.valueOf(Cate5[1])){
                int o = 0; 
                double []ReplaceArr = new double[allStudents.length];
                for(int t = 0; t < allStudents.length; t++, o++) {
                    if (InfoArr[t][1].equals(Cate4[1])) {
                        ReplaceArr[o] = allStudents[t][Num];
    
                    }
                }
                double av = average(ReplaceArr);
                System.out.println(" For course"+Num + "the student has a grade of"+ av);// be

            }
            if( Double.valueOf(Cate5[1])>Double.valueOf(Cate1[1]) &&  Double.valueOf(Cate5[1])>Double.valueOf(Cate2[1]) && Double.valueOf(Cate5[1])>Double.valueOf(Cate3[1]) && Double.valueOf(Cate5[1])>Double.valueOf(Cate4[1])){
                int o = 0; 
                double []ReplaceArr = new double[allStudents.length];
                for(int t = 0; t < allStudents.length; t++, o++) {
                    if (InfoArr[t][1].equals(Cate5[1])) {
                        ReplaceArr[o] = allStudents[t][Num];
    
                    }
                }
                double av = average(ReplaceArr);
                System.out.println(" For course"+Num + "the student has a grade of"+ av);// be

            }


            


        } catch (Exception ex) {
            ex.printStackTrace();
		}
    }
        
        
public static String[] categroy1class(double [][]allStudents,String[][] InfoArr, int Num, String[] Category1, int Catnum ) throws Exception{
    String[] answer = new String[2];         
    int countfull = 0 ;
            int countmedium = 0 ;
            int countlow = 0 ;
            int countnothing = 0 ;
            double averageFull = 0 ;
            double averagMedium = 0 ;
            double AverageLow = 0 ;
            double AverageNothing = 0 ;
            double average1 = 0 ; 
        for( int i = 0 ; i < 100 ; i++){// this should be at least 5000 times to get accurate reading due to a monte carlo simulation, yet  my computer isn't stro,g enough to handle this 
            allStudents = monteCarlo(true);
            String[] Cate1 = ListBasedParameter(allStudents, InfoArr, Num ,Category1, Catnum); 
            System.out.println(Arrays.toString(Cate1));
            if( Cate1[0].equals("full")){
                countfull++;
                averageFull += Double.parseDouble(Cate1[1] ); 
                
            }
            if( Cate1[0].equals("medium")){
                countmedium++;
                averagMedium += Double.parseDouble(Cate1[1] );
            }
            if( Cate1[0].equals("low")){
                countlow++;
                AverageLow += Double.parseDouble(Cate1[1] );
            }
            if( Cate1[0].equals("nothing")){
                countnothing++;
                AverageNothing += Double.parseDouble(Cate1[1] );
            }
        }
        if( countfull>countlow&&countfull>countmedium && countfull>countnothing){
            System.out.println(" the best value to sort your data out is Full  " );
            average1 = averageFull/countfull ;
            answer[0] = "Full " ;
            answer[1] = Double.toString(average1);
    
            return answer ;

        }
        if( countlow>countfull&&countlow>countmedium && countlow>countnothing){
            System.out.println(" the best value to sort your data out is Low  " );
            average1 = AverageLow/countlow;
            answer[0] = "Low " ;
            answer[1] = Double.toString(average1);
    
            return answer ;

        }
        if( countmedium>countfull&&countmedium>countlow && countmedium>countnothing){
            System.out.println(" the best value to sort your data out is medium  " );
            average1 = averagMedium/countmedium;
            answer[0] = "medium " ;
            answer[1] = Double.toString(average1);
    
            return answer ;

        }
        if( countnothing>countfull&&countnothing>countlow && countnothing>countmedium){
            System.out.println(" the best value to sort your data out is nothing  " );
            average1 = AverageNothing/countnothing;
            answer[0] = "Nothing " ;
            answer[1] = Double.toString(average1);
    
            return answer ;

        }
        return answer;
}
        
public static String[] categroy3class(double [][]allStudents,String[][] InfoArr, int Num, String[] Category3, int Catnum ) throws Exception{
        String[] answer= new String[2];
        int count1tau = 0 ;
        int count2tau = 0 ;
        int count3tau = 0 ;
            double averag1tau = 0 ;
            double average2tau = 0 ;
            double average3tau = 0 ;
            double average2 = 0 ; 
        for( int i = 0 ; i < 100 ; i++){// this should be at least 5000 times to get accurate reading due to a monte carlo simulation, yet  my computer isn't stro,g enough to handle this 
        allStudents = monteCarlo(true);
        String Cate3[] = ListBasedParameter(allStudents, InfoArr, Num ,Category3, Catnum); 
        System.out.println(Arrays.toString(Cate3));
        if( Cate3[0].equals("1 tau")){
            count1tau++;
            averag1tau += Double.parseDouble(Cate3[1] );
        }
        if( Cate3[0].equals("2 tau")){
            count2tau++;
            average2tau += Double.parseDouble(Cate3[1] );

        }
        if( Cate3[0].equals("3 tau")){
            count3tau++;
            average3tau += Double.parseDouble(Cate3[1] );

        }
     }
     if( count1tau>count2tau&&count1tau>count3tau ){
        System.out.println(" the best value to sort your data out is 1 tau  " );
        average2= averag1tau/count1tau;
        answer[0] = "1 tau " ;
        answer[1] = Double.toString(average2);

        return answer ; 
    }
     if( count2tau>count1tau&&count2tau>count3tau ){
        System.out.println(" the best value to sort your data out is 2 tau  " );
        average2 = average2tau/count2tau ; 
        answer[0] = "2 tau " ;
        answer[1] = Double.toString(average2);

        return answer ; 
    }
     if( count3tau>count1tau&&count3tau>count2tau ){
        System.out.println(" the best value to sort your data out is 3 tau  " );
        average2 = average3tau/count3tau;
        answer[0] = "3 tau " ;
        answer[1] = Double.toString(average2);

        return answer ; 
    }
    return answer ;
}
public static String[] categroy4class(double [][]allStudents,String[][] InfoArr, int Num, String[] Category4, int Catnum ) throws Exception{
    String[] answer = new String[2];
    double averagA = 0 ;
    double averageB = 0 ;
    double averageC = 0 ;
    double averageD = 0 ; 
    double averageE = 0 ; 
    double average = 0 ;
    double averageF = 0 ;


        int countA = 0 ;
        int countB = 0 ;
        int countC = 0 ;
        int countD = 0; 
        int countE = 0 ; 
        int countF = 0 ; 
    for( int i = 0 ; i < 100 ; i++){// this should be at least 5000 times to get accurate reading due to a monte carlo simulation, yet  my computer isn't stro,g enough to handle this 
        allStudents = monteCarlo(true);
        String Cate4[] = ListBasedParameter(allStudents, InfoArr, Num ,Category4, Catnum); 
        System.out.println(Arrays.toString(Cate4));
        if( Cate4[0].equals("A")){
            countA++;
            averagA += Double.parseDouble(Cate4[1] );
        }
        if( Cate4[0].equals("B")){
            countB++;
            averageB += Double.parseDouble(Cate4[1] );
        }
        if( Cate4[0].equals("C")){
            countC++;
            averageC += Double.parseDouble(Cate4[1] );
        }
        if( Cate4[0].equals("D")){
            countD++;
            averageD += Double.parseDouble(Cate4[1] );
        }
        if( Cate4[0].equals("E")){
            countE++;
            averageE += Double.parseDouble(Cate4[1] );
        }
        if( Cate4[0].equals("F")){
            countF++;
            averageF += Double.parseDouble(Cate4[1] );
        }
    }
    if( countA>countB&&countA>countC && countA>countD&&countA>countE && countA>countF ){
        System.out.println(" the best value to sort your data out is A  " );
        average = averagA/countA;
        answer[0] = "A " ;
        answer[1] = Double.toString(average);

        return answer ; 
    }
    if( countB>countA&&countB>countC && countB>countD&&countB>countE && countB>countF ){
        System.out.println(" the best value to sort your data out is B  " );
        average = averageB/countB;
        answer[0] = "B " ;
        answer[1] = Double.toString(average);

        return answer ; 
    }
    if( countC>countA&&countC>countB && countC>countD&&countC>countE && countC>countF ){
        System.out.println(" the best value to sort your data out is C  " );
        average = averageC/countC ; 
        answer[0] = "C " ;
        answer[1] = Double.toString(average);

        return answer ; 
        

    }
    if( countD>countA&&countD>countB && countD>countC&&countD>countE && countD>countF ){
        System.out.println(" the best value to sort your data out is D  " );
        average = averageD/countD ;
        answer[0] = "D " ;
        answer[1] = Double.toString(average);

        return answer ; 
    }
    if( countE>countA&&countE>countB && countE>countD&&countE>countC&& countE>countF ){
        System.out.println(" the best value to sort your data out is E  " );
        average = averageE/countE ;
        answer[0] = "E " ;
        answer[1] = Double.toString(average);

        return answer ; 
    }
    if( countF>countA&&countF>countB && countF>countD&&countF>countC&& countF>countE ){
        System.out.println(" the best value to sort your data out is F  " );
        average= averageF/countF ; 
        answer[0] = "F " ;
        answer[1] = Double.toString(average);

        return answer ; 
    }
    return answer ;
}
public static String[] categroy5class(double [][]allStudents,String[][] InfoArr, int Num, String[] Category5, int Catnum ) throws Exception{
    String[] answer = new String[2];    
    int count01 = 0 ;
        int count05 = 0 ;
        int count1 = 0; 
        int count5 = 0 ; 
        double average01 = 0  ; 
        double average05 = 0  ; 
        double average1 = 0  ; 
        double average5 = 0  ; 
        double average = 0 ; 

    for( int i = 0 ; i < 100 ; i++){// this should be at least 5000 times to get accurate reading due to a monte carlo simulation, yet  my computer isn't stro,g enough to handle this 
        allStudents = monteCarlo(true);
        String[] Cate5 = ListBasedParameter(allStudents, InfoArr, Num ,Category5, Catnum); 
        System.out.println(Arrays.toString(Cate5));
        if( Cate5[0].equals("0.1 Hz")){
            count01++;
            average01 += Double.parseDouble(Cate5[1] );
        }
        if( Cate5[0].equals("0.5 Hz")){
            count05++;
            average05 += Double.parseDouble(Cate5[1] );
        }
        if( Cate5[0].equals("1.0 Hz")){
            count1++;
            average1 += Double.parseDouble(Cate5[1] );

        }
        if( Cate5[0].equals("5.0 Hz")){
            count5++;
            average5 += Double.parseDouble(Cate5[1] );

        }

    }
    if( count01>count05&&count01>count1 && count01>count5 ){
        System.out.println(" the best value to sort your data out is 0.1 Hz  " );
        average = average01/count01;
        answer[0] = "0.1Hz " ;
        answer[1] = Double.toString(average);

        return answer ;
    }
    if( count05>count01&&count05>count1 && count05>count5 ){
        System.out.println(" the best value to sort your data out is 0.5 Hz  " );
        average = average05/count05;
        answer[0] = "0.5Hz " ;
        answer[1] = Double.toString(average);

        return answer ;
    }
    if( count1>count01&&count1>count05&& count1>count5 ){
        System.out.println(" the best value to sort your data out is 1.0 Hz  " );
        average = average1/count1;
        answer[0] = "1Hz " ;
        answer[1] = Double.toString(average);

        return answer ;
    }
    if( count5>count01&&count5>count1 && count5>count05 ){
        System.out.println(" the best value to sort your data out is 5.0 Hz  " );
        average = average5/count5 ; 
        answer[0] = "5.0Hz " ;
        answer[1] = Double.toString(average);

        return answer ;
    }
    return answer;
}
public static String[] categroy2class(double [][]allStudents,String[][] InfoArr, int Num, int[] Category2, int Catnum ) throws Exception{
    String[] answer = new String[2];
    int countrange1 = 0 ;
    int countrange2 = 0 ;
    int countrange3 = 0; 
    int countrange4 = 0 ; 
    int countrange5 = 0; 
    double average = 0 ; 
    double average1 = 0 ; 
    double average2 = 0 ; 
    double average3 = 0 ; 
    double average4 = 0 ; 
    double average5 = 0 ; 


    
    

    for( int i = 0 ; i < 100 ; i++){// this should be at least 5000 times to get accurate reading due to a monte carlo simulation, yet  my computer isn't stro,g enough to handle this 
        allStudents = monteCarlo(true);
        double Cate2[] = ListBaseParaCate2(allStudents, InfoArr, Num ,Category2); 
        System.out.println(Arrays.toString(Cate2));
 
        if( Cate2[0] >= -55 && Cate2[0]<= -0){
            countrange2++;
            average2 += Cate2[1] ;
        }
        if( Cate2[0] >= 1 && Cate2[0]<= 50){
            countrange3++;
            average3 += Cate2[1] ;

        }
        if( Cate2[0] >= 51 && Cate2[0]<= 100){
            countrange4++;
            average4 += Cate2[1] ;

        }
        if( Cate2[0] >= 101 && Cate2[0]<= 150){
            countrange5++;
            average5 += Cate2[1] ;

        }


        }


        if( countrange2>countrange1&&countrange2>countrange3 && countrange2>countrange4 && countrange2>countrange5 ){
            System.out.println(" the best value to sort your data out is between -55 to 0   " );
            average = average2/countrange2;
            answer[0] = "-55 to 0  " ;
            answer[1] = Double.toString(average);
    
            return answer ;

        }
        if( countrange3>countrange1&&countrange3>countrange2 && countrange3>countrange4 && countrange3>countrange5 ){
            System.out.println(" the best value to sort your data out is between 1  to 50   " );
            average = average3/countrange3 ; 
            answer[0] = "-1 to 50  " ;
            answer[1] = Double.toString(average);
    
            return answer ;
        }
        if( countrange4>countrange1&&countrange4>countrange2 && countrange4>countrange3 && countrange4>countrange5 ){
            System.out.println(" the best value to sort your data out is between 51 to 100   " );
            average = average4/countrange4;
            answer[0] = "51 to 100  " ;
            answer[1] = Double.toString(average);
    
            return answer ;
        }
        if( countrange5>countrange1&&countrange5>countrange2 && countrange5>countrange3 && countrange5>countrange4 ){
            System.out.println(" the best value to sort your data out is between 101 to 150   " );
            average = average5/countrange5;
            answer[0] = "101 to 150  " ;
            answer[1] = Double.toString(average);
    
            return answer ;
        }


         return answer ;  
    }


      
            

			


    public static double average(double [] allStudents ) {
        double sum = 0; 
        
        for( int i = 0 ; i <allStudents.length ; i++ ){
            sum += allStudents[i] ;

        }
        double average = sum/allStudents.length ; 
 
        return average; 

    }
    public static double TotalVariance ( double[] allStudents , int course){
        double TotalVar= 0 ; 
        double sum = 0 ; 
        double average1Z = average(allStudents);

        for( int i = 0 ; i <allStudents.length ; i++ ){
            sum += Math.pow(Math.abs(allStudents[i] - average1Z), 2);
        }
        TotalVar = sum/allStudents.length ; 

        return TotalVar; 
    }
    public static double calculateWeightedVariance(double[] allStudents, int courses) {// change the parameter to the subCatergory A or whatever to make it specific for what you want.
            int[]  NumCount = new int[11]; 

            // Count the occurrences of each grade
            for (int i = 0; i < allStudents.length; i++) {
                int grade = (int) allStudents[i];
                if (grade >= 0 && grade <= 10) {
                    NumCount[grade]++;
                }
            }
        
            // Calculate the weighted mean using the counts as weights
            double weightedSum = 0;
            double sumWeights = 0;
        
            for (int grade = 0; grade <= 10; grade++) {
                int count = NumCount[grade];
                weightedSum += count * grade; 
                sumWeights += count;          
            }
        
            double weightedMean = weightedSum / sumWeights;
        
            // Calculate the weighted variance
            double varianceSum = 0;
        
            for (int grade = 0; grade <= 10; grade++) {
                int count = NumCount[grade];
                double deviation = grade - weightedMean;
                varianceSum += count * deviation * deviation; 
            }
        
            double weightedVariance = varianceSum / sumWeights;
        
            return weightedVariance;
        }
    public static double VarianceReduction (double[] allStudents , int course ) {
        double TVar =  TotalVariance(allStudents ,  course );
        double WeightR = calculateWeightedVariance(allStudents, course);
        double VarianceRed = 0 ; 
        
        VarianceRed=  (((Math.floor(10000000*(TVar))/10000000) -(Math.floor(1000*(WeightR))/100000))/((Math.floor(1000000*(TVar))/10000000))) *100;


        return VarianceRed ; 

    }
    public static String[]  ListBasedParameter (double[][]allStudents ,String[][] InfoArr, int num , String[] Category, int CatgeroyName) throws Exception{
            double[] ReplaceArr = new double[allStudents.length];
            int min =0;           
            double temp = Double.MAX_VALUE; // Initialize temp to a large value
            int o = 0;
            String [] answer = new String[2];
            double sum = 0 ; 
            
            // Loop over categories
            for (int k = 0; k < Category.length; k++) {
                sum = 0;
                o = 0; // Reset o for each category
                // Build the ReplaceArr based on matching categories
                
                int numtimesCarlo = 10; // should be 5000 but it takes way too long 
                // Monte Carlo simulation loop
                for (int j = 0; j < numtimesCarlo; j++) {
                    allStudents = monteCarlo(true);
                    o=0;
                    for (int t = 0; t < allStudents.length; t++, o++) {
                        if (InfoArr[t][CatgeroyName].equals(Category[k])) {
                            ReplaceArr[o] = allStudents[t][num];
                           
                        }
                    }
                    //System.out.println(Arrays.toString(ReplaceArr));
                    sum = 0;  // Reset sum for each iteration                    
                    double Variance = VarianceReduction(ReplaceArr, num); // Assuming this function is defined
                    sum += Variance;
                }
        
                sum /= numtimesCarlo; // Average the sum over the 10 iterations
                System.out.println("Average Variance for category " + Category[k] + ": " + sum);
                // Update temp and i if sum is smaller than temp
                if (sum < temp) {
                    temp = sum; 
                    min = k; // Store the index of the category with the smallest variance
                }
                
            }
            answer[0] = Category[min];
            answer[1] = Double.toString(temp) ; 
        
            return answer; // Return the category with the smallest average variance
        }
    public static double[]  ListBaseParaCate2 (double[][]allStudents ,String [][] InfoArr, int num , int [] Category) throws Exception{
            double[] ReplaceArr = new double[allStudents.length];
            int min =0;           
            double temp = Double.MAX_VALUE; // Initialize temp to a large value
            int o = 0;
            double[] answer = new double[2];
            double sum = 0;

            // Loop over categories
            for (int k = 0; k < Category.length; k= k+2) {
                o = 0; // Reset o for each category
                // Build the ReplaceArr based on matching categories
                
                 sum = 0;
                int numtimesCarlo = 10;
                // Monte Carlo simulation loop
                for (int j = 0; j < numtimesCarlo; j++) {
                    allStudents = monteCarlo(true);
                    o=0;
                    for (int t = 1; t < allStudents.length; t++, o++) {
                        if (Integer.parseInt(InfoArr[t][2]) >=Category[k] && Integer.parseInt( InfoArr[t][2])<=Category[k+1]) {
                            ReplaceArr[o] = allStudents[t][num];
                           
                        }
                    }
                    //System.out.println(Arrays.toString(ReplaceArr));
                    sum = 0;  // Reset sum for each iteration                    
                    double Variance = VarianceReduction(ReplaceArr, num); // Assuming this function is defined
                    sum += Variance;
                }
        
                sum /= numtimesCarlo; // Average the sum over the 10 iterations
                System.out.println("Average Variance for category " + Category[k] + ": " +Category[k+1]+":"+sum);
                // Update temp and i if sum is smaller than temp
                if (sum < temp) {
                    temp = sum; 
                    min = k; // Store the index of the category with the smallest variance
                }
            }
            answer[0] = Category[min];
            answer[1] = sum ;
        
            return answer; // Return the category with the smallest average variance
        }
	// Stores all info from StudentInfo.csv into a 2d array
	public static String[][] studentInfo() throws Exception{
		// Adapt this when you want to read and display a different file.
		String fileName = "C:\\Users\\marco\\Documents\\ken_group10_2024\\src\\main\\resources\\csv\\StudentInfo.csv";
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
	// Stores all info from CurrentGrades.csv into a 2d array and runs a MonteCarlo simulation
	public static double[][] monteCarlo(boolean simulating) throws Exception {
		// Adapt this when you want to read and display a different file.
		if(simulating == true) {
			String fileName = "C:\\Users\\marco\\Documents\\ken_group10_2024\\src\\main\\resources\\csv\\CurrentGrades.csv";
			File file=new File(fileName);
			
			// This code uses two Scanners, one which scans the file line per line
			Scanner fileScanner = new Scanner(file);
			allStudents = new double[1328][33];
			
		

			int linesDone = -1 ; 
			int coursesDone = 0 ;

		
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
					}  if (lineScanner.hasNextDouble()) {
						allStudents[linesDone][coursesDone++] = lineScanner.nextDouble();
					} else {
						String s = lineScanner.next();
						if(s.equals("NG")){

							allStudents[linesDone][coursesDone++] = -1;

						}
					}
				}
				linesDone++;
				lineScanner.close();
			}
			fileScanner.close();
			//stores the array with the ng (-1) values seperately
			allStudentsNG = new double[allStudents.length][];
			for(int i = 0; i < allStudents.length; i++){
				double[] aMatrix = allStudents[i];
				int aLength = aMatrix.length;
				allStudentsNG[i] = new double[aLength];
				System.arraycopy(aMatrix, 0, allStudentsNG[i], 0, aLength);	
			}
		} else {
			//refreshes the ng (-1) values
			allStudents = new double[allStudentsNG.length][];
			for(int i = 0; i < allStudentsNG.length; i++){
				double[] aMatrix = allStudentsNG[i];
				int aLength = aMatrix.length;
				allStudents[i] = new double[aLength];
				System.arraycopy(aMatrix, 0, allStudents[i], 0, aLength);	
			}
		}

		double[][] allCousesCount = new double[33][11];
		for (int i = 0 ; i<33 ; i++){
			double[] array = reps(allStudents, i);
			for( int j = 0 ; j< 11 ; j++){
				allCousesCount[i][j] = array[j];
			}
		}


		// makin the margins for the randomisation
		double[] ranges = add(allCousesCount, 0);
		int sum = 0 ;// this is going to be the total number or grades; 
		//System.out.println(Arrays.toString(ranges));

		for( int i =0 ; i <ranges.length ; i++){
			sum+= ranges[i];
			
		}

		for( int i = 0 ;  i < allStudents.length ; i++){
			for( int j = 0 ; j< allStudents[0].length ; j++){
				int random = (int)(Math.random()*sum);
				if( allStudents[i][j]== -1.0){
					if(random < ranges[2] && random>=0){
						allStudents[i][j]=2.0 ; 
					}
					if(random <= ranges[3]+ranges[2] && random>=ranges[2]){
						allStudents[i][j]=3.0 ; 

					}
					if(random <= ranges[4]+ranges[3] + ranges[2] && random>=ranges[3]+ ranges[2]){
						allStudents[i][j]=4.0 ; 
					}
					if(random <= ranges[5]+ranges[4]+ranges[3] + ranges[2] && random>=ranges[4]+ranges[3]+ ranges[2]){
						allStudents[i][j]=5.0 ; 
					}
					if(random <= ranges[6]+ ranges[5]+ranges[4]+ranges[3] + ranges[2] && random>=ranges[5]+ranges[4]+ranges[3]+ ranges[2]){
						allStudents[i][j]=6.0 ; 
					}
					if(random <= ranges[7]+ranges[6]+ ranges[5]+ranges[4]+ranges[3] + ranges[2] && random>= ranges[6]+ranges[5]+ranges[4]+ranges[3]+ ranges[2]){
						allStudents[i][j]=7.0 ; 
					}
					if(random <= ranges [8] + ranges[7]+ranges[6]+ ranges[5]+ranges[4]+ranges[3] + ranges[2] && random> ranges[7]+ranges[6]+ranges[5]+ranges[4]+ranges[3]+ ranges[2]){
						allStudents[i][j]=8.0 ; 
					}
					if(random <= ranges [9] +ranges[8]+ ranges[7]+ranges[6]+ ranges[5]+ranges[4]+ranges[3] + ranges[2] && random>=ranges[8]+ranges[7]+ranges[6]+ranges[5]+ranges[4]+ranges[3]+ ranges[2]){
						allStudents[i][j]=9.0 ; 
					}
					if(random <= ranges[10]+ranges [9] +ranges[8]+ ranges[7]+ranges[6]+ ranges[5]+ranges[4]+ranges[3] + ranges[2] && random> ranges[9]+ ranges[8]+ranges[7]+ranges[6]+ranges[5]+ranges[4]+ranges[3]+ ranges[2]){
						allStudents[i][j]=10.0 ; 
					}


				}

			}
			

		}
        return allStudents;
	}

	public static double[] add(double[][] allCousesCount , int invalid){
		double [] count = new double[11] ; 
		double c = 0 ; 
		for( int i = 0 ; i<11 ; i++){
			for( int j = 0; j<33; j++){
			c += allCousesCount[j][i] ;
			}
			count[i]= c ; 
			c=0; 
		}
		return count; 
	}

	public static double[] reps( double[][] allStudents , int classc){
		
		double[] course = new double [11];


		int num0 = 0 ; 
		int num1 = 0 ;
		int num2 = 0 ; 
		int num3 = 0 ; 
		int num4 = 0 ; 
		int num5 = 0 ; 
		int num6 = 0 ; 
		int num7 = 0 ; 
		int num8 = 0 ; 
		int num9 = 0 ; 
		int num10 = 0 ; 
		
			
		for( int j = 0 ; j< allStudents.length ; j++){
			if( allStudents[j][classc] == 0){
				course[0] = num0++ ;
			}
			if( allStudents[j][classc] == 1 ){
				course[1] = num1++ ;
			}
			if( allStudents[j][classc] == 2){
				course[2] = num2++ ;
			}
			if( allStudents[j][classc] == 3 ){
				course[3] = num3++ ;
			}
			if( allStudents[j][classc] == 4 ){
				course[4] = num4++ ;
			}
			if( allStudents[j][classc] == 5 ){
				course[5] = num5++ ;
			}
			if( allStudents[j][classc] == 6 ){
				course[6] = num6++ ;;
			}
			if( allStudents[j][classc] == 7 ){
				course[7] = num7++ ;
			}
			if( allStudents[j][classc] == 8 ){
				course[8] = num8++ ;
			}
			if( allStudents[j][classc] == 9 ){
				course[9] = num9++ ;
			}
			if( allStudents[j][classc] == 10 ){
				course[10] = num10++ ;
			}
			else{

			}

		}
		return course ;
	}
}



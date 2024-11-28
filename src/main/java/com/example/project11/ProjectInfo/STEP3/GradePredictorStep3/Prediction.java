package com.example.project11.ProjectInfo.STEP3.GradePredictorStep3;

import com.example.project11.ProjectInfo.loaders.WeightedBootstrapping;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner ;
import java.util.Locale.Category; 

class Prediction{
    public static double[][] bootStrapping ;
    public static int course ; 
    public static String [][] infoInStrings;
    public static int min ;
    public static int max ;
    public static int amountDiff ;
    public static String[] Category1 = {"full", "medium","nothing",  "low", "high"};
    public static String[] Category3 = { "2 tau","1 tau", "3 tau"};
    public static int[]    Category2 = {-45 ,-16, -15, 15, 16,  45,46, 75,76,  105,106, 135,136, 165};
    public static String[] Category4 = {  "C","A", "D","B", "E", "F"};
    public static String[] Category5 = {"0.1 Hz ", "0.5 Hz ", "1.0 Hz ", "5.0 Hz "};
    public static int  numRows = 1329;
    public static int  numCols = 33;
    public static String  StudentID ; 
    public static double[][]allStudents2 = new double[1329][33];
    public static double prediction =0 ; 

    public Prediction(String StudentID , int Course){
        this.StudentID = StudentID ;
        this.course = Course; 
    }
               

        
        public double getPrediction() throws Exception{
                    //int course = sc.nextInt();
        // Adapt this when you want to read and display a different file.
        File file = new File("C:\\Users\\marco\\Documents\\Project 1-1\\CODE\\STEP3 2\\Step3\\CurrentGrades.csv");

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
                    allStudents2[linesDone][coursesDone++] = lineScanner.nextDouble();
                } else {
                    String s = lineScanner.next();
                    if (s.equals("NG")) {

                        allStudents2[linesDone][coursesDone++] = newNg;

                    }
                }
            }
            linesDone++;
            lineScanner.close();
        }


            infoInStrings = studentInfo();

        
            
            String[] answer = checker();
             // true + not(-1) == student with grade 
        // true + -1 == student without grade 
        // false  == studen does not exist 
            
			if (answer[0].equals("false")){
                System.out.println("Invalid ID");
                return -1; 
			}
			else if(Double.parseDouble(answer[1]) == -1.0 && answer[0].equals("true")){
                double val = predictor();
                System.out.println("The Grade for the student "+ StudentID+ " is "+ val);
                return val ;
                
			}
			else if (Double.parseDouble(answer[1]) != -1.0 && answer[0].equals("true")){
                System.out.println("The Grade for the student "+ StudentID+ " is "+ answer[1]);
                return Double.parseDouble(answer[1]);
			}
            return -1; 
        }
        public static double getAverage () {
            double sum = 0 ; 
            for( int i = 0 ; i<bootStrapping.length  ; i++){
                sum += bootStrapping[i][course];
            }

            return (double)sum/(double)bootStrapping.length;
        }
        public static double getAverageSpe (ArrayList<Double> arr)throws Exception{
            double sum = 0 ; 
            for( int i = 0 ; i<arr.size()  ; i++){
                sum+= arr.get(i);
            }
            return (double)sum/(double)arr.size();
        }
        public static double getTotalVariance()throws Exception{
            double sum = 0 ; 
            int count = 0 ;
            double average =  getAverage();
            for(int i = 0 ; i<bootStrapping.length ; i++){
                sum += (bootStrapping[i][course] - average )*(bootStrapping[i][course] - average );
                count++;
            }

            return (double)sum/(double)count ; 
        }
        public static double getTotalVarianceSpe(ArrayList<Double> arr)throws Exception{
            double sum = 0 ; 
            int count = 0 ;
            double average =  getAverageSpe(arr);

            for(int i = 0 ; i<arr.size() ; i++){
                sum+= ((double)arr.get(i) - ((double)average))  *  ((double)arr.get(i) - ((double)average));
                count++;
            }
            return (double)sum/arr.size() ; 
        }
        public static double getWeightedVariance(String Name , int CategoryNum)throws Exception{
            ArrayList<Double> grades = new ArrayList<>();
            ArrayList<Double> Notgrades = new ArrayList<>(); 
            double count1= 0 ;
            double count2 = 0 ; 


            for( int i = 0  ; i<bootStrapping.length ; i++){
                if(Name.equals(infoInStrings[i][CategoryNum]) == true){
                    grades.add(bootStrapping[i][course]);
                    count1++;

                }
                else if(Name.equals(infoInStrings[i][CategoryNum]) == false) {
                    Notgrades.add(bootStrapping[i][course]);
                    count2++;

                }
            }
            double totalVar = getTotalVarianceSpe(grades);
            double notTotalVar = getTotalVarianceSpe(Notgrades);


            double weight = count1/(double)bootStrapping.length;
            double notWeight = count2/(double)bootStrapping.length ; 

        

            double weightedVar = ((double)weight*(double)totalVar)+((double)notWeight*(double)notTotalVar);
            return weightedVar;
        }
        public static double getVarianceReduction ( double TotalVar , double WeightedVar)throws Exception{
            double varianceReduction = ((double)TotalVar - (double)WeightedVar)*1000 ; 
            return (varianceReduction);
        }
        public static double getVarianceReductionAverage(String Name, int CategoryNum)throws Exception {
            double sum = 0; // To accumulate variance reduction values
            int bootstrapIterations = 75; // Number of bootstrap iterations
        
            for (int i = 0; i < bootstrapIterations; i++) {
                WeightedBootstrapping2 brootstrapping = new WeightedBootstrapping2(course, allStudents2);
                bootStrapping = brootstrapping.readAllStudents();
        
                double totalVariance = getTotalVariance();
                double weightedVariance = getWeightedVariance(Name, CategoryNum);
                double varianceReduction = getVarianceReduction(totalVariance, weightedVariance);
        
                sum += varianceReduction;
            }
            System.out.println("Variance Reduction individual "+sum/bootstrapIterations);
            return sum / bootstrapIterations;
        }
        public static String [] getBestOutOfCategory(String[] Category, int CategoryNum) throws Exception{
            String [] answer = new String[2];
            double [] arr  = new double[Category.length ];

            for(int i = 0 ; i<Category.length ; i++){
                arr[i] = getVarianceReductionAverage(Category[i], CategoryNum);
            }
            double max = 0 ; 
            int index = 0 ;
            for( int i = 0 ; i<Category.length ; i++){
                if( arr[i]> max){
                    max = arr[i];
                    index = i ; 
                }

            }
            answer[0] = Integer.toString(index) ; 
            answer[1] = Double.toString(max);
            return answer; 
        }
        

        public static double[] getWeightedVariance2(int[] Category) throws Exception{

            ArrayList<Double> boundery = new ArrayList<>();
            ArrayList<Double> NotBoundery = new ArrayList<>();
            double[] WeightedVar = new double[Category.length/2];

            int c = 0 ; 

            for( int j = 0 ; j<WeightedVar.length ; j+=1){
                for (int i = 1 ; i<infoInStrings.length ; i++){
                    if(Double.parseDouble(infoInStrings[i][2])>=Category[j*2] && Double.parseDouble(infoInStrings[i][2])<= Category[j*2+1]){
                        boundery.add(bootStrapping[i][course]);
                    }
                    else{
                        NotBoundery.add(bootStrapping[i][course]);
                    }
                    
 


                }
     
                    
                    double VarianceSpe = getTotalVarianceSpe(boundery);
                    double NotVarianceSpe = getTotalVarianceSpe(NotBoundery);

                    
                    double weight = (double)boundery.size()/(double)bootStrapping.length  ; 
                    double Notweight = (double)NotBoundery.size()/(double)bootStrapping.length  ; 
                    
                    double WeightedVarVal = (weight*VarianceSpe) + (Notweight*NotVarianceSpe);

                    WeightedVar[j] = WeightedVarVal ; 
                    c++;
                    boundery.clear();
                    NotBoundery.clear();

            }
 
            

            return WeightedVar ;           

        }
        
        public static String[] getVarianceReductionAverage2(int[] Category)throws Exception {
            double sum = 0; // To accumulate variance reduction values
            int bootstrapIterations = 100; // Number of bootstrap iterations
            double[] varianceReduction = new double[7];
        
            for (int i = 0; i < bootstrapIterations; i++) {
                WeightedBootstrapping2 brootstrapping = new WeightedBootstrapping2(course , allStudents2);
                bootStrapping = brootstrapping.readAllStudents();
        
                double totalVariance = getTotalVariance();
                double[] weightedVariance = getWeightedVariance2(Category);
                for ( int j = 0  ; j< weightedVariance.length ; j++){
                    varianceReduction[j] += getVarianceReduction(totalVariance, weightedVariance[j]);


                }
                
            }
            for( int i = 0 ; i<varianceReduction.length ;i++){
                varianceReduction[i]= varianceReduction[i]/bootstrapIterations;
            }
            System.out.println("Variance Reduction list "+ Arrays.toString(varianceReduction));

            int index = 0 ;
            double max3 = 0 ; 

            for( int i = 0 ; i<varianceReduction.length ; i++){
                if (varianceReduction[i]>max3){
                    index = i ; 
                    max3 = varianceReduction[i];
                }
            }
            String answer[] = new String[2];
            answer[0] = Integer.toString(index);
            answer[1] = Double.toString(max3);

            return answer;
        }

        public static double[][] getBestOFBest(String[] Variance1 , String[] Variance2 , String[] Variance3 , String[] Variance4, String[] Variance5){
            double[][]arr = {{Double.parseDouble(Variance1 [0]),Double.parseDouble(Variance1 [1]) ,1}, {Double.parseDouble(Variance2 [0]),Double.parseDouble(Variance2 [1]) ,2} , {Double.parseDouble(Variance3 [0]),Double.parseDouble(Variance3 [1]) ,3}, {Double.parseDouble(Variance4 [0]),Double.parseDouble(Variance4 [1]) ,4}, {Double.parseDouble(Variance5 [0]),Double.parseDouble(Variance5 [1]) ,5}};
            // Variance [0] == index in the Category Array
            // Variance[1] == Variance Reduction 
            //The number which corresponds to the category 
            double temp = 0 ; 
            double index = 0 ; 
            
            Arrays.sort(arr, (a, b) -> Double.compare(b[1], a[1]));

            return arr ;

        }

        public static double predictor() throws Exception{

            String[] answer = checker();
            String[] answerArr = getBestOutOfCategory(Category1 , 1);
            String[] answerArr2 = getVarianceReductionAverage2(Category2);
            String[] answerArr3 = getBestOutOfCategory(Category3 , 3);
            String[] answerArr4 = getBestOutOfCategory(Category4 , 4);
            String[] answerArr5 = getBestOutOfCategory(Category5 , 5);

            ArrayList<String> Category1List = new ArrayList<>(Arrays.asList(Category1));
            ArrayList<Integer> Category2List = new ArrayList<>(Arrays.asList(-150, -100, -99, -50, -49, -1, 0, 50, 51, 100, 101, 150));
            ArrayList<String> Category3List = new ArrayList<>(Arrays.asList(Category3));
            ArrayList<String> Category4List = new ArrayList<>(Arrays.asList(Category4));
            ArrayList<String> Category5List = new ArrayList<>(Arrays.asList(Category5));




            //[[3.0, 1.9659695566871795, 2.0], [3.0, 1.0210578741461427, 4.0], 
            //[2.0, 0.9450172313672754, 1.0], [1.0, 0.8358140320626894, 5.0], 
            //[1.0, 0.691538137472687, 3.0]]
            double index = 0 ; 
            double[][] grade = getBestOFBest( answerArr ,answerArr2,answerArr3,answerArr4,answerArr5);
            for( int c = 0  ; c<3  ; c++){
                for (int i = 0 ; i<grade.length ; i++){
                    for (int j = 1; j< infoInStrings.length ; j++){
                        if(grade[i][2] == 1){
                            if( infoInStrings[j][1].equals(Category1[(int)grade[i][0]])){
                                return getGrade(Category1[(int) grade[i][0]], 1);
                            }
                        }
                        if(grade[i][2] == 2){
                            if( (Integer.parseInt(infoInStrings[j][2]))>=(Category2[(int)grade[i][0]]) && (Integer.parseInt(infoInStrings[j][2]))<=(Category2[(int)grade[i][0]+1])){
                                return getGrade2((int)grade[i][0]);
                            }
                            
                        }
                        if(grade[i][2] == 3){
                            if( infoInStrings[j][3].equals(Category3[(int)grade[i][0]])){
                                return getGrade(Category3[(int) grade[i][0]], 3);
                            }
                            
                        }
                        if(grade[i][2] == 4){
                            if( infoInStrings[j][4].equals(Category4[(int)grade[i][0]])){
                                return getGrade(Category4[(int) grade[i][0]], 4);
                            }
                            
                        }
                        if(grade[i][2] == 5){
                            if( infoInStrings[j][5].equals(Category5[(int)grade[i][0]])){
                                return getGrade(Category5[(int) grade[i][0]], 5);
                            }
                            
                        }
         
                    }
                    Category1List.remove(grade[i][0]);
                            Category2List.remove(grade[i][0]);
                            Category2List.remove(grade[i][0]+1);
                            Category3List.remove(grade[i][0]);
                            Category4List.remove(grade[i][0]);
                            Category5List.remove(grade[i][0]);

                            String [] Category1Updated= Category1List.stream().toArray(String[]::new);
                            int[] Category2Updated= Category2List.stream().mapToInt(Integer::intValue).toArray();
                            String [] Category3Updated = Category3List.stream().toArray(String[]::new);
                            String [] Category4Updated = Category4List.stream().toArray(String[]::new);
                            String [] Category5Updated = Category5List.stream().toArray(String[]::new);

                             answerArr = getBestOutOfCategory(Category1Updated , 1);
                             answerArr2 = getVarianceReductionAverage2(Category2Updated);
                             answerArr3 = getBestOutOfCategory(Category3Updated , 3);
                             answerArr4 = getBestOutOfCategory(Category4Updated , 4);
                             answerArr5 = getBestOutOfCategory(Category5Updated , 5);
                             grade = getBestOFBest( answerArr ,answerArr2,answerArr3,answerArr4,answerArr5);


                }
                
            }
               
            return -1; 
        }
         
      
        public static String[] checker(){
            String[] result = new String[2];
                for(int i = 1 ; i<infoInStrings.length ; i++){
                    if( StudentID.equals(infoInStrings[i][0])){
                        if( allStudents2[i-1][course] != -1.0){
                            result[0] = "true" ; 
                            result[1] = String.valueOf(allStudents2[i-1][course]);
                            return result;
                        }
                        else{
                            result[0] = "true";
                            result[1] = "-1.0" ; 
                            return result; 
                        }
                        
                    }


                }
                result[0] = "false" ; 
                result[1] = "0";
                return result ; 
	
        }
        public static double getGrade(String CatgeroyName , int CatgeroyNum){
            ArrayList<Double> ArrList = new ArrayList<>(); 
            double[] counter= new double[11];

            for( int i =0 ; i <infoInStrings.length ; i++){
                if(CatgeroyName.equals(infoInStrings[i][CatgeroyNum])){
                    ArrList.add(bootStrapping[i][course]);



                }
            }
            for( int i = 0 ; i < ArrList.size() ; i++){
                for( int j = 0 ; j<counter.length ; j++ ){
                    if( ArrList.get(i) == j ){
                        counter[j]++ ;
                    }


                }
            }
            double biggest = 0 ; 
            double index  = 0 ; 
            for( int i = 0 ; i<counter.length ; i++){
                if( counter[i]>biggest){
                    biggest = counter[i] ; 
                    index = i ; 

                }
            } 
            return index ;           

        }
        public static double getGrade2(int index){
            ArrayList<Double> ArrList = new ArrayList<>(); 
            double[] counter= new double[11];

            for( int i = 1 ; i <infoInStrings.length ; i++){
                if((Integer.parseInt(infoInStrings[i][2]))>=Category2[index] && (Integer.parseInt(infoInStrings[i][2]))<= Category2[index+1] ){
                    ArrList.add(bootStrapping[i][course]);
                }
            }
            for( int i = 0 ; i < ArrList.size() ; i++){
                for( int j = 0 ; j<counter.length ; j++ ){
                    if( ArrList.get(i) == j ){
                        counter[j]++ ;
                    }


                }
            }
            double biggest = 0 ; 
            double index1  = 0 ; 
            for( int i = 0 ; i<counter.length ; i++){
                if( counter[i]>biggest){
                    biggest = counter[i] ; 
                    index1 = i ; 

                }
            } 
            return index1 ;           

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
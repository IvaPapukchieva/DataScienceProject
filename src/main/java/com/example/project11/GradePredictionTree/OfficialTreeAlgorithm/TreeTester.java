package com.example.project11.GradePredictionTree.OfficialTreeAlgorithm;


import com.example.project11.ProjectInfo.loaders.StudentInfoLoader;
import com.example.project11.ProjectInfo.loaders.WeightedBootstrapping;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TreeTester {

    private int amountOfStudents;
    private double[][] CategoryArray  = {{0,1,2,3,4},{1},{1,2,3},{0,1,2,3,4,5},{1.0,5.0,0.5,0.1},};
    private String[][] CategoryArrayInString  = {{"full", "nothing", "medium","low", "high"},{},{"1 tau", "2 tau", "3 tau"},{"A","B","C","D","E","F"},{"0.1 Hz ", "0.5 Hz ", "1.0 Hz ","5.0 Hz "}};

    //We need to transform the studentInfo Array into a double[][] Array, so the tree can use it
    private double[][] studentInfoArrayInDouble ;
    private String[][] studentInfoArray;

    private double[][] weightedBootstrappingArray ;
    private double []  gradesForOneCourse;

    private double[] ArrayOf80PercentIndex ;

    private int Course;

    public TreeTester( int amountOfStudents, int Course) throws FileNotFoundException {

        this.Course = Course;
        this.amountOfStudents = amountOfStudents;


        StudentInfoLoader studentInfoLoader  = new StudentInfoLoader();
        studentInfoArray = studentInfoLoader.readInfoString();

        WeightedBootstrapping weightedBootstrapping = new WeightedBootstrapping();
        weightedBootstrappingArray = weightedBootstrapping.readAllStudents();

        gradesForOneCourse = new double[weightedBootstrappingArray.length];

        ArrayOf80PercentIndex = new double[(int)(weightedBootstrappingArray.length*0.8)];

        // intitialising the studentInfoArray in double with the appropriate length ;
        studentInfoArrayInDouble = new double[studentInfoArray.length][studentInfoArray[0].length-1];

        // populates GradesForOneCourse
        for(int i =0  ;i<weightedBootstrappingArray.length ; i++){
            gradesForOneCourse[i]= weightedBootstrappingArray[i][Course];
        }

}



    // method that transfers the String studentinfoArray into a 2D array of doubles
    public double[][] transferStudentInfoArray(){
        for(int i = 1 ; i<studentInfoArrayInDouble.length ; i++){
            for( int j =0 ; j<studentInfoArrayInDouble[0].length; j++){
                if(j == 0 ) {
                    for (int c = 0; c < CategoryArrayInString[0].length; c++) {
                        if (studentInfoArray[i][j + 1].equals(CategoryArrayInString[0][c])) {
                            studentInfoArrayInDouble[i][j] = c;
                        }
                    }
                }
                if(j == 1){
                    studentInfoArrayInDouble[i][j] = Double.parseDouble(studentInfoArray[i][j+1]);
                }
                if( j == 2){
                    studentInfoArrayInDouble[i][j] = Double.parseDouble(studentInfoArray[i][j+1].substring(0,1));
                }
                if(j == 3){
                    for(int c = 0 ; c<CategoryArrayInString[3].length; c++){
                        if(studentInfoArray[i][j+1].equals(CategoryArrayInString[3][c])){
                            studentInfoArrayInDouble[i][j] = c ;
                        }
                    }
                }
                if( j == 4){
                    studentInfoArrayInDouble[i][j] = Double.parseDouble(studentInfoArray[i][j+1].substring(0,4));
                }
            }
        }
        return studentInfoArrayInDouble;
    }

    // gets the random 80 percent
    public double[] getRandom80percent(){
        Random random = new Random();
        Set<Integer> uniqueIndices = new HashSet<>();
        int targetSize = (int) (gradesForOneCourse.length * 0.8);

        // Generate unique random values
        while (uniqueIndices.size() < targetSize) {
            int val = random.nextInt(1329) + 1; // Random value between 1 and poolSize
            uniqueIndices.add(val); // Add to the set (ignores duplicates automatically)
        }

        // Convert the set to an array
        int[] result = new int[uniqueIndices.size()];
        int i = 0;
        for (int index : uniqueIndices) {
            ArrayOf80PercentIndex[i++] = index;
        }
        return ArrayOf80PercentIndex;
    }




    // gets the remaining 20 percent of grades for testing
    public double[] getRandom20percent(){

        double[] ArrayOf20percent = new double[(int)(gradesForOneCourse.length*0.2)+1];
        int n = 0 ;
        for(int i = 1; i <ArrayOf80PercentIndex.length; i++){
            if(ArrayOf80PercentIndex[i]-ArrayOf80PercentIndex[i-1] != 1){
                for(int j = (int)ArrayOf80PercentIndex[i-1]+1; j <ArrayOf80PercentIndex[i]; j++){
                    ArrayOf20percent[n] = j;
                    n+=1;
                }
            }
        }
        return ArrayOf20percent;
    }


    //Creates a set of imaginary students such that we have a larger data set to test the model.
    public double[][] getArrayOfImagianryStudents(){
        double[][]StudentArray = new double[amountOfStudents][4];
        for(int i = 0; i < amountOfStudents; i++){
            Student student = new Student(CategoryArray);
            StudentArray[i] = student.finalValues();
        }
        return StudentArray;
    }



}


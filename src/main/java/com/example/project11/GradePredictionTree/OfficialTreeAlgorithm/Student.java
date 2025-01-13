package com.example.project11.GradePredictionTree.OfficialTreeAlgorithm;

import com.example.project11.ProjectInfo.loaders.StudentInfoLoader;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;


public class Student {

    private String[][] CategoryArrayInString ;
    private List<List<String>> CategoryListInStringlist = new ArrayList<>();

    private int length2;
    private String[][] studentInfoArray;
    //private double[][] CategoryArray;

    public Student(String[][] studentInfoArrayInput) throws FileNotFoundException {


        this.studentInfoArray  = studentInfoArrayInput;

        Set<String> uniqueProperty2 = new HashSet<>();

        for (int i = 0 ; i<studentInfoArray.length ; i++) {
            uniqueProperty2.add(studentInfoArray[i][2]); // Add to the set (ignores duplicates automatically)
        }
        List<String> listFor2 = new ArrayList<>(uniqueProperty2);
        // for the experiments you can use this to change the randomness
        CategoryListInStringlist.add(List.of("full", "nothing", "medium", "low", "high"));
        CategoryListInStringlist.add(listFor2); // Empty row
        CategoryListInStringlist.add(List.of("1 tau", "2 tau", "3 tau"));
        CategoryListInStringlist.add(List.of("A", "B", "C", "D", "E", "F"));
        CategoryListInStringlist.add(List.of("0.1 Hz", "0.5 Hz", "1.0 Hz", "5.0 Hz"));



        length2 = listFor2.size();
        CategoryArrayInString = transform2DList(CategoryListInStringlist);


       // this.CategoryArray = CategoryArray;
    }

    //Method to transform the list into a 2D array
    public String[][] transform2DList(List<List<String>> CategoryListInString){
        String[][] transformedArray = new String[CategoryListInString.size()][];

        for(int i = 0; i < CategoryListInString.size(); i++){
            transformedArray[i] = CategoryListInString.get(i).toArray(new String[0]);
        }

        return transformedArray;
    }

    //sets Category 1 to a random value
    public String Category1RandomValue(){
        Random random = new Random();
        int randomNumber = random.nextInt(5);
        String cat1 = CategoryArrayInString[0][randomNumber];
        return cat1;
    }

    //sets Category 2 to a random value
    public String Category2RandomValue(){
        Random random = new Random();
        int randomNumber = random.nextInt(length2);
        String cat2 = CategoryArrayInString[1][randomNumber];
        return cat2;
    }

    //sets Category 3 to a random value
    public String Category3RandomValue(){
        Random random = new Random();
        int randomNumber = random.nextInt(3);
        String cat2 = CategoryArrayInString[2][randomNumber];
        return cat2;
    }

    //sets Category 4 to a random value
    public String Category4RandomValue(){
        Random random = new Random();
        int randomNumber = random.nextInt(6);
        String cat3 = CategoryArrayInString[3][randomNumber];
        return cat3;
    }

    //sets Category 5 to a random value
    public String Category5RandomValue(){
        Random random = new Random();
        int randomNumber = random.nextInt(4);
        String cat4 = CategoryArrayInString[4][randomNumber];
        return cat4;
    }

    //Creates an array with all the final random values for each student
    public String[] finalValues(){
        String[]Student = new String[5];
        Student[0] = Category1RandomValue();
        Student[1] = Category2RandomValue();
        Student[2] = Category3RandomValue();
        Student[3] = Category4RandomValue();
        Student[4] = Category5RandomValue();
        return Student;
    }

}

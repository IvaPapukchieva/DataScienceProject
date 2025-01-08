package com.example.project11.GradePredictionTree.TreeAlgorithm;

import java.util.Random;

public class Student {
    private String[][] CategoryArray  = {{"full", "nothing", "medium","low", "high"},{"1 tau", "2 tau", "3 tau"},{"A","B","C","D","E","F"},{"0.1 Hz ", "0.5 Hz ", "1.0 Hz ","5.0 Hz "}};

    public Student(String[][] CategoryArray){
        this.CategoryArray = CategoryArray;
    }

    //sets Category 1 to a random value
    public String Category1RandomValue(String[][]CategoryArray){
        Random random = new Random();
        int randomNumber = random.nextInt(5);
        String cat1 = CategoryArray[0][randomNumber];
        return cat1;
    }
    public String Category2RandomValue(String[][]CategoryArray){
        Random random = new Random();
        int randomNumber = random.nextInt(3);
        String cat2 = CategoryArray[1][randomNumber];
        return cat2;
    }
    public String Category3RandomValue(String[][]CategoryArray){
        Random random = new Random();
        int randomNumber = random.nextInt(6);
        String cat3 = CategoryArray[2][randomNumber];
        return cat3;
    }
    public String Category4RandomValue(String[][]CategoryArray){
        Random random = new Random();
        int randomNumber = random.nextInt(4);
        String cat4 = CategoryArray[3][randomNumber];
        return cat4;
    }
    public String[] finalValues(){
        String[]Student = new String[4];
        Student[0] = Category1RandomValue(CategoryArray);
        Student[1] = Category2RandomValue(CategoryArray);
        Student[2] = Category3RandomValue(CategoryArray);
        Student[3] = Category4RandomValue(CategoryArray);
        return Student;
    }

}

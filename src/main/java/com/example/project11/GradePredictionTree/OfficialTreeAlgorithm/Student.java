package com.example.project11.GradePredictionTree.OfficialTreeAlgorithm;

import java.util.Random;

public class Student {
    private double[][] CategoryArray;

    public Student(double[][] CategoryArray){

        this.CategoryArray = CategoryArray;
    }

    //sets Category 1 to a random value
    public double Category1RandomValue(){
        Random random = new Random();
        int randomNumber = random.nextInt(5);
        double cat1 = CategoryArray[0][randomNumber];
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

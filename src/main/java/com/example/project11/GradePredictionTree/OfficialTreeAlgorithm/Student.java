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
    public double Category2RandomValue(){
        Random random = new Random();
        int randomNumber = random.nextInt(4);
        double cat2 = CategoryArray[2][randomNumber];
        return cat2;
    }
    public double Category3RandomValue(){
        Random random = new Random();
        int randomNumber = random.nextInt(6);
        double cat3 = CategoryArray[3][randomNumber];
        return cat3;
    }
    public double Category4RandomValue(){
        Random random = new Random();
        int randomNumber = random.nextInt(4);
        double cat4 = CategoryArray[4][randomNumber];
        return cat4;
    }
    public double[] finalValues(){
        double[]Student = new double[4];
        Student[0] = Category1RandomValue();
        //Student[1] = Category2RandomValue();
        Student[2] = Category3RandomValue();
        Student[3] = Category4RandomValue();
        return Student;
    }

}

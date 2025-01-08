package com.example.project11.GradePredictionTree.OfficialTreeAlgorithm;

public class TreeTester {
    private int amountOfStudents;
    public TreeTester( int amountOfStudents){
        this.amountOfStudents = amountOfStudents;
    }

    public String[][] ArrayOfStudents(){
        String[][]StudentArray = new String[amountOfStudents][4];
        String[][] CategoryArray  = {{"full", "nothing", "medium","low", "high"},{"1 tau", "2 tau", "3 tau"},{"A","B","C","D","E","F"},{"0.1 Hz ", "0.5 Hz ", "1.0 Hz ","5.0 Hz "}};
        for(int i = 0; i < amountOfStudents; i++){
            Student student = new Student(CategoryArray);
            StudentArray[i] = student.finalValues();
        }
        return StudentArray;
    }
}


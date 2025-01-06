package com.example.project11.GradePredictionTree.TreeAlgorithm;



import com.example.project11.ProjectInfo.loaders.StudentInfoLoader;
import com.example.project11.ProjectInfo.loaders.WeightedBootstrapping;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GradeDistribution<T>{

    private ArrayList<Double> gradeDistribution;

    //Parameters that hae to be put int the constructor
    private int Course;
    private T Property ;
    private int indexOfCategory;

    private int amountOfStudents =1328 ;

    //StudentPropertyArray
    private String[][] StudentPropertyArray ;

    //Weighted Bootsrapped Array
    private final double[][] bootstrappedArray ;



    public GradeDistribution(int Course,  T Property, int indexOfCategory) throws FileNotFoundException {

        //Loads the StudentInfo Properties
        StudentInfoLoader studentInfoLoader = new StudentInfoLoader();
        StudentPropertyArray = studentInfoLoader.readInfoString();

        //Loads the Weighted Bootsrapped Array
        WeightedBootstrapping weightedBootstrapping = new WeightedBootstrapping();
        bootstrappedArray = weightedBootstrapping.readAllStudents();


        this.Course = Course ;
        this.Property = Property;
        this.indexOfCategory = indexOfCategory;


    }


    //Counts Occurences of the grade
    public int[] getGradeCount(int Course , T Property, int indexOfCategory){
        int [] gradeCount = new int[11]; // gets the occurances of a specific grade


        // for category that is not 2
        if(Property instanceof String){
            for(int i = 0; i<bootstrappedArray.length ; i++){
                if(StudentPropertyArray[i][indexOfCategory].equals((String)Property)){
                    gradeCount[(int)bootstrappedArray[i][Course]]++;

                }
            }
            return gradeCount;
        }
        //For Category 2
        if(Property instanceof Integer){
            for(int i = 1; i<bootstrappedArray.length ; i++){
                if(Integer.parseInt(StudentPropertyArray[i][2]) == (Integer)(Property)){
                    gradeCount[(int)bootstrappedArray[i][Course]]++;


                }
            }
            return gradeCount;


        }
        //for general grade distribution of the course
        if(Property instanceof Boolean){
            for(int i = 0 ; i<bootstrappedArray.length ; i++){
                gradeCount[(int)bootstrappedArray[i][Course]]++;

            }
            return gradeCount;

        }
        return gradeCount;
    }









}

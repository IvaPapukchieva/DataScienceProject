package com.example.project11.ProjectInfo.STEP4;

import com.example.project11.ProjectInfo.loaders.StudentInfoLoader;

import java.util.ArrayList;
import java.util.Arrays;

class Main{
        public static void main(String[] args) throws Exception{

            StudentInfoLoader STL = new StudentInfoLoader();
            String[][] studentPropertyArray  = STL.readInfoString();

                    prediction effPrediction = new prediction(0, "210333");
                    System.out.println((effPrediction.getPrediction()));






            
        }
    }

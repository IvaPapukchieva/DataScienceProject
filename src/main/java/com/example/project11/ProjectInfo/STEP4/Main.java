package com.example.project11.ProjectInfo.STEP4;

import java.util.Arrays;

class Main{
        public static void main(String[] args) throws Exception{
            // make it more efficient by checking the state of the course and if it is the same i do not have to invoke certain methods 
            // else if it is different i need to invoke those methods, but at least it works :)

            prediction effPrediction = new prediction(0 , " ");
     

            System.out.println(Arrays.deepToString(effPrediction.getPrediction()));

            
        }
    }

package com.example.project11.ProjectInfo.loaders;

import java.io.FileNotFoundException;

public class AverageWeightedBootstrapping {
    private double[][] WeighedtBootstrappingArray  ;


    public AverageWeightedBootstrapping() throws FileNotFoundException {
        WeightedBootstrapping weightedBootstrapping = new WeightedBootstrapping();
        WeighedtBootstrappingArray = weightedBootstrapping.readAllStudents();

    }
    public double[][] getWeightedBootstrappingAverage(int Itterations) throws FileNotFoundException {
        double[][] weightedbootstrappingAverage = new  double[WeighedtBootstrappingArray.length][WeighedtBootstrappingArray[0].length];
        for( int i = 0 ; i<Itterations; i++){
            WeightedBootstrapping weightedBootstrapping = new WeightedBootstrapping();
            WeighedtBootstrappingArray = weightedBootstrapping.readAllStudents();
            for( int j = 0 ;j<WeighedtBootstrappingArray.length;j++){
                for( int c = 0 ; c<WeighedtBootstrappingArray[0].length ; c++){
                    weightedbootstrappingAverage[j][c] +=WeighedtBootstrappingArray[j][c];

                }
            }

        }
        for( int j = 0 ;j<WeighedtBootstrappingArray.length;j++){
            for( int c = 0 ; c<WeighedtBootstrappingArray[0].length ; c++){
                weightedbootstrappingAverage[j][c] = ((double)weightedbootstrappingAverage[j][c]/(double)Itterations);

            }
        }
        return  weightedbootstrappingAverage ;
    }
}

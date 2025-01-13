package com.example.project11.ProjectInfo.loaders;

import java.io.FileNotFoundException;

public class AverageWeightedBootstrapping {
    private double[][] WeighedtBootstrappingArray  ;


    public AverageWeightedBootstrapping() throws FileNotFoundException {
        WeightedBootstrapping weightedBootstrapping = new WeightedBootstrapping();
        WeighedtBootstrappingArray = weightedBootstrapping.readAllStudents();


    }
}

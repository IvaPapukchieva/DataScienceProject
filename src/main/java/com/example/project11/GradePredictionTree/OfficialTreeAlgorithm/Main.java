package com.example.project11.GradePredictionTree.OfficialTreeAlgorithm;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        TreeTester treeTester = new TreeTester(100,1);
        System.out.println(Arrays.deepToString(treeTester.getArrayOfImagianryStudents()));

        System.out.println(Arrays.toString(treeTester.getRandom80percent()));

        System.out.println(Arrays.toString(treeTester.getRandom20percent()));


    }
}

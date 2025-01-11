package com.example.project11.GradePredictionTree.OfficialTreeAlgorithm;

import com.sun.source.tree.Tree;

import java.io.FileNotFoundException;

public class TreeObj  {
    private int Course;
    private int OptimalDepth ;


    private DecisionTreeRegressor tree ;
    private double PerformanceMeasureRM ;

    private double[] Rad80percentStudentIndex;
    private double[] Rad20percentStudentIndex;

    private String[][] infoStudent;
    private double[][] weightedBootstrapping;
    private TreeTester treeTester;

    private DecisionTreeRegressor regressor;




    public TreeObj(int Course, String[][] infoStudent, double[][] weightedBootstrapping) throws FileNotFoundException {
        this.Course = Course;
        this.infoStudent = infoStudent;
        this.weightedBootstrapping = weightedBootstrapping;

        treeTester = new TreeTester(100, Course, infoStudent, weightedBootstrapping);
        Rad80percentStudentIndex = treeTester.getRandom80percent();
        Rad20percentStudentIndex = treeTester.getRandom20percent();
    }
    public double[] getRad80percentStudentIndex(){
        return Rad80percentStudentIndex;
    }
    public double[] getRad20percentStudentIndex(){
        return Rad20percentStudentIndex;
    }
    // method that gets optimal depth
    public int getOptimalDepth(){
        int testingAmount = 20;
        int mindepth = 2;
        double[] PerformanceMeasureArray = new double[testingAmount-mindepth];

        for(int i = mindepth; i<testingAmount ; i++) {
            DecisionTreeRegressor regressor = new DecisionTreeRegressor(5, i);
            regressor.fit(getStudentProperty80percent(Rad80percentStudentIndex), getGradesOf80percentStudents(Rad80percentStudentIndex));

            double[] predictions = regressor.predict(getStudentProperty20percent(Rad20percentStudentIndex));
            TreeAnalyser treeAnalyser = new TreeAnalyser(predictions, getGradesOf20percentStudents(Rad20percentStudentIndex), Course);
            PerformanceMeasureArray[i-mindepth] = treeAnalyser.getMethodRunning();

        }
        double smallestPerformanceMeasure = 100;
        for(int i = 0; i < PerformanceMeasureArray.length; i++){
            if(PerformanceMeasureArray[i] < smallestPerformanceMeasure){
                smallestPerformanceMeasure = PerformanceMeasureArray[i];
                OptimalDepth = i;
            }
        }
        System.out.println("Optimal Depth : "+ OptimalDepth);
        return OptimalDepth;
    }
    public int getOptimalDepthVal(){
        return OptimalDepth;
    }


    // method that gets the Smallest Performance Measure
    public double getOptimalPerformanceMeasure(){
        DecisionTreeRegressor regressor = new DecisionTreeRegressor(2, getOptimalDepth());
        regressor.fit(getStudentProperty80percent(Rad80percentStudentIndex), getGradesOf80percentStudents(Rad80percentStudentIndex));
        double[] predictions = regressor.predict(getStudentProperty20percent(Rad20percentStudentIndex));
        TreeAnalyser treeAnalyser = new TreeAnalyser(predictions, getGradesOf20percentStudents(Rad20percentStudentIndex), Course);
        return treeAnalyser.getMethodRunning();
    }



    public String[][] getStudentProperty80percent(double[] ArrayOf80percentIndexes){
        String[][] propertiesOf80Percent = new String[ArrayOf80percentIndexes.length][5];
        for(int i = 0; i < ArrayOf80percentIndexes.length; i++){
            for( int j = 1 ; j<6 ; j++){
                propertiesOf80Percent[i][j-1] = infoStudent[(int)(ArrayOf80percentIndexes[i])][j];
            }
        }
        return propertiesOf80Percent;
    }

    public String[][] getStudentProperty20percent(double[] ArrayOf20percentIndexes){
        String[][] propertiesOf20Percent = new String[ArrayOf20percentIndexes.length][5];
        for(int i = 0; i < ArrayOf20percentIndexes.length; i++){
            for( int j = 1 ; j<6 ; j++){
                propertiesOf20Percent[i][j-1] = infoStudent[(int)(ArrayOf20percentIndexes[i])][j];
            }
        }
        return propertiesOf20Percent;
    }

    // This method gets the grades of the students in the 80 percent data set
    public double[] getGradesOf80percentStudents(double[] ArrayOf80percentIndexes){
        double[] grades80Percent = new double[ArrayOf80percentIndexes.length];

        for(int i = 0; i < ArrayOf80percentIndexes.length; i++){
            grades80Percent[i]= weightedBootstrapping[(int)ArrayOf80percentIndexes[i]][Course];
        }
        return grades80Percent;
    }

    public  double[] getGradesOf20percentStudents(double[] ArrayOf20percentIndexes){
        double[] grades20Percent = new double[ArrayOf20percentIndexes.length];

        for(int i = 0; i < ArrayOf20percentIndexes.length; i++){
            grades20Percent[i] = weightedBootstrapping[(int)ArrayOf20percentIndexes[i]][Course] ;
        }
        return grades20Percent;
    }


    // regressor is tree --> trains and builds the tree
    // performance measure saves the best performance measure achieved
}

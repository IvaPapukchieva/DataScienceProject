package com.example.project11.GradePredictionTree.OfficialTreeAlgorithm;

import com.example.project11.ProjectInfo.loaders.Loader;
import com.example.project11.ProjectInfo.loaders.StudentInfoLoader;
import com.example.project11.ProjectInfo.loaders.WeightedBootstrapping;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TreeAlgorithmUtil {
    public static String[][] studentInfoArray;
    public static double[][] weightedBootstrappingArray;

    public static int course ;




    public static void main(String[] args) throws FileNotFoundException {
        // Example data encoded numerically
        course  = 0 ;

        StudentInfoLoader studentInfoLoader = new StudentInfoLoader();
        studentInfoArray = studentInfoLoader.readInfoString();

        WeightedBootstrapping weightedBootstrapping = new WeightedBootstrapping();
        weightedBootstrappingArray = weightedBootstrapping.readAllStudents();

     //   double[] Grades = {3, 10, 2, 6, 9, 2, 6, 3, 2, 5, 6, 2, 8, 5, 4, 8, 3, 4, 5, 6, 5, 7, 4, 6, 8, 1, 10, 6, 10, 7, 6, 2, 4, 7, 4, 3, 4, 5, 3, 10, 8, 9, 1, 7, 8, 8, 4, 10, 4, 10, 4, 7, 10, 1, 3, 8, 10, 5, 3, 7, 3, 3, 8, 10, 4, 5, 9, 1, 5, 4, 2, 3, 4, 3, 1, 10, 8, 2, 5, 1, 8, 3, 6, 3, 10, 3, 10, 5, 10, 10, 6, 8, 2, 4, 9, 10, 7, 5, 2, 5};





        TreeTester treeTester = new TreeTester(100, course, studentInfoLoader.readInfoString(), weightedBootstrapping.readAllStudents());

        double[] Rad80Student = treeTester.getRandom80percent();
        double[] Rad20Student = treeTester.getRandom20percent();



        System.out.println("80% Grades:" + Arrays.toString(getGradesOf80percentStudents(Rad80Student)));
        System.out.println("20% Grades:" + Arrays.toString(getGradesOf20percentStudents(Rad20Student)));

//        System.out.println("80% Indices:" + Arrays.toString(Rad80Student));
//        System.out.println("20% Indices:" + Arrays.toString(Rad20Student));

//        System.out.println(getGradesOf80percentStudents(Rad80Student).length);
//        System.out.println(getGradesOf20percentStudents(Rad20Student).length);

//        System.out.println("80% properties:" + Arrays.deepToString(getStudentProperty80percent(Rad80Student)));
//        System.out.println("20% properties:" + Arrays.deepToString(getStudentProperty20percent(Rad20Student)));
//
//        System.out.println(getStudentProperty80percent(Rad80Student).length);
//        System.out.println(getStudentProperty20percent(Rad20Student).length);

        DecisionTreeRegressor regressor = new DecisionTreeRegressor(2,4);
        regressor.fit(getStudentProperty80percent(Rad80Student), getGradesOf80percentStudents(Rad80Student));

        // Print the decision tree
        regressor.printTree();

        double[] predictions = regressor.predict(getStudentProperty20percent(Rad20Student));
        System.out.println("Predictions: " + Arrays.toString(predictions));


        TreeAnalyser treeAnalyser = new TreeAnalyser(predictions, weightedBootstrappingArray, course);
        treeAnalyser.getMethodRunning();
    }






    // method that correlates the Indexes of 80 percent with the student properties .
    public static String[][] getStudentProperty80percent(double[] ArrayOf80percentIndexes){
        String[][] propertiesOf80Percent = new String[ArrayOf80percentIndexes.length][5];
        for(int i = 0; i < ArrayOf80percentIndexes.length; i++){
            for( int j = 1 ; j<6 ; j++){
                propertiesOf80Percent[i][j-1] = studentInfoArray[(int)(ArrayOf80percentIndexes[i])][j];
            }
        }
        return propertiesOf80Percent;
    }

    // method that correlates the Indexes of 20 percent with the student properties .
    public static String[][] getStudentProperty20percent(double[] ArrayOf20percentIndexes){
        String[][] propertiesOf20Percent = new String[ArrayOf20percentIndexes.length][5];
        for(int i = 0; i < ArrayOf20percentIndexes.length; i++){
            for( int j = 1 ; j<6 ; j++){
                propertiesOf20Percent[i][j-1] = studentInfoArray[(int)(ArrayOf20percentIndexes[i])][j];
            }
        }
        return propertiesOf20Percent;
    }

    // This method gets the grades of the students in the 80 percent data set
    public static double[] getGradesOf80percentStudents(double[] ArrayOf80percentIndexes){
        double[] grades80Percent = new double[ArrayOf80percentIndexes.length];

        for(int i = 0; i < ArrayOf80percentIndexes.length; i++){
             grades80Percent[i]= weightedBootstrappingArray[(int)ArrayOf80percentIndexes[i]][course];
        }
        return grades80Percent;
    }

    // This method gets the grades of the students in the 20 percent data set
    public static double[] getGradesOf20percentStudents(double[] ArrayOf20percentIndexes){
        double[] grades20Percent = new double[ArrayOf20percentIndexes.length];

        for(int i = 0; i < ArrayOf20percentIndexes.length; i++){
            grades20Percent[i] = weightedBootstrappingArray[(int)ArrayOf20percentIndexes[i]][course] ;
        }
        return grades20Percent;
    }

    private static String[][] formatStudentInfo(String[][] studentInfo) {
        String[][] formattedStudentInfo = new String[studentInfo.length-1][studentInfo[0].length-1];

        for(int i = 1; i < studentInfo.length; i++) {
            for (int j = 1; j < studentInfo[i].length; j++) {
                formattedStudentInfo[i-1][j-1] = studentInfo[i][j];
            }
        }
        return formattedStudentInfo;
    }

    public static double[] gradesExtractor(Loader loader, int course) throws FileNotFoundException {
        double[][] dataSet = loader.readAllStudents();
        double[] grades = new double[dataSet.length];
        for (int i = 0; i < dataSet.length; i++) {
            grades[i] = dataSet[i][course];
        }
        return grades;
    }

}

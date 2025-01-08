package com.example.project11.GradePredictionTree.OfficialTreeAlgorithm;

import com.example.project11.ProjectInfo.loaders.Loader;
import com.example.project11.ProjectInfo.loaders.StudentInfoLoader;
import com.example.project11.ProjectInfo.loaders.WeightedBootstrapping;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TreeAlgorithmUtil {
    public static Map<String, Integer> propertyMap = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        // Example data encoded numerically

        StudentInfoLoader studentInfoLoader = new StudentInfoLoader();
        String[][] X = formatStudentInfo(studentInfoLoader.readInfoString());

        // Extract the grades
        Loader loader = new WeightedBootstrapping();
        double[] Y = gradesExtractor(loader, 0);

        DecisionTreeRegressor regressor = new DecisionTreeRegressor(2,4);
        regressor.fit(X, Y);

        // Print the decision tree
        regressor.printTree();

        // Predict
        String[][] testX = {
                {"high", "86", "3 tau", "A", "0.5 Hz "},
                {"high", "71", "1 tau", "F", "0.1 Hz "},
                {"full", "122", "3 tau", "E", "0.1 Hz "},
                {"nothing", "2", "1 tau", "D", "1.0 Hz "},
                {"full", "-34", "3 tau", "E", "0.5 Hz "},
                {"low", "111", "3 tau", "E", "5.0 Hz "},
                {"full", "-42", "1 tau", "D", "1.0 Hz "},
                {"nothing", "-39", "2 tau", "D", "0.1 Hz "},
                {"high", "122", "1 tau", "D", "1.0 Hz "},
                {"high", "-38", "3 tau", "A", "0.1 Hz "},
                {"full", "-14", "1 tau", "F", "5.0 Hz "},
                {"low", "0", "2 tau", "D", "5.0 Hz "},
                {"high", "122", "3 tau", "C", "5.0 Hz "},
                {"nothing", "69", "1 tau", "A", "5.0 Hz "},
                {"nothing", "74", "3 tau", "B", "0.1 Hz "},
                {"medium", "-25", "1 tau", "B", "5.0 Hz "},
                {"low", "-18", "2 tau", "B", "0.1 Hz "},
                {"high", "70", "2 tau", "D", "5.0 Hz "},
                {"full", "-33", "1 tau", "C", "0.1 Hz "},
                {"full", "94", "2 tau", "E", "0.1 Hz "},
                {"high", "-11", "1 tau", "F", "0.5 Hz "},
                {"high", "58", "2 tau", "C", "5.0 Hz "},
                {"medium", "-28", "2 tau", "A", "0.5 Hz "},
                {"high", "27", "2 tau", "A", "5.0 Hz "},
                {"full", "121", "3 tau", "F", "1.0 Hz "},
                {"full", "-10", "1 tau", "D", "0.1 Hz "},
                {"medium", "25", "1 tau", "A", "0.5 Hz "},
                {"low", "11", "1 tau", "C", "0.5 Hz "},
                {"nothing", "40", "1 tau", "A", "1.0 Hz "},
                {"high", "76", "2 tau", "B", "0.5 Hz "},
                {"high", "145", "1 tau", "C", "5.0 Hz "},
                {"full", "122", "1 tau", "B", "0.1 Hz "},
                {"nothing", "-17", "1 tau", "C", "0.1 Hz "},
                {"medium", "-16", "2 tau", "E", "5.0 Hz "},
                {"full", "34", "3 tau", "F", "0.1 Hz "},
                {"nothing", "-4", "3 tau", "E", "0.5 Hz "},
                {"full", "119", "1 tau", "F", "0.1 Hz "},
                {"high", "68", "3 tau", "A", "1.0 Hz "},
                {"nothing", "-42", "1 tau", "F", "5.0 Hz "},
                {"high", "44", "3 tau", "E", "0.1 Hz "},
                {"high", "-18", "3 tau", "D", "0.1 Hz "},
                {"medium", "145", "3 tau", "B", "0.5 Hz "},
                {"medium", "-25", "3 tau", "C", "0.5 Hz "},
                {"full", "131", "3 tau", "A", "0.5 Hz "},
                {"full", "1", "2 tau", "A", "0.1 Hz "},
                {"nothing", "96", "3 tau", "B", "0.5 Hz "},
                {"high", "74", "1 tau", "F", "0.5 Hz "},
                {"nothing", "57", "3 tau", "B", "5.0 Hz "},
                {"nothing", "-13", "2 tau", "F", "0.5 Hz "},
                {"nothing", "128", "3 tau", "B", "0.5 Hz "},
                {"low", "140", "3 tau", "A", "1.0 Hz "},
                {"high", "34", "3 tau", "D", "5.0 Hz "},
                {"medium", "-8", "1 tau", "C", "5.0 Hz "},
                {"low", "41", "3 tau", "B", "0.1 Hz "},
                {"high", "75", "3 tau", "E", "1.0 Hz "},
                {"high", "80", "3 tau", "A", "0.1 Hz "},
                {"nothing", "55", "1 tau", "D", "1.0 Hz "},
                {"high", "92", "2 tau", "D", "1.0 Hz "},
                {"medium", "60", "1 tau", "A", "0.5 Hz "},
                {"medium", "4", "1 tau", "C", "0.1 Hz "},
                {"medium", "70", "2 tau", "B", "0.5 Hz "},
                {"low", "3", "1 tau", "D", "0.1 Hz "},
                {"low", "-41", "3 tau", "E", "1.0 Hz "},
                {"nothing", "37", "1 tau", "C", "0.1 Hz "},
                {"full", "122", "1 tau", "E", "1.0 Hz "},
                {"low", "36", "2 tau", "E", "5.0 Hz "},
                {"nothing", "74", "2 tau", "C", "5.0 Hz "},
                {"medium", "-8", "2 tau", "B", "0.1 Hz "},
                {"high", "-13", "2 tau", "A", "0.5 Hz "},
                {"medium", "-7", "2 tau", "A", "0.5 Hz "},
                {"full", "-5", "2 tau", "F", "0.1 Hz "},
                {"low", "142", "3 tau", "C", "5.0 Hz "},
                {"high", "132", "2 tau", "F", "0.1 Hz "},
                {"low", "-33", "1 tau", "D", "1.0 Hz "},
                {"full", "146", "2 tau", "D", "5.0 Hz "},
                {"nothing", "53", "2 tau", "A", "5.0 Hz "},
                {"low", "14", "2 tau", "F", "5.0 Hz "},
                {"full", "107", "2 tau", "A", "5.0 Hz "},
                {"full", "21", "1 tau", "C", "0.5 Hz "},
                {"high", "113", "1 tau", "D", "0.5 Hz "},
                {"full", "-12", "2 tau", "D", "5.0 Hz "},
                {"nothing", "-16", "3 tau", "F", "0.5 Hz "},
                {"high", "79", "2 tau", "F", "5.0 Hz "},
                {"medium", "134", "2 tau", "A", "0.5 Hz "},
                {"medium", "60", "1 tau", "A", "0.1 Hz "},
                {"high", "14", "2 tau", "A", "0.1 Hz "},
                {"nothing", "46", "2 tau", "E", "1.0 Hz "},
                {"high", "98", "2 tau", "C", "0.1 Hz "},
                {"low", "-24", "1 tau", "B", "5.0 Hz "},
                {"low", "71", "1 tau", "E", "1.0 Hz "},
                {"high", "-40", "1 tau", "A", "1.0 Hz "},
                {"high", "64", "3 tau", "C", "0.5 Hz "},
                {"full", "63", "2 tau", "A", "0.1 Hz "},
                {"medium", "-4", "2 tau", "D", "5.0 Hz "},
                {"medium", "8", "1 tau", "F", "1.0 Hz "},
                {"full", "128", "1 tau", "C", "0.1 Hz "},
                {"high", "-16", "3 tau", "F", "0.5 Hz "},
                {"medium", "-37", "1 tau", "F", "1.0 Hz "},
                {"full", "104", "3 tau", "C", "0.1 Hz "},
                {"high", "120", "1 tau", "B", "0.5 Hz "}
        };


        double[] predictions = regressor.predict(testX);

        System.out.println("Predictions: " + Arrays.toString(predictions));

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

    public static double[][] propertiesExtractor(StudentInfoLoader loader) throws FileNotFoundException {
        String[][] studentInfo = loader.readInfoString();
        double[][] studentInfoFormatted = new double[studentInfo.length][studentInfo[0].length];
        for (int i = 0; i < studentInfo.length; i++) {
            for(int j = 0; j < studentInfo[i].length; j++) {
                studentInfoFormatted[i][j] = propertyMap.get(studentInfo[i][j]);
            }
        }


        return studentInfoFormatted;
    }
}

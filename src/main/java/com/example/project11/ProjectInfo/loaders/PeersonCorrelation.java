package com.example.project11.ProjectInfo.loaders;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PeersonCorrelation{
    private double[][] graduatingGradesArray;

    public PeersonCorrelation() throws FileNotFoundException {
        GraduatingGradesLoader graduatingGradesLoader = new GraduatingGradesLoader();
        graduatingGradesArray = graduatingGradesLoader.readAllStudents();
    }

    public Map<Integer, ArrayList<PearsonMeasure>> getCorrelationMap() throws FileNotFoundException {
        Map<Integer, ArrayList<PearsonMeasure>> CorrelationMap = new HashMap<>();
        ArrayList<PearsonMeasure> pearsonMeasureList = new ArrayList<>();
        for( int i = 0 ; i<33 ; i++){
            pearsonMeasureList.clear();
            for(int j = 0 ; j<33 ; j++){
                PearsonMeasure pearsonMeasure = new PearsonMeasure(i,j,graduatingGradesArray);
                pearsonMeasureList.add(pearsonMeasure);
               // System.out.println("Course : "+i +" r Value :"+ pearsonMeasure.getR() +" get other class"+ j);
            }
            CorrelationMap.put(i,pearsonMeasureList);

        }

        return CorrelationMap;
    }
    public Map<Integer, ArrayList<PearsonMeasure>> getBestCorrelationMap() throws FileNotFoundException {
        Map<Integer, ArrayList<PearsonMeasure>> CorrelationMap = getCorrelationMap();        ArrayList<PearsonMeasure> pearsonMeasureList = new ArrayList<>();
        ArrayList<PearsonMeasure> bestpearsonMeasureList = new ArrayList<>();
        Map<Integer, ArrayList<PearsonMeasure>> bestCorrelationMap  = new HashMap<>();

        for(int i = 0 ; i<33; i++){
            bestpearsonMeasureList.clear();
            for(int  j = 0 ; j<CorrelationMap.get(i).size()-1; j++){
                if(CorrelationMap.get(i).get(j).getR()>=0.5){
                    bestpearsonMeasureList.add(CorrelationMap.get(i).get(j));
                    System.out.println("course "+ i + " complementary course "+ CorrelationMap.get(i).get(j).getComplementingCourse()+ " for R value of "+CorrelationMap.get(i).get(j).getR());
                }
            }
            bestCorrelationMap.put(i,bestpearsonMeasureList);

        }
        return  bestCorrelationMap;

    }
    public ArrayList<PearsonMeasure> order(ArrayList<PearsonMeasure> arraylist) {
        // Sort the ArrayList in descending order based on the getR() value
        for (int i = 0; i < arraylist.size() - 1; i++) {
            for (int j = i + 1; j < arraylist.size(); j++) {
                if (arraylist.get(i).getR() < arraylist.get(j).getR()) {
                    // Swap elements
                    PearsonMeasure temp = arraylist.get(i);
                    arraylist.set(i, arraylist.get(j));
                    arraylist.set(j, temp);
                }
            }
        }
        return arraylist;
    }


    public double[][] getFilledStudentArray() throws FileNotFoundException {
        CurrentGradeLoader currentGradeLoader = new CurrentGradeLoader();
        double[][] currentGradeLoaderArray = currentGradeLoader.readAllStudents();


        Map<Integer, ArrayList<PearsonMeasure>> bestCorrelationMap  = getBestCorrelationMap();

        for( int i = 0 ; i<currentGradeLoaderArray.length;i++){
            for (int j = 0 ; j<currentGradeLoaderArray[0].length;j++){
                if(currentGradeLoaderArray[i][j] == -1){
                        for (int c = 0; c < bestCorrelationMap.get(j).size(); c++) {
                            if (currentGradeLoaderArray[i][bestCorrelationMap.get(j).get(c).getComplementingCourse()] != -1) {
                                currentGradeLoaderArray[i][j] = currentGradeLoaderArray[i][bestCorrelationMap.get(j).get(c).getComplementingCourse()];
                            }

                        }

                }
            }
        }
        return currentGradeLoaderArray;
    }

    public static void main(String[] args) throws FileNotFoundException {
        PeersonCorrelation peersonCorrelation = new PeersonCorrelation();
        System.out.println(Arrays.deepToString(peersonCorrelation.getFilledStudentArray()));
    }


}

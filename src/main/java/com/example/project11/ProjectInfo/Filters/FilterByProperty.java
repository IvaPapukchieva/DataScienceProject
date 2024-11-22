package com.example.project11.ProjectInfo.Filters;
import com.example.project11.ProjectInfo.loaders.StudentInfoLoader;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class FilterByProperty extends StudentInfoLoader {

    private List<Integer> StudentIDByProperty;
    private String Category;
    private  String SubCategory;
    private  double[][] allStudents;


    public FilterByProperty(String Category, String SubCategory, double[][] allStudents) throws FileNotFoundException {

        this.allStudents = allStudents;
        this.Category = Category;
        this.SubCategory = SubCategory;
        StudentIDByProperty = getFilteredData();

    }

    public List<Integer> getFilteredData() {
        int index=0;
        if(Category.equals("Neuro-Synaptic Interface Level")){
            index = 1;
        }
        else if(Category.equals("Plasma Conductivity Quotient")){
            index = 2;
        }
        else if(Category.equals("Chrono-Adaptation Rate")){
            index = 3;
        }
        else if(Category.equals("Telepathic Synchronisation Index")){
            index = 4;
        }
        else  if(Category.equals("Aetheric Resonance Capacity")){
            index = 5;
        }


        int finalIndex = index;
        return Arrays.stream(infoInStrings)
                .filter(row -> SubCategory.equals(row[finalIndex]))
                .map(row->Integer.parseInt(row[0]))
                .toList();



    }

    public double[][] getStudentsGradesById() {
        double[][] results = new double[StudentIDByProperty.size()][33];

        for (int j = 0; j < StudentIDByProperty.size(); j++) {
            int id = StudentIDByProperty.get(j);

            results[j] = IntStream.range(0, StudentIDByProperty.size())
                    .filter(i -> StudentID[i] ==id)
                    .mapToObj(i -> allStudents[i])
                    .findFirst()
                    .orElse(new double[0]);
        }
        return results;



}


}









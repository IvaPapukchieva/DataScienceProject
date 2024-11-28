package com.example.project11.FilterProcessing;

import com.example.project11.ProjectInfo.Filters.FilterByGrade;
import com.example.project11.ProjectInfo.loaders.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class FilterData {
    ArrayList<Filter> filterData;
    String selectedDataSet;

    public FilterData(ArrayList<Filter> filterData, String selectedDataSet) {
        this.filterData = filterData;
        this.selectedDataSet = selectedDataSet;
    }

    public double[][] applyFilters() throws FileNotFoundException {
        double[][] result = getDataSet();

        for(Filter filter : filterData ){
            ArrayList<?> inputs = (ArrayList) filter.getValues();
            switch (filter.getType()) {
                case "By Grade":
                    FilterByGrade filterByGrade = new FilterByGrade(result);
                    if(filter.getValues().size() == 1) {
                        result = filterByGrade.filterStudents((int) (double) inputs.getFirst());
                    } else if(filter.getValues().size() == 2) {
                        result = filterByGrade.filterStudents((int) (double) inputs.getFirst(), (int) (double) inputs.getLast());
                    } else if(filter.getValues().size() > 2) {
                        result = filterByGrade.filterStudents((Arrays.stream(inputs.toArray())
                                .mapToInt(o -> (int) o) // Cast each Object to Integer and unbox
                                .toArray()));
                    }
                case "By Course":

                case "By GPA":

                case "By Property":

                case "By Student ID":

            }
        }

        return result;
    }

    private double[][] getDataSet() throws FileNotFoundException {
        Loader currentLoader = null;
        switch (selectedDataSet) {
            case "currentGradeLoader":
                currentLoader = new CurrentGradeLoader();
                break;
            case "currentGradeLoaderNG":
                currentLoader = new CurrentGradeLoaderNG();
                break;
            case "currentGradeLoaderRemoveNG":
                currentLoader = new CurrentGradeLoaderRemoveNG();
                break;
            case "graduatingGradesLoader":
                currentLoader = new GraduatingGradesLoader();
                break;
            case "studentInfoLoader":
                currentLoader = new StudentInfoLoader();
                break;
            case "weightedBootstrappingLoader":
                currentLoader = new WeightedBootstrapping();
                break;
        }

        double[][] result = currentLoader.readAllStudents();
        return result;
    }


}

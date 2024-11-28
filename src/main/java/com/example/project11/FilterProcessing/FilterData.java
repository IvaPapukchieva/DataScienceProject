package com.example.project11.FilterProcessing;

import com.example.project11.ProjectInfo.Filters.FilterByCourse;
import com.example.project11.ProjectInfo.Filters.FilterByGPA;
import com.example.project11.ProjectInfo.Filters.FilterByGrade;
import com.example.project11.ProjectInfo.Filters.FilterByProperty;
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
                    switch (filter.getSubType()) {
                        case "Number" -> result = filterByGrade.filterStudents((int) (double) inputs.getFirst());
                        case "Range" -> result = filterByGrade.filterStudents((int) (double) inputs.getFirst(), (int) (double) inputs.getLast());
                        case "Multiple" -> result = filterByGrade.filterStudents((int[]) inputs.getFirst());
                    }
                    break;
                case "By Course":
                    FilterByCourse filterByCourse = new FilterByCourse(result);
                    switch (filter.getSubType()) {
                        case "Number" -> result = filterByCourse.filterStudents((int) (double) inputs.getFirst());
                        //case "Range" -> result = filterByCourse.filterStudents((int) (double) inputs.getFirst(), (int) (double) inputs.getLast());
                        case "Multiple" -> result = filterByCourse.filterStudents((int[]) inputs.getFirst());
                    }
                    break;
                case "By GPA":
                    FilterByGPA filterByGPA = new FilterByGPA(result);
                    result = filterByGPA.filterStudents((double) inputs.getFirst(), (double) inputs.getLast());
                    break;
                case "By Property":
                    FilterByProperty filterByProperty = new FilterByProperty((String) filter.getValues().getLast(), (String) filter.getValues().getFirst(), result);
                    switch (filter.getSubType()) {
                        case "Categorical" -> result = filterByProperty.filterStudents();
                        case "Numerical" -> result = filterByProperty.filterStudents();
                    }
                    result = filterByProperty.filterStudents();
                    break;
                case "By Student ID":

                    break;
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

package com.example.project11.FilterProcessing;

import com.example.project11.ProjectInfo.Filters.*;
import com.example.project11.ProjectInfo.loaders.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

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
                        case "Range" -> result = filterByCourse.filterStudents((int) (double) inputs.getFirst(), (int) (double) inputs.getLast());
                        case "Multiple" -> result = filterByCourse.filterStudents((int[]) inputs.getFirst());
                    }
                    break;

                case "By GPA":
                    FilterByStudentGPA filterByStudentGPA = new FilterByStudentGPA(result);
                    switch (filter.getSubType()) {
                        case "Number" -> result = filterByStudentGPA.filterStudents((int) (double) inputs.getFirst());
                        case "Range" ->
                                result = filterByStudentGPA.filterStudents((int) (double) inputs.getFirst(), (int) (double) inputs.getLast());
                        case "Multiple" -> result = filterByStudentGPA.filterStudents((int[]) inputs.getFirst());
                    }
                    break;
                case "By Property":
                    FilterByProperty filterByProperty = new FilterByProperty((String) filter.getValues().getLast(), (String) filter.getValues().getFirst(), result);
                    switch (filter.getSubType()) {
                        case "Categorical" -> result = filterByProperty.filterStudents();
                        case "Numerical" -> result = filterByProperty.filterStudents();
                    }
                    result = filterByProperty.filterStudents();
                    break;
                case "By Course GPA":
                    FilterByCourseGPA filterByCourseGPA = new FilterByCourseGPA(result);
                    switch (filter.getSubType()) {
                        case "Number" -> result = filterByCourseGPA.filterStudents((int) (double) inputs.getFirst());
                        case "Range" ->
                                result = filterByCourseGPA.filterStudents((int) (double) inputs.getFirst(), (int) (double) inputs.getLast());
                        case "Multiple" -> result = filterByCourseGPA.filterStudents((int[]) inputs.getFirst());
                    }
                    break;

                case "By Course NG":
                    FilterByCourseGPA filterByCourseNG = new FilterByCourseGPA(result);
                    switch (filter.getSubType()) {
                        case "Number" -> result = filterByCourseNG.filterStudents((int) (double) inputs.getFirst());
                        case "Range" ->
                                result = filterByCourseNG.filterStudents((int) (double) inputs.getFirst(), (int) (double) inputs.getLast());
                        case "Multiple" -> result = filterByCourseNG.filterStudents((int[]) inputs.getFirst());
                    }
                    break;


                case "By Student NG":
                    FilterByStudentNG filterByStudentNG = new FilterByStudentNG(result);
                    switch (filter.getSubType()) {
                        case "Number" -> result = filterByStudentNG.filterStudents((int) (double) inputs.getFirst());
                        case "Range" ->
                                result = filterByStudentNG.filterStudents((int) (double) inputs.getFirst(), (int) (double) inputs.getLast());
                        case "Multiple" -> result = filterByStudentNG.filterStudents((int[]) inputs.getFirst());
                    }
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

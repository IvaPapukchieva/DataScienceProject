package com.example.project11;

import com.example.project11.ProjectInfo.loaders.*;
import com.example.project11.ProjectInfo.STEP1.*;
import com.example.project11.ProjectInfo.STEP2.*;
import com.example.project11.ProjectInfo.STEP3.*;



import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MainData {
 public static void main(String[] args) throws FileNotFoundException {

     CurrentGradeLoaderNG currentGradeLoaderNG = new CurrentGradeLoaderNG();
     CurrentGradeLoader currentGradeLoader=new CurrentGradeLoader();
     GraduatingGradesLoader graduatingGradesLoader= new GraduatingGradesLoader();
     CurrentGradeLoaderRemoveNG currentGradeLoaderRemoveNG=new CurrentGradeLoaderRemoveNG();
     WeightedBootstrapping weightedBootstrapping= new WeightedBootstrapping();


     /**
      * Methods for handling missing "NG" (No Grade) entries in CurrentGrade and Graduate grade datasets:
      * - `loaderNG`: Loads CurrentGrade data with "NG" entries intact.
      * - `meanLoader`: Replaces "NG" entries in CurrentGrade with class mean grades.
      * - `graduateLoader`: Loads Graduate grades data without modifications.
      * - `loaderNoNG`: Loads CurrentGrade data with "NG" entries removed for analysis.
      * - `bootstrappingLoader`: Replaces "NG" entries in CurrentGrade using weighted randomization for realistic data replacement.
      */

     double[][] meanLoader = currentGradeLoaderNG.readAllStudents();
     double[][] loaderNG= currentGradeLoader.readAllStudents();
     double[][] graduateLoader= graduatingGradesLoader.readAllStudents();
     ArrayList<ArrayList<Double>> loaderNoNG=currentGradeLoaderRemoveNG.readAllStudents();
     double[][] bootstrappingLoader= weightedBootstrapping.readAllStudents();


//     FilterByStudentID filterByStudentID=new FilterByStudentID();
//     System.out.println(Arrays.toString(filterByStudentID.getStudentGradesById(4)));
//     FilterByID.FilterByStudentIDGraduatingGradesLoader filetr=new FilterByID.FilterByStudentIDGraduatingGradesLoader();
//     System.out.println(Arrays.toString(filetr.getStudentGradesById(4)));
//     FilterByID.FilterByStudentICurrentGradesLoader ko=new FilterByID.FilterByStudentICurrentGradesLoader();
//     System.out.println(Arrays.toString(ko.getStudentGradesById(210333)));
//
//     FilterByID. FilterByStudentIDWeightedBootstrappingGradesLoader ho=new FilterByID.FilterByStudentIDWeightedBootstrappingGradesLoader();
//     System.out.println(Arrays.toString(ho.getStudentGradesById(210333)));
//     System.out.println(Arrays.deepToString(bootstrappingLoader));

/**
 * Finds the easiest classes based on the grade distribution of students.
 * This class analyzes the grades of students and identifies which classes have the highest average grades
 * or the least difficulty based on some criteria.
 */
     EasiestClasses findEasiestClassesMeanLoader = new EasiestClasses();
     EasiestClasses findEasiestClassesGraduateLoader = new EasiestClasses();
     EasiestClasses findEasiestClassesBootstrappingLoader = new EasiestClasses();



/**
 * Finds the hardest classes based on the grade distribution of students.
 * This class identifies the classes that have the lowest average grades or the greatest difficulty,
 * considering the grade performance of students in those classes.
 */
     HardestClasses findHardestClassesMeanLoader = new HardestClasses();
     HardestClasses findHardestClassesGraduateLoader = new HardestClasses();
     HardestClasses findHardestClassesBootstrappingLoader = new HardestClasses();

/**
 * Identifies students eligible for honors based on their GPA.
 * This class evaluates the GPAs of students and classifies them into Cum Laude, Magna Cum Laude,
 * and Summa Cum Laude categories based on predefined GPA thresholds.
 */
     CumLaude findCumLaudeMeanLoader = new CumLaude(meanLoader);
     CumLaude findCumLaudeLoaderGraduate = new CumLaude(graduateLoader);
     CumLaude findCumLaudeLoaderBootstrapping = new CumLaude(bootstrappingLoader);

//     findCumLaudeLoaderBootstrapping.FindCumLaudeStudents();
//     findCumLaudeLoaderGraduate.FindCumLaudeStudents();


FilterByProperty propertyFilter = new FilterByProperty();

System.out.println(propertyFilter.getFilteredData("Neuro-Synaptic Interface Level","medium"));

/**
 * Identifies the best and worst students based on their GPA.
 * This class calculates the best and worst-performing students by finding the highest and lowest GPAs
 * in the dataset and prints the information of the corresponding students.
 */
     BestAndWorstStudent findBestAndWorstMeanLoader = new BestAndWorstStudent(meanLoader);
     BestAndWorstStudent findBestAndWorstLoaderGraduate = new BestAndWorstStudent(graduateLoader);
     BestAndWorstStudent findBestAndWorstLoaderBootstrapping = new BestAndWorstStudent(bootstrappingLoader);

//
//
//     SimilarClasses findSimilarClassesLoaderNoNG = new SimilarClasses(loaderNoNG);
//     SimilarClasses findSimilarClassesGraduateLoader = new SimilarClasses(graduateLoader);
//     SimilarClasses findSimilarClassesBootsrappingLoader = new SimilarClasses(bootstrappingLoader);
//
//     System.out.println(Arrays.toString(findSimilarClassesLoaderNoNG.calculateSimilarClasses(loaderNoNG)));




}}

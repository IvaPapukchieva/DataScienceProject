package com.example.project11.ProjectInfo.Filters;
import com.example.project11.ProjectInfo.loaders.StudentInfoLoader;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        public Map<String, Integer> calculateCourseGradeDistribution() {

            double [][] StudentResults=getStudentsGradesById();
            Map<String, Integer> gradeCounts = new HashMap<>();

            gradeCounts.put("0", 0);
            gradeCounts.put("1", 0);
            gradeCounts.put("2", 0);
            gradeCounts.put("3", 0);
            gradeCounts.put("4", 0);
            gradeCounts.put("5", 0);
            gradeCounts.put("6", 0);
            gradeCounts.put("7", 0);
            gradeCounts.put("8", 0);
            gradeCounts.put("9", 0);
            gradeCounts.put("10", 0);

            for (double[] studentGrades :  StudentResults) {
                for (double grade : studentGrades) {
                    String range = getGradeRange(grade);
                    gradeCounts.put(range, gradeCounts.get(range) + 1);
                }
            }

            return gradeCounts;
        }


        private String getGradeRange(double grade) {
            if (grade == 10) return "10";
            if (grade >= 9) return "9";
            if (grade >= 8) return "8";
            if (grade >= 7) return "7";
            if (grade >= 6) return "6";
            if (grade >= 5) return "5";
            if (grade >= 4) return "4";
            if (grade >= 3) return "3";
            if (grade >= 2) return "2";
            if (grade >= 1) return "1";
            return "0";


        }




}









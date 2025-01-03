package com.example.project11.ProjectInfo.Filters;

import java.util.HashMap;
import java.util.Map;

public class FilterByCourseGPA {

    private final double [][] allStudents;
    private double []  averageByCourse;


    public FilterByCourseGPA(double[][] allStudents){
        this.allStudents=allStudents;
        this.averageByCourse=calculateCourseAverages();
    }

    public double[] calculateCourseAverages() {
        int numRows = allStudents.length;
        int numCols = allStudents[0].length;
        double[] averageByCourse = new double[numCols];

        for (int j = 0; j < numCols; j++) {
            double sum = 0;
            for (int i = 0; i < numRows; i++) {
                sum += allStudents[i][j];
            }
            averageByCourse[j] = sum / numRows;
        }
        return averageByCourse;
    }

    public Map<String,Double> getValidCourses(double grade){
        Map<String,Double> validCourses=new HashMap<>();
        for(int i=0;i<averageByCourse.length;i++){
            if(grade==averageByCourse[i]){
                validCourses.put(courses[i],grade);
            }
        }
        return validCourses;

    }

    public Map<String,Double> getValidCourses(int lowerBound,int upperBound){
        if (lowerBound > upperBound) {
            int temp = lowerBound;
            lowerBound = upperBound;
            upperBound = temp;
        }
        Map<String,Double> validCourses=new HashMap<>();

        for(int i=0;i<averageByCourse.length;i++){
            if(averageByCourse[i]>=lowerBound&& averageByCourse[i]<=upperBound){
                validCourses.put(courses[i],averageByCourse[i]);
            }
        }
        return validCourses;

    }

    public Map<String, Double> getValidCourses(int[] grades) {
        Map<String, Double> validCourses = new HashMap<>();

        for (int i = 0; i < averageByCourse.length; i++) {
            for (int grade : grades) {
                if (grade == (int) averageByCourse[i]) {
                    validCourses.put(courses[i], averageByCourse[i]);
                    break;
                }
            }
        }

        return validCourses;
    }




    private final String[] courses = {
            "Arkonian Warfare Tactics",
            "ExoGenetics Evolution",
            "Transdimensional Navigation",
            "Warp Field Theory",
            "Dark Matter Biophysics",
            "Zarnithian Philosophy",
            "Drakthon Linguistics",
            "Xynthium Material Sciences",
            "Hyperspace Topology",
            "Helio-Bio Interface",
            "Luminarian Art Theory",
            "Stellar Cartography",
            "Chrono-Kinetics",
            "Technotronic Linguistic Fusion",
            "Cybernetic Ethics",
            "Nebulon Astrophysics",
            "Quasar Dynamics",
            "Glacial Holo-Architecture",
            "Aether Resonance",
            "Gravix Planetary Studies",
            "Vortex Quantum Mechanics",
            "Holo-Temporal Engineering",
            "Psi-Energy Manipulation",
            "Plasmawave Analysis",
            "Neutronia Metallurgy",
            "Syntho-Chemical Engineering",
            "Sublight Propulsion Systems",
            "Krythos Biomechanics",
            "Flux Capacitor Management",
            "Xyloprax Computation",
            "Yridium Power Systems",
            "Quantum Neuro-Hacking",
            "Zyglon Neurology"
    };

}

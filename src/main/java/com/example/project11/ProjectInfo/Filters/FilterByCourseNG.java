package com.example.project11.ProjectInfo.Filters;

import java.util.HashMap;
import java.util.Map;

public class FilterByCourseNG {

    private final double[][] allStudents;
    private double[] numberOfNG;

    public FilterByCourseNG(double[][] allStudents) {
        this.allStudents = allStudents;
        numberOfNG=getAllTheNGByCourse();
    }

    public double[] getAllTheNGByCourse() {

        numberOfNG = new double[allStudents[0].length];
        double count = 0;

        for (int i = 0; i < allStudents[0].length; i++) {
            for (int j = 0; j < allStudents.length; j++) {
                if (allStudents[j][i] == -1.0) {
                    count++;
                }
            }
            numberOfNG[i] = count;
            count = 0;
        }
        return numberOfNG;
    }

    public Map<String, Double> getFilterByCourseNG(double NG) {
        Map<String, Double> validCourses = new HashMap<>();

        for (int i = 0; i < numberOfNG.length; i++) {
            if (NG == numberOfNG[i]) {
                validCourses.put(courses[i], numberOfNG[i]);
            }
        }
        return validCourses;
    }

    public Map<String, Double> getFilterByCourseNG(int lowerBound, int upperBound) {
        if (lowerBound > upperBound) {
            int temp = lowerBound;
            lowerBound = upperBound;
            upperBound = temp;
        }
        Map<String, Double> validCourses = new HashMap<>();

        for (int i = 0; i < numberOfNG.length; i++) {
            if (numberOfNG[i] >= lowerBound && numberOfNG[i] <= upperBound) {
                validCourses.put(courses[i], numberOfNG[i]);
            }
        }
        return validCourses;
    }

    public Map<String, Double> getFilterByCourseNG(double[] NGs) {
        Map<String, Double> validCourses = new HashMap<>();

        for (int i = 0; i < numberOfNG.length; i++) {
            for (double NG : NGs) {
                if (NG == numberOfNG[i]) {
                    validCourses.put(courses[i], numberOfNG[i]);
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
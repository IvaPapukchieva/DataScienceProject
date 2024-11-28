package com.example.project11.ProjectInfo.Filters;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class FilterBySpecificCourse {
    private int courseIndex;
    private double [][]allStudents;
    private Map<Integer, String> gradeMap=CourseIndexMapper();

    public FilterBySpecificCourse (int courseIndex, double [][]allStudents) {
        this.courseIndex = courseIndex;
        this.allStudents = allStudents;
    }

    public double [] getSpecificCourse() {
//        this can be 1D
        double[] filteredStudents = new double[allStudents.length];
       for (int i = 0; i < allStudents.length; i++) {
         filteredStudents[i] = allStudents[i][courseIndex];
       }
       return filteredStudents;
    }


    public String getLabel(){
       return gradeMap.get(courseIndex);
    }



    public Map<Integer,String> CourseIndexMapper(){
        Map<Integer, String> courses = new HashMap<>();
        courses.put(1, "Arkonian Warfare Tactics");
        courses.put(2, "ExoGenetics Evolution");
        courses.put(3, "Transdimensional Navigation");
        courses.put(4, "Warp Field Theory");
        courses.put(5, "Dark Matter Biophysics");
        courses.put(6, "Zarnithian Philosophy");
        courses.put(7, "Drakthon Linguistics");
        courses.put(8, "Xynthium Material Sciences");
        courses.put(9, "Hyperspace Topology");
        courses.put(10, "Helio-Bio Interface");
        courses.put(11, "Luminarian Art Theory");
        courses.put(12, "Stellar Cartography");
        courses.put(13, "Chrono-Kinetics");
        courses.put(14, "Technotronic Linguistic Fusion");
        courses.put(15, "Cybernetic Ethics");
        courses.put(16, "Nebulon Astrophysics");
        courses.put(17, "Quasar Dynamics");
        courses.put(18, "Glacial Holo-Architecture");
        courses.put(19, "Aether Resonance");
        courses.put(20, "Gravix Planetary Studies");
        courses.put(21, "Vortex Quantum Mechanics");
        courses.put(22, "Holo-Temporal Engineering");
        courses.put(23, "Psi-Energy Manipulation");
        courses.put(24, "Plasmawave Analysis");
        courses.put(25, "Neutronia Metallurgy");
        courses.put(26, "Syntho-Chemical Engineering");
        courses.put(27, "Sublight Propulsion Systems");
        courses.put(28, "Krythos Biomechanics");
        courses.put(29, "Flux Capacitor Management");
        courses.put(30, "Xyloprax Computation");
        courses.put(31, "Yridium Power Systems");
        courses.put(32, "Quantum Neuro-Hacking");
        courses.put(33, "Zyglon Neurology");

        return courses;
    }

}

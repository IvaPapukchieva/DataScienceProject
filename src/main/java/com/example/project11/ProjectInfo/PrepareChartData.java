package com.example.project11.ProjectInfo;

import java.util.HashMap;
import java.util.Map;

public class PrepareChartData {

//    3 ways to display the data/ grade distribution/ course GPA/ Amount of NGs
//this can be tricky when I save the id, which can be also from 1-10 and the course indexes

    public Map<String, Double> calculateGradeDistribution(double [][] allStudents) {

        Map<String, Double> gradeCounts = new HashMap<>();
        gradeCounts.put("0", 0.0);
        gradeCounts.put("1", 0.0);
        gradeCounts.put("2", 0.0);
        gradeCounts.put("3", 0.0);
        gradeCounts.put("4", 0.0);
        gradeCounts.put("5", 0.0);
        gradeCounts.put("6", 0.0);
        gradeCounts.put("7", 0.0);
        gradeCounts.put("8", 0.0);
        gradeCounts.put("9", 0.0);
        gradeCounts.put("10", 0.0);
        for (double[] studentGrades : allStudents) {
            for (double grade : studentGrades) {
                if (grade == -1) continue;
                String range = getGradeRange(grade);
                gradeCounts.put(range, gradeCounts.get(range) + 1);
            }
        }

        return gradeCounts;
    }


    public Map<String, Double> calculateStudentsGradeDistribution(double []allStudents) {

        Map<String, Double> gradeCounts = new HashMap<>();
        gradeCounts.put("0", 0.0);
        gradeCounts.put("1", 0.0);
        gradeCounts.put("2", 0.0);
        gradeCounts.put("3", 0.0);
        gradeCounts.put("4", 0.0);
        gradeCounts.put("5", 0.0);
        gradeCounts.put("6", 0.0);
        gradeCounts.put("7", 0.0);
        gradeCounts.put("8", 0.0);
        gradeCounts.put("9", 0.0);
        gradeCounts.put("10", 0.0);
            for (double grade : allStudents){
                if (grade == -1) continue;
                String range = getGradeRange(grade);
                gradeCounts.put(range, gradeCounts.get(range) + 1);
            }


        return gradeCounts;
    }



//    this Map will work for FilterByStudentNG
//    index zero is the id and the want to save the next thing
//    it will display the student id and the amount

public Map<String, Double> displayNGByStudentID(double[][] allStudents) {
    Map<String, Double> studentNGs = new HashMap<>();

    for (double[] student :allStudents) {
        if(student[1]==-1){
            continue;
        }
        String studentID = String.valueOf(student[0]);
        double ngCount = student[1];
        studentNGs.put(studentID, ngCount);
    }

    return studentNGs;
}


//this Map will work for FilterByCourseNG
public Map<String, Double> calculateNGsByYear(double[][] courseData) {
        Map<String, Double> yearNGs = new HashMap<>();

        double firstYearNGs = 0;
        double secondYearNGs = 0;
        double thirdYearNGs = 0;
        double fourthYearNGs = 0;
        double fifthYearNGs = 0;

        for (double[] course : courseData) {
            double courseIndex = course[0];

            if (courseIndex >= 0.0 && courseIndex <= 12.0) {
                firstYearNGs ++;
            } else if (courseIndex >= 583.0 && courseIndex <= 595.0) {
                secondYearNGs ++;
            } else if (courseIndex >= 599.0 && courseIndex <= 622.0) {
                thirdYearNGs ++;
            } else if (courseIndex >= 985.0 && courseIndex <= 1058.0) {
                fourthYearNGs ++;
            } else if (courseIndex >= 1317.0 && courseIndex <= 1339.0) {
                fifthYearNGs ++;
            }
        }

        yearNGs.put("First Year", firstYearNGs);
        yearNGs.put("Second Year", secondYearNGs);
        yearNGs.put("Third Year", thirdYearNGs);
        yearNGs.put("Fourth Year", fourthYearNGs);
        yearNGs.put("Fifth Year", fifthYearNGs);

        return yearNGs;
    }

//    This map should display GPA by course
//    works with FilterByCourseGPA
    public Map<String, Double> calculateNGByYear(double[][] courseData) {
        Map<String,Double> courses = new HashMap<>();

        for(double[] course : courseData){
            double  courseIndex = course[0];
            double GPA= course[1];
            String courseName=getCourseIndex(courseIndex);
            courses.put(courseName,GPA);

        }
        return courses;

    }

    private String getCourseIndex(double courseIndex) {
        int index =  (int)courseIndex;
        if (index >= 0 && index < courses.length) {
            return courses[index];
        }
        return "Unknown Course";
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

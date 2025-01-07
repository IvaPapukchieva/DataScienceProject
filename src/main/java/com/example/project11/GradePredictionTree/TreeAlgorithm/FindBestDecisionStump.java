package com.example.project11.GradePredictionTree.TreeAlgorithm;

import com.example.project11.ProjectInfo.loaders.StudentInfoLoader;
import com.example.project11.ProjectInfo.loaders.WeightedBootstrapping;
import java.io.FileNotFoundException;
import java.util.*;

public class FindBestDecisionStump {

    private double[][] BootstrappedArray;
    private String[][] StudentPropertyArray;
    private int Course;
    private ArrayList<Double> GradeDistributionForCourse;
    private String[][] properties = {
            {"full", "medium", "nothing", "low", "high"},
            {},
            {"1 tau", "2 tau", "3 tau"},
            {"A", "B", "C", "D", "E", "F"},
            {"0.1 Hz ", "0.5 Hz ", "5.0 Hz ", "1.0 Hz "}
    };

    private HashMap<String, Integer> propertyHashMap = new HashMap<>() {{
        put("full", 1);
        put("medium", 1);
        put("nothing", 1);
        put("low", 1);
        put("high", 1);

        put("1 tau", 3);
        put("2 tau", 3);
        put("3 tau", 3);

        put("A", 4);
        put("B", 4);
        put("C", 4);
        put("D", 4);
        put("E", 4);
        put("F", 4);

        put("0.1 HZ", 5);
        put("0.5 HZ", 5);
        put("5.0 HZ", 5);
        put("1.0 HZ", 5);
    }};

    private double[] generalGradeDistribution;

    public FindBestDecisionStump(int Course) throws FileNotFoundException {
        WeightedBootstrapping bootstrapping = new WeightedBootstrapping();
        BootstrappedArray = bootstrapping.readAllStudents();

        GradeDistribution gradeDistribution = new GradeDistribution(Course, true, 2);
        generalGradeDistribution = getPercentageDistribution(gradeDistribution.getGradeCount(Course, true, 2));
        this.Course = Course;

        StudentInfoLoader studentInfoLoader = new StudentInfoLoader();
        StudentPropertyArray = studentInfoLoader.readInfoString();

        for (int i = -42; i <= 147; i++) {
            propertyHashMap.put(String.valueOf(i), 2);
        }
    }

    public double[] getPercentageDistribution(int[] gradeCount) {
        double[] PercentageDistribution = new double[11];
        double sum = 0;

        for (int grade : gradeCount) {
            sum += grade;
        }

        for (int i = 0; i < gradeCount.length; i++) {
            PercentageDistribution[i] = ((double) gradeCount[i] / sum) * 100;
        }
        return PercentageDistribution;
    }

    public double[] getDifferenceArray(int[] gradeCount) {
        double[] percentageDistribution = getPercentageDistribution(gradeCount);
        double[] differenceArray = new double[gradeCount.length];

        for (int i = 0; i < differenceArray.length; i++) {
            differenceArray[i] = Math.pow((generalGradeDistribution[i] - percentageDistribution[i]), 2);
        }
        return differenceArray;
    }

    public double getMeasure(int[] gradeCount) {
        double[] differenceArray = getDifferenceArray(gradeCount);
        double sum = 0;
        for (double difference : differenceArray) {
            sum += difference;
        }
        return sum;
    }

    public TreeNode buildDecisionTree(int course, List<Integer> remainingCategories) {
        if (remainingCategories.isEmpty()) {
            return null; // Leaf node
        }

        double bestMeasure = Double.MAX_VALUE;
        String bestProperty = null;
        int bestCategory = -1;

        for (int category : remainingCategories) {
            for (String property : properties[category - 1]) {
                int[] gradeCount = getGradeCount(course, category, property);
                double measure = getMeasure(gradeCount);

                if (measure < bestMeasure) {
                    bestMeasure = measure;
                    bestProperty = property;
                    bestCategory = category;
                }
            }
        }

        if (bestProperty == null) {
            return null; // No valid split found
        }

        TreeNode node = new TreeNode(bestProperty);

        // Partition data for left and right branches
        List<Integer> nextCategories = new ArrayList<>(remainingCategories);
        nextCategories.remove((Integer) bestCategory);

        int[] gradeCountLeft = getGradeCount(course, bestCategory, bestProperty);
        int[] gradeCountRight = getGradeCountForNotProperty(course, bestCategory, bestProperty);

        node.left = buildDecisionTree(course, nextCategories);
        node.right = buildDecisionTree(course, nextCategories);

        return node;
    }

    public int[] getGradeCountForNotProperty(int Course, int indexOfCategory, String Property) {
        int[] gradeCount = new int[11];
        for (int i = 0; i < BootstrappedArray.length; i++) {
            if (!StudentPropertyArray[i][indexOfCategory].equals(Property)) {
                gradeCount[(int) BootstrappedArray[i][Course]]++;
            }
        }
        return gradeCount;
    }

    public int[] getGradeCount(int Course, int indexOfCategory, String... Properties) {
        int[] gradeCount = new int[11];
        for (String Property : Properties) {
            for (int j = 0; j < BootstrappedArray.length; j++) {
                if (StudentPropertyArray[j][indexOfCategory].equals(Property)) {
                    gradeCount[(int) BootstrappedArray[j][Course]]++;
                }
            }
        }
        return gradeCount;
    }

    class TreeNode {
        String property;
        TreeNode left;
        TreeNode right;

        TreeNode(String property) {
            this.property = property;
        }

        @Override
        public String toString() {
            return toString(0);
        }

        private String toString(int depth) {
            StringBuilder sb = new StringBuilder("  ".repeat(depth) + "Property: " + property + "\n");
            if (left != null) {
                sb.append("  ".repeat(depth) + "Left:\n" + left.toString(depth + 1));
            }
            if (right != null) {
                sb.append("  ".repeat(depth) + "Right:\n" + right.toString(depth + 1));
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        FindBestDecisionStump treeBuilder = new FindBestDecisionStump(1);
        List<Integer> allCategories = Arrays.asList(1, 2, 3, 4, 5);
        TreeNode root = treeBuilder.buildDecisionTree(1, allCategories);

        System.out.println("Decision tree created successfully.");
        System.out.println(root);
}
}
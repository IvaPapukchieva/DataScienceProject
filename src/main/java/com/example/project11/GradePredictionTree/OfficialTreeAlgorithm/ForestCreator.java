package com.example.project11.GradePredictionTree.OfficialTreeAlgorithm;


import com.sun.source.tree.Tree;

import java.io.FileNotFoundException;
import java.util.*;

public class ForestCreator {
    private int AmountofTreesToSave;
    private int AmountofTreesToProcess;
    private Map<Integer, TreeObj> WildForest;
    private Map<Integer, TreeObj> FilteredForest;
    private int Course;
    private String[][] infoStudent;
    private double[][] weightedBootstrapping;


    public ForestCreator(int AmountofTreesToSave, int AmountofTreesToProcess, int Course, String[][] infoStudent, double[][] weightedBootstrapping) {
        this.AmountofTreesToProcess = AmountofTreesToProcess;
        this.AmountofTreesToSave = AmountofTreesToSave;
        this.Course = Course;
        this.infoStudent = infoStudent;
        this.weightedBootstrapping = weightedBootstrapping;
        WildForest = new HashMap<>();
        FilteredForest = new HashMap<>();
    }

    public Map<Integer, TreeObj> getWildForest() throws FileNotFoundException {
        for (int i = 0; i < AmountofTreesToProcess; i++) {
            WildForest.put(i, new TreeObj(Course, infoStudent, weightedBootstrapping));
        }

        return WildForest;
    }

    public Map<Integer, TreeObj> getFilteredForest() throws FileNotFoundException {
        getWildForest();
        Map<Integer, Double> PerformanceMeasureMap = new HashMap<>();

        for (Map.Entry<Integer, TreeObj> entry : WildForest.entrySet()) {
            Integer treeIndex = entry.getKey(); // Access the key (tree index)
            TreeObj tree = entry.getValue();   // Access the value (TreeObj)

            PerformanceMeasureMap.put(treeIndex, tree.getOptimalPerformanceMeasure());
        }

        List<Map.Entry<Integer, Double>> PerformanceMeasurelist = new ArrayList<>(PerformanceMeasureMap.entrySet());

        // Sort the list by value
        PerformanceMeasurelist.sort(Map.Entry.comparingByValue());

        // Create a new LinkedHashMap to preserve the sorted order
        Map<Integer, Double> SortedPerformanceMeasureMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, Double> entry : PerformanceMeasurelist) {
            SortedPerformanceMeasureMap.put(entry.getKey(), entry.getValue());
        }

        int count = 0;
        for (Integer key : SortedPerformanceMeasureMap.keySet()) {
            if (count >= AmountofTreesToSave) {
                break; // Stop after extracting N keys
            }
            FilteredForest.put(key, WildForest.get(key)); // Use the key to fetch TreeObj from WildForest
            count++;
        }

        // Return the filtered forest
        return FilteredForest;
    }
}
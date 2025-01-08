package com.example.project11.GradePredictionTree.OfficialTreeAlgorithm;

import java.util.*;
import java.util.stream.Collectors;

class Node {
    int featureIndex;
    double threshold;
    Node left;
    Node right;
    Double value;

    public Node(int featureIndex, double threshold, Node left, Node right, Double value) {
        this.featureIndex = featureIndex;
        this.threshold = threshold;
        this.left = left;
        this.right = right;
        this.value = value;
    }
}

class DecisionTreeRegressor {
    private Node root;
    private int minSamplesSplit;
    private int maxDepth;
    private Map<String, Integer> propertyMap = new HashMap<>();

    // MAP TO PRINT CLEARLY WHICH CATEGORY CORRESPONDS TO WHICH INDEX
    private Map<Integer, String> featuresMap = new HashMap<>();

    public DecisionTreeRegressor(int minSamplesSplit, int maxDepth) {
        this.minSamplesSplit = minSamplesSplit;
        this.maxDepth = maxDepth;

        featuresMap.put(0, "Category 1");
        featuresMap.put(1, "Category 2");
        featuresMap.put(2, "Category 3");
        featuresMap.put(3, "Category 4");
        featuresMap.put(4, "Category 5");

        // Initialize the property map for categorical encoding
        populatePropertyMap();
    }

    private void populatePropertyMap() {
        // Category 1
        propertyMap.put("full", 0);
        propertyMap.put("medium", 1);
        propertyMap.put("high", 2);
        propertyMap.put("low", 3);
        propertyMap.put("nothing", 4);

        // Category 2
        for (int i = -42; i <= 147; i++) {
            propertyMap.put(Integer.toString(i), i);
        }

        // Category 3
        propertyMap.put("1 tau", 0);
        propertyMap.put("2 tau", 1);
        propertyMap.put("3 tau", 2);

        // Category 4
        propertyMap.put("A", 0);
        propertyMap.put("B", 1);
        propertyMap.put("C", 2);
        propertyMap.put("D", 3);
        propertyMap.put("E", 4);
        propertyMap.put("F", 5);

        // Category 5
        propertyMap.put("1.0 Hz ", 0);
        propertyMap.put("5.0 Hz ", 1);
        propertyMap.put("0.5 Hz ", 2);
        propertyMap.put("0.1 Hz ", 3);
    }

    private Node buildTree(double[][] dataset, int depth) {
        if (dataset.length < minSamplesSplit || depth >= maxDepth) {
            return new Node(-1, -1, null, null, calculateLeafValue(dataset));
        }

        Split bestSplit = getBestSplit(dataset);
        if (bestSplit == null) {
            return new Node(-1, -1, null, null, calculateLeafValue(dataset));
        }

        Node left = buildTree(bestSplit.leftDataset, depth + 1);
        Node right = buildTree(bestSplit.rightDataset, depth + 1);
        return new Node(bestSplit.featureIndex, bestSplit.threshold, left, right, null);
    }

    private Split getBestSplit(double[][] dataset) {
        int numFeatures = dataset[0].length - 1;
        Split bestSplit = null;
        double maxVarRed = Double.NEGATIVE_INFINITY;

        for (int featureIndex = 0; featureIndex < numFeatures; featureIndex++) {
            int finalFeatureIndex = featureIndex;
            Set<Double> thresholds = Arrays.stream(dataset)
                    .map(row -> row[finalFeatureIndex])
                    .collect(Collectors.toSet());

            for (double threshold : thresholds) {
                int finalFeatureIndex1 = featureIndex;
                double[][] left = Arrays.stream(dataset).filter(row -> row[finalFeatureIndex1] <= threshold).toArray(double[][]::new);
                int finalFeatureIndex2 = featureIndex;
                double[][] right = Arrays.stream(dataset).filter(row -> row[finalFeatureIndex2] > threshold).toArray(double[][]::new);

                if (left.length == 0 || right.length == 0) continue;

                double varRed = varianceReduction(dataset, left, right);
                if (varRed > maxVarRed) {
                    bestSplit = new Split(featureIndex, threshold, left, right);
                    maxVarRed = varRed;
                }
            }
        }
        return bestSplit;
    }

    private double varianceReduction(double[][] parent, double[][] left, double[][] right) {
        double parentVar = calculateVariance(parent);
        double leftWeight = (double) left.length / parent.length;
        double rightWeight = (double) right.length / parent.length;

        return parentVar - (leftWeight * calculateVariance(left) + rightWeight * calculateVariance(right));
    }

    private double calculateVariance(double[][] dataset) {
        double mean = Arrays.stream(dataset).mapToDouble(row -> row[row.length - 1]).average().orElse(0);
        return Arrays.stream(dataset).mapToDouble(row -> Math.pow(row[row.length - 1] - mean, 2)).average().orElse(0);
    }

    private double calculateLeafValue(double[][] dataset) {
        return Arrays.stream(dataset).mapToDouble(row -> row[row.length - 1]).average().orElse(0);
    }

    public void fit(String[][] X, double[] Y) {
        double[][] dataset = new double[X.length][X[0].length + 1];
        for (int i = 0; i < X.length; i++) {
            for (int j = 0; j < X[i].length; j++) {
                dataset[i][j] = propertyMap.get(X[i][j]);
            }
            dataset[i][X[i].length] = Y[i];
        }
        root = buildTree(dataset, 0);
    }

    public double[] predict(String[][] X) {
        double[][] numericalX = new double[X.length][X[0].length];
        for (int i = 0; i < X.length; i++) {
            for (int j = 0; j < X[i].length; j++) {
                numericalX[i][j] = propertyMap.get(X[i][j]);
            }
        }
        return Arrays.stream(numericalX).mapToDouble(this::predictSingle).toArray();
    }

    private double predictSingle(double[] row) {
        Node current = root;
        while (current.value == null) {
            if (row[current.featureIndex] <= current.threshold) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return current.value;
    }

    // Print the decision tree
    public void printTree() {
        printTreeRecursive(root, "  ");
    }

    private void printTreeRecursive(Node node, String indent) {
        if (node == null) return;

        if (node.value != null) {
            System.out.println(indent + "Predict: " + node.value);
        } else {
            System.out.println(indent + "Feature[" + featuresMap.get(node.featureIndex) + "] <= " + node.threshold + "?");
            System.out.println(indent + "Left:");
            printTreeRecursive(node.left, indent + "  ");
            System.out.println(indent + "Right:");
            printTreeRecursive(node.right, indent + "  ");
        }
    }

    static class Split {
        int featureIndex;
        double threshold;
        double[][] leftDataset;
        double[][] rightDataset;

        public Split(int featureIndex, double threshold, double[][] leftDataset, double[][] rightDataset) {
            this.featureIndex = featureIndex;
            this.threshold = threshold;
            this.leftDataset = leftDataset;
            this.rightDataset = rightDataset;
        }
    }
}

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

    //MAP TO PRINT CLEARLY WHICH CATEGORY CORRESPONDING TO WHICH INDEX
    private Map<Integer, String> featuresMap = new HashMap<>();


    public DecisionTreeRegressor(int minSamplesSplit, int maxDepth) {
        this.minSamplesSplit = minSamplesSplit;
        this.maxDepth = maxDepth;

        featuresMap.put(0, "Neuro-Synaptic Interface");
        featuresMap.put(1, "Plasma Conductivity Quotient");
        featuresMap.put(2, "Chrono Adaptation Rate");
        featuresMap.put(3, "Telepathic Syncronization Index");
        featuresMap.put(4, "Aetheric Resonance Cap");
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

    public void fit(double[][] X, double[] Y) {
        double[][] dataset = new double[X.length][X[0].length + 1];
        for (int i = 0; i < X.length; i++) {
            System.arraycopy(X[i], 0, dataset[i], 0, X[i].length);
            dataset[i][X[i].length] = Y[i];
        }
        root = buildTree(dataset, 0);
    }

    public double[] predict(double[][] X) {
        return Arrays.stream(X).mapToDouble(this::predictSingle).toArray();
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

public class TreeBuilding {
    public static void main(String[] args) {
        // Example data encoded numerically
        double[][] X = {

                {4, -42, 1.0, 0, 1},// full, -42, 1.0 Hz, A, 1 tau
                {3, 20, 5.0, 1, 2},   // medium, 20, 5.0 Hz, B, 2 tau
                {2, 100, 0.5, 2, 3},  // high, 100, 0.5 Hz, C, 3 tau
                {1, 50, 0.1, 3, 1},   // low, 50, 0.1 Hz, D, 1 tau
                {0, 147, 1.0, 4, 2}   // nothing, 147, 1.0 Hz, E, 2 tau
        };
        double[] Y = {90, 85, 78, 88, 95};  // Grades

        DecisionTreeRegressor regressor = new DecisionTreeRegressor(2,8);
        regressor.fit(X, Y);

        // Print the decision tree
        regressor.printTree();

        // Predict
        double[][] testX = {
                {3, 30, 5.0, 1, 2},  // medium, 30, 5.0 Hz, B, 2 tau
                {1, 60, 0.1, 3, 1}   // low, 60, 0.1 Hz, D, 1 tau
        };
        double[] predictions = regressor.predict(testX);

        System.out.println("Predictions: " + Arrays.toString(predictions));
    }
}

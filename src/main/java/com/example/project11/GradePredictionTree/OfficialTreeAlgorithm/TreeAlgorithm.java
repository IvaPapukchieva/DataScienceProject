package com.example.project11.GradePredictionTree.OfficialTreeAlgorithm;

import java.util.*;
import java.util.stream.Collectors;

class Node {
    int featureIndex;
    double threshold;
    Node left;
    Node right;
    Double value;
    boolean isLeaf;

    public Node(int featureIndex, double threshold, Node left, Node right, Double value, boolean isLeaf) {
        this.featureIndex = featureIndex;
        this.threshold = threshold;
        this.left = left;
        this.right = right;
        this.value = value;
        this.isLeaf = isLeaf;
    }
    private int getFeatureIndex(){
        return featureIndex;
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



    private Node buildTree(double[][] dataset, int depth, List<Integer> branchHistory) {
        // Base case: Stop building if conditions are met
        if (dataset.length < minSamplesSplit || depth >= maxDepth) {
            return new Node(-1, -1, null, null, calculateLeafValue(dataset), true);
        }

        // Get the best split
        Split bestSplit = getBestSplit(dataset);
        if (bestSplit == null) {
            return new Node(-1, -1, null, null, calculateLeafValue(dataset), true);
        }
 // Add the current feature index to the branch history
//        System.out.println(resolveThresholdDescription(bestSplit.featureIndex, bestSplit.threshold));
//        System.out.println("Left Students:");
//
//        String[][] leftDataSet = new String[bestSplit.leftDataset.length][bestSplit.leftDataset[0].length] ;
//        for (int i = 0; i < bestSplit.leftDataset.length; i++) {
//            for (int j = 0; j < bestSplit.leftDataset[i].length; j++) {
//                leftDataSet[i][j] = resolveThresholdDescription(j, bestSplit.leftDataset[i][j]);
//            }
//
//        }
//        System.out.println(Arrays.deepToString(leftDataSet));
//
//        System.out.println("Right Students:");
//
//        String[][] rightDataSet = new String[bestSplit.rightDataset.length][bestSplit.rightDataset[0].length] ;
//        for (int i = 0; i < bestSplit.rightDataset.length; i++) {
//            for (int j = 0; j < bestSplit.rightDataset[i].length; j++) {
//                rightDataSet[i][j] = resolveThresholdDescription(j, bestSplit.rightDataset[i][j]);
//            }
//
//        }
//        System.out.println(Arrays.deepToString(rightDataSet));
//

//
        // Recursively build the left and right subtrees
        Node left = buildTree(bestSplit.leftDataset, depth + 1, new ArrayList<>(branchHistory)); // Copy history for left branch
        Node right = buildTree(bestSplit.rightDataset, depth + 1, branchHistory); // Right branch shares history


        //System.out.println("Branching History : "+ branchHistory);
        // Otionally, log or process the branch history

        // Return the current node

        return new Node(bestSplit.featureIndex, bestSplit.threshold, left, right, null, false);
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
                double[][] left;
                double[][] right;
                if(featureIndex == 1) {
                    left = Arrays.stream(dataset).filter(row -> row[finalFeatureIndex1] <= threshold).toArray(double[][]::new);
                    right = Arrays.stream(dataset).filter(row -> row[finalFeatureIndex1] > threshold).toArray(double[][]::new);

                } else {
                     left = Arrays.stream(dataset).filter(row -> row[finalFeatureIndex1] == threshold).toArray(double[][]::new);
                     right = Arrays.stream(dataset).filter(row -> row[finalFeatureIndex1] != threshold).toArray(double[][]::new);

                }

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
        root = buildTree(dataset, 0 ,new ArrayList<>() );
    }
    public Node getRoot(){
        return root;
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

    public void getTreeArrayList(Node node, List<String> treeList) {
        if (node == null) {
            return; // Base case: stop if the node is null
        }



        // Decision node: resolve the feature and threshold into human-readable format
        String thresholdDescription = resolveThresholdDescription(node);
        treeList.add(thresholdDescription);


        // Recursively process the left and right children
        getTreeArrayList(node.left, treeList);
        getTreeArrayList(node.right, treeList);
    }


    private String resolveThresholdDescription(Node node) {
        double threshold = node.threshold;
        int featureIndex = node.featureIndex;
        switch (featureIndex) {
            case 0: // Category 1: "full", "medium", etc.
                switch((int) threshold)  {
                    case 0:
                        return "full";
                    case 1:
                        return "medium";
                    case 2:
                        return "high";
                    case 3:
                        return "low";
                    case 4:
                        return "nothing";
                }
                break;

            case 1: // Category 2: integers from -42 to 147
                return Integer.toString((int) threshold);

            case 2: // Category 3: "1 tau", "2 tau", etc.
                switch((int) threshold)  {
                    case 0:
                        return "1 tau";
                    case 1:
                        return "2 tau";
                    case 2:
                        return "3 tau";
                }
                break;
            case 3: // Category 4: "A", "B", etc.
                switch((int) threshold)  {
                    case 0:
                        return "A";
                    case 1:
                        return "B";
                    case 2:
                        return "C";
                    case 3:
                        return "D";
                    case 4:
                        return "E";
                    case 5:
                        return "F";
                }
                break;
            case 4: // Category 5: "1.0 Hz", "5.0 Hz", etc.
                switch((int) threshold)  {
                    case 0:
                        return "1.0 Hz ";
                    case 1:
                        return "5.0 Hz ";
                    case 2:
                        return "0.5 Hz ";
                    case 3:
                        return "0.1 Hz ";
                }
                break;
            case -1:
                return node.value + "leaf";
        }
        return "Unknown"; // Fallback if no match is found
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



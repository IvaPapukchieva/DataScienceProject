package com.example.project11.GradePredictionTree.TreeAlgorithm;

class Nodes extends NodeProperty{
    // gets the position of the Node based on BFS
    private static int NodeIndex ;
    private String Property ;
    private double VarianceReductionVal ;

    public Nodes(int NodeIndex, String Property , double VarianceReductionVal){
        super(Property, VarianceReductionVal);
        this.NodeIndex= NodeIndex;
        this.VarianceReductionVal = super.getVarianceReductionVal();
        this.Property = super.getProperty();
    }
    public void setNodeIndex(int Index){
        this.NodeIndex =Index;
    }
    public int getNodeIndex(){
        return NodeIndex;
    }
}
package com.example.project11.GradePredictionTree.TreeAlgorithm;

public class NodeProperty {
    private String Property;
    private double VarianceReductionVal ;

    public NodeProperty(String Property , double VarianceReduction ){
        this.Property = Property;
        this.VarianceReductionVal = VarianceReduction;

    }
    public void setProperty(String Property ){
        this.Property = Property;
    }
    public void setVarianceReuctionVal(double VarRedVal){
        this.VarianceReductionVal = VarRedVal;
    }
    public String getProperty(){
        return Property;
    }
    public double getVarianceReductionVal(){
        return VarianceReductionVal;
    }
}

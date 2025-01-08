package com.example.project11.GradePredictionTree.TreeAlgorithm;


import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TreeBuilder {
    private double VarianceReductionVal ;
    private int Course;
    private int DepthOfTree = 4;

    private double[][] dataSet ;
    private int[] dataSetOfID ;


    private ArrayList<Nodes> NodeMap ;
    private String[][] CategoryArray  = {{"full", "nothing", "medium","low", "high"},{"full"},{"1 tau", "2 tau", "3 tau"},{"A","B","C","D","E","F"},{"0.1 Hz ", "0.5 Hz ", "1.0 Hz ","5.0 Hz "}};
    private int NumOfPropertyInCategoryArray ;



    public TreeBuilder(double[][] dataSet, int[] dataSetOfID, int Course){

        this.Course = Course;
        this.dataSet = dataSet;
        this.dataSetOfID  = dataSetOfID;
        NodeMap = new ArrayList<>(getLengthOfNodeMap());



    }
    // recursively findes the best combination of Nodes
    public ArrayList<Nodes> TreeBuilder() throws FileNotFoundException {

        // Base Case
        if(NodeMap.size() == 0 ){
            Nodes BestNode = new Nodes(1,"full" ,0);
            BestNode.setVarianceReuctionVal(getVarianceReductionVal(BestNode.getProperty(), 1));
            Nodes TestNode = new Nodes(-1, null , -1);

            for( int i = 0  ;i<CategoryArray.length ; i++){
                for( int j = 0 ; j<CategoryArray[i].length; j++){

                    TestNode.setNodeIndex(NodeMap.size());
                    TestNode.setProperty(CategoryArray[i][j]);
                    TestNode.setVarianceReuctionVal(getVarianceReductionVal(CategoryArray[i][j],i+1));

                    if (TestNode.getVarianceReductionVal()>BestNode.getVarianceReductionVal()){

                        BestNode.setProperty(TestNode.getProperty());
                        BestNode.setNodeIndex(TestNode.getNodeIndex());
                        BestNode.setVarianceReuctionVal(TestNode.getVarianceReductionVal());

                    }

                }
            }
            System.out.println("Base Case Complete");
            NodeMap.add(BestNode);
            return TreeBuilder();
        }
        else if(NodeMap.size()<getLengthOfNodeMap() && NodeMap.size()>0){
            // need a stream, that passes through the predefined set, skipping
            Nodes nodeEmpty  = new Nodes(2,"test", 0);
            System.out.println("recursive step");
            NodeMap.add(nodeEmpty);
            return TreeBuilder();
        }



        return NodeMap ;
    }

    // Tree Using Method
    // Takes in array of StudentProperties
    //returns array of grades Array of grades


    //Method that calculates the length that the arraylist has to be based on the depth
    public int getLengthOfNodeMap(){
        int length = 0 ;
        int previousVal = 1;
        for( int i = 1 ; i<DepthOfTree; i++){
            previousVal= previousVal*2;
            length+=previousVal;
        }
        return length+1 ;
    }

    // gets the variance reduction for a specific Node  ;
    public double getVarianceReductionVal(String Property , int CategoryIndex) throws FileNotFoundException {
        VarianceReduction varianceReduction = new VarianceReduction(dataSetOfID, dataSet, Property, Course, CategoryIndex);
        VarianceReductionVal = varianceReduction.getVarianceReduction();
        return VarianceReductionVal ;
    }







}

package com.example.project11.GradePredictionTree.TreeAlgorithm;


import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TreeBuilder {
    private double VarianceReductionVal ;
    private int Course;
    private int DepthOfTree;

    private double[][] dataSet ;
    private int[] dataSetOfID ;


    private ArrayList<Nodes> NodeMap ;
    private String[][] CategoryArray  = {{"full", "nothing", "medium","low", "high"},{"full"},{"1 tau", "2 tau", "3 tau"},{"A","B","C","D","E","F"},{"0.1 Hz ", "0.5 Hz ", "1.0 Hz ","5.0 Hz "}};
    private int NumOfPropertyInCategoryArray ;



    public TreeBuilder(double[][] dataSet, int[] dataSetOfID, int Course){

        this.Course = Course;
        this.dataSet = dataSet;
        this.dataSetOfID  = dataSetOfID;
        NodeMap = new ArrayList<>();



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

                    System.out.println(TestNode.getProperty());
                    if (TestNode.getVarianceReductionVal()>BestNode.getVarianceReductionVal()){

                        BestNode.setProperty(TestNode.getProperty());
                        BestNode.setNodeIndex(TestNode.getNodeIndex());
                        BestNode.setVarianceReuctionVal(TestNode.getVarianceReductionVal());

                    }

                }
            }
            NodeMap.add(BestNode);
            return NodeMap;
        }
        else if(NodeMap.size()<DepthOfTree && NodeMap.size()>0){
            // need a stream, that passes through the predefined set, skipping

        }



        return NodeMap ;
    }

    // Tree Using Method
    // Takes in array of StudentProperties
    //returns array of grades Array of grades




    // gets the variance reduction for a specific Node  ;
    public double getVarianceReductionVal(String Property , int CategoryIndex) throws FileNotFoundException {
        VarianceReduction varianceReduction = new VarianceReduction(dataSetOfID, dataSet, Property, Course, CategoryIndex);
        VarianceReductionVal = varianceReduction.getVarianceReduction();
        return VarianceReductionVal ;
    }







}

package com.example.project11.Controllers.Charts;

import com.example.project11.Controllers.Controller;
import com.example.project11.ProjectInfo.GradeDistributionCalculator;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXML;

public class ChartSelectionController extends Controller {


    private GradeDistributionCalculator gradeDistributionCalculator;
    private Map<String,Integer> chartData;
    @FXML
    private AreaChartController areaChart;
    @FXML
    private BarChartController barChart;
    @FXML
    private LineChartController lineChart;
    @FXML
    private PieChartController pieChart;
    @FXML
    private ScatterChartController scatterChart;


    public ChartSelectionController(){

    }

    public void initialize(double [][] filteredData){
        this.gradeDistributionCalculator = new GradeDistributionCalculator();
        gradeDistributionCalculator.calculateGradeDistribution(filteredData);
        areaChart.setChartData(chartData);
        barChart.setChartData(chartData);
        lineChart.setChartData(chartData);
        pieChart.setChartData(chartData);
        scatterChart.setChartData(chartData);


    }



}

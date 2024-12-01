package com.example.project11.Controllers.Charts;



import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LineChartController {
    @FXML
    private LineChart<String, Number> lineChart;


    public void setChartData(Map<String, Integer> gradeDistribution) {
        lineChart.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Grade Distribution");

        List<String> colorList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : gradeDistribution.entrySet()) {
            XYChart.Data<String, Number> data = new XYChart.Data<>(entry.getKey(), entry.getValue());
            series.getData().add(data);


        lineChart.getData().add(series);
            lineChart.applyCss();
            lineChart.layout();



    }
}}
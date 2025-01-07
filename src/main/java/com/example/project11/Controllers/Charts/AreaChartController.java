package com.example.project11.Controllers.Charts;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.Map;

public class AreaChartController implements ChartController {

    @FXML
    private AreaChart<String, Number> areaChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    public void setChartData(Map<String, Double> gradeDistribution) {
        areaChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Grade Distribution");

        for (Map.Entry<String, Double> entry : gradeDistribution.entrySet()) {
            String xValue = entry.getKey(); // Use String for x-axis
            double yValue = entry.getValue();
            XYChart.Data<String, Number> data = new XYChart.Data<>(xValue, yValue);
            series.getData().add(data);
        }

        areaChart.getData().add(series);
        areaChart.applyCss();
        areaChart.layout();
    }

    public void setChartLabels(String xAxisLabel, String yAxisLabel) {
        if (xAxisLabel != null && yAxisLabel != null) {
            areaChart.getXAxis().setLabel(xAxisLabel);
            areaChart.getYAxis().setLabel(yAxisLabel);
        }
    }
}

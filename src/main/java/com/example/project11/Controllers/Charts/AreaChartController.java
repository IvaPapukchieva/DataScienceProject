package com.example.project11.Controllers.Charts;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AreaChartController {
    @FXML
    private AreaChart<Number, Number> areaChart;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    public void setChartData(Map<String, Integer> gradeDistribution) {

        areaChart.getData().clear();
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Grade Distribution");
//       the container for data points in a chart.

        for (Map.Entry<String, Integer> entry : gradeDistribution.entrySet()) {
            double xValue = Integer.parseInt(entry.getKey());
            double yValue = entry.getValue();
            // Add each entry as a new Data point in the series
            XYChart.Data<Number, Number> data = new XYChart.Data<>(xValue, yValue);
            series.getData().add(data);
        }

        areaChart.getData().add(series);
        areaChart.applyCss();
        areaChart.layout();


        }
    }


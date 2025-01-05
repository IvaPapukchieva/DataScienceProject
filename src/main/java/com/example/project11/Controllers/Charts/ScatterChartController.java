package com.example.project11.Controllers.Charts;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

import java.util.Map;

public class ScatterChartController  implements ChartController{
    @FXML
    private ScatterChart<String, Number> scatterChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    public void setChartData(Map<String, Double> dataPoints) {
        scatterChart.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Data Points");

        for (Map.Entry<String, Double> entry : dataPoints.entrySet()) {
            XYChart.Data<String, Number> data = new XYChart.Data<>(entry.getKey(), entry.getValue());
            series.getData().add(data);
        }

        scatterChart.getData().add(series);

        scatterChart.applyCss();
        scatterChart.layout();
    }

    public void setChartLabels(String xAxisLabel, String yAxisLabel) {
        if (xAxisLabel != null && yAxisLabel != null) {
            scatterChart.getXAxis().setLabel(xAxisLabel);
            scatterChart.getYAxis().setLabel(yAxisLabel);
        }
    }
}

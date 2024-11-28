package com.example.project11.Charts;
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

    private final String[] colors = {
            "#6082B6",
            "#87CEEB",
            "#008080",
            "#ADD8E6",
            "#89CFF0"
    };

    public void setChartData(Map<String, Integer> gradeDistribution) {

        areaChart.getData().clear();
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Grade Distribution");

        List<String> colorList = new ArrayList<>();
        int index = 0;

        for (Map.Entry<String, Integer> entry : gradeDistribution.entrySet()) {
            // Use the key for x-value, and the value for y-value
            double xValue = Integer.parseInt(entry.getKey());
            double yValue = entry.getValue();

            // Add each entry as a new Data point in the series
            XYChart.Data<Number, Number> data = new XYChart.Data<>(xValue, yValue);
            series.getData().add(data);

            colorList.add(colors[index % colors.length]);
            index++;
        }

        // Add the series to the area chart
        areaChart.getData().add(series);

        // Apply CSS and layout adjustments
        areaChart.applyCss();
        areaChart.layout();

        // Color the area under the line
        for (int i = 0; i < series.getData().size(); i++) {
            XYChart.Data<Number, Number> data = series.getData().get(i);
            String color = colorList.get(i);

            // Apply the color to the filled area under each segment of the chart line
            if (data.getNode() != null) {
                data.getNode().setStyle("-fx-fill: " + color + ";");
            }

        }
    }
}

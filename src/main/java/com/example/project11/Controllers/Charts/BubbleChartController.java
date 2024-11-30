package com.example.project11.Controllers.Charts;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BubbleChartController {
    @FXML
    private BubbleChart<Number, Number> bubbleChart;
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
        bubbleChart.getData().clear();

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Grade Distribution");

        List<String> colorList = new ArrayList<>();
        int index = 0;

        for (Map.Entry<String, Integer> entry : gradeDistribution.entrySet()) {
            // Use the key for x-value, the value for y-value, and set a fixed size for the bubble
            double xValue = Integer.parseInt(entry.getKey());
            double yValue = entry.getValue(); // number of students is y value
            double size = 10 + (yValue * 1.1); // Bubble size

            // Add each entry as a new Data point in the series
            XYChart.Data<Number, Number> data = new XYChart.Data<>(xValue, yValue, size);
            series.getData().add(data);

            // Cycles through colors for each bubble
            colorList.add(colors[index % colors.length]);
            index++;
        }

        // Add the series to the bubble chart
        bubbleChart.getData().add(series);

        bubbleChart.applyCss();
        bubbleChart.layout();

        // Apply the colors to each bubble
        for (int i = 0; i < series.getData().size(); i++) {
            XYChart.Data<Number, Number> data = series.getData().get(i);
            String color = colorList.get(i);

            String colorClass = "";
            if (data.getNode() != null) {
                for (String cls : data.getNode().getStyleClass()) {
                    if (cls.startsWith("default-color")) {
                        colorClass = cls;
                        break;
                    }
                }

                for (Node n : bubbleChart.lookupAll("." + colorClass)) {
                    n.setStyle("-fx-bubble-fill: " + color + ";");
                }
            }
        }
    }
}




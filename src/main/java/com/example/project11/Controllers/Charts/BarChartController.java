package com.example.project11.Controllers.Charts;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BarChartController  implements ChartController{
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    private final String[] colors = {
            "#6082B6",
            "#87CEEB",
            "#008080",
            "#ADD8E6",
            "#89CFF0"
    };

    public void setChartData(Map<String, Double> gradeDistribution) {

        barChart.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(" Grade Distribution");

        List<String> colorList = new ArrayList<>();
        for (Map.Entry<String, Double> entry : gradeDistribution.entrySet()) {
            // Add each entry as a new Data point in the series
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            colorList.add(colors[colorList.size() % colors.length]); // Cycles through colors
        }

        // Add the series to the bar chart
        barChart.getData().add(series);

        // Apply CSS and layout adjustments to apply the colors
        barChart.applyCss();
        barChart.layout();

        for (int i = 0; i < series.getData().size(); i++) {
            XYChart.Data<String, Number> data = series.getData().get(i);
            String color = colorList.get(i);

            // Determine the style class of the current data node
            String colorClass = "";
            if (data.getNode() != null) {
                for (String cls : data.getNode().getStyleClass()) {
                    if (cls.startsWith("default-color")) {
                        colorClass = cls;
                        break;
                    }
                }

                // Apply the color to all nodes sharing this style class
                for (Node n : barChart.lookupAll("." + colorClass)) {
                    n.setStyle("-fx-bar-fill: " + color + ";");
                }
            }
        }
    }
    public void setChartLabels(String xAxisLabel, String yAxisLabel) {
        if (xAxisLabel != null && yAxisLabel != null) {
            barChart.getXAxis().setLabel(xAxisLabel);
            barChart.getYAxis().setLabel(yAxisLabel);
        }
    }

    @Override
    public BarChartController clone() {
        try {
            // Shallow copy using Object.clone
            BarChartController cloned = (BarChartController) super.clone();

            // Deep copy mutable fields
            cloned.barChart = new BarChart<>(new CategoryAxis(), new NumberAxis());
            cloned.xAxis = new CategoryAxis();
            cloned.yAxis = new NumberAxis();

            // Recreate color array (if it were mutable, this would matter)
            System.arraycopy(this.colors, 0, cloned.colors, 0, this.colors.length);

            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }
}


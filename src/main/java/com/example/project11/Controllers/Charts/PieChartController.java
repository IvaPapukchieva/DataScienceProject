package com.example.project11.Controllers.Charts;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PieChartController {
    @FXML
    private PieChart pieChart;

    private final String[] colors = {
            "#6082B6",
            "#87CEEB",
            "#008080",
            "#ADD8E6",
            "#89CFF0"
    };

    public void setChartData(Map<String, Double> gradeDistribution) {
        pieChart.getData().clear();

        List<String> colorList = new ArrayList<>();
        for (Map.Entry<String, Double> entry : gradeDistribution.entrySet()) {
            PieChart.Data data = new PieChart.Data(entry.getKey(), entry.getValue());
            pieChart.getData().add(data);
            colorList.add(colors[colorList.size() % colors.length]); // Cycles through colors
        }

        pieChart.applyCss();
        pieChart.layout();

        for (int i = 0; i < pieChart.getData().size(); i++) {
            PieChart.Data data = pieChart.getData().get(i);
            String color = colorList.get(i);

            String colorClass = "";
            for (String cls : data.getNode().getStyleClass()) {
                if (cls.startsWith("default-color")) {
                    colorClass = cls;
                    break;
                }
            }

            for (Node n : pieChart.lookupAll("." + colorClass)) {
                n.setStyle("-fx-pie-color: " + color + ";");
            }
        }

    }
}
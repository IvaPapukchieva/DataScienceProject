package com.example.project11.Controllers.Charts;

import com.example.project11.Controllers.Controller;
import com.example.project11.ProjectInfo.PrepareChartData;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Map;

public class ChartSelectionController extends Controller {

    private Map<String, Double> chartData;
    private static double[][] filteredData;

    public ChartSelectionController() {
        // Constructor
    }

    public void setFilteredData(double[][] allStudents) {

        this.filteredData = allStudents;
    }

    @Override
    public void mouseClickedComponent(MouseEvent mouseEvent) throws IOException {
        if (filteredData == null || filteredData.length == 0) {
            System.out.println("Filtered data is null or empty!");
            return;
        }

        PrepareChartData gradeDistributionCalculator = new PrepareChartData();

        chartData = gradeDistributionCalculator.calculateGradeDistribution(filteredData);

        String chartId = ((ImageView) mouseEvent.getSource()).getId();

        switch (chartId) {
            case "AreaChart" -> {
                AreaChartController areaChartController = (AreaChartController) controllers.get("Area Chart");
                areaChartController.setChartData(chartData);
                openNewChartWindow("Area Chart");
            }
            case "PieChart" -> {
                PieChartController pieChartController = (PieChartController) controllers.get("Pie Chart");
                pieChartController.setChartData(chartData);
                openNewChartWindow("Pie Chart");
            }
            case "BarChart" -> {
                BarChartController barChartController = (BarChartController) controllers.get("Bar Chart");
                barChartController.setChartData(chartData);
                openNewChartWindow("Bar Chart");
            }
            case "ScatterChart" -> {
                ScatterChartController scatterChartController = (ScatterChartController) controllers.get("Scatter Chart");
                scatterChartController.setChartData(chartData);
                openNewChartWindow("Scatter Chart");
            }
            case "BubbleChart" -> {
                BubbleChartController bubbleChartController = (BubbleChartController) controllers.get("Bubble Chart");
                bubbleChartController.setChartData(chartData);
                openNewChartWindow("Bubble Chart");
            }
            case "LineChart" -> {
                LineChartController lineChartController = (LineChartController) controllers.get("Line Chart");
                lineChartController.setChartData(chartData);
                openNewChartWindow("Line Chart");
            }
        }
    }

    private void openNewChartWindow(String chartName) throws IOException {
        Scene chartScene = scenes.get(chartName);
        if (chartScene != null) {
            Stage stage = new Stage();
            stage.setScene(chartScene);
            stage.setTitle(chartName);
            stage.show();
        } else {
            System.out.println("Scene for " + chartName + " not found!");
        }
    }
}

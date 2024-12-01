package com.example.project11.Controllers.Charts;

import com.example.project11.Controllers.Controller;
import com.example.project11.ProjectInfo.GradeDistributionCalculator;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Map;

public class ChartSelectionController extends Controller {

    private Map<String, Integer> chartData;
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

        GradeDistributionCalculator gradeDistributionCalculator = new GradeDistributionCalculator();
        chartData = gradeDistributionCalculator.calculateGradeDistribution(filteredData);

        String chartId = ((ImageView) mouseEvent.getSource()).getId();

        if (chartId.equals("AreaChart")) {
            AreaChartController areaChartController = (AreaChartController) controllers.get("Area Chart");
            areaChartController.setChartData(chartData);
            openNewChartWindow("Area Chart");

        } else if (chartId.equals("PieChart")) {
            PieChartController pieChartController = (PieChartController) controllers.get("Pie Chart");
            pieChartController.setChartData(chartData);
            openNewChartWindow("Pie Chart");

        } else if (chartId.equals("BarChart")) {
            BarChartController barChartController = (BarChartController) controllers.get("Bar Chart");
            barChartController.setChartData(chartData);
            openNewChartWindow("Bar Chart");

        } else if (chartId.equals("ScatterChart")) {
            ScatterChartController scatterChartController = (ScatterChartController) controllers.get("Scatter Chart");
            scatterChartController.setChartData(chartData);
            openNewChartWindow("Scatter Chart");

        } else if (chartId.equals("BubbleChart")) {
            BubbleChartController bubbleChartController = (BubbleChartController) controllers.get("Bubble Chart");
            bubbleChartController.setChartData(chartData);
            openNewChartWindow("Bubble Chart");

        } else if (chartId.equals("LineChart")) {
            LineChartController lineChartController = (LineChartController) controllers.get("Line Chart");
            lineChartController.setChartData(chartData);
            openNewChartWindow("Line Chart");
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

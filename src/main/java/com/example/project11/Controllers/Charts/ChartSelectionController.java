package com.example.project11.Controllers.Charts;

import com.example.project11.Controllers.Controller;
import com.example.project11.Controllers.SelectionsController;
import com.example.project11.ProjectInfo.GradeDistributionCalculator;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ChartSelectionController extends Controller {


    private Map<String,Integer> chartData;
    private static double  [][] filteredData;



    public ChartSelectionController(){


    }

    public void setFilteredData(double[][]allStudents){
        this.filteredData=allStudents;
    }

@Override
@FXML
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
        changeScene(mouseEvent, "Area Chart");

    } else if (chartId.equals("PieChart")) {
        PieChartController pieChartController = (PieChartController) controllers.get("Pie Chart");
        pieChartController.setChartData(chartData);
        changeScene(mouseEvent, "Pie Chart");

    } else if (chartId.equals("BarChart")) {
        BarChartController barChartController = (BarChartController) controllers.get("Bar Chart");
        barChartController.setChartData(chartData);
        changeScene(mouseEvent, "Bar Chart");

    } else if (chartId.equals("ScatterChart")) {
        ScatterChartController scatterChartController = (ScatterChartController) controllers.get("Scatter Chart");
        scatterChartController.setChartData(chartData);
        changeScene(mouseEvent, "Scatter Chart");

    } else if (chartId.equals("BubbleChart")) {
        BubbleChartController bubbleChartController = (BubbleChartController) controllers.get("Bubble Chart");
        bubbleChartController.setChartData(chartData);
        changeScene(mouseEvent, "Bubble Chart");

    } else if (chartId.equals("LineChart")) {
        LineChartController lineChartController = (LineChartController) controllers.get("Line Chart");
        lineChartController.setChartData(chartData);
        changeScene(mouseEvent, "Line Chart");
    }
}


}


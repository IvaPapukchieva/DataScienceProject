package com.example.project11.Controllers.Charts;

import java.util.Map;

public interface ChartController{

    void setChartData(Map<String, Double> map);
    void setChartLabels(String xAxisLabel, String yAxisLabel);


}

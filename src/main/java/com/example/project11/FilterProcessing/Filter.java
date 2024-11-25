package com.example.project11.FilterProcessing;
import java.util.List;

public class Filter {
    private final String type; // e.g., "By Grade", "By GPA"
    private final List<Double> values; // The values for the filter

    public Filter(String type, List<Double> values) {
        this.type = type;
        this.values = values;
    }

    public String getType() {
        return type;
    }

    public List<Double> getValues() {
        return values;
    }
}
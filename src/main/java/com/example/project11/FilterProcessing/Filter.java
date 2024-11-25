package com.example.project11.FilterProcessing;
import java.util.List;

public class Filter<O> {
    private final String type; // e.g., "By Grade", "By GPA"
    private final List<Object> values; // The values for the filter

    public Filter(String type, List<Object> values) {
        this.type = type;
        this.values = values;
    }

    public String getType() {
        return type;
    }

    public List<Object> getValues() {
        return values;
    }

    @Override
    public String toString() {
        return "Filter: " + type + " - " + values.toString();
    }
}
package com.example.project11.FilterProcessing;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Filter {
    private final String type; // e.g., "By Grade", "By GPA"
    private final String subType;
    private final List<Object> values; // The values for the filter

    public Filter(String type,String subType ,List<Object> values) {
        this.type = type;
        this.values = values;
        this.subType = subType;
    }

    public String getType() {
        return type;
    }

    public String getSubType() {return subType;}

    public List<Object> getValues() {
        return values;
    }



    @Override
    public String toString() {
        return "Filter: " + type + "/" + subType + " - " + Arrays.deepToString(values.toArray());
    }
}
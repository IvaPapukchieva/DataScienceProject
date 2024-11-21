package com.example.project11.ProjectInfo.loaders;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class FilterByProperty extends StudentInfoLoader {

    public FilterByProperty() throws FileNotFoundException {
        super();
    }

    public  List<String> getFilteredData(String Category, String SubCategory) {
        int index=0;
       if(Category.equals("Neuro-Synaptic Interface Level")){
           index = 1;
       }
        else if(Category.equals("Plasma Conductivity Quotient")){
            index = 2;
        }
       else if(Category.equals("Chrono-Adaptation Rate")){
            index = 3;
        }
        else if(Category.equals("Telepathic Synchronisation Index")){
            index = 4;
        }
       else  if(Category.equals("Aetheric Resonance Capacity")){
            index = 5;
        }


        int finalIndex = index;
        return Arrays.stream(infoInStrings)
                .filter(row -> SubCategory.equals(row[finalIndex]))
                .map(row->row[0])
                .toList();

}

}

package com.example.project11.ProjectInfo.STEP4;

import java.util.ArrayList;
import java.util.Locale.Category;


class Filter extends prediction {
    // gets an array that is filtered by the specific parameter
    private ArrayList<Double> specificArrGrades = new ArrayList<>()   ;
    private int CategoryNum;
    private String Name;
    
        public Filter(String Name, int CatgeroyNum ) {
    
            super(course , STUDENTID) ; 

        this.Name = Name;
        this.CategoryNum = CatgeroyNum;

    }

    public void filerize() {
        for (int i = 0; i < bootstrapArray.length; i++) {
            if (infoInStrings[i][CategoryNum].equals(Name)) {
                specificArrGrades.add(bootstrapArray[i][course]);
            }

        }

    }

    public ArrayList<Double> getFilter() {
        filerize();
        return specificArrGrades;
    }
}



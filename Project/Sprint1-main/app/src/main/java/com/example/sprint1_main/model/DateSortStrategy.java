package com.example.sprint1_main.model;

import java.util.Collections;
import java.util.List;

public class DateSortStrategy implements Strategy{

    public DateSortStrategy() {

    }

    public List<Sortable> execute(List<Sortable> unsorted) {
        DateCalculatorModel calculator = new DateCalculatorModel();

        int n = unsorted.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (calculator.dateBefore((DateModel)unsorted.get(j), (DateModel)unsorted.get(j+1))) {
                    Collections.swap(unsorted, j, j+1);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

        return unsorted;
    }


}

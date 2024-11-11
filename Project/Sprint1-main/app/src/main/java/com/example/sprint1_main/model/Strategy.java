package com.example.sprint1_main.model;

import java.util.List;

public interface Strategy {


    public List<Sortable> execute(List<Sortable> unsorted);
}

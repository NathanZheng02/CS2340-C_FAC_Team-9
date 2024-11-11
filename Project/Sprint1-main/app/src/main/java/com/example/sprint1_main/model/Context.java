package com.example.sprint1_main.model;

import java.util.List;

public class Context {
    private Strategy strategy;

    public Context() {
        this.strategy = null;
    }

    public List<Sortable> executeStrategy(List<Sortable> unsorted) {
        return strategy.execute(unsorted);
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }


}

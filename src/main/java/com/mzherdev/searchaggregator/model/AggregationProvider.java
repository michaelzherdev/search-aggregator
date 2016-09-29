package com.mzherdev.searchaggregator.model;

import com.mzherdev.searchaggregator.model.strategy.AggregateStrategy;
import com.mzherdev.searchaggregator.vo.SearchResult;

import java.util.List;
import java.util.Set;

/**
 * Created by mzherdev on 28.09.16.
 */
public class AggregationProvider {

    private AggregateStrategy strategy;

    public AggregationProvider(AggregateStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(AggregateStrategy strategy) {
        this.strategy = strategy;
    }

    public String getStrategy() {
        return strategy.getClass().getSimpleName();
    }

    public Set<SearchResult> onNextEngine(List<Set<SearchResult>> resultSet) {
        return strategy.onNextEngine(resultSet);
    }
}

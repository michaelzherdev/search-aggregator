package com.mzherdev.searchaggregator.model;

import com.mzherdev.searchaggregator.model.strategy.Strategy;
import com.mzherdev.searchaggregator.vo.SearchResult;

import java.util.Set;

/**
 * Created by mzherdev on 28.09.16.
 */
public class Provider {

    private Strategy strategy;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public String getStrategy() {
        return strategy.getClass().getSimpleName();
    }

    public Set<SearchResult> getSearchResults(String searchString){
        return strategy.getSearchResults(searchString);
    }
}

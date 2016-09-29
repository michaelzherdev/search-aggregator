package com.mzherdev.searchaggregator.model.strategy;

import com.mzherdev.searchaggregator.vo.SearchResult;

import java.util.List;
import java.util.Set;

/**
 * Created by mzherdev on 28.09.16.
 */
public class RoundRobinStrategy implements AggregateStrategy {

    private int position = -1;
    private int size = 0;

    public Set<SearchResult> onNextEngine(List<Set<SearchResult>> resultSet) {
        this.size = resultSet.size();
        return resultSet.get(getNextIndex());
    }

    private int getNextIndex() {
        while(true) {
            int next = ++position;
            if(next >= size)
                next = 0;
            position = next;
            return position;
        }
    }
}

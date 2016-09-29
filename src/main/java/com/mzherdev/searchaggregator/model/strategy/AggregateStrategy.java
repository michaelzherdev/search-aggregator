package com.mzherdev.searchaggregator.model.strategy;

import com.mzherdev.searchaggregator.vo.SearchResult;

import java.util.List;
import java.util.Set;

/**
 * Created by mzherdev on 28.09.16.
 */
public interface AggregateStrategy {

    Set<SearchResult> onNextEngine(List<Set<SearchResult>> resultSet);
}

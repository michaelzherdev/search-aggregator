package com.mzherdev.searchaggregator.model.strategy;

import com.mzherdev.searchaggregator.vo.SearchResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mzherdev on 29.09.16.
 */
public class AdditionalStrategy implements AggregateStrategy {

    private int position = -1;
    private int size = 0;

    public Set<SearchResult> onNextEngine(List<Set<SearchResult>> resultSet) {
        List<Set<SearchResult>> result = new ArrayList<Set<SearchResult>>();
        Set<SearchResult> searchResults = new HashSet<SearchResult>();
        for (Set set : resultSet)
            searchResults.addAll(set);

        Set<SearchResult> comResults = new HashSet<SearchResult>();
        Set<SearchResult> otherResults = new HashSet<SearchResult>();

        for (SearchResult searchResult : searchResults) {
            if (searchResult.getUrl().contains(".com/") ||
                    searchResult.getUrl().contains(".org/"))
                comResults.add(searchResult);
            else
                otherResults.add(searchResult);

        }
        result.add(comResults);
        result.add(otherResults);
        this.size = result.size();
        return result.get(getNextIndex());
    }

    private int getNextIndex() {
        while (true) {
            int next = ++position;
            if (next >= size)
                next = 0;
            position = next;
            return position;
        }
    }

}

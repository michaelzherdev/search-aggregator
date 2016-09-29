package com.mzherdev.searchaggregator.model;

import com.mzherdev.searchaggregator.vo.SearchResult;
import com.mzherdev.searchaggregator.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by mzherdev on 28.09.16.
 */
public class Model {

    private View view;
    private List<Provider> providers;
    private AggregationProvider aggregationProvider;

    private List<Set<SearchResult>> resultSet; // search results storage

    public Model(View view, List<Provider> providers, AggregationProvider aggregationProvider) {
        if (view == null || providers == null || providers.size() == 0)
            throw new IllegalArgumentException();
        this.view = view;
        this.providers = providers;
        this.aggregationProvider = aggregationProvider;
        resultSet = new ArrayList<>();
    }

    public void makeSearch(String searchString) {
        for (Provider provider : providers)
            resultSet.add(provider.getSearchResults(searchString));
    }

    public void showSearchResult() {
        Set<SearchResult> searchResults = aggregationProvider.onNextEngine(resultSet);
        view.printSearchResultSet(searchResults, getHeaderList(aggregationProvider.getStrategy()));
    }

    private String[] getHeaderList(String aggregateStrategyName) {
        switch (aggregateStrategyName) {
            case "AdditionalStrategy":
                return new String[]{"sites in .com domain ", "sites in other domain "};
            case "RoundRobinStrategy":
            default:
                String[] headers = new String[providers.size()];
                for (int i = 0; i < providers.size(); i++) {
                    headers[i] = providers.get(i).getStrategy().replace("Strategy", "");
                }
                return headers;
        }
    }
}

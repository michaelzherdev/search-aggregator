package com.mzherdev.searchaggregator.view;

import com.mzherdev.searchaggregator.Controller;
import com.mzherdev.searchaggregator.vo.SearchResult;

import java.util.Set;

/**
 * Created by mzherdev on 28.09.16.
 */
public class View {

    Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void makeSearch(String searchString) {
        controller.onSearch(searchString);
    }

    public void printSearchResultSet(Set<SearchResult> searchResults, String[] headers) {
        size = headers.length;
        System.out.println(headers[getNextIndex()] + " found: " + searchResults.size());
        for (SearchResult searchResult : searchResults)
            System.out.println(searchResult);
    }

    public void showSearchResult() {
        controller.onShowResultSet();
    }

    private int position = -1;
    private int size = 0;

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

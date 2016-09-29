package com.mzherdev.searchaggregator;

import com.mzherdev.searchaggregator.model.Model;

/**
 * Created by mzherdev on 28.09.16.
 */
public class Controller {

    Model model;

    public Controller(Model model) {
        if (model == null)
            throw new IllegalArgumentException();
        this.model = model;
    }

    public void onSearch(String searchString) {
        model.makeSearch(searchString);
    }

    public void onShowResultSet() {
        model.showSearchResult();
    }
}

package com.mzherdev.searchaggregator.model.strategy;

import com.mzherdev.searchaggregator.vo.SearchResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static com.mzherdev.searchaggregator.Aggregator.USER_AGENT;

/**
 * Created by mzherdev on 28.09.16.
 */
public class GoogleStrategy implements Strategy {

    private static final String URL = "https://www.google.com/search?q=%s&start=%d";

    public Set<SearchResult> getSearchResults(String searchString) {

        Set<SearchResult> searchResults = new HashSet<SearchResult>();

        try {
            for(int i = 0; i < 20; i += 10) { //parse first 2 pages
                Document document = Jsoup.connect(String.format(URL, searchString, i))
                        .userAgent(USER_AGENT)
                        .get();

                Elements elements = document.select("div.rc");

                for (Element element : elements) {
                    SearchResult searchResult = new SearchResult();
                    searchResult.setName(element.getElementsByTag("h3").first().getElementsByTag("a").first().text());
                    searchResult.setUrl(element.getElementsByTag("h3").first().getElementsByTag("a").attr("href"));
                    searchResult.setDescription(element.select("span.st").first().text());

                    searchResults.add(searchResult);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResults;
    }
}

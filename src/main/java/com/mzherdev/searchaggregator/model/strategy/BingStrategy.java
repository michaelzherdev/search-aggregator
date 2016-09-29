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
 * Created by macuser on 28.09.16.
 */
public class BingStrategy implements Strategy {

    private static final String URL = "http://www.bing.com/search?q=%s&first=%d&FORM=PERE";

    public Set<SearchResult> getSearchResults(String searchString) {

        Set<SearchResult> searchResults = new HashSet<SearchResult>();

        try {
            for (int i = 0; i < 20; i += 10) { //parse first 2 pages
                Document document = Jsoup.connect(String.format(URL, searchString, i))
                        .userAgent(USER_AGENT)
                        .get();

                document.body();
                Elements elements = document.select("li.b_algo");

                for (Element element : elements) {
                    SearchResult searchResult = new SearchResult();
                    Element nameElement = element.getElementsByTag("div").first()
                            .getElementsByTag("h2").first();

                    if (nameElement == null)
                        nameElement = element.getElementsByTag("h2").first();

                    searchResult.setName(nameElement.getElementsByTag("a").first().text());
                    searchResult.setUrl(nameElement.getElementsByTag("a").attr("href"));
                    searchResult.setDescription(element.select("div.b_caption").first().getElementsByTag("p").first().text());

                    searchResults.add(searchResult);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResults;
    }
}

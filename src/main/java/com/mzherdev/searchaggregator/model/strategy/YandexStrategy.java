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
public class YandexStrategy implements Strategy {

    private static final String URL = "https://yandex.ru/search/?text=%s&lr=15&p=%d";

    public Set<SearchResult> getSearchResults(String searchString) {

        Set<SearchResult> searchResults = new HashSet<SearchResult>();

        try {
            for(int i = 0; i < 2; i++) { //parse first 2 pages
                Document document = Jsoup.connect(String.format(URL, searchString, i))
                        .userAgent(USER_AGENT)
                        .get();

                Elements elements = document.select("div.organic");

                for (Element element : elements) {
                    SearchResult searchResult = new SearchResult();
                    Element descElement = element.select("div.organic__content-wrapper").first();
                    if(descElement == null)
                        continue;
                    searchResult.setName(element.getElementsByTag("h2").first().getElementsByTag("a").first().text());
                    searchResult.setUrl(element.getElementsByTag("h2").first().getElementsByTag("a").attr("href"));
                    searchResult.setDescription(descElement.getElementsByTag("div").first().text());

                    searchResults.add(searchResult);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResults;
    }
}

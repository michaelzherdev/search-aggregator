package com.mzherdev.searchaggregator;

import com.mzherdev.searchaggregator.model.strategy.AggregateStrategy;
import com.mzherdev.searchaggregator.model.AggregationProvider;
import com.mzherdev.searchaggregator.model.Model;
import com.mzherdev.searchaggregator.model.Provider;
import com.mzherdev.searchaggregator.model.strategy.*;
import com.mzherdev.searchaggregator.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mzherdev on 28.09.16.
 */
public class Aggregator {

    public static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36";

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter search string: ");
        String searchPhrase = reader.readLine();

        System.out.println("Enter search engine(s) (Google, Bing, Yandex): ");
        String searchEngine = reader.readLine();

        System.out.println("Enter search strategy (RR or ADD)");
        String searchStrategy = reader.readLine();

        List<Provider> providers = new ArrayList<>();

        String[] engines = searchEngine.split("\\W+");

        for (String s : engines)
            providers.add(getProvider(s));

        AggregationProvider aggregationProvider = new AggregationProvider(getAggregateStrategy(searchStrategy));

        View view = new View();
        Model model = new Model(view, providers, aggregationProvider);
        Controller controller = new Controller(model);
        view.setController(controller);

        String searchString = searchPhrase.replace(' ', '+');
        view.makeSearch(searchString);

        view.showSearchResult();

        do {
            System.out.println("************************");
            System.out.println("Enter 'next' to view next result set. Enter 'exit' to quit search");
            String s = reader.readLine();
            if (s.equals("exit"))
                break;
            if (s.equals("next") || s.isEmpty())
                view.showSearchResult();
        } while (true);

        reader.close();
    }

    private static Provider getProvider(String searchEngine) {
        switch (searchEngine.toLowerCase()) {
            case "bing":
                return new Provider(new BingStrategy());
            case "yandex":
                return new Provider(new YandexStrategy());
            case "google":
            default:
                return new Provider(new GoogleStrategy());
        }
    }

    private static AggregateStrategy getAggregateStrategy(String strategy) {
        switch (strategy.toLowerCase()) {
            case "add":
                return new AdditionalStrategy();
            case "rr":
            default:
                return new RoundRobinStrategy();
        }
    }
}

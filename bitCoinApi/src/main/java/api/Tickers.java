package api;

import org.codehaus.jackson.annotate.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by анна on 12.06.2016.
 */
public class Tickers {
    Map<String, String> tickers = new HashMap<>();

    @JsonAnySetter
    public void add(String key, String value) {
        tickers.put(key, value);
    }
    public Map<String, String> tickers() {
        return tickers;
    }
}

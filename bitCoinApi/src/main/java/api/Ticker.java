package api;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by анна on 12.06.2016.
 */
public class Ticker {
    public BigDecimal ask;
    public BigDecimal bid;
    public BigDecimal last;
    public BigDecimal avg;
    public String timestamp;
    public double volumeBtc;
    public double volumePercent;

    @JsonCreator
    public Ticker(
            @JsonProperty("ask") BigDecimal ask,
            @JsonProperty("bid") BigDecimal bid,
            @JsonProperty("last") BigDecimal last,
            @JsonProperty("timestamp") String timestamp,
            @JsonProperty("volume_btc") double volumeBtc,
            @JsonProperty("volume_percent") double volumePercent,
            @JsonProperty("24h_avg") BigDecimal avg
    ) {
        this.ask = ask;
        this.bid = bid;
        this.last = last;
        this.avg = avg;
        this.timestamp = timestamp;
        this.volumeBtc = volumeBtc;
        this.volumePercent = volumePercent;
    }
}

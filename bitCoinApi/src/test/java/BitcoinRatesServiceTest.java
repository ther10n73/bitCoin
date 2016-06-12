import api.BitcoinRatesService;
import api.Ticker;
import api.Tickers;
import api.WebTarget;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by анна on 12.06.2016.
 */
public class BitcoinRatesServiceTest {
    static WebTarget wt;
    static BitcoinRatesService ratesService;

    @BeforeClass
    public static void init() throws Exception {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("https://api.bitcoinaverage.com/");
        ratesService = target.proxy(api.BitcoinRatesService.class);
    }

    @Test
    public void testProxyCreation() throws Exception {
        Assert.assertNotNull("Proxy shouldn't be null", ratesService);
    }

    @Test
    public void testListAll() throws Exception {
        Tickers tickers = ratesService.listAll();
        Assert.assertTrue("We've got some list", tickers.tickers().size() > 5);
        String pathToRUB = tickers.tickers().get("RUB");
        Assert.assertNotNull("There is RUB ticker", pathToRUB);
    }

    @Test
    public void testGet() throws Exception {
        Tickers tickers = ratesService.listAll();
        Ticker rubTicker = ratesService.get("LVL");
        Assert.assertTrue("We've got some list", tickers.tickers().size() > 150);
        Assert.assertNotNull("Ask fields is set", rubTicker.ask);
        Assert.assertNotNull("Bid fields is set", rubTicker.bid);
        Assert.assertNotNull("Last fields is set", rubTicker.last);
        Assert.assertNotNull("Timestamp fields is set", rubTicker.timestamp);
    }
}

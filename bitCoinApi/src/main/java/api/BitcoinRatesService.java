package api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Created by анна on 12.06.2016.
 */
@Produces("application/json")
public interface BitcoinRatesService {

    @GET
    @Path("/ticker/global/")
    Tickers listAll();

    @GET
    @Path("/ticker/global/{tickerName}/")
    Ticker get(@PathParam("tickerName") String tickerName);
}

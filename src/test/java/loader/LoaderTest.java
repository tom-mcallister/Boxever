package loader;

import model.Airport;
import model.AirportFixture;
import model.RouteFixture;
import model.input.Route;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class LoaderTest {


    @Test
    public void shouldLoadRoutes() throws IOException {
        String routesConf = this.getClass().getResource("/routes.csv").getPath();
        List<Route> actualRoutes = Loader.loadRoutes(routesConf);

        List<Route> expectedRoutes = RouteFixture.ROUTES;
        Assert.assertEquals(expectedRoutes, actualRoutes);

    }

    @Test
    public void shouldExtractAirportsFromRoutes() {
        List<Route> routes = RouteFixture.ROUTES;
        List<Airport> expectedAirports = AirportFixture.AIRPORTS;

        List<Airport> actualAirports = Loader.extractAirportsFromRoutes(routes);
        Assert.assertEquals(expectedAirports.size(), actualAirports.size());
        Assert.assertTrue(expectedAirports.containsAll(actualAirports) && actualAirports.containsAll(expectedAirports));

    }



}
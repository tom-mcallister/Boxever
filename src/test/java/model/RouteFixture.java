package model;

import model.Airport;
import model.input.Route;

import java.util.ArrayList;
import java.util.List;

public class RouteFixture {

    public static final List<Route> ROUTES = getRoutesFixture();

    private static List<Route> getRoutesFixture() {

        List<Route> routes = new ArrayList<>();
        routes.add(routeBuilder("DUB", "LHR", 1));
        routes.add(routeBuilder("DUB", "CDG", 2));
        routes.add(routeBuilder("CDG", "BOS", 6));
        routes.add(routeBuilder("CDG", "BKK", 9));
        routes.add(routeBuilder("ORD", "LAS", 2));
        routes.add(routeBuilder("LHR", "NYC", 5));
        routes.add(routeBuilder("NYC", "LAS", 3));
        routes.add(routeBuilder("BOS", "LAX", 4));
        routes.add(routeBuilder("LHR", "BKK", 9));
        routes.add(routeBuilder("BKK", "SYD", 11));
        routes.add(routeBuilder("LAX", "LAS", 2));
        routes.add(routeBuilder("DUB", "ORD", 6));
        routes.add(routeBuilder("LAX", "SYD", 13));
        routes.add(routeBuilder("LAS", "SYD", 14));

        return routes;

    }


    public static Route routeBuilder(String departureAirport, String arrivalAirport, int duration) {
        return Route.builder()
                .departureAirportIataCode(departureAirport)
                .arrivalAirportIataCode(arrivalAirport)
                .duration(duration)
                .build();
    }

}


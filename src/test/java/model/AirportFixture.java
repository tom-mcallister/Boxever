package model;

import java.util.*;

public class AirportFixture {

    public static final List<Airport> AIRPORTS = getAirportsFixture();

    private static List<Airport> getAirportsFixture() {

        List<Airport> airports = new ArrayList<>();
        airports.add(airportBuilder(Map.of("LHR", 1, "CDG", 2, "ORD", 6), "DUB"));
        airports.add(airportBuilder(Map.of("DUB", 1, "BKK", 9, "NYC", 5), "LHR"));
        airports.add(airportBuilder(Map.of("DUB", 2, "BOS", 6, "BKK", 9), "CDG"));
        airports.add(airportBuilder(Map.of("ORD", 2, "NYC", 3, "LAX", 2, "SYD", 14), "LAS"));
        airports.add(airportBuilder(Map.of("LHR", 5, "LAS", 3), "NYC"));
        airports.add(airportBuilder(Map.of("LAS", 2, "DUB", 6), "ORD"));
        airports.add(airportBuilder(Map.of("CDG", 6, "LAX", 4), "BOS"));
        airports.add(airportBuilder(Map.of("CDG", 9, "LHR", 9, "SYD", 11), "BKK"));
        airports.add(airportBuilder(Map.of("BOS", 4, "LAS", 2, "SYD", 13), "LAX"));
        airports.add(airportBuilder(Map.of("BKK", 11, "LAX", 13, "LAS", 14), "SYD"));

        return airports;
    }


    public static Airport airportBuilder(Map<String, Integer> connections, String name) {
        return Airport.builder()
                .connections(connections)
                .name(name)
                .build();
    }

}

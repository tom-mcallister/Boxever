package loader;

import model.Airport;
import model.input.Route;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

public class Loader {

    public static final String[] CSV_HEADERS = new String[]{"dept_airport_iata", "arrival_airport_iata", "duration_hours"};

    public static List<Airport> extractAirportsFromRoutes(List<Route> routes) {
        Set<String> airportIataCodes = new HashSet<>();
        for(Route route : routes){
            airportIataCodes.add(route.getDepartureAirportIataCode());
            airportIataCodes.add(route.getArrivalAirportIataCode());
        }

        return airportIataCodes.stream()
                .map(code -> createAirportWithConnections(code, routes))
                .collect(Collectors.toList());
    }

    private static Airport createAirportWithConnections(String code, List<Route> routes) {
        Map<String, Integer> connections = new HashMap<>();

        List<Route> filteredRoutes = routes.stream()
                .filter(r -> r.getDepartureAirportIataCode().equals(code) || r.getArrivalAirportIataCode().equals(code))
                .collect(Collectors.toList());

        for(Route filteredRoute : filteredRoutes) {
            String connectionName = extractConnectionName(filteredRoute, code);
            connections.put(connectionName, filteredRoute.getDuration());
        }

        return new Airport(code, connections);
    }

    private static String extractConnectionName(Route route, String code) {
        return code.equals(route.getDepartureAirportIataCode()) ? route.getArrivalAirportIataCode() : route.getDepartureAirportIataCode();

    }

    public static List<Route> loadRoutes(String fileName) throws IOException {
        Iterable<CSVRecord> records = readCsv(fileName);
        List<Route> routes = new ArrayList<>();
        for(CSVRecord record : records) {
            Route route = parseRoute(record);
            routes.add(route);
        }
        return routes;
    }

    private static Route parseRoute(CSVRecord record) {
        return Route.builder()
                .departureAirportIataCode(record.get("dept_airport_iata"))
                .arrivalAirportIataCode(record.get("arrival_airport_iata"))
                .duration(Integer.parseInt(record.get("duration_hours")))
                .build();
    }


    private static Iterable<CSVRecord> readCsv(String fileName) throws IOException {
        Reader in = new FileReader(fileName);
        return CSVFormat.DEFAULT
                .withHeader(CSV_HEADERS)
                .withFirstRecordAsHeader()
                .parse(in);
    }
}

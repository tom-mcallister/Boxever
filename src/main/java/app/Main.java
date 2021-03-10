package app;

import calculator.Calculator;
import calculator.CalculatorResult;
import calculator.DijkstraDurationCalculator;
import validator.Validator;
import loader.Loader;
import model.Airport;
import model.input.Route;

import java.io.IOException;
import java.util.*;

public class Main {

    private static final String ROUTES_CONFIG = "routes.csv";

    public static void main(String[] args) {

        List<Route> routes = getRoutes();
        List<Airport> airports = Loader.extractAirportsFromRoutes(routes);
        Validator.Parameters inputParams = Validator.validate(airports);
        Calculator calculator = new DijkstraDurationCalculator(airports);
        CalculatorResult result = calculator.computeMinPath(inputParams.getDepartureAirport(), inputParams.getArrivalAirport());

        System.out.println(result.prettyString());

    }

    private static List<Route> getRoutes() {
        List<Route> routes = null;
        try {
            routes = Loader.loadRoutes(ROUTES_CONFIG);
        } catch (IOException e) {
            System.err.println("Encountered unrecoverable error " + e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        }
        return routes;
    }

}


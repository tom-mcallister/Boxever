package collector;

import lombok.Builder;
import lombok.Value;
import model.Airport;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Collector {


    private static final Pattern INPUT_PATTERN = Pattern.compile("\\w\\w\\w-\\w\\w\\w");
    private static final String SUPPORTED_AIRPORTS = "DUB, LHR, BOS, CDG, LAS, LAX, NYC, ORD, BKK, SYD";


    public static Parameters collect(List<Airport> airports) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your Departure iata code and Arrival iata code separated by a '-'");
        System.out.println("Supported airports are " + SUPPORTED_AIRPORTS);

        while (true) {
            if (scanner.hasNext(INPUT_PATTERN)) {
                String route = scanner.next();
                String[] airportNames = route.split("-");
                String departureAirport = airportNames[0].toUpperCase();
                String arrivalAirport = airportNames[1].toUpperCase();

                if (!departureAirport.equals(arrivalAirport) && validAirportCode(departureAirport, airports) && validAirportCode(arrivalAirport, airports)) {
                    return new Parameters(departureAirport, arrivalAirport);
                }
            } else {
                System.out.println("Please ensure that your input matches the required format and that your selected airports are supported");
                scanner.nextLine(); // flush invalid input
            }
        }
    }


    private static boolean validAirportCode(String airportCode, List<Airport> airports) {
        for (Airport airport : airports) {
            if (airport.getName().equals(airportCode)) {
                return true;
            }
        }
        return false;
    }


    @Value
    @Builder
    public static class Parameters {
        String departureAirport;
        String arrivalAirport;
    }


}

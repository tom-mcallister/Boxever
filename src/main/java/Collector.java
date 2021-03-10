import lombok.Builder;
import lombok.Value;
import model.Airport;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Collector {


    private static Pattern INPUT_PATTERN = Pattern.compile("\\w\\w\\w-\\w\\w\\w");


    public static Parameters collect(List<Airport> airports) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your Departure Iata code and Arrival Iata code separated by a '-'");

        while(true) {
            if(scanner.hasNext(INPUT_PATTERN) ) {
                String route = scanner.next();
                String[] airportNames = route.split("-");
                String departureAirport = airportNames[0].toUpperCase();
                String arrivalAirport = airportNames[1].toUpperCase();

                if(!departureAirport.equals(arrivalAirport) && validAirportCode(departureAirport, airports) && validAirportCode(arrivalAirport, airports)) {
                    return new Parameters(departureAirport, arrivalAirport);
                }
            } else {
                System.out.println("Invalid input, try again");
                scanner.nextLine() ; // flush invalid input
            }
        }
    }



    private static boolean validAirportCode(String airportCode, List<Airport> airports) {
        for(Airport airport : airports) {
            if(airport.getName().equals(airportCode)) {
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

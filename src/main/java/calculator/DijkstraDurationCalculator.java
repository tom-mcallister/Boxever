package calculator;

import lombok.Value;
import model.Airport;

import java.util.*;
import java.util.stream.Collectors;

public class DijkstraDurationCalculator implements Calculator {

    private Map<String, Airport> airports;

    @Value
    private static class AirportDistance {
        String airportCode;
        int distance;
    }

    @Value
    static class DijkstraResult implements CalculatorResult {
        List<String> path;
        List<Integer> durations;
        int duration;
    }

    @Override
    public CalculatorResult computeMinPath(String departureAirport, String arrivalAirport) {
        Map<String, Integer> durationToReachByAirport = new HashMap<>();
        Map<String, String> prevNode = new HashMap<>();


        durationToReachByAirport.put(departureAirport, 0);

        PriorityQueue<AirportDistance> openSet = new PriorityQueue<>(
                Comparator.comparingInt(AirportDistance::getDistance)
        );
        Set<String> closedSet = new HashSet<>();
        openSet.add(new AirportDistance(departureAirport, 0));


        while (closedSet.size() != this.airports.size()) {

            AirportDistance current = openSet.poll();
            String currentAirportCode = current.airportCode;

            closedSet.add(currentAirportCode);

            Map<String, Integer> neighbours = this.airports.get(currentAirportCode).getConnections();

            for (Map.Entry<String, Integer> neighbour : neighbours.entrySet()) {
                String neighbourAirportCode = neighbour.getKey();
                if (!closedSet.contains(neighbourAirportCode)) {
                    int dist = neighbour.getValue() + durationToReachByAirport.get(currentAirportCode);
                    durationToReachByAirport.putIfAbsent(neighbour.getKey(), Integer.MAX_VALUE);
                    int oldDist = durationToReachByAirport.get(neighbourAirportCode);
                    if (dist < oldDist) {
                        durationToReachByAirport.put(neighbourAirportCode, dist);
                        prevNode.put(neighbour.getKey(), currentAirportCode);
                    }
                    openSet.add(new AirportDistance(neighbourAirportCode, durationToReachByAirport.get(neighbourAirportCode)));
                }
            }

        }

        List<String> path = new LinkedList<>();
        path.add(arrivalAirport);
        generatePath(prevNode, arrivalAirport, path);

        List<Integer> durations = path.stream().map(durationToReachByAirport::get).collect(Collectors.toList());

        return new DijkstraResult(path, durations, durationToReachByAirport.get(arrivalAirport));
    }

    private void generatePath(Map<String, String> prevMapping, String arrival, List<String> path) {
        if (prevMapping.containsKey(arrival)) {
            String prev = prevMapping.get(arrival);
            path.add(0, prev);
            generatePath(prevMapping, prev, path);
        }
    }

    public DijkstraDurationCalculator(List<Airport> airports) {
        this.airports = new HashMap<>();
        airports.forEach(a -> this.airports.put(a.getName(), a));
    }
}

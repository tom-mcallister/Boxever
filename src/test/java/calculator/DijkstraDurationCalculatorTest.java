package calculator;

import model.AirportFixture;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class DijkstraDurationCalculatorTest {

    private Calculator calculator;
    private String departureAirport;
    private String arrivalAirport;
    private Integer expectedDuration;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"BKK", "SYD", 11},
                {"BKK", "CDG", 9},
                {"BKK", "LHR", 9},

                {"BOS", "LAX", 4},
                {"BOS", "CDG", 6},

                {"CDG", "BOS", 6},
                {"CDG", "BKK", 9},
                {"CDG", "DUB", 2},

                {"DUB", "LHR", 1},
                {"DUB", "CDG", 2},
                {"DUB", "ORD", 6},

                {"LAS", "SYD", 14},
                {"LAS", "NYC", 3},
                {"LAS", "LAX", 2},
                {"LAS", "ORD", 2},

                {"LAX", "LAS", 2},
                {"LAX", "BOS", 4},
                {"LAX", "SYD", 13},

                {"LHR", "DUB", 1},
                {"LHR", "BKK", 9},
                {"LHR", "NYC", 5},

                {"NYC", "LAS", 3},
                {"NYC", "LHR", 5},

                {"ORD", "LAS", 2},
                {"ORD", "DUB", 6},

                {"SYD", "BKK", 11},
                {"SYD", "LAS", 14},
                {"SYD", "LAX", 13},

                {"BKK", "DUB", 10},
                {"BKK", "NYC", 14},
                {"BKK", "BOS", 15},
                {"BKK", "LAX", 19},
                {"BKK", "ORD", 16},
                {"BKK", "LAS", 17},

                {"BOS", "LAS", 6},
                {"BOS", "ORD", 8},
                {"BOS", "NYC", 9},
                {"BOS", "DUB", 8},
                {"BOS", "LHR", 9},
                {"BOS", "BKK", 15},
                {"BOS", "SYD", 17},

                {"CDG", "LHR", 3},
                {"CDG", "NYC", 8},
                {"CDG", "ORD", 8},
                {"CDG", "LAS", 10},
                {"CDG", "LAX", 10},
                {"CDG", "SYD", 20},

                {"DUB", "NYC", 6},
                {"DUB", "BKK", 10},
                {"DUB", "LAS", 8},
                {"DUB", "LAX", 10},
                {"DUB", "SYD", 21},
                {"DUB", "BOS", 8},

                {"LAS", "BOS", 6},
                {"LAS", "DUB", 8},
                {"LAS", "LHR", 8},
                {"LAS", "CDG", 10},
                {"LAS", "BKK", 17},

                {"LAX", "ORD", 4},
                {"LAX", "CDG", 10},
                {"LAX", "DUB", 10},
                {"LAX", "BKK", 19},
                {"LAX", "LHR", 10},
                {"LAX", "NYC", 5},

                {"LHR", "CDG", 3},
                {"LHR", "ORD", 7},
                {"LHR", "LAS", 8},
                {"LHR", "LAX", 10},
                {"LHR", "BOS", 9},
                {"LHR", "SYD", 20},

                {"NYC", "DUB", 6},
                {"NYC", "CDG", 8},
                {"NYC", "BOS", 9},
                {"NYC", "ORD", 5},
                {"NYC", "SYD", 17},
                {"NYC", "LAX", 5},
                {"NYC", "BKK", 14},

                {"ORD", "BOS", 8},
                {"ORD", "BKK", 16},
                {"ORD", "CDG", 8},
                {"ORD", "NYC", 5},
                {"ORD", "LAX", 4},
                {"ORD", "LHR", 7},
                {"ORD", "SYD", 16},

                {"SYD", "DUB", 21},
                {"SYD", "ORD", 16},
                {"SYD", "BOS", 17},
                {"SYD", "NYC", 17},
                {"SYD", "CDG", 20},
                {"SYD", "LHR", 20},

        });
    }

    @Before
    public void initialize() {
        calculator = new DijkstraDurationCalculator(AirportFixture.AIRPORTS);
    }

    public DijkstraDurationCalculatorTest(String departureAirport, String arrivalAirport, Integer expectedDuration) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.expectedDuration = expectedDuration;
    }


    /**
     *
     * This test looks at all combinations of flights from the provided routes.
     * It first looks at the one leg flights from each airport and then considers multi-leg journeys
     *
     */

    @Test
    public void shouldComputeFlightDistances() {
        Integer actualDuration = calculator.computeMinPath(departureAirport, arrivalAirport).getDuration();
        Assert.assertEquals(expectedDuration, actualDuration);
    }

}
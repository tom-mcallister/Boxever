package model.input;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Route {
    @NonNull
    private String departureAirportIataCode;
    @NonNull
    private String arrivalAirportIataCode;
    private int duration;
}

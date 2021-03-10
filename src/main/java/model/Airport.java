package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class Airport {

    private String name;
    private Map<String, Integer> connections;

}

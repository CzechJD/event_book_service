package org.example.location;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Location {
    private final Integer id;
    private final String name;
    private final String address;
    private final Integer capacity;
    private final String description;
}

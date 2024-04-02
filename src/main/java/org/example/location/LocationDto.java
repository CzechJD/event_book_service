package org.example.location;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationDto {
    private final Integer id;
    @NotBlank
    private final String name;
    @NotBlank
    private final String address;
    @NotNull
    @Min(5)
    private final Integer capacity;
    private final String description;
}

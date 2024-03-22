package org.example.location;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationDto {
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotNull
    @Min(5)
    private Integer capacity;
    private String description;
}

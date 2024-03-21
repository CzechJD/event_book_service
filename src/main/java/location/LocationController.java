package location;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
@AllArgsConstructor
public class LocationController {
    private final LocationService locationService;
    private LocationMapper mapper;

    @GetMapping
    public ResponseEntity<List<LocationDto>> getAllLocations() {
        List<LocationDto> locationsDto = locationService.getAllLocations().stream()
                .map(mapper::fromLocationToDto)
                .toList();
        return ResponseEntity.ok(locationsDto);
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<LocationDto> getLocationById(@PathVariable("locationId") Integer id) {
        Location location = locationService.getLocationById(id);
        LocationDto locationDto = mapper.fromLocationToDto(location);
        return ResponseEntity.ok(locationDto);
    }

    @PostMapping
    public ResponseEntity<LocationDto> createLocation(@RequestBody @Validated LocationDto locationDto) {
        Location location = mapper.fromDtoToLocation(locationDto);
        Location createdLocation = locationService.createLocation(location);
        LocationDto createdLocationDto = mapper.fromLocationToDto(createdLocation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLocationDto);
    }

    @PutMapping("/{locationId}")
    public ResponseEntity<LocationDto> updateLocationById(@PathVariable("locationId") Integer id,
                                                          @RequestBody @Validated LocationDto locationDto) {
        Location location = mapper.fromDtoToLocation(locationDto);
        Location updatedLocation = locationService.updateLocation(id, location);
        LocationDto updatedLocationDto = mapper.fromLocationToDto(updatedLocation);
        return ResponseEntity.ok(updatedLocationDto);
    }

    @DeleteMapping("/{locationId}")
    public ResponseEntity<Void> deleteLocation(@PathVariable("locationId") Integer id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}

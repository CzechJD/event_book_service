package org.example.location;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationService {
    private final LocationRepository repository;
    private final LocationMapper mapper;

    public List<Location> getAllLocations() {
        return repository.findAll().stream()
                .map(mapper::fromEntityToLocation)
                .toList();
    }

    public Location getLocationById(Integer id) {
        LocationEntity foundEntity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Сущность не найдена"));
        return mapper.fromEntityToLocation(foundEntity);
    }

    public Location createLocation(Location location) {
        if (location == null) {
            throw new NullPointerException("Локация не может быть пустой");
        }
        LocationEntity entity = mapper.fromLocationToEntity(location);
        LocationEntity savedEntity = repository.save(entity);
        return mapper.fromEntityToLocation(savedEntity);
    }

    public Location updateLocation(Integer id, Location location) {
        LocationEntity foundEntity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Сущность не найдена"));

        foundEntity.setName(location.getName());
        foundEntity.setAddress(location.getAddress());
        foundEntity.setCapacity(location.getCapacity());
        foundEntity.setDescription(location.getDescription());

        LocationEntity savedEntity = repository.save(foundEntity);
        return mapper.fromEntityToLocation(savedEntity);
    }
    public void deleteLocation(Integer id) {
        repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Сущность не найдена"));

        repository.deleteById(id);

    }
}
package edu.ap.facilitytoolspringboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ap.facilitytoolspringboot.models.Location;
import edu.ap.facilitytoolspringboot.repositories.LocationRepository;

@Service
public class LocationService {
    private LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location create(Location location) {
        return locationRepository.save(location);

    }

    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    public List<Location> getByName(String name) {
        return locationRepository.findByName(name);
    }

    public Location getByRoom(String room) {
        return locationRepository.findByRoom(room);
    }

    public List<Location> getByCampus(String campus) {
        return locationRepository.findByCampus(campus);
    }

    public List<Location> getByFloor(int floor) {
        return locationRepository.findByFloor(floor);
    }

}
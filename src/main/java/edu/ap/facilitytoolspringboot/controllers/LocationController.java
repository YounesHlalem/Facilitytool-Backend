package edu.ap.facilitytoolspringboot.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.ap.facilitytoolspringboot.models.Location;
import edu.ap.facilitytoolspringboot.services.LocationService;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path="api")
public class LocationController {
    private static final Logger LOG = LoggerFactory.getLogger(LocationController.class);
    private LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/locations")
    public ResponseEntity<List<Location>> getAll() {
        try {
            List<Location> locations = locationService.getAll();
            if (locations.isEmpty()) {
                LOG.info("There are no locations to return");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            LOG.info("Returned all locations");
            return new ResponseEntity<>(locations, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Couldn't return the locations", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/locations/by-name/{name}")
    public ResponseEntity<List<Location>> getByName(@PathVariable("name") String name) {
        try {
            List<Location> locations = locationService.getByName(name);
            if (locations.isEmpty()) {
                LOG.info("There are no locations with the name: {}", name);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            LOG.info("Returned the locations with the name: {}", name);
            return new ResponseEntity<>(locations, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Couldn't return the locations with the name: {}", name, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/locations/by-room/{room}")
    public ResponseEntity<Location> getByRoom(@PathVariable("room") String room) {
        try {
            Location location = locationService.getByRoom(room);
            if (location == null) {
                LOG.info("There are no locations matching the given room ({})", room);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            LOG.info("Returned the location matching the given room ({})", room);
            return new ResponseEntity<>(location, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Couldn't return the location with room: {}", room, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/locations/by-campus/{campus}")
    public ResponseEntity<List<Location>> getByCampus(@PathVariable("campus") String campus) {
        try {
            List<Location> locations = locationService.getByCampus(campus);
            if (locations.isEmpty()) {
                LOG.info("Campus: {} doesn't exist", campus);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            LOG.info("Returned all locations of the campus: {}", campus);
            return new ResponseEntity<>(locations, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Couldn't return the locations of the campus: {}", campus, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/locations/by-floor/{floor}")
    public ResponseEntity<List<Location>> getByFloor(@PathVariable("floor") int floor) {
        try {
            List<Location> locations = locationService.getByFloor(floor);
            if (locations.isEmpty()) {
                LOG.info("There are no locations on floor {}", floor);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            LOG.info("Returned all locations of floor {}", floor);
            return new ResponseEntity<>(locations, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Couldn't return th locations of floor {}", floor, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/locations")
    public ResponseEntity<Location> postLocation(@RequestBody Location location) {
        try {
            Location loc = locationService.create(location);
            LOG.info("Created a new location");
            return new ResponseEntity<>(loc, HttpStatus.CREATED);
        } catch (Exception e) {
            LOG.error("Couldn't create a new location", e);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}

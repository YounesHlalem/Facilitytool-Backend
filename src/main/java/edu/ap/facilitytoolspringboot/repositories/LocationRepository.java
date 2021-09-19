package edu.ap.facilitytoolspringboot.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.ap.facilitytoolspringboot.models.Location;

@Repository
public interface LocationRepository extends MongoRepository<Location, String> {

    List<Location> findByCampus(String campus);

    List<Location> findByFloor(int floor);

    Location findByRoom(String room);

    List<Location> findByName(String name);

}
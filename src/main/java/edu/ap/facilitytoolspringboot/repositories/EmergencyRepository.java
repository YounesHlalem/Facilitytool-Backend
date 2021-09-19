package edu.ap.facilitytoolspringboot.repositories;

import edu.ap.facilitytoolspringboot.models.Emergency;
import edu.ap.facilitytoolspringboot.models.Report;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyRepository extends MongoRepository<Emergency, String> {
}

package edu.ap.facilitytoolspringboot.repositories;

import edu.ap.facilitytoolspringboot.models.ExternalFirm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalFirmRepository extends MongoRepository<ExternalFirm, String> {
}

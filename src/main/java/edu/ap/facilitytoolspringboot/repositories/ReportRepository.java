package edu.ap.facilitytoolspringboot.repositories;

import java.util.List;

import edu.ap.facilitytoolspringboot.models.Report;
import edu.ap.facilitytoolspringboot.models.enums.EnumStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends MongoRepository<Report, String> {
    Report findByReporter(String reporter);
    List<Report> findByLocation(String location);
    List<Report> findByCategory(String category);
    List<Report> findByStatus(EnumStatus status);
}
package edu.ap.facilitytoolspringboot.repositories;

import edu.ap.facilitytoolspringboot.models.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
}

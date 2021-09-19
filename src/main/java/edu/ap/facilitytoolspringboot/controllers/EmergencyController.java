package edu.ap.facilitytoolspringboot.controllers;

import edu.ap.facilitytoolspringboot.models.Emergency;
import edu.ap.facilitytoolspringboot.models.Report;
import edu.ap.facilitytoolspringboot.repositories.EmergencyRepository;
import edu.ap.facilitytoolspringboot.repositories.ReportRepository;
import edu.ap.facilitytoolspringboot.services.EmergencyService;
import edu.ap.facilitytoolspringboot.services.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@Controller
@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path="api")
public class EmergencyController {

    private static final Logger LOG = LoggerFactory.getLogger(ReportController.class);
    private EmergencyService emergencyService;
    private EmergencyRepository emergencyRepository;

    @Autowired
    public EmergencyController(EmergencyService emergencyService) {
        this.emergencyService = emergencyService;
    }

    @GetMapping("/emergencies")
    public ResponseEntity<List<Emergency>> getAll() {
        try {
            List<Emergency> emergencies = emergencyService.getAllEmergencies();
            if (emergencies.isEmpty()) {
                LOG.info("There are no emergencies to return");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            LOG.info("Returned all reports");
            return new ResponseEntity<>(emergencies, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Couldn't return the reports", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/emergencies")
    public ResponseEntity<Emergency> postEmergency(@RequestBody Emergency emergency) {
        try {
            Emergency em = emergencyService.create(emergency);
            LOG.info("Created a new Emergency with the id: {}", em.getId());
            return new ResponseEntity<>(em, HttpStatus.CREATED);
        } catch (Exception e) {
            LOG.error("Couldn't create a new emergency", e);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/emergencies/{id}")
    public ResponseEntity<Emergency> putEmergency(@PathVariable("id") String id, @RequestBody Emergency emergency) {
        try {
            Emergency em = emergencyService.changeEmergency(id,emergency);
            LOG.info("changed an Emergency with the id: {}", em.getId());
            return new ResponseEntity<>(em, HttpStatus.CREATED);
        } catch (Exception e) {
            LOG.error("Couldn't change an emergency", e);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

   @DeleteMapping("/emergencies/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") String id) {
        try {
            emergencyService.deleteById(id);
            LOG.info("Emergency with id: {} removed successfully", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            LOG.error("Couldn't remove the Emergency with id: {}", id, e);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}

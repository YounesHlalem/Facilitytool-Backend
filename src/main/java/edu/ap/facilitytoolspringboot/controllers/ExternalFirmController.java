package edu.ap.facilitytoolspringboot.controllers;


import edu.ap.facilitytoolspringboot.models.ExternalFirm;
import edu.ap.facilitytoolspringboot.repositories.ExternalFirmRepository;
import edu.ap.facilitytoolspringboot.services.ExternalFirmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path="api")
public class ExternalFirmController {

    private static final Logger LOG = LoggerFactory.getLogger(ReportController.class);
    private ExternalFirmService externalFirmService;
    private ExternalFirmRepository externalFirmRepository;

    @Autowired
    public ExternalFirmController(ExternalFirmService externalFirmService) {
        this.externalFirmService = externalFirmService;
    }

    @GetMapping("/externalFirms")
    public ResponseEntity<List<ExternalFirm>> getAll() {
        try {
            List<ExternalFirm> externalFirms = externalFirmService.getAllExternalFirms();
            if (externalFirms.isEmpty()) {
                LOG.info("There are no externalFirms to return");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            LOG.info("Returned all externalFirms");
            return new ResponseEntity<>(externalFirms, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Couldn't return the externalFirms", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/externalFirms")
    public ResponseEntity<ExternalFirm> postExternalFirm(@RequestBody ExternalFirm externalFirm) {
        try {
            ExternalFirm ef = externalFirmService.create(externalFirm);
            LOG.info("Created a new ExternalFirm with the id: {}", ef.getId());
            return new ResponseEntity<>(ef, HttpStatus.CREATED);
        } catch (Exception e) {
            LOG.error("Couldn't create a new externalFirm", e);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/externalFirm/{id}")
    public ResponseEntity<ExternalFirm> putExternalFirm(@PathVariable("id") String id, @RequestBody ExternalFirm externalFirm) {
        try {
            ExternalFirm ef = externalFirmService.changeExternalFirm(id,externalFirm);
            LOG.info("changed an ExternalFirm with the id: {}", ef.getId());
            return new ResponseEntity<>(ef, HttpStatus.CREATED);
        } catch (Exception e) {
            LOG.error("Couldn't change an ExternalFirm", e);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/externalFirm/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") String id) {
        try {
            externalFirmService.deleteById(id);
            LOG.info("ExternalFirm with id: {} removed successfully", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            LOG.error("Could not remove the ExternalFirm with id: {}", id, e);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}

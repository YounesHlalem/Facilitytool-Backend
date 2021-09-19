package edu.ap.facilitytoolspringboot.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.ap.facilitytoolspringboot.models.MailTemplate;
import edu.ap.facilitytoolspringboot.services.MailTemplateService;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path="api")
public class MailTemplateController {
    private static final Logger LOG = LoggerFactory.getLogger(MailTemplateController.class);
    private MailTemplateService mailTemplateService;

    @Autowired
    public MailTemplateController(MailTemplateService mailTemplateService) {
        this.mailTemplateService = mailTemplateService;
    }

    @GetMapping("/mail-templates")
    public ResponseEntity<List<MailTemplate>> getAll() {
        try {
            List<MailTemplate> mailtTemplates = mailTemplateService.getAll();
            if (mailtTemplates.isEmpty()) {
                LOG.info("There are no mail templates to return");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            LOG.info("Returned all mail templates");
            return new ResponseEntity<>(mailtTemplates, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Couldn't return the mail templates", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/mail-templates")
    public ResponseEntity<MailTemplate> postMailTemplate(@RequestBody MailTemplate mailTemplate) {
        try {
            MailTemplate mt = mailTemplateService.create(mailTemplate);
            LOG.info("Created a new mail template");
            return new ResponseEntity<>(mt, HttpStatus.CREATED);
        } catch (Exception e) {
            LOG.info("Couldn't create a new mail template", e);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/mail-templates/by-name/{name}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("name") String name) {
        try {
            mailTemplateService.deleteByName(name);
            LOG.info("Deleted the mail template with the name: {}", name);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            LOG.error("Couldn't delete the mail template with the name; {}", name);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}

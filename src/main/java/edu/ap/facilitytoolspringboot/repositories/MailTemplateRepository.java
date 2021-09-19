package edu.ap.facilitytoolspringboot.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.ap.facilitytoolspringboot.models.MailTemplate;

@Repository
public interface MailTemplateRepository extends MongoRepository<MailTemplate, String> {
    MailTemplate findByName(String name);
}
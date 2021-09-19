package edu.ap.facilitytoolspringboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ap.facilitytoolspringboot.models.MailTemplate;
import edu.ap.facilitytoolspringboot.repositories.MailTemplateRepository;

@Service
public class MailTemplateService {
    private MailTemplateRepository mailTemplateRepository;

    @Autowired
    public MailTemplateService(MailTemplateRepository mailTemplateRepository) {
        this.mailTemplateRepository = mailTemplateRepository;
    }

    public MailTemplate create(MailTemplate mailTemplate) {
        return mailTemplateRepository.save(mailTemplate);
    }

    public List<MailTemplate> getAll() {
        return mailTemplateRepository.findAll();
    }

    public void deleteByName(String name) {
        MailTemplate m = mailTemplateRepository.findByName(name);
        mailTemplateRepository.delete(m);
    }
}
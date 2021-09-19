package edu.ap.facilitytoolspringboot.services;

import edu.ap.facilitytoolspringboot.models.ExternalFirm;
import edu.ap.facilitytoolspringboot.repositories.ExternalFirmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExternalFirmService {

    private ExternalFirmRepository externalFirmRepository;

    @Autowired
    public ExternalFirmService(ExternalFirmRepository externalFirmRepository) {
        this.externalFirmRepository = externalFirmRepository;
    }

    public ExternalFirm create(ExternalFirm externalFirm) {
        return externalFirmRepository.save(externalFirm);
    }


    public ExternalFirm changeExternalFirm(String id, ExternalFirm externalFirm) {
        Optional<ExternalFirm> existingExternalFirm = externalFirmRepository.findById(id);

        if (existingExternalFirm.isPresent()) {
            ExternalFirm externalFirm1 = existingExternalFirm.get();
            externalFirm1 = externalFirm;
            externalFirmRepository.save(externalFirm1);
            return externalFirm1;
        }
        return null;
    }

    public List<ExternalFirm> getAllExternalFirms() {
        return externalFirmRepository.findAll();
    }


    /***
     * Bij andere services hebben we eerst gekeken of een id present is,
     * maar hier returnen we de id en kijken we of dat het bestaat of niet.
     * @param id
     * @return
     */
    public ExternalFirm getById(String id) {
        Optional<ExternalFirm> externalFirm = externalFirmRepository.findById(id);
        return externalFirm.orElse(null);
    }

    public void deleteById(String id) {
        externalFirmRepository.deleteById(id);
    }


}

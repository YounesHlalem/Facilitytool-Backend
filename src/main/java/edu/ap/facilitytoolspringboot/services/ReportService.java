package edu.ap.facilitytoolspringboot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.ap.facilitytoolspringboot.models.Report;
import edu.ap.facilitytoolspringboot.models.enums.EnumStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ap.facilitytoolspringboot.models.Reaction;
import edu.ap.facilitytoolspringboot.repositories.ReportRepository;

@Service
public class ReportService {
    private ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public Report create(Report report) {
        return reportRepository.save(report);
    }

    /***
     * we zoeken eerst of dat de id van een melding bestaat,
     * daarna halen we specifieke reacties op en voegen we dat achter de lijst op.
     * @param id
     * @param reaction
     * @return
     */
    public Reaction saveReactions(String id, Reaction reaction) {
        Optional<Report> report = reportRepository.findById(id);
        if (report.isPresent()){
            Report rep = report.get();
            List<Reaction> reactionList = rep.getReactions();
            reactionList.add(reaction);
            rep.setReactions(reactionList);
            reportRepository.save(rep);
        }
        return reaction;
    }

    public List<Report> getAllReports() {
        List<Report> allReports = reportRepository.findAll();
        List<Report> reportsNotInArchive = new ArrayList<>();
        for (Report report : allReports) {
            if (report.getStatus() != EnumStatus.VOLTOOID && report.getStatus() != EnumStatus.GEANNULEERD
            && report.getStatus() != EnumStatus.WORDT_NIET_UITGEVOERD) {
                reportsNotInArchive.add(report);
            }
        }
        return reportsNotInArchive;
    }

    public Report getByReporter(String reporter) {
        return reportRepository.findByReporter(reporter);
    }

    public List<Report> getByLocation(String location) {
        return reportRepository.findByLocation(location);
    }

    public Report getById(String id) {
        Optional<Report> report = reportRepository.findById(id);
        return report.orElse(null);
    }

    // Archive
    /***
     * Een melding gaat naar het archief als het Voltooid, Geannuleerd of Niet uitgevoerd wordt.
     * Dit hebben we gedaan via een enum.
     * @param type
     * @return
     */
    public List<Report> getReportsForArchive(String type){
        List<Report> allReports = reportRepository.findAll();
        List<Report> reportsForArchive = new ArrayList<>();
        for (Report report : allReports) {
            if ( report.getType().trim().equalsIgnoreCase(type) && (
                    report.getStatus() == EnumStatus.VOLTOOID ||
                    report.getStatus() == EnumStatus.GEANNULEERD ||
                    report.getStatus() == EnumStatus.WORDT_NIET_UITGEVOERD
                    )
            ) {
                reportsForArchive.add(report);
            }
        }
        return reportsForArchive;
    }

    public void deleteAll() {
        reportRepository.deleteAll();
    }

    public void deleteByReporter(String reporter) {
        Report report = reportRepository.findByReporter(reporter);
        reportRepository.delete(report);
    }

    public void deleteById(String id) {
        reportRepository.deleteById(id);
    }


    // Upvote
    /***
     *
     * @param reportId
     * @param userId
     * @return
     */
    public Report toggleUpVote(String reportId, String userId){
        Optional<Report> existingDefect = reportRepository.findById(reportId);


        if (existingDefect.isPresent()) {
            List<String> idsOfUpVoters;
            Report report = existingDefect.get();
            idsOfUpVoters = report.getUpVotedByIds();

            if (report.getUpVotedByIds().contains(userId)) {
                report.decrementNumberUpVotes();
                idsOfUpVoters.remove(userId);
            } else {
                report.incrementNumberUpVotes();
                idsOfUpVoters.add(userId);
            }

            report.setUpVotedByIds(idsOfUpVoters);
            reportRepository.save(report);
            return report;
        }
        return null;
    }

    // statusChange
    public Report changeStatus(String id, EnumStatus status) {
        Optional<Report> existingDefect = reportRepository.findById(id);

        if (existingDefect.isPresent()) {
            Report report = existingDefect.get();
            report.setStatus(status);
            
            reportRepository.save(report);
            return report;
        }
        return null;
    }

    public Report changeReport(String id, Report report) {
        Optional<Report> existingReport = reportRepository.findById(id);

        if (existingReport.isPresent()) {
            Report r1 = existingReport.get();
            r1 = report;
            reportRepository.save(r1);
            return r1;
        }
        return null;
    }
}

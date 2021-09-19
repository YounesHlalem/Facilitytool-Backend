package edu.ap.facilitytoolspringboot.services;

import edu.ap.facilitytoolspringboot.models.Report;
import edu.ap.facilitytoolspringboot.models.User;
import edu.ap.facilitytoolspringboot.repositories.UserRepository;
import edu.ap.facilitytoolspringboot.models.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private ReportService reportService;

    @Autowired
    public UserService(UserRepository userRepository, ReportService reportService) {
        this.userRepository = userRepository;
        this.reportService = reportService;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    /***
     * We kunnen een specifeke gebruiker ophalen met de id en het model van UserPrincipal
     * @param userPrincipal
     * @return
     */
    public User getCurrentUser(UserPrincipal userPrincipal) {
        Optional<User> user =  userRepository.findById(userPrincipal.getId());
        return user.orElse(null);
    }

    public List<Report> getAllReports(String userId){
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User user1 = user.get();
            List<String> reportIds = user1.getAssignedReportsId();
            List<Report> reports = new ArrayList<>();
            for (String reportId : reportIds) {
                reports.add(reportService.getById(reportId));
            }
            return reports;
        }
        return new ArrayList<>();
    }

    public String addAssignedReportId(String userId, String reportId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User user1 = user.get();
            if (!user1.isReportAlreadyAssigned(reportId)) {
                List<String> userList = user1.getAssignedReportsId();
                userList.add(reportId);
                user1.setAssignedReportsId(userList);
                userRepository.save(user1);
                return reportId;
            }
        }
        return null;
    }

    /***
     * We kijken via id en de body van een user of dat de id eigenlijk bestaat.
     * Als een specifieke rol niet gelijk is aan een rol die werd opgehaald dan wordt er een rol opgeslaan.
     * @param id
     * @param updatedUser
     * @return
     */
    public User updateRole(String id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            String role = updatedUser.getRole();
            if (!role.equals(user.getRole())) {
                user.setRole(role);
                userRepository.save(user);
            }
            return user;
        } else {
            userRepository.save(updatedUser);
            return updatedUser;
        }
    }

    public User removeRole(String id) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setRole("Medewerker");
            userRepository.save(user);
            return user;
        } else {
            return null;
        }
    }
}

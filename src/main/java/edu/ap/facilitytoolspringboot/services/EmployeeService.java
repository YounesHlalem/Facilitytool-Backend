package edu.ap.facilitytoolspringboot.services;

import edu.ap.facilitytoolspringboot.models.Employee;
import edu.ap.facilitytoolspringboot.models.Report;
import edu.ap.facilitytoolspringboot.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private ReportService reportService; 

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ReportService reportService) {
        this.employeeRepository = employeeRepository;
        this.reportService = reportService; 
    }

    public List<Employee> getAll() {
       return employeeRepository.findAll();
    }

    public Employee getById(String id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElse(null);
    }

    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    /***
     * Als we een melding willen toewijzen zoeken we de id, maar ook de id van een werknemer
     * @param employeeId
     * @param reportId
     * @return
     */
    public String addAssignedReportId(String employeeId, String reportId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            Employee emp = employee.get();
            if (!emp.isReportAlreadyAssigned(reportId)) {
                List<String> employeeList = emp.getAssignedReportsId();
                employeeList.add(reportId);
                emp.setAssignedReports(employeeList);
                employeeRepository.save(emp);
                return reportId;
            }
        }
        return null;
    }

    public List<Report> getAllReports(String employeeId){
        Employee employee = getById(employeeId);
        List<String> reportIds = employee.getAssignedReportsId(); 
        List<Report> reports = new ArrayList<>();
        for (String reportId : reportIds) {
            reports.add(reportService.getById(reportId));
        }
        return reports; 
    }

}

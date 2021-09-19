package edu.ap.facilitytoolspringboot.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String pNumber;
    private List<String> assignedReportsId;

    public Employee() {
        assignedReportsId = new ArrayList<>();
    }

    public Employee(String name, String pNumber) {
        this.name = name;
        this.pNumber = pNumber;
    }

    // For testing only
    public Employee(String id, String name, String pNumber) {
        this.id = id;
        this.name = name;
        this.pNumber = pNumber;
        assignedReportsId = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPNumber() {
        return pNumber;
    }

    public void setPNumber(String pNumber) {
        this.pNumber = pNumber;
    }

    public List<String> getAssignedReportsId() {
        return assignedReportsId;
    }

    public void setAssignedReports(List<String> assignedReportsId) {
        this.assignedReportsId = assignedReportsId;
    }

    public boolean isReportAlreadyAssigned(String reportId) {
        List<String> allReportIds = new ArrayList<>();
        for (String repId : assignedReportsId ) {
            allReportIds.add(repId);
        }
        return allReportIds.contains(reportId);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pNumber='" + pNumber + '\'' +
                ", assignedReportsId=" + assignedReportsId +
                '}';
    }
    
}

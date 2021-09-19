package edu.ap.facilitytoolspringboot.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document(collection = "emergency")
public class Emergency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String emergencyContactsType;
    private String employeeType;
    private String employeeName;
    private String phoneNumber;
    private String mobileNumber;
    private String mail;
    private String mail2;

    public Emergency() {
        super();
    }

    public Emergency(String employeeName) {
        this.employeeName = employeeName;
    }

    public Emergency(String employeeName, String mail) {
        this.employeeName = employeeName;
        this.mail = mail;
    }


    public Emergency(String id, String emergencyContactsType, String employeeType, String employeeName, String phoneNumber, String mobileNumber, String mail, String mail2) {
        this.id = id;
        this.emergencyContactsType = emergencyContactsType;
        this.employeeType = employeeType;
        this.employeeName = employeeName;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
        this.mail = mail;
        this.mail2 = mail2;
    }

    public String getId() {
        return id;
    }

    public String getEmergencyContactsType() {
        return emergencyContactsType;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getMail() {
        return mail;
    }

    public String getMail2() {
        return mail2;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmergencyContactsType(String emergencyContactsType) {
        this.emergencyContactsType = emergencyContactsType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMail2(String mail2) {
        this.mail2 = mail2;
    }

    @Override
    public String toString() {
        return "Emergency{" +
                "id='" + id + '\'' +
                ", emergencyContactsType='" + emergencyContactsType + '\'' +
                ", employeeType='" + employeeType + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", mail='" + mail + '\'' +
                ", mail2='" + mail2 + '\'' +
                '}';
    }
}

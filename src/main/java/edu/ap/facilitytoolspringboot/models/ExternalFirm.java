package edu.ap.facilitytoolspringboot.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document(collection = "externalFirm")
public class ExternalFirm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String contactPerson;
    private String mail;
    private String phoneNumber;
    private String mobileNumber;
    private String companyName;

    public ExternalFirm(){

    }

    public ExternalFirm(String contactPerson, String companyName) {
        this.contactPerson = contactPerson;
        this.companyName = companyName;
    }

    public ExternalFirm(String id, String contactPerson, String mail, String phoneNumber, String mobileNumber, String companyName) {
        this.id = id;
        this.contactPerson = contactPerson;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
        this.companyName = companyName;
    }

    public ExternalFirm(String contactPerson, String mail, String phoneNumber, String mobileNumber, String companyName) {
        this.contactPerson = contactPerson;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
        this.companyName = companyName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    @Override
    public String toString() {
        return "ExternalFirm{" +
                "id='" + id + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", mail='" + mail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}

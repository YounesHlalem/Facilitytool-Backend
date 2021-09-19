package edu.ap.facilitytoolspringboot.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document(collection = "locatie")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String campus;
    private int floor;
    private String room;
    private String name;

    public Location() {
    }

    public Location(String campus, int floor, String room, String name) {
        this.campus = campus;
        this.floor = floor;
        this.room = room;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCampus() {
        return this.campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public int getFloor() {
        return this.floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getRoom() {
        return this.room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id='" + id + '\'' +
                ", campus='" + campus + '\'' +
                ", floor=" + floor +
                ", room='" + room + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
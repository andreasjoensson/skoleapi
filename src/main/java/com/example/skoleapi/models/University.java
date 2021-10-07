package com.example.skoleapi.models;

import javax.persistence.*;

@Entity
@Table(name = "universitys")
public class University {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;
    private String logo;
    private String type;
    private String city;

    public University(long id, String name, String logo, String type, String city) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.type = type;
        this.city = city;
    }

    public University() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getType() {
        return type;
    }

    void findByName(String name) {

    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

package com.example.demo.Entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Entity
@Data
@Table(name = "rsvp_users")
public class RsvpUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Date date;
    private Boolean attending;
    private String email;
    private String allergens;
    private Boolean deleted;

    public RsvpUser(){

    }

    public RsvpUser(String name, String surname, Date date, Boolean attending, String email, String allergens, Boolean deleted) {
        this.name = name;
        this.surname = surname;
        this.date = date;
        this.attending = attending;
        this.email = email;
        this.allergens = allergens;
        this.deleted = deleted;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAttending(Boolean attending) {
        this.attending = attending;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getDate() {
        return date;
    }

    public Boolean getAttending() {
        return attending;
    }

    public String getEmail() {
        return email;
    }

    public String getAllergens() {
        return allergens;
    }
}

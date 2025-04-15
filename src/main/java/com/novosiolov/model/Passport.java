package com.novosiolov.model;

import jakarta.persistence.*;

@Entity
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String number;

    @OneToOne(mappedBy = "passport")
    private Person person;

    public Passport() {}

    public Passport(String number) {
        this.number = number;
    }

    public int getId() { return id; }
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public Person getPerson() { return person; }
    public void setPerson(Person person) { this.person = person; }
}


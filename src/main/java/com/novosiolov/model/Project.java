package com.novosiolov.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "projects")
    private Set<Person> people = new HashSet<>();

    public Project() {}

    public Project(String name) {
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Set<Person> getPeople() { return people; }
    public void setPeople(Set<Person> people) { this.people = people; }
}


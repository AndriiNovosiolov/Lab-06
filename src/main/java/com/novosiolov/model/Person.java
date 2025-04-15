package com.novosiolov.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int age;

    @Column(name = "is_married")
    private boolean isMarried;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id")
    private Passport passport;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "person_project",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projects = new HashSet<>();

    public Person() {}

    public Person(String name, int age, boolean isMarried, Address address) {
        this.name = name;
        this.age = age;
        this.isMarried = isMarried;
        this.address = address;
    }

    // --- Getters and Setters ---

    public int getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public boolean isMarried() { return isMarried; }
    public void setMarried(boolean married) { isMarried = married; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public Passport getPassport() { return passport; }
    public void setPassport(Passport passport) { this.passport = passport; }

    public Set<Project> getProjects() { return projects; }
    public void setProjects(Set<Project> projects) { this.projects = projects; }
}


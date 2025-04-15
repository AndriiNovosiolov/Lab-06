package com.novosiolov.model;

import jakarta.persistence.*;

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

    public Person() {}

    public Person(String name, int age, boolean isMarried, Address address) {
        this.name = name;
        this.age = age;
        this.isMarried = isMarried;
        this.address = address;
    }

    public int getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public boolean isMarried() { return isMarried; }
    public void setMarried(boolean married) { isMarried = married; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
}


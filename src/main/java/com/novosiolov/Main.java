package com.novosiolov;

import com.novosiolov.model.*;
import com.novosiolov.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // Адреса (OneToMany)
        Address address = new Address("Odesa", "Odeska");
        session.persist(address);

        // Паспорт (OneToOne)
        Passport passport = new Passport("OD123456");

        // Проєкти (ManyToMany)
        Project project1 = new Project("Car Wash System");
        Project project2 = new Project("AI Chatbot");

        // Персона з усім
        Person person = new Person("Sofiia", 25, false, address);
        person.setPassport(passport);
        person.getProjects().add(project1);
        person.getProjects().add(project2);

        // Звʼязки ManyToMany двосторонні
        project1.getPeople().add(person);
        project2.getPeople().add(person);

        // Persist усіх
        session.persist(passport);
        session.persist(project1);
        session.persist(project2);
        session.persist(person);

        tx.commit();

        // READ
        List<Person> people = session.createQuery("FROM Person", Person.class).list();
        for (Person p : people) {
            System.out.println("👤 " + p.getName());
            System.out.println("📄 Passport: " + p.getPassport().getNumber());
            System.out.println("🏠 Address: " + p.getAddress().getCity());
            System.out.println("💼 Projects:");
            p.getProjects().forEach(project -> System.out.println(" - " + project.getName()));
        }

        // DELETE (optional)
        /*
        tx = session.beginTransaction();
        session.remove(person);
        tx.commit();
        */

        session.close();
        HibernateUtil.getSessionFactory().close();
    }
}

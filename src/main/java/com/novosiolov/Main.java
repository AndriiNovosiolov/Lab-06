package com.novosiolov;

import com.novosiolov.model.Address;
import com.novosiolov.model.Person;
import com.novosiolov.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        Address address = new Address("Kyiv", "Kyivska");
        session.persist(address);

        Person person = new Person("Andrii", 22, true, address);
        session.persist(person);

        tx.commit();

        List<Person> people = session.createQuery("FROM Person", Person.class).list();
        System.out.println("\n--- All People ---");
        people.forEach(p -> System.out.println(p.getId() + ": " + p.getName() + " — " + p.getAddress().getCity()));

        tx = session.beginTransaction();
        person.setName("Andrii Novosiolov");
        session.merge(person);
        tx.commit();

        tx = session.beginTransaction();
        session.remove(person);
        tx.commit();

        session.close();
        HibernateUtil.getSessionFactory().close();
    }
}

package com.airline.dao;

import com.airline.model.Flight;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class FlightDAO {
    private EntityManager em;

    public FlightDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Flight flight) {
        em.getTransaction().begin();
        em.persist(flight);
        em.getTransaction().commit();
    }

    public  Flight find(int id) {
        return this.em.find(Flight.class, id);
    }

    public List<Flight> search(String origin, String destination, LocalDate flightDate) {
        return em.createQuery(
                        "SELECT f FROM Flight f WHERE f.origin = :o AND f.destination = :d AND f.flightDate  = :dt",
                        Flight.class
                ).setParameter("o", origin)
                .setParameter("d", destination)
                .setParameter("dt", flightDate)
                .getResultList();
    }
}

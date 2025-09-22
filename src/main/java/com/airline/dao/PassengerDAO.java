package com.airline.dao;

import com.airline.model.Booking;
import com.airline.model.Passenger;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PassengerDAO {
    private EntityManager em;

    public PassengerDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Passenger passenger) {
        em.getTransaction().begin();
        em.persist(passenger);
        em.getTransaction().commit();
    }

    public Passenger find(int id) {
        return this.em.find(Passenger.class, id);
    }

    public void getBookings(int passengerId) {
        List<Booking> bookings = em.createQuery(
                        "SELECT b FROM Booking b WHERE b.passengerId = :pid", Booking.class)
                .setParameter("pid", passengerId)
                .getResultList();

        if (bookings.isEmpty()) {
            System.out.println("No bookings found for passenger " + passengerId);
        } else {
            for (Booking b : bookings) {
                System.out.println("Booking ID: " + b.getId() +
                        ", Flight ID: " + b.getFlightId() +
                        ", Seat ID: " + b.getSeatNumber());
            }
        }
    }
}

package com.airline.dao;

import com.airline.model.Booking;
import jakarta.persistence.EntityManager;

public class BookingDAO {
    private EntityManager em;

    public BookingDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Booking booking) {
        em.getTransaction().begin();
        em.persist(booking);
        em.getTransaction().commit();
    }

    public void delete(int bookingID) {
        Booking booking = find(bookingID);
        em.remove(booking);
    }

    public  Booking find(int id) {
        return this.em.find(Booking.class, id);
    }
}

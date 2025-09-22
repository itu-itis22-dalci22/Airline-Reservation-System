package com.airline.service;

import com.airline.dao.FlightDAO;
import com.airline.dao.BookingDAO;
import com.airline.model.Booking;
import com.airline.model.Flight;
import com.airline.model.Passenger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class BookingService {
    private FlightDAO flightDAO;
    private BookingDAO bookingDAO;
    private EntityManager em;

    public BookingService(EntityManager em) {
        this.em = em;
        this.bookingDAO = new BookingDAO(em);
        this.flightDAO = new FlightDAO(em);
    }

    public void bookFlight(Passenger passenger, Flight flight, int seatNumber) {
        if (!flight.hasSeatsLeft()){
            throw new IllegalStateException("No Seats Available");
        }

        flight.reduceAvailableSeats();

        Booking booking = new Booking(seatNumber, passenger.getId(), flight.getId());

        flightDAO.save(flight);
        bookingDAO.save(booking);
    }

    public void cancelBooking(int bookingId) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            bookingDAO.delete(bookingId);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }



}

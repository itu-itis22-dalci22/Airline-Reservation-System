package com.airline.service;

import com.airline.dao.PassengerDAO;
import com.airline.model.Booking;
import com.airline.model.Passenger;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PassengerService {
    private PassengerDAO passengerDAO;
    private EntityManager em;

    public PassengerService(EntityManager em) {
        this.em = em;
        this.passengerDAO = new PassengerDAO(em);
    }

    public void registerPassenger(Passenger p) {
        passengerDAO.save(p);
    }

    public Passenger findPassengerById(int id) {
        return passengerDAO.find(id);
    }

    public void showBookings(int passengerId) {
        passengerDAO.getBookings(passengerId);
    }
}

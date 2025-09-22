package com.airline.service;

import com.airline.dao.FlightDAO;
import com.airline.model.Flight;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class FlightService {
    private FlightDAO flightDAO;
    private EntityManager em;

    public FlightService(EntityManager em) {
        this.em = em;
        this.flightDAO = new FlightDAO(em);
    }
    public void addFlight(Flight flight){
        flightDAO.save(flight);
    }

    public List<Flight> searchFlights(String origin, String destination, LocalDate flightDate) {
        return flightDAO.search(origin, destination, flightDate);
    }

    public Flight getFlightById(int Id) {
        return flightDAO.find(Id);
    }

}

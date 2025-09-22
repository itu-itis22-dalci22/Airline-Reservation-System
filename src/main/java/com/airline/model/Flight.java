package com.airline.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Integer id;

    private String origin;
    private String destination;

    @Column(name = "flight_date")
    private LocalDate flightDate;

    @Column(name = "total_seats")
    private int totalSeats;

    @Column(name = "available_seats")
    private int availableSeats;

    // 🔹 REQUIRED by JPA
    public Flight() {}

    // Business constructor
    public Flight(String origin, String destination, LocalDate flightDate, int totalSeats) {
        this.origin = origin;
        this.destination = destination;
        this.flightDate = flightDate;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;  // all seats available initially
    }

    public Integer getId() {
        return id;
    }

    public String getFlightInfo() {
        return String.format(
                "FlightId: %d | %s → %s | Date: %s | Seats: %d/%d",
                id, origin, destination, flightDate, availableSeats, totalSeats
        );
    }

    public void reduceAvailableSeats() {
        if (availableSeats > 0) {
            availableSeats--;
        }
    }

    public boolean hasSeatsLeft() {
        return availableSeats > 0;
    }
}

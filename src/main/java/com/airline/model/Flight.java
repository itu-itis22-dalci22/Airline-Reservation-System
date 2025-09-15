package com.airline.model;

import java.time.LocalDate;
import java.util.HashSet;

public class Flight {
    private static int counter = 0;
    private Integer id = 1;
    private String origin, destination;
    private LocalDate dateTime;
    private int totalSeats;
    private HashSet<Integer> availableSeats;

    public Flight(String origin, String destination, LocalDate dateTime, int totalSeats){
        counter++;
        this.id = counter;
        this.origin = origin;
        this.destination = destination;
        this.dateTime = dateTime;
        this.totalSeats = totalSeats;
        this.availableSeats = new HashSet<>();
    }

    public Integer getId(){
        return id;
    }
    public String getFlightInfo(){
        return "FlightId: " + id + " origin: " + origin + " destination " + destination  + " dateTime: " + dateTime  + " totalSeats: " + totalSeats ;
    }
    public void reduceAvailableSeats(Integer seatNumber){
        availableSeats.remove(seatNumber);
    }
    public void increaseAvailableSeats(int seatNumber){
        availableSeats.add(seatNumber);
    }
    public boolean hasSeatsLeft(){
        return false;
    }
}

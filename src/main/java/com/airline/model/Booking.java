package com.airline.model;

public class Booking {
    private static int counter = 0;
    private int id = 1;
    private int seatNumber;
    private Passenger passenger;
    private Flight flight;

    public Booking(int seatNumber, Passenger passenger, Flight flight){
        counter++;
        this.id = counter;
        this.seatNumber = seatNumber;
        this.passenger = passenger;
        this.flight = flight;
    }

    public Integer getId(){
        return id;
    }
    public Integer getPassengerId(){
        return passenger.getId();
    }
    public Integer getFlightId(){
        return flight.getId();
    }

    public String getBookingInfo(){
        return "booking ıd -> " + id + " seatNumber " + seatNumber + passenger.getInfoPassenger() + flight.getFlightInfo();
    }
}

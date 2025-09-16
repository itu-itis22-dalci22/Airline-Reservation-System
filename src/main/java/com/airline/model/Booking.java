package com.airline.model;

public class Booking {
    private static int counter = 0;
    private int id = 1;
    private int seatNumber;
    private int passengerId;
    private int flightId;

    public Booking(int seatNumber, Integer passengerID, Integer flightID){
        counter++;
        this.id = counter;
        this.seatNumber = seatNumber;
        this.passengerId = passengerID;
        this.flightId = flightID;
    }

    public Integer getId(){
        return id;
    }
//    public Integer getPassengerId(){
//        return passenger.getId();
//    }

//    public String getBookingInfo(){
//        return "booking ıd -> " + id + " seatNumber " + seatNumber + passenger.getInfoPassenger() + flight.getFlightInfo();
//    }
}

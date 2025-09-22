package com.airline.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int id = 1;

    @Column(name = "seat_number")
    private int seatNumber;
    @Column(name = "passenger_id")
    private int passengerId;
    @Column(name = "flight_id")
    private int flightId;

    public Booking () {}

    public Booking(int seatNumber, Integer passengerID, Integer flightID){
        this.seatNumber = seatNumber;
        this.passengerId = passengerID;
        this.flightId = flightID;
    }

    public Integer getId(){
        return id;
    }

    public int getFlightId() {
        return flightId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }


//    public Integer getPassengerId(){
//        return passenger.getId();
//    }

//    public String getBookingInfo(){
//        return "booking ıd -> " + id + " seatNumber " + seatNumber + passenger.getInfoPassenger() + flight.getFlightInfo();
//    }
}

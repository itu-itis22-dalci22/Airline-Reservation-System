package com.airline.model;

import com.airline.service.Operations;

import java.time.LocalDate;
import java.util.List;

public class Passenger {
    static int counter = 0;
    private int id;
    private String name;
    private String email;

    public Passenger(String name, String email){
        counter ++;
        this.id = counter;
        this.name = name;
        this.email = email;
    }

    public Integer getId(){
        return id;
    }
    public String getInfoPassenger(){
        return " name : " + name + " email: " + email;
    }
    public void bookFlightFromService(Integer flightId, Integer seatNumber){
        Operations.bookFlight(this, flightId, seatNumber);
    }
    public void cancelBooking(Integer bookingID){
        Operations.cancelBooking(bookingID, id);
    }
    public void searchFlights(String origin, String destination, LocalDate dateTime){
        List<Flight> flights = Operations.searchFlights(origin,destination,dateTime);
        if(flights == null){
            System.out.println("There is no flight in this date. ");
            return;
        }
        for(Flight flight:Operations.searchFlights(origin,destination,dateTime)) System.out.println(flight.getFlightInfo());
    }
    public void viewBookings(){
        List<Booking> passengerBookings = Operations.viewBookings(id);
        for(Booking booking:passengerBookings){
            System.out.println(booking.getBookingInfo());
        }
    }
}

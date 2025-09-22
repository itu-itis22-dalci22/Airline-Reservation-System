package com.airline.model;

import com.airline.service.Operations;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Passengers")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private int id;
    private String name;
    private String email;

    public Passenger() {}

    public Passenger(String name, String email){
        this.name = name;
        this.email = email;
    }

    public String getInfoPassenger(){
        return " name : " + name + " email: " + email;
    }

    public int getId() {
        return id;
    }

//    public void bookFlightFromService(Integer flightId, Integer seatNumber){
//        Operations.bookFlight(this, flightId, seatNumber);
//    }
//    public void cancelBooking(Integer bookingID){
//        Operations.cancelBooking(bookingID, id);
//    }
//    public void searchFlights(String origin, String destination, LocalDate dateTime){
//        List<Flight> flights = Operations.searchFlights(origin,destination,dateTime);
//        if(flights == null){
//            System.out.println("There is no flight in this date. ");
//            return;
//        }
//        for(Flight flight:Operations.searchFlights(origin,destination,dateTime)) System.out.println(flight.getFlightInfo());
//    }
//    public void viewBookings(){
//        List<Booking> passengerBookings = Operations.viewBookings(id);
//        for(Booking booking:passengerBookings){
//            System.out.println(booking.getBookingInfo());
//        }
//    }
}

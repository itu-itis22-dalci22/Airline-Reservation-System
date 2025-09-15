package com.airline.service;

import com.airline.model.Booking;
import com.airline.model.Flight;
import com.airline.model.Passenger;
import com.airline.util.FlightKey;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Operations {
    public static HashMap<FlightKey, List<Flight>> flightKeyFlightHashMap = new HashMap<FlightKey,List<Flight>>();
    public static HashMap<Integer, Booking> bookingIdBookingHashMap = new HashMap<Integer, Booking>();
    public static HashMap<Integer, Set<Integer>> passengerIdBookingIdsHashmap = new HashMap<Integer, Set<Integer>>();
    public static HashMap<Integer, Set<Integer>> flightIdBookingIdsHashmap = new HashMap<Integer, Set<Integer>>();
    public static HashMap<Integer, Flight> flightIdFlightHashmap = new HashMap<Integer, Flight>();
    public static HashMap<Integer, Passenger> passengerIdPassengerHashmap = new HashMap<Integer,Passenger>();

    public static List<Flight> searchFlights(String origin, String destination, LocalDate dateTime){
        FlightKey flightKey = new FlightKey(origin, destination, dateTime);
        return flightKeyFlightHashMap.get(flightKey);
    }
    public static Booking bookFlight(Passenger passenger, Integer flightID, Integer seatNum){
        Flight flight = flightIdFlightHashmap.get(flightID);
        Booking booking = new Booking(seatNum, passenger, flight);
        bookingIdBookingHashMap.put(booking.getId(), booking);
        passengerIdBookingIdsHashmap
                .computeIfAbsent(passenger.getId(), k -> new HashSet<>())
                .add(booking.getId());
        return booking;
    }
    public static void cancelBooking(Integer bookingId, Integer passengerId) {
        bookingIdBookingHashMap.remove(bookingId);
        Set<Integer> passengerBookings = passengerIdBookingIdsHashmap.get(passengerId);
        if (passengerBookings != null) {
            passengerBookings.remove(bookingId);

            // if the passenger has no more bookings, you can clean up
            if (passengerBookings.isEmpty()) {
                passengerIdBookingIdsHashmap.remove(passengerId);
            }
        }
    }
    public static List<Booking> viewBookings(Integer passengerId){
        return passengerIdBookingIdsHashmap
                .getOrDefault(passengerId, Set.of())      // fallback if no set
                .stream()
                .map(bookingIdBookingHashMap::get)       // map id → booking
                .filter(Objects::nonNull)                // skip nulls
                .collect(Collectors.toList());
    }

    public static HashMap<FlightKey, List<Flight>> getFlightKeyFlightHashMap() {
        return flightKeyFlightHashMap;
    }

}

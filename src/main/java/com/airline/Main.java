package com.airline;


// hashmap <Flightkey, Flight>
// hashmap <bookingID, Booking>
// hashmap <passengerID, List<bookingID>>
// hashmap <flightID, List<bookingID>>

import com.airline.model.Flight;
import com.airline.model.Passenger;
import com.airline.service.Operations;
import com.airline.util.FlightKey;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        HashMap<FlightKey, List<Flight>> flightKeyFlightHashMap = new HashMap<FlightKey,List<Flight>>();

        LocalDate dt1 = LocalDate.parse("2025-03-26");
        Flight firstFlight = new Flight("TR", "GER", dt1, 100);
        FlightKey firstFlightKey = new FlightKey("TR", "GER", dt1);

        LocalDate dt2 = LocalDate.parse("2025-03-24");
        Flight secondFlight = new Flight("TR", "ITA", dt2, 120);
        FlightKey secondFlightKey = new FlightKey("TR", "ITA", dt2);

        LocalDate dt3 = LocalDate.parse("2025-03-25");
        Flight thirdFlight = new Flight("TR", "USA", dt3, 150);
        FlightKey thirdFlightKey = new FlightKey("TR", "USA", dt3);

        LocalDate dt4 = LocalDate.parse("2025-03-25");
        Flight fourthFlight = new Flight("TR", "USA", dt4, 130);
        FlightKey fourthFlightKey = new FlightKey("TR", "USA", dt4);

        flightKeyFlightHashMap.computeIfAbsent(firstFlightKey, k -> new ArrayList<>()).add(firstFlight);
        flightKeyFlightHashMap.computeIfAbsent(secondFlightKey, k -> new ArrayList<>()).add(secondFlight);
        flightKeyFlightHashMap.computeIfAbsent(thirdFlightKey, k -> new ArrayList<>()).add(thirdFlight);
        flightKeyFlightHashMap.computeIfAbsent(fourthFlightKey, k -> new ArrayList<>()).add(fourthFlight);

        Operations.flightKeyFlightHashMap = flightKeyFlightHashMap;
        Operations.flightIdFlightHashmap.put(firstFlight.getId(), firstFlight);
        Operations.flightIdFlightHashmap.put(secondFlight.getId(), secondFlight);
        Operations.flightIdFlightHashmap.put(thirdFlight.getId(), thirdFlight);
        Operations.flightIdFlightHashmap.put(fourthFlight.getId(), fourthFlight);

        Passenger firstPassenger = new Passenger("Emre", "edalci@thy.com");
        firstPassenger.searchFlights("TR","ITA",dt2);

        Scanner userObj = new Scanner(System.in);
        while (true){
            System.out.println("Options:");
            System.out.println("1-Search a Flight");
            System.out.println("2-Book a Flight");
            System.out.println("3-Cancel a Flight");
            System.out.println("4-View my Flights");

            String answer = userObj.nextLine();

            switch (answer){
                case "1":
                    System.out.print("Enter origin: ");
                    String origin = userObj.nextLine().strip();

                    System.out.print("Enter destination: ");
                    String destination = userObj.nextLine().strip();

                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String dateInput = userObj.nextLine().strip();
                    LocalDate flightDate = LocalDate.parse(dateInput);

                    firstPassenger.searchFlights(origin, destination, flightDate);
                    break;
                case "2":
                    System.out.print("Enter Flight Id");
                    Integer flightId = userObj.nextInt();

                    System.out.print("Enter Seat Number");
                    Integer seatNum = userObj.nextInt();

                    firstPassenger.bookFlightFromService(flightId, seatNum);
                    break;
                case "3":
                    System.out.print("Enter Booking Id");
                    Integer bookingId = userObj.nextInt();

                    firstPassenger.cancelBooking(bookingId);
                case "4":
                    firstPassenger.viewBookings();
                    break;
            }
        }
    }

}

package com.airline;

import com.airline.model.Flight;
import com.airline.model.Passenger;
import com.airline.service.BookingService;
import com.airline.service.FlightService;
import com.airline.service.PassengerService;
import jakarta.persistence.EntityManager;
import com.airline.util.jpaUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        EntityManager em = jpaUtil.getEntityManager();

        FlightService flightService = new FlightService(em);
        PassengerService passengerService = new PassengerService(em);
        BookingService bookingService = new BookingService(em);

        // Hardcoded first passenger (simulate login)
        Passenger currentPassenger = new Passenger("CagriDssalscı", "dalaci@s2itu.com");
        passengerService.registerPassenger(currentPassenger);

        Scanner userObj = new Scanner(System.in);
        while (true) {
            System.out.println("Options:");
            System.out.println("1-Search a Flight");
            System.out.println("2-Book a Flight");
            System.out.println("3-Cancel a Flight");
            System.out.println("4-View my Flights");
            System.out.println("5-Exit");

            String answer = userObj.nextLine();

            switch (answer) {
                case "1":
                    System.out.print("Enter origin: ");
                    String origin = userObj.nextLine().strip();

                    System.out.print("Enter destination: ");
                    String destination = userObj.nextLine().strip();

                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String dateInput = userObj.nextLine().strip();
                    LocalDate flightDate = LocalDate.parse(dateInput);

                    List<Flight> searchedFlights = flightService.searchFlights(origin, destination, flightDate);
                    if (searchedFlights.isEmpty()) {
                        System.out.println("No flights found.");
                    } else {
                        for (Flight flight : searchedFlights) {
                            System.out.println(flight.getFlightInfo());
                        }
                    }
                    break;

                case "2":
                    System.out.print("Enter Flight Id: ");
                    int flightId = Integer.parseInt(userObj.nextLine());

                    System.out.print("Enter Seat Number: ");
                    int seatNum = Integer.parseInt(userObj.nextLine());

                    try {
                        bookingService.bookFlight(currentPassenger, flightService.getFlightById(flightId), seatNum);
                        System.out.println("Booking successful!");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "3":
                    System.out.print("Enter Booking Id: ");
                    int bookingId = Integer.parseInt(userObj.nextLine());

                    bookingService.cancelBooking(bookingId);
                    System.out.println("Booking cancelled.");
                    break;

                case "4":
                    passengerService.showBookings(currentPassenger.getId());
                    break;

                case "5":
                    System.out.println("Goodbye!");
                    em.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}

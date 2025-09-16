package com.airline;
import com.airline.model.Flight;
import com.airline.service.Operations;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/airline_db";
        String user = "root";
        String password = "2011";

        try (Connection conn = DriverManager.getConnection(url,user,password)){
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

                        List<Flight> searchedFlights = Operations.searchFlights(conn, origin, destination, flightDate);
                        for(Flight flight:searchedFlights){
                            System.out.println(flight.getFlightInfo());
                        }
                        break;
                    case "2":
                        System.out.print("Enter Flight Id");
                        Integer flightId = userObj.nextInt();

                        System.out.print("Enter Seat Number");
                        Integer seatNum = userObj.nextInt();

                        Operations.bookFlight(conn,1,flightId,seatNum);
                        break;
                    case "3":
                        System.out.print("Enter Booking Id");
                        Integer bookingId = userObj.nextInt();

                        Operations.cancelBooking(conn,bookingId);
                        break;
                    case "4":
                        Operations.viewBookings(conn,1);
                        break;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }



    }

}

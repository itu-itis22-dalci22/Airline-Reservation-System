package com.airline.service;

import com.airline.model.Booking;
import com.airline.model.Flight;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;


//TODO Biraz daha gerçekçi bir business logic kurulabilir.
// Mesela bir yolcu aynı flight birden fazla almasın
// Available Seats sayısı 0 altına düşerse bilet book edilmesin :)
public class Operations {
    public static List<Flight> searchFlights(Connection conn, String origin, String destination, LocalDate dateTime) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Flights WHERE origin = ? AND destination = ? AND flight_date = ?")) {
            stmt.setString(1 , origin);
            stmt.setString(2, destination);
            stmt.setDate(3, java.sql.Date.valueOf(dateTime));
            try (ResultSet resultSet = stmt.executeQuery()) {
                List<Flight> searchFlights = new ArrayList<>();
                while (resultSet.next()) {
                    Flight flight = new Flight(origin,destination,dateTime,resultSet.getInt("total_seats"));
                    searchFlights.add(flight);
                }
                return searchFlights;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return List.of();
    }

    public static void bookFlight(Connection conn, Integer passengerId, Integer flightId, Integer seatNum){
        String insertBookingSQL = "INSERT INTO Bookings (passenger_id, flight_id, seat_number) VALUES (?, ?, ?)";
        String updateBookingSQL = "UPDATE Flights SET available_seats = available_seats - 1 WHERE flight_id = ?";
        Booking booking;

        try{
            conn.setAutoCommit(false);

            try( PreparedStatement insertStmt = conn.prepareStatement(insertBookingSQL);
                 PreparedStatement updateStmt = conn.prepareStatement(updateBookingSQL)) {

                // Insert booking
                insertStmt.setInt(1, passengerId);
                insertStmt.setInt(2, flightId);
                insertStmt.setInt(3, seatNum);
                insertStmt.executeUpdate();

                // Reduce available seats
                updateStmt.setInt(1, flightId);
                updateStmt.executeUpdate();

                conn.commit();
                System.out.println("Booking successful!");
            } catch (SQLException e) {
                conn.rollback(); // rollback if any step fails
                System.out.println("Booking failed, rolled back. Error: " + e.getMessage());
            } finally {
                conn.setAutoCommit(true); // restore default behavior
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void viewBookings(Connection conn, Integer passengerId){
        String sql = "SELECT b.booking_id, b.seat_number, f.flight_id, f.origin, f.destination, f.flight_date " +
                "FROM Bookings b " +
                "JOIN Flights f ON b.flight_id = f.flight_id " +
                "WHERE b.passenger_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, passengerId);

            //execute query
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()){
                    int bookingID = resultSet.getInt("booking_id");
                    int seatNumber = resultSet.getInt("seat_number");
                    int flightId = resultSet.getInt("flight_id");
                    String origin = resultSet.getString("origin");
                    String destination = resultSet.getString("destination");
                    Date flightDate = resultSet.getDate("flight_date");

                    System.out.printf("Booking %d: Seat %d on flight %d (%s -> %s) at %s%n",
                            bookingID, seatNumber, flightId, origin, destination, flightDate.toString());

                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void cancelBooking(Connection conn, Integer bookingId) {
        String sql = "DELETE FROM Bookings WHERE booking_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookingId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Booking " + bookingId + " deleted successfully.");
            } else {
                System.out.println("No booking found with ID " + bookingId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

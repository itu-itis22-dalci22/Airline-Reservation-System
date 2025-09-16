package com.airline;

import com.airline.model.Booking;
import com.airline.service.Operations;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OperationTest {
    private static Connection conn;

    @BeforeAll
    static void setup() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline_db", "root", "2011");
    }

    @Test
    void testBookFlight() throws SQLException {
        Operations.bookFlight(conn, 1,2,13);

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Bookings WHERE booking_id=?");
        stmt.setInt(1,1);
        ResultSet resultSet = stmt.executeQuery();

        assertTrue(resultSet.next());
        assertEquals(13, resultSet.getInt("seat_number"));

    }

    @AfterAll
    static void teardown() throws SQLException {
        conn.close();
    }
}

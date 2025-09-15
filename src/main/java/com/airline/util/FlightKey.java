package com.airline.util;

import java.time.LocalDate;
import java.util.Objects;

public class FlightKey {
    public String origin;
    public String destination;
    public LocalDate date;

    public FlightKey(String origin, String destination, LocalDate date){
        this.origin = origin;
        this.destination = destination;
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // same object
        if (obj == null || getClass() != obj.getClass()) return false; // null or different types
        FlightKey that = (FlightKey) obj;
        return Objects.equals(origin, that.origin) &&
                Objects.equals(destination, that.destination) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, destination, date);
    }
}
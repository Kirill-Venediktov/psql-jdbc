package ru.kirillvenediktov.psql.models;

import java.util.Objects;

public class Seat {

    private String aircraftCode;

    private String seatNumber;

    private String fareCondition;

    public Seat() {
    }

    public Seat(String aircraftCode, String seatNumber, String fareCondition) {
        this.aircraftCode = aircraftCode;
        this.seatNumber = seatNumber;
        this.fareCondition = fareCondition;
    }

    public String getAircraftCode() {
        return aircraftCode;
    }

    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode = aircraftCode;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getFareCondition() {
        return fareCondition;
    }

    public void setFareCondition(String fareCondition) {
        this.fareCondition = fareCondition;
    }

    @Override
    public String toString() {
        return "Seat{" +
            "aircraftCode='" + aircraftCode + '\'' +
            ", seatNumber='" + seatNumber + '\'' +
            ", farecondition='" + fareCondition + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return Objects.equals(aircraftCode, seat.aircraftCode) &&
            Objects.equals(seatNumber, seat.seatNumber) &&
            Objects.equals(fareCondition, seat.fareCondition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aircraftCode, seatNumber, fareCondition);
    }

}

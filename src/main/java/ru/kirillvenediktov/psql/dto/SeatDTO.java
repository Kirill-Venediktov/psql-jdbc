package ru.kirillvenediktov.psql.dto;

import java.util.Objects;

public class SeatDTO {

    private String aircraftCode;

    private String seatNumber;

    private String fareCondition;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatDTO dto = (SeatDTO) o;
        return Objects.equals(aircraftCode, dto.aircraftCode) &&
            Objects.equals(seatNumber, dto.seatNumber) &&
            Objects.equals(fareCondition, dto.fareCondition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aircraftCode, seatNumber, fareCondition);
    }

    @Override
    public String toString() {
        return "SeatDTO{" +
            "aircraftCode='" + aircraftCode + '\'' +
            ", seatNumber='" + seatNumber + '\'' +
            ", fareCondition='" + fareCondition + '\'' +
            '}';
    }

}

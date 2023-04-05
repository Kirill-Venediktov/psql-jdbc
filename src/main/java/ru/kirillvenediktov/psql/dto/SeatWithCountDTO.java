package ru.kirillvenediktov.psql.dto;

public class SeatWithCountDTO {

    private String aircraftCode;

    private String fareCondition;

    private int count;

    public String getAircraftCode() {
        return aircraftCode;
    }

    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode = aircraftCode;
    }

    public String getFareCondition() {
        return fareCondition;
    }

    public void setFareCondition(String fareCondition) {
        this.fareCondition = fareCondition;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}

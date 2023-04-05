package ru.kirillvenediktov.psql.dto;

import java.util.Objects;

public class AircraftDTO {

    private String aircraftCode;

    private String model;

    private int range;

    public String getAircraftCode() {
        return aircraftCode;
    }

    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode = aircraftCode;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AircraftDTO that = (AircraftDTO) o;
        return range == that.range &&
            Objects.equals(aircraftCode, that.aircraftCode) &&
            Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aircraftCode, model, range);
    }

    @Override
    public String toString() {
        return "AircraftDTO{" +
            "aircraftCode='" + aircraftCode + '\'' +
            ", model='" + model + '\'' +
            ", range=" + range +
            '}';
    }

}

package ru.kirillvenediktov.psql.models;

import java.util.Objects;

public class Aircraft {

    private String aircraftCode;

    private String model;

    private int range;

    public Aircraft() {
    }

    public Aircraft(String aircraftCode, String model, int range) {
        this.aircraftCode = aircraftCode;
        this.model = model;
        this.range = range;
    }

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
    public String toString() {
        return "Aircraft{" +
            "aircraftCode='" + aircraftCode + '\'' +
            ", model='" + model + '\'' +
            ", range=" + range +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aircraft aircraft = (Aircraft) o;
        return range == aircraft.range &&
            Objects.equals(aircraftCode, aircraft.aircraftCode) &&
            Objects.equals(model, aircraft.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aircraftCode, model, range);
    }

}

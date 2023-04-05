package ru.kirillvenediktov.psql.dao;

import ru.kirillvenediktov.psql.dto.AircraftDTO;
import ru.kirillvenediktov.psql.dto.SeatDTO;
import ru.kirillvenediktov.psql.dto.SeatWithCountDTO;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRequestManager {

    private ConnectionManager connectionManager = new ConnectionManager();

    public int executeUpdate(String query) throws FileNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            result = statement.executeUpdate(query);
        }
        return result;
    }

    private void showAircraftResultSet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            String aircraftCode = rs.getString("aircraft_code");
            String model = rs.getString("model");
            int range = rs.getInt("range");
            System.out.println("code = " + aircraftCode + ", model = " + model + ", range = " + range);
        }
    }

    private AircraftDTO createAircraftDTO(ResultSet rs) throws SQLException {
        AircraftDTO aircraft = new AircraftDTO();
        aircraft.setAircraftCode(rs.getString("aircraft_code"));
        aircraft.setModel(rs.getString("model"));
        aircraft.setRange(rs.getInt("range"));
        return aircraft;

    }

    private SeatDTO createSeatDTO(ResultSet rs) throws SQLException {
        SeatDTO seat = new SeatDTO();
        seat.setAircraftCode(rs.getString("aircraft_code"));
        seat.setFareCondition(rs.getString("fare_conditions"));
        seat.setSeatNumber(rs.getString("seat_no"));
        return seat;

    }

    public List<AircraftDTO> getAircrafts() throws FileNotFoundException, SQLException {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM jdbc_planes.aircrafts");
            List<AircraftDTO> aircrafts = new ArrayList<>();
            while (rs.next()) {
                AircraftDTO aircraft = createAircraftDTO(rs);
                aircrafts.add(aircraft);
            }
            return aircrafts;
        }
    }

    public List<SeatDTO> getSeats() throws FileNotFoundException, SQLException {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM jdbc_planes.seats");
            List<SeatDTO> seats = new ArrayList<>();
            while (rs.next()) {
                SeatDTO seat = createSeatDTO(rs);
                seats.add(seat);
            }
            return seats;
        }
    }

    public SeatDTO getSeat(String aircraftCode, String seatNumber) throws FileNotFoundException, SQLException {
        String query = "SELECT * FROM jdbc_planes.seats" +
            " WHERE  aircraft_code = ? AND seat_no = ?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, aircraftCode);
            statement.setString(2, seatNumber);
            SeatDTO seat = null;
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                seat = createSeatDTO(rs);
            }
            return seat;
        }
    }

    public AircraftDTO getAircraft(String code) throws FileNotFoundException, SQLException {
        String query = "SELECT model, aircraft_code, range FROM jdbc_planes.aircrafts" +
            " WHERE  aircraft_code = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, code);
            ResultSet rs = statement.executeQuery();
            AircraftDTO aircraft = null;
            if (rs.next()) {
                aircraft = createAircraftDTO(rs);
            }
            return aircraft;
        }
    }

    public int doubleFlightRange(String code) throws FileNotFoundException, SQLException {
        String query = "UPDATE jdbc_planes.aircrafts SET range = range*2" +
            " WHERE  aircraft_code = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, code);
            return statement.executeUpdate();
        }
    }

    public void showDescSortingAircrafts() throws FileNotFoundException, SQLException {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT model, aircraft_code, range FROM jdbc_planes.aircrafts " +
                "ORDER BY range DESC;");
            showAircraftResultSet(rs);
        }
    }


    public List<AircraftDTO> getAircraftsInRange(int from, int to) throws FileNotFoundException, SQLException {
        String query = "SELECT model, aircraft_code, range FROM jdbc_planes.aircrafts" +
            " WHERE  range >= ? AND range <= ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, from);
            statement.setInt(2, to);
            ResultSet rs = statement.executeQuery();
            List<AircraftDTO> aircrafts = new ArrayList<>();
            while (rs.next()) {
                AircraftDTO aircraft = createAircraftDTO(rs);
                aircrafts.add(aircraft);
            }
            return aircrafts;
        }
    }

    public void deleteAllAircrafts() throws FileNotFoundException, SQLException {
        executeUpdate("DELETE FROM jdbc_planes.aircrafts;");
    }

    public void deleteAircraftsInRange(int from, int to) throws SQLException, FileNotFoundException {
        String query = "DELETE FROM jdbc_planes.aircrafts WHERE range > ? OR range < ?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(2, from);
            statement.setInt(1, to);
            statement.executeUpdate();
        }
    }

    public List<SeatWithCountDTO> getSeatCount() throws FileNotFoundException, SQLException {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT aircraft_code, fare_conditions, count( * )" +
                " FROM jdbc_planes.seats " +
                "GROUP BY aircraft_code, fare_conditions " +
                "ORDER BY aircraft_code, fare_conditions;");
            List<SeatWithCountDTO> seatWithCountDTOS = new ArrayList<>();
            while (rs.next()) {
                SeatWithCountDTO dto = new SeatWithCountDTO();
                dto.setAircraftCode(rs.getString(1));
                dto.setFareCondition(rs.getString(2));
                dto.setCount(rs.getInt(3));
                seatWithCountDTOS.add(dto);
            }
            return seatWithCountDTOS;
        }
    }

}

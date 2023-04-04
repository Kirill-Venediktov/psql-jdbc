package ru.kirillvenediktov.psql.dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public void showAircrafts() throws FileNotFoundException, SQLException {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM jdbc_planes.aircrafts");
            showAircraftResultSet(rs);
        }
    }

    public void showSortingAircrafts() throws FileNotFoundException, SQLException {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT model, aircraft_code, range FROM jdbc_planes.aircrafts " +
                    "ORDER BY model");
            showAircraftResultSet(rs);
        }
    }

    public void showAircraftByCode(String code) throws FileNotFoundException, SQLException {
        String query = "SELECT model, aircraft_code, range FROM jdbc_planes.aircrafts" +
            " WHERE  aircraft_code = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, code);
            ResultSet rs = statement.executeQuery();
            showAircraftResultSet(rs);
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

    public void showAircraftsInRange(int from, int to) throws SQLException, FileNotFoundException {
        String query = "SELECT model, aircraft_code, range FROM jdbc_planes.aircrafts" +
            " WHERE  range >= ? AND range <= ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, from);
            statement.setInt(2, to);
            ResultSet rs = statement.executeQuery();
            showAircraftResultSet(rs);
        }
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

    public void showNumberOfSeats() throws SQLException, FileNotFoundException {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT aircraft_code, fare_conditions, count( * )" +
                " FROM jdbc_planes.seats " +
                "GROUP BY aircraft_code, fare_conditions " +
                "ORDER BY aircraft_code, fare_conditions;");
            while (rs.next()) {
                String aircraftCode = rs.getString(1);
                String fareCondition = rs.getString(2);
                int count = rs.getInt(3);
                System.out.println(
                    "code = " + aircraftCode + " | fare condition = " + fareCondition + " | count = " + count);
            }
        }
    }

}

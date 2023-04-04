package ru.kirillvenediktov.psql.dao;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class TablesFiller {

    private static UserRequestManager userRequestManager = new UserRequestManager();

    public int createSchema() throws FileNotFoundException, SQLException {
        String query = "CREATE SCHEMA jdbc_planes\n" +
            "    AUTHORIZATION \"postgres\";";
        return userRequestManager.executeUpdate(query);
    }

    public int createAircraftsTable() throws FileNotFoundException, SQLException {
        String query = "CREATE TABLE jdbc_planes.aircrafts\n" +
            "( aircraft_code char( 3 ) NOT NULL,\n" +
            "  model text NOT NULL,\n" +
            "  range integer NOT NULL,\n" +
            "  CHECK ( range > 0),\n" +
            "  PRIMARY KEY ( aircraft_code )\n" +
            ");";
        return userRequestManager.executeUpdate(query);
    }

    public int createSeatsTable() throws FileNotFoundException, SQLException {
        String query = "\n" +
            "CREATE TABLE jdbc_planes.seats\n" +
            "(\n" +
            "\taircraft_code\tchar( 3 )\tNOT NULL,\n" +
            "\tseat_no\t\tvarchar( 4 )\tNOT NULL,\n" +
            "\tfare_conditions\tvarchar( 10 )\tNOT NULL,\n" +
            "\tCHECK\n" +
            "\t( fare_conditions IN ( 'Economy', 'Comfort', 'Business' )\n" +
            "\t),\n" +
            "\tPRIMARY KEY ( aircraft_code, seat_no ),\n" +
            "\tFOREIGN KEY ( aircraft_code )\n" +
            "\t\tREFERENCES jdbc_planes.aircrafts ( aircraft_code )\n" +
            "\t\tON DELETE CASCADE\n" +
            ");";
        return userRequestManager.executeUpdate(query);
    }

    public int fillAircraftsTable() throws FileNotFoundException, SQLException {
        String query = "INSERT INTO jdbc_planes.aircrafts ( aircraft_code, model, range ) \n" +
            "  VALUES ( '773', 'Boeing 777-300', 11100 ),\n" +
            "\t ( '763', 'Boeing 767-300', 7900 ),\n" +
            "\t ( '733', 'Boeing 737-300', 4200 ),\n" +
            "\t ( '320', 'Airbus A320-200', 5700 ),\n" +
            "\t ( '321', 'Airbus A321-200', 5600 ),\n" +
            "\t ( '319', 'Airbus A319-100', 6700 ),\n" +
            "\t ( 'CN1', 'Cessna 208 Caravan', 1200 ),\n" +
            "\t ( 'CR2', 'Bombardier CRJ-200', 2700 );";
        return userRequestManager.executeUpdate(query);
    }

    public int fillSeatsTable() throws FileNotFoundException, SQLException {
        String query = "INSERT INTO jdbc_planes.seats VALUES " +
            " ( 'SU9', '1A', 'Business' ), " +
            " ( 'SU9', '1B', 'Business' ), " +
            " ( 'SU9', '10A', 'Economy' ), " +
            " ( 'SU9', '10B', 'Economy' ), " +
            " ( 'SU9', '10F', 'Economy' ), " +
            " ( 'SU9', '20F', 'Economy' ), " +
            " ( '773', '2A', 'Business' ), " +
            " ( '773', '2B', 'Business' ), " +
            " ( '773', '11A', 'Economy' ), " +
            " ( '773', '12B', 'Economy' ), " +
            " ( '773', '11F', 'Economy' ), " +
            " ( '773', '22F', 'Economy' ), " +
            " ( '763', '3A', 'Business' ), " +
            " ( '763', '1C', 'Business' ), " +
            " ( '763', '12A', 'Economy' ), " +
            " ( '763', '13B', 'Economy' ), " +
            " ( '763', '12F', 'Economy' ), " +
            " ( '763', '22F', 'Economy' )," +
            " ( '733', '3B', 'Business' ), " +
            " ( '733', '1C', 'Business' ), " +
            " ( '733', '13A', 'Economy' ), " +
            " ( '733', '12C', 'Economy' ), " +
            " ( '733', '12D', 'Economy' ), " +
            " ( '733', '21E', 'Economy' ), " +
            " ( '320', '3B', 'Business' ), " +
            " ( '320', '1C', 'Business' ), " +
            " ( '320', '12C', 'Economy' ), " +
            " ( '320', '11C', 'Economy' ), " +
            " ( '320', '13A', 'Economy' ), " +
            " ( '320', '19C', 'Economy' ), " +
            " ( '321', '3A', 'Business' ), " +
            " ( '321', '2B', 'Business' ), " +
            " ( '321', '9A', 'Economy' ), " +
            " ( '321', '11A', 'Economy' ), " +
            " ( '321', '13C', 'Economy' ), " +
            " ( '321', '10C', 'Economy' )," +
            " ( '319', '1C', 'Business' ), " +
            " ( '319', '4A', 'Business' ), " +
            " ( '319', '9B', 'Economy' ), " +
            " ( '319', '10D', 'Economy' ), " +
            " ( '319', '18A', 'Economy' ), " +
            " ( '319', '12C', 'Economy' )," +
            " ( 'CN1', '1A', 'Business' ), " +
            " ( 'CN1', '1B', 'Business' ), " +
            " ( 'CN1', '5A', 'Economy' ), " +
            " ( 'CN1', '5B', 'Economy' ), " +
            " ( 'CN1', '6A', 'Economy' ), " +
            " ( 'CN1', '6B', 'Economy' ), " +
            " ( 'CR2', '1A', 'Business' ), " +
            " ( 'CR2', '2A', 'Business' ), " +
            " ( 'CR2', '6B', 'Economy' ), " +
            " ( 'CR2', '7A', 'Economy' ), " +
            " ( 'CR2', '6A', 'Economy' ), " +
            " ( 'CR2', '8A', 'Economy' );";
        return userRequestManager.executeUpdate(query);
    }

}

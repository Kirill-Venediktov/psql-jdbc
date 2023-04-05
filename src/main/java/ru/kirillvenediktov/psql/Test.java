package ru.kirillvenediktov.psql;

import ru.kirillvenediktov.psql.dto.SeatWithCountDTO;
import ru.kirillvenediktov.psql.service.DBLogicManager;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class Test {

    static final DBLogicManager manager = new DBLogicManager();

    public static void main(String[] args) {
        try {
            System.out.println(manager.getAircrafts());
            System.out.println(manager.getAircraft("SU9"));
            List<SeatWithCountDTO> dtos = manager.getSeatCount();
            for (SeatWithCountDTO dto : dtos) {
                System.out.println(
                    "code = " + dto.getAircraftCode() +
                        " | fare condition = " + dto.getFareCondition() +
                        " | count = " + dto.getCount());
            }
            manager.doubleFlightRange("SU9");
            System.out.println(manager.getAircraft("SU9"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

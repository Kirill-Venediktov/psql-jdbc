package ru.kirillvenediktov.psql.service;

import ru.kirillvenediktov.psql.dao.UserRequestManager;
import ru.kirillvenediktov.psql.dto.AircraftDTO;
import ru.kirillvenediktov.psql.dto.SeatDTO;
import ru.kirillvenediktov.psql.dto.SeatWithCountDTO;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class DBLogicManager {

    private UserRequestManager requestManager = new UserRequestManager();

    public List<AircraftDTO> getAircrafts() throws FileNotFoundException, SQLException {
        return requestManager.getAircrafts();
    }

    public List<AircraftDTO> getAircraftsInRange(int from, int to) throws FileNotFoundException, SQLException {
        return requestManager.getAircraftsInRange(from, to);
    }

    public AircraftDTO getAircraft(String code) throws FileNotFoundException, SQLException {
        return requestManager.getAircraft(code);
    }

    public List<SeatDTO> getSeats() throws FileNotFoundException, SQLException {
        return requestManager.getSeats();
    }

    public SeatDTO getSeat(String aircraftCode, String seatNumber) throws FileNotFoundException, SQLException {
        return requestManager.getSeat(aircraftCode, seatNumber);
    }

    public List<SeatWithCountDTO> getSeatCount() throws FileNotFoundException, SQLException {
        return requestManager.getSeatCount();
    }

    public void deleteAircraftsInRange(int from, int to) throws FileNotFoundException, SQLException {
        requestManager.deleteAircraftsInRange(from, to);
    }

    public void deleteAllAircrafts() throws FileNotFoundException, SQLException {
        requestManager.deleteAllAircrafts();
    }

    public void doubleFlightRange(String code) throws FileNotFoundException, SQLException {
        requestManager.doubleFlightRange(code);
    }

}

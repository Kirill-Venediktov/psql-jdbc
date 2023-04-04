package ru.kirillvenediktov.psql;

import ru.kirillvenediktov.psql.dao.TablesFiller;
import ru.kirillvenediktov.psql.dao.UserRequestManager;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Test {

    static final UserRequestManager requestManager = new UserRequestManager();
    static final TablesFiller filler = new TablesFiller();

    public static void main(String[] args) {
        try {
//            filler.createSchema();
//            filler.createAircraftsTable();
//            filler.createSeatsTable();
//            filler.fillAircraftsTable();
//            requestManager.executeUpdate("INSERT INTO jdbc_planes.aircrafts ( aircraft_code, model, range )" +
//                " VALUES ( 'SU9', 'Sukhoi SuperJet-100', 3000 );");
//            requestManager.showAircrafts();
//            System.out.println("----");
//            requestManager.showSortingAircrafts();
//            System.out.println("-----");
//            requestManager.showAircraftsInRange(4000, 6000);
//            requestManager.executeUpdate("UPDATE jdbc_planes.aircrafts SET range = 4500" +
//                " WHERE aircraft_code = 'SU9' ;");
//            requestManager.executeUpdate("DELETE FROM jdbc_planes.aircrafts;");
//            filler.fillSeatsTable();
//            requestManager.showNumberOfSeats();
//            System.out.println("----");
//            requestManager.showDescSortingAircrafts();
            requestManager.showAircraftByCode("SU9");
            requestManager.executeUpdate("UPDATE jdbc_planes.aircrafts SET range = range*2" +
                " WHERE aircraft_code = 'SU9' ;");
            requestManager.showAircraftByCode("SU9");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

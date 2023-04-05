package ru.kirillvenediktov.psql.service;

import ru.kirillvenediktov.psql.dao.TablesFiller;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class DBFillerManager {

    private TablesFiller tablesFiller = new TablesFiller();

    public void createSchema() throws FileNotFoundException, SQLException {
        tablesFiller.createSchema();
    }

    public void createAircraftsTable() throws FileNotFoundException, SQLException {
        tablesFiller.createAircraftsTable();
    }

    public void createSeatsTable() throws FileNotFoundException, SQLException {
        tablesFiller.fillSeatsTable();
    }

    public void fillAircraftsTable() throws FileNotFoundException, SQLException {
        tablesFiller.fillAircraftsTable();
    }

    public void fillSeatsTable() throws FileNotFoundException, SQLException {
        tablesFiller.fillSeatsTable();
    }

}

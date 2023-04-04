package ru.kirillvenediktov.psql.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    static final String PROPERTIES_PATH = "src/main/resources/config.properties";

    public static Properties getProperties() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(PROPERTIES_PATH)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public Connection getConnection() throws SQLException, FileNotFoundException {
        Properties properties = getProperties();
        if (!properties.isEmpty()) {
            String url = (String) properties.get("URL");
            String username = (String) properties.get("Uname");
            String password = (String) properties.get("password");
            return DriverManager.getConnection(url, username, password);
        } else {
            throw new FileNotFoundException("Properties file not found!");
        }
    }

}

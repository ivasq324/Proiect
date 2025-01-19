package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clasa DBHelper gestionează conexiunile la baza de date utilizată de aplicația Agenda Telefonică.
 */
public class DBHelper {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres"; // URL-ul bazei de date
    private static final String USER = "user"; // Utilizatorul bazei de date
    private static final String PASSWORD = "123"; // Parola bazei de date

    /**
     * Constructor implicit pentru clasa DBHelper.
     */
    public DBHelper() {
        // Constructor gol.
    }

    /**
     * Creează și returnează o conexiune la baza de date.
     *
     * @return Obiectul Connection care reprezintă conexiunea la baza de date.
     * @throws SQLException dacă apare o eroare la conectarea la baza de date.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

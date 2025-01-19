package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {

    private AgendaTelefonica agenda;
    private JTextArea resultArea;

    @BeforeEach
    void setup() {
        resultArea = new JTextArea();
        agenda = new AgendaTelefonica(resultArea);

        try (Connection connection = DBHelper.getConnection();
             Statement stmt = connection.createStatement()) {
            // Resetăm baza de date pentru fiecare test
            stmt.execute("DROP TABLE IF EXISTS contacte");
            stmt.execute("CREATE TABLE contacte (nume VARCHAR(255), numar_telefon VARCHAR(255), email VARCHAR(255))");
        } catch (Exception e) {
            fail("Setup failed: " + e.getMessage());
        }
    }

    @Test
    void testAgendaTelefonicaFlow() {
        // Testăm clasa Contact
        Contact contact = new Contact("Ion Popescu", "0723456789", "ion.popescu@example.com");
        assertEquals("Ion Popescu", contact.getNume());
        assertEquals("0723456789", contact.getNumarTelefon());
        assertEquals("ion.popescu@example.com", contact.getEmail());

        contact.setNume("Maria Ionescu");
        contact.setEmail("maria.ionescu@example.com");
        assertEquals("Maria Ionescu", contact.getNume());
        assertEquals("maria.ionescu@example.com", contact.getEmail());
        assertEquals("Nume: Maria Ionescu, Telefon: 0723456789, Email: maria.ionescu@example.com", contact.toString());

        // Testăm conexiunea la baza de date
        try (Connection connection = DBHelper.getConnection()) {
            assertNotNull(connection);
            assertFalse(connection.isClosed());
        } catch (Exception e) {
            fail("Connection failed: " + e.getMessage());
        }

        // Testăm operațiunile din AgendaTelefonica
        agenda.adaugaContact("Ion Popescu", "0723456789", "ion.popescu@example.com");
        assertTrue(resultArea.getText().contains("Contact adăugat cu succes"));

        agenda.adaugaContact("Maria Ionescu", "0744123456", "maria.ionescu@example.com");
        assertTrue(resultArea.getText().contains("Contact adăugat cu succes"));

        agenda.vizualizeazaContacte();
        assertTrue(resultArea.getText().contains("Ion Popescu"));
        assertTrue(resultArea.getText().contains("Maria Ionescu"));

        agenda.cautaContactDupaNume("Ion Popescu");
        assertTrue(resultArea.getText().contains("0723456789"));

        agenda.cautaContactDupaNumar("0744123456");
        assertTrue(resultArea.getText().contains("Maria Ionescu"));

        agenda.actualizeazaContact("Maria Ionescu", "Maria Georgescu", "maria.georgescu@example.com");
        assertTrue(resultArea.getText().contains("Contact actualizat cu succes"));

        agenda.cautaContactDupaNume("Maria Georgescu");
        assertTrue(resultArea.getText().contains("maria.georgescu@example.com"));

        agenda.stergeContact("Ion Popescu");
        assertTrue(resultArea.getText().contains("Contactul cu numele Ion Popescu a fost șters"));

        agenda.vizualizeazaContacte();
        assertFalse(resultArea.getText().contains("Ion Popescu"));
    }
}

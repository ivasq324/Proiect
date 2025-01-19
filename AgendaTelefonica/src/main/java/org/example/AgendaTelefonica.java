package org.example;

import javax.swing.*;
import java.sql.*;

/**
 * Clasa AgendaTelefonica oferă funcționalități pentru gestionarea unei baze de date de contacte.
 * Interacțiunile sunt afișate într-o zonă de text (`JTextArea`).
 */
public class AgendaTelefonica {

    /**
     * Zonă de text utilizată pentru afișarea rezultatelor și a mesajelor de eroare.
     */
    private JTextArea resultArea;

    /**
     * Constructorul clasei AgendaTelefonica.
     *
     * @param resultArea JTextArea în care vor fi afișate rezultatele operațiunilor.
     */
    public AgendaTelefonica(JTextArea resultArea) {
        this.resultArea = resultArea;
    }

    /**
     * Adaugă un nou contact în baza de date.
     *
     * @param nume         Numele contactului.
     * @param numarTelefon Numărul de telefon al contactului.
     * @param email        Adresa de email a contactului.
     */
    public void adaugaContact(String nume, String numarTelefon, String email) {
        try (Connection conn = DBHelper.getConnection()) {
            String sql = "INSERT INTO contacte (nume, numar_telefon, email) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nume);
                stmt.setString(2, numarTelefon);
                stmt.setString(3, email);
                stmt.executeUpdate();
                resultArea.setText("Contact adăugat cu succes: " + nume + ", " + numarTelefon + ", " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Eroare la adăugarea contactului.");
        }
    }

    /**
     * Actualizează informațiile unui contact existent.
     *
     * @param nume     Numele actual al contactului.
     * @param numeNou  Noul nume al contactului.
     * @param emailNou Noua adresă de email a contactului.
     */
    public void actualizeazaContact(String nume, String numeNou, String emailNou) {
        try (Connection conn = DBHelper.getConnection()) {
            String sql = "UPDATE contacte SET nume = ?, email = ? WHERE nume = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, numeNou);
                stmt.setString(2, emailNou);
                stmt.setString(3, nume);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    resultArea.setText("Contact actualizat cu succes: " + numeNou + ", " + emailNou);
                } else {
                    resultArea.setText("Contactul nu a fost găsit!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Eroare la actualizarea contactului.");
        }
    }

    /**
     * Șterge un contact din baza de date pe baza numelui.
     *
     * @param nume Numele contactului de șters.
     */
    public void stergeContact(String nume) {
        try (Connection conn = DBHelper.getConnection()) {
            String sql = "DELETE FROM contacte WHERE nume = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nume);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    resultArea.setText("Contactul cu numele " + nume + " a fost șters cu succes!");
                } else {
                    resultArea.setText("Contactul nu a fost găsit!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Eroare la ștergerea contactului.");
        }
    }

    /**
     * Vizualizează toate contactele din baza de date, ordonate alfabetic după nume.
     */
    public void vizualizeazaContacte() {
        StringBuilder contacts = new StringBuilder();
        try (Connection conn = DBHelper.getConnection()) {
            String sql = "SELECT * FROM contacte ORDER BY nume ASC";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    String nume = rs.getString("nume");
                    String numarTelefon = rs.getString("numar_telefon");
                    String email = rs.getString("email");
                    contacts.append("Nume: ").append(nume)
                            .append(", Telefon: ").append(numarTelefon)
                            .append(", Email: ").append(email).append("\n");
                }
                resultArea.setText(contacts.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Eroare la vizualizarea contactelor.");
        }
    }

    /**
     * Caută un contact după numele său.
     *
     * @param nume Numele contactului de căutat.
     */
    public void cautaContactDupaNume(String nume) {
        try (Connection conn = DBHelper.getConnection()) {
            String sql = "SELECT * FROM contacte WHERE nume = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nume);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String numarTelefon = rs.getString("numar_telefon");
                        String email = rs.getString("email");
                        resultArea.setText("Nume: " + nume + ", Telefon: " + numarTelefon + ", Email: " + email);
                    } else {
                        resultArea.setText("Contactul nu a fost găsit!");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Eroare la căutarea contactului.");
        }
    }

    /**
     * Caută un contact după numărul său de telefon.
     *
     * @param numarTelefon Numărul de telefon al contactului de căutat.
     */
    public void cautaContactDupaNumar(String numarTelefon) {
        try (Connection conn = DBHelper.getConnection()) {
            String sql = "SELECT * FROM contacte WHERE numar_telefon = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, numarTelefon);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String nume = rs.getString("nume");
                        String email = rs.getString("email");
                        resultArea.setText("Nume: " + nume + ", Telefon: " + numarTelefon + ", Email: " + email);
                    } else {
                        resultArea.setText("Contactul nu a fost găsit!");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Eroare la căutarea contactului.");
        }
    }
}

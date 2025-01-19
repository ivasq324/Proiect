package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clasa principală a aplicației care creează interfața grafică pentru gestionarea unei agende telefonice.
 * Aplicația permite utilizatorului să adauge, actualizeze, șteargă și să vizualizeze contacte, folosind o bază de date.
 */
public class App {

    private AgendaTelefonica agenda; // Instanța pentru gestionarea contactelor
    private JTextField numeField, numarTelefonField, emailField; // Câmpuri de text pentru introducerea datelor
    private JTextArea resultArea; // Zonă pentru afișarea rezultatelor

    /**
     * Constructor implicit pentru clasa App.
     */
    public App() {
        // Constructor gol.
    }

    /**
     * Punctul de intrare al aplicației.
     *
     * @param args Argumente din linia de comandă (nu sunt utilizate).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.createUI();
        });
    }

    /**
     * Creează interfața grafică pentru gestionarea contactelor.
     * Configurează câmpurile de introducere, butoanele și zona de afișare a rezultatelor.
     */
    public void createUI() {
        // Crearea ferestrei principale
        JFrame frame = new JFrame("Agenda Telefonică");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        // Panou pentru introducerea datelor
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        // Crearea etichetelor și câmpurilor de text
        JLabel numeLabel = new JLabel("Nume:");
        numeField = new JTextField();
        JLabel numarTelefonLabel = new JLabel("Număr de telefon:");
        numarTelefonField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        inputPanel.add(numeLabel);
        inputPanel.add(numeField);
        inputPanel.add(numarTelefonLabel);
        inputPanel.add(numarTelefonField);
        inputPanel.add(emailLabel);
        inputPanel.add(emailField);

        // Panou pentru butoane
        JPanel buttonPanel = new JPanel();
        JButton adaugaButton = new JButton("Adăugare contact");
        JButton actualizeazaButton = new JButton("Actualizare contact");
        JButton stergeButton = new JButton("Ștergere contact");
        JButton cautaNumeButton = new JButton("Căutare după nume");
        JButton cautaNumarButton = new JButton("Căutare după număr");
        JButton vizualizeazaButton = new JButton("Vizualizează contacte");

        buttonPanel.add(adaugaButton);
        buttonPanel.add(actualizeazaButton);
        buttonPanel.add(stergeButton);
        buttonPanel.add(cautaNumeButton);
        buttonPanel.add(cautaNumarButton);
        buttonPanel.add(vizualizeazaButton);

        // Zonă pentru afișarea rezultatelor
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setPreferredSize(new Dimension(400, 200));
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Instanțierea clasei AgendaTelefonica
        agenda = new AgendaTelefonica(resultArea);

        // Adăugarea acțiunilor pentru butoane
        adaugaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nume = numeField.getText();
                String numarTelefon = numarTelefonField.getText();
                String email = emailField.getText();
                agenda.adaugaContact(nume, numarTelefon, email);
            }
        });

        actualizeazaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nume = numeField.getText();
                String numeNou = numeField.getText();
                String emailNou = emailField.getText();
                agenda.actualizeazaContact(nume, numeNou, emailNou);
            }
        });

        stergeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nume = numeField.getText();
                agenda.stergeContact(nume);
            }
        });

        cautaNumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nume = numeField.getText();
                agenda.cautaContactDupaNume(nume);
            }
        });

        cautaNumarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numarTelefon = numarTelefonField.getText();
                agenda.cautaContactDupaNumar(numarTelefon);
            }
        });

        vizualizeazaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agenda.vizualizeazaContacte();
            }
        });

        // Adăugarea componentelor în fereastră
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}

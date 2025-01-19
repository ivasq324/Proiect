package org.example;

/**
 * Clasa Contact reprezintă un contact individual din agenda telefonică.
 * Conține informații precum numele, numărul de telefon și adresa de email.
 */
public class Contact {

    private String nume;
    private final String numarTelefon;
    private String email;

    /**
     * Constructorul pentru crearea unui contact.
     *
     * @param nume         Numele contactului.
     * @param numarTelefon Numărul de telefon al contactului.
     * @param email        Adresa de email a contactului.
     */
    public Contact(String nume, String numarTelefon, String email) {
        this.nume = nume;
        this.numarTelefon = numarTelefon;
        this.email = email;
    }

    /**
     * Returnează numele contactului.
     *
     * @return Numele contactului.
     */
    public String getNume() {
        return nume;
    }

    /**
     * Returnează adresa de email a contactului.
     *
     * @return Adresa de email a contactului.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returnează numărul de telefon al contactului.
     *
     * @return Numărul de telefon al contactului.
     */
    public String getNumarTelefon() {
        return numarTelefon;
    }

    /**
     * Setează numele contactului.
     *
     * @param nume Noul nume al contactului.
     */
    public void setNume(String nume) {
        this.nume = nume;
    }

    /**
     * Setează adresa de email a contactului.
     *
     * @param email Noua adresă de email a contactului.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returnează o descriere completă a contactului.
     *
     * @return O descriere a contactului sub formă de text.
     */
    @Override
    public String toString() {
        return "Nume: " + nume + ", Telefon: " + numarTelefon + ", Email: " + email;
    }
}

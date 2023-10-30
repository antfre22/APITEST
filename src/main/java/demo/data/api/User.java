package demo.data.api;

//Interface zum Speichern der User-Daten
//Uebernommen vom Hartiwg

public interface User {

    String getFirstName();

    String setFirstName(String firstName);

    String getLastName();

    String setLastName(String lastName);

    String getPasswort();

    String setPasswort(String passwort);

    String getEmail();

    String setEmail(String email);

    String getToken();
    //Wie lange Token gueltig?
    float getValidUntil();
}

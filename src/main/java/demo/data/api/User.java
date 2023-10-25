package demo.data.api;

//Interface zum Speichern der User-Daten
//Uebernommen vom Hartiwg

public interface User {

    String getFirstName();

    String setFirstName();

    String getLastName();

    String setLastName();

    String getPasswort();

    String setPasswort();

    String getEmail();

    String setEmail();

    String getToken();
    //Wie lange Token gueltig?
    long getValidUntil();
}

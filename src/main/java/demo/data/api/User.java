package demo.data.api;

//Interface zum Speichern der User-Daten
//Übernommen von Hartiwg

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
    //Wie lange Token gültig?
    long getValidUntil();
}

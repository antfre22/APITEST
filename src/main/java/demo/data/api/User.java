package demo.data.api;

//Interface zum Speichern der User-Daten
//Übernommen von Hartiwg

public interface User {

    String getFirstName();

    String getLastName();

    String getPasswort();

    String getEmail();

    String getToken();

    //Wie lange Token gültig?
    long getValidUntil();
}

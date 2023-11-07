package demo.data.api;

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

    float getValidUntil();
}

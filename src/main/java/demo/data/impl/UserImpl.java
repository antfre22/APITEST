package demo.data.impl;

import demo.data.api.User;
public class UserImpl implements User {
    String lastName;

    String firstName;

    String password;

    String token;

    String email;

    public UserImpl(String firstName,String lastName, String password, String email, String token) {
          this.firstName = firstName;
          this.lastName = lastName;
          this.password = password;
          this.email = email;
          this.token = token;
    }

    @Override
    public String getFirstName() {
        return null;
    }

    @Override
    public String setFirstName(String name) {
        return null;
    }

    @Override
    public String getLastName() {
        return null;
    }

    @Override
    public String setLastName(String lastName) {
        return null;
    }

    @Override
    public String getPasswort() {
        return password;
    }

    @Override
    public String setPasswort(String passwort) {
        return null;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String setEmail(String email) {
        return null;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public float getValidUntil() {
        return 0;
    }
}

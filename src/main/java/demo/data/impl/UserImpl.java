package demo.data.impl;

import demo.data.api.User;
public class UserImpl implements User{
    String lastName;
    String firstName;
    String password;

    String email;
    public UserImpl(String firstName,String lastName, String password, String email){
      this.firstName = firstName;
      this.lastName = lastName;
      this.password = password;
      this.email = email;
    }

    @Override
    public String getFirstName() {
        return null;
    }

    @Override
    public String setFirstName() {
        return null;
    }

    @Override
    public String getLastName() {
        return null;
    }

    @Override
    public String setLastName() {
        return null;
    }

    @Override
    public String getPasswort() {
        return null;
    }

    @Override
    public String setPasswort() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String setEmail() {
        return null;
    }

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public long getValidUntil() {
        return 0;
    }
}

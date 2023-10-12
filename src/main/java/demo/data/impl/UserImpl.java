package demo.data.impl;

import demo.data.api.User;
public class UserImpl implements User{
    String name;
    String password;
    public UserImpl(String name, String password){
      this.name = name;
      this.password = password;
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

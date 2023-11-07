package demo.data.api;

import java.util.List;

public interface UserManager {
    List<User> readAllUsers();

    String createUser(String firstName, String lastName, String userPassword, String email);

    String Login (String Email, String Password);

    String Logout (String Email);

    void createUserTable();
}

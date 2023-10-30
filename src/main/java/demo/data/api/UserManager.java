package demo.data.api;

import java.util.List;

public interface UserManager {
    List<User> readAllUsers();
    String createUser(String firstName, String lastName, String userPassword, String email);
    User logUserIn(String email, String password);
    User logUserOff(String email, String token);
    String getEmailForToken(String token);

    String Login (String Email, String Password);

   void createUserTable();
}

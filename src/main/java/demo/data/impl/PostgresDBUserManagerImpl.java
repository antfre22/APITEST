package demo.data.impl;

import demo.data.api.UserManager;
import demo.data.api.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
public class PostgresDBUserManagerImpl implements UserManager {
    //ToDo für Authentifizierung an der Datenbank
    String databaseURL = "jdbc:postgresql://ec2-34-202-127-5.compute-1.amazonaws.com:5432/defa4ehgv6lm5";
    String username = "etjbssbrohalwp";
    String password = "2d579abf3a3eb6e77a889ca22e20677c6b88c60041a2d6c8796d547bf0ae5e99";

    public List<User> readAllUsers(){
        List<User> myList = new ArrayList<>();
        return myList;
    }
    public User createUser(String firstName, String lastName, String userPassword, String email){
        UserImpl user = new UserImpl("Hallo","Test");
        return user;
    }
    public User logUserIn(String email, String password){
        UserImpl user = new UserImpl("Hallo","Test");
        return user;
    }
    public User logUserOff(String email, String token){
        UserImpl user = new UserImpl("Hallo","Test");
        return user;
    }
    public String getEmailForToken(String token){
        return "hello";
    }

}

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
    String databaseURL = "jdbc:postgresql://ec2-52-1-92-133.compute-1.amazonaws.com:5432/dbq8q1o8ump5db";
    String username = "qkmdiqnoiwgfyj";
    String password = "74fa1789b3b99e9a4ce0877b688e5aea90eea02573ceb014fff0eac7ccb9b2ff";

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

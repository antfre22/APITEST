package demo.data.impl;

import demo.data.api.Ingredients;
import demo.data.api.UserManager;
import demo.data.api.User;
import org.apache.commons.dbcp.BasicDataSource;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
public class PostgresDBUserManagerImpl implements UserManager {
    String databaseURL = "jdbc:postgresql://ec2-52-1-92-133.compute-1.amazonaws.com:5432/dbq8q1o8ump5db";
    String username = "qkmdiqnoiwgfyj";
    String password = "74fa1789b3b99e9a4ce0877b688e5aea90eea02573ceb014fff0eac7ccb9b2ff";

    BasicDataSource basicDataSource;

    // Singleton
    static PostgresDBUserManagerImpl postgresDBUserManager = null;
    private PostgresDBUserManagerImpl() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(databaseURL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
    }
    public static PostgresDBUserManagerImpl getPostgresDBUserManagerImpl() {
        if (postgresDBUserManager == null)
            postgresDBUserManager = new PostgresDBUserManagerImpl();
        return postgresDBUserManager;
    }

    public void createUserTable() {

        //  It deletes data if table already exists.
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            String createUserTable = "CREATE TABLE users (" +
                    "id SERIAL PRIMARY KEY, " +
                    "firstname varchar(100) NOT NULL, " +
                    "lastname varchar(100) NOT NULL, " +
                    "password varchar(100) NOT NULL, " +
                    "email varchar(100) NOT NULL, " +
                    "token varchar(100) NOT NULL, " +
                    "validuntil float NOT NULL)";


            stmt.executeUpdate(createUserTable);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String Login(String Email, String Password)
    {
        final Logger readTaskLogger = Logger.getLogger("ReadUserLogger");
        readTaskLogger.log(Level.INFO,"Start reading List of Users");


        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * " +
                                                "FROM users" +
                                                "WHERE Email =" + Email + " and Password = " + Password);

            if (rs.next()) {
                return "Anmeldung erfolgreich!";
            }
            else {
               return "Falsche Email oder Passwort!";
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Hat noch nicht funktioniert";
    }


    public List<User> readAllUsers(){

        final Logger readTaskLogger = Logger.getLogger("ReadUserLogger");
        readTaskLogger.log(Level.INFO,"Start reading List of Users");

        List<User> myUsers = new ArrayList<>();
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            while (rs.next()) {
                myUsers.add(
                        //String firstName,String lastName,
                        //String password, String email, String token
                        new UserImpl(
                                rs.getString("firstname"),
                                rs.getString("lastname"),
                                rs.getString("password"),
                                rs.getString("email"),
                                rs.getString("token")
                                 )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myUsers;
    }
    public String createUser(String firstName, String lastName, String userPassword, String email){

        final Logger createTaskLogger = Logger.getLogger("CreateUserLogger");
        createTaskLogger.log(Level.INFO,"Start creating user: " + firstName);

        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into users (firstname, lastname, password, email, token, validuntil) VALUES (" +
                    "'" + firstName + "', " +
                    "'" + lastName + "', " +
                    "'" + password + "', " +
                    "'" + email + "', " +
                    "'logged-off', " +
                    "0)";

            stmt.executeUpdate(udapteSQL);

            stmt.close();
            connection.close();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        return "Added User with name: " + firstName + " and email:  " + email ;
    }
    public User logUserIn(String email, String password){
        UserImpl user = new UserImpl("Hallo","Test","ganzstark", "","");
        return user;
    }
    public User logUserOff(String email, String token){
        UserImpl user = new UserImpl("Hallo","Test", "ganzstark", "", "");
        return user;
    }
    public String getEmailForToken(String token){

        return "hello";
    }

}

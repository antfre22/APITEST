package demo.data.impl;

import demo.data.api.Ingredients;
import demo.data.api.UserManager;
import demo.data.api.User;
import org.apache.commons.dbcp.BasicDataSource;

import javax.swing.plaf.nimbus.State;
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
    String dbpassword = "74fa1789b3b99e9a4ce0877b688e5aea90eea02573ceb014fff0eac7ccb9b2ff";

    //Encoder noch rein


    //74fa1789b3b99e9a4ce0877b688e5aea90eea02573ceb014fff0eac7ccb9b2ff
    BasicDataSource basicDataSource;

    // Singleton
    static PostgresDBUserManagerImpl postgresDBUserManager = null;
    private PostgresDBUserManagerImpl() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(databaseURL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(dbpassword);
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
        readTaskLogger.log(Level.INFO,"Start authentification of the User");

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = basicDataSource.getConnection();
            readTaskLogger.info("Connection established.");

            String selectSQL = "SELECT * FROM users WHERE email = ? AND password = ?;";
            stmt = connection.prepareStatement(selectSQL);
            stmt.setString(1, Email);
            stmt.setString(2, Password);
            readTaskLogger.info("Executing query with email: " + Email);

            rs = stmt.executeQuery();

            if (rs.next())
            {
                readTaskLogger.info("User found in database");
                return "erfolgreich";
            }
            else
            {
                readTaskLogger.warning("No user Found");
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

   /*     Statement stmt = null;
        Connection connection = null;
        List<User> myUsers = readAllUsers();
        User userT = null;

        for (User u : myUsers){
            if(u.getEmail().equals(Email))
            {
                userT = u;
            }
        }
        if(!userT.getPasswort().equals(Password))
        {
            return null;
        }
        try {
            connection = basicDataSource.getConnection();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    */
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
                    "'" + userPassword + "', " +
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

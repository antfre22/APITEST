package demo.data.impl;

import demo.data.api.UserManager;
import demo.data.api.User;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgresDBUserManagerImpl implements UserManager {
    String databaseURL = "jdbc:postgresql://ec2-52-1-92-133.compute-1.amazonaws.com:5432/dbq8q1o8ump5db";
    String username = "qkmdiqnoiwgfyj";
    String dbpassword = "74fa1789b3b99e9a4ce0877b688e5aea90eea02573ceb014fff0eac7ccb9b2ff";

    public String tokenGenerator() {

        return UUID.randomUUID().toString();
    }

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
                    "token varchar(100), " +
                    "validuntil TIMESTAMP)";


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

    public String Login(String Email, String Password) {

        final Logger readTaskLogger = Logger.getLogger("ReadUserLogger");
        readTaskLogger.log(Level.INFO, "Start authentification of the User");

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

            if (rs.next()) {
                readTaskLogger.info("User found in database");
                String token = tokenGenerator();

                Timestamp validUntil = new Timestamp(System.currentTimeMillis() + 24 * 60 * 60 * 1000);

                String updateSQL = "UPDATE users SET token = ?, validuntil = ? WHERE email = ?;";

                PreparedStatement newStmt = connection.prepareStatement(updateSQL);
                newStmt.setString(1, token);
                newStmt.setTimestamp(2, validUntil);
                newStmt.setString(3, Email);

                newStmt.executeUpdate();
                return token;
            } else {
                readTaskLogger.warning("No user Found");
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String Logout(String Email) {

        final Logger readTaskLogger = Logger.getLogger("ReadUserLogger");
        readTaskLogger.log(Level.INFO, "Start authentification of the User");

        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = basicDataSource.getConnection();
            readTaskLogger.info("Connection established.");

            String updateSQL = "UPDATE users SET token = NULL, validuntil = NULL WHERE email = ?;";
            stmt = connection.prepareStatement(updateSQL);
            stmt.setString(1, Email);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                readTaskLogger.info("User logged out successfully.");
                return "User wurde ausgeloggt";
            } else {
                readTaskLogger.warning("User not found or already logged out.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Close resources (stmt, connection) and handle exceptions if needed.
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return "nicht funktioniert";
    }

    public List<User> readAllUsers() {

        final Logger readTaskLogger = Logger.getLogger("ReadUserLogger");
        readTaskLogger.log(Level.INFO, "Start reading List of Users");

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

    public String createUser(String firstName, String lastName, String userPassword, String email) {

        final Logger createTaskLogger = Logger.getLogger("CreateUserLogger");
        createTaskLogger.log(Level.INFO, "Start creating user: " + firstName);

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
                    "null)";

            stmt.executeUpdate(udapteSQL);

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Added User with name: " + firstName + " and email:  " + email;
    }
}

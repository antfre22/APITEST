package demo.data.impl;

import demo.data.api.Ingredients;
import demo.data.api.ListManager;
import org.apache.commons.dbcp.BasicDataSource;

import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgresDBListManagerImpl implements ListManager {

    String databaseURL = "jdbc:postgresql://ec2-52-1-92-133.compute-1.amazonaws.com:5432/dbq8q1o8ump5db";
    String username = "qkmdiqnoiwgfyj";
    String password = "74fa1789b3b99e9a4ce0877b688e5aea90eea02573ceb014fff0eac7ccb9b2ff";

    BasicDataSource basicDataSource;

    // Singleton
    static PostgresDBListManagerImpl postgresDBListManager = null;
    private PostgresDBListManagerImpl() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(databaseURL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
    }
    public static PostgresDBListManagerImpl getPostgresDBUserManagerImpl() {
        if (postgresDBListManager == null)
            postgresDBListManager = new PostgresDBListManagerImpl();
        return postgresDBListManager;
    }

    @Override
    public List<Ingredients> readAllIngredients() {
        return null;
    }

    @Override
    public void addIngredients(Ingredients ingredients) {

    }

    @Override
    public void createListTable() {
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            // String dropTable = "DROP TABLE tasks";
            // stmt.executeUpdate(dropTable);

            String createTable = "CREATE TABLE shoppingList (" +
                    "shopListId SERIAL PRIMARY KEY, " +
                    "ingredients varchar(100) NOT NULL, " +
                    "quantity double NOT NULL)";

            stmt.executeUpdate(createTable);

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
}
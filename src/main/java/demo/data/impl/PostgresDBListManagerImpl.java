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
    public static PostgresDBListManagerImpl getPostgresDBListManagerImpl() {
        if (postgresDBListManager == null)
            postgresDBListManager = new PostgresDBListManagerImpl();
        return postgresDBListManager;
    }


    @Override
    public List<Ingredients> readAllIngredients() {
        final Logger readTaskLogger = Logger.getLogger("ReadListLogger");
        readTaskLogger.log(Level.INFO,"Start reading shoppingList ");

        List<Ingredients> ingredients = new ArrayList<>();
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM shoppingList");

            while (rs.next()) {
                ingredients.add(
                        new IngredientsImpl(
                                rs.getString("ingredients"),
                                rs.getFloat("quantity")
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

        return ingredients;
    }

    @Override
    public void addIngredients(String ingredients, float quantity) {

        final Logger addIngredientsLogger = Logger.getLogger("AddIngredientsLogger");
        addIngredientsLogger.log(Level.INFO,"Start adding ingredient: " + ingredients);

        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            String udapteSQL = "INSERT into shoppingList (ingredients, quantity) VALUES (" +
                    "'" + ingredients + "', " +
                    quantity + ")";

            stmt.executeUpdate(udapteSQL);

            stmt.close();
            connection.close();
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

    public void createListTable() {
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            // String dropTable = "DROP TABLE tasks";
            // stmt.executeUpdate(dropTable);

            String createTable = "CREATE TABLE shoppingList (" +
                    "Shoplistid SERIAL PRIMARY KEY" +
                    "Ingredients varchar(100) NOT NULL " +
                    "quantity float NOT NULL)";

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

//Test

    @Override
    public void deleteIngredient(int ingredient) {
        Statement stmt = null;
        Connection connection = null;

        final Logger deleteIngredientsLogger = Logger.getLogger("DeleteIngredientsLogger");
        deleteIngredientsLogger.log(Level.INFO,"Start deleting ingredient at id= " + ingredient);


        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();


            String deleteSQL = "DELETE FROM shoppingList WHERE Shoplistid = " + ingredient;

            stmt.executeUpdate(deleteSQL);

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

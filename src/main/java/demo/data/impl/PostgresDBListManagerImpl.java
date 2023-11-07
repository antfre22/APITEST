package demo.data.impl;

import demo.data.api.Ingredients;
import demo.data.api.ListManager;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
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
        readTaskLogger.log(Level.INFO, "Start reading shoppingList ");

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
        addIngredientsLogger.log(Level.INFO, "Start adding ingredient: " + ingredients);

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

    //Delete Ingredient Neu

    @Override
    public void deleteIngredient(String ingredient) {


        final Logger deleteIngredientsLogger = Logger.getLogger("DeleteIngredientsLogger");
        deleteIngredientsLogger.log(Level.INFO, "Start deleting ingredient at name= " + ingredient);


        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = basicDataSource.getConnection();
            deleteIngredientsLogger.info("Connection erfolgreich");


            String deleteSQL = "DELETE FROM shoppingList WHERE ingredients = ?;";
            stmt = connection.prepareStatement(deleteSQL);
            stmt.setString(1, ingredient);


            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                deleteIngredientsLogger.info("Deleted successfully.");

            } else {
                deleteIngredientsLogger.warning("Zutat nicht da.");
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

    }
}

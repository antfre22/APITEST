package demo.data.impl;
import demo.data.api.Ingredients;
import demo.data.api.Recipe;
import demo.data.api.RecipeManager;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgresDBRecipeManagerImpl implements RecipeManager{
    String databaseURL = "jdbc:postgresql://ec2-52-1-92-133.compute-1.amazonaws.com:5432/dbq8q1o8ump5db";
    String username = "qkmdiqnoiwgfyj";
    String password = "74fa1789b3b99e9a4ce0877b688e5aea90eea02573ceb014fff0eac7ccb9b2ff";

    BasicDataSource basicDataSource;
    static PostgresDBRecipeManagerImpl postgresDBRecipeManager = null;
    private PostgresDBRecipeManagerImpl() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(databaseURL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
    }
    public static PostgresDBRecipeManagerImpl getPostgresDBRecipeManagerImpl() {
        if (postgresDBRecipeManager == null)
            postgresDBRecipeManager = new PostgresDBRecipeManagerImpl();
        return postgresDBRecipeManager;
    }


    @Override
    public List<Recipe> readAllRecipes() {

        final Logger readTaskLogger = Logger.getLogger("ReadListLogger");
        readTaskLogger.log(Level.INFO, "Start reading shoppingList ");

        List<Recipe> recipes = new ArrayList<>();
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM recipes");

            while (rs.next()) {
                java.sql.Date sqlDate = rs.getDate("datum");
                java.util.Date recipeDate = new java.util.Date(sqlDate.getTime());

                recipes.add(
                        new RecipeImpl(
                                rs.getString("recipeName"),
                                recipeDate
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

        return null;
    }

    @Override
    public void addRecipes(String recipeName, Date datum) {
        final Logger createRecipeLogger = Logger.getLogger("AddRecipeLogger");
        createRecipeLogger.log(Level.INFO,"Start adding recipe " + recipeName);

        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            String udapteSQL = "INSERT into recipes ( TokenOfUser, recipeName, datum) VALUES (" +
                    " 'notoken', " +
                    "'" + recipeName+ "', "
                    + "'" + datum + "')";

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
    @Override
    public void deleteRecipe(String name, Date date) {


        final Logger deleteRecipeLogger = Logger.getLogger("DeleteRecipeLogger");
        deleteRecipeLogger.log(Level.INFO, "Start deleting recipe at name= " + name);


        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = basicDataSource.getConnection();
            deleteRecipeLogger.info("Connection erfolgreich");


            String deleteSQL = "DELETE FROM recipes WHERE recipeName = ? AND datum = ? ;";
            stmt = connection.prepareStatement(deleteSQL);
            stmt.setString(1, name);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            stmt.setDate(2, sqlDate);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {

                deleteRecipeLogger.info("Deleted successfully.");

            }
            else {
                deleteRecipeLogger.warning("Rezept nicht da.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    @Override
    public void createRecipeTable() {
        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            String createTable = "CREATE TABLE recipes (" +
                    "id SERIAL PRIMARY KEY, " +
                    "TokenOfUser varchar(100) NOT NULL, " +
                    "recipeName varchar(100) NOT NULL, " +
                    "datum date NOT NULL)";

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

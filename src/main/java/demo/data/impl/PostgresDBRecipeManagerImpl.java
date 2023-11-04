package demo.data.impl;
import demo.data.api.Recipe;
import demo.data.api.RecipeManager;
import org.apache.commons.dbcp.BasicDataSource;
import java.util.Date;
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

        return null;
    }

    @Override
    public void addRecipes(String recipeName, Date datum) {
        final Logger createRecipeLogger = Logger.getLogger("AddRecipeLogger");
        createRecipeLogger.log(Level.INFO,"Start adding recipe" + recipeName);

        Statement stmt = null;
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
            stmt = connection.createStatement();

            String udapteSQL = "INSERT into recipes (recipeName, datum, TokenOfUser) VALUES (" +
                    "'" + recipeName+ "', "
                    + "'" + datum + "')"
                    + "'notoken'";

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

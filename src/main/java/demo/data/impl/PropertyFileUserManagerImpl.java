package demo.data.impl;

import demo.data.api.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyFileUserManagerImpl implements UserManager {
//Brauchen wir den überhapt??
    String userPropertyFile;

    // Singleton
    static PropertyFileUserManagerImpl propertyFileUserManager = null;
    private PropertyFileUserManagerImpl(String userPropertyFile) {
        this.userPropertyFile = userPropertyFile;
    }
    public static PropertyFileUserManagerImpl getPropertyFileUserManagerImpl(String userPropertyFile) {
        if (propertyFileUserManager == null)
            propertyFileUserManager = new PropertyFileUserManagerImpl(userPropertyFile);
        return propertyFileUserManager;
    }


    @Override
    public String createUser(String firstName, String lastName, String userPassword, String email) {

        final Logger createUserLogger = Logger.getLogger("CreateUserLogger");
        createUserLogger.log(Level.INFO,"Start creating " + email);

        List<User> users = readAllUsers();

        createUserLogger.log(Level.INFO,"Adding new user.");

        // User newUser = new UserImpl(firstName, lastName, userPassword, email, "logged-off",0);
        // users.add(newUser);

        createUserLogger.log(Level.INFO,"Start storing all users.");

        storeAllUsers(users);

        return
                null;
    }

    public void storeAllUsers(List<User> users) {
        // todo
    }


    public List<User> readAllUsers() {

        final Logger readTaskLogger = Logger.getLogger("ReadTaskLogger");
        readTaskLogger.log(Level.INFO, "Start reading ");

        List<User> userData = new ArrayList<>();
        Properties properties = new Properties();

        int i = 1;
        try {
            properties.load(new FileInputStream(userPropertyFile));
            while (properties.containsKey("user." + i + ".token")) {
                userData.add(
                        new UserImpl(
                                properties.getProperty("user." + i + ".firstName"),
                                properties.getProperty("user." + i + ".lastName"),
                                properties.getProperty("user." + i + ".password"),
                                properties.getProperty("user." + i + ".email"),
                                properties.getProperty("user." + i + ".token")
                        )
                );
                i++;
            }
        }
        catch(IOException e){
                e.printStackTrace();
            }

            return userData;

    }




    @Override
    public String getEmailForToken(String token) {
        for(User u : readAllUsers())
            if (u.getToken().equals(token))
                return u.getEmail();
        return "not found";
    }

    @Override
    public String Login(String Email, String Password) {
        return "falsche Methode genommen";
    }

    @Override
    public String Logout(String Email) {
        return "falsche methode genommen";
    }

    @Override
    public void createUserTable() {

    }


}
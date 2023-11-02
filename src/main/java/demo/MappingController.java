package demo;


import demo.data.api.*;
import demo.data.api.ListManager;
import demo.data.impl.*;

import demo.model.Alexa.AlexaRO;
import demo.model.Alexa.OutputSpeechRO;
import demo.model.Alexa.ResponseRO;
import demo.model.ShoppingList;
import demo.model.SendBackToken;
import demo.model.TokenIngredient;
import demo.model.UserList;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import demo.data.impl.PropertyFileUserManagerImpl;

import org.apache.commons.dbcp.BasicDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import demo.model.TokenUser;



@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1.0")
public class MappingController {

    //sehr wichtig, nach API First Prinzip hier vorzugehen
    //Testen der HTTP:Resquest mit Prestige.dev und den passenden URL und HTTP-Aufrufe
    //folgender UserManager legt die User in der DB an und bearbeitet ihre Daten
    //UserManager userManager = PostgresDBUserManagerImpl.getPostgresDBUserManagerImpl();
    UserManager userManager = PostgresDBUserManagerImpl.getPostgresDBUserManagerImpl();
    ListManager listManager = PostgresDBListManagerImpl.getPostgresDBListManagerImpl();
    //evtl. noch ein shoppingListManager der sich um alles mit der ShoppingList kümmert
    //ähnlich dem Prinzip von Hartwig mit TaskManager

    /*
     GET-Methode
      für Basic-Abfrage ob Server "alive" ist
     */
    @GetMapping("/auth")

    public String getInfo(@RequestParam(value = "name", defaultValue = "User") String name) {
        Logger.getLogger("MappingController").log(Level.INFO, "MappingController auth " + name);
        return "im alive guys";
    }

    /*
     Post-Methode
      fuer den Login eines Users in das System
     */
    @PostMapping(
            path = ("/auth/login"),
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String userLogin(@RequestBody TokenUser user ) {
        //To-do: Check Verification of the token
        //Step 1: Check Token des Users
        List<User> usersFromDB = userManager.readAllUsers();

        Iterator<User> iterator = usersFromDB.iterator();
        while (iterator.hasNext()) {
            User currentUser = iterator.next();
            if (currentUser.getEmail() == user.getUser().getUserEmail()) {

                return "User: " + user.getUser().getUserEmail() + " wurde angemeldet";

            }
        }
      //  return new SendBackToken("jhnaosvgioa gvi", 67978);
        return "Anmeldung fehlgeschlagen";
    }

    @DeleteMapping(
            path = ("/auth/logoff"),
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public String userLogoff() {
        //Step 1: Token des Users löschen
        //Step 2: Ausgabe Log off war erfolgreich
        return "Log off was succesfull";
    }

    /*
     POST-Methode, die es ermoeglicht das der User sich
     einmalig im System registrieren kann
     */
    @PostMapping(
            path = ("/user"),
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String registerUser(@RequestBody TokenUser TokenUser) {
            //Step 1: Token kreieren
            //Step 2: fetch data from Userregistrierung in die DB
        String rs = userManager.createUser(TokenUser.getUser().getFirstName(),TokenUser.getUser().getLastName(), TokenUser.getUser().getUserPassword(), TokenUser.getUser().getUserEmail());
            //Step 3: Token an User ausgeben mit validInSeconds
    //    return new SendBackToken("uavoiggpvagiv", 360);
        return rs;
    }

    /*
     GET-Methode, die es ermöglicht das der User
     seine Daten zurückbekommt
     */

    //Request-Param einzelne Parameter JSON

    //Request-Body - alles

    @GetMapping("/user")
    public User getUserData(@RequestParam(value = "token", defaultValue = "no-token") String token){
        //Step 1: Check Token to the requested Data
        //Step 2: fetch data from DB
        //noch zu impeln
        //List<User> myUsers = userManager.readAllUsers();

        //Step 3: Ausgabe der Daten des Users
        return null;
    }

    /*
 DELETE-Methode, die es ermoeglicht das
 der Nutzer seinen Account loeschen kann
 */
    @DeleteMapping(
            path = ("/user"),
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public String deleteUser() {
        //Step 1: Check Token
        //Step 2: delete data in the DB from table users
        return "User got deleted with the following name: Test";
    }

    /*
 GET-Methode, die es ermöglicht das der Admin
 alle User mit ihren Daten angezeigt bekommt
 */
    @GetMapping("/user/all")
    public UserList getAllUsers(@RequestParam(value = "token", defaultValue = "no-token") String token) {
        Logger.getLogger("MappingController")
                .log(Level.INFO,"MappingController /user/all ");
            //Step 1: Check admin Rechte
            //Step 2: fetch data from DB
        List<User> usersfromDB = userManager.readAllUsers();
        List<demo.model.User> myUsers = new ArrayList<>();
            //Step 3: Ausgabe einer Liste mit den Usern und ihren Daten
        for(User user : usersfromDB) {
            myUsers.add(new demo.model.User(user.getEmail(),user.getPasswort(),
                    user.getLastName(), user.getFirstName(), user.getToken()));
        }
        return new UserList(myUsers);
 /*       {
            "users": [
            {
                "userEmail": "fef",
                    "userPassword": null,
                    "lastName": null,
                    "firstName": null,
                    "token": "logged-off"
            },
            {
                "userEmail": "fefgjngjdjgd",
                    "userPassword": null,
                    "lastName": null,
                    "firstName": null,
                    "token": "logged-off"
            }
  ] Ausgabe muss noch noch richtig gemaxht werden
        } */
    }
    /*
    GET-Methode, die dem Nutzer ermöglicht seine aktuelle
    Einkaufsliste angezeigt zu bekommen
     */

   @GetMapping("/shoppinglist")
    public ShoppingList getShoppingList(@RequestParam(value = "token", defaultValue = "no-token") String token) {

        Logger.getLogger("MappingController")
                .log(Level.INFO,"MappingController /ingredients/all ");
        //Step 1: Check Token
        //Step 2: fetch shoppingList from DB
      List <demo.data.api.Ingredients> ingredientsFromFile = listManager.readAllIngredients();
       List<demo.model.Ingredients> myIngredients = new ArrayList<>();
       for (demo.data.api.Ingredients t : ingredientsFromFile)
           myIngredients.add(new demo.model.Ingredients(t.getName(), t.getQuantity()));
        // Step 3: Ausgabe analog Hartwig Tasks
       return new ShoppingList(myIngredients);
    }

    /*
    Post-Methode, die dem Nutzer ermöglicht seine
    Einkaufsliste mit Zutaten zu füllen
     */
    @PostMapping(
            path = ("/shoppinglist"),
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public String addIngredient(@RequestBody TokenIngredient tokenIngredient) {

        Logger.getLogger("MappingController").log(Level.INFO,"MappingController POST /tasks "
                + tokenIngredient.getIngredients().getName());
        //analog zu addATask von Hartwig
        //Step 1: Check Token
        //Step 2: fetch shopping List von DB
        //Step 3: Add Ingredient zu der Shopping List
        listManager.addIngredients(tokenIngredient.getIngredients().getName(), tokenIngredient.getIngredients().getQuantity());
        //Step 4: send back shoppingList zu DB
        return "You added: " + tokenIngredient.getIngredients().getName() + " to your List! ";
    }

    /*
    Delete-Methode, die dem Nutzer ermöglicht seine
    Einkaufsliste zu löschen
     */
    @DeleteMapping(
            path = ("/shoppinglist"),
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public String deleteShoppinglist() {
        //Step 1: Check Token

        //Step 2: delete shopping List in der DB
        return "shoppingList got deleted";
    }
    @DeleteMapping("/shoppinglist/ingredient")

    public String deleteIngredient(@RequestParam int ingredient) {
        //Step 1: Check Token
        listManager.deleteIngredient(ingredient);
        //Step 2: delete specific ingredient from shopping List in der DB
        return "We deleted the following ingredient: " +  ingredient ;
    }

    @GetMapping("/create-list-table")
    public String createDBTable(@RequestParam(value = "token", defaultValue = "no-token") String token) {
        Logger.getLogger("MappingController")
                .log(Level.INFO,"MappingController create-list-table " + token);

        // Check token

        listManager.createListTable();

        return "ok";
    }

    //test1234

    @GetMapping("/create-user-table")
    public String createUserTable(@RequestParam(value = "token", defaultValue = "an dem liegts") String token) {
        Logger.getLogger("MappingController")
                .log(Level.INFO,"MappingController create-user-table " + token);

        // Check token

        userManager.createUserTable();

        return "UserTabelle erstellt";
    }

    //Alexa

    private AlexaRO prepareResponse(AlexaRO alexaRO, String outText, boolean shouldEndSession) {

        alexaRO.setRequest(null);
        alexaRO.setContext(null);
        alexaRO.setSession(null);
        OutputSpeechRO outputSpeechRO = new OutputSpeechRO();
        outputSpeechRO.setType("PlainText");
        outputSpeechRO.setText(outText);
        ResponseRO response = new ResponseRO(outputSpeechRO, shouldEndSession);
        alexaRO.setResponse(response);
        return alexaRO;
    }

    //TestAlexa
    @PostMapping(
            path = "/alexa",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public AlexaRO sendShoppingListToAlexa(@RequestBody AlexaRO alexaRO) {
        Logger.getLogger("MappingController").log(Level.INFO,"MappingController POST /alexa ");
        String outText = "";

        if (alexaRO.getRequest().getType().equalsIgnoreCase("LaunchRequest"))
            outText += "Welcome to the Student Food Planer. ";

        if (alexaRO.getRequest().getType().equalsIgnoreCase("IntentRequest")
                &&
                (alexaRO.getRequest().getIntent().getName().equalsIgnoreCase("ReadShoppingListIntent"))
        ) {
            outText += "You have the following shopping List. ";
            List<Ingredients> ingredients  = listManager.readAllIngredients();
            int i = 1;
            for (Ingredients t : ingredients) {
                outText += "Ingredient Number " + i + " : " + t.getName()
                        + " with quantity " + t.getQuantity() + " . ";
                i++;
            }
        }
        return
                prepareResponse(alexaRO, outText, true);
    }


}
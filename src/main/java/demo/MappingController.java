package demo;

import demo.data.api.*;
import demo.data.api.Ingredients;
import demo.data.api.ListManager;
import demo.data.api.User;
import demo.data.impl.*;

import demo.model.*;
import demo.model.Alexa.AlexaRO;
import demo.model.Alexa.OutputSpeechRO;
import demo.model.Alexa.ResponseRO;
import demo.model.ShoppingList;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1.0")
public class MappingController {

    UserManager userManager = PostgresDBUserManagerImpl.getPostgresDBUserManagerImpl();

    ListManager listManager = PostgresDBListManagerImpl.getPostgresDBListManagerImpl();

    RecipeManager recipeManager = PostgresDBRecipeManagerImpl.getPostgresDBRecipeManagerImpl();

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
    public String userLogin(@RequestBody UserImpl user) {
        //To-do: Check Verification of the token
        // Step 1: Check Token des User
      return  userManager.Login(user.getEmail(), user.getPasswort());
    }

    /*
    DELETE-Methode fuer den Logout eines Users im System
     */
    @DeleteMapping(
            path = ("/auth/logoff"),
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public String userLogoff(@RequestBody UserImpl user) {
        //Step 1: Token des Users löschen
        return userManager.Logout(user.getEmail());
        //Step 2: Ausgabe Log off war erfolgreich
    }

    /*
     POST-Methode, die es ermoeglicht, dass der User sich
     einmalig im System registrieren kann
     */
    @PostMapping(
            path = ("/user"),
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public String registerUser(@RequestBody TokenUser TokenUser) {

        String rs = userManager.createUser(TokenUser.getUser().getFirstName(),TokenUser.getUser().getLastName(), TokenUser.getUser().getUserPassword(), TokenUser.getUser().getUserEmail());

        return rs;
    }

    /*
     GET-Methode, die es ermoeglicht, dass alle User ausgegeben werden
     */
    @GetMapping("/user")
    public User getUserData(@RequestParam(value = "token", defaultValue = "no-token") String token){
        //Step 1: Check Token to the requested Data
        //Step 2: fetch data from DB
        //noch zu implementieren in der Zukunft
        //List<User> myUsers = userManager.readAllUsers();
        //Step 3: Ausgabe der Daten des Users
        return null;
    }

    /*
    DELETE-Methode, die es ermoeglicht, dass
    der Nutzer seinen Account loeschen kann
    */
    @DeleteMapping(
            path = ("/user"),
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public String deleteUser() {
        //Step 1: Check Token
        //Step 2: delete data in the DB from table users
        //Noch zu implementieren in der Zukunft
        return "User got deleted with the following name: Test";
    }

    /*
    GET-Methode, die es ermoeglicht, dass der Admin
    alle User mit ihren Daten angezeigt bekommt
    */
    @GetMapping("/user/all")
    public UserList getAllUsers(@RequestParam(value = "token", defaultValue = "no-token") String token) {

        Logger.getLogger("MappingController")
                .log(Level.INFO,"MappingController /user/all ");
            //Step 1: fetch data from DB
        List<User> usersfromDB = userManager.readAllUsers();
        List<demo.model.User> myUsers = new ArrayList<>();
            //Step 2: Ausgabe einer Liste mit den Usern und ihren Daten
        for(User user : usersfromDB) {
            myUsers.add(new demo.model.User(user.getEmail(),user.getPasswort(),
                    user.getLastName(), user.getFirstName(), user.getToken()));
        }
        return new UserList(myUsers);
    }

    /*
    GET-Methode, die dem Nutzer ermoeglicht, seine aktuelle
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
    Post-Methode, die dem Nutzer ermoeglicht, seine
    Einkaufsliste mit Zutaten zu füllen
     */
    @PostMapping(
            path = ("/shoppinglist"),
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public String addIngredient(@RequestBody TokenIngredient tokenIngredient) {

        Logger.getLogger("MappingController").log(Level.INFO,"MappingController POST /shoppinglist "
                + tokenIngredient.getIngredients().getName());
        //analog zu addATask von Hartwig
        //Step 1: Check Token
        //Step 2: fetch shopping List von DB
        //Step 3: Add Ingredient zur Shopping List
        listManager.addIngredients(tokenIngredient.getIngredients().getName(), tokenIngredient.getIngredients().getQuantity());
        //Step 4: send back shoppingList zu DB
        return "You added: " + tokenIngredient.getIngredients().getName() + " to your List! ";
    }

    /*
    Delete-Methode, die dem Nutzer ermoeglicht, seine
    Einkaufsliste zu loeschen
     */
    @DeleteMapping(
            path = ("/shoppinglist"),
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public String deleteShoppinglist() {
        //Step 1: Check Token
        //Step 2: delete shopping List in der DB
        //noch zu implementieren in der Zukunft
        return "shoppingList got deleted";
    }

    /*
    Delete-Methode, die dem Nutzer ermoeglicht, Zutaten aus der
    Einkaufsliste zu loeschen
     */
    @DeleteMapping("/shoppinglist/ingredient")
    public String deleteIngredient(@RequestBody TokenIngredient ingredient) {

        listManager.deleteIngredient(ingredient.getIngredients().getName());
        return "Following Ingredient deleted: " + ingredient.getIngredients().getName() ;
    }

    /*
    Post-Methode, die Rezepte in der Datenbank ablegt, damit diese
    im Kalender gespeichert bleiben
     */
    @PostMapping(
            path = ("/recipes"),
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public String saveRecipeInDB(@RequestBody TokenRecipe tokenRecipe){

        Logger.getLogger("MappingController").log(Level.INFO,"MappingController POST /recipes "
                + tokenRecipe.getRecipe().getName());

            recipeManager.addRecipes(tokenRecipe.getRecipe().getName(),tokenRecipe.getRecipe().getDate());

        return "Successful add of recipe: " + tokenRecipe.getRecipe().getName() +
                " and Date: " + tokenRecipe.getRecipe().getDate();
    }

    /*
    Get-Methode, um sich die Rezepte auszugeben
     */
    @GetMapping("/recipes")
    public RecipeList getRecipes(@RequestParam(value = "token", defaultValue = "no-token") String token) {

        Logger.getLogger("MappingController")
                .log(Level.INFO,"MappingController /recipes ");
        //Step 1: Check Token
        //Step 2: fetch shoppingList from DB
        List <demo.data.api.Recipe> recipesFromFile = recipeManager.readAllRecipes();
        List<demo.model.Recipe> myRecipes = new ArrayList<>();
        for (demo.data.api.Recipe t : recipesFromFile)
            myRecipes.add(new demo.model.Recipe(t.getRecipeName(),t.getDate()));
        // Step 3: Ausgabe analog Hartwig Tasks
        return new RecipeList(myRecipes);
    }

    /*
    Delete-Methode, um die Rezepte aus der Datenbank zu loeschen
     */
    @DeleteMapping("/recipes")
    public String deleteRecipe(@RequestBody TokenRecipe recipe) {

        recipeManager.deleteRecipe(recipe.getRecipe().getName(), recipe.getRecipe().getDate());
        return "Following recipe deleted: " + recipe.getRecipe().getName() + " on date: " +  recipe.getRecipe().getDate();
    }

    /*
    Get-Methode, um Einkaufslistentabelle zu erstellen
     */
    @GetMapping("/create-list-table")
    public String createDBTable(@RequestParam(value = "token", defaultValue = "no-token") String token) {
        Logger.getLogger("MappingController")
                .log(Level.INFO,"MappingController create-list-table " + token);

        listManager.createListTable();
        return "ok";
    }

    /*
    Get-Methode, um User-Tabelle zu erstellen
     */
    @GetMapping("/create-user-table")
    public String createUserTable(@RequestParam(value = "token", defaultValue = "no-token") String token) {
        Logger.getLogger("MappingController")
                .log(Level.INFO,"MappingController create-user-table " + token);

        userManager.createUserTable();
        return "UserTabelle erstellt";
    }

    /*
    Get-Methode, um Rezept-Tabelle zu erstellen
     */
    @GetMapping("/create-recipes-table")
    public String createRecipeTable(@RequestParam(value = "token", defaultValue = "no-token") String token) {
        Logger.getLogger("MappingController")
                .log(Level.INFO,"MappingController create-recipes-table " + token);

        recipeManager.createRecipeTable();
        return "RezeptTabelle erstellt";
    }

    //Alexa Implementation ab hier

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

    /*
    Post-Methode, damit Alexa den ReadAllIngredientsIntent ausfuehrt, und die Einkaufsliste ausgibt
     */
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
        return prepareResponse(alexaRO, outText, false);
    }
}
package demo;

import demo.data.api.*;
import demo.data.impl.*;

import demo.model.SendBackToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1.0")
public class MappingController {

    //sehr wichtig, nach API First Prinzip hier vorzugehen
    //Testen der HTTP:Resquest mit Prestige.dev und den passenden URL und HTTP-Aufrufe
    //folgender UserManager legt die User in der DB an und bearbeitet ihre Daten
    //UserManager userManager = PostgresDBUserManagerImpl.getPostgresDBUserManagerImpl();
    UserManager userManager = PropertyFileUserManagerImpl.getPropertyFileUserManagerImpl("src/main/resources/users.properties");
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
      für den Login eines Users in das System
     */
    @PostMapping(
            path = ("/auth/login"),
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public SendBackToken userLogin(@RequestBody User user) {
        //To-do: Check Verification of the token
        //Step 1: Check Token des Users
        //Step 2: Wie viele Sekunden soll dieser gelten ?

        return new SendBackToken("jhnaosvgioa gvi", 67978);
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
     POST-Methode, die es ermöglicht das der User sich
     einmalig im System registrieren kann
     */
    @PostMapping(
            path = ("/user"),
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public SendBackToken registerUser() {
            //Step 1: Token kreieren
            //Step 2: fetch data from Userregistrierung in die DB
            //Step 3: Token an User ausgeben mit validInSeconds
        return new SendBackToken("uavoiggpvagiv", 360);
    }

    /*
     GET-Methode, die es ermöglicht das der User
     seine Daten zurückbekommt
     */

    //Request-Param einzelne Parameter JSON

    //Request-Body - alles

    @GetMapping(
            path = ("/user"),
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public String getUserData(){
        //Step 1: Check Token to the requested Data
        String email = userManager.getEmailForToken("12345");
        //Step 2: fetch data from DB

        //Step 3: Ausgabe der Daten des Users
        return email;
    }

    /*
 DELETE-Methode, die es ermöglicht das
 der Nutzer seinen Account löschen kann
 */
    @DeleteMapping(
            path = ("/user"),
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public String deleteUser() {
        //Step 1: Check Token
        //Step 2: delete data in the DB from table users
        return "User got deleted with the follwing name: Test";
    }

    /*
 GET-Methode, die es ermöglicht das der Admin
 alle User mit ihren Daten angezeigt bekommt
 */
    @GetMapping(
            path = ("/user/all"),
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public String getAllUsers() {
            //Step 1: Check admin Rechte
            //Step 2: fetch data from DB
            //Step 3: Ausgabe einer Liste mit den Usern und ihren Daten
        return "Hallo, Test";
    }
    /*
    GET-Methode, die dem Nutzer ermöglicht seine aktuelle
    Einkaufsliste angezeigt zu bekommen
     */

    @GetMapping(
            path = ("/shoppinglist"),
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public String getShoppinglist() {
        //Step 1: Check Token
        //Step 2: fetch shoppingList from DB
        return "hier die Shoppinglist";
    }

    /*
    Put-Methode, die dem Nutzer ermöglicht seine
    Einkaufsliste mit Zutaten zu füllen
     */
    @PutMapping(
            path = ("/shoppinglist"),
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public String addIngredient() {
        //Step 1: Check Token
        //Step 2: fetch shopping List von DB
        //Step 3: Add Ingredient zu der Shopping List
        //Step 4: send back shoppingList zu DB
        return "Ingredient got added to the shopping List";
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
        //Step 1: Check Token +
        //Step 2: delete shopping List in der DB
        //Step 3: Ausgabe über Erfolg oder Miserfolg der Aktion
        return "shoppingList got deleted";
    }


}
package demo;

import demo.data.api.*;

import demo.model.SendBackToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.logging.Level;
import java.util.logging.Logger;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1.0")
public class MappingController {
    //Kommentar entfernen für Datenspeicherung in DB
    //UserManager userManager = PostgresDBUserManagerImpl.getPostgresDBUserManagerImpl();
    //sehr wichtig, nach API First Prinzip hier vorzugehen
    //ToDo: API_Design Tabelle aus GoogleDrive Ordner anschauen und demnach vorgehen
    // bzw. Methoden erstellen, bei Hartwig nachschauen wegen genauen Bezeichnungen
    //Testen der HTTP:Resquest mit Prestige.dev und den passenden URL und HTTP-Aufrufe
    @GetMapping("/auth")
    public String getInfo(@RequestParam(value = "name", defaultValue = "Student") String name) {
        Logger.getLogger("MappingController").log(Level.INFO,"MappingController auth " + name);
        return "ok";
    }
@PostMapping(
        path=("/auth/login"),
        consumes ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
)
public SendBackToken userLogin(@RequestBody User user){

    return new SendBackToken("jhnaosvgioa gvi",67978);
}

@DeleteMapping (
        path=("/auth/logoff"),
        consumes ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public String userLogoff(@RequestBody User user){

        return "Log off was succesfull";
    }


    @GetMapping(
            path = ("/user/all"),
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public String getAllUsers(){

    return "Hallo ich funktioniere";
    }

}

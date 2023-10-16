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

@PostMapping(
        path=("/auth/login"),
        consumes ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
)
public SendBackToken userLog(@RequestBody User user){

    return new SendBackToken("jhnaosvgioa gvi",67978);
}
}

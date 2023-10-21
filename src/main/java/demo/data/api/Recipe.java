package demo.data.api;

//Interface zum Aufrufen des Rezepts
//Soll Name und Zutaten aufrufen
//Muss noch mehr aufgerufen werden

public interface Recipe {

    //Rufe Name auf
    String getName();

    //Rufe Anzahl der Zutaten auf
    int getQuantityOfIngredients();
}

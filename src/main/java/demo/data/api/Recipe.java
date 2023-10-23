package demo.data.api;

//Interface zum Aufrufen des Rezepts
//Soll Name und Zutaten aufrufen
//Muss noch mehr aufgerufen werden

import java.util.List;

public interface Recipe {

    List<Ingredients> getIngredients();


}

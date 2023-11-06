package demo.data.impl;

import demo.data.api.Ingredients;
import demo.data.api.ShoppingList;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class ShoppingListImpl implements ShoppingList {

    List<Ingredients> ingredientsList = new ArrayList<>();
    int listID;
    @Override
    public String addIngredient(Ingredients ingredient) {
        ingredientsList.add(ingredient);
        return ingredient + " got added.";
    }

    @Override
    public String deleteIngredient(Ingredients ingredient) {
        //To-do: Iterator schreiben zum durchiterieren der Liste
        Iterator<Ingredients> iterator = ingredientsList.iterator();
        while (iterator.hasNext()) {
            Ingredients currentIngredient = iterator.next();
            if (currentIngredient.equals(ingredient)) {
                iterator.remove();
                return ingredient + "got deletetd.";

            }
        }
        return ingredient + "not found in the List.";
    }



    @Override
    public String editIngredients(Ingredients ingredient) {
        //To-do: edit Name and Quantity of Ingredients , not so important
        return null;
    }
}

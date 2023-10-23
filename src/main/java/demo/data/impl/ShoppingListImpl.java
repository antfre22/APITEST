package demo.data.impl;

import demo.data.api.Ingredients;
import demo.data.api.ShoppingList;

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
        return ingredient + " got deleted.";
    }

    @Override
    public String editIngredients(Ingredients ingredient) {
        //To-do: edit Name and Quantity of Ingredients , not so important
        return null;
    }
}

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

        return ingredient + " got deleted.";
    }

    @Override
    public String editIngredients(Ingredients ingredient) {
        return null;
    }
}

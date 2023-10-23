package demo.data.impl;

//Implementierungsklasse der Rezepte

import demo.data.api.Ingredients;
import demo.data.api.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeImpl implements Recipe {

    List<Ingredients> ingredientsList = new ArrayList<>();

    public RecipeImpl(List<Ingredients> ingredientList) {
        this.ingredientsList = ingredientList;
    }

    @Override
    public List<Ingredients> getIngredients() {
        return ingredientsList;
    }

}

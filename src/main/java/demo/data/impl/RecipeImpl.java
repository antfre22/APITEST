package demo.data.impl;

//Implementierungsklasse der Rezepte

import demo.data.api.Recipe;

public class RecipeImpl implements Recipe {

    private String name;

    private int NumberOfIngredients;

    public RecipeImpl(String name, int NumberOfIngredients) {
        this.name = name;
        this.NumberOfIngredients = NumberOfIngredients;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getNumberOfIngredients() {
        return NumberOfIngredients;
    }

    public void  setNumberOfIngredients(int NumberOfIngredients) {
        this.NumberOfIngredients = NumberOfIngredients;
    }
}

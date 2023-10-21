package demo.data.impl;

//Implementierungsklasse der Rezepte

import demo.data.api.Recipe;

public class RecipeImpl implements Recipe {

    private String name;

    private int QuantityOfIngredients;

    public RecipeImpl(String name, int NumberOfIngredients) {
        this.name = name;
        this.QuantityOfIngredients = NumberOfIngredients;
    }

    @Override
    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    @Override
    public int getQuantityOfIngredients() {

        return QuantityOfIngredients;
    }

    public void  setQuantityOfIngredients(int NumberOfIngredients) {

        this.QuantityOfIngredients = NumberOfIngredients;
    }
}

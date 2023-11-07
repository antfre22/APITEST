package demo.data.impl;

import java.util.Calendar;
import java.util.Date;

import demo.data.api.Recipe;
public class RecipeImpl implements Recipe{
    String recipeName;
    Date recipeDate;

    public RecipeImpl(String recipeName, Date recipeDate){
        this.recipeName = recipeName;
        this.recipeDate = recipeDate;
    }

    @Override
    public String getRecipeName() {
        return recipeName;
    }

    @Override
    public Date getDate() {
        return recipeDate;
    }


}

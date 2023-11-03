package demo.data.api;

import java.util.List;

public interface RecipeManager {

    List<Recipe> readAllRecipes();
    void addRecipes(Recipe recipe);
    void createRecipeTable();
}

package demo.data.api;

import java.util.List;
import java.util.Date;

public interface RecipeManager {

    List<Recipe> readAllRecipes();

    void addRecipes(String recipeName, Date datum);

    void createRecipeTable();

    void deleteRecipe(String name, Date date);
}

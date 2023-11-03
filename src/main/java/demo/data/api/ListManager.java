package demo.data.api;

import java.util.List;

public interface ListManager {
    List<Ingredients> readAllIngredients();
    void addIngredients(String name, float quantity);
     void createListTable();
    void createRecipeTable();
     void deleteIngredient(int ingredient);


}

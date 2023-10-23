package demo.data.api;

import java.util.List;

public interface ListManager {
    List<Ingredients> readAllIngredients();
    void addIngredients(Ingredients ingredients);
     void createListTable();
}

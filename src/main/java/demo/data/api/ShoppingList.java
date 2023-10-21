package demo.data.api;

public interface ShoppingList {
   // eine Zutat zur ShoppingList adden
    String addIngredient();
    // eine Zutat von der ShoppingList deleten
    String deleteIngredient();
    // ShoppingList bearbeiten (explizit Zutaten)
    String editIngredients();
}

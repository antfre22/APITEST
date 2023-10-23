package demo.data.api;

public interface ShoppingList {
   // eine Zutat zur ShoppingList adden
    String addIngredient(Ingredients ingredient);
    // eine Zutat von der ShoppingList deleten
    String deleteIngredient(Ingredients ingredient);
    // ShoppingList bearbeiten (explizit Zutaten)
    String editIngredients(Ingredients ingredient);
}

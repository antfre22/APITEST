package demo.model.Ingredients;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import demo.model.Ingredients.Ingredients;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingList {

    private Collection<Ingredients> Ingredient;

    private String name;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public ShoppingList() { }

    public ShoppingList(Collection<Ingredients> Ingr) {
        this.Ingredient = Ingr;
    }

    public Collection<Ingredients> getIngredients() {
        return Ingredient;
    }

    public void setIngredients(Collection<Ingredients> Ingr) {
        this.Ingredient = Ingr;
    }

    public String getName() {
        return name;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}













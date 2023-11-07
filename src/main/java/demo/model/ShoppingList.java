package demo.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingList {

    private Collection<demo.model.Ingredients> Ingredient;

    private String name;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public ShoppingList() { }

    public ShoppingList(Collection<demo.model.Ingredients> Ingr) {
        this.Ingredient = Ingr;
    }

    public Collection<demo.model.Ingredients> getIngredients() {
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













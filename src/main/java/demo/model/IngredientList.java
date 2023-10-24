package demo.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import demo.data.api.Ingredients;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class IngredientList {

    private Collection<Ingredients> Ingredient;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public IngredientList() { }

    public IngredientList(Collection<Ingredients> Ingr) {
        this.Ingredient = Ingr;
    }

    public Collection<Ingredients> getIngredients() {
        return Ingredient;
    }

    public void setIngredients(Collection<Ingredients> Ingr) {
        this.Ingredient = Ingr;
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













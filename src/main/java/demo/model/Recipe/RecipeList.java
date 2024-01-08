package demo.model.Recipe;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import demo.model.Recipe.Recipe;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RecipeList {

    private Collection<Recipe> recipes;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public RecipeList() { }

    public RecipeList(Collection<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Collection<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Collection<Recipe> recipes) {
        this.recipes = recipes;
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



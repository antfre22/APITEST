package demo.model.Recipe;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import demo.model.Recipe.Recipe;

import java.util.HashMap;
import java.util.Map;

public class TokenRecipe {

    private Recipe recipe;

    private String token;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public TokenRecipe(){}

    public TokenRecipe(Recipe recipe, String token) {
        this.recipe = recipe;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
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

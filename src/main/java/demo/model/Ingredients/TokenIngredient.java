package demo.model.Ingredients;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import demo.model.Ingredients.Ingredients;

import java.util.HashMap;
import java.util.Map;
public class TokenIngredient {

    private String token;

    private Ingredients ingredients;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public TokenIngredient() {
    }

    public TokenIngredient(Ingredients ingredients, String token) {
        this.ingredients = ingredients;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
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


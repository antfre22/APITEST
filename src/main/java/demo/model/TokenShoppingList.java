package demo.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

public class TokenShoppingList {

    private String token;
    private demo.model.ShoppingList shoppingList;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public TokenShoppingList() {
    }

    public TokenShoppingList(demo.model.ShoppingList shoppingList, String token) {
        this.shoppingList = shoppingList;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public demo.model.ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(demo.model.ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
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


package demo.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
public class UserList {

    private Collection<User> users;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public UserList() { }

    public UserList(Collection<User> users) {

        this.users = users;
    }

    public Collection<User> getUsers() {

        return users;
    }


    public void setUsers(Collection<User> users) {
        this.users = users;
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

package demo.data.impl;

import demo.data.api.Ingredients;

public class IngredientsImpl implements Ingredients {

    String name;
    float quantity;

    public IngredientsImpl(String name, float quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName(){
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }


    public float getQuantity(){
        return quantity;
    }

    @Override
    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }


}

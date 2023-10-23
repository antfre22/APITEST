package demo.data.impl;

import demo.data.api.Ingredients;

public class IngredientsImpl implements Ingredients {

    String name;
    int quantity;

    public IngredientsImpl(String name, int quantity) {
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


    public int getQuantity(){
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}

package com.kroger.fastcheckout;

import java.util.ArrayList;

public class Cart {

    private ArrayList<Product> cartItems = new ArrayList<Product>();

    public Product getProducts(int position){
        return cartItems.get(position);
    }

    public void setProducts(Product Products){
        cartItems.add(Products);
    }

    public int getCartsize(){
        return cartItems.size();
    }

    public boolean CheckProductInCart(Product product){
        return cartItems.contains(product);
    }
}

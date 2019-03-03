package com.kroger.fastcheckout;

import android.app.Application;

import java.util.ArrayList;

public class Controller extends Application {

    private ArrayList<Product> myproducts = new ArrayList<Product>();

    private Cart myCart = new Cart();

    public Product getProducts(int pPosition){
        return myproducts.get(pPosition);
    }

    public void addProducts(Product products){
        myproducts.add(products);
    }

    public Cart getCart(){
        return myCart;
    }

    public int  getProductArraylistsize(){
        return myproducts.size();
    }
}

package com.kroger.fastcheckout;

import android.app.Application;

import java.util.ArrayList;

public class Controller extends Application {

    private ArrayList<Product> myproducts = new ArrayList<Product>();

    public Product getProducts(int pPosition){
        return myproducts.get(pPosition);
    }

    public void addProducts(Product products){
        myproducts.add(products);
    }

    public int  getProductArraylistsize(){
        return myproducts.size();
    }

    public int getGrandTotal()
    {
        int total = 0;
        for(int i=0; i < myproducts.size(); i++)
        {
            total += myproducts.get(i).getPrice() * myproducts.get(i).getQuantity();
        }
        return total;
    }

}

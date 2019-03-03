package com.kroger.fastcheckout;

import android.support.annotation.Nullable;

import java.io.Serializable;

public class Product implements Serializable {
    private String serialNumber;
    private String productName;
    private String productDesc;
    private int productPrice;
    private int quantity;

    public Product(String productName,String productDesc,int productPrice, String serialNumber){
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
        this.serialNumber = serialNumber;
    }

    public Product(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName(){
        return productName;
    }

    public String getDesc(){
        return productDesc;
    }

    public int getPrice(){
        return productPrice;
    }

    public String getSerialNumber(){
        return serialNumber;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public void setQuantity(String quantity) {
        this.quantity = Integer.parseInt(quantity);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Product.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Product other = (Product) obj;
        if ((this.serialNumber == null) ? (other.serialNumber != null) : !this.serialNumber.equals(other.serialNumber)) {
            return false;
        }

        return true;
    }
}

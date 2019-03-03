package com.kroger.fastcheckout;

import java.util.ArrayList;
import java.util.List;

public class DummyDataHelper {

    public List<Product> getStoredProducts() {
        ArrayList<Product> storedProducts = new ArrayList<Product>();
        storedProducts.add(new Product("Product Item", "Description", 11, "111"));
        storedProducts.add(new Product("Product Item", "Description", 11, "222"));
        storedProducts.add(new Product("Hershey - Mlk Almd", "Hersheys Milk Choclate With Almond", 1, "03424102"));
        storedProducts.add(new Product("Product Item", "Description", 11, "444"));
        storedProducts.add(new Product("Product Item", "Description", 11, "555"));
        storedProducts.add(new Product("Product Item", "Description", 11, "666"));
        return storedProducts;
    }

    public Product GetProductFromLookup(String barcodeString) {
        Product product = null;

        int index = getStoredProducts().indexOf(new Product(barcodeString));
        if(index != -1) {
            product = getStoredProducts().get(index);
        }

        return product;
    }
}

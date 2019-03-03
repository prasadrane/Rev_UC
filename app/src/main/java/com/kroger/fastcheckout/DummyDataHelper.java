package com.kroger.fastcheckout;

import java.util.ArrayList;
import java.util.List;

public class DummyDataHelper {

    public List<Product> getStoredProducts() {
        ArrayList<Product> storedProducts = new ArrayList<Product>();
        storedProducts.add(new Product("Doritos", "Nacho Cheese Chips", 2, "028400090896"));
        storedProducts.add(new Product("Pretzel Rld Gld", "Pretzel Rold Gold", 3, "028400040068"));
        storedProducts.add(new Product("Hershey - Milk Almd", "Hersheys Milk Choclate With Almond", 1, "03424102"));
        storedProducts.add(new Product("Monster Energy", "Monster Energy B-Vitamin", 2, "070847811169"));
        storedProducts.add(new Product("Krgr Grape Tomatoes", "Kroger Grape Tomatoes", 5, "011110916860"));
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

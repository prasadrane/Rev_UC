package com.kroger.fastcheckout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProductDetails extends AppCompatActivity {

    Product productToAdd;

    TextView txtName;
    TextView txtDescription;
    TextView txtPrice;
    EditText txtQuantity;
    Button btnMinusQuantity;
    Button btnPlusQuantity;
    Button btnCancel;
    Button btnAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        // Initialize controls
        txtName = findViewById(R.id.txtProdName);
        txtDescription = findViewById(R.id.txtProdDescription);
        txtPrice = findViewById(R.id.txtProdPrice);
        txtQuantity = findViewById(R.id.editTxtProdQuantity);

        //Get the product from intent bundle
        productToAdd = (Product) getIntent().getSerializableExtra("Product");

        // Set values on UI
        if(productToAdd != null){
            txtName.setText(productToAdd.getName());
            txtDescription.setText(productToAdd.getDesc());
            txtPrice.setText("" +productToAdd.getPrice());
            txtQuantity.setText("1"); // Default one quantity
        }

        // Button Clicks
        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Returning to scanning activity
                ContinueScanning();
            }
        });

        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddToCart();
            }
        });

        btnMinusQuantity = findViewById(R.id.btnMinus);
        btnMinusQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecrementQuantity();
            }
        });

        btnPlusQuantity = findViewById(R.id.btnPlus);
        btnPlusQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IncrementQuantity();
            }
        });

    }

    public void ContinueScanning() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void ShowCart() {
        Intent intent = new Intent(this, MainCart.class);
        startActivity(intent);
    }

    public void AddToCart()
    {
        // Update product quantity
        productToAdd.setQuantity(txtQuantity.getText().toString());
        Controller ct = (Controller) getApplicationContext();
        ct.addProducts(productToAdd);

        ShowCart();

    }

    public void DecrementQuantity () {
        String quantity = txtQuantity.getText().toString();

        int originalQuantity = Integer.parseInt(quantity);

        if (originalQuantity>0)
        originalQuantity--;

        txtQuantity.setText(""+originalQuantity);
    }

    public void IncrementQuantity () {
        String quantity = txtQuantity.getText().toString();

        int originalQuantity = Integer.parseInt(quantity);

        originalQuantity++;

        txtQuantity.setText(""+originalQuantity);
    }
}

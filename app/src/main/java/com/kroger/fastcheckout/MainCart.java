package com.kroger.fastcheckout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainCart extends Activity {




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LinearLayout layout = (LinearLayout) findViewById(R.id.linearMain);
        final Button btn = (Button)findViewById(R.id.second);
        final Controller ct = (Controller) getApplicationContext();
        Product products = null;
        for(int i= 1; i<=7;i++){
            int Price = 15+ i;
            products = new Product("Product Item" +i, "Description"+i, Price, "322"+i);
            ct.addProducts(products);
        }
        int productsize = ct.getProductArraylistsize();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        for (int j=0;j< productsize;j++){
            String pName = ct.getProducts(j).getName();
            int pPrice = ct.getProducts(j).getPrice();
            LinearLayout la = new LinearLayout(this);
            la.setOrientation(LinearLayout.HORIZONTAL);
            TextView tv = new TextView(this);
            tv.setText(" "+pName+" ");
            la.addView(tv);
            TextView tv1 = new TextView(this);
            tv1.setText("$"+pPrice+" ");
            la.addView(tv1);

            EditText quant = new EditText(this);
            quant.setId(j);
            quant.setText("1");
            la.addView(quant);
            /*final Button btn1 = new Button(this);
            btn1.setId(j+1);
            btn1.setText("Add to Cart");
            btn1.setLayoutParams(params);*/
            final int index = j;
            /*btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.i("TAG", "index:"+index);
                    ModelProducts productsObject = ct.getProducts(index);
                    if(!ct.getCart().CheckProductInCart(productsObject)){
                        btn1.setText("Item Added");
                        ct.getCart().setProducts(productsObject);
                        Toast.makeText(getApplicationContext(), "New CartSize:" +ct.getCart().getCartsize(),Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Products"+(index+1)+"Already Added",Toast.LENGTH_LONG ).show();
                    }
                }
            });
            la.addView(btn1)*/;
            layout.addView(la);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getBaseContext(),QRCode.class);
                startActivity(in);
            }
        });
    }


}

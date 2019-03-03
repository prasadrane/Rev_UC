package com.kroger.fastcheckout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainCart extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cart);
        final LinearLayout layout = (LinearLayout) findViewById(R.id.linearMain);
        final Button btn = (Button) findViewById(R.id.second);
        final Button btnScanAgain = (Button) findViewById(R.id.scanAgain);
        final Controller ct = (Controller) getApplicationContext();

        int productsize = ct.getProductArraylistsize();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (int j = 0; j < productsize; j++) {
            String pName = ct.getProducts(j).getName();
            int pPrice = ct.getProducts(j).getPrice();
            LinearLayout la = new LinearLayout(this);
            la.setOrientation(LinearLayout.HORIZONTAL);
            TextView tv = new TextView(this);
            tv.setText(" " + pName + " ");
            la.addView(tv);
            TextView tv1 = new TextView(this);
            tv1.setText("$" + pPrice + " ");
            la.addView(tv1);

            EditText quant = new EditText(this);
            quant.setId(j);
            quant.setText("" + ct.getProducts(j).getQuantity() + "");
            la.addView(quant);
            final int index = j;
            TextView txtSubTotal = new TextView(this);
            txtSubTotal.setTextSize(18);
            txtSubTotal.setText(" Sub Total: $" + pPrice * ct.getProducts(j).getQuantity() + " ");
            la.addView(txtSubTotal);
            layout.addView(la);
        }

        // Display Grand Total
        LinearLayout grandTotalLayout = new LinearLayout(this);
        grandTotalLayout.setOrientation(LinearLayout.HORIZONTAL);
        TextView txtGrandTotal = new TextView(this);
        txtGrandTotal.setTextSize(35);
        txtGrandTotal.setText(" Grand Total: $" + ct.getGrandTotal() + " ");
        grandTotalLayout.addView(txtGrandTotal);
        layout.addView(grandTotalLayout);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getBaseContext(), QRCode.class);
                startActivity(in);
            }
        });

        btnScanAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getBaseContext(), MainActivity.class);
                startActivity(in);
            }
        });
    }
}

package com.kroger.fastcheckout;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        Controller ct = (Controller) getApplicationContext();
        String qr = "";

        if(ct.getProductArraylistsize() == 0){
            qr = "null";
        }
        else{
            for(int i = 0; i < ct.getProductArraylistsize(); i++){
                Product p = ct.getProducts(i);
                qr += p.getSerialNumber();
                qr += " ";
                qr += p.getQuantity();
                qr += ",";
            }
        }

        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            // generate a 150x150 QR code
            BitMatrix bm = qrCodeWriter.encode(qr, BarcodeFormat.QR_CODE, 300, 300);

            Bitmap bmp = Bitmap.createBitmap(300, 300, Bitmap.Config.RGB_565);
            for (int x = 0; x < 300; x++) {
                for (int y = 0; y < 300; y++) {
                    bmp.setPixel(x, y, bm.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            ImageView img = (ImageView) findViewById(R.id.imageView);
            if (bm != null) {
                img.setImageBitmap(bmp);
            }
        } catch (WriterException e) {
            System.out.print(""+e.getMessage());
        }
    }
}
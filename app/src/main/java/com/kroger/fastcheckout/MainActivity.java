package com.kroger.fastcheckout;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class MainActivity extends Activity {

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCameraPermissionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        cameraSource.start(cameraPreview.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    boolean productDetailsCartOpen;
    SurfaceView cameraPreview;
    TextView txtError;
    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;
    Button btnCart;
    final int RequestCameraPermissionID = 1001;
    int retryCounter = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        productDetailsCartOpen = false;
        retryCounter = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCart = findViewById(R.id.btnCart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCart();
            }
        });
        cameraPreview = (SurfaceView) findViewById(R.id.cameraPreview);

        txtError = (TextView) findViewById(R.id.txtError);


        barcodeDetector = new BarcodeDetector.Builder(this)
                //.setBarcodeFormats(Barcode.QR_CODE)
                .build();

        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 580)
                .build();

        // Add Event
        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // Request Permission
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);
                    return;
                }
                try {
                    cameraSource.start(cameraPreview.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });


        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {

                final SparseArray<Barcode> qrcode = detections.getDetectedItems();
                if (qrcode.size() != 0) {
                    txtError.post(new Runnable() {
                        @Override
                        public void run() {
                            // Create Vibration
                            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(1000);

                            // Navigate to Product details page for "Add to cart"
                            String barcodeString = qrcode.valueAt(0).displayValue;
                            DummyDataHelper dummyDataHelper = new DummyDataHelper();
                            Product product = dummyDataHelper.GetProductFromLookup(barcodeString);
                            if (product != null) {
                                openCart(product);
                            }
                            else{

                                txtError.setText("Item Not Found!");
                            }
                        }
                    });
                }
                // Wait for sometime for stabilize
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void goToCart()
    {
        Intent intent = new Intent(this, MainCart.class);
        startActivity(intent);
    }

    public void openCart(Product product) {

        if(!productDetailsCartOpen) {
            productDetailsCartOpen = true;
            Intent intent = new Intent(this, ProductDetails.class);

            //Add the bundle to the intent
            intent.putExtra("Product", product);

            startActivity(intent);
            //cameraSource.stop();
        }
    }
}

package com.example.vegeshop;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.mlkit.vision.common.InputImage;
import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

public class CameraActivity extends AppCompatActivity implements IRecognizeBarcodeListener{

    CameraView cameraView;
    Button btnCheck;
    BarcodeProcessor barcodeProcessor;

    Boolean hasRecognizedImage = false;


    @Override
    protected void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraView.stop();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        cameraView  =  (CameraView)findViewById(R.id.cameraView);
        btnCheck = (Button)findViewById(R.id.btnCheck);
        barcodeProcessor = new BarcodeProcessor();

        barcodeProcessor.AddListener(this);


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraView.start();
                cameraView.captureImage();
            }
        });

        cameraView.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {

            }

            @Override
            public void onError(CameraKitError cameraKitError) {

            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {
                Bitmap bitmap = cameraKitImage.getBitmap();
                bitmap = Bitmap.createScaledBitmap(bitmap,  cameraView.getWidth(),  cameraView.getHeight(), false);
                barcodeProcessor.scanBarcodes(InputImage.fromBitmap(bitmap, 0));
                cameraView.stop();
            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {

            }
        });
    }

    @Override
    public void onRecognizeBarcode(String ID) {
        hasRecognizedImage = true;
        Intent intent = new Intent(CameraActivity.this, ResultActivity.class);
        intent.putExtra("ProductID", ID);
        startActivity(intent);
    }

    @Override
    public void onFinishedProcessingBarcode() {
        if(!hasRecognizedImage) {
            cameraView.start();
            Toast.makeText(
                    CameraActivity.this,
                    "Scan failed. Please try again",
                    Toast.LENGTH_LONG)
                    .show();
        }
    }
}
package com.example.ankita.intellichef;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.AWSStartupHandler;
import com.amazonaws.mobile.client.AWSStartupResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager viewPager;
    int images[] = {R.drawable.dosa, R.drawable.pizza, R.drawable.salad, R.drawable.soup};
    MyCustomPagerAdapter myCustomPagerAdapter;
    private static  Button cameraButton, chatButtton;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AWSMobileClient.getInstance().initialize(this, new AWSStartupHandler() {
            @Override
            public void onComplete(AWSStartupResult awsStartupResult) {
                Log.d("YourMainActivity", "AWSMobileClient is instantiated and you are connected to AWS!");
            }
        }).execute();


        viewPager = (ViewPager)findViewById(R.id.viewPager);

        myCustomPagerAdapter = new MyCustomPagerAdapter(MainActivity.this, images);
        viewPager.setAdapter(myCustomPagerAdapter);
        // More onCreate code ...
        cameraButton = findViewById(R.id.camera);
        chatButtton =findViewById(R.id.chat);

        cameraButton.setOnClickListener(this);
        chatButtton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == cameraButton) {
//            Toast.makeText(this, "camera button clicked", Toast.LENGTH_LONG).show();
//            Intent intent = new Intent();

//            intent.setType("image/*");
//
//            intent.setAction(Intent.ACTION_GET_CONTENT);
//
//            startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);

//            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");


//            startActivityForResult(intent, CAMERA_REQUEST);

            if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        MY_CAMERA_PERMISSION_CODE);
            } else {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        }
        if (view == chatButtton) {
            Toast.makeText(this, "chat button clicked", Toast.LENGTH_LONG).show();
//            Intent i = new Intent(MainActivity.this, ImageDisplay.class);
//            startActivity(i);
        }
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            imageView.setImageBitmap(photo);
            Intent i = new Intent(MainActivity.this, ImageDisplay.class);
        i.putExtra("data", photo);
        startActivity(i);
        }
//        System.out.print("hello");
//        Intent i = new Intent(MainActivity.this, ImageDisplay.class);
//        i.putExtra("data", data);
//        startActivity(i);
    }
}

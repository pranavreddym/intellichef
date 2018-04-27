package com.example.ankita.intellichef;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by panny on 26-04-2018.
 */

public class ImageDisplay extends AppCompatActivity {
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagedisplay);
        Bundle bundle = getIntent().getExtras();
        img = findViewById(R.id.imagedisplay);
//Extract the data…
        Bitmap photo = getIntent().getExtras().getParcelable("data");
        img.setImageBitmap(photo);
        }


}


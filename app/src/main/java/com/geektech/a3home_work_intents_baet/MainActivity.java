package com.geektech.a3home_work_intents_baet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    TextView textView;
    ImageView imageView;
    Bitmap bitmap;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textViewFirstActivity);
        imageView = findViewById(R.id.imageViewFirstActivity);

        textView.setText(getIntent().getStringExtra("message"));
        bitmap = ActivityTwo.bitmap;
        imageView.setImageBitmap(bitmap);


    }

    @SuppressLint("IntentReset")
    public void onClick(View view) {

        composeEmail(new String[]{"toktonbekovbaet@gmail.com", "microsoftbaet@gmail.com", "baet.toktonbekov@mail.ru"},
                "new subject", textView.getText().toString());

    }

    @SuppressLint({"QueryPermissionsNeeded", "IntentReset"})
    public void composeEmail(String[] addresses, String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Send email using..."));
        }
    }

    public void onClickOpen(View view) {
        Intent intent = new Intent(this, ActivityTwo.class);
        startActivity(intent);

    }
}
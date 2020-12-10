package com.geektech.a3home_work_intents_baet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class ActivityTwo extends AppCompatActivity {

    public static final int PICK_IMAGE = 100;
    public static Bitmap bitmap;


    ImageView imageView;
    EditText editText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        imageView = findViewById(R.id.imageViewActivityTwo);
        editText = findViewById(R.id.etTextActivityTwo);

    }

    @SuppressLint({"IntentReset", "SetTextI18n"})
    public void clickImage(View view) {
        pickImageFromGallery();
    }

    private void switchTextDataToMainActivity() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        switchActivityIntent.putExtra("message", editText.getText().toString());
        startActivity(switchActivityIntent);
    }

    @SuppressLint("IntentReset")
    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE && data != null) {
            Uri imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                switchTextDataToMainActivity();
            } catch (IOException e) {
                Toast.makeText(this, "Something went wrong! \uD83E\uDD7A", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

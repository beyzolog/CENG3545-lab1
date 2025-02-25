package com.example.mobilelecture3;

import static android.provider.MediaStore.Images.Media.getBitmap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PostAtivity extends AppCompatActivity {
    ImageView img;
    TextView txtMessage;
    static final int CAPTURE_IMAGE = 0;
    ImageButton btnOk;
    ImageButton btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        txtMessage = findViewById(R.id.txtMessage);
        img = findViewById(R.id.imageView);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAPTURE_IMAGE);
            }
        });

        btnOk = findViewById(R.id.btnOk);
        btnCancel = findViewById(R.id.btnCancel);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();   // resimle mesajı mainviewdaki liste göndereceğiz
                bundle.putParcelable("bitmap", ((BitmapDrawable)img.getDrawable()).getBitmap());
                bundle.putCharSequence("msg", txtMessage.getText());
                intent.putExtras(bundle);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CAPTURE_IMAGE && resultCode == AppCompatActivity.RESULT_OK){
            Bundle bundle = data.getExtras(); // for represent data
            Bitmap image = (Bitmap) bundle.get("data");
            img.setImageBitmap(image);

        }
    }
}
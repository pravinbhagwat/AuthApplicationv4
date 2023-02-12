package com.example.authapplicationv4.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.authapplicationv4.R;
import com.google.android.material.button.MaterialButton;

public class PushNotification extends AppCompatActivity {

   private  MaterialButton button_notifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notification);

        button_notifi = findViewById(R.id.button_notification);

        button_notifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
package com.example.authapplicationv4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.authapplicationv4.firebase.PushNotification;
import com.example.authapplicationv4.sqlitedb.SignIn;
import com.example.authapplicationv4.sqlitedb.SignUp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(getApplicationContext(), PushNotification.class);
        startActivity(intent);
    }
}
package com.example.authapplicationv4.sqlitedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.authapplicationv4.MainActivity;
import com.example.authapplicationv4.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

public class SignIn extends AppCompatActivity {

    private TextInputEditText usernameText, passwordText;
    private MaterialButton signInButton;
    private MaterialTextView signUpTextView;
    private ShapeableImageView imageViewGoogle, imageViewFacebook;

    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usernameText = findViewById(R.id.textInput_emailId);
        passwordText = findViewById(R.id.textInput_password);
        signInButton = findViewById(R.id.button_signIn);
        signUpTextView = findViewById(R.id.button_signUp);
        imageViewGoogle = findViewById(R.id.imageView_google);
        imageViewFacebook = findViewById(R.id.imageView_facebook);

        //db
        dbHandler = new DBHandler(SignIn.this);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                Boolean ans = dbHandler.signInValidate(email, password);
                if(ans){
                    Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
                }
            }
        });

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });
    }
}
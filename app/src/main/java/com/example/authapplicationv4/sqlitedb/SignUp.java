package com.example.authapplicationv4.sqlitedb;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.authapplicationv4.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.io.FileInputStream;
import java.sql.Array;
import java.util.ArrayList;

public class SignUp extends AppCompatActivity {

    TextInputEditText firstnameInput, lastnameInput, emailInput, mobileInput, passwordInput, confirmPassInput;

    private ShapeableImageView imageViewProfile;
    private MaterialButton buttonProfile, buttonSignUp;
    private MaterialAutoCompleteTextView autoTextDOB;
    private MaterialAutoCompleteTextView autoTextGender;
    private MaterialAutoCompleteTextView autoTextLanguages;
    private MaterialTextView signInTextView;

    //Db
    private DBHandler dbHandler;

    private String[] languagesArray = new String[]{
            "English", "Hindi", "Marathi", "Spanish", "Russian", "Japanese", "German", "Chinese"
    };

    private String[] genderArray = new String[]{
            "Male", "Female", "Transgender"
    };

    ActivityResultLauncher<String> mTakePhoto;
    ActivityResultLauncher<Intent> CTakePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        imageViewProfile = findViewById(R.id.imageView_profile);
        buttonProfile = findViewById(R.id.button_uploadProfile);
        firstnameInput = findViewById(R.id.editText_fname);
        lastnameInput = findViewById(R.id.editText_lname);
        emailInput = findViewById(R.id.editText_email);
        mobileInput = findViewById(R.id.editText_mobile);
        autoTextDOB = findViewById(R.id.autoComplete_dob);
        autoTextGender = findViewById(R.id.autoComplete_gender);
        autoTextLanguages = findViewById(R.id.autoComplete_language);
        passwordInput = findViewById(R.id.editText_password);
        confirmPassInput = findViewById(R.id.editText_confirmPass);
        buttonSignUp = findViewById(R.id.button_signUp);
        signInTextView = findViewById(R.id.button_signIn);

        //db
        dbHandler = new DBHandler(SignUp.this);

        //DatePicker for DOB
        MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date").setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();
        autoTextDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show(getSupportFragmentManager(), "Material_Date_Picker");
                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        autoTextDOB.setText(datePicker.getHeaderText());
                    }
                });
            }
        });

//        Selecting img from gallery
        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTakePhoto.launch("image/*");
            }
        });

        // Selecting Image from Gallery
        mTakePhoto = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                imageViewProfile.setImageURI(result);
            }
        });

        //ArrayAdapter for Language
        ArrayAdapter<String> adapterLanguage = new ArrayAdapter<>(
                SignUp.this,
                R.layout.dropdown_item_menu,
                languagesArray
        );

        //ArrayAdapter for Gender
        autoTextLanguages.setAdapter(adapterLanguage);

        ArrayAdapter<String> adapterGender = new ArrayAdapter<>(
                SignUp.this,
                R.layout.dropdown_item_menu,
                genderArray
        );

        autoTextGender.setAdapter(adapterGender);

        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register register = getData();
                dbHandler.saveRegistration(register);
                Toast.makeText(SignUp.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public Register getData() {
//        FileInputStream fis = new FileInputStream(Path);
//        byte[] image= new byte[fis.available()];
//        fis.read(image);


        String fname = firstnameInput.getText().toString();
        String lname = lastnameInput.getText().toString();
        String email = emailInput.getText().toString();
        String mobile = mobileInput.getText().toString();
        String dob = autoTextDOB.getText().toString();
        String gender = autoTextGender.getText().toString();
        String language = autoTextLanguages.getText().toString();
        String password = passwordInput.getText().toString();

        Register register = new Register(fname, lname, email, mobile, dob, gender, language, password);
        return register;
    }
}
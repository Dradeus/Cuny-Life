package com.example.rachid.myapplication;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;

public class LogInActivity extends AppCompatActivity{


    private EditText logInEmail;
    private EditText logInPassWord;
    private FirebaseAuthException mAuth;
    private DatabaseReference mDatabase;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}

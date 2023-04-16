package com.example.pairresearch;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pairresearch.models.enums.UserType;

public class App extends AppCompatActivity {
    public static UserType currentUserType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

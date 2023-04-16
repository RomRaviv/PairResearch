package com.example.pairresearch.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.pairresearch.R;
import com.example.pairresearch.models.DataManager;

import java.nio.charset.StandardCharsets;

public class LoginActivity extends AppCompatActivity {
    private DataManager dataManager = DataManager.getInstance(this);
    private EditText edit_text_email;
    private EditText edit_text_password;
    private TextView text_view_register;
    private TextView text_view_forgot_password;
    private Button button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();
        initViews();

    }

    private void initViews() {
        //Press login
        button_login.setOnClickListener(view -> {
            String email = edit_text_email.getText().toString();
            String password = edit_text_password.getText().toString();
            dataManager.login(email, password, response -> {
                // Handle successful login
                Intent intent = new Intent(LoginActivity.this, NavigationMenuActivity.class);
                startActivity(intent);
                finish();

            }, error -> {
                // Handle login error
                String errorMessage = "Oops! Something didn't match. Try again!";
//                if (error.networkResponse != null && error.networkResponse.data != null) {
//                    errorMessage = new String(error.networkResponse.data, StandardCharsets.UTF_8);
//                }
                Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                edit_text_email.setText("");
                edit_text_password.setText("");
            });

        });
        text_view_register.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
        text_view_forgot_password.setOnClickListener(view -> {
//            Intent intent = new Intent(LoginActivity.this, NavigationMenuActivity.class);
//            startActivity(intent);
//            finish();
        });

    }

    public void findViews(){
        edit_text_email = findViewById(R.id.edit_text_password);
        edit_text_password = findViewById(R.id.edit_text_password);
        button_login = findViewById(R.id.button_login);
        text_view_register = findViewById(R.id.text_view_register);
        text_view_forgot_password = findViewById(R.id.text_view_forgot_password);
    }
}
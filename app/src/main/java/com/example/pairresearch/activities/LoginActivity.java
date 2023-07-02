package com.example.pairresearch.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
 import android.widget.RadioGroup;
 import android.widget.TextView;
import android.widget.Toast;

import com.example.pairresearch.App;
import com.example.pairresearch.R;
 import com.example.pairresearch.activities.NavigationMenuActivity;
 import com.example.pairresearch.activities.RegisterActivity;
 import com.example.pairresearch.models.Researcher;
 import com.example.pairresearch.models.Student;
import com.example.pairresearch.models.User;
import com.example.pairresearch.models.enums.UserType;
import com.example.pairresearch.services.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ApiService apiService = App.getApiService();
    private EditText edit_text_email;
    private EditText edit_text_password;
    private TextView text_view_register;
    private TextView text_view_forgot_password;
    private Button button_login;
    private RadioGroup radio_group_login;
    private UserType userType;



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
            handleRadioButtons();
            if(userType != null && !email.isEmpty() && !password.isEmpty()){
                switch (userType){
                    case Student:
                        apiService.login_student(email, password).enqueue(new Callback<Student>() {
                            @Override
                            public void onResponse(Call<Student> call, Response<Student> response) {
                                if (response.isSuccessful()) {
                                    // Get the logged in user
                                    Student student = response.body();

                                    // Do something with the user object
                                    App.setLoggedUser(student);
                                    App.currentUserType = UserType.Student;

                                    // Handle successful login
                                    Intent intent = new Intent(LoginActivity.this, NavigationMenuActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // Handle login error
                                    Toast.makeText(LoginActivity.this, "Oops! Something didn't match. Try again!", Toast.LENGTH_SHORT).show();
                                    edit_text_email.setText("");
                                    edit_text_password.setText("");
                                }
                            }
                            @Override
                            public void onFailure(Call<Student> call, Throwable t) {
                                // Handle failure
                                Toast.makeText(LoginActivity.this, "Network error. Please try again.", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case Researcher:
                        apiService.login_researcher(email, password).enqueue(new Callback<Researcher>() {
                            @Override
                            public void onResponse(Call<Researcher> call, Response<Researcher> response) {
                                if (response.isSuccessful()) {
                                    // Get the logged in user
                                    Researcher researcher = response.body();

                                    // Do something with the user object
                                    App.setLoggedUser(researcher);
                                    App.currentUserType = UserType.Researcher;

                                    // Handle successful login
                                    Intent intent = new Intent(LoginActivity.this, NavigationMenuActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // Handle login error
                                    Toast.makeText(LoginActivity.this, "Oops! Something didn't match. Try again!", Toast.LENGTH_SHORT).show();
                                    edit_text_email.setText("");
                                    edit_text_password.setText("");
                                }
                            }
                            @Override
                            public void onFailure(Call<Researcher> call, Throwable t) {
                                // Handle failure
                                Toast.makeText(LoginActivity.this, "Network error. Please try again.", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                }
            }
            else{
                Toast.makeText(LoginActivity.this, "Email and Password cannot be empty. Also you must choose a user, Researcher or Student?", Toast.LENGTH_SHORT).show();
            }


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

    private void handleRadioButtons() {
        int selectedId = radio_group_login.getCheckedRadioButtonId();
        if (selectedId == R.id.radio_student_login) {
            userType = UserType.Student;
        } else if (selectedId == R.id.radio_researcher_login) {
            userType = UserType.Researcher;
        }
        else
            userType = null;
    }

    public void findViews(){
        edit_text_email = findViewById(R.id.edit_text_password);
        edit_text_password = findViewById(R.id.edit_text_password);
        button_login = findViewById(R.id.button_login);
        text_view_register = findViewById(R.id.text_view_register);
        text_view_forgot_password = findViewById(R.id.text_view_forgot_password);
        radio_group_login = findViewById(R.id.radio_group_login);
    }
}
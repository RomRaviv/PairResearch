package com.example.pairresearch.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.pairresearch.R;
import com.example.pairresearch.models.enums.UserType;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_IMAGE = 100;

    private Button btn_register_done;
    private Button btn_upload_image;
    private EditText edit_text_name;
    private EditText edit_text_email;
    private EditText edit_text_password;
    private EditText edit_text_password_confirm;
    private EditText edit_text_description;
    private TextInputLayout nameTextInput;
    private TextInputLayout emailTextInput;
    private TextInputLayout passwordTextInput;
    private TextInputLayout confirmPasswordTextInput;
    private TextInputLayout descriptionTextInput;

    private RadioGroup radio_group_register;
    private ImageView iv_image_preview;
    private Uri imageUri;
    private UserType userType;
    private ActivityResultLauncher<String> imagePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViews();
        initViews();

        // Get references to all TextInputLayouts









        imagePickerLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
                uri -> {
                    imageUri = uri;
                    iv_image_preview.setImageURI(uri);
                });
    }

    private void initViews() {
        btn_upload_image.setOnClickListener(v -> imagePickerLauncher.launch("image/*"));

        btn_register_done.setOnClickListener(v -> {
            doneClicked();

        });

        handleRadioButtons();
        //handle radio button
        if(userType != null){
            if(userType == UserType.Researcher){

            }
        }
        else{
        }




    }

    private void handleRadioButtons() {
        int selectedId = radio_group_register.getCheckedRadioButtonId();
        if (selectedId == R.id.radio_student) {
            userType = UserType.Student;
        } else if (selectedId == R.id.radio_researcher) {
            userType = UserType.Researcher;
        }
    }

    private boolean doneClicked() {
        boolean valid = true;
        String toastMsg = "";

        String name = edit_text_name.getText().toString().trim();
        String email = edit_text_email.getText().toString().trim();
        String password = edit_text_password.getText().toString().trim();
        String confirmPassword = edit_text_password_confirm.getText().toString().trim();
        String description = edit_text_description.getText().toString().trim();

        // Check if any TextInputLayouts are empty and show an error message
        if (name.isEmpty()) {
            nameTextInput.setError("Name is required");
            valid = false;
        }
        if (email.isEmpty()) {
            emailTextInput.setError("Email is required");
            valid = false;
        }
        if (password.isEmpty()) {
            passwordTextInput.setError("Password is required");
            valid = false;
        }
        if (confirmPassword.isEmpty()) {
            confirmPasswordTextInput.setError("Please confirm password");
            valid = false;
        }
        if (description.isEmpty()) {
            descriptionTextInput.setError("Description is required");
            valid = false;
        }

        if(!password.equals(confirmPassword)){
            edit_text_password.setText("");
            edit_text_password_confirm.setText("");
            passwordTextInput.setError("Passwords must match!");
            confirmPasswordTextInput.setError("Passwords must match!");
            valid = false;
        }



        return valid;

    }

    private void findViews() {
        btn_register_done = findViewById(R.id.btn_register_next);
        btn_upload_image = findViewById(R.id.btn_upload_image);
        nameTextInput = findViewById(R.id.text_input_name);
        emailTextInput = findViewById(R.id.text_input_email);
        passwordTextInput = findViewById(R.id.text_input_password);
        confirmPasswordTextInput = findViewById(R.id.text_input_password_confirm);
        descriptionTextInput = findViewById(R.id.text_input_description);
        edit_text_name = findViewById(R.id.edit_text_name);
        edit_text_email = findViewById(R.id.edit_text_email);
        edit_text_password = findViewById(R.id.edit_text_password);
        edit_text_password_confirm = findViewById(R.id.edit_text_password_confirm);
        edit_text_description = findViewById(R.id.edit_text_description);
        radio_group_register = findViewById(R.id.radio_group_register);
        iv_image_preview = findViewById(R.id.iv_image_preview);
        userType = null;
    }
}
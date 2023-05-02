package com.example.pairresearch.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pairresearch.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterNextActivity extends AppCompatActivity {

    private TextInputLayout instituteInputLayout;
    private TextInputEditText instituteEditText;
    private TextInputLayout yearOfStudyInputLayout;
    private TextInputEditText yearOfStudyEditText;
    private TextInputLayout qualificationsInputLayout;
    private TextInputEditText qualificationsEditText;
    private Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_next);

        instituteInputLayout = findViewById(R.id.text_input_institute);
        instituteEditText = findViewById(R.id.edit_text_institute);
        yearOfStudyInputLayout = findViewById(R.id.text_input_year_of_study);
        yearOfStudyEditText = findViewById(R.id.edit_text_year_of_study);
        qualificationsInputLayout = findViewById(R.id.text_input_qualifications);
        qualificationsEditText = findViewById(R.id.edit_text_qualifications);
        doneButton = findViewById(R.id.btn_register_student_done);

        doneButton.setOnClickListener(v -> registerStudent());
    }

    private void registerStudent() {
        String institute = instituteEditText.getText().toString().trim();
        String yearOfStudy = yearOfStudyEditText.getText().toString().trim();
        String qualifications = qualificationsEditText.getText().toString().trim();

        if (institute.isEmpty()) {
            instituteInputLayout.setError("Please enter your institute or university.");
            instituteEditText.requestFocus();
            return;
        }

        if (yearOfStudy.isEmpty()) {
            yearOfStudyInputLayout.setError("Please enter your year of study.");
            yearOfStudyEditText.requestFocus();
            return;
        }

        if (qualifications.isEmpty()) {
            qualificationsInputLayout.setError("Please enter your qualifications.");
            qualificationsEditText.requestFocus();
            return;
        }

        // Do something with the user input, such as storing it in a database.
        // ...

        // Go back to the previous activity.
        finish();
    }
}
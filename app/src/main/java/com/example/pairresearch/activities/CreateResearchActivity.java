package com.example.pairresearch.activities;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.pairresearch.R;

public class CreateResearchActivity extends AppCompatActivity {
//    private static final int REQUEST_CODE_IMAGE = 100;

    private Button btn_create_research_done;
    private Button btn_upload_image;
    private ImageView ivImagePreview;
    private Uri imageUri;
    private ActivityResultLauncher<String> imagePickerLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_research);
        btn_create_research_done = findViewById(R.id.btn_create_research_done);



    }
}